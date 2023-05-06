package GestionPharmacie;

public class QueryStatement {

	/*
	 * *** *
	 * CIS_CIP_BDPM
	 * *** *
	 */
	public final static String ADD_CIS_CIP_BDPM_QUERY = "INSERT INTO cis_cip_bdpm( id ,libele) VALUES(?, ? )";

	public static final String GET_ALL_CIS_CIP_BDPM_QUERY = "SELECT * FROM cis_cip_bdpm";

	public final static String DELETE_CIS_CIP_BDPM_QUERY = "DELETE FROM cis_cip_bdpm WHERE id=?";

	public final static String UPDATE_CIS_CIP_BDPM_QUERY = "UPDATE cis_cip_bdpm libeleCommune=? where id=?"; // "SET
	// id=?"
	// Possible

	/*
	 * *** *
	 * CIS_GRP_BDPM
	 * *** *
	 */
	public static final String ADD_CIS_GRP_BDPM_QUERY = "INSERT INTO cis_grp_bdpm( id_cis ,id_cis_grp, voie_administration, libele_grp_gen) VALUES(?, ?, ?, ? )";

	public static final String GET_ALL_CIS_GRP_BDPM_QUERY = "SELECT * FROM cis_grp_bdpm";

	public final static String DELETE_CIS_GRP_BDPM_QUERY = "DELETE FROM cis_grp_bdpm WHERE id_cis_grp =?";

	public final static String UPDATE_CIS_GRP_BDPM_QUERY = "UPDATE cis_grp_bdpm  id_cis=?, voie_administration=?, libele_grp_gen=?  WHERE  id_cis_grp = ?";
	// id=?"

	/*
	 * *** *
	 * CIS_PHARM_BDPM
	 * *** *
	 */

	public static final String ADD_CIS_PHARM_BDPM_QUERY = "INSERT INTO cis_pharm_bdpm( id_cis_grp ,code_cis, status_specialite, libele_pharmaceutique, mention_Change) VALUES (?, ?, ?, ?, ?)";

	/*
	 * *** *
	 * CIS_COMPLEMENT_BDPM
	 * *** *
	 */
	public static final String ADD_CIS_COMPLEMENT_BDPM_QUERY = "INSERT INTO cis_complement_bdpm( code_cis, code_cis_sub) VALUES (?, ?)";

	/*
	 * *** *
	 * CIS_PRENTATION_BDPM
	 * *** *
	 */

	public static final String ADD_CIS_PRENTATION_BDPM_QUERY = "INSERT INTO	cis_presentation_bdpm( code_cis_pharm, code_cip, no_used, libele_presentation) VALUES (?, ?, ?, ?)";

	/*
	 * *** *
	 * PHARMACIEN
	 * *** *
	 */

	public final static String ADD_PHARMACIEN_QUERY = "INSERT INTO pharmacien( nom,  prenom,  fonction,  adresse, telephone, mail,  mot_de_passe) VALUES(? ,? ,? ,? ,? ,? ,? )"; // idpharmacien

	public final static String UPDATE_PHARMACIEN_QUERY = "UPDATE pharmacien SET nom=?, prenom=?, fonction=?, adresse=?,telephone=?,mail=?, mot_de_passe=? where idPharmacien = ?"; // idPharmacien=?,

	public final static String DELETE_PHARMACIEN_QUERY = "DELETE FROM pharmacien WHERE idPharmacien=?";

	public final static String SELECT_ALL_PHARMACIEN_QUERY = "SELECT * FROM pharmacien";

}
