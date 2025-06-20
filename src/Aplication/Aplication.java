package Aplication;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Aplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/View_Arranque.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);            
            stage.show();
        } catch (IOException e) {
            e.getStackTrace();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
