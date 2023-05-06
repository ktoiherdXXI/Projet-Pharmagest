package GestionPharmacie.JFx.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import GestionPharmacie.ConnectionFactory;

/*  ******** EventScheduler pour gérer la programmation des événements ******** 
 * Dans ce code, la méthode start est appelée pour démarrer le thread d'arrière-plan.
 * La méthode checkEventsAndExecute est appelée périodiquement toutes les CHECK_INTERVAL_MILLIS millisecondes pour vérifier si un événement est dû et,
 * si c'est le cas, insérer un nouvel ordre et mettre à jour l'état de l'événement en "exécuté".
*/

public class EventScheduler {
    private static final int CHECK_INTERVAL_MILLIS = 60000; // 1 minute

    public static void start() {
        Thread backgroundThread = new Thread(new BackgroundEvent());
        backgroundThread.start();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    checkEventsAndExecute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, 0, CHECK_INTERVAL_MILLIS);
    }

    static void checkEventsAndExecute() throws SQLException {
        List<Event> events = getEvents();
        for (Event event : events) {
            if (event.getState().equals("En Attente") && isEventDue(event)) {
                Model.insertIntoOrder();
                updateEventState(event.getId(), "Exécuté");
            }
        }
    }

    /**
     ** Cette fonction vérifie si un événement est dû.
     ** Elle compare la date et l'heure courante avec la date et l'heure de
     * l'événement.
     ** Si la date et l'heure courante sont égales ou supérieures à la date et
     * l'heure de l'événement,
     ** elle renvoie "vrai". Sinon, elle renvoie "faux".
     * 
     * Cette fonction est appelée de manière répétée grâce à l'utilisation d'un
     * Timer dans la méthode start(). Le Timer a un intervalle de temps défini pour
     * l'exécution répétée, qui est défini par la constante CHECK_INTERVAL_MILLIS.
     * 
     * @param event
     * @return
     */
    private static boolean isEventDue(Event event) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        return currentTimestamp.compareTo(event.getDateTime()) >= 0;
        // Compare current date and time with event date and time
        // Return true if the current date and time are equal or greater than event date
        // and time Return false otherwise
    }

    private static List<Event> getEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM events");

            while (rs.next()) {
                String id = rs.getString("id");
                Timestamp dateTime = rs.getTimestamp("date_time");
                String state = rs.getString("state");

                Event event = new Event(id, dateTime, state);
                events.add(event);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return events;
    }

    private static void updateEventState(String string, String state) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            pstmt = conn.prepareStatement("UPDATE events SET state = ? WHERE id = ?");
            pstmt.setString(1, state);
            pstmt.setString(2, string);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
