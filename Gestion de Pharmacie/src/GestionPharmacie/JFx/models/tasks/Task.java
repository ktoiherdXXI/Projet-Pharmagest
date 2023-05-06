package GestionPharmacie.JFx.models.tasks;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Logger;

public class Task {

    private Integer taskId;
    private String taskName;
    private LocalDateTime taskDateTime;
    private int frequency;
    private LocalDateTime dueTime;
    private StateTask stateTask;

    public Task(Integer taskId, String taskName, LocalDateTime taskDateTime, int frequency, StateTask stateTask) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDateTime = taskDateTime;
        this.frequency = frequency;
        this.stateTask = StateTask.En_Attente;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDateTime getTaskDateTime() {
        return taskDateTime;
    }

    public void setTaskDateTime(LocalDateTime taskDateTime) {
        this.taskDateTime = taskDateTime;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public LocalDateTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(LocalDateTime dueTime) {
        this.dueTime = dueTime;
    }

    public StateTask getStateTask() {
        return stateTask;
    }

    public void setStateTask(StateTask stateTask) {
        this.stateTask = stateTask;
    }

    public enum StateTask {
        Executer("Tâche: Executer"),
        Annuler("Tâche: Annuler"),
        En_Attente("Tâche: En_Attente"),
        Archiver("Tâche: Archiver");

        private String stateTask;

        private StateTask(String stateTask) {
            this.stateTask = stateTask;
        }

        @Override
        public String toString() {
            return stateTask;
        }

        public static StateTask fromString(String state) {
            state = state.replace("Tâche: ", "");
            for (StateTask s : values()) {
                if (s.toString().equals(state)) {
                    return s;
                }
            }
            throw new IllegalArgumentException("Invalid state: " + state);
        }
    }
}