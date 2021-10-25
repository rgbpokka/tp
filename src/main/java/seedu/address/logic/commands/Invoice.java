package seedu.address.logic.commands;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import seedu.address.model.guest.Guest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class Invoice {
    public static final String DEST = "./hello_world.pdf";
    public static final String DATA = "./myFile0.csv";

    private static final PdfFont FONT = Invoice.createStandardFont();
    private static final PdfFont FONT_BOLD = Invoice.createStandardBoldFont();

    // https://stackoverflow.com/questions/1866770/how-to-handle-a-static-final-field-initializer-that-throws-checked-exception
    private static PdfFont createStandardFont() {
        try {
            return PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        } catch (IOException e) {
            // TODO
            return null;
        }
    }

    private static PdfFont createStandardBoldFont() {
        try {
            return PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
        } catch (IOException e) {
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        Invoice.generateInvoicePdf(DEST);
    }

    public static void generateInvoicePdf(String dest) throws IOException {

        // Initialize document
        Document document = createEmptyDocument(dest);

        // ToDo
        Paragraph guestDetails = new Paragraph("Daniel Craner, Room 102");
        document.add(guestDetails);

        document.add(createInvoiceTable());


        //Close document
        document.close();
    }

    private static Document createEmptyDocument(String dest) throws IOException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);

        return new Document(pdf);
    }

    private static Table createInvoiceTable() throws IOException {
        Table table = new Table(new float[]{1, 4, 2, 1, 3});
        BufferedReader br = new BufferedReader(new FileReader(DATA));
        String line = br.readLine();
        processCsv(table, line, FONT_BOLD, true);
        while ((line = br.readLine()) != null) {
            processCsv(table, line, FONT, false);
        }
        br.close();

        return table;
    }

    // https://kb.itextpdf.com/home/it7kb/examples
    private static void processCsv(Table table, String line, PdfFont font, boolean isHeader) {
        StringTokenizer tokenizer = new StringTokenizer(line, ",");
        while (tokenizer.hasMoreTokens()) {
            if (isHeader) {
                table.addHeaderCell(
                        new Cell().add(
                                new Paragraph(tokenizer.nextToken()).setFont(font)));
            } else {
                table.addCell(
                        new Cell().add(
                                new Paragraph(tokenizer.nextToken()).setFont(font)));
            }
        }
    }
}
