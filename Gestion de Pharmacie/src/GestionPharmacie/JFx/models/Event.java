package GestionPharmacie.JFx.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.scene.control.Alert;

public class Event {
    private String id;
    private Timestamp dateTime;
    private String state;

    private String eventName;
    private LocalDateTime eventDateTime;

    public Event(String id2, Timestamp dateTime, String state) {
        this.id = id2;
        this.dateTime = dateTime;
        this.state = state;
    }

    public Event() {
    }

    // Getters et /* setters */ pour les propriétés ci-dessus

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getId() {
        return id;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public String getState() {
        return state;
    }

    public boolean verifyEventDateTime(LocalDateTime eventDateTime) {
        if (eventDateTime == null || eventDateTime.isBefore(LocalDateTime.now())) {
            // Afficher un message d'erreur pour indiquer que la date et l'heure de
            // l'événement
            // sont passées
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Date et heure de l'événement passées");
            alert.setContentText("Veuillez entrer une date et une heure futures pour l'événement.");
            alert.showAndWait();
            return false;
        }
        return true;
        // Si la date et l'heure de l'événement sont futures, enregistrer les données
        // dans
        // la base de données
        // ...
    }

    public boolean verifyTimeFields(String hoursStr, String minutesStr, String secondsStr) {

        int hours, minutes, seconds;
        try {
            hours = Integer.parseInt(hoursStr);
            minutes = Integer.parseInt(minutesStr);
            seconds = Integer.parseInt(secondsStr);
        } catch (NumberFormatException ex) {
            // Afficher une alerte pour signaler que les valeurs des champs d'heure, de
            // minute ou de seconde
            // sont incorrectes
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Valeurs des champs d'heure, de minute ou de seconde incorrectes");
            alert.setContentText(
                    "Veuillez entrer des valeurs numériques correctes pour les champs d'heure, de minute et de seconde.");
            alert.showAndWait();
            return false;
        }

        if (hours < 0 || hours > 23) {
            // Afficher une alerte pour signaler que l'heure est incorrecte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Heure incorrecte");
            alert.setContentText("Veuillez entrer une heure correcte (entre 0 et 23).");
            alert.showAndWait();
            return false;
        }

        if (minutes < 0 || minutes > 59) {
            // Afficher une alerte pour signaler que les minutes sont incorrectes
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Minutes incorrectes");
            alert.setContentText("Veuillez entrer des minutes correctes (entre 0 et 59).");
            alert.showAndWait();
            return false;
        }

        if (seconds < 0 || seconds > 59) {
            // Afficher une alerte pour signaler que les secondes sont incorrectes
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Secondes incorrectes");
            alert.setContentText("Veuillez entrer des secondes correctes (entre 0 et 59).");
            alert.showAndWait();
            return false;
        }

        return true;
    }

    public boolean verifyEventName(String eventName) {
        if (eventName.isEmpty() || eventName == null || eventName.trim().length() == 0) {
            // Afficher un message d'erreur pour indiquer que le nom de l'événement est vide
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Nom de l'événement vide");
            alert.setContentText("Veuillez entrer un nom pour l'événement.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    // Si le nom de l'événement n'est pas vide, enregistrer les données dans la base
    // de données
    // ...
}
