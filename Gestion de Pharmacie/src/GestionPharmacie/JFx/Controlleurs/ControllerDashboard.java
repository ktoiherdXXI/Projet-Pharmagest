package GestionPharmacie.JFx.Controlleurs;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXToggleNode;

import GestionPharmacie.ConnectionFactory;
import GestionPharmacie.JFx.models.TimeRemaining;

// import GestionPharmacie.JFx.utils.Tile;

import eu.hansolo.tilesfx.Tile;

import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.TimeSection;
import eu.hansolo.tilesfx.TimeSectionBuilder;
import eu.hansolo.tilesfx.Tile.SkinType;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class ControllerDashboard implements Initializable {

    /***************************************************************************
     * *
     * * Properties
     * *
     **************************************************************************/

    private Tile clockTile;

    private Tile timerControlTile;

    @FXML
    AnchorPane dashboardPane;

    @FXML
    private View viewPane;

    @FXML
    private Text nbMedicamaent;

    @FXML
    private Text nbGrpMedicament;

    @FXML
    private ToggleGroup deconnexion;

    @FXML
    private Text pseudo;

    @FXML
    private JFXToggleNode btnDashboard;

    @FXML
    private ToggleGroup btnLateral;

    @FXML
    private MFXTextField searchBar;

    @FXML
    private BorderPane borderPane;

    @FXML
    private JFXToggleNode btnInventaire;

    @FXML
    private JFXToggleNode btnListeMedicament;

    @FXML
    private ToggleButton btnGrpMedicament;

    @FXML
    private JFXToggleNode btnConfig;

    @FXML
    private JFXToggleNode btnVente;

    @FXML
    private JFXToggleNode btnPayment;

    @FXML
    private JFXToggleNode btnRapport;

    @FXML
    private JFXToggleNode btnAchat;

    @FXML
    private JFXToggleNode btnClient;

    @FXML
    private JFXToggleNode btnFournisseur;

    @FXML
    private JFXToggleNode btnStock;

    @FXML
    private JFXToggleNode btnUtilisateur;

    @FXML
    private JFXToggleNode btnPharmacie;

    @FXML
    private JFXToggleNode btnParametre;

    @FXML
    private JFXToggleNode btnDeconnexion;

    private static final double TILE_WIDTH = 150;
    private static final double TILE_HEIGHT = 150;

    /***************************************************************************
     * *
     * * Test
     * *
     **************************************************************************/
    @FXML
    void initialize() {
        assert btnDashboard != null : "fx:id=\"btnDashboard\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnLateral != null : "fx:id=\"btnLateral\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnInventaire != null
                : "fx:id=\"btnInventaire\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert dashboardPane != null
                : "fx:id=\"dashboardPane\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert deconnexion != null : "fx:id=\"deconnexion\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert pseudo != null : "fx:id=\"pseudo\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert searchBar != null : "fx:id=\"searchBar\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert borderPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnVente != null : "fx:id=\"btnVente\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnAchat != null : "fx:id=\"btnAchat\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnClient != null : "fx:id=\"btnClient\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnFournisseur != null
                : "fx:id=\"btnFournisseur\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnStock != null : "fx:id=\"btnStock\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnUtilisateur != null
                : "fx:id=\"btnUtilisateur\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnPharmacie != null : "fx:id=\"btnPharmacie\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnParametre != null : "fx:id=\"btnParametre\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnDeconnexion != null
                : "fx:id=\"btnDeconnexion\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnGrpMedicament != null
                : "fx:id=\"btnGrpMedicament\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnListeMedicament != null
                : "fx:id=\"btnListeMedicament\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnConfig != null : "fx:id=\"btnConfig\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnPayment != null : "fx:id=\"btnPayment\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert btnRapport != null : "fx:id=\"btnRapport\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert viewPane != null : "fx:id=\"pane\" was not injected: check your FXML file 'dashboard.fxml'.";

    }

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    public void ShowDashboardPane(ActionEvent event) {
        setNode(dashboardPane, "../../FxmlVue/dashboardPane.fxml");
    }

    @FXML
    void ShowInventairePane(ActionEvent event) throws IOException {
        setNode(dashboardPane, "../../FxmlVue/Containers/inventairePane.fxml");
    }

    @FXML
    void ShowGroupMedicamentPane(ActionEvent event) {
        setNode(dashboardPane, "../../FxmlVue/Containers/listeGroupMedicamentPane.fxml");
    }

    @FXML
    void ShowRapportPane(ActionEvent event) {
        setNode(dashboardPane, "../../FxmlVue/Containers/rapportPane.fxml");
    }

    @FXML
    void ShowRapportPaymentPane(ActionEvent event) {
        setNode(dashboardPane, "../../FxmlVue/Containers/rapportPaymentPane.fxml");

    }

    @FXML
    void ShowRapportVentePane(ActionEvent event) {
        setNode(dashboardPane, "../../FxmlVue/Containers/rapportVentePane.fxml");

    }

    @FXML
    void ShowConfigPane(ActionEvent event) {
        setNode(dashboardPane, "../../FxmlVue/Containers/configPane.fxml");
    }

    @FXML
    void Deconnexion(ActionEvent event) {
    }

    @FXML
    void ShowAchatPane(ActionEvent event) {
        setNode(dashboardPane, "../../FxmlVue/Containers/achatPane.fxml");
    }

    @FXML
    void ShowListeMedicament(ActionEvent event) {
        setNode(dashboardPane, "../../FxmlVue/Containers/listeMedicamentPane.fxml");
    }

    @FXML
    void ShowClientPane(ActionEvent event) {
        setNode(dashboardPane, "../../FxmlVue/Containers/clientPane.fxml");
    }

    @FXML
    void ShowFournisseurPane(ActionEvent event) {
        setNode(dashboardPane, "../../FxmlVue/Containers/fournisseurPane.fxml");
    }

    @FXML
    void ShowCommandePane(ActionEvent event) {
        setNode(dashboardPane, "../../FxmlVue/Containers/commandePane.fxml");
    }

    @FXML
    void ShowStockPane(ActionEvent event) {
        setNode(dashboardPane, "../../FxmlVue/Containers/stockPane.fxml");
    }

    // borderPane.setCenter(dashboardPane);

    /**
     * @param node , chemin
     */
    public void setNode(Parent node, String chemin) {

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

    /***************************************************************************
     * *
     * * Méthodes
     * *
     **************************************************************************/

    // Clock Data
    public void ClockControl() {
        clockTile = TileBuilder.create()
                .skinType(SkinType.CLOCK)
                // .prefSize(TILE_WIDTH, TILE_HEIGHT)
                // use max
                // .maxSize(200, 200)
                // .title("Bonjour Mr Nadje (❁´◡`❁) Il est")
                // .text("Whatever text")
                // set text invisble
                // .textVisible(false) // Not work
                // .dateVisible(false) // Not work
                // .textAlignment(TextAlignment.CENTER)

                .titleAlignment(TextAlignment.CENTER)
                .textColor(Color.DARKBLUE)
                .titleColor(Color.DARKBLUE)
                .backgroundColor(Color.rgb(244, 244, 244, 0.5))
                // set size of hour

                .dateColor(Color.rgb(48, 183, 245))

                .textVisible(true)
                // .backgroundColor(Color.rgb(0, 0, 0, 0.5))
                // // .borderColor(Color.rgb(0, 0, 0, 0.5))
                .roundedCorners(true)

                .locale(Locale.FRENCH)
                .running(true)
                .build();

        viewPane.setCenter(clockTile);

    }

    // TimeControl Data
    public void TimeControl() {
        TimeSection timeSection = TimeSectionBuilder.create()
                .start(LocalTime.now().plusSeconds(20))
                .stop(LocalTime.now().plusHours(1))

                .days(DayOfWeek.MONDAY, DayOfWeek.FRIDAY)
                .color(Color.rgb(30, 39, 51, 1))
                .highlightColor(Color.rgb(30, 39, 51, 1))
                .build();

        timerControlTile = TileBuilder.create()
                .skinType(SkinType.TIMER_CONTROL)
                .hourColor(Color.DARKBLUE)
                .minuteColor(Color.DARKGOLDENROD)
                .secondColor(Color.DARKRED)
                .secondsVisible(true)
                .backgroundColor(Color.rgb(244, 244, 244, 1))
                // .borderColor(Color.rgb(0, 0, 0, 1))
                .roundedCorners(true)
                .title("Timer Control")
                .titleAlignment(TextAlignment.CENTER)
                .titleColor(Color.rgb(30, 39, 51, 1))
                .textColor(Color.rgb(30, 39, 51, 1))
                .textVisible(true)
                .running(true)
                .timeSections(timeSection)
                .build();

        viewPane.setCenter(timerControlTile);
    }

    void hoverOclock() {
        // On hover to viewPane show clockTile in viewPane
        viewPane.setOnMouseEntered(e -> {
            ClockControl();
        });
        viewPane.setOnMouseExited(e -> {
            TimeControl();
        });
    }

    void DataSynchro() throws SQLException {
        // set nbMedicamaent comme etant le nombre de médicament enregistré sur la Table
        // médicament

        Connection conn = ConnectionFactory.getConnection();
        String sqlNbMedic = "SELECT COUNT(DISTINCT id) FROM cis_cip_bdpm";
        String sqlNbGrpMedic = "SELECT COUNT(DISTINCT id_cis_grp) FROM cis_grp_bdpm";

        try {
            PreparedStatement ps = conn.prepareStatement(sqlNbMedic);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nbMedicamaent.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement ps = conn.prepareStatement(sqlNbGrpMedic);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nbGrpMedicament.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initialize();
        TimeControl();
        hoverOclock();

        try {
            DataSynchro();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
