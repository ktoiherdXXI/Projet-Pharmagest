package GestionPharmacie.JFx.Controlleurs;

import java.io.IOException;

import com.jfoenix.controls.JFXTextArea;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ControllerDetailMedicament {
    @FXML
    private AnchorPane detailMedicamentAnchorPane;

    @FXML
    private MFXButton editMedoc;

    @FXML
    private AnchorPane effetSecondMedoc;

    @FXML
    private Label grpMedoc;

    @FXML
    private Label idMedoc;

    @FXML
    private Hyperlink listMedicamentLink;

    @FXML
    private Label nbFournisseurs;

    @FXML
    private Label nbMedocRestant;

    @FXML
    private Label nbVenteSurMedoc;

    @FXML
    private Label nomMedicament;

    @FXML
    private MFXButton supMedoc;

    @FXML
    private JFXTextArea usageMedoc;

    @FXML
    void GoListMedicament(ActionEvent event) {
        setNode(detailMedicamentAnchorPane, "../../FxmlVue/Containers/listeMedicamentPane.fxml");
    }

    public void setNode(Parent node, String chemin) {

        FadeTransition ft = new FadeTransition(Duration.millis(0500));
        ft.setNode(node);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        ft.setOnFinished((e) -> {
            try {
                // Stage stg = new Stage();
                Parent listMedicament = FXMLLoader.load(getClass().getResource(chemin));
                detailMedicamentAnchorPane.getChildren().setAll(listMedicament);
                detailMedicamentAnchorPane.setOpacity(1);
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

}
