package seedu.address.logic.commands;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import seedu.address.InvoiceNewPageHandler;
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
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new InvoiceNewPageHandler());
        // ToDo
        Paragraph guestDetails = new Paragraph("Daniel Craner, Room 102");
        document.add(guestDetails);

        document.add(createInvoiceTable());


        //Close document
        document.close();
    }

    private static Table createInvoiceTable() throws IOException {
        Table table = new Table(new float[]{1, 4, 2, 1, 3});
        BufferedReader br = new BufferedReader(new FileReader(DATA));
        String currentLine = br.readLine();

        // Add header row
        addRowToTable(table, currentLine, FONT_BOLD, true);

        // Process all but last row
        String oneLineAhead = br.readLine();
        String twoLinesAhead = br.readLine();
        while ((twoLinesAhead != null)) { // oneLineAhead contain last row, when loop breaks
            currentLine = oneLineAhead;
            oneLineAhead = twoLinesAhead;
            twoLinesAhead = br.readLine();
            addRowToTable(table, currentLine, FONT, false);
        }

        // add total cost
        addCostRowToTable(table, oneLineAhead, FONT);
        br.close();

        return table;
    }

    // https://kb.itextpdf.com/home/it7kb/examples
    private static void addRowToTable(Table table, String line, PdfFont font, boolean isHeader) {
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

    // https://stackoverflow.com/questions/41607891/itext-7-borderless-table-no-border
    private static void addCostRowToTable(Table table, String line, PdfFont font) {
        StringTokenizer tokenizer = new StringTokenizer(line, ",");
        while (tokenizer.hasMoreTokens()) {
            String currentToken = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens()) {
                Cell cell = new Cell();
                cell.add(new Paragraph(currentToken).setFont(font));
                table.addCell(cell);
            } else {
                Cell cell = new Cell();
                cell.add(new Paragraph("fluff").setFont(font));
                cell.setBorder(Border.NO_BORDER);
                table.addCell(cell);
            }
        }
    }
}
