package Controllers;

import Data_Structures.Circular_List_Users;
import Data_Structures.Data_Singleton;
import Models.Admin;
import Models.Client;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Controller_View_Login implements Initializable {

    private final Circular_List_Users list_users = Data_Singleton.getInstance().getList_users();

    @FXML
    private ImageView ima_user;
    @FXML
    private Group ima_admin;
    @FXML
    private TextField txt_email;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button btn_login;
    @FXML
    private Hyperlink link_regist;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void run_arranque() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/View_Arranque.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            Stage miStage = (Stage) this.btn_login.getScene().getWindow();
            miStage.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller_View_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run_signingUP(String type) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/View_SigningUP.fxml"));
            Parent root = loader.load();

            Controller_View_SigningUP controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);

            stage.setOnShown(event -> controller.getLabel_user().setText(type));

            stage.setOnCloseRequest((WindowEvent value) -> {
                controller.run_login(type);
            });

            stage.show();

            Stage miStage = (Stage) this.btn_login.getScene().getWindow();
            miStage.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller_View_Arranque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void seeIma(String type) {
        if (type.equals("Admin")) {
            ima_admin.setVisible(true);
        } else {
            ima_user.setVisible(true);
        }
    }

    @FXML
    private void eventKey(KeyEvent event) {
        if (event.getCharacter().equals(" ")) {
            event.consume();
        }
    }

    @FXML
    private void eventAction(ActionEvent event) {

        if (event.getSource() == link_regist) {
            if (ima_admin.isVisible()) {
                run_signingUP("Admin");
            } else {
                run_signingUP("User");
            }
        } else if (event.getSource() == btn_login) {
            login_up();
        }
    }

    public void login_up() {
        if (txt_email.getText().isEmpty() && txt_password.getText().isEmpty()) {

            list_users.Alert(Alert.AlertType.WARNING, "Aviso", "No se puede verificar\n"
                    + "Los campos están vacios");

        } else if (txt_email.getText().isEmpty()) {

            list_users.Alert(Alert.AlertType.WARNING, "Aviso", "No se puede verificar\n"
                    + "Debe ingresar un correo");

        } else if (txt_password.getText().isEmpty()) {

            list_users.Alert(Alert.AlertType.WARNING, "Aviso", "No se puede verificar\n"
                    + "Debe ingresar una contraseña");

        } else if (!txt_email.getText().isEmpty() && !txt_password.getText().isEmpty()) {

            User search = list_users.searchByEmail(txt_email.getText());

            if (search != null) {

                if (ima_admin.isVisible() && search instanceof Client) {
                    list_users.Alert(Alert.AlertType.CONFIRMATION, "Ojo -_-", "Acceso denegado\nDebe iniciar con una cuenta de administrador..!");
                    txt_email.clear();
                    txt_password.clear();
                    txt_email.requestFocus();
                    return;
                } else if (ima_user.isVisible() && search instanceof Admin) {
                    list_users.Alert(Alert.AlertType.CONFIRMATION, "Ojo -_-", "Acceso denegado\nDebe iniciar con una cuenta de cliente..!");
                    txt_email.clear();
                    txt_password.clear();
                    txt_email.requestFocus();
                    return;
                }

                if ((search.getPassword()).equals(txt_password.getText())) {

                    list_users.Alert(Alert.AlertType.CONFIRMATION, "Bienvenido", "NIKE-STORE LE DA LA BIENVENIDA..!");
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/View_Catalog_Home.fxml"));
                        Parent root = loader.load();

                        Controller_View_Catalog_Home controller = loader.getController();

                        Scene scene = new Scene(root);
                        Stage stage = new Stage();

                        stage.setScene(scene);

                        stage.setOnShown((WindowEvent event) -> {
                            controller.getLabelUser().setText(txt_email.getText());
                            controller.associate_products_pane(search.getSex());
                            controller.isAdmin();
                            controller.load_products();
                        });

                        stage.setOnCloseRequest((WindowEvent value) -> {
                            controller.run_arranque();
                        });

                        stage.show();

                        Stage miStage = (Stage) this.btn_login.getScene().getWindow();
                        miStage.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Controller_View_Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    txt_email.setText("");
                    txt_password.setText("");

                } else {
                    list_users.Alert(Alert.AlertType.WARNING, "Aviso", "Contraseña incorrecta.");
                    txt_password.setText("");
                    txt_password.requestFocus();

                }
            } else {
                list_users.Alert(Alert.AlertType.WARNING, "Aviso", "Correo no registrado o equivocado, verifique por favor.");
                txt_email.setText("");
                txt_password.setText("");
                txt_email.requestFocus();
            }
        }
    }
}
