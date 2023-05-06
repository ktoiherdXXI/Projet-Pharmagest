package GestionPharmacie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

import GestionPharmacie.BDPM.Cis_Pharm_bdpm;
import GestionPharmacie.BDPM.Cis_cip_bdpm;
import GestionPharmacie.BDPM.Cis_complement_bdpm;
import GestionPharmacie.BDPM.Cis_grp_bdpm;
import GestionPharmacie.BDPM.Cis_presentation_bdpm;

// import GestionPharmacie.QueryStatement;
// import GestionPharmacie.Pharmacien;

public class DBUtil {

	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet = null;
	private static List<Pharmacien> pharmacienList = new ArrayList<Pharmacien>();
	private static List<Cis_cip_bdpm> cis_cip_bdpmList = new ArrayList<Cis_cip_bdpm>();

	/*
	 * * Cis_cip_bdpm * *
	 * *******************************************************************
	 */
	private static void setPreparedStatementProperties(Integer id, String libeleCommune) {
		try {
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, libeleCommune);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Cis_cip_bdpm> getAllCis_cip_bdpm() throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.GET_ALL_CIS_CIP_BDPM_QUERY);
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Cis_cip_bdpm cis_cip_bdpm = new Cis_cip_bdpm();
			cis_cip_bdpm.setID(resultSet.getInt("id"));
			cis_cip_bdpm.setLibeleCommune(resultSet.getString("libeleCommune"));
			cis_cip_bdpmList.add(cis_cip_bdpm);
		}
		closeConnections();
		return cis_cip_bdpmList;
	}

	public static void addCis_cip_bdpm(Cis_cip_bdpm cis_cip_bdpm) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.ADD_CIS_CIP_BDPM_QUERY);
		setPreparedStatementProperties(
				cis_cip_bdpm.getID(),
				cis_cip_bdpm.getLibeleCommune());
		preparedStatement.executeUpdate();

		closeConnections();
	}

	public static void updateCis_cip_bdpm(Cis_cip_bdpm cis_cip_bdpm) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.UPDATE_CIS_CIP_BDPM_QUERY);
		setPreparedStatementProperties(
				cis_cip_bdpm.getID().toString(),
				cis_cip_bdpm.getLibeleCommune());
		preparedStatement.executeUpdate();

		closeConnections();
	}

	/*
	 * * Cis_grp_bdpm * *
	 * *******************************************************************
	 */
	private static void setPreparedStatementProperties(Integer id_cis, Integer id_cis_grp, String voie_administration,
			String libele_grp_gen) {
		try {
			preparedStatement.setInt(1, id_cis);
			preparedStatement.setInt(2, id_cis_grp);
			preparedStatement.setString(3, voie_administration);
			preparedStatement.setString(4, libele_grp_gen);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addCis_grp_bdpm(Cis_grp_bdpm cis_grp_bdpm) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.ADD_CIS_GRP_BDPM_QUERY);
		setPreparedStatementProperties(
				cis_grp_bdpm.getId_cis(),
				cis_grp_bdpm.getId_cis_grp(),
				cis_grp_bdpm.getVoie_administration(),
				cis_grp_bdpm.getLibele_grp_gen());
		preparedStatement.executeUpdate();
		closeConnections();

	}

	public static void updateCis_grp_bdpm(Cis_grp_bdpm cis_grp_bdpm) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.UPDATE_CIS_GRP_BDPM_QUERY);
		setPreparedStatementProperties(
				cis_grp_bdpm.getId_cis(),
				cis_grp_bdpm.getId_cis_grp(),
				cis_grp_bdpm.getVoie_administration(),
				cis_grp_bdpm.getLibele_grp_gen());
		preparedStatement.executeUpdate();
		closeConnections();
	}

	/*
	 * * Cis_pharm_bdpm * *
	 * *******************************************************************
	 */
	private static void setPreparedStatementProperties(Integer id_cis_grp, Integer Code_cis,
			Character Status_Specialite, String libelePharmaceutique, String mention_Change) {
		try {
			preparedStatement.setInt(1, id_cis_grp);
			preparedStatement.setInt(2, Code_cis);
			preparedStatement.setString(3, Status_Specialite.toString());
			preparedStatement.setString(4, libelePharmaceutique);
			preparedStatement.setString(5, mention_Change);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addCis_pharm_bdpm(Cis_Pharm_bdpm cis_pharm_bdpm) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.ADD_CIS_PHARM_BDPM_QUERY);
		setPreparedStatementProperties(
				cis_pharm_bdpm.getId_cis_grp(),
				cis_pharm_bdpm.getCode_cis(),
				cis_pharm_bdpm.getStatus_Specialite(),
				cis_pharm_bdpm.getLibelePharmaceutique(),
				cis_pharm_bdpm.getMention_Change());
		preparedStatement.executeUpdate();
		closeConnections();
	}

	/*
	 * * Cis_pharm_bdpm * *
	 * *******************************************************************
	 */

	public static void addCis_complement_bdpm(Cis_complement_bdpm cis_complement_bdpm) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.ADD_CIS_COMPLEMENT_BDPM_QUERY);
		try {
			preparedStatement.setInt(1, cis_complement_bdpm.getCode_cis());
			preparedStatement.setInt(2, cis_complement_bdpm.getCode_cis_sub());
			preparedStatement.executeUpdate();
			closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * * Cis_prentation_bdpm * *
	 * *******************************************************************
	 */

	private static void setPreparedStatementProperties(Integer code_cis_pharm, String code_cip, String no_used,
			String libele_presentation) {
		try {
			preparedStatement.setInt(1, code_cis_pharm);
			preparedStatement.setString(2, code_cip);
			preparedStatement.setString(3, no_used);
			preparedStatement.setString(4, libele_presentation);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addCis_presentation_bdpm(Cis_presentation_bdpm cis_prentation_bdpm) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.ADD_CIS_PRENTATION_BDPM_QUERY);

		setPreparedStatementProperties(
				cis_prentation_bdpm.getCode_cis_pharm(),
				cis_prentation_bdpm.getCode_cip(),
				cis_prentation_bdpm.getNo_used(),
				cis_prentation_bdpm.getLibele_presentation());
		preparedStatement.executeUpdate();
		closeConnections();
	}

	/*
	 * * Pharmacien * *
	 * *******************************************************************
	 */

	public static void addPharmacien(Pharmacien pharmacien) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.ADD_PHARMACIEN_QUERY);

		/// String
		setPreparedStatementProperties(
				// pharmacien.idPharmacien.toString(),
				pharmacien.getNom(),
				pharmacien.getPrenom(),
				pharmacien.getFonction(),
				pharmacien.getAdresse(),
				pharmacien.getTelephone(),
				pharmacien.getMail(),
				pharmacien.getPassword());

		preparedStatement.executeUpdate();

		closeConnections();
	}

	public static void updatePharmacien(Pharmacien pharmacien) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.UPDATE_PHARMACIEN_QUERY);
		// pharmacien.toString()
		setPreparedStatementProperties(
				// pharmacien.idPharmacien.toString(),
				pharmacien.getNom(),
				pharmacien.getPrenom(),
				pharmacien.getFonction(),
				pharmacien.getAdresse(),
				pharmacien.getTelephone(),
				pharmacien.getMail(),
				pharmacien.getPassword());
		preparedStatement.executeUpdate();

		closeConnections();
	}

	public static void deletePharmacien(Pharmacien pharmacien) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.DELETE_PHARMACIEN_QUERY);

		setPreparedStatementProperties(pharmacien.idPharmacien.toString());
		preparedStatement.executeUpdate();

		closeConnections();
	}

	public static List<Pharmacien> getAllPharmacien() throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.SELECT_ALL_PHARMACIEN_QUERY);

		resultSet = preparedStatement.executeQuery();

		pharmacienList.clear();

		while (resultSet.next()) {
			Pharmacien pharmacien = new Pharmacien();
			pharmacien.setNom(resultSet.getString(1));
			pharmacien.setPrenom(resultSet.getString(2));
			pharmacien.setFonction(resultSet.getString(3));
			pharmacien.setAdresse(resultSet.getString(4));
			pharmacien.setTelephone(resultSet.getString(5));
			pharmacien.setMail(resultSet.getString(6));
			pharmacien.setPassword(resultSet.getString(7));
			pharmacien.setIdPharmacien(resultSet.getInt(8));
			pharmacienList.add(pharmacien);
		}

		return pharmacienList;
	}

	private static void closeConnections() throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

	// @param variable length array of strings as pharmacien properties
	private static void setPreparedStatementProperties(String... Args) throws SQLException {
		for (int i = 0; i < Args.length; i++) {
			preparedStatement.setString(i + 1, Args[i]);
		}
	}

}
