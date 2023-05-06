package GestionPharmacie.JFx.Controlleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.ResourceBundle;

import GestionPharmacie.BDPM.Cis_cip_bdpm;
import GestionPharmacie.JFx.models.Model;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;

// import MFXPaginatedTableView from the project

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.EnumFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.observables.When;

import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class ControllerListeMedicPane implements Initializable {

    // ================================================================================
    // Properties
    // ================================================================================

    @FXML
    private MFXButton btnAjoutMedicament;

    @FXML
    private MFXPaginatedTableView<Cis_cip_bdpm> listMedicTable;

    @FXML
    private AnchorPane listMedicamentAnchPane;

    // ================================================================================
    // Methods
    // ================================================================================
    private void setupPaginatedTab() throws SQLException {
        MFXTableColumn<Cis_cip_bdpm> idColumn = new MFXTableColumn<>("ID Médicament", false,
                Comparator.comparing(Cis_cip_bdpm::getID));
        MFXTableColumn<Cis_cip_bdpm> nameColumn = new MFXTableColumn<>("Nom Médicament", true,
                Comparator.comparing(Cis_cip_bdpm::getLibeleCommune));
        // MFXTableColumn<Device> ipColumn = new MFXTableColumn<>("IP", false,
        // Comparator.comparing(Device::getIP));
        // MFXTableColumn<Device> ownerColumn = new MFXTableColumn<>("Owner", false,
        // Comparator.comparing(Device::getOwner));
        // MFXTableColumn<Device> stateColumn = new MFXTableColumn<>("State", false,
        // Comparator.comparing(Device::getState));

        idColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Cis_cip_bdpm::getID));
        nameColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Cis_cip_bdpm::getLibeleCommune));
        nameColumn.setScaleShape(false);
        // ipColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getIP) {
        // {
        // setAlignment(Pos.CENTER); // Allignement du contenu de la colone "IP" au
        // Centre
        // }
        // });
        // ownerColumn.setRowCellFactory(device -> new
        // MFXTableRowCell<>(Device::getOwner));
        // stateColumn.setRowCellFactory(device -> new
        // MFXTableRowCell<>(Device::getState));
        // ipColumn.setAlignment(Pos.CENTER); // Allignement de la colone "IP"

        // listMedicTable.getTableColumns().addAll(idColumn, nameColumn, ipColumn,
        // ownerColumn, stateColumn);
        listMedicTable.getTableColumns().addAll(idColumn, nameColumn);
        listMedicTable.getFilters().addAll(new IntegerFilter<>("ID", Cis_cip_bdpm::getID), new StringFilter<>("Name",
                Cis_cip_bdpm::getLibeleCommune)
        // new StringFilter<>("IP", Device::getIP),
        // new StringFilter<>("Owner", Device::getOwner),
        // new EnumFilter<>("State", Device::getState, Device.State.class)
        );
        listMedicTable.setItems(Model.getAllMedoc());
        // Model.getMedoc().forEach(System.out::println);
    }

    @FXML
    void AjoutMedicament(ActionEvent event) {

        setNode(listMedicamentAnchPane, "../../FxmlVue/Containers/ajoutMedicamentPane.fxml");
    }

    public void setNode(Parent node, String chemin) {

        FadeTransition ft = new FadeTransition(Duration.millis(0500));
        ft.setNode(listMedicamentAnchPane);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        ft.setOnFinished((e) -> {
            try {
                // Stage stg = new Stage();
                Parent listMedicament = FXMLLoader.load(getClass().getResource(chemin));
                listMedicamentAnchPane.getChildren().setAll(listMedicament);
                listMedicamentAnchPane.setOpacity(1);
                // 👆🏾
                // listMedicamentAnchPane.getChildren().addAll(listMedicament);
                // Aussi Fonctionnel pour remplacer la ligne au dessus

                // stg.setTitle("PharmaGest ⚕ Votre Pharmacie");
                // stg.setScene(scene);
                // stg.setResizable(false);
                // stg.centerOnScreen();
                // stg.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setupPaginatedTab();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        When.onChanged(listMedicTable.currentPageProperty())
                .then((oldValue, newValue) -> listMedicTable.autosizeColumns())
                .listen();

    }

}
