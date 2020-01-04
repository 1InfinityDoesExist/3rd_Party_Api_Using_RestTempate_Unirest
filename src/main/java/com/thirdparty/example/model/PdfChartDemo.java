/*
 * package com.thirdparty.example.model;
 * 
 * import java.io.FileOutputStream; import java.io.IOException;
 * 
 * import com.itextpdf.text.DocumentException; import
 * com.itextpdf.text.Rectangle; import com.itextpdf.text.pdf.AcroFields; import
 * com.itextpdf.text.pdf.ColumnText; import
 * com.itextpdf.text.pdf.PdfContentByte; import com.itextpdf.text.pdf.PdfPTable;
 * import com.itextpdf.text.pdf.PdfReader; import
 * com.itextpdf.text.pdf.PdfStamper;
 * 
 * public class PdfChartDemo {
 * 
 * public static void main(String[] args) throws DocumentException, IOException
 * {
 * 
 * manipulatePdf("/home/avinash/Desktop/fileRead/bhai.pdf",
 * "/home/avinash/Desktop/fileRead/HelloWorldStamper2.pdf", 5); }
 * 
 * public static void manipulatePdf(String src, String dest, int no) throws
 * DocumentException, IOException { no = 5; PdfReader reader = new
 * PdfReader("/home/avinash/Desktop/fileRead/fb.pdf"); Rectangle pagesize =
 * reader.getPageSize(1); PdfStamper stamper = new PdfStamper(reader, new
 * FileOutputStream("/home/avinash/Desktop/fileRead/HelloWorldStamper2.pdf"));
 * AcroFields form = stamper.getAcroFields(); form.setField("Name", "Jennifer");
 * form.setField("Company", "iText's next customer"); form.setField("Country",
 * "No Man's Land"); PdfPTable table = new PdfPTable(2); table.addCell("#");
 * table.addCell("description"); table.setHeaderRows(1); table.setWidths(new
 * int[] { 10000000, 800000000 }); for (int i = 1; i <= no; i++) {
 * table.addCell(String.valueOf(i)); table.addCell("test " + i); } ColumnText
 * column = new ColumnText(stamper.getOverContent(1)); Rectangle rectPage1 = new
 * Rectangle(50, 50, 559, 540); column.setSimpleColumn(rectPage1);
 * column.addElement(table); int pagecount = 1; Rectangle rectPage2 = new
 * Rectangle(36, 36, 559, 806); int status = column.go(); while
 * (ColumnText.hasMoreText(status)) { status = triggerNewPage(stamper, pagesize,
 * column, rectPage2, ++pagecount); } stamper.setFormFlattening(true);
 * stamper.close(); reader.close(); }
 * 
 * public static int triggerNewPage(PdfStamper stamper, Rectangle pagesize,
 * ColumnText column, Rectangle rect, int pagecount) throws DocumentException {
 * stamper.insertPage(pagecount, pagesize); PdfContentByte canvas =
 * stamper.getOverContent(pagecount); column.setCanvas(canvas);
 * column.setSimpleColumn(rect); return column.go(); }
 * 
 * }
 */