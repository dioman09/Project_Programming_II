package Data_Structures;

import Models.Product;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Stacks_Products {

    private final Stack<Product> products_carShop;
    private final Stack<Product> products_purchaseHistory;
    private final Stack<Product> products_shipped;
    private final Stack<Product> products_all;

    public Stacks_Products() {
        this.products_carShop = new Stack<>();
        this.products_purchaseHistory = new Stack<>();
        this.products_shipped = new Stack<>();
        this.products_all = new Stack<>();
    }

    public Stack<Product> getProducts_carShop() {
        return products_carShop;
    }

    public Stack<Product> getProducts_purchaseHistory() {
        return products_purchaseHistory;
    }

    public Stack<Product> getProducts_shipped() {
        return products_shipped;
    }

    public Stack<Product> getProducts_all() {
        return products_all;
    }

    public void pushProduct(Stack<Product> stack, Product product) {
        if (!stack.contains(product)) {
            stack.push(product);
        } else {
            System.out.println("Este producto ya se encuentra registrado.");
        }
    }

    public Product search_product_by_email_and_id(Stack<Product> stack, String email, String id) {
        for (Product product : stack) {
            if (product.getEmail_buyer().equals(email) && id.equals(product.getId())) {
                return product;
            }
        }
        return null;
    }

    public Stack<Product> getProductsBySex(Stack<Product> stack, String sex) {
        Stack<Product> filtered = new Stack<>();
        for (Product product : stack) {
            if (product.getSex().equals(sex)) {
                filtered.push(product);
            }
        }
        return filtered;
    }

    public Stack<Product> getProductsByEmail(Stack<Product> stack, String email) {
        Stack<Product> filtered = new Stack<>();
        for (Product product : stack) {
            if (product.getEmail_buyer().equals(email)) {
                filtered.push(product);
            }
        }
        return filtered;
    }

    public void remove_product_by_email_and_id(Stack<Product> stack, String email, String id) {
        Product product = search_product_by_email_and_id(stack, email, id);
        if (!stack.isEmpty()) {
            if (product != null && stack.remove(product)) {
                JOptionPane.showMessageDialog(null, "Producto eliminado!");
            } else {
                JOptionPane.showMessageDialog(null, "El producto no existe!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay productos registrados!");
        }
    }

    public Stack<Product> cloneStack(Stack<Product> stack) {
        return new Stack<Product>() {
            {
                addAll(stack);
            }
        };
    }

    public void saveStackToFile(Stack<Product> stack, String filename) {
        String path = System.getProperty("user.dir") + "\\src\\Text_Files\\" + filename;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            for (Product product : stack) {
                writer.write(product.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            Logger.getLogger(Stacks_Products.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void loadStackFromFile(Stack<Product> stack, String filename) {
        String path = System.getProperty("user.dir") + "\\src\\Text_Files\\" + filename;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            stack.clear();
            while ((line = reader.readLine()) != null) {
                Product product = Product.fromFileString(line);
                if (product != null) {
                    stack.push(product);
                }
            }
        } catch (IOException e) {
            Logger.getLogger(Stacks_Products.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void export_catalog_pdf(String location, String sex) throws FileNotFoundException, IOException {
        String info = "                CATALOGO " + sex + "\n" + "\n";
        @SuppressWarnings("unchecked")
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(location + "NIKE-STORE CATALOGO " + sex + ".pdf"));
            document.open();

            BaseFont bf1 = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            Font font1 = new Font(bf1, 14);

            BaseFont bf2 = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            Font font2 = new Font(bf2, 12);

            BaseFont bf3 = BaseFont.createFont(BaseFont.HELVETICA_BOLDOBLIQUE, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            Font font3 = new Font(bf3, 12);

            PdfPTable table = new PdfPTable(2);

            String[] headers = {"Modelo", "Descripcion"};

            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell();
                headerCell.setPhrase(new com.itextpdf.text.Phrase(header, font3));
                table.addCell(headerCell);
            }

            for (Product product : products_all) {
                if (product.getSex().equals(sex)) {
                    String info_product = "\n\nNombre: " + product.getName() + "\n\n"
                            + "Marca: " + product.getBrand() + "\n\n"
                            + "Sexo: " + product.getSex() + "\n\n"
                            + "Precio: " + String.valueOf(product.getPrice()) + "\n\n"
                            + "Tallas: " + product.getSize();

                    PdfPCell celda = new PdfPCell();

                    String imagePath = product.getUrls_images().get(0);
                    File imageFile = new File(imagePath);

                    if (imageFile.exists()) {
                        try {
                            Image imagen = Image.getInstance(imagePath);
                            celda.addElement(imagen);
                            table.addCell(celda);
                        } catch (IOException | DocumentException e) {
                        }
                    } else {
                        System.out.println("La ruta de la imagen no existe: " + imagePath);
                    }

                    table.addCell(new Paragraph(info_product, font2));
                }
            }
            Paragraph parag = new Paragraph(info, font1);
            parag.setAlignment(Element.ALIGN_CENTER);
            document.add(parag);
            document.add(table);

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            Logger.getLogger(Stacks_Products.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void saveCarShop() {
        saveStackToFile(products_carShop, "Data_carShop.txt");
    }

    public void loadCarShop() {
        loadStackFromFile(products_carShop, "Data_carShop.txt");
    }

    public void savePurchaseHistory() {
        saveStackToFile(products_purchaseHistory, "Data_history.txt");
    }

    public void loadPurchaseHistory() {
        loadStackFromFile(products_purchaseHistory, "Data_history.txt");
    }

    public void saveShipped() {
        saveStackToFile(products_shipped, "Data_shipped.txt");
    }

    public void loadShipped() {
        loadStackFromFile(products_shipped, "Data_shipped.txt");
    }

    public void saveAll() {
        saveStackToFile(products_all, "Data_products.txt");
    }

    public void loadAll() {
        loadStackFromFile(products_all, "Data_products.txt");
    }
}
