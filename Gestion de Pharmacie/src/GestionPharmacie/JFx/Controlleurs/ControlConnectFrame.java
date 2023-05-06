package GestionPharmacie.JFx.Controlleurs;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;

import GestionPharmacie.App;
import GestionPharmacie.ConnectionFactory;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
/* Import Login Frame */
import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

public class ControlConnectFrame implements Initializable {

    ControlRegisterFrame registerFrame = new ControlRegisterFrame();
    // recuperer l'anchorPane de la classe ControlRegister
    @FXML
    private AnchorPane registerF = registerFrame.getRegister();

    @FXML
    private BorderPane connectStage;

    @FXML
    private AnchorPane connectPane;

    // Pour pouvoir utiliser le MFXPassword de la newPageLogin*/
    // @FXML
    // private TextField login;
    // @FXML
    // private PasswordField pass;

    /*
     * @FXML
     * private JFXButton validateBtn;
     */
    String log, pswd;

    /* New Login Frame */
    @FXML
    private Hyperlink btnRegister;

    @FXML
    private MFXTextField login;

    @FXML
    private MFXPasswordField pass;

    @FXML
    private MFXButton dbConnect;

    @FXML
    private AutoCompleteTextField<?> dbHost;

    @FXML
    private AutoCompleteTextField<?> dbName;

    @FXML
    private PasswordField dbPswd;

    @FXML
    private AutoCompleteTextField<?> dbUser;

    @FXML
    private MFXButton loginBtn;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private MFXPasswordField password;

    @FXML
    private AutoCompleteTextField<?> port;

    // @FXML
    // void login(ActionEvent event) {
    // }
    @FXML
    void Verif_Db_connect(ActionEvent event) {

    }

    /**
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void Verif_Id(ActionEvent event) throws SQLException, IOException {
        CheckLogin();
    }
    // Bienvenu com = new Bienvenu();
    // com.setVisible(true);
    // dispose();
    // JOptionPane.showMessageDialog(btnNewButton, " connexion reussi avec succer");

    // } else {
    // System.out.println("Connexion Failled");
    // JOptionPane.showMessageDialog(btnNewButton, " Identifient ou Mot de passe
    // Invalid");
    // }
    private void CheckLogin() throws SQLException, IOException {

        log = login.getText();

        pswd = pass.getText();

        Connection conn = ConnectionFactory.getConnection();
        String query = "SELECT * FROM Pharmacien WHERE mail =? and mot_de_passe =?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, log);
        pst.setString(2, pswd);
        ResultSet rst = pst.executeQuery();

        if (rst.next() == true) {

            System.out.println("Connexion okay");

            Notifications.create()
                    .title("INFORMATION")
                    .text("Connexion établie")
                    .threshold(3, Notifications.create().title("Collapsed Notification"))
                    .showWarning();
            TransitionToDashboard();
        } else {

            Notifications.create()
                    .darkStyle()
                    .title("Error")
                    .text("Connexion Echoué: Verifiez vos identifiants")
                    .showWarning();
        }
    }

    private void TransitionToRegister() {

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(loginPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        // loginPane.setOpacity(0);

        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try {
                    LoadSign_UpScene();
                } catch (IOException e) {

                    e.printStackTrace();
                }

            }

            private void LoadSign_UpScene() throws IOException {
                Parent nextView = (AnchorPane) FXMLLoader.load(getClass().getResource(
                        "../../FxmlVue/sign_up.fxml"));

                // Parent nextView = registerF;
                Scene newScene = new Scene(nextView);

                Stage currentStage = (Stage) loginPane.getScene().getWindow();
                currentStage.setScene(newScene);
            }

        });

        fadeTransition.play();
    }

    private void TransitionToDashboard() {

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(connectStage);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        // App appli = new App();
        // connectPane.isDisabled();
        // connectPane.opacityProperty();

        // loginPane.setOpacity(0);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try {
                    LoadDashboardFrame();
                } catch (IOException e) {

                    e.printStackTrace();
                }

            }

            private void LoadDashboardFrame() throws IOException {
                // Stage DashboardStage = new Stage();
                Parent Dashboard = FXMLLoader.load(getClass().getResource(
                        "../../FxmlVue/DashboardFrame.fxml"));

                Scene scene = new Scene(Dashboard);
                Stage currentStage = (Stage) loginPane.getScene().getWindow();
                currentStage.setResizable(true);
                currentStage.setScene(scene);

                // DashboardStage.setTitle("PharmaGest ⚕ Votre Pharmacie ");
                // DashboardStage.setScene(scene);
                // DashboardStage.centerOnScreen();
                // DashboardStage.show();
                // ***************************************
                // Parent nextView = registerF;
                // Scene newScene = new Scene(nextView);
            }

        });

        fadeTransition.play();
    }

    @FXML
    void GoToRegister(ActionEvent event) throws IOException {
        // loginPane.setOpacity(1);
        TransitionToRegister();
        // appli.changeScene("GestionPharmacie/FxmlVue/sign_up.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
