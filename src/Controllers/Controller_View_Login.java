package Controllers;

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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Controller_View_Login implements Initializable {

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
    }

    @FXML
    private void eventAction(ActionEvent event) {

        if (event.getSource() == link_regist) {
            if (ima_admin.isVisible()) {
                run_signingUP("Admin");
            } else {
                run_signingUP("User");
            }
        }
    }

}
