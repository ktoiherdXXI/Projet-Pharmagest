package GestionPharmacie.JFx.Controlleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import GestionPharmacie.JFx.models.Device;
import GestionPharmacie.JFx.models.Model;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;

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

public class ControllerListeMedicPane2 implements Initializable {

    // ================================================================================
    // Properties
    // ================================================================================

    @FXML
    private MFXButton btnAjoutMedicament;

    @FXML
    private MFXPaginatedTableView<Device> listMedicTable;

    @FXML
    private AnchorPane listMedicamentAnchPane;

    // ================================================================================
    // Methods
    // ================================================================================
    private void setupPaginatedTab() {
        MFXTableColumn<Device> idColumn = new MFXTableColumn<>("ID", false, Comparator.comparing(Device::getID));
        MFXTableColumn<Device> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(Device::getName));
        MFXTableColumn<Device> ipColumn = new MFXTableColumn<>("IP", false, Comparator.comparing(Device::getIP));
        MFXTableColumn<Device> ownerColumn = new MFXTableColumn<>("Owner", false,
                Comparator.comparing(Device::getOwner));
        MFXTableColumn<Device> stateColumn = new MFXTableColumn<>("State", false,
                Comparator.comparing(Device::getState));

        idColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getID));
        nameColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getName));
        nameColumn.setScaleShape(false);
        ipColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getIP) {
            {
                setAlignment(Pos.CENTER); // Allignement du contenu de la colone "IP" au Centre
            }
        });
        ownerColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getOwner));
        stateColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getState));
        ipColumn.setAlignment(Pos.CENTER); // Allignement de la colone "IP"

        listMedicTable.getTableColumns().addAll(idColumn, nameColumn, ipColumn, ownerColumn, stateColumn);
        listMedicTable.getFilters().addAll(
                new IntegerFilter<>("ID", Device::getID),
                new StringFilter<>("Name", Device::getName),
                new StringFilter<>("IP", Device::getIP),
                new StringFilter<>("Owner", Device::getOwner),
                new EnumFilter<>("State", Device::getState, Device.State.class));
        listMedicTable.setItems(Model.devices);
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
        setupPaginatedTab();

        When.onChanged(listMedicTable.currentPageProperty())
                .then((oldValue, newValue) -> listMedicTable.autosizeColumns())
                .listen();

    }

}
