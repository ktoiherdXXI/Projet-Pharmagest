package GestionPharmacie.JFx.Controlleurs;

import GestionPharmacie.BDPM.Cis_cip_bdpm;
import GestionPharmacie.JFx.models.ListeGrpMedicament;
import GestionPharmacie.JFx.models.Model;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Map;
import java.util.ResourceBundle;

public class ControllerGrpMedicPane implements Initializable {
    @FXML
    private MFXGenericDialog dialogContent;

    @FXML
    private MFXStageDialog dialog;

    @FXML
    private MFXButton btnAjoutMedicament;

    @FXML
    private MFXTableView<Cis_cip_bdpm> grpMedicTable;

    @FXML
    private GridPane grid;

    @FXML
    private AnchorPane grpMedicamentPane;

    @FXML
    private MFXFilterComboBox<ListeGrpMedicament> filterCombo;

    @FXML
    private MFXTextField data;

    private void setupTable() throws SQLException {
        MFXTableColumn<Cis_cip_bdpm> colIdMedoc = new MFXTableColumn<>("ID Medicament ",
                false,
                Comparator.comparing(Cis_cip_bdpm::getID));
        MFXTableColumn<Cis_cip_bdpm> colGrpMedoc = new MFXTableColumn<>("Libelé Medicament ",
                true,
                Comparator.comparing(Cis_cip_bdpm::getLibeleCommune));

        // MFXTableColumn<Cis_grp_bdpm> colVoieAdministration = new
        // MFXTableColumn<>("Voie d'administration",
        // false,
        // Comparator.comparing(Cis_grp_bdpm::getVoie_administration));
        // MFXTableColumn<Cis_grp_bdpm> colNomGrp = new MFXTableColumn<>("Nom Groupe",
        // true,
        // Comparator.comparing(Cis_grp_bdpm::getLibele_grp_gen));
        // Declaration de Variable grpMedicament à cette emplacement même.
        // Une variable simple Pour faire fonctionner le code
        // =============================👇🏾 ⚠ C'est different de GrpMedicament⚠
        // colIdMedoc.setRowCellFactory(grpMedicament -> new
        // MFXTableRowCell<>(Cis_grp_bdpm::getId_cis));

        colIdMedoc.setRowCellFactory(grpMedicament -> new MFXTableRowCell<>(Cis_cip_bdpm::getID) {

            {
                setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

                        String IDMedoc = String.valueOf(grpMedicament.getID());
                        String NomMedoc = String.valueOf(grpMedicament.getLibeleCommune());

                        System.out.println(IDMedoc);
                        System.out.println(NomMedoc);

                        // dataR.setText(id_Cis);
                        // ajouter
                        // String.valueOf(grpMedicament.getId_cis()));

                        // setNode(
                        // listeGrpMedicamentPane,
                        // "../../FxmlVue/Containers/groupMedicamentPane.fxml");
                    }

                });
            }
        });
        colGrpMedoc.setRowCellFactory(grpMedicament -> new MFXTableRowCell<>(Cis_cip_bdpm::getLibeleCommune) {
            {
                // setAlignment(Pos.CENTER_RIGHT);
                setOnMouseClicked(getOnDragDetected());

                setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

                        String IDMedoc = String.valueOf(grpMedicament.getID());
                        String NomMedoc = String.valueOf(grpMedicament.getLibeleCommune());

                        System.out.println(IDMedoc);
                        System.out.println(NomMedoc);

                        // dataR.setText(id_Cis);
                        // ajouter
                        // String.valueOf(grpMedicament.getId_cis()));

                        // setNode(
                        // listeGrpMedicamentPane,
                        // "../../FxmlVue/Containers/groupMedicamentPane.fxml");
                    }
                });
            }
        });

        // colNomGrp.setAlignment(Pos.CENTER_RIGHT);
        // colVoieAdministration.setRowCellFactory(
        // grpMedicament -> new
        // MFXTableRowCell<>(Cis_grp_bdpm::getVoie_administration));
        // colNomGrp.setRowCellFactory(grpMedicament -> new
        // MFXTableRowCell<>(Cis_grp_bdpm::getLibele_grp_gen));

        grpMedicTable.getTableColumns().addAll(colIdMedoc, colGrpMedoc);

        // ⚠ ☝🏿 À bien indiquer la class qui porte l'observableArray sur le Tableau <?>

        grpMedicTable.getFilters().addAll(
                new IntegerFilter<>("ID Médicament", Cis_cip_bdpm::getID),
                new StringFilter<>("Nom Médicament ",
                        Cis_cip_bdpm::getLibeleCommune));
        // new IntegerFilter<>("ID Groupe ", Cis_grp_bdpm::getId_cis_grp),

        // new StringFilter<>("Voie d'administration",
        // Cis_grp_bdpm::getVoie_administration)
        grpMedicTable.setItems(Model.getMedoc());
        Model model = new Model();
        // data.setText(String.valueOf(model.valeur_cis_cip_bdpm_id));
        // Model.getMedoc().forEach(System.out::println);
    }

    void ControllerGrpMedicPane(Stage stage) {

        Platform.runLater(() -> {
            this.dialogContent = MFXGenericDialogBuilder.build()
                    // .setContent(Content())
                    // .setContentText(Model.ipsum)
                    // .setContentText("Ajouter un Médicament au Groupe")
                    .makeScrollable(true)
                    .get();
            this.dialog = MFXGenericDialogBuilder.build(dialogContent)
                    .toStageDialogBuilder()
                    .initOwner(stage)
                    .initModality(Modality.APPLICATION_MODAL)
                    .setDraggable(true)
                    .setTitle("Dialogs Preview")
                    .setOwnerNode(grpMedicamentPane)
                    .setScrimPriority(ScrimPriority.WINDOW)
                    .setScrimOwner(true)
                    .get();

            MFXButton cancelButton = new MFXButton("Cancel");
            cancelButton.setStyle(
                    "-fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: #ff0000; -fx-alignment: left;");

            // HBox buttonContainer = new HBox();
            // buttonContainer.getChildren().addAll(btnConfirmation, cancelButton);
            // buttonContainer.setAlignment(Pos.BASELINE_LEFT);

            dialogContent.addActions(
                    Map.entry(new MFXButton("Confirm") /* btnConfirmation , */ , event -> {
                    }),
                    Map.entry(cancelButton, event -> dialog.close()));

            dialogContent.setMaxSize(565, 270);
        });
    }

    void convertDialogTo(String styleClass) {
        dialogContent.getStyleClass().removeIf(
                s -> s.equals("mfx-info-dialog") || s.equals("mfx-warn-dialog") || s.equals("mfx-error-dialog"));

        if (styleClass != null)
            dialogContent.getStyleClass().add(styleClass);
    }

    void Content() {
        // dialogContent.setContentText("Veuillez choisir un Médicament à ajouter au
        // groupe");
        // text.setStyle("-fx-font-weight: bold");
        // text.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        Text text = new Text("Veuillez choisir un Médicament à ajouter au groupe");
        text.setFont(Font.font("null", FontWeight.SEMI_BOLD, 12));
        // String str = text.getText();

        MFXFilterComboBox comboBox = new MFXFilterComboBox();
        comboBox.setPrefWidth(300);
        comboBox.setPrefHeight(30);

        comboBox.setPromptText("Entrer le nom du médicament ou son Id ");
        // set FontSize of ComboBox

        comboBox.getItems().addAll("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8",
                "Item 9", "Item 10", "Item 11", "Item 12", "Item 13", "Item 14", "Item 15", "Item 16", "Item 17",
                "Item 18", "Item 19", "Item 20", "Item 21", "Item 22", "Item 23", "Item 24",
                "Item 25", "Item 26", "Item 27", "Item 28", "Item 29", "Item 30", "Item 31",
                "Item 32", "Item 33", "Item 34", "Item 35", "Item 36", "Item 37", "Item 38",
                "Item 39", "Item 40", "Item 41", "Item 42", "Item 43", "Item 44", "Item 45",
                "Item 46", "Item 47", "Item 48", "Item 49", "Item 50");
        // dialogContent.setCenter(comboBox);

        MFXButton btnConfirmation = new MFXButton("Confirmer");
        btnConfirmation.setPrefWidth(100);
        btnConfirmation.setPrefHeight(30);
        btnConfirmation.setStyle(
                "-fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: #00bfff; -fx-alignment: left;");

        btnConfirmation.setOnAction(event -> { // Action à venir
            // dialog.close();
        });

        VBox vbox = new VBox(text, comboBox/* , btnConfirmation */);
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setSpacing(10);

        dialogContent.setCenter(vbox);
        // vbox.setMargin(vbox, new Insets(0, 10, 0, 0)); // Ajoute une marge de 10
        // pixels à droite du texte

        dialogContent.setBottom(btnConfirmation);
    }

    @FXML
    public void openInfo(ActionEvent event) {
        ControllerGrpMedicPane((Stage) grpMedicamentPane.getScene().getWindow());
        MFXFontIcon infoIcon = new MFXFontIcon("mfx-info-circle", 18);
        dialogContent.setHeaderIcon(infoIcon);
        dialogContent.setHeaderText("Ajouter un Médicament au Groupe");
        convertDialogTo("mfx-info-dialog");
        Content();
        dialog.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setupTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ControllerGrpMedicPane(dialog);

    }

}
