package Data_Structures;

import Models.Admin;
import Models.Client;
import Models.Node_User;
import Models.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Circular_List_Users {

    private Node_User head;

    public Circular_List_Users() {
        this.head = null;
    }

    public Node_User getHead() {
        return head;
    }

    public void setHead(Node_User head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clean() {
        head = null;
    }

    public void Alert(Alert.AlertType alertType, String tit, String mj) {
        Alert a = new Alert(alertType);
        a.setTitle(tit);
        a.setContentText(mj);
        a.showAndWait();
    }

    public User searchByEmail(String email) {
        if (isEmpty()) {
            return null;
        }

        Node_User current = head;
        do {
            if (current.getUser().getEmail().equals(email)) {
                return current.getUser();
            }
            current = current.getNext();
        } while (current != head);
        return null;
    }

    public void addToEnd(User user) {
        Node_User newNode = new Node_User(user);

        if (isEmpty()) {
            head = newNode;
            head.setNext(head);
            head.setFormer(head);
        } else {
            Node_User last = head.getFormer();

            last.setNext(newNode);
            newNode.setFormer(last);
            newNode.setNext(head);
            head.setFormer(newNode);
        }
    }

    public void addNodeUser(TextField txt_name, TextField txt_email, TextField txt_phone, ComboBox comb_sex, PasswordField txt_password, Label label_user) {

        User user = createUser(txt_name, txt_email, txt_phone, comb_sex, txt_password, label_user);

        if (user != null) {
            addToEnd(user);
        }
    }

    public User createUser(TextField txt_name, TextField txt_email, TextField txt_phone, ComboBox comb_sex, PasswordField txt_password, Label label_user) {

        User search = searchByEmail(txt_email.getText());

        try {
            if (search != null) {
                Alert(Alert.AlertType.WARNING, "Importante..!",
                        "Ya existe un usuario con este correo");
                return null;
            } else {

                User newUser = null;

                if (label_user.getText().equals("Admin")) {
                    newUser = new Admin(txt_name.getText(), comb_sex.getValue().toString(), txt_email.getText(), txt_password.getText(), Long.parseLong(txt_phone.getText()));
                } else {
                    newUser = new Client(txt_name.getText(), comb_sex.getValue().toString(), txt_email.getText(), txt_password.getText(), Long.parseLong(txt_phone.getText()));
                }

                Alert(Alert.AlertType.CONFIRMATION, "Dialogo de confirmación",
                        "Registro realizado con exito...!\n"
                        + "Felicidades...! ya haces parte de nuestros usuarios :)");
                txt_name.setText("");
                txt_email.setText("");
                txt_phone.setText("");
                comb_sex.getSelectionModel().clearSelection();
                txt_password.setText("");
                return newUser;
            }
        } catch (NumberFormatException e) {
            Logger.getLogger(Circular_List_Users.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public void save() {
        save_information_in_user_file();
    }

    public void take() {
        take_data_from_user_file();
    }

    public void save_information_in_user_file() {

        String direccion = System.getProperty("user.dir") + "\\src\\Text_Files\\Data_users.txt";

        Path archivo = Paths.get(direccion);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo.toFile(), false))) {
            Node_User current = head;

            do {                
                writer.write(current.getUser().toFileString());
                writer.newLine();

                current = current.getNext();
            } while (current != head);
        } catch (IOException e) {
            Logger.getLogger(Circular_List_Users.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void take_data_from_user_file() {

        String direccion = System.getProperty("user.dir") + "\\src\\Text_Files\\Data_users.txt";

        Path file = Paths.get(direccion);

        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {

            String line;

            clean();

            while ((line = reader.readLine()) != null) {

                User user = User.parseUser(line);

                addToEnd(user);
            }
        } catch (IOException e) {
            Logger.getLogger(Circular_List_Users.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
