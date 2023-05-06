package GestionPharmacie.JFx.models.client;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import GestionPharmacie.ConnectionFactory;

public class ModelClientPane {

    // Requêtes SQL
    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM clients ORDER BY nom, prenom";
    private static final String SELECT_CLIENT_BY_ID = "SELECT * FROM clients WHERE id=?";
    private static final String INSERT_CLIENT = "INSERT INTO clients(nom, prenom, date_naissance, adresse, telephone, email, ssn, assurance, allergies, conditions, medicaments, antecedents, derniere_visite) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CLIENT = "UPDATE clients SET nom=?, prenom=?, date_naissance=?, adresse=?, telephone=?, email=?, ssn=?, assurance=?, allergies=?, conditions=?, medicaments=?, antecedents=? WHERE id=?";
    private static final String DELETE_CLIENT = "DELETE FROM clients WHERE id=?";

    /**
     * Mappe une ligne de la table clients vers un objet Client
     *
     * @param rs le ResultSet contenant la ligne à mapper
     * @return l'objet Client correspondant à la ligne
     * @throws SQLException
     * 
     */

    private Client mapRowToClient(ResultSet rs) throws SQLException {

        int idClient = rs.getInt("id");
        String nom = rs.getString("nom");
        String prenom = rs.getString("prenom");
        String dateNaissance = rs.getString("date_naissance");
        String adresse = rs.getString("adresse");
        String telephone = rs.getString("telephone");
        String email = rs.getString("email");
        String ssn = rs.getString("ssn");
        String assurance = rs.getString("assurance");
        String allergies = rs.getString("allergies");
        String conditions = rs.getString("conditions");
        String medicaments = rs.getString("medicaments");
        String antecedents = rs.getString("antecedents");
        LocalDate derniereVisite = rs.getDate("derniere_visite").toLocalDate();

        return new Client(
                idClient, nom, prenom, dateNaissance, adresse, telephone, email, ssn, assurance, allergies,
                conditions, medicaments, antecedents, derniereVisite);
    }

    /**
     * Obtient la liste de tous les clients dans la base de données
     * 
     * @return la liste de tous les clients
     */
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_ALL_CLIENTS)) {
            while (rs.next()) {
                Client client = mapRowToClient(rs);
                clients.add(client);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clients;
    }

    /**
     * Obtient un client dans la base de données par son identifiant
     * 
     * @param id l'identifiant du client
     * @return le client correspondant à l'identifiant, null si le client n'existe
     *         pas
     */
    public Client getClientById(int id) {
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_CLIENT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToClient(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Ajoute un nouveau client dans la base de données
     * 
     * @param client le client à ajouter
     */
    public void addClient(Client client) {
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(INSERT_CLIENT)) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setDate(3, Date.valueOf(client.getDateNaissance()));
            ps.setString(4, client.getAdresse());
            ps.setString(5, client.getTelephone());
            ps.setString(6, client.getEmail());
            ps.setString(7, client.getSsn());
            ps.setString(8, client.getAssurance());
            ps.setString(9, client.getAllergies());
            ps.setString(10, client.getConditions());
            ps.setString(11, client.getMedicaments());
            ps.setString(12, client.getAntecedents());
            ps.setDate(13, Date.valueOf(client.getDerniereVisite()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour un client dans la base de données
     * 
     * @param client le client à mettre à jour
     */

    public void updateClient(Client client) {
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(UPDATE_CLIENT)) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setDate(3, Date.valueOf(client.getDateNaissance()));
            ps.setString(4, client.getAdresse());
            ps.setString(5, client.getTelephone());
            ps.setString(6, client.getEmail());
            ps.setString(7, client.getSsn());
            ps.setString(8, client.getAssurance());
            ps.setString(9, client.getAllergies());
            ps.setString(10, client.getConditions());
            ps.setString(11, client.getMedicaments());
            ps.setString(12, client.getAntecedents());
            ps.setInt(13, client.getIdClient());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime un client de la base de données
     * 
     * @param client le client à supprimer
     */

    public void deleteClient(Client client) {
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(DELETE_CLIENT)) {
            ps.setInt(1, client.getIdClient());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

 