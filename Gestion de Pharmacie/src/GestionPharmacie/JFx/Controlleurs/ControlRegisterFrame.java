package GestionPharmacie.JFx.Controlleurs;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
// import java.util.function.Function;

import org.controlsfx.control.Notifications;
// import com.jfoenix.controls.JFXButton;

import com.gluonhq.charm.glisten.control.TextField;

import GestionPharmacie.DBUtil;
import GestionPharmacie.Pharmacien;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
// import javafx.scene.control.TextField;
// import javafx.scene.control.TextField;
// import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControlRegisterFrame implements Initializable {

    // *******

    @FXML
    private AnchorPane register;

    @FXML
    private TextField name;

    @FXML
    private TextField lastName;

    @FXML
    private TextField fonction;

    @FXML
    private TextField address;

    @FXML
    private TextField phone;

    @FXML
    private TextField mail;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField cPassword;

    @FXML
    private Label msgConfirm;

    @FXML
    private Label msgErr;

    @FXML
    private Hyperlink btnConnect;
    // private JFXButton btnConnect;

    @FXML
    private MFXButton btnRegister;
    // private JFXButton btnRegister;

    // Comment récuperer la valeur d'un anchor ici en Private dans une autre class?

    String fieldsMsg = "* Please enter all fields ";
    // String errorMsg = "* Please enter all fields ";
    String confirMsg = "* Password and confirmation password don't match! ";

    @FXML
    void InsertUserData(ActionEvent event) {
        Pharmacien pharmacien = new Pharmacien();
        try {

            if (name != null || lastName != null || fonction != null || address != null || phone != null || mail != null
                    || password != null) {

                pharmacien.setNom(name.getText());
                pharmacien.setPrenom(lastName.getText());
                pharmacien.setFonction(fonction.getText());
                pharmacien.setAdresse(address.getText());
                pharmacien.setTelephone(phone.getText());
                pharmacien.setMail(mail.getText());
                pharmacien.setPassword(password.getText());
                // pharmacien.setNom(name.getText());

                DBUtil.addPharmacien(pharmacien);

                Notifications.create()
                        .title("INFORMATION")
                        .text("Utilisateur ajouté :" + name.getText() + " " + lastName.getText() + "\n User added :"
                                + name.getText() + " " + lastName.getText())
                        .threshold(3, Notifications.create().title("Collapsed Notification"))
                        .showWarning();

            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    private void GoToConnexion() {

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(register);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                loadNextScene();

            }

        });
        fadeTransition.play();

    }

    private void loadNextScene() {
        try {
            Parent secondView;
            secondView = FXMLLoader.load(getClass().getResource("../../Fxml/NewLogin.fxml"));
            // secondView = (StackPane)
            // FXMLLoader.load(getClass().getResource("../../ConnectFrame.fxml"));

            Scene newScene = new Scene(secondView);

            Stage currentStage = (Stage) register.getScene().getWindow();
            currentStage.setScene(newScene);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @FXML
    void GoToConnexionPage(ActionEvent event) {
        GoToConnexion();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // } else if (!password.getText().equals(cPassword.getText())) {

    // msgErr = new Label("Test");
    // msgErr.setFont(Font.font("Cambria", 32));
    // msgErr.setTextFill(Color.web("#0076a3"));

    //
    /**
     * @return AnchorPane return the register
     */
    public AnchorPane getRegister() {
        return register;
    }

    /**
     * @param register the register to set
     */
    public void setRegister(AnchorPane register) {
        this.register = register;
    }

}