package Controllers;

import Data_Structures.Circular_List_Users;
import Data_Structures.Data_Singleton;
import Data_Structures.Stacks_Products;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Controller_View_Arranque implements Initializable {

    private final Circular_List_Users list_users = Data_Singleton.getInstance().getList_users();
    private final Stacks_Products stacks_products = Data_Singleton.getInstance().getStacks();

    @FXML
    private Button btn_admin;
    @FXML
    private Button btn_signing_up;
    @FXML
    private Button btn_login;
    @FXML
    private Button link_01;
    @FXML
    private Button link_02;
    @FXML
    private ImageView ima_link_01;
    @FXML
    private ImageView ima_link_02;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image imageNormal = new Image(getClass().getResourceAsStream("/Images/Publics/GitHub.png"));
        Image imageHover = new Image(getClass().getResourceAsStream("/Images/Publics/GitHub2.png"));

        link_01.setOnMouseEntered(event -> ima_link_01.setImage(imageHover));
        link_01.setOnMouseExited(event -> ima_link_01.setImage(imageNormal));

        Image imageNormal_ = new Image(getClass().getResourceAsStream("/Images/Publics/WhatsApp.png"));
        Image imageHover_ = new Image(getClass().getResourceAsStream("/Images/Publics/WhatsApp2.png"));

        link_02.setOnMouseEntered(event -> ima_link_02.setImage(imageHover_));
        link_02.setOnMouseExited(event -> ima_link_02.setImage(imageNormal_));

        list_users.take();
        stacks_products.load();
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

            Stage miStage = (Stage) this.btn_login.getScene().getWindow();
            miStage.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller_View_Arranque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run_signingUP() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/View_SigningUP.fxml"));
            Parent root = loader.load();

            Controller_View_SigningUP controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);

            stage.setOnShown(event -> controller.getLabel_user().setText("User"));

            stage.setOnCloseRequest((WindowEvent value) -> {
                controller.run_arranque();
            });

            stage.show();

            Stage miStage = (Stage) this.btn_login.getScene().getWindow();
            miStage.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller_View_Arranque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void action_event(ActionEvent event) {
        if (event.getSource() == btn_admin) {
            run_login("Admin");
        } else if (event.getSource() == btn_login) {
            run_login("User");
        } else if (event.getSource() == btn_signing_up) {
            run_signingUP();
        }
    }

    @FXML
    private void irLink(ActionEvent event) {
    }
}
