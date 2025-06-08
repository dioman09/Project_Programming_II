package Data_Structures;

import Models.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Stacks_Products {

    private final Stack<Product> products_carShop;
    private final Stack<Product> products_purchaseHistory;
    private final Stack<Product> products_shipped;

    public Stacks_Products() {
        this.products_carShop = new Stack<>();
        this.products_purchaseHistory = new Stack<>();
        this.products_shipped = new Stack<>();
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

    public void setPushProduct(Product product) {
        int pos = products_carShop.indexOf(product);
        if (pos == -1) {
            products_carShop.push(product);
        } else {
            System.out.println("Este producto ya se encuentra registrado.");
        }
    }

    public Stack<Product> getProductsEmail(String email) {
        Stack<Product> pila = new Stack<>();
        for (Product aux : products_carShop) {
            if (aux.getEmail_buyer().equals(email)) {
                pila.push(aux);
            }
        }
        return pila;
    }

    public Product getProductEmail(String email) {
        for (Product aux : products_carShop) {
            if (aux.getEmail_buyer().equals(email)) {
                return aux;
            }
        }
        return null;
    }

    public void setPopProductEmail(String email) {
        Product aux = null;
        if (!products_carShop.empty()) {
            aux = getProductEmail(email);
            if ((aux != null) && (products_carShop.remove(aux))) {
                JOptionPane.showMessageDialog(null, "Producto eliminado!");
            } else {
                JOptionPane.showMessageDialog(null, "El producto no existe!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay productos registrados!");
        }
    }

    public Stack<Product> getClonarProductsStack() {
        Stack<Product> caux = new Stack<>();
        int i;
        Product aux = null;
        if (products_carShop == null) {
            return null;
        } else {
            for (i = 0; i < products_carShop.size(); i++) {
                aux = products_carShop.get(i);
                caux.add(i, aux);
            }
            return caux;
        }
    }

    public void save() {
        save_data_in_carShop_file();
    }

    public void take() {
        take_data_from_carShop_file();
    }

    private void save_data_in_carShop_file() {

        String url = System.getProperty("user.dir") + "\\src\\Text_Files\\Data_carShop.txt";

        Path path = Paths.get(url);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), false))) {
            Stack<Product> products = products_carShop;

            for (Product product : products) {
                writer.write(product.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            Logger.getLogger(Stacks_Products.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void take_data_from_carShop_file() {

        String url = System.getProperty("user.dir") + "\\src\\Text_Files\\Data_carShop.txt";

        Path path = Paths.get(url);

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {

            String line;

            if (!products_carShop.isEmpty()) {
                products_carShop.clear();
            }

            while ((line = reader.readLine()) != null) {

                Product product = Product.fromFileString(line);

                setPushProduct(product);
            }
        } catch (IOException e) {
            Logger.getLogger(Stacks_Products.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
