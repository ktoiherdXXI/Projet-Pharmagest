package GestionPharmacie.JFx.models.tasks;

import java.sql.SQLException;

import GestionPharmacie.JFx.models.Model;

public class TaskExecutor implements Runnable {
    private Task taskData;

    public TaskExecutor(Task taskData) {
        this.taskData = taskData;
    }

    @Override
    public void run() {
        // logique de la tâche à exécuter
        try {
            Model.insertIntoOrder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
