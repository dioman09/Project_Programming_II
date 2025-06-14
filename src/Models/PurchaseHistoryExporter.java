package Models;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseHistoryExporter {

    private final Stack<Product> purchaseHistory;

    public PurchaseHistoryExporter(Stack<Product> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    public void exportPurchaseHistoryPdf(String outputDirectory) throws IOException {
        validateOutputDirectory(outputDirectory);

        Document document = new Document();
        try {
            String filePath = buildFilePath(outputDirectory);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();
            configureDocumentMetadata(document);

            addTitle(document);
            document.add(createPurchaseHistoryTable());

        } catch (DocumentException e) {
            handleDocumentException(e);
        } finally {
            closeDocumentSafely(document);
        }
    }

    private void validateOutputDirectory(String directory) throws IOException {
        if (directory == null || directory.trim().isEmpty()) {
            throw new IOException("El directorio de salida no puede estar vacío");
        }

        File dir = new File(directory);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IOException("El directorio especificado no existe o no es válido");
        }
    }

    private String buildFilePath(String directory) {
        String fileName = "DCM_Historial_Compras_"
                + LocalDate.now().format(DateTimeFormatter.ISO_DATE) + ".pdf";
        return Paths.get(directory, fileName).toString();
    }

    private void configureDocumentMetadata(Document document) {
        document.addTitle("Historial de Compras");
        document.addSubject("Listado de productos comprados");
        document.addKeywords("Compras, Historial, Productos");
        document.addAuthor("Sistema de Gestión de Compras");
        document.addCreator("PurchaseHistoryExporter");
    }

    private void addTitle(Document document) throws DocumentException {
        Font titleFont = createFont(BaseFont.HELVETICA_BOLD, 16);
        Paragraph title = new Paragraph("LISTADO DE COMPRAS - TABLA", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20f);
        document.add(title);
    }

    private PdfPTable createPurchaseHistoryTable() throws DocumentException {
        PdfPTable table = new PdfPTable(6);
        configureTableAppearance(table);

        Font headerFont = createFont(BaseFont.HELVETICA_BOLDOBLIQUE, 12);
        addTableHeader(table, headerFont,
                "IdProducto", "Marca", "Tipo",
                "Precio", "Comprador", "Fecha Compra");

        Font contentFont = createFont(BaseFont.HELVETICA, 10);
        addPurchaseHistoryRows(table, contentFont);

        return table;
    }

    private void configureTableAppearance(PdfPTable table) {
        try {
            table.setWidthPercentage(100);
            table.setSpacingBefore(15f);
            table.setSpacingAfter(15f);
            table.setWidths(new float[]{1, 2, 2, 1.5f, 2, 2});
        } catch (DocumentException ex) {
            Logger.getLogger(PurchaseHistoryExporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addTableHeader(PdfPTable table, Font font, String... headers) {
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, font));
            cell.setBackgroundColor(new BaseColor(220, 220, 220));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(5);
            table.addCell(cell);
        }
    }

    private void addPurchaseHistoryRows(PdfPTable table, Font font) {
        for (Product producto : purchaseHistory) {
            addProductRow(table, producto, font);
        }
    }

    private void addProductRow(PdfPTable table, Product producto, Font font) {
        table.addCell(createCell(String.valueOf(producto.getId()), font));
        table.addCell(createCell(producto.getBrand(), font));
        table.addCell(createCell(producto.getName(), font));
        table.addCell(createCell(String.format("$%,.2f", producto.getPrice()), font));
        table.addCell(createCell(producto.getEmail_buyer(), font));
        table.addCell(createCell(formatPurchaseDate(producto.getDate_purchase()), font));
    }

    private PdfPCell createCell(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setPadding(5);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private String formatPurchaseDate(LocalDateTime fechaCompra) {
        if (fechaCompra == null) {
            return "N/A";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(fechaCompra);
    }

    private Font createFont(String fontName, float size) {
        try {
            BaseFont baseFont = BaseFont.createFont(fontName, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            return new Font(baseFont, size);
        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Error creando fuente", e);
        }
    }

    private void handleDocumentException(DocumentException e) {
        Logger.getLogger(PurchaseHistoryExporter.class.getName())
                .log(Level.SEVERE, "Error al generar el documento PDF", e);
        throw new RuntimeException("Error generando el PDF", e);
    }

    private void closeDocumentSafely(Document document) {
        if (document != null && document.isOpen()) {
            document.close();
        }
    }
}
