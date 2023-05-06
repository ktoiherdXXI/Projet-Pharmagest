package GestionPharmacie.BDPM;

public class Cis_Pharm_bdpm {

    private Integer id_cis_grp;
    private Integer code_cis;
    private Character status_Specialite;
    private String libelePharmaceutique;
    private String mention_Change;

    public Cis_Pharm_bdpm() {
    }

    public Cis_Pharm_bdpm(Integer id_cis_grp, Integer code_cis, Character status_Specialite,
            String libelePharmaceutique, String mention_Change) {
        this.id_cis_grp = id_cis_grp;
        this.code_cis = code_cis;
        this.status_Specialite = status_Specialite;
        this.libelePharmaceutique = libelePharmaceutique;
        this.mention_Change = mention_Change;
    }

    public Integer getId_cis_grp() {
        return id_cis_grp;
    }

    public void setId_cis_grp(Integer id_cis_grp) {
        this.id_cis_grp = id_cis_grp;
    }

    public Integer getCode_cis() {
        return code_cis;
    }

    public void setCode_cis(Integer code_cis) {
        this.code_cis = code_cis;
    }

    public Character getStatus_Specialite() {
        return status_Specialite;
    }

    public void setStatus_Specialite(Character status_Specialite) {
        this.status_Specialite = status_Specialite;
    }

    public String getLibelePharmaceutique() {
        return libelePharmaceutique;
    }

    public void setLibelePharmaceutique(String libelePharmaceutique) {
        this.libelePharmaceutique = libelePharmaceutique;
    }

    public String getMention_Change() {
        return mention_Change;
    }

    public void setMention_Change(String mention_Change) {
        this.mention_Change = mention_Change;
    }

}
