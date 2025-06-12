package com.example.laptopshop.entity;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.io.InputStream;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.awt.Color;
import java.util.Locale;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;


public class SingleOrderExport {
    private Orders hoaDon;
    private Font fontRegular;
    private Font fontTitle;
    private Font fontHeader;
    public SingleOrderExport(Orders hoaDon) {
        this.hoaDon = hoaDon;
        loadFonts(); // Khởi tạo font ở đây
    }
     private void loadFonts() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("fonts/arial.ttf");
            assert is != null;
            BaseFont baseFont = BaseFont.createFont("arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, true, is.readAllBytes(), null);

            fontRegular = new Font(baseFont, 12);
            fontHeader = new Font(baseFont, 12, Font.BOLD, Color.WHITE);
            fontTitle = new Font(baseFont, 18, Font.BOLD, Color.BLUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A6);
        PdfWriter.getInstance(document, response.getOutputStream());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        document.open();

        Paragraph title = new Paragraph("Hóa đơn #"+hoaDon.getId(), fontTitle);
        title.setSpacingAfter(20);
        document.add(title);
        
        Paragraph hoTen = new Paragraph("Khách Hàng: " +hoaDon.getUser().getFullName(),fontRegular);
        document.add(hoTen);

        Paragraph ngayDat = new Paragraph("Ngày đặt: " + formatter.format(hoaDon.getOrderDate()),fontRegular);
        document.add(ngayDat);

        Paragraph diaChi = new Paragraph("Địa chỉ: " +hoaDon.getAddress(),fontRegular);
        document.add(diaChi);

        Paragraph soDienThoai = new Paragraph("SDT: " +hoaDon.getPhone(),fontRegular);
        document.add(soDienThoai);
        
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setSpacingAfter(10);
        table.setWidths(new float[]{1.5f, 6f, 1.5f, 3f, 3f});

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        double tongTien = hoaDon.getOrderItems().stream()
        .mapToDouble(item -> item.getPrice() * item.getQuantity())
        .sum();

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        String tongTienStr = numberFormat.format(tongTien);

        Paragraph tongTienParagraph = new Paragraph("Tổng tiền: " + tongTienStr + " VND", fontRegular);
        tongTienParagraph.setAlignment(Paragraph.ALIGN_RIGHT);
        //tongTienParagraph.setSpacingBefore(10); // Khoảng cách với bảng
        document.add(tongTienParagraph);

        document.close();
    }
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        cell.setPhrase(new Phrase("ID", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Tên", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Phrase("SL", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Đơn giá", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Thành tiền", fontHeader));
        table.addCell(cell);

    }
    private void writeTableData(PdfPTable table) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
    List<OrderItem> ds = hoaDon.getOrderItems();  // hoaDon là đối tượng Orders
    for (OrderItem item : ds) {
        table.addCell(new Phrase(String.valueOf(item.getId()), fontRegular));
        table.addCell(new Phrase(item.getLaptop().getName(), fontRegular));
        table.addCell(new Phrase(String.valueOf(item.getQuantity()), fontRegular));
        table.addCell(new Phrase(numberFormat.format(item.getPrice()), fontRegular));
        double thanhTien = item.getQuantity() * item.getPrice();
        table.addCell(new Phrase(numberFormat.format(thanhTien), fontRegular));
    }
}

}
