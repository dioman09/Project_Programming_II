package Controllers;

import Data_Structures.Circular_List_Users;
import Data_Structures.Data_Singleton;
import Data_Structures.Stacks_Products;
import Models.Admin;
import Models.Product;
import Models.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Controller_View_Catalog_Home implements Initializable {

    private final Stacks_Products stacks_products = Data_Singleton.getInstance().getStacks();
    private final Circular_List_Users list_users = Data_Singleton.getInstance().getList_users();
    private ObservableList<File> images_games;
    private int index = 0;

    @FXML
    private Pane pane_car_shop;
    @FXML
    private TextField txtTotal;
    @FXML
    private Pane pane_send_product;
    @FXML
    private Button btnGen;
    @FXML
    private Button btnExp;
    @FXML
    private Pane Pmenu;
    @FXML
    private Pane PmenuE;
    @FXML
    private Button btnExFem;
    @FXML
    private Button btnExMas;
    @FXML
    private Button btnPerfil;
    @FXML
    private Button btnHistory;
    @FXML
    private Button btnEnviado;
    @FXML
    private Button btnCarro;
    @FXML
    private Label labelUser;
    @FXML
    private Pane img1;
    @FXML
    private Pane img5;
    @FXML
    private Pane img2;
    @FXML
    private Label lblMarca4;
    @FXML
    private Label lblGenero4;
    @FXML
    private Label lblTipo4;
    @FXML
    private Label lblPrecio4;
    @FXML
    private Pane img8;
    @FXML
    private Pane img7;
    @FXML
    private Pane img6;
    @FXML
    private Pane img3;
    @FXML
    private Pane img4;
    @FXML
    private Pane PmenuP;
    @FXML
    private Button btnCerrarS;
    @FXML
    private Button btnCambC;
    @FXML
    private ComboBox<String> cmb_size_1;
    @FXML
    private ComboBox<String> cmb_size_5;
    @FXML
    private ComboBox<String> cmb_size_2;
    @FXML
    private ComboBox<String> cmb_size_3;
    @FXML
    private ComboBox<String> cmb_size_4;
    @FXML
    private ComboBox<String> cmb_size_6;
    @FXML
    private ComboBox<String> cmb_size_7;
    @FXML
    private ComboBox<String> cmb_size_8;
    @FXML
    private Pane father_1;
    @FXML
    private Pane father_5;
    @FXML
    private Pane father_2;
    @FXML
    private Pane father_3;
    @FXML
    private Pane father_4;
    @FXML
    private Pane father_6;
    @FXML
    private Pane father_7;
    @FXML
    private Pane father_8;
    @FXML
    private GridPane container_catalog;
    @FXML
    private Button btn_cat_fem;
    @FXML
    private Button btn_cat_mas;
    @FXML
    private Button btn_add_car_shop_1;
    @FXML
    private Button btn_add_car_shop_5;
    @FXML
    private Button btn_add_car_shop_2;
    @FXML
    private Button btn_add_car_shop_3;
    @FXML
    private Button btn_add_car_shop_4;
    @FXML
    private Button btn_add_car_shop_6;
    @FXML
    private Button btn_add_car_shop_7;
    @FXML
    private Button btn_add_car_shop_8;
    @FXML
    private Label lbl_brand_1;
    @FXML
    private Label lbl_name_1;
    @FXML
    private Label lbl_price_1;
    @FXML
    private Label lbl_sex_1;
    @FXML
    private Label lbl_brand_5;
    @FXML
    private Label lbl_name_5;
    @FXML
    private Label lbl_price_5;
    @FXML
    private Label lbl_sex_5;
    @FXML
    private Label lbl_brand_2;
    @FXML
    private Label lbl_name_2;
    @FXML
    private Label lbl_price_2;
    @FXML
    private Label lbl_sex_2;
    @FXML
    private Label lbl_brand_3;
    @FXML
    private Label lbl_name_3;
    @FXML
    private Label lbl_price_3;
    @FXML
    private Label lbl_sex_3;
    @FXML
    private Label lbl_brand_4;
    @FXML
    private Label lbl_name_4;
    @FXML
    private Label lbl_price_4;
    @FXML
    private Label lbl_sex_4;
    @FXML
    private Label lbl_brand_6;
    @FXML
    private Label lbl_name_6;
    @FXML
    private Label lbl_price_6;
    @FXML
    private Label lbl_sex_6;
    @FXML
    private Label lbl_brand_7;
    @FXML
    private Label lbl_name_7;
    @FXML
    private Label lbl_price_7;
    @FXML
    private Label lbl_sex_7;
    @FXML
    private Label lbl_brand_8;
    @FXML
    private Label lbl_name_8;
    @FXML
    private Label lbl_price_8;
    @FXML
    private Label lbl_sex_8;
    @FXML
    private FlowPane container_purchase_state;
    @FXML
    private Button btn_back;
    @FXML
    private FlowPane conten_elements_car;
    @FXML
    private Button btn_deleteH;
    @FXML
    private Button btnExpH;
    @FXML
    private Button btn_closed;
    @FXML
    private TableView<Product> tableHistory;
    @FXML
    private TableColumn<Product, Integer> col1;
    @FXML
    private TableColumn<Product, String> col2;
    @FXML
    private TableColumn<Product, String> col3;
    @FXML
    private TableColumn<Product, Float> col4;
    @FXML
    private TableColumn<Product, String> col5;
    @FXML
    private TableColumn<Product, LocalDateTime> col6;
    @FXML
    private BorderPane panel_history;
    @FXML
    private Pane panel_details_product;
    @FXML
    private ImageView image_primary_product;
    @FXML
    private VBox container_miniatures;
    @FXML
    private Button rigth;
    @FXML
    private Button left;
    @FXML
    private TextFlow txt_description;
    @FXML
    private Label label_name;
    @FXML
    private ComboBox<String> cmb_size_details;
    @FXML
    private Button btn_add_details;
    @FXML
    private Button btn_buy_product;
    @FXML
    private VBox pass_toggle_panel;
    @FXML
    private PasswordField current_password_field;
    @FXML
    private VBox pass_new_section;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button continueButton;

    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        images_games = FXCollections.observableArrayList();

        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        col2.setCellValueFactory(new PropertyValueFactory<>("brand"));
        col3.setCellValueFactory(new PropertyValueFactory<>("name"));
        col4.setCellValueFactory(new PropertyValueFactory<>("price"));
        col4.setCellFactory(tc -> new FormattedTableCell<>("%,.2f"));
        col5.setCellValueFactory(new PropertyValueFactory<>("email_buyer"));
        col6.setCellValueFactory(new PropertyValueFactory<>("date_purchase"));
    }

    @FXML
    private void handleCancel(ActionEvent event) {        
        current_password_field.clear();
        newPasswordField.clear();
        confirmPasswordField.clear();
        errorLabel.setVisible(false);
        pass_new_section.setVisible(false);
        pass_toggle_panel.setVisible(false);
    }

    @FXML
    private void handleContinue(ActionEvent event) {
        String currentPassword = current_password_field.getText();
        String email = labelUser.getText();
        
        if (currentPassword.isEmpty()) {
            showError("Por favor ingrese su contraseña actual");
            return;
        }

        User user = list_users.searchByEmail(email);
        if (user == null) {
            showError("Usuario no encontrado");
            return;
        }

        if (!pass_new_section.isVisible()) {
            if (!user.getPassword().equals(currentPassword)) {
                showError("Contraseña actual incorrecta");
                return;
            }

            pass_new_section.setVisible(true);
            continueButton.setText("Guardar");
            return;
        }
        
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            showError("Por favor complete todos los campos");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showError("Las contraseñas no coinciden");
            return;
        }

        if (newPassword.equals(currentPassword)) {
            showError("La nueva contraseña debe ser diferente a la actual");
            return;
        }        
        user.setPassword(newPassword);           
        list_users.save();
        list_users.take();
        showAlert("Aviso.", "Contaseña cambiada con exito..!");
        handleCancel(event);
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    /**
     *
     * @param <S>
     * @param <T>
     */
    @SuppressWarnings("PublicInnerClass")
    public class FormattedTableCell<S, T> extends TableCell<S, T> {

        private final String format;

        public FormattedTableCell(String format) {
            this.format = format;
        }

        /**
         *
         * @param item
         * @param empty
         */
        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
            } else {
                setText(String.format(format, item));
            }
        }
    }

    private void load_image_primary() {
        index = 0;
        Product product = (Product) image_primary_product.getUserData();
        images_games.clear();
        for (String url : product.getUrls_images()) {
            String url_local = System.getProperty("user.dir") + "\\" + url;
            File file = Paths.get(url_local).toFile();

            if (file != null) {
                images_games.add(file);
            }
        }
        try {
            File file = images_games.get(index);
            image_primary_product.setImage(new Image(new FileInputStream(file)));
            update_miniatures();
        } catch (FileNotFoundException e) {
            Logger.getLogger(Controller_View_Catalog_Home.class.getName()).log(Level.SEVERE, "Error al tratar de actualizar el carrusel", e);
        }

    }

    private void reload_image_primary() {
        try {
            File imagen = images_games.get(index);
            image_primary_product.setImage(new Image(new FileInputStream(imagen)));
        } catch (FileNotFoundException e) {
            Logger.getLogger(Controller_View_Catalog_Home.class.getName()).log(Level.SEVERE, "Error al tratar de actualizar el carrusel", e);
        }
    }

    public void update_miniatures() {
        try {
            container_miniatures.getChildren().clear();
            for (int i = 0; i < images_games.size(); i++) {
                int finalI = i;
                ImageView thumb = new ImageView(new Image(new FileInputStream(images_games.get(i))));
                thumb.setFitWidth(100);
                thumb.setFitHeight(100);
                thumb.setPreserveRatio(true);
                thumb.setStyle("-fx-cursor: hand; -fx-background-radius: 8; -fx-border-radius: 8;");
                thumb.setOnMouseClicked(e -> {
                    if (index != finalI) {
                        index = finalI;
                        reload_image_primary();
                    }
                });
                container_miniatures.getChildren().add(thumb);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller_View_Catalog_Home.class.getName()).log(Level.SEVERE, "Error al tratar de cargar miniaturas", ex);
        }
    }

    private void load_product(Product product) {
        label_name.setText(product.getName());
        btn_back.setVisible(true);
        String[] sizes = product.getSize().split(",");
        cmb_size_details.setItems(FXCollections.observableArrayList(sizes));
        List<Text> list_text = new ArrayList<>();
        for (String string : product.getDescription().split(", ")) {
            Text text = new Text(string + "\n");
            list_text.add(text);
        }
        txt_description.getChildren().addAll(list_text);
    }

    public Label getLabelUser() {
        return labelUser;
    }

    public void isAdmin() {
        User user = list_users.searchByEmail(labelUser.getText());
        if (user != null && user instanceof Admin) {
            btnHistory.setVisible(true);
            btnExp.setVisible(true);
        } else {
            btnHistory.setVisible(false);
            btnExp.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    public void actualizarTabla() {
        tableHistory.getItems().clear();

        if (!stacks_products.getProducts_purchaseHistory().isEmpty()) {

            for (Product product : stacks_products.getProducts_purchaseHistory()) {
                tableHistory.getItems().add(product);
            }
        }
    }

    public void load_products() {
        conten_elements_car.getChildren().clear();
        container_purchase_state.getChildren().clear();
        float total = 0;
        for (Product product : stacks_products.getProducts_carShop()) {
            if (product.getEmail_buyer().equals(labelUser.getText())) {
                try {
                    total += product.getPrice();
                    String urlLocal = System.getProperty("user.dir") + "\\" + product.getUrls_images().get(0);
                    File file = Paths.get(urlLocal).toFile();
                    process_product(product, new Image(new FileInputStream(file)));
                } catch (FileNotFoundException e) {
                    Logger.getLogger(Controller_View_Catalog_Home.class.getName()).log(Level.SEVERE, "Error al cargar imagen del producto.", e);
                }
            }
        }
        String total_ = String.format("%,.2f", total);
        txtTotal.setText("$" + total_);
        for (Product product : stacks_products.getProducts_purchaseHistory()) {
            if (product.getEmail_buyer().equals(labelUser.getText())) {
                try {
                    String urlLocal = System.getProperty("user.dir") + "\\" + product.getUrls_images().get(0);
                    File file = Paths.get(urlLocal).toFile();
                    createPurchaseHistoryItem(product, new Image(new FileInputStream(file)));
                } catch (FileNotFoundException e) {
                    Logger.getLogger(Controller_View_Catalog_Home.class.getName()).log(Level.SEVERE, "Error al cargar imagen del producto.", e);
                }
            }
        }
    }

    private void createPurchaseHistoryItem(Product product, Image image) {
        ProductUIComponents components = new ProductUIComponents();
        components.imageView = new ImageView(image);
        components.imageView.setFitHeight(60);
        components.imageView.setFitWidth(70);
        components.imagePane.getChildren().add(components.imageView);

        Label priceLabel = new Label("Precio: " + product.getPrice());
        Label statusLabel = new Label("Estado: Enviado");
        Label dateLabel = new Label("Fecha envio: " + product.getDate_purchase_formatt(product.getDate_purchase()));

        GridPane historyGrid = new GridPane();
        historyGrid.setMaxSize(375, 394);

        ColumnConstraints column = new ColumnConstraints();
        column.setPrefWidth(container_purchase_state.getWidth() / 2);
        historyGrid.getColumnConstraints().add(column);

        historyGrid.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, null, null)));
        historyGrid.getStylesheets().add(getClass().getResource("/Style_Sheets/Styles.css").toExternalForm());
        historyGrid.getStyleClass().add("grid-car");

        historyGrid.add(components.imagePane, 0, 1);
        historyGrid.add(priceLabel, 1, 0);
        historyGrid.add(statusLabel, 1, 1);
        historyGrid.add(dateLabel, 1, 2);

        container_purchase_state.getChildren().add(historyGrid);
    }

    public void export_catalog_pdf(String sex) {
        Stage stage = new Stage();
        stage.setTitle("Exportar Catalogo " + sex);

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));

        Button button = new Button("Select Directory");
        button.setOnAction((ActionEvent ex) -> {
            File selectedDirectory = directoryChooser.showDialog(stage);
            stacks_products.export_catalog_pdf(selectedDirectory.getAbsolutePath() + "\\", sex);
            stage.close();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Archivo Guardado Exitosamente");
            a.showAndWait();
        });

        HBox vs = new HBox(new Label("Selecciona Direccion de Carpeta: "), button);
        vs.setAlignment(Pos.CENTER);
        vs.setPadding(new Insets(10));
        vs.setSpacing(10);

        Scene scene = new Scene(vs);

        stage.setScene(scene);
        stage.show();
    }

    public void export_history_pdf() {
        Stage stage = new Stage();
        stage.setTitle("Exportar Historial");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));

        Button button = new Button("Select Directory");
        button.setOnAction((ActionEvent ex) -> {
            File selectedDirectory = directoryChooser.showDialog(stage);
            stacks_products.export_history_pdf(selectedDirectory.getAbsolutePath() + "\\");
            stage.close();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Archivo Guardado Exitosamente");
            a.show();
        });

        HBox vs = new HBox(new Label("Selecciona Direccion de Carpeta: "), button);
        vs.setAlignment(Pos.CENTER);
        vs.setPadding(new Insets(10));
        vs.setSpacing(10);

        Scene scene = new Scene(vs);

        stage.setScene(scene);
        stage.show();
    }

    public void run_arranque() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/View_Arranque.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            Stage miStage = (Stage) this.btnCarro.getScene().getWindow();
            miStage.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller_View_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void show_products(String sex) {
        Platform.runLater(() -> {
            for (Node node : container_catalog.getChildren()) {
                if (node instanceof Pane) {
                    Pane pane = (Pane) node;
                    if (pane.getUserData() instanceof Product) {
                        Product product = (Product) pane.getUserData();

                        for (Node node_ : pane.getChildren()) {
                            if (node_ instanceof Label) {
                                Label label = (Label) node_;
                                for (int a = 1; a < 9; a++) {
                                    if (label.getId().equals("lbl_brand_" + a)) {
                                        label.setText(product.getBrand());
                                        break;
                                    }
                                    if (label.getId().equals("lbl_name_" + a)) {
                                        label.setText(product.getName());
                                        break;
                                    }
                                    if (label.getId().equals("lbl_sex_" + a)) {
                                        label.setText(product.getSex());
                                        break;
                                    }
                                    if (label.getId().equals("lbl_price_" + a)) {
                                        label.setText(product.getPrice() + "");
                                        break;
                                    }
                                }
                            }

                            if (product.getSex().equals(sex)) {
                                Pane paneIma = (Pane) pane.lookup(".panel-view");
                                if (paneIma != null) {
                                    try {
                                        String url = product.getUrls_images().get(0);
                                        String urlLocal = System.getProperty("user.dir") + "\\" + url;
                                        File file = Paths.get(urlLocal).toFile();

                                        ImageView imageView = new ImageView(new Image(new FileInputStream(file)));
                                        imageView.setFitWidth(230);
                                        imageView.setFitHeight(150);
                                        imageView.setLayoutX(10);
                                        imageView.setLayoutY(10);
                                        imageView.setOnMouseClicked((MouseEvent event) -> {
                                            ImageView image_view = (ImageView) event.getSource();
                                            Pane fatherPane = (Pane) image_view.getParent();
                                            Pane grandFather = (Pane) fatherPane.getParent();

                                            if (grandFather.getUserData() != null) {
                                                Product product_ = (Product) grandFather.getUserData();

                                                load_product(product_);
                                                image_primary_product.setUserData(product_);
                                                load_image_primary();

                                                btnExp.setVisible(false);
                                                btnGen.setVisible(false);
                                                container_catalog.setVisible(false);
                                                panel_details_product.setVisible(true);
                                            }
                                        });
                                        paneIma.getChildren().clear();
                                        paneIma.getChildren().add(imageView);
                                    } catch (FileNotFoundException e) {
                                        Logger.getLogger(Controller_View_Catalog_Home.class.getName()).log(Level.SEVERE, "Error al cargar imagen", e);
                                    }

                                    ComboBox<String> cmb = (ComboBox<String>) pane.lookup(".combo-box");
                                    if (cmb != null) {
                                        String[] sizes = product.getSize().split(",");
                                        cmb.setItems(FXCollections.observableArrayList(sizes));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public void associate_products_pane(String sex) {
        int i = 0;
        for (Node node : container_catalog.getChildren()) {
            if (node instanceof Pane) {
                Pane pane = (Pane) node;
                if (pane.getId() != null) {
                    if (stacks_products.getProducts_all().isEmpty()) {
                        return;
                    }
                    Product product = stacks_products.getProductsBySex(stacks_products.getProducts_all(), sex).get(i++);
                    pane.setUserData(product);
                }
            }
        }
        show_products(sex);
    }

    private Node get_node_whit_gridPane(GridPane gridPane, int rowIndex, int colIndex) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node) == colIndex) {
                return node;
            }
        }
        return null;
    }

    private void process_product(Product product, Image image) {
        if (product == null) {
            return;
        }

        ProductUIComponents components = createProductComponents(product, image);

        GridPane productGrid = setupProductGrid(components);

        productGrid.setUserData(product);

        setupButtonActions(components, product, productGrid);

        conten_elements_car.getChildren().add(productGrid);
    }

    private static class ProductUIComponents {

        Label sizeLabel = new Label();
        Label priceLabel = new Label();
        Label brandLabel = new Label();
        Label sexLabel = new Label();
        Label nameLabel = new Label();
        Pane imagePane = new Pane();
        ImageView imageView;
        Button buyButton = new Button("Comprar");
        Button deleteButton = new Button("Eliminar");
    }

    private ProductUIComponents createProductComponents(Product product, Image image) {
        ProductUIComponents components = new ProductUIComponents();

        components.imageView = new ImageView(image);
        components.imageView.setFitHeight(60);
        components.imageView.setFitWidth(70);
        components.imagePane.getChildren().add(components.imageView);

        components.sizeLabel.setText(product.getSize());
        components.priceLabel.setText(String.valueOf(product.getPrice()));
        components.brandLabel.setText(product.getBrand());
        components.sexLabel.setText(product.getSex());
        components.nameLabel.setText(product.getName());

        String stylesheet = getClass().getResource("/Style_Sheets/Styles.css").toExternalForm();
        components.buyButton.getStylesheets().add(stylesheet);
        components.buyButton.getStyleClass().add("btn-add");
        components.deleteButton.getStylesheets().add(stylesheet);
        components.deleteButton.getStyleClass().add("btn-remove");

        return components;
    }

    private GridPane setupProductGrid(ProductUIComponents components) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setMaxSize(375, 394);

        ColumnConstraints column = new ColumnConstraints();
        column.setPrefWidth(conten_elements_car.getWidth() / 2);
        grid.getColumnConstraints().add(column);

        grid.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
        grid.getStylesheets().add(getClass().getResource("/Style_Sheets/Styles.css").toExternalForm());
        grid.getStyleClass().add("grid-car");

        grid.add(components.imagePane, 0, 1);
        grid.add(components.brandLabel, 0, 2);
        grid.add(components.nameLabel, 1, 0);
        grid.add(components.sexLabel, 2, 0);
        grid.add(components.sizeLabel, 2, 1);
        grid.add(components.priceLabel, 1, 1);
        grid.add(components.buyButton, 1, 2);
        grid.add(components.deleteButton, 2, 2);

        return grid;
    }

    private void setupButtonActions(ProductUIComponents components, Product product, GridPane productGrid) {
        components.deleteButton.setOnAction(event -> handleDeleteProduct(product, productGrid));
        components.buyButton.setOnAction(event -> handleBuyProduct(product, productGrid));
    }

    private void handleDeleteProduct(Product product, GridPane productGrid) {
        conten_elements_car.getChildren().remove(productGrid);
        stacks_products.getProducts_carShop().remove(product);
        stacks_products.saveCarShop();

        updateTotalPrice();
        showAlert("Aviso", "Se ha eliminado el producto del carrito.");
    }

    private void handleBuyProduct(Product product, GridPane productGrid) {
        stacks_products.getProducts_carShop().remove(product);
        stacks_products.saveCarShop();

        LocalDateTime date_purchase = LocalDateTime.now();
        product.setDate_purchase(date_purchase);
        stacks_products.pushProduct(stacks_products.getProducts_purchaseHistory(), product);
        stacks_products.savePurchaseHistory();

        createPurchaseHistoryItem(product, productGrid);
        conten_elements_car.getChildren().remove(productGrid);

        updateTotalPrice();
        String price = String.format("%,.2f", product.getPrice());
        showAlert("Aviso", "Compra realizada con exito\nHa comprado " + product.getName() + "por un valor de $" + price + ".");
    }

    private void createPurchaseHistoryItem(Product product, GridPane sourceGrid) {
        Node imageNode = get_node_whit_gridPane(sourceGrid, 1, 0);

        Label priceLabel = new Label("Precio: " + product.getPrice());
        Label statusLabel = new Label("Estado: Enviado");
        Label dateLabel = new Label("Fecha envio: " + product.getDate_purchase_formatt(product.getDate_purchase()));

        GridPane historyGrid = new GridPane();
        historyGrid.setMaxSize(375, 394);

        ColumnConstraints column = new ColumnConstraints();
        column.setPrefWidth(container_purchase_state.getWidth() / 3);
        historyGrid.getColumnConstraints().add(column);

        historyGrid.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, null, null)));
        historyGrid.getStylesheets().add(getClass().getResource("/Style_Sheets/Styles.css").toExternalForm());
        historyGrid.getStyleClass().add("grid-car");

        historyGrid.add(imageNode, 0, 1);
        historyGrid.add(priceLabel, 1, 0);
        historyGrid.add(statusLabel, 1, 1);
        historyGrid.add(dateLabel, 1, 2);

        container_purchase_state.getChildren().add(historyGrid);
    }

    private void updateTotalPrice() {
        double total = conten_elements_car.getChildren().stream()
                .filter(node -> node instanceof GridPane)
                .mapToDouble(node -> {
                    GridPane grid = (GridPane) node;
                    Node priceNode = get_node_whit_gridPane(grid, 1, 1);
                    return priceNode instanceof Label ? Double.valueOf(((Label) priceNode).getText()) : 0;
                })
                .sum();

        txtTotal.setText(total > 0 ? "$" + total : "");
    }

    private void showAlert(String title, String message) {
        list_users.Alert(Alert.AlertType.INFORMATION, title, message);
    }

    @FXML
    private void buy_products(ActionEvent event) {
        float total = 0;
        Alert alert = new Alert(Alert.AlertType.NONE, "Aviso.", ButtonType.OK);
        if (stacks_products.getProductsByEmail(stacks_products.getProducts_carShop(), labelUser.getText()) == null) {
            alert.setContentText("Antes de relaizar el pago debe asegurarse de tener productos por comprar.");
            alert.show();
            return;
        }

        for (Node node : conten_elements_car.getChildren()) {
            GridPane grid = (GridPane) node;
            if (grid.getUserData() != null) {

                Product product = (Product) grid.getUserData();
                total += product.getPrice();
                LocalDateTime date_purchase = LocalDateTime.now();
                if (stacks_products.getProducts_carShop().contains(product)) {
                    stacks_products.getProducts_carShop().remove(product);
                    stacks_products.saveCarShop();                    
                }
                product.setDate_purchase(date_purchase);
                stacks_products.getProducts_purchaseHistory().add(product);
                stacks_products.savePurchaseHistory();
                actualizarTabla();
            }
        }
        load_products();
        updateTotalPrice();
        String total_ = String.format("%,.2f", total);
        alert.setContentText("Gracias por realizar su compra, el total fue de " + total_);
        alert.show();
    }

    @FXML
    private void toggle_action(ActionEvent event) {
        if (event.getSource() == left) {
            if (index > 0) {
                index--;
                reload_image_primary();
            }

        } else {
            if (index < (images_games.size() - 1)) {
                index++;
                reload_image_primary();
            }
        }
    }

    @FXML
    private void actionEvent(ActionEvent event) {

        if (event.getSource() == btnGen) {
            if (Pmenu.isVisible()) {

                Pmenu.setVisible(false);
            } else {

                PmenuE.setVisible(false);
                Pmenu.setVisible(true);
                PmenuP.setVisible(false);
                pane_send_product.setVisible(false);
                pane_car_shop.setVisible(false);
            }
        } else if (event.getSource() == btnExp) {
            if (PmenuE.isVisible()) {

                PmenuE.setVisible(false);
            } else {
                Pmenu.setVisible(false);
                PmenuE.setVisible(true);
                PmenuP.setVisible(false);
                pane_send_product.setVisible(false);
                pane_car_shop.setVisible(false);
            }
        } else if (event.getSource() == btnExFem) {
            export_catalog_pdf("Mujer");
        } else if (event.getSource() == btnExMas) {
            export_catalog_pdf("Hombre");
        } else if (event.getSource() == btnHistory) {
            PmenuP.setVisible(false);
            Pmenu.setVisible(false);
            PmenuE.setVisible(false);
            pane_car_shop.setVisible(false);
            pane_send_product.setVisible(false);
            panel_history.setVisible(!panel_history.isVisible());
            actualizarTabla();
        } else if (event.getSource() == btnPerfil) {
            if (PmenuP.isVisible()) {

                PmenuP.setVisible(false);
            } else {

                Pmenu.setVisible(false);
                PmenuE.setVisible(false);
                PmenuP.setVisible(true);
                pane_send_product.setVisible(false);
                pane_car_shop.setVisible(false);
            }
        } else if (event.getSource() == btnCarro) {
            load_products();
            pane_send_product.setVisible(!pane_send_product.isVisible());
            pane_car_shop.setVisible(!pane_car_shop.isVisible());
            PmenuP.setVisible(false);
            Pmenu.setVisible(false);
            PmenuE.setVisible(false);
            pane_send_product.setVisible(false);
        } else if (event.getSource() == btnCerrarS) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
            alert.setContentText("Está apunto de cerrar la sesión\nEstá seguro de esto? :)");
            alert.setTitle("Confirmación");
            alert.showAndWait().ifPresent((ButtonType response) -> {
                if (response == ButtonType.YES) {
                    run_arranque();
                } else if (response == ButtonType.NO) {
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.FINISH);
                    alert2.setTitle("Información");
                    alert2.setHeaderText("Te quedaste?!");
                    alert2.setContentText("¡Sigue comprando...!");
                    alert2.show();
                    alert.close();
                }
            });
        } else if (event.getSource() == btnEnviado) {
            load_products();
            pane_send_product.setVisible(!pane_send_product.isVisible());
            PmenuP.setVisible(false);
            Pmenu.setVisible(false);
            PmenuE.setVisible(false);
            pane_car_shop.setVisible(false);
        } else if (event.getSource() == btnCambC) {
            pass_toggle_panel.setVisible(true);
        } else if (event.getSource() == btn_back) {
            btn_back.setVisible(false);
            btnExp.setVisible(true);
            btnGen.setVisible(true);
            container_catalog.setVisible(true);
            panel_details_product.setVisible(false);
        } else if (event.getSource() == btn_deleteH) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
            alert.setContentText("Está apunto de eliminar todo el historial de compras\n"
                    + "Los datos eliminados tambien se eliminarán de la base de datos\n"
                    + "Está seguro de esto? -_-");
            alert.setTitle("Confirmación");
            alert.showAndWait().ifPresent((ButtonType response) -> {
                if (response == ButtonType.YES) {

                    if (!stacks_products.getProducts_purchaseHistory().isEmpty()) {

                        while (!stacks_products.getProducts_purchaseHistory().isEmpty()) {
                            stacks_products.getProducts_purchaseHistory().pop();
                        }
                        stacks_products.savePurchaseHistory();
                        actualizarTabla();
                    } else {

                        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
                        alert2.setTitle("Información");
                        alert2.setContentText("¡No hay elementos por eliminar!");
                        alert2.show();
                        alert.close();
                    }
                } else if (response == ButtonType.NO) {
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.FINISH);
                    alert2.setTitle("Información");
                    alert2.setContentText("¡No se eliminaron los datos...!");
                    alert2.show();
                    alert.close();
                }
            });

        } else if (event.getSource() == btnExpH) {

            export_history_pdf();

        } else if (event.getSource() == btn_closed) {
            panel_history.setVisible(false);
            stacks_products.savePurchaseHistory();
            actualizarTabla();
        }
    }

    @FXML
    private void toogle_catalog(ActionEvent event) {
        if (event.getSource() == btn_cat_mas) {
            associate_products_pane("MASCULINO");
        }

        if (event.getSource() == btn_cat_fem) {
            associate_products_pane("FEMENINO");
        }
    }

    @FXML
    private void pVisible(MouseEvent event) {
        PmenuP.setVisible(false);
        Pmenu.setVisible(false);
        PmenuE.setVisible(false);
        pane_car_shop.setVisible(false);
        pane_send_product.setVisible(false);
    }

    @FXML
    private void add_car_shop(ActionEvent event) {
        Button button = (Button) event.getSource();
        Pane pane = (Pane) button.getParent();
        Product product = (Product) pane.getUserData();

        Alert alert = new Alert(Alert.AlertType.WARNING, "", ButtonType.OK);
        if (event.getSource() == btn_add_details) {
            Pane parent = (Pane) btn_add_details.getParent();
            Product product_ = (Product) image_primary_product.getUserData();
            if (search_size(parent, product_)) {
                try {
                    String url = product_.getUrls_images().get(0);
                    String urlLocal = System.getProperty("user.dir") + "\\" + url;
                    File file = Paths.get(urlLocal).toFile();
                    product_.setEmail_buyer(labelUser.getText());
                    process_product(product_, new Image(new FileInputStream(file)));

                    stacks_products.getProducts_carShop().add(product_);
                    stacks_products.saveCarShop();
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("Listo..!");
                    alert.setContentText("¡Producto agregado al carrito de forma exitosa...!");
                    alert.show();
                } catch (FileNotFoundException e) {
                    Logger.getLogger(Controller_View_Catalog_Home.class.getName()).log(Level.SEVERE, "Error al cargar imagen del producto.", e);
                }
            } else {

                alert.setTitle("Información");
                alert.setHeaderText("Ojo -_-");
                alert.setContentText("¡Antes de agregar al carrito debes elegir una talla...!");
                alert.show();
            }
        } else {
            if (search_size(pane, product)) {
                try {
                    String url = product.getUrls_images().get(0);
                    String urlLocal = System.getProperty("user.dir") + "\\" + url;
                    File file = Paths.get(urlLocal).toFile();
                    product.setEmail_buyer(labelUser.getText());
                    process_product(product, new Image(new FileInputStream(file)));

                    stacks_products.getProducts_carShop().add(product);
                    stacks_products.saveCarShop();
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("Listo..!");
                    alert.setContentText("¡Producto agregado al carrito de forma exitosa...!");
                    alert.show();
                } catch (FileNotFoundException e) {
                    Logger.getLogger(Controller_View_Catalog_Home.class.getName()).log(Level.SEVERE, "Error al cargar imagen del producto.", e);
                }
            } else {

                alert.setTitle("Información");
                alert.setHeaderText("Ojo -_-");
                alert.setContentText("¡Antes de agregar al carrito debes elegir una talla...!");
                alert.show();
            }
        }
    }

    private boolean search_size(Pane pane, Product product) {
        ComboBox<String> cmb = (ComboBox<String>) pane.lookup(".combo-box");
        if (cmb != null) {
            if (cmb.getValue() != null) {
                product.setSize(cmb.getValue());
                return true;
            }
        }
        return false;
    }
}
