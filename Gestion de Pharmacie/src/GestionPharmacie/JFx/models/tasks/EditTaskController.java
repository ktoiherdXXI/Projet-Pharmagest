package GestionPharmacie.JFx.models.tasks;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditTaskController implements Initializable {
    @FXML
    private TextField taskNameTextField;

    @FXML
    private TextField taskDateTimeTextField;

    @FXML
    private TextField frequencyTextField;

    private MFXComboBox<String> taskFrequencyComboBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private Task task;

    public void setTask(Task task) {
        this.task = task;
        taskNameTextField.setText(task.getTaskName());
        taskDateTimeTextField.setText(task.getTaskDateTime().toString());
        frequencyTextField.setText(String.valueOf(task.getFrequency()));
    }

    public Task getTask() {
        return task;
    }

    public void initData(Task task) {
        // Initialiser les champs de la fenêtre d'édition à partir de la tâche
        // sélectionnée
        this.task = task;
        taskNameTextField.setText(task.getTaskName());
        taskDateTimeTextField.setText(task.getTaskDateTime().toString());
        frequencyTextField.setText(String.valueOf(task.getFrequency()));
    }

    @FXML
    private void initialize() {
        saveButton.setOnAction(event -> {
            task.setTaskName(taskNameTextField.getText());
            task.setTaskDateTime(LocalDateTime.parse(taskDateTimeTextField.getText()));
            task.setFrequency(Integer.valueOf(frequencyTextField.getText()));
            closeWindow();
        });

        cancelButton.setOnAction(event -> {
            closeWindow();
        });
    }

    private void closeWindow() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Ajouter des éléments au ComboBox
        taskFrequencyComboBox.getItems().addAll("Date et heure precisé", "Tous les jours", "Toutes les semaines",
                "Tous les mois");

        // Ajouter un gestionnaire d'événements pour détecter la sélection d'un élément
        // dans le ComboBox
        taskFrequencyComboBox.setOnAction(event -> {
            String selectedItem = taskFrequencyComboBox.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // Afficher l'élément sélectionné dans une boîte de dialogue d'alerte
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Fréquence sélectionnée");
                alert.setContentText(selectedItem);
                alert.showAndWait();
            }
        });
    }

}
