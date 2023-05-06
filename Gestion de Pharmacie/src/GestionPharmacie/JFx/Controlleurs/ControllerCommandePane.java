package GestionPharmacie.JFx.Controlleurs;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import GestionPharmacie.ConnectionFactory;
import GestionPharmacie.JFx.models.Event;
import GestionPharmacie.JFx.models.EventScheduler;
import GestionPharmacie.JFx.models.Model;
import GestionPharmacie.JFx.models.Stock;
import GestionPharmacie.JFx.models.TimeRemaining;
import GestionPharmacie.JFx.models.Stock.StateCMD;
import GestionPharmacie.JFx.models.tasks.ModelTaskAcces;
import GestionPharmacie.JFx.models.tasks.Task;
import GestionPharmacie.JFx.models.tasks.TaskScheduler;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ControllerCommandePane implements Initializable {

    @FXML
    private BorderPane cmdBorderPane;

    @FXML
    private TableView<Stock> seuilStockTab;

    @FXML
    private Label time;

    @FXML
    private TextField daysField;

    @FXML
    private TextField hoursField;

    @FXML
    private TextField minutesField;

    @FXML
    private TextField secondsField;

    @FXML
    private MFXButton confirmBtn;

    @FXML
    private MFXButton dateConfirmBtn;

    @FXML
    private MFXDatePicker dateEvent;

    @FXML
    private TextField dateHoursField;

    @FXML
    private TextField dateMinutesField;

    @FXML
    private TextField dateSecondsField;

    @FXML
    private VBox dateTimeEvent;

    @FXML
    private VBox countdownEvent;

    @FXML
    private RadioButton onceOption;

    @FXML
    private RadioButton dailyOption;

    @FXML
    private RadioButton monthlyOption;

    @FXML
    private RadioButton weeklyOption;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private MFXButton planTask;

    @FXML
    private TextField taskNameField;

    @FXML
    private MFXDatePicker taskDatePicker;

    @FXML
    private TextField taskHoursField;

    @FXML
    private TextField taskMinutesField;

    @FXML
    private TextField taskSecondsField;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private void initialize() {
        dailyOption.setSelected(false);
    }

    /*
     * ***************
     * METHODES *
     * ***************
     */

    /*
     * Switch entre les deux parametre {@link #Le_Compte_à_rebours} et {@link
     * #La_Date_Time}
     */

    private void disableVBox(VBox vBoxToDisable) {
        vBoxToDisable.setDisable(true);
    }

    private void enableVBox(VBox vBoxToEnable) {
        vBoxToEnable.setDisable(false);
    }

    @FXML
    private void toggleVBox(ActionEvent event) {
        if (dateTimeEvent.isDisabled()) {
            disableVBox(countdownEvent);
            enableVBox(dateTimeEvent);
        } else {
            disableVBox(dateTimeEvent);
            enableVBox(countdownEvent);
        }
    }
    /* ********************************************************************** */

    ClassLoader classLoader = getClass().getClassLoader();

    ImageView getEuroIcon() {
        // Obtenez l'emplacement de la ressource à partir du classpath
        String imagePath = "Image/Icon8/price_80px.png";
        java.net.URL imageURL = classLoader.getResource(imagePath);

        // Chargez l'image à partir de l'URL obtenue
        Image euro = new Image(imageURL.toString(), 15, 30, true, true);
        ImageView iconEuro = new ImageView(euro);
        return iconEuro;
    }

    public ImageView getDeleteIcon() {
        // Obtenez l'emplacement de la ressource à partir du classpath
        String imagePath = "Image\\Icon8\\Delete_100px.png";
        java.net.URL imageURL = classLoader.getResource(imagePath);

        // Chargez l'image à partir de l'URL obtenue
        Image deleteImage = new Image(imageURL.toString(), 15, 30, true, true);

        ImageView iconDelete = new ImageView(deleteImage);
        return iconDelete;
    }

    public ImageView getEditIcon() {
        // Obtenez l'emplacement de la ressource à partir du classpath
        String imagePath = "Image\\Icon8\\edit_100px.png";
        java.net.URL imageURL = classLoader.getResource(imagePath);

        // Chargez l'image à partir de l'URL obtenue
        Image editImage = new Image(imageURL.toString(), 15, 30, true, true);

        ImageView iconEdit = new ImageView(editImage);
        return iconEdit;
    }

    ImageView getCancelIcon() {
        String imagePath = "Image\\Icon8\\cancel_10px.png";
        java.net.URL imageURL = classLoader.getResource(imagePath);
        // Chargez l'image à partir de l'URL obtenue
        Image editImage = new Image(imageURL.toString(), 15, 30, true, true);

        ImageView iconAnnuler = new ImageView(editImage);
        return iconAnnuler;
    }

    /*
     * *************************************************************
     * Configuration du tableau avec les medicament inferieur au seuil *
     */

    private void setUpCMDTable() throws SQLException {

        seuilStockTab.setEditable(true);

        TableColumn<Stock, Integer> IdMedoc = new TableColumn<Stock, Integer>("ID Medicament");
        TableColumn<Stock, Integer> NomMedoc = new TableColumn<Stock, Integer>("Nom Medicament");
        TableColumn<Stock, Integer> qtyMedoc = new TableColumn<Stock, Integer>("Quantité");
        TableColumn<Stock, Integer> qtyACMD = new TableColumn<Stock, Integer>("Quantité à commander");

        TableColumn<Stock, Double> prixMedoc = new TableColumn<Stock, Double>("Prix Utinitaire ( En € ) ");
        TableColumn<Stock, Double> montantCMD = new TableColumn<Stock, Double>("Montant Commande ");

        TableColumn<Stock, String> fournisseur = new TableColumn<Stock, String>("Fournisseur");

        TableColumn<Stock, StateCMD> etatCMD = new TableColumn<>("État de la commande");

        TableColumn<Stock, String> CountdownDate = new TableColumn<>("Compte à rebours");

        TableColumn<Stock, String> action = new TableColumn<Stock, String>("Action");

        IdMedoc.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("col_id"));

        // TableColumn<Stock, String> prixCmd = new TableColumn<Stock, String>("Montant
        // Commande");
        NomMedoc.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("col_nom"));
        // NomMedoc.setCellFactory(TextFieldTableCell.forTableColumn(new
        // IntegerStringConverter()));
        // NomMedoc.setOnEditCommit(
        // (CellEditEvent<Stock, Integer> t) -> {
        // ((Stock) t.getTableView().getItems().get(
        // t.getTablePosition().getRow())).setCol_quant_a_cmd(t.getNewValue());
        // });

        qtyMedoc.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("col_quant"));
        // qtyMedoc.setCellFactory(TextFieldTableCell.forTableColumn(new
        // IntegerStringConverter()));
        // qtyMedoc.setOnEditCommit(
        // (CellEditEvent<Stock, Integer> t) -> {
        // ((Stock) t.getTableView().getItems().get(
        // t.getTablePosition().getRow())).setCol_quant_a_cmd(t.getNewValue());
        // });

        qtyACMD.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("col_quant_a_cmd"));
        qtyACMD.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        qtyACMD.setOnEditCommit(
                (CellEditEvent<Stock, Integer> t) -> {
                    ((Stock) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setCol_quant_a_cmd(t.getNewValue());
                    try (Connection conn = ConnectionFactory.getConnection()) {
                        conn.setAutoCommit(false);
                        String sql = "UPDATE stock SET qty_a_cmd = ? WHERE idmedicament = ? AND nom = ?";
                        try {
                            PreparedStatement ps = conn.prepareStatement(sql);
                            ps.setInt(1, t.getNewValue());
                            ps.setInt(2, t.getTableView().getItems().get(t.getTablePosition().getRow()).getCol_id());
                            ps.setString(3,
                                    t.getTableView().getItems().get(t.getTablePosition().getRow()).getCol_nom());
                            ps.executeUpdate();
                            conn.commit();
                        } catch (SQLException e) {
                            conn.rollback();
                            e.printStackTrace();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

        prixMedoc.setCellValueFactory(new PropertyValueFactory<Stock, Double>("col_prix"));
        // prixMedoc.setCellFactory(TextFieldTableCell.forTableColumn(new
        // DoubleStringConverter()));
        // prixMedoc.setOnEditCommit(
        // (CellEditEvent<Stock, Double> t) -> {
        // ((Stock) t.getTableView().getItems().get(
        // t.getTablePosition().getRow())).setCol_prix(t.getNewValue());
        // });

        montantCMD.setCellValueFactory(new PropertyValueFactory<Stock, Double>("col_mont_cmd"));
        // montantCMD.setCellFactory(TextFieldTableCell.forTableColumn(new
        // DoubleStringConverter()));
        // montantCMD.setOnEditCommit(
        // (CellEditEvent<Stock, Double> t) -> {
        // ((Stock) t.getTableView().getItems().get(
        // t.getTablePosition().getRow())).setCol_mont_cmd(t.getNewValue());
        // });

        fournisseur.setCellValueFactory(new PropertyValueFactory<Stock, String>("col_four"));
        fournisseur.setCellFactory(TextFieldTableCell.forTableColumn());
        fournisseur.setOnEditCommit(
                (CellEditEvent<Stock, String> t) -> {
                    ((Stock) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setCol_four(t.getNewValue());
                    try (Connection conn = ConnectionFactory.getConnection()) {
                        conn.setAutoCommit(false);
                        String sql = "UPDATE stock SET fournisseur = ? WHERE idmedicament = ? AND nom = ?";
                        try {
                            PreparedStatement ps = conn.prepareStatement(sql);
                            ps.setString(1, t.getNewValue());
                            ps.setInt(2, t.getTableView().getItems().get(t.getTablePosition().getRow()).getCol_id());
                            ps.setString(3,
                                    t.getTableView().getItems().get(t.getTablePosition().getRow()).getCol_nom());
                            ps.executeUpdate();
                            conn.commit();
                        } catch (SQLException e) {
                            conn.rollback();
                            e.printStackTrace();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

        etatCMD.setCellValueFactory(new PropertyValueFactory<Stock, StateCMD>("stateCMD"));
        etatCMD.setCellFactory(ComboBoxTableCell.forTableColumn(StateCMD.values()));
        etatCMD.setEditable(false);
        etatCMD.setOnEditCommit(
                (CellEditEvent<Stock, StateCMD> t) -> {
                    Stock editedStock = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    editedStock.setStateCMD(t.getNewValue());

                    // Vérifiez si la quantité minimale n'a pas été atteinte ici.
                    if (editedStock.getCol_quant() >= editedStock.getCol_quant_min()) {
                        try (Connection conn = ConnectionFactory.getConnection()) {
                            conn.setAutoCommit(false);
                            String sql = "UPDATE stock SET etat_cmd = ? WHERE idmedicament = ? AND nom = ?";
                            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                                ps.setString(1, t.getNewValue().etatCommande);
                                ps.setInt(2, editedStock.getCol_id());
                                ps.setString(3, editedStock.getCol_nom());
                                ps.executeUpdate();
                                conn.commit();
                            } catch (SQLException e) {
                                conn.rollback();
                                e.printStackTrace();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    } else {
                        // Affichez une erreur si la quantité minimale a été atteinte.
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Impossible de changer l'état de la commande");
                        alert.setContentText("La quantité minimale n'a pas été atteinte");
                        alert.showAndWait();
                    }
                });

        CountdownDate.setCellValueFactory(new PropertyValueFactory<Stock, String>("col_countdown_date"));

        // CountdownDate.setCellFactory(TextFieldTableCell.forTableColumn(new
        // LocalDateTimeStringConverter()));
        // CountdownDate.setOnEditCommit(
        // (CellEditEvent<Stock, LocalDateTime> t) -> {
        // ((Stock) t.getTableView().getItems().get(
        // t.getTablePosition().getRow())).setCol_countdown_date(t.getNewValue());
        // try (Connection conn = ConnectionFactory.getConnection()) {
        // conn.setAutoCommit(false);
        // String sql = "UPDATE stock SET countdown_date = ? WHERE idmedicament = ? AND
        // nom = ?";
        // try (PreparedStatement ps = conn.prepareStatement(sql)) {
        // ps.setTimestamp(1, Timestamp.valueOf(t.getNewValue()));
        // ps.setInt(2,
        // t.getTableView().getItems().get(t.getTablePosition().getRow()).getCol_id());
        // ps.setString(3,
        // t.getTableView().getItems().get(t.getTablePosition().getRow()).getCol_nom());
        // ps.executeUpdate();
        // conn.commit();
        // } catch (SQLException e) {
        // conn.rollback();
        // e.printStackTrace();
        // }
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
        // });

        action.setCellValueFactory(new PropertyValueFactory<Stock, String>(null));

        // Add button on a colonne
        Callback<TableColumn<Stock, String>, TableCell<Stock, String>> cellFactory = new Callback<TableColumn<Stock, String>, TableCell<Stock, String>>() {
            @Override
            public TableCell call(final TableColumn<Stock, String> param) {
                final TableCell<Stock, String> cell = new TableCell<Stock, String>() {

                    final Button btnCMD = new Button();

                    final Button btnAnnule = new Button();

                    final Button editButton = new Button();

                    HBox hbox = new HBox(editButton, btnCMD, btnAnnule) {
                        {
                            setSpacing(10);
                            setAlignment(getInitialAlignment().CENTER);
                        }
                    };

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            editButton.setGraphic(getEditIcon()); // Recupération d'ImageIcon de maniere Dynamique
                            editButton.setTooltip(new Tooltip("Modifier la commande"));

                            btnAnnule.setGraphic(getCancelIcon()); // Recupération d'ImageIcon de maniere Dynamique
                            btnAnnule.setTooltip(new Tooltip("Annuler la commande"));

                            btnCMD.setGraphic(getEuroIcon()); // Recupération d'ImageIcon de maniere Dynamique
                            btnCMD.setTooltip(new Tooltip("Passer la commande"));
                            // btnAnnule.setGraphic(iconAnnule);

                            btnCMD.setOnAction(event -> {
                                Stock stock = getTableView().getItems().get(getIndex());
                                Integer qtyAcmd = stock.get_col_quant_a_cmdProperty();
                                System.out.println(qtyAcmd);

                                Double prix = stock.getCol_prix();// recuperation du prix deouis la BDD
                                Integer qty = stock.getCol_quant();// recuperation du quantité deouis la BDD
                                Integer qtyMax = stock.getCol_quant_max();// recuperation du quantité max deouis la BDD

                                Integer qtyConsomer = qtyMax - qty;// quantité à commander

                                Double quantiteAcommander  = (double)qtyConsomer; //conversion en double

                                Double MntCmd = quantiteAcommander * prix;


                                /*
                                boucle while
                                qty inferieur qty min
                                 */

                                if (qtyAcmd == null || (qtyAcmd <= 0)) {
                                    // envoyer la quantite a commmander dans la bdd
                                    //recuperre le montant

                                    qtyConsomer = qtyMax - qty;
                                    qtyAcmd = qtyConsomer;
                                    MntCmd = quantiteAcommander * prix;

                                    try (Connection conn = ConnectionFactory.getConnection()) {
                                        conn.setAutoCommit(false);
                                        String sql = "UPDATE stock SET montant_cmd = ?, qty_a_cmd = ? WHERE idmedicament = ? AND nom = ?";
                                        try (PreparedStatement ps = conn.prepareStatement(sql)) {
                                            ps.setDouble(1, MntCmd);
                                            ps.setInt(2, qtyAcmd);
                                            ps.setInt(3, stock.getCol_id());
                                            ps.setString(4, stock.getCol_nom());

                                            ps.executeUpdate();
                                            conn.commit();
                                        } catch (SQLException e) {
                                            conn.rollback();
                                            e.printStackTrace();
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }


                                  /*  Alert alert = new Alert(AlertType.ERROR);
                                    alert.setTitle("Erreur");
                                    alert.setHeaderText("Quantité à commander invalide");
                                    alert.setContentText("Veuillez saisir une quantité à commander valide");
                                    alert.showAndWait();

                                   */
                                    try (Connection conn = ConnectionFactory.getConnection()) {
                                        conn.setAutoCommit(false);
                                        String sql = "select * from stock  inner join medicament on idmedicament  = id;";
                                        try (PreparedStatement ps = conn.prepareStatement(sql)) {


                                            ps.executeUpdate();
                                            conn.commit();
                                        } catch (SQLException e) {
                                            conn.rollback();
                                            e.printStackTrace();
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }


                                    stock.setCol_mont_cmd(MntCmd);


                                } else {
                                    /*
                                     * Calcul Montant Cmd *
                                     */


                                   /* Integer qtyConsomer = qtyMax - qty;// quantité à commander

                                    Double quantiteAcommander  = (double)qtyConsomer; //conversion en double

                                    Double MntCmd = quantiteAcommander * prix;

                                    */


                                    stock.setCol_mont_cmd(MntCmd);

                                    try (Connection conn = ConnectionFactory.getConnection()) {
                                        conn.setAutoCommit(false);
                                        String sql = "UPDATE stock SET montant_cmd = ?, qty_a_cmd = ? WHERE idmedicament = ? AND nom = ?";
                                        try (PreparedStatement ps = conn.prepareStatement(sql)) {
                                            ps.setDouble(1, MntCmd);
                                            ps.setDouble(2, quantiteAcommander);

                                            ps.setInt(3, stock.getCol_id());
                                            ps.setString(4, stock.getCol_nom());
                                            ps.executeUpdate();
                                            conn.commit();
                                        } catch (SQLException e) {
                                            conn.rollback();
                                            e.printStackTrace();
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }

                                    // stock.setStateCMD("");
                                    getTableView().refresh();

                                    /* ****************************************** */
                                }

                                // On Click
                            });

                            btnAnnule.setOnAction(event -> {
                                Stock stock = getTableView().getItems().get(getIndex());
                                System.out.println(stock.getCol_four());
                            });

                            editButton.setOnAction(event -> {
                                Stock stock = getTableView().getItems().get(getIndex());
                                System.out.println(stock.getCol_id());
                                // Rendre editable cellule qyt_a_cmd et la cellule fournisseur

                                // qtyACMD.setEditable(true);
                                // fournisseur.setEditable(true);
                            });

                            setGraphic(hbox);

                            // ajouter les deux buttons dans la cellule
                            setContentDisplay(getContentDisplay().CENTER);

                            setText(null);
                            setAlignment(getAlignment().CENTER);
                        }
                    }
                };
                return cell;
            }
        };

        action.setCellFactory(cellFactory);

        seuilStockTab.getColumns().setAll(NomMedoc, qtyMedoc, qtyACMD, prixMedoc, montantCMD, fournisseur, etatCMD,
                /* CountdownDate, */ action);

        seuilStockTab.setItems(Model.getMedocInSeuil());

    }

    public void  passerCommande(){


    }

    /**** Ce bloc determinie le comprtement du Compte à rebours et la Date ****/
    private int days;
    private int hours;
    private int minutes;
    private int seconds;

    ObservableList<Event> listOfEvents = FXCollections.observableArrayList();

    /**
     * *
     * ***************************{@link #ControlSaisie()}****************************************
     * 
     * Ce code utilise les propriétés de liaison de JavaFX pour écouter les
     * modifications de la valeur de chaque champ de saisie. Chaque champ de saisie
     * est associé à un écouteur d'événement qui utilise une expression régulière
     * pour valider les entrées utilisateur. Si une entrée ne correspond pas à
     * l'expression régulière, elle est supprimée. <b> Les champs pour les jours et
     * les heures n'acceptent que des nombres, tandis que les champs pour les
     * minutes et les secondes n'acceptent que des nombres compris entre 0 et
     * 59.</b>
     * L'expression régulière [0-5]?[0-9] correspond à :
     * 
     * Un nombre compris entre 0 et 5, optionnel (le ? signifie optionnel)
     * Suivi d'un nombre compris entre 0 et 9
     * Si la nouvelle valeur entrée ne correspond pas à cette expression régulière,
     * la valeur précédente (oldValue) est réaffectée au champ minutesField en
     * utilisant minutesField.setText(oldValue).
     * 
     * Ainsi, seuls les nombres compris entre 0 et 59 peuvent être entrés dans ce
     * champ.
     */
    void ControlSaisie() {
        daysField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                daysField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        hoursField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                hoursField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        minutesField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-5]?[0-9]")) {
                minutesField.setText(oldValue);
            }
        });

        secondsField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-5]?[0-9]")) {
                secondsField.setText(oldValue);
            }
        });
    }

    void GetCountdown() {

        days = Integer.parseInt(daysField.getText());
        hours = Integer.parseInt(hoursField.getText());
        minutes = Integer.parseInt(minutesField.getText());
        seconds = Integer.parseInt(secondsField.getText());

    };

    @FXML
    void SetCountdown(ActionEvent event) throws SQLException {
        GetCountdown();
        TimeRemaining timing = new TimeRemaining();
        timing.startCountdown(time, days, hours, minutes, seconds);

    }

    /**
     * Cette Méthode doit etre appeler dans tous controle de saisi
     * À ajouter son interface de configuration dans la config de l'appli
     * 
     * @param message
     */
    public void showErrorMessage(String message) {
        Label errorLabel = new Label(/* s'il ny'a pas de message aficher "Entrée non valide" */
                message == null ? "Entrée non valide" : message);
        errorLabel.setTextFill(Color.WHITESMOKE);
        errorLabel.setFont(Font.font("Montserrat", FontWeight.BOLD, 14));
        StackPane sp = new StackPane(errorLabel);
        sp.setBackground(new Background(new BackgroundFill(Color.rgb(240, 65, 69), null, null)));
        // sp.setStyle("-fx-background-color: red;");

        sp.setAlignment(Pos.CENTER);
        cmdBorderPane.setBottom(sp);
        cmdBorderPane.setAlignment(sp, Pos.BOTTOM_CENTER);

        FadeTransition ft = new FadeTransition(Duration.millis(5000), sp);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
    }

    public void showInfoMessage(String message) {
        Label errorLabel = new Label(/* s'il ny'a pas de message aficher "Entrée non valide" */
                message == null ? "Entrée non valide" : message);
        errorLabel.setTextFill(Color.WHITESMOKE);
        errorLabel.setFont(Font.font("Montserrat", FontWeight.BOLD, 14));
        StackPane sp = new StackPane(errorLabel);
        sp.setBackground(new Background(new BackgroundFill(Color.rgb(171, 181, 91), null, null)));
        // sp.setStyle("-fx-background-color: red;");

        sp.setAlignment(Pos.CENTER);
        cmdBorderPane.setBottom(sp);
        cmdBorderPane.setAlignment(sp, Pos.BOTTOM_CENTER);

        FadeTransition ft = new FadeTransition(Duration.millis(5000), sp);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
    }

    private void setupDate() {
        dateEvent.setText("00-00-0000");
        dateEvent.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{2}-\\d{2}-\\d{4}")) {
                showErrorMessage("Invalid input. Only numbers are allowed.");
                dateEvent.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }

    private void setupDaysField() {
        daysField.setText("0");
        daysField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                showErrorMessage("Invalid input. Only numbers are allowed.");
                daysField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void setupHoursField() {
        hoursField.setText("0");
        hoursField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                showErrorMessage("Invalid input. Only numbers are allowed.");
                hoursField.setText(newValue.replaceAll("[^\\d]", ""));
                // Restrict hoursField.text to a number between 0 and 23
            } else if (Integer.parseInt(newValue) > 23) {
                showErrorMessage("Invalid input. Only numbers between 0 and 23 are allowed.");
                hoursField.setText(oldValue);
            }
            // bloquer le nombre à seulement 2 chiffre
            if (hoursField.getText().length() > 2) {
                hoursField.setText(hoursField.getText().substring(0, 2));
            }

        });

        dateHoursField.setText("0");
        dateHoursField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                showErrorMessage("Invalid input. Only numbers are allowed.");
                dateHoursField.setText(newValue.replaceAll("[^\\d]", ""));
            } else if (Integer.parseInt(newValue) > 23) {
                showErrorMessage("Invalid input. Only numbers between 0 and 23 are allowed.");
                dateHoursField.setText(oldValue);
            }
            // bloquer le nombre à seulement 2 chiffre

            if (dateHoursField.getText().length() > 2) {
                dateHoursField.setText(dateHoursField.getText().substring(0, 2));
            }

        });
    }

    private void setupMinutesField() {
        // minutesField.setText("0");
        minutesField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-5]?[0-9]")) {
                showErrorMessage("Invalid input. Only numbers between 0 and 59 are allowed.");
                minutesField.setText(oldValue);
            }
        });

        dateMinutesField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-5]?[0-9]")) {
                showErrorMessage("Invalid input. Only numbers between 0 and 59 are allowed.");
                dateMinutesField.setText(oldValue);
            }
        });

    }

    private void setupSecondsField() {
        // secondsField.setText("0");
        secondsField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-5]?[0-9]")) {
                showErrorMessage("Invalid input. Only numbers between 0 and 59 are allowed.");
                secondsField.setText(oldValue);
            }
        });

        dateSecondsField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-5]?[0-9]")) {
                showErrorMessage("Invalid input. Only numbers between 0 and 59 are allowed.");
                dateSecondsField.setText(oldValue);
            }
        });
    }

    private String showInputDialog(String title, String headerText) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private void showConfirmationDialog(String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static String generateEventID() {
        long timestamp = System.currentTimeMillis();
        int random = (int) (Math.random() * (999 - 100) + 100);
        return timestamp + "EVENT" + random;
    }

    @FXML
    void SetDate(ActionEvent event) {
        LocalDate eventDate = dateEvent.getValue();
        int hours;
        int minutes;
        int seconds;

        try {
            hours = Integer.parseInt(dateHoursField.getText().trim());
            minutes = Integer.parseInt(dateMinutesField.getText().trim());
            seconds = Integer.parseInt(dateSecondsField.getText().trim());
        } catch (NumberFormatException e) {
            showErrorMessage(
                    "Données de l'heure incorrectes ,Veuillez entrer des données d'heure valides (heures, minutes et secondes)");
            return;
        }

        LocalDateTime eventDateTime = LocalDateTime.of(eventDate, LocalTime.of(hours, minutes, seconds));

        String eventName = null;
        Event event2 = new Event();
        /*
         * Mise en place une alerte pour signaler que l'heure saisie (minutes
         * ou secondes) est supérieure à ce qui est autorisé 👇🏾
         * Si toutes les valeurs sont correctes, la méthode retourne true, sinon elle
         * retourne false.
         */
        if (dateHoursField.getText().isEmpty() || dateMinutesField.getText().isEmpty()
                || dateSecondsField.getText().isEmpty()) {
            showErrorMessage(
                    "Champs d'heure, de minute ou de seconde vides, Veuillez entrer des valeurs pour les champs d'heure, de minute et de seconde.");
            return;
            // Afficher une alerte pour signaler que les champs d'heure, de minute ou de
            // seconde sont vides
            // Alert alert = new Alert(Alert.AlertType.ERROR);
            // alert.setTitle("Erreur");
            // alert.setHeaderText("Champs d'heure, de minute ou de seconde vides");
            // alert.setContentText("Veuillez entrer des valeurs pour les champs d'heure, de
            // minute et de seconde.");
            // alert.showAndWait();
            // return;
        }

        if (!event2.verifyTimeFields(dateHoursField.getText(), dateMinutesField.getText(),
                dateSecondsField.getText())) {
            return;
        }

        if (!event2.verifyEventDateTime(eventDateTime)) {
            return;
        }

        while (eventName == null || !event2.verifyEventName(eventName)) {
            eventName = showInputDialog("Nom de l'événement", "Entrez le nom de l'événement");
            if (eventName == null) {
                break;
            } else if (!event2.verifyEventName(eventName)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Nom de l'événement vide");
                alert.setContentText("Veuillez entrer un nom pour l'événement.");
                alert.showAndWait();
            }
        }

        if (event2.verifyEventName(eventName)) {
            boolean eventExists = false;
            for (Event existingEvent : listOfEvents) {
                if (existingEvent.getEventName().equals(eventName)) {
                    eventExists = true;
                    break;
                }
            }
            if (!eventExists) {
                event2.setEventName(eventName);
                event2.setEventDateTime(eventDateTime);
                listOfEvents.add(event2);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Evénement déjà existant");
                alert.setContentText("Un événement avec ce nom existe déjà. Veuillez entrer un nom différent.");
                alert.showAndWait();
            }
        }

        String eventId = generateEventID();

        try (Connection conn = ConnectionFactory.getConnection()) {
            String sqlQuery = "INSERT INTO events (id, name, date_time) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sqlQuery);
            pst.setString(1, eventId);
            pst.setString(2, eventName);
            pst.setTimestamp(3, Timestamp.valueOf(eventDateTime));
            pst.executeUpdate();
            showConfirmationDialog("L'événement a été enregistré avec succès");
            EventScheduler.start();
        } catch (SQLException e) {
            showErrorMessage("Une erreur est survenue lors de l'enregistrement de l'événement");
        }
    }

    LocalDateTime taskDateTime = null;

    ModelTaskAcces taskAcces = new ModelTaskAcces();

    // Création d'une variable pour stocker la fréquence de la tâche
    int frequency = 1; // 1 = quotidienne, 7 = hebdomadaire, 30 = mensuelle

    @FXML
    void SetFrequency(ActionEvent event) throws SQLException {
        LocalDate taskDate = taskDatePicker.getValue();
        String taskName = taskNameField.getText().trim();
        if (dailyOption.isSelected()) {
            frequency = 0;
        } else if (dailyOption.isSelected()) {
            frequency = 1;
        } else if (weeklyOption.isSelected()) {
            frequency = 7;
        } else if (monthlyOption.isSelected()) {
            frequency = 30;
        }

        // Validation des données d'heure
        try {
            int hours = Integer.parseInt(taskHoursField.getText().trim());
            int minutes = Integer.parseInt(taskMinutesField.getText().trim());
            int seconds = Integer.parseInt(taskSecondsField.getText().trim());
            taskDateTime = LocalDateTime.of(taskDate, LocalTime.of(hours, minutes, seconds));

            if (hours > 23 || minutes > 59 || seconds > 59) {
                showErrorMessage(
                        "Heure, minutes ou secondes incorrectes, veuillez entrer des valeurs correctes pour les champs d'heure, de minute et de seconde");
                return;
            }
        } catch (NumberFormatException e) {
            showErrorMessage(
                    "Données de l'heure incorrectes, veuillez entrer des données d'heure valides (heures, minutes et secondes)");
            return;
        }

        // Validation des champs vides
        if (taskHoursField.getText().isEmpty() || taskMinutesField.getText().isEmpty()
                || taskSecondsField.getText().isEmpty() || taskName.isEmpty()) {
            showErrorMessage(
                    "Champs d'heure, de minute, de seconde ou de nom de tâche vides, veuillez remplir tous les champs");
            return;
        }

        // Code à ajouter pour enregistrer la tâche dans la base de données...

        // recupere la valeur de la fréquence de la tâche ici
        // System.out.println(frequency);

        // Insération des données dans la base de données
        taskAcces.saveTask(taskName, taskDateTime, frequency);

    }

    // Ajout de la tâche à la base de donn
    List<Task> taskList;
    ModelTaskAcces modelTaskAccess = new ModelTaskAcces();
    TaskScheduler taskScheduler = new TaskScheduler();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            taskList = modelTaskAccess.getAllTasks();
            taskList.forEach(task -> taskScheduler.scheduleTask(task));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // setupDate();
        try {
            taskAcces.displayTasks(taskTable);
            // creer moi la methode displayTasks dans la class ModelTaskAcces

        } catch (SQLException e) {
            e.printStackTrace();
        }
        setupDaysField();
        setupHoursField();
        setupMinutesField();
        setupSecondsField();
        try {
            setUpCMDTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
