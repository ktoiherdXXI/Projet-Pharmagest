package GestionPharmacie.JFx.models;

import java.sql.SQLException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TimeRemaining {
    private int days = 0;
    private int hours = 0;
    private int minutes = 1;
    private int seconds = 0;
    private Label timeR = new Label();
    private Timeline timeline;

    public void startCountdown(Label label, int days, int hours, int minutes, int seconds)
            throws SQLException {

        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;

        timeR = label;

        // Enregistrer la date et l'heure actuelles dans la base de données pour ce
        // produit

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            try {
                updateTime();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            timeR
                    .setText(String.format("%d days %d hours %d minutes %d seconds", this.days, this.hours,
                            this.minutes, this.seconds));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // timeline.setOnFinished(e -> {
        // // Code pour insérer des données dans la table commande
        // timeR.setText("Commande envoyée");

        // });
    }
    // System.out.println("Données envoyées vers la table commande");
    // });
    // }

    private void updateTime() throws SQLException {
        if (seconds > 0) {
            seconds--;
        } else if (minutes > 0) {
            minutes--;
            seconds = 59;
        } else if (hours > 0) {
            hours--;
            minutes = 59;
            seconds = 59;
        } else if (days > 0) {
            days--;
            hours = 23;
            minutes = 59;
            seconds = 59;
        } else {
            timeline.stop();
            Model model = new Model();
            try {
                model.insertIntoOrder();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
            timeR.setText("Commande envoyée");

        }
    }
}
