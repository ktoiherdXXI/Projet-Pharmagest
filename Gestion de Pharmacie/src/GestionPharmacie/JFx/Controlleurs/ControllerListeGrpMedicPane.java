package GestionPharmacie.JFx.Controlleurs;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.ResourceBundle;

import org.controlsfx.control.tableview2.TableColumn2;
import org.controlsfx.control.tableview2.TableView2;
import GestionPharmacie.BDPM.Cis_grp_bdpm;
import GestionPharmacie.JFx.models.Model;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;

public class ControllerListeGrpMedicPane implements Initializable {

        @FXML
        private BorderPane borderPane;

        @FXML
        public TableView<Cis_grp_bdpm> tableView;

        // @FXML
        // public TableColumn<Cis_grp_bdpm, Integer> idGrp;

        // @FXML
        // public TableColumn<Cis_grp_bdpm, Integer> idMedoc;

        // @FXML
        // public TableColumn<Cis_grp_bdpm, String> nomGrp;

        // @FXML
        // public TableColumn<Cis_grp_bdpm, String> voieAdminGrp;

        @FXML
        private AnchorPane listeGrpMedicamentPane;

        // @FXML
        // private MFXTableView<Cis_grp_bdpm> listeGrpMedicTable;

        @FXML
        private MFXTextField dataR;

        @FXML
        private MFXTextField dataR1;

        public static Integer id_grp;
        public static Integer id_cis;

        /*
         * =============================================================================
         * Methods
         * =============================================================================
         */
        protected void setNode(Parent node, String chemin) {

                FadeTransition ft = new FadeTransition(Duration.millis(0100));
                ft.setNode(node);
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.setCycleCount(1);
                ft.setAutoReverse(false);
                ft.play();
                ft.setOnFinished((e) -> {
                        try {
                                Parent Dashboard = FXMLLoader.load(getClass().getResource(chemin));
                                borderPane.setCenter(Dashboard);
                                // borderPane.setOpacity(1);
                        } catch (IOException e1) {
                                e1.printStackTrace();
                        }
                });
        }

        private void setUpSimpleTable() throws SQLException {

                // tab.setEditable(true);

                TableColumn<Cis_grp_bdpm, Integer> idMedoc = new TableColumn<Cis_grp_bdpm, Integer>("ID Medicament");
                TableColumn<Cis_grp_bdpm, Integer> idGrp = new TableColumn<Cis_grp_bdpm, Integer>("ID Groupe");
                TableColumn<Cis_grp_bdpm, String> nomGrp = new TableColumn<Cis_grp_bdpm, String>(
                                "Nom Groupe Médicament");
                TableColumn2<Cis_grp_bdpm, String> voieAdminGrp = new TableColumn2<Cis_grp_bdpm, String>(
                                "Voie d'administration");

                idMedoc.setCellValueFactory(new PropertyValueFactory<Cis_grp_bdpm, Integer>("id_cis"));
                idGrp.setCellValueFactory(new PropertyValueFactory<Cis_grp_bdpm, Integer>("id_cis_grp"));
                nomGrp.setCellValueFactory(new PropertyValueFactory<Cis_grp_bdpm, String>("voie_administration"));
                voieAdminGrp.setCellValueFactory(new PropertyValueFactory<Cis_grp_bdpm, String>("libele_grp_gen"));

                tableView.getColumns().setAll(idMedoc, idGrp, nomGrp, voieAdminGrp);

                tableView.setItems(Model.getGrpMedoc());

                // idMedoc.setCellFactory(TextField2TableCell.forTableColumn());
                // idGrp.setCellFactory(TextField2TableCell.forTableColumn());

                // tab.getFixedColumns().setAll(nomGrp);
                // tab.getFixedRows().setAll(0, 1, 2);

                // tableView.setRowHeaderVisible(true);
        }

        private void selectDataTable() {

                // On double click on a row
                tableView.setOnMouseClicked(event -> {
                        if (event.getButton().equals(MouseButton.PRIMARY)) {
                                Cis_grp_bdpm cis_grp_bdpm = tableView.getSelectionModel().getSelectedItem();
                                id_grp = cis_grp_bdpm.getId_cis_grp();
                                id_cis = cis_grp_bdpm.getId_cis();
                                dataR.setText(String.valueOf(id_cis));
                                dataR1.setText(String.valueOf(id_grp));

                                System.out.println("id_grp = " + id_grp);
                                System.out.println("id_cis = " + id_cis);
                                if (event.getClickCount() == 2) {

                                        setNode(listeGrpMedicamentPane,
                                                        "../../FxmlVue/Containers/groupMedicamentPane.fxml");
                                }
                        }
                });

        }

