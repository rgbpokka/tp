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
import seedu.address.model.Chargeable.Chargeable;
import seedu.address.model.guest.Guest;

public class Invoice {
    public static final String BASE_PATH = "./";
    private static final PdfFont FONT = Invoice.createStandardFont();
    private static final PdfFont FONT_BOLD = Invoice.createStandardBoldFont();
    private static final int NUM_HEADERS = 6;

    // Solution adapted from
    // https://stackoverflow.com/questions/1866770/
    // how-to-handle-a-static-final-field-initializer-that-throws-checked-exception
    private static PdfFont createStandardFont() {
        try {
            return PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        } catch (IOException e) {
            // TODO better way of catching exception
            return null;
        }
    }

    // todo better way of catching exception
    private static PdfFont createStandardBoldFont() {
        try {
            return PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
        } catch (IOException e) {
            return null;
        }
    }

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

        // Add headers and page numbers
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new InvoiceNewPageHandler());

        // Add guest details
        Paragraph guestDetails = new Paragraph("Bill to: " + g.getName() + "\nRoom Number: " + g.getRoomNumber());
        guestDetails.setTextAlignment(TextAlignment.CENTER);
        document.add(guestDetails);

        // Generate invoice table
        document.add(createInvoiceTable(g));


        //Close document
        document.close();
    }

    // Adapted solution from https://www.javatpoint.com/java-get-current-date
    private static String generateFileName(Guest g) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();

        return g.getPassportNumber().toString() + " " + now.format(dtf) + ".pdf";
    }

    private static Table createInvoiceTable(Guest g) {
        Table table = new Table(new float[]{1, 4, 2, 1, 3, 3});
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        final String[] HEADER_TEXT = new String[]{"ITEM NUM", "VENDOR NAME",
            "SERVICE", "COST", "QUANTITY", "LINE COST"};

        // Ensures that header tally up with fields required
        assert HEADER_TEXT.length == 6;

        // Add header rows
        for (String header : HEADER_TEXT) {
            table.addHeaderCell(
                    new Cell().add(
                            new Paragraph(header).setFont(FONT_BOLD)));
        }


        int hotelCost = 100;

        // Charge base price
        addCellToTable("1", table);
        addCellToTable("Hotel", table);
        addCellToTable("Hotel Stay", table);
        addCellToTable(String.valueOf(hotelCost), table);
        addCellToTable("1", table);
        addCellToTable(String.valueOf(hotelCost), table);

        // Iterate through processed vendors and add to table
        int itemCount = 2;
        double totalCost = hotelCost;
        for (Chargeable charge : g.getChargeableUsed()) {
            addCellToTable(String.valueOf(itemCount), table);
            addCellToTable(charge.getName().toString() + " [" + charge.getVendorId().toString() + "]", table);
            addCellToTable(charge.getServiceName().toString(), table);
            addCellToTable(charge.getCost().toString(), table);
            addCellToTable(charge.getQuantity().toString(), table);

            double lineCost = Double.parseDouble(charge.getCost().toString())
                    * Double.parseDouble(charge.getQuantity().toString());
            addCellToTable(String.valueOf(lineCost), table);

            totalCost += lineCost;
            itemCount++;
        }

        // Add total cost row
        addCostRowToTable(table, String.valueOf(totalCost));

        return table;
    }

    private static void addCellToTable(String text, Table table) {
        Paragraph paragraph = new Paragraph(text).setFont(FONT);
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
    private static void addCostRowToTable(Table table, String totalCost) {
        for (int i = 0; i < NUM_HEADERS; i++) {
            Cell cell = new Cell();
            if (i != NUM_HEADERS - 1) {
                cell.add(new Paragraph("").setFont(FONT_BOLD));
                cell.setBorder(Border.NO_BORDER);
            } else {
                cell.add(new Paragraph(totalCost).setFont(FONT_BOLD));
            }
            table.addCell(cell);
        }
    }
}
