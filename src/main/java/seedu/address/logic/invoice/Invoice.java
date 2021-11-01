package seedu.address.logic.invoice;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import seedu.address.model.chargeable.Chargeable;
import seedu.address.model.guest.Guest;

public class Invoice {
    public static final String BASE_PATH = "./";
    private static final int NUM_HEADERS = 6;

    /**
     * Generates an invoice for the guests stay.
     * Invoice includes a flat fee for the hotel stay and the sum of all chargeable services that they have consumed.
     *
     * @param g Guest to be charged
     * @throws IOException If invalid file path or fail to add rows to table.
     */
    public static void generateInvoicePdf(Guest g) throws IOException {
        String dest = BASE_PATH + generateFileName(g);

        // Initialize document
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);

        // Add headers and page numbers
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new InvoiceNewPageHandler());

        // Add guest details
        Paragraph guestDetails = new Paragraph("Bill to: " + g.getName() + "\nRoom Number: " + g.getRoomNumber());
        guestDetails.setTextAlignment(TextAlignment.CENTER).setFont(boldFont);
        document.add(guestDetails);

        // Generate invoice table
        document.add(createInvoiceTable(g, font, boldFont));

        Paragraph thankYouParagraph = new Paragraph("THANK YOU FOR YOUR VISIT!");
        thankYouParagraph.setTextAlignment(TextAlignment.CENTER).setFont(boldFont);
        document.add(thankYouParagraph);

        //Close document
        document.close();
    }

    // Adapted solution from https://www.javatpoint.com/java-get-current-date
    private static String generateFileName(Guest g) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();

        return g.getPassportNumber().toString() + " " + now.format(dtf) + ".pdf";
    }

    private static Table createInvoiceTable(Guest g, PdfFont font, PdfFont boldFont) {
        Table table = new Table(new float[]{1, 4, 2, 1, 3, 3});
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        final String[] headerText = new String[]{"ITEM NUM", "VENDOR NAME",
            "SERVICE", "COST", "QUANTITY", "LINE COST"};

        // Ensures that header tally up with fields required
        assert headerText.length == 6;

        // Add header rows
        for (String header : headerText) {
            table.addHeaderCell(
                    new Cell().add(
                            new Paragraph(header).setFont(boldFont)));
        }


        final int hotelCost = 100;

        // Add base price row
        addCellToTable("1", table, font);
        addCellToTable("Hotel", table, font);
        addCellToTable("Hotel Stay", table, font);
        addCellToTable(String.valueOf(hotelCost), table, font);
        addCellToTable("1", table, font);
        addCellToTable(String.valueOf(hotelCost), table, font);

        // Iterate through processed vendors and add to table
        int itemCount = 2;
        double totalCost = hotelCost;
        for (Chargeable charge : g.getChargeableUsed()) {
            addCellToTable(String.valueOf(itemCount), table, font);
            addCellToTable(charge.getName().toString() + " [" + charge.getVendorId().toString() + "]", table, font);
            addCellToTable(charge.getServiceName().toString(), table, font);
            addCellToTable(charge.getCost().toString(), table, font);
            addCellToTable(charge.getQuantity().toString(), table, font);

            double lineCost = Double.parseDouble(charge.getCost().toString())
                    * Double.parseDouble(charge.getQuantity().toString());
            addCellToTable(String.valueOf(lineCost), table, font);

            totalCost += lineCost;
            itemCount++;
        }

        // Round off total cost to 2 d.p
        totalCost = (float) Math.round(totalCost * 100) / 100;

        // Add total cost row
        addCostRowToTable(table, String.valueOf(totalCost), font);

        return table;
    }

    private static void addCellToTable(String text, Table table, PdfFont font) {
        Paragraph paragraph = new Paragraph(text).setFont(font);
        Cell cell = new Cell();
        cell.add(paragraph);
        table.addCell(paragraph);
    }

    /**
     * Adds total cost row at the bottom of the table.
     *
     * Solution adapted from Samuel Huylebroeck
     * https://stackoverflow.com/questions/41607891/itext-7-borderless-table-no-border.
     *
     * @param table     Table to add total cost row.
     * @param totalCost Total cost.
     */
    private static void addCostRowToTable(Table table, String totalCost, PdfFont boldFont) {
        for (int i = 0; i < NUM_HEADERS; i++) {
            Cell cell = new Cell();
            if (i != NUM_HEADERS - 1) {
                cell.add(new Paragraph("").setFont(boldFont));
                cell.setBorder(Border.NO_BORDER);
            } else {
                cell.add(new Paragraph(totalCost).setFont(boldFont));
            }
            table.addCell(cell);
        }
    }
}
