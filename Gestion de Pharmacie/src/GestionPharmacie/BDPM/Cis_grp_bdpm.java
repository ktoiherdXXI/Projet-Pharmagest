package GestionPharmacie.BDPM;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cis_grp_bdpm {

    private IntegerProperty id_cis = new SimpleIntegerProperty();
    private IntegerProperty id_cis_grp = new SimpleIntegerProperty();
    private StringProperty voie_administration = new SimpleStringProperty();
    private StringProperty libele_grp_gen = new SimpleStringProperty();

    public Cis_grp_bdpm(Integer id_cis, Integer id_cis_grp, String voie_administration, String libele_grp_gen) {
        setId_cis(id_cis);
        setId_cis_grp(id_cis_grp);
        setVoie_administration(voie_administration);
        setLibele_grp_gen(libele_grp_gen);
    }

    public Integer getId_cis() {
        return id_cis_Property().get();
    }

    public IntegerProperty id_cisProperty() {
        return id_cis_Property();
    }

    public void setId_cis(Integer id_cis) {
        id_cis_Property().set(id_cis);
    }

    public IntegerProperty id_cis_Property() {
        if (id_cis == null)
            id_cis = new SimpleIntegerProperty(this, "NULL");
        return id_cis;
    }

    public Integer getId_cis_grp() {
        return id_cis_grp_Property().get();
    }

    public IntegerProperty id_cis_grpProperty() {
        return id_cis_grp_Property();
    }

    public void setId_cis_grp(Integer id_cis_grp) {
        id_cis_grp_Property().set(id_cis_grp);
    }

    public IntegerProperty id_cis_grp_Property() {
        if (id_cis_grp == null)
            id_cis_grp = new SimpleIntegerProperty(this, "NULL");
        return id_cis_grp;
    }

    public String getVoie_administration() {
        return voie_administration.get();
    }

    public StringProperty voie_administrationProperty() {
        return voie_administration;
    }

    public void setVoie_administration(String voie_administration) {
        this.voie_administration.set(voie_administration);
    }

    public String getLibele_grp_gen() {
        return libele_grp_gen_Property().get();
    }

    public StringProperty libele_grp_genProperty() {
        return libele_grp_gen_Property();
    }

    public void setLibele_grp_gen(String libele_grp_gen) {
        libele_grp_gen_Property().set(libele_grp_gen);
    }

    public StringProperty libele_grp_gen_Property() {
        if (libele_grp_gen == null)
            libele_grp_gen = new SimpleStringProperty(this, "NULL");
        return libele_grp_gen;
    }
}
