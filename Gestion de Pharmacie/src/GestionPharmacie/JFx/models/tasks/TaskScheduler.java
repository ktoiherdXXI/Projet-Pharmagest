package GestionPharmacie.JFx.models.tasks;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import GestionPharmacie.JFx.models.tasks.Task.StateTask;

public class TaskScheduler {
    private static final int POOL_SIZE = 10;
    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(POOL_SIZE);
    private static HashMap<Task, ScheduledFuture<?>> taskMap = new HashMap<>();

    public static void scheduleTask(Task task) {

        if (task.getStateTask() != StateTask.Executer) {
            return;
        }

        if (task.getStateTask() == Task.StateTask.Annuler || task.getStateTask() == Task.StateTask.En_Attente) {
            return;
        }

        LocalDateTime taskDateTime = task.getTaskDateTime();
        LocalDateTime now = LocalDateTime.now();
        long delay = ChronoUnit.MILLIS.between(now, taskDateTime);

        if (task.getFrequency() == 0) {
            executorService.schedule(new TaskExecutor(task), delay, TimeUnit.MILLISECONDS);
        } else {
            long period = 0;
            switch (task.getFrequency()) {
                case 1:
                    period = 24 * 60 * 60 * 1000; // 24 heures en millisecondes
                    break;
                case 7:
                    period = 7 * 24 * 60 * 60 * 1000; // une semaine en millisecondes
                    break;
                case 30:
                    period = 30 * 24 * 60 * 60 * 1000; // un mois en millisecondes
                    break;
                default:
                    throw new IllegalArgumentException("Invalid frequency value: " + task.getFrequency());
            }

            ScheduledFuture<?> future = executorService.scheduleAtFixedRate(new TaskExecutor(task), delay, period,
                    TimeUnit.MILLISECONDS);
            taskMap.put(task, future);
        }
    }

    public static void cancelTask(Task task) {
        ScheduledFuture<?> future = taskMap.get(task);

        // if (task.getStateTask() != StateTask.Executer) {
        // return;
        // }

        if (future != null) {
            future.cancel(false);
            taskMap.remove(task);
        }
    }
}
