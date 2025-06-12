package Models;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.logging.*;

public class ProductCatalogExporter {

    private final Stack<Product> products_all;
    
    public ProductCatalogExporter(Stack<Product> products) {
        this.products_all = products;
    }

    public void exportCatalogPdf(String outputDirectory, String gender) throws IOException {
        validateInputParameters(outputDirectory, gender);

        Document document = new Document();
        try {
            String filePath = buildFilePath(outputDirectory, gender);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();
            configureDocumentMetadata(document, gender);

            addTitle(document, gender);
            document.add(createProductsTable(gender));

        } catch (DocumentException e) {
            handleDocumentException(e);
        } finally {
            closeDocumentSafely(document);
        }
    }
    
    private void validateInputParameters(String directory, String gender) throws IOException {
        if (directory == null || directory.trim().isEmpty()) {
            throw new IOException("El directorio de salida no puede estar vacío");
        }

        File dir = new File(directory);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IOException("El directorio especificado no existe o no es válido");
        }

        if (!gender.equalsIgnoreCase("Hombre")
                && !gender.equalsIgnoreCase("Mujer")) {
            throw new IllegalArgumentException("Género no válido. Valores aceptados: Hombre, Mujer, Unisex");
        }
    }

    private String buildFilePath(String directory, String gender) {
        String fileName = String.format("NIKE-STORE-CATALOGO-%s-%s.pdf",
                gender.toUpperCase(),
                LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        return Paths.get(directory, fileName).toString();
    }

    private void configureDocumentMetadata(Document document, String gender) {
        document.addTitle("Catálogo Nike " + gender);
        document.addSubject("Catálogo de productos");
        document.addKeywords("Nike, Catálogo, " + gender);
        document.addAuthor("Nike Store");
        document.addCreator("Sistema de Generación de Catálogos");
    }

    private void addTitle(Document document, String gender) throws DocumentException {
        Font titleFont = createFont(BaseFont.HELVETICA_BOLD, 18);
        Paragraph title = new Paragraph("CATÁLOGO " + gender.toUpperCase(), titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20f);
        document.add(title);
    }

    private PdfPTable createProductsTable(String gender) throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        configureTableAppearance(table);

        Font headerFont = createFont(BaseFont.HELVETICA_BOLDOBLIQUE, 12);
        addTableHeader(table, headerFont, "Imagen", "Información", "Descripción");

        Font contentFont = createFont(BaseFont.HELVETICA, 10);
        for (Product product : products_all) {
            String sex = gender.equals("Hombre") ? "MASCULINO" : "FEMENINO";

            if (product.getSex().equals(sex)) {
                addProductToTable(table, product, contentFont);
            }
        }

        return table;
    }

    private void configureTableAppearance(PdfPTable table) throws DocumentException {
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);
        table.setWidths(new float[]{2, 3, 4});
    }

    private void addTableHeader(PdfPTable table, Font font, String... headers) {
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, font));
            cell.setBackgroundColor(new BaseColor(220, 220, 220));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8);
            table.addCell(cell);
        }
    }

    private void addProductToTable(PdfPTable table, Product product, Font font) throws DocumentException {     

        addProductImage(table, product);
        
        addProductInfoCell(table, product, font);
        
        addProductDescCell(table, product, font);
    }

    private void addProductImage(PdfPTable table, Product product) {
        try {
            String imagePath = product.getUrls_images().get(0);
            if (new File(imagePath).exists()) {
                Image image = Image.getInstance(imagePath);
                image.scaleToFit(150, 150);
                image.setAlignment(Image.MIDDLE);

                PdfPCell imageCell = new PdfPCell(image, true);
                imageCell.setPadding(5);
                imageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                imageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(imageCell);
            } else {
                addPlaceholderCell(table, "Imagen no disponible");
            }
        } catch (BadElementException | IOException e) {
            addErrorCell(table, "Error cargando imagen");
        }
    }

    private void addProductInfoCell(PdfPTable table, Product product, Font font) {
        String info = buildProductInfo(product);
        PdfPCell infoCell = new PdfPCell(new Phrase(info, font));
        infoCell.setPadding(5);
        infoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(infoCell);
    }
    
    private void addProductDescCell(PdfPTable table, Product product, Font font) {
        String description = product.getDescription();
        PdfPCell infoCell = new PdfPCell(new Phrase(description, font));
        infoCell.setPadding(5);
        infoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(infoCell);
    }

    private String buildProductInfo(Product product) {
        return String.format(
                "Nombre: %s\n\nMarca: %s\n\nGénero: %s\n\nPrecio: $%,.2f\n\nTallas: %s",
                product.getName(),
                product.getBrand(),
                product.getSex(),
                product.getPrice(),
                String.join(", ", product.getSize())
        );
    }

    private void addPlaceholderCell(PdfPTable table, String message) {
        PdfPCell cell = new PdfPCell(new Phrase(message));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
    }

    private void addErrorCell(PdfPTable table, String message) {
        Font errorFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.RED);
        PdfPCell cell = new PdfPCell(new Phrase(message, errorFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
    }

    private Font createFont(String fontName, float size) {
        try {
            BaseFont baseFont = BaseFont.createFont(fontName, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            return new Font(baseFont, size);
        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Error generando el PDF", e);
        }
    }

    private void handleDocumentException(DocumentException e) {
        Logger.getLogger(ProductCatalogExporter.class.getName())
                .log(Level.SEVERE, "Error al generar el documento PDF", e);
        throw new RuntimeException("Error generando el PDF", e);
    }

    private void closeDocumentSafely(Document document) {
        if (document != null && document.isOpen()) {
            document.close();
        }
    }
}
