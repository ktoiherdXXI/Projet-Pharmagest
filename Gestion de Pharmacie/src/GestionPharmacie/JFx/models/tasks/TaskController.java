package GestionPharmacie.JFx.models.tasks;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TaskController {
    ModelTaskAcces modelTaskAcces = new ModelTaskAcces();

    public TaskController() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    checkAndExecuteTasks();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 60 * 1000);
    }

    public void checkAndExecuteTasks() throws SQLException {
        List<Task> taskList = modelTaskAcces.getAllTasks();
        for (Task task : taskList) {
            if (task.getDueTime().isBefore(LocalDateTime.now())) {
                TaskExecutor taskExecutor = new TaskExecutor(task);
                new Thread(taskExecutor).start();
            }
        }
    }
}
