package seedu.quotely.writer;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Chunk;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;
import java.awt.Color;

import seedu.quotely.data.CompanyName;
import seedu.quotely.data.Quote;
import seedu.quotely.data.Item;

public class PDFWriter {
    private static PDFWriter writer = null;

    private PDFWriter() {
    }

    public static PDFWriter getInstance() {
        if (writer == null) {
            writer = new PDFWriter();
        }
        return writer;
    }

    public void writeQuoteToPDF(Quote quote, CompanyName companyName) {
        // Invoice data
        String invoiceNumber = "INV-1001";
        String customerName = "John Doe";
        String customerAddress = "123 Main Street, City, Country";
        List<Item> items = quote.getItems();

        try {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("invoice.pdf"));
            document.open();

            // Add invoice title
            Font titleFont = new Font(Font.HELVETICA, 20, Font.BOLD);
            Paragraph title = new Paragraph("INVOICE", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(Chunk.NEWLINE);

            // Add invoice info
            Font infoFont = new Font(Font.HELVETICA, 12);
            Paragraph invoiceInfo = new Paragraph("Invoice Number: " + invoiceNumber + "\n" + 
                "Customer: " + customerName + "\n" + 
                "Address: " + customerAddress, infoFont);
            document.add(invoiceInfo);

            document.add(Chunk.NEWLINE);

            // Table header
            Font headFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            String[] headers = {"Description", "Unit Price", "Qty", "Taxed", "Amount"};
            int columnNumber = headers.length;

            // Add table for items
            PdfPTable table = new PdfPTable(columnNumber); // 5 columns
            table.setWidthPercentage(100);
            table.setWidths(new float[]{4, 2, 1, 2, 2});

            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h, headFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(230, 230, 230));
                table.addCell(cell);
            }
            double subtotal = 0.0;
            double totalTax = 0.0;

            // Table rows
            for (Item item : items) {
                table.addCell(new PdfPCell(new Phrase(item.getItemName())));

                PdfPCell priceCell = new PdfPCell(new Phrase(String.format("%.2f", item.getPrice())));
                priceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(priceCell);
                
                PdfPCell qtyCell = new PdfPCell(new Phrase(String.valueOf(item.getQuantity())));
                qtyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(qtyCell);

                PdfPCell taxCell = new PdfPCell(new Phrase(String.format("%.2f %%", item.getTaxRate())));
                taxCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(taxCell);

                PdfPCell totalCell = new PdfPCell(new Phrase(String.format("%.2f", 
                    item.getItemTotalTax() + item.getItemTotalPriceWithoutTax())));
                totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(totalCell);

                subtotal += item.getItemTotalPriceWithoutTax();
                totalTax += item.getItemTotalTax();
            }

            
            PdfPCell emptyCell = new PdfPCell(new Phrase(""));
            emptyCell.setColspan(columnNumber - 1);
            emptyCell.setBorder(Rectangle.NO_BORDER);

            // Add subtotal
            table.addCell(emptyCell);
            PdfPCell subtotalCell = new PdfPCell(new Phrase(String.format("$ %.2f", subtotal), headFont));
            subtotalCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            subtotalCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(subtotalCell);

            // Add total tax
            table.addCell(emptyCell);
            PdfPCell totalTaxCell = new PdfPCell(new Phrase(String.format("$ %.2f", totalTax), headFont));
            totalTaxCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            totalTaxCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(totalTaxCell);

            // Add grand total
            table.addCell(emptyCell);
            PdfPCell totalCell = new PdfPCell(new Phrase(String.format("$ %.2f", subtotal + totalTax), headFont));
            totalCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            totalCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(totalCell);

            document.add(table);

            document.close();
            writer.close();
            System.out.println("Invoice PDF created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
