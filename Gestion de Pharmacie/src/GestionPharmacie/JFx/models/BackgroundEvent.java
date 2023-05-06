package GestionPharmacie.JFx.models;

import java.sql.SQLException;

public class BackgroundEvent implements Runnable {
    @Override
    public void run() {
        try {
            EventScheduler.checkEventsAndExecute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}