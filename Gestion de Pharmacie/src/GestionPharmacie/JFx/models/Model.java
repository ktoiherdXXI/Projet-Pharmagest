package GestionPharmacie.JFx.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import GestionPharmacie.ConnectionFactory;
import GestionPharmacie.BDPM.Cis_cip_bdpm;
import GestionPharmacie.BDPM.Cis_grp_bdpm;
import GestionPharmacie.JFx.Controlleurs.ControllerListeGrpMedicPane;
import GestionPharmacie.JFx.models.Stock.StateCMD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * * Faut savoir que les Methodes * *
 *** {@link #getDate1()} et {@link #countDown1()} ****
 * * ne sont pas utilisé mais elles peuvent servir dans des cas spécifique ****
 */

public class

Model {

  // e.g:Le nom de la Class "ListeGrpMedicament" 👇🏾 & La Fonction👇🏾 = people
  public static final ObservableList<ListeGrpMedicament> people;
  public static final ObservableList<Device> devices;

  public static ObservableList<Cis_cip_bdpm> listMedic = FXCollections.observableArrayList();
  public static ObservableList<Cis_cip_bdpm> listAllMedic = FXCollections.observableArrayList();
  public static ObservableList< Cis_grp_bdpm> listGrpMedic = FXCollections.observableArrayList();

  public static ObservableList<Stock> listCMD = FXCollections.observableArrayList();
  public static ObservableList<Stock> listDate = FXCollections.observableArrayList();

  public static String ipsum = """
      Lorem Ipsum is simply dummy text of the printing and typesetting industry.
      Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
      It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.
      It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
      """;

  static {

    people = FXCollections.observableArrayList(
        ListeGrpMedicament.ofSplit("Turner Romero", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Harley Hays", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Jeffrey Cannon", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Simeon Huang", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Jennifer Donovan", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Hezekiah Stout", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Roberto Evans", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Braxton Watts", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Jayvon Wilkinson", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Anabelle Chang", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Abigayle Christensen", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Fletcher May", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Marisol Morris", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Grant Wilson", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Hayden Baldwin", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Markus Davidson", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Madelyn Farmer", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Deandre Crosby", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Casey Hardy", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Carmelo Velazquez", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Phillip Hays", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Damari Mcfarland", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Selina Norton", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Lukas Vaughan", " ").randomAge(),
        ListeGrpMedicament.ofSplit("Charlie Carney", " ").randomAge());

    devices = FXCollections.observableArrayList(
        new Device(Device.randomID(), "HP Gaming Laptop", "144.156.1.1", "Me", Device.State.ONLINE),
        new Device(Device.randomID(), "Own Gaming Desktop", "", "Me", Device.State.OFFLINE),
        new Device(Device.randomID(), "Pear fPhone X", "144.156.1.98", "Me", Device.State.ONLINE),
        new Device(Device.randomID(), "Alexa Echo Dot", "144.156.1.71", "Me", Device.State.ONLINE),
        new Device(Device.randomID(), "Surface Pro", "", "Wife", Device.State.OFFLINE),
        new Device(Device.randomID(), "Pear sPhone S", "144.156.1.70", "Wife", Device.State.ONLINE),
        new Device(Device.randomID(), "Chromebook", "", "Wife", Device.State.OFFLINE),
        new Device(Device.randomID(), "Wife Gaming Desktop", "144.156.1.69", "Wife", Device.State.ONLINE),
        new Device(Device.randomID(), "Chromecast", "", "", Device.State.OFFLINE),
        new Device(Device.randomID(), "Smart Dishwasher", "144.156.1.7", "", Device.State.ONLINE),
        new Device(Device.randomID(), "Samsung Smart TV", "", "", Device.State.OFFLINE),
        new Device(Device.randomID(), "Google Home Mini", "144.156.1.58", "", Device.State.ONLINE),
        new Device(Device.randomID(), "Roomba Killer 2000", "144.156.1.42", "", Device.State.ONLINE),
        new Device(Device.randomID(), "Smart WC", "144.156.1.99", "", Device.State.ONLINE),
        new Device(Device.randomID(), "Kids Gaming Desktop", "144.156.1.127", "Kids", Device.State.ONLINE),
        new Device(Device.randomID(), "Kids Gaming Laptop", "144.156.1.153", "Kids", Device.State.ONLINE),
        new Device(Device.randomID(), "XBox 360 No Scope Edition", "", "Kids", Device.State.OFFLINE),
        new Device(Device.randomID(), "PS5", "", "Kids", Device.State.OFFLINE),
        new Device(Device.randomID(), "PS6", "", "My cousin", Device.State.OFFLINE),
        new Device(Device.randomID(), "Smart Watch", "144.156.1.155", "Kids", Device.State.ONLINE),
        new Device(Device.randomID(), "Smart Watch", "144.156.1.154", "Kids", Device.State.ONLINE),
        new Device(Device.randomID(), "Kids' Smartphone", "144.156.1.221", "Kids", Device.State.ONLINE),
        new Device(Device.randomID(), "Kids Smartphone", "", "Kids", Device.State.OFFLINE),
        new Device(Device.randomID(), "Home Lights Controller", "144.156.1.91", "", Device.State.ONLINE),
        new Device(Device.randomID(), "WiFi Extender", "144.156.1.10", "", Device.State.ONLINE));

  }

  public static ObservableList<Cis_cip_bdpm> getMedoc() throws SQLException {
    Connection conn = ConnectionFactory.getConnection();
    // ControllerListeGrpMedicPane controllerListeGrpMedicPane = new
    // ControllerListeGrpMedicPane();

    try {
      // ObservableList<Cis_cip_bdpm> listMedic = FXCollections.observableArrayList();

      // afficher la liste (listMedic) des items en console
      // for (Cis_cip_bdpm cis_cip_bdpm : listMedic) {
      // System.out.println(valeur_cis_cip_bdpm_id);
      // }

      // String query1 = "SELECT * From cis_cip_bdpm WHERE id_cis_grp = ?";
      listMedic.clear();
      String query = "SELECT * FROM cis_cip_bdpm WHERE id = 1";
      PreparedStatement ps = conn.prepareStatement(query);
      // ps.setInt(1, valeur_cis_cip_bdpm_id);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        listMedic.addAll(new Cis_cip_bdpm(
            rs.getInt("id"),
            rs.getString("libele")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return listMedic;
  }

  public static ObservableList<Cis_grp_bdpm> getGrpMedoc() throws SQLException {
    Connection conn = ConnectionFactory.getConnection();

    try {
      listGrpMedic.clear();
      String query = "SELECT * FROM cis_grp_bdpm";
      PreparedStatement ps = conn.prepareStatement(query);

      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        listGrpMedic.add(new Cis_grp_bdpm(
            rs.getInt("id_cis"),
            rs.getInt("id_cis_grp"),
            rs.getString("voie_administration"),
            rs.getString("libele_grp_gen")));
      }

      // Afiche la liste en console
      for (Cis_grp_bdpm cis_grp_bdpm : listGrpMedic) {
        System.out.println(cis_grp_bdpm.getId_cis());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return listGrpMedic;
  }

  public static ObservableList<Cis_cip_bdpm> getAllMedoc() throws SQLException {
    Connection conn = ConnectionFactory.getConnection();

    try {
      listAllMedic.clear();
      String query = "SELECT * FROM cis_cip_bdpm";
      PreparedStatement ps = conn.prepareStatement(query);

      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        listAllMedic.addAll(new Cis_cip_bdpm(
            rs.getInt("id"),
            rs.getString("libele")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return listAllMedic;
    // afficher la liste des items en console

  }

  // Integer seuil = 20;

  public static ObservableList<Stock> getMedocInSeuil() throws SQLException {
    Connection conn = ConnectionFactory.getConnection();

    try {

      listCMD.clear();

      String query = "SELECT idmedicament, nom, quantite, quant_min, quant_max, prix, fournisseur, qty_a_cmd, montant_cmd, etat_cmd, countdouwn_date FROM stock WHERE quantite < quant_min ";

      PreparedStatement ps = conn.prepareStatement(query);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Timestamp countdownDate = rs.getTimestamp("countdouwn_date");
        Timestamp ldt = null;
        if (countdownDate != null) {
          ldt = countdownDate;
        }

        String etat_cmd = rs.getString("etat_cmd");
        StateCMD state = null;
        for (StateCMD s : StateCMD.values()) {
          if (s.toString().equals(etat_cmd)) {
            state = s;
            break;
          }
        }

        if (state == null) {
          state = StateCMD.En_Attente;

        }

        listCMD.add(new Stock(
            rs.getInt("idmedicament"),
            rs.getString("nom"),
            rs.getInt("quantite"),
            rs.getInt("quant_min"),
            rs.getInt("quant_max"),
            rs.getDouble("prix"),
            rs.getString("fournisseur"),
            rs.getInt("qty_a_cmd"),
            rs.getDouble("montant_cmd"),
            state,
            ldt));
      }
      saveDate();
    } finally {
      conn.close();
    }

    return listCMD;
  }

  // methode qui lance un compte à rebours en temps reel et affiche la decompte du
  // compte à rebours jusqu'a la fin et fait une action

  // méthode qui recupere date et heure actuel et la sauvegarde dans la base
  // en format timestamp
  public static void saveDate() throws SQLException {
    Connection conn = ConnectionFactory.getConnection();

    // si countdownDate contient deja une date àlors recuperer ne rein faire
    // sinon lui attribuer la date du jour

    try {
      String query = "UPDATE stock SET countdouwn_date = COALESCE(countdouwn_date, CURRENT_TIMESTAMP) WHERE quantite < quant_min";
      // String query = "UPDATE stock SET countdouwn_date = IFNULL(countdouwn_date,
      // CURRENT_TIMESTAMP) WHERE quantite < quant_min";
      // Cela signifie que si la valeur de countdown_date est nulle, la requête mettra
      // à jour la colonne avec la date actuelle, sinon elle ne fera rien.

      PreparedStatement ps = conn.prepareStatement(query);

      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  // méthode qui recupere la timestamp dans la base pour chaque medicament
  // en dessous du seuil
  public static ObservableList<Stock> getDate1() throws SQLException {
    Connection conn = ConnectionFactory.getConnection();

    try {
      listDate.clear();
      String query = "SELECT countdouwn_date FROM stock WHERE quantite < quant_min";

      PreparedStatement ps = conn.prepareStatement(query);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Timestamp countdownDate = rs.getTimestamp("countdouwn_date");
        Timestamp ldt = null;
        if (countdownDate != null) {
          ldt = countdownDate;
        }

        listDate.add(new Stock(
            rs.getInt("idmedicament"),
            rs.getString("nom"),
            rs.getInt("quantite"),
            rs.getInt("quant_min"),
            rs.getInt("quant_max"),
            rs.getDouble("prix"),
            rs.getString("fournisseur"),
            rs.getInt("qty_a_cmd"),
            rs.getDouble("montant_cmd"),
            StateCMD.En_Attente,
            ldt));

      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return listDate;

  }

  // methode qui creer et lance un countdouwn avec les paramettre du temps et
  // debute à partir de la timestamp recupere dans la base par medicament,
  // afficher le countdounw en temps reel en format jours,heures,minuites,seconde
  public static void countDown1() throws SQLException {
    Connection conn = ConnectionFactory.getConnection();

    try {
      String query = "SELECT countdouwn_date FROM stock WHERE quantite < quant_min";

      PreparedStatement ps = conn.prepareStatement(query);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Timestamp countdownDate = rs.getTimestamp("countdouwn_date");
        Timestamp ldt = null;
        if (countdownDate != null) {
          ldt = countdownDate;
        }

        listDate.add(new Stock(
            rs.getInt("idmedicament"),
            rs.getString("nom"),
            rs.getInt("quantite"),
            rs.getInt("quant_min"),
            rs.getInt("quant_max"),
            rs.getDouble("prix"),
            rs.getString("fournisseur"),
            rs.getInt("qty_a_cmd"),
            rs.getDouble("montant_cmd"),
            StateCMD.En_Attente,
            ldt));

      }
      Timestamp countdownDate = rs.getTimestamp("countdouwn_date");
      // recupere la date actuel
      LocalDateTime now = LocalDateTime.now();
      // recupere la date de la timestamp

      LocalDateTime ldt = LocalDateTime.ofInstant(countdownDate.toInstant(), ZoneId.systemDefault());
      // calcule la difference entre les deux dates
      Duration duration = Duration.between(now, ldt);
      // affiche le resultat en format jours,heures,minuites,seconde
      System.out.println(duration.toDays() + " jours " + duration.toHours() + " heures " + duration.toMinutes()
          + " minuites " + duration.getSeconds() + " secondes ");

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

  }

  /**
   * une méthode pour générer l'ID de commande aléatoire**
   * {@link #generateIDCommande()}, puis une autre méthode
   * {@link #saveOrder(Stock stock)}pour insérer les données de la liste de
   * médicaments en
   * seuil (listCMD) dans
   * la table "commande".
   */

  // private static String generateIDCommande() {
  // int random = (int) (Math.random() * (99999 - 10000) + 10000);
  // return "CMD" + random;
  // }
  public static String generateIDCommande() {
    long timestamp = System.currentTimeMillis();
    int random = (int) (Math.random() * (999 - 100) + 100);
    return timestamp + "CMD" + random;
  }

  public static void saveOrder(Stock stock) throws SQLException {
    Connection conn = ConnectionFactory.getConnection();
    String id_cmd = generateIDCommande();

    try {
      // Vérifier si le médicament est déjà présent dans la table "commande"
      String checkQuery = "SELECT * FROM commande WHERE idmedicament = ?";
      PreparedStatement checkPs = conn.prepareStatement(checkQuery);
      checkPs.setInt(1, stock.getCol_id());
      ResultSet resultSet = checkPs.executeQuery();

      if (resultSet.next()) {
        // Le médicament est déjà présent dans la table, ne rien faire et le programme
        // peut retourner simplement
        return;
      } else {
        // Le médicament n'est pas présent dans la table, insérer les données
        String query = "INSERT INTO commande (id_cmd, idmedicament, nom, quantite, quant_min, quant_max, prix, fournisseur, qty_a_cmd, montant_cmd, etat_cmd, date_cmd) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id_cmd);
        ps.setInt(2, stock.getCol_id());
        ps.setString(3, stock.getCol_nom());
        ps.setInt(4, stock.getCol_quant());
        ps.setInt(5, stock.getCol_quant_min());
        ps.setInt(6, stock.getCol_quant_max());
        ps.setDouble(7, stock.getCol_prix());
        ps.setString(8, stock.getCol_four());
        ps.setInt(9, stock.get_col_quant_a_cmdProperty());
        ps.setDouble(10, stock.get_col_mont_cmdProperty());

        ps.setObject(11, StateCMD.En_Cours.name(), Types.OTHER);

        ps.setTimestamp(12, new Timestamp(System.currentTimeMillis()));

        ps.executeUpdate();

        ps.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * {@link #insertIntoOrder()}une méthode pour récupérer les médicaments en
   * seuil (quantité < quantité minimale) de la table "stock" et les insérer dans
   * une liste de médicaments en seuil (listCMD).
   */
  public static void insertIntoOrder() throws SQLException {
    getMedocInSeuil();

    for (Stock stock : listCMD) {
      if (stock.getCol_quant() < stock.getCol_quant_min()) {
        saveOrder(stock);
      }
    }
  }

}
// autres opérations pour PS...11 ...

// ps.setString(11, StateCMD.En_Cours.name());
// ps.setObject(11, "etat(" + StateCMD.En_Cours.name() + ")");
// ps.setObject(11, StateCMD.En_Cours);
// ps.setString(11, "cast('" + StateCMD.En_Cours.name() + "' as etat)");
// ps.setString(11, StateCMD.En_Cours.name() + "::etat");
// ps.setString(11, StateCMD.En_Cours.name());
// ps.setInt(11, StateCMD.En_Cours.ordinal());
// ps.setString(11, "En_Cours");

// ps.setString(11, StateCMD.toString(StateCMD.En_Cours));
// ps.setObject(11, StateCMD.valueOf("En_Cours"));
// ps.setString(11, StateCMD.En_Cours.toString());

// ps.setInt(11, stock.getStateCMD().etatCommande);
// Stock.StateCMD.valueOf(Stock.StateCMD.class,
// String.valueOf(stock.getStateCMD().etatCommande));
// ps.setString(11, Enum.valueOf(StateCMD.class,
// stock.getStateCMD().etatCommande).toString());
// ps.setString(11, Integer.toString(stock.getStateCMD().etatCommande));
// ps.setInt(11, StateCMD.En_Cours.etatCommande);
// ps.setInt(11, stock.getStateCMD().etatCommande);
// ps.setString(11, stock.getStateCMD().name());
// ps.setObject(11, StateCMD.valueOf("En_Cours"));
// ps.setString(11, Enum.valueOf(StateCMD.class, "En_Cours").name());
// ps.setString(11, StateCMD.En_Cours.name());