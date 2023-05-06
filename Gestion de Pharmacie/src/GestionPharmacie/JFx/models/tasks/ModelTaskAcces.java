package GestionPharmacie.JFx.models.tasks;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import GestionPharmacie.ConnectionFactory;
import GestionPharmacie.JFx.Controlleurs.ControllerCommandePane;
import GestionPharmacie.JFx.models.tasks.Task.StateTask;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ModelTaskAcces {
    List<Task> tasks = new ArrayList<>();

    ObservableList<Task> data = FXCollections.observableArrayList();

    private int taskId;

    public List<Task> getAllTasks() throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String selectSQL = "SELECT * FROM task";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                Integer taskId = resultSet.getInt("task_id");
                String taskName = resultSet.getString("task_name");
                Timestamp taskDateTime = resultSet.getTimestamp("task_date");
                int frequency = resultSet.getInt("task_frequency");
                String stateTask = resultSet.getString("task_etat");

                tasks.add(new Task(taskId, taskName, taskDateTime.toLocalDateTime(), frequency,
                        StateTask.valueOf(stateTask)));
            }
        } catch (SQLException ex) {
            throw new SQLException("Erreur lors de la récupération des tâches : " + ex.getMessage());
        }
        return tasks;
    }

    /*
     * La méthode refreshTable() vide les listes tasks et data, puis appelle
     * getAllTasks() pour remplir à nouveau la liste tasks. Enfin, il ajoute les
     * tâches de la liste tasks à la liste data et renvoie data. Cette méthode peut
     * être utilisée pour rafraîchir la vue affichant les tâches.
     */
    public ObservableList<Task> refreshTable() throws SQLException {
        tasks.clear();
        data.clear();
        tasks = getAllTasks();
        data.addAll(tasks);
        return data;
    }

    public void saveTask(String taskName, LocalDateTime taskDateTime, int frequency)
            throws SQLException, IllegalArgumentException {
        if (taskName == null || taskName.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la tâche ne peut pas être vide.");
        }
        if (taskDateTime == null) {
            throw new IllegalArgumentException("La date et l'heure de la tâche sont incorrectes.");
        }

        if (frequency != 0 && frequency != 1 && frequency != 7 && frequency != 30) {
            throw new IllegalArgumentException(
                    "La fréquence de la tâche est incorrecte. La valeur doit être soit 0, 1, 7 ou 30.");
        }
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO task (task_name, task_date, task_frequency, task_etat) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, taskName);
            statement.setTimestamp(2, Timestamp.valueOf(taskDateTime));
            statement.setInt(3, frequency);
            statement.setObject(4, StateTask.En_Attente.name(), Types.OTHER);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                taskId = rs.getInt(1);
            }
            refreshTable();
            Task taskToExecute = new Task(taskId, taskName, taskDateTime, frequency, StateTask.En_Attente);
            TaskScheduler.scheduleTask(taskToExecute);
        } catch (SQLException ex) {
            throw new SQLException("Error saving task: " + ex.getMessage());
        }
    }

    public void updateTask(Task task, String taskName, LocalDateTime taskDateTime, int frequency)
            throws SQLException, IllegalArgumentException {
        if (taskName == null || taskName.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la tâche ne peut pas être vide.");
        }
        if (taskDateTime == null) {
            throw new IllegalArgumentException("La date et l'heure de la tâche sont incorrectes.");
        }

        if (frequency != 0 && frequency != 1 && frequency != 7 && frequency != 30) {
            throw new IllegalArgumentException(
                    "La fréquence de la tâche est incorrecte. La valeur doit être soit 0, 1, 7 ou 30.");
        }
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE task SET task_name = ?, task_date = ?, task_frequency = ? WHERE task_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, taskName);
            statement.setTimestamp(2, Timestamp.valueOf(taskDateTime));
            statement.setInt(3, frequency);
            statement.setInt(4, task.getTaskId());
            statement.executeUpdate();
            refreshTable();
            TaskScheduler.cancelTask(task);
            Task taskToExecute = new Task(task.getTaskId(), taskName, taskDateTime, frequency, StateTask.En_Attente);
            TaskScheduler.scheduleTask(taskToExecute);
        } catch (SQLException ex) {
            throw new SQLException("Error updating task: " + ex.getMessage());
        }
    }

    public void deleteTask(Task task) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM task WHERE task_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getTaskId());
            statement.executeUpdate();
            System.out.println(statement.executeUpdate() + " rows updated");
            refreshTable();
        } catch (SQLException ex) {
            throw new SQLException("Error deleting task: " + ex.getMessage());
        }
    }

    private void showEditTaskDialog(Task task) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit Task");
        stage.setResizable(false);

        Label nameLabel = new Label("Task Name:");
        TextField nameTextField = new TextField(task.getTaskName());
        Label dateLabel = new Label("Task Date:");
        DatePicker datePicker = new DatePicker(task.getTaskDateTime().toLocalDate());
        Label timeLabel = new Label("Task Time:");
        Spinner<Integer> hourSpinner = new Spinner<>(0, 23, task.getTaskDateTime().getHour());
        Spinner<Integer> minuteSpinner = new Spinner<>(0, 59, task.getTaskDateTime().getMinute());
        Label frequencyLabel = new Label("Task Frequency:");
        ComboBox<Integer> frequencyComboBox = new ComboBox<>(FXCollections.observableArrayList(0, 1, 7, 30));
        frequencyComboBox.setValue(task.getFrequency());

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            String name = nameTextField.getText();
            LocalDate date = datePicker.getValue();
            int hour = hourSpinner.getValue();
            int minute = minuteSpinner.getValue();
            LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(hour, minute));
            int frequency = frequencyComboBox.getValue();
            try {
                updateTask(task, name, dateTime, frequency);
                stage.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.addRow(0, nameLabel, nameTextField);
        gridPane.addRow(1, dateLabel, datePicker);
        gridPane.addRow(2, timeLabel, hourSpinner, new Label(":"), minuteSpinner);
        gridPane.addRow(3, frequencyLabel, frequencyComboBox);
        gridPane.addRow(4, saveButton);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.showAndWait();
    }

    // Créer une fonction qui prends en paramettre un tableau pour afficher les data
    // stocker dans la table task
    public void displayTasks(TableView<Task> tableView) throws SQLException {
        // data.addAll(getAllTasks());
        tableView.setItems(refreshTable());
        TableColumn<Task, Integer> taskIdColumn = new TableColumn<>("ID");
        taskIdColumn.setCellValueFactory(new PropertyValueFactory<>("taskId"));

        TableColumn<Task, String> taskNameColumn = new TableColumn<>("Nom");
        taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));

        TableColumn<Task, LocalDateTime> taskDateTimeColumn = new TableColumn<>("Date et heure");
        taskDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("taskDateTime"));

        // TableColumn<Task, Integer> frequencyColumn = new TableColumn<>("Fréquence");
        TableColumn<Task, String> frequencyColumn = new TableColumn<>("Fréquence");
        frequencyColumn.setCellValueFactory(new PropertyValueFactory<>("frequency"));
        // Sur frequencyColumn: affiché "Quotidien" si valeur à affiché = 1,
        // "Hebdomadaire" si valleur = 7, Mensuelle si valeur = 30
        frequencyColumn.setCellValueFactory(cellData -> {
            Integer frequency = cellData.getValue().getFrequency();
            String frequencyString = "";
            switch (frequency) {
                case 0:
                    frequencyString = "Date et heure precisé  ";
                    break;
                case 1:
                    frequencyString = "Quotidien";
                    break;
                case 7:
                    frequencyString = "Hebdomadaire";
                    break;
                case 30:
                    frequencyString = "Mensuelle";
                    break;
                default:
                    frequencyString = "Inconnue";
                    break;
            }
            return new SimpleStringProperty(frequencyString);
        });

        TableColumn<Task, Void> actionColumn = new TableColumn<>("Actions");

        Callback<TableColumn<Task, Void>, TableCell<Task, Void>> cellFactory = new Callback<TableColumn<Task, Void>, TableCell<Task, Void>>() {
            @Override
            public TableCell<Task, Void> call(final TableColumn<Task, Void> param) {
                final TableCell<Task, Void> cell = new TableCell<Task, Void>() {
                    ControllerCommandePane controllerCommandePane = new ControllerCommandePane();
                    private final Button editButton = new Button();
                    private final Button deleteButton = new Button();
                    {
                        editButton
                                .setGraphic(controllerCommandePane.getEditIcon());
                        editButton.setOnAction(event -> {
                            Task selectedTask = tableView.getSelectionModel().getSelectedItem();
                            if (selectedTask != null) {
                                showEditTaskDialog(selectedTask);
                            }
                        });

                        deleteButton.setGraphic(controllerCommandePane.getDeleteIcon());
                        deleteButton.setOnAction((ActionEvent event) -> {
                            Task task = getTableView().getItems().get(getIndex());
                            try {
                                deleteTask(task);
                                System.out.println("Task deleted successfully!");
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Suppression réussie");
                                alert.setHeaderText("La tâche a été supprimée avec succès.");
                                alert.showAndWait();
                            } catch (SQLException ex) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Erreur");
                                alert.setHeaderText("Une erreur est survenue lors de la suppression de la tâche");
                                alert.setContentText(ex.getMessage());
                                alert.showAndWait();
                            } finally {
                                try {
                                    refreshTable();
                                    data.remove(task);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox hBox = new HBox(editButton, deleteButton);
                            hBox.setAlignment(Pos.CENTER);
                            hBox.setSpacing(10);
                            setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        };

        actionColumn.setCellFactory(cellFactory);
        tableView.getColumns().addAll(/* taskIdColumn, */ taskNameColumn, taskDateTimeColumn, frequencyColumn,
                actionColumn);

        tableView.setItems(data);

    }
}