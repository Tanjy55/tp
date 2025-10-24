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
import java.util.Arrays;
import java.util.List;
import java.awt.Color;

public class PDFWriter {
    // Mock data classes
    static class Item {
        String description;
        int quantity;
        double price;
        public Item(String description, int quantity, double price) {
            this.description = description;
            this.quantity = quantity;
            this.price = price;
        }
        public double getTotal() { 
            return quantity * price; 
        }
    }

    public static void main(String[] args) {
        // Invoice data
        String invoiceNumber = "INV-1001";
        String customerName = "John Doe";
        String customerAddress = "123 Main Street, City, Country";

        List<Item> items = Arrays.asList(
                new Item("Laptop", 1, 1200.00),
                new Item("Mouse", 2, 25.50),
                new Item("Keyboard", 1, 45.75)
        );

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

            // Add table for items
            PdfPTable table = new PdfPTable(4); // 4 columns
            table.setWidthPercentage(100);
            table.setWidths(new float[]{4, 1, 2, 2});

            // Table header
            Font headFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            String[] headers = {"Description", "Qty", "Unit Price", "Total"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h, headFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(230, 230, 230));
                table.addCell(cell);
            }

            // Table rows
            for (Item item : items) {
                table.addCell(new PdfPCell(new Phrase(item.description)));
                
                PdfPCell qtyCell = new PdfPCell(new Phrase(String.valueOf(item.quantity)));
                qtyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(qtyCell);

                PdfPCell priceCell = new PdfPCell(new Phrase(String.format("$%.2f", item.price)));
                priceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(priceCell);

                PdfPCell totalCell = new PdfPCell(new Phrase(String.format("$%.2f", item.getTotal())));
                totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(totalCell);
            }

            // Add subtotal
            double subtotal = items.stream().mapToDouble(Item::getTotal).sum();
            PdfPCell emptyCell = new PdfPCell(new Phrase(""));
            emptyCell.setColspan(3);
            emptyCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(emptyCell);

            PdfPCell subtotalCell = new PdfPCell(new Phrase(String.format("$%.2f", subtotal), headFont));
            subtotalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(subtotalCell);

            document.add(table);

            document.close();
            writer.close();
            System.out.println("Invoice PDF created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
