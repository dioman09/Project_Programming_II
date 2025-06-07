package Controllers;

import Data_Structures.Circular_List_Users;
import Data_Structures.Data_Singleton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Controller_View_SigningUP implements Initializable {

    private final Circular_List_Users list_users = Data_Singleton.getInstance().getList_users();

    @FXML
    private Button btn_signin;
    @FXML
    private TextField txt_phone;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_name;
    @FXML
    private PasswordField txt_passwordC;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Label label_user;
    @FXML
    private ComboBox<String> comb_sex;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] sex = {"MASCULINO", "FEMENINO"};

        comb_sex.setItems(FXCollections.observableArrayList(sex));
    }

    public void run_arranque() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/View_Arranque.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            Stage miStage = (Stage) this.btn_signin.getScene().getWindow();
            miStage.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller_View_SigningUP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run_login(String type) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/View_Login.fxml"));
            Parent root = loader.load();

            Controller_View_Login controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);

            stage.setOnShown(event -> controller.seeIma(type));

            stage.setOnCloseRequest((WindowEvent value) -> {
                controller.run_arranque();
            });

            stage.show();

            Stage miStage = (Stage) this.btn_signin.getScene().getWindow();
            miStage.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller_View_SigningUP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Label getLabel_user() {
        return label_user;
    }

    @FXML
    private void eventKey(KeyEvent event) {
    }

    @FXML
    private void eventAction(ActionEvent event) {
        addUser(txt_name, txt_email, txt_phone, comb_sex, txt_password, label_user);
    }

    public void addUser(TextField txt_name, TextField txt_email, TextField txt_phone, ComboBox comb_sex, PasswordField txt_password, Label label_user) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setHeaderText("Mensaje de informacion");
        a.setTitle("Dialogo de advertencia");

        if (!txt_name.getText().isEmpty()) {
            if (!txt_email.getText().isEmpty()) {
                if (!txt_phone.getText().isEmpty()) {
                    if (comb_sex.getValue() != null) {
                        if (!txt_password.getText().isEmpty()) {
                            if (txt_password.getText().equals(txt_passwordC.getText())) {
                                
                                list_users.addNodeUser(txt_name, txt_email, txt_phone, comb_sex, txt_password, label_user);
                                list_users.save();
                                run_login(label_user.getText());                                
                            } else {
                                a.setContentText("Verifique su contraseña.");
                                a.showAndWait();
                                txt_passwordC.setText("");
                                txt_passwordC.requestFocus();
                            }
                        } else {
                            a.setContentText("Es necesario que se escriba una contraseña.");
                            a.showAndWait();
                        }
                    } else {
                        a.setContentText("Seleccione un genero.");
                        a.showAndWait();
                    }
                } else {
                    a.setContentText("Es necesario que se escriba un"
                            + "\nnumero de numero de telefono.");
                    a.showAndWait();
                }
            } else {
                a.setContentText("Es necesario que se escriba un Correo / Usuario.");
                a.showAndWait();
            }
        } else {
            a.setContentText("Es necesario que se escriba un nombre.");
            a.showAndWait();
        }
    }
}
