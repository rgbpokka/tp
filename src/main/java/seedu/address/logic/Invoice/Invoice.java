package seedu.address.logic.Invoice;

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
import seedu.address.model.Chargeable.Chargeable;
import seedu.address.model.guest.Guest;

import java.io.IOException;

public class Invoice {
    public static final String DEST = "./hello_world.pdf";
    public static final String DATA = "./myFile0.csv";
    public static final String DATA2 = "./guestInvoice.csv";


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
     * @throws IOException
     */
    public static void generateInvoicePdf(Guest g) throws IOException {
        String dest = DEST;
        // Initialize document
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Add headers and page numbers
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new InvoiceNewPageHandler());

        // Add guest details
        Paragraph guestDetails = new Paragraph("Bill to: " + g.getName() + " Room Number: " + g.getRoomNumber());
        document.add(guestDetails);

        // Generate invoice table
        document.add(createInvoiceTable(g));


        //Close document
        document.close();
    }

    private static Table createInvoiceTable(Guest g) throws IOException {
        Table table = new Table(new float[]{1, 4, 2, 1, 3, 3});

        final String[] HEADER_TEXT = new String[] {"Item num", "Vendor Name", "Service", "Cost", "Quantity", "Line Cost"};

        // Ensures that header tally up with fields required
        assert HEADER_TEXT.length == 6;

        // Add header rows
        for (String header : HEADER_TEXT) {
                table.addHeaderCell(
                        new Cell().add(
                                new Paragraph(header).setFont(FONT)));
        }


        int HotelCost = 100;

        // charge base price
        addCellToTable("1", table, FONT);
        addCellToTable("Hotel", table, FONT);
        addCellToTable("Hotel Stay", table, FONT);
        addCellToTable(String.valueOf(HotelCost), table, FONT);
        addCellToTable("1", table, FONT);
        addCellToTable(String.valueOf(HotelCost), table, FONT);

        // Iterate through processed vendors and add to table
        int itemCount = 2;
        double totalCost = HotelCost;
        for (Chargeable charge : g.getChargableUsed()) {
            addCellToTable(String.valueOf(itemCount), table, FONT);
            addCellToTable(charge.getName().toString(), table, FONT);
            addCellToTable(charge.getServiceName().toString(), table, FONT);
            addCellToTable(charge.getCost().toString(), table, FONT);
            addCellToTable(charge.getQuantity().toString(), table, FONT);

            double lineCost = Double.valueOf(charge.getCost().toString()) * Double.valueOf(charge.getQuantity().toString());
            addCellToTable(String.valueOf(lineCost), table, FONT);

            totalCost += lineCost;
            itemCount++;
        }

        // Add total cost row
        addCostRowToTable(table, String.valueOf(totalCost), FONT);

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
     * @param table Table to add total cost row.
     * @param totalCost Total cost.
     * @param font Font for text.
     */
    private static void addCostRowToTable(Table table, String totalCost, PdfFont font) {
        for (int i = 0; i < NUM_HEADERS; i++) {
           if (i != NUM_HEADERS - 1) {
                Cell cell = new Cell();
                cell.add(new Paragraph("").setFont(font));
                cell.setBorder(Border.NO_BORDER);
                table.addCell(cell);
            } else {
                   Cell cell = new Cell();
                   cell.add(new Paragraph(totalCost).setFont(font));
                   table.addCell(cell);
           }
        }
    }
}
