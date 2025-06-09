package Data_Structures;

import Models.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
