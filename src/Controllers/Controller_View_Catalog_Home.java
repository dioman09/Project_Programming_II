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
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Controller_View_Catalog_Home implements Initializable {

    private final Stacks_Products stacks_products = Data_Singleton.getInstance().getStacks();
    private final Circular_List_Users list_users = Data_Singleton.getInstance().getList_users();

    @FXML
    private Pane pane_car_shop;
    @FXML
    private Button btnPagarProduct;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO                           
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

    public void load_products() {
        for (Product product : stacks_products.getProducts_carShop()) {
            if (product.getEmail_buyer().equals(labelUser.getText())) {
                try {
                    String urlLocal = System.getProperty("user.dir") + "\\" + product.getUrls_images().get(0);
                    File file = Paths.get(urlLocal).toFile();
                    process_product_2(product, new Image(new FileInputStream(file)));
                } catch (FileNotFoundException e) {
                    Logger.getLogger(Controller_View_Catalog_Home.class.getName()).log(Level.SEVERE, "Error al cargar imagen del producto.", e);
                }
            }
        }

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
        column.setPrefWidth(container_purchase_state.getWidth() / 3);
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

        setupButtonActions(components, product, productGrid);

        conten_elements_car.getChildren().add(productGrid);

        showSuccessAlert(product);
    }

    private void process_product_2(Product product, Image image) {
        if (product == null) {
            return;
        }

        ProductUIComponents components = createProductComponents(product, image);

        GridPane productGrid = setupProductGrid(components);

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

    private void showSuccessAlert(Product product) {
        showAlert("Aviso", "El " + product.getName() + " agregado exitosamente al carrito de compras.");
    }

    private void showAlert(String title, String message) {
        list_users.Alert(Alert.AlertType.INFORMATION, title, message);
    }

    @FXML
    private void realizarPago(ActionEvent event) {
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
            double t = 0;
            for (int i = 0; i < conten_elements_car.getChildren().size(); i++) {
                if (conten_elements_car.getChildren().get(i) instanceof GridPane) {

                    GridPane gridPane = (GridPane) conten_elements_car.getChildren().get(i);

                    Node dato = get_node_whit_gridPane(gridPane, 1, 1);

                    Label precio = (Label) dato;

                    double p = Double.parseDouble(precio.getText());

                    txtTotal.setText("$" + (p + t));
                    String[] to = txtTotal.getText().split("\\$");
                    t = Double.parseDouble(to[1]);
                }
            }

            if (conten_elements_car.getChildren().isEmpty()) {
                txtTotal.setText("");
            }

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
            pane_send_product.setVisible(!pane_send_product.isVisible());
            PmenuP.setVisible(false);
            Pmenu.setVisible(false);
            PmenuE.setVisible(false);
            pane_car_shop.setVisible(false);

        } else if (event.getSource() == btnCambC) {

        } else if (event.getSource() == btn_back) {
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

        if (search_size(pane, product)) {
            try {
                String url = product.getUrls_images().get(0);
                String urlLocal = System.getProperty("user.dir") + "\\" + url;
                File file = Paths.get(urlLocal).toFile();
                product.setEmail_buyer(labelUser.getText());
                process_product(product, new Image(new FileInputStream(file)));

                stacks_products.getProducts_carShop().add(product);
                stacks_products.saveCarShop();
            } catch (FileNotFoundException e) {
                Logger.getLogger(Controller_View_Catalog_Home.class.getName()).log(Level.SEVERE, "Error al cargar imagen del producto.", e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "", ButtonType.OK);
            alert.setTitle("Información");
            alert.setHeaderText("Ojo -_-");
            alert.setContentText("¡Antes de agregar al carrito debes elegir una talla...!");
            alert.show();
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

    @FXML
    private void show_product(MouseEvent event) {
    }
}