        private void setupTable() throws SQLException {

                MFXTableColumn<Cis_grp_bdpm> colIdMedoc = new MFXTableColumn<>("ID Medicament ",
                                false,
                                Comparator.comparing(Cis_grp_bdpm::getId_cis));
                MFXTableColumn<Cis_grp_bdpm> colGrpMedoc = new MFXTableColumn<>("ID Groupe ",
                                false,
                                Comparator.comparing(Cis_grp_bdpm::getLibele_grp_gen));
                MFXTableColumn<Cis_grp_bdpm> colVoieAdministration = new MFXTableColumn<>("Voie d'administration",
                                false,
                                Comparator.comparing(Cis_grp_bdpm::getVoie_administration));
                MFXTableColumn<Cis_grp_bdpm> colNomGrp = new MFXTableColumn<>("Nom Groupe",
                                true,
                                Comparator.comparing(Cis_grp_bdpm::getLibele_grp_gen));
                // Declaration de Variable listeGrpMedicament à cette emplacement même.
                // Une variable simple Pour faire fonctionner le code
                // =============================👇🏾 ⚠ C'est different de listeGrpMedicament
                // colIdMedoc.setRowCellFactory(listeGrpMedicament -> new
                // MFXTableRowCell<>(Cis_grp_bdpm::getId_cis));

                colIdMedoc.setRowCellFactory(listeGrpMedicament -> new MFXTableRowCell<>(Cis_grp_bdpm::getId_cis) {
                        {
                                setOnMouseClicked(event -> {
                                        String id_Cis = String.valueOf(listeGrpMedicament.getId_cis_grp());
                                        id_grp = listeGrpMedicament.getId_cis_grp();

                                        String id_Grp = String.valueOf(listeGrpMedicament.getId_cis());
                                        id_cis = listeGrpMedicament.getId_cis();

                                        dataR.setText(id_Grp);
                                        dataR1.setText(id_Cis);

                                        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

                                                System.out.println(id_Cis);
                                                setNode(
                                                                listeGrpMedicamentPane,
                                                                "../../FxmlVue/Containers/groupMedicamentPane.fxml");
                                        }

                                });
                        }
                });
                colGrpMedoc.setRowCellFactory(listeGrpMedicament -> new MFXTableRowCell<>(Cis_grp_bdpm::getId_cis_grp) {
                        {
                                // setAlignment(Pos.CENTER_RIGHT);
                                setOnMouseClicked(getOnDragDetected());

                                setOnMouseClicked(event -> {
                                        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

                                                System.out.println(listeGrpMedicament.getId_cis_grp());
                                                String id_Cis = String.valueOf(listeGrpMedicament.getId_cis());
                                                String id_Grp = String.valueOf(listeGrpMedicament.getId_cis_grp());
                                                // ajouter id_Cis et id_Grp dans dataR
                                                dataR.setText(id_Grp);
                                                dataR1.setText(id_Cis);

                                                System.out.println(id_Cis);

                                                // dataR.setText(id_Cis);
                                                // ajouter
                                                // String.valueOf(grpMedicament.getId_cis()));

                                                setNode(
                                                                listeGrpMedicamentPane,
                                                                "../../FxmlVue/Containers/groupMedicamentPane.fxml");
                                        }
                                });
                        }
                });
                // colNomGrp.setAlignment(Pos.CENTER_RIGHT);
                colVoieAdministration.setRowCellFactory(
                                listeGrpMedicament -> new MFXTableRowCell<>(Cis_grp_bdpm::getVoie_administration));
                colNomGrp.setRowCellFactory(
                                listeGrpMedicament -> new MFXTableRowCell<>(Cis_grp_bdpm::getLibele_grp_gen));
                // listeGrpMedicTable.getTableColumns().addAll(colIdMedoc, colGrpMedoc,
                // colNomGrp, colVoieAdministration);
                // listeGrpMedicTable.getFilters().addAll(new IntegerFilter<>("ID Medicament",
                // Cis_grp_bdpm::getId_cis),
                // new IntegerFilter<>("ID Groupe ", Cis_grp_bdpm::getId_cis_grp),
                // new StringFilter<>("Nom Groupe ", Cis_grp_bdpm::getLibele_grp_gen),
                // new StringFilter<>("Voie d'administration",
                // Cis_grp_bdpm::getVoie_administration));
                // listeGrpMedicTable.setItems(Model.getGrpMedoc());

                // Model.getMedoc().forEach(System.out::println);
        }

        /*
         * 
         * Initialize
         * 
         */

        /*
         * (non-Javadoc)
         * 
         * @see javafx.fxml.Initializable#initialize(java.net.URL,
         * java.util.ResourceBundle)
         */
        @Override
        public void initialize(URL location, ResourceBundle resources) {
                selectDataTable();
                try {
                        Model.getGrpMedoc();
                        setUpSimpleTable();
                } catch (SQLException e) {
                        e.printStackTrace();
                }

                // listeGrpMedicTable.autosizeColumnsOnInitialization();
                // grpMedicTable.getTransformableList();
        }
}
