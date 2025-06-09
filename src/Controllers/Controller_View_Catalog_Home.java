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
import javafx.stage.Stage;

public class Controller_View_Catalog_Home implements Initializable {

    private final Stacks_Products stacks_products = Data_Singleton.getInstance().getStacks();
    private final Circular_List_Users list_users = Data_Singleton.getInstance().getList_users();

    @FXML
    private Pane scrollPane01;
    @FXML
    private Button btnPagarProduct;
    @FXML
    private TextField txtTotal;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox panelContenCarrito;
    @FXML
    private Pane panelContenProdPagados;
    @FXML
    private VBox contenPagados;
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
        } else {
            btnHistory.setVisible(false);
        }
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
        Label lbl_size = new Label();
        Label lbl_price = new Label();
        Label lbl_brand = new Label();
        Label lbl_sex = new Label();
        Label lbl_name = new Label();
        Pane Imag = new Pane();
        ImageView miniV;
        Button btnComprar = new Button("Comprar");
        Button btnEliminar = new Button("Eliminar");
        btnComprar.getStylesheets().add(getClass().getResource("/Style_Sheets/Styles.css").toExternalForm());
        btnComprar.getStyleClass().add("btn-add");
        btnEliminar.getStylesheets().add(getClass().getResource("/Style_Sheets/Styles.css").toExternalForm());
        btnEliminar.getStyleClass().add("btn-add");

        GridPane contendElemtProductos = new GridPane();
        contendElemtProductos.setAlignment(Pos.CENTER);
        contendElemtProductos.setMaxSize(375, 394);
        ColumnConstraints column = new ColumnConstraints();
        column.setPrefWidth(panelContenCarrito.getWidth() / 2);

        contendElemtProductos.getColumnConstraints().addAll(column);
        contendElemtProductos.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
        contendElemtProductos.getStylesheets().add(getClass().getResource("/Style_Sheets/Styles.css").toExternalForm());
        contendElemtProductos.getStyleClass().add("grid-car");

        btnEliminar.setOnAction((ActionEvent event) -> {
            panelContenCarrito.getChildren().remove(contendElemtProductos);
            double t = 0;
            for (int i = 0; i < panelContenCarrito.getChildren().size(); i++) {
                if (panelContenCarrito.getChildren().get(i) instanceof GridPane) {

                    GridPane gridPane = (GridPane) panelContenCarrito.getChildren().get(i);

                    Node dato = get_node_whit_gridPane(gridPane, 1, 1);

                    Label precio = (Label) dato;

                    double p = Double.parseDouble(precio.getText());

                    txtTotal.setText("$" + (p + t));
                    String[] to = txtTotal.getText().split("\\$");
                    t = Double.parseDouble(to[1]);
                }
            }

            if (panelContenCarrito.getChildren().isEmpty()) {
                txtTotal.setText("");
            }

            list_users.Alert(Alert.AlertType.INFORMATION, "Aviso", "Se ha eliminado el porducto del carrito.");
        });

        btnComprar.setOnAction((ActionEvent event) -> {
            Product producto = product;
            String email = labelUser.getText();

            LocalDateTime date_purchase = LocalDateTime.now();

            product.setDate_purchase(date_purchase);
            producto.setEmail_buyer(email);

            stacks_products.pushProduct(stacks_products.getProducts_purchaseHistory(), producto);
            stacks_products.savePurchaseHistory();

            GridPane grid1 = (GridPane) btnComprar.getParent();
            Node image1 = get_node_whit_gridPane(grid1, 1, 0);
            Node total = get_node_whit_gridPane(grid1, 1, 1);
            Label pre = (Label) total;
            Label precioT = new Label();
            precioT.setText("Precio: " + pre.getText());
            Label estado = new Label();
            estado.setText("Estado: Enviado");
            Label fecha = new Label();

            fecha.setText("Fecha envio: " + producto.getDate_purchase_formatt(date_purchase));
            GridPane grid2 = new GridPane();
            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPrefWidth(contenPagados.getWidth() / 3);
            grid2.getColumnConstraints().addAll(column1);
            grid2.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, null, null)));
            grid2.getStylesheets().add(getClass().getResource("/Style_Sheets/Styles.css").toExternalForm());
            grid2.getStyleClass().add("grid-car");
            grid2.add(image1, 0, 1);
            grid2.add(precioT, 1, 0);
            grid2.add(estado, 1, 1);
            grid2.add(fecha, 1, 2);
            contenPagados.getChildren().add(grid2);
            panelContenCarrito.getChildren().remove(contendElemtProductos);
            double t = 0;
            for (int i = 0; i < panelContenCarrito.getChildren().size(); i++) {
                if (panelContenCarrito.getChildren().get(i) instanceof GridPane) {

                    GridPane gridPane = (GridPane) panelContenCarrito.getChildren().get(i);

                    Node dato = get_node_whit_gridPane(gridPane, 1, 1);

                    Label precio2 = (Label) dato;

                    double p = Double.parseDouble(precio2.getText());

                    txtTotal.setText("$" + (p + t));
                    String[] to = txtTotal.getText().split("\\$");
                    t = Double.parseDouble(to[1]);
                }
            }
            if (panelContenCarrito.getChildren().isEmpty()) {
                txtTotal.setText("");
            }
        });

        if (product != null) {
            miniV = new ImageView(image);
            miniV.setFitHeight(40);
            miniV.setFitWidth(30);
            Imag.getChildren().add(miniV);

            lbl_size.setText(product.getSize());
            lbl_price.setText(product.getPrice() + "");
            lbl_brand.setText(product.getBrand());
            lbl_sex.setText(product.getSex());
            lbl_name.setText(product.getName());

            contendElemtProductos.add(Imag, 0, 1);
            contendElemtProductos.add(lbl_brand, 0, 2);
            contendElemtProductos.add(lbl_name, 1, 0);
            contendElemtProductos.add(lbl_sex, 2, 0);
            contendElemtProductos.add(lbl_size, 2, 1);
            contendElemtProductos.add(lbl_price, 1, 1);
            contendElemtProductos.add(btnComprar, 1, 2);
            contendElemtProductos.add(btnEliminar, 2, 2);

            panelContenCarrito.getChildren().add(contendElemtProductos);
        }

        list_users.Alert(Alert.AlertType.INFORMATION, "Aviso", "El " + product.getName() + " agregado exitosamente al carrito de compras.");
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
                panelContenProdPagados.setVisible(false);
                scrollPane.setVisible(false);
                scrollPane01.setVisible(false);
            }
        } else if (event.getSource() == btnExp) {
            if (PmenuE.isVisible()) {

                PmenuE.setVisible(false);
            } else {
                Pmenu.setVisible(false);
                PmenuE.setVisible(true);
                PmenuP.setVisible(false);
                panelContenProdPagados.setVisible(false);
                scrollPane.setVisible(false);
                scrollPane01.setVisible(false);
            }
        } else if (event.getSource() == btnExFem) {

        } else if (event.getSource() == btnExMas) {

        } else if (event.getSource() == btnHistory) {
            PmenuP.setVisible(false);
            Pmenu.setVisible(false);
            PmenuE.setVisible(false);
            scrollPane.setVisible(false);
            scrollPane01.setVisible(false);
            panelContenProdPagados.setVisible(false);

        } else if (event.getSource() == btnPerfil) {
            if (PmenuP.isVisible()) {

                PmenuP.setVisible(false);
            } else {

                Pmenu.setVisible(false);
                PmenuE.setVisible(false);
                PmenuP.setVisible(true);
                panelContenProdPagados.setVisible(false);
                scrollPane.setVisible(false);
                scrollPane01.setVisible(false);
            }
        } else if (event.getSource() == btnCarro) {
            double t = 0;
            for (int i = 0; i < panelContenCarrito.getChildren().size(); i++) {
                if (panelContenCarrito.getChildren().get(i) instanceof GridPane) {

                    GridPane gridPane = (GridPane) panelContenCarrito.getChildren().get(i);

                    Node dato = get_node_whit_gridPane(gridPane, 1, 1);

                    Label precio = (Label) dato;

                    double p = Double.parseDouble(precio.getText());

                    txtTotal.setText("$" + (p + t));
                    String[] to = txtTotal.getText().split("\\$");
                    t = Double.parseDouble(to[1]);
                }
            }

            if (panelContenCarrito.getChildren().isEmpty()) {
                txtTotal.setText("");
            }

            scrollPane.setVisible(!scrollPane.isVisible());
            scrollPane01.setVisible(!scrollPane01.isVisible());
            PmenuP.setVisible(false);
            Pmenu.setVisible(false);
            PmenuE.setVisible(false);
            panelContenProdPagados.setVisible(false);
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
            panelContenProdPagados.setVisible(!panelContenProdPagados.isVisible());
            PmenuP.setVisible(false);
            Pmenu.setVisible(false);
            PmenuE.setVisible(false);
            scrollPane.setVisible(false);
            scrollPane01.setVisible(false);

        } else if (event.getSource() == btnCambC) {

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
    }

    @FXML
    private void mostrarProducto(MouseEvent event) {
    }

    @FXML
    private void add_car_shop(ActionEvent event) {
        Button button = (Button) event.getSource();
        Pane pane = (Pane) button.getParent();
        Product product = (Product) pane.getUserData();

        try {
            String url = product.getUrls_images().get(0);
            String urlLocal = System.getProperty("user.dir") + "\\" + url;
            File file = Paths.get(urlLocal).toFile();
            process_product(product, new Image(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            Logger.getLogger(Controller_View_Catalog_Home.class.getName()).log(Level.SEVERE, "Error al cargar imagen del producto.", e);
        }
    }

}
