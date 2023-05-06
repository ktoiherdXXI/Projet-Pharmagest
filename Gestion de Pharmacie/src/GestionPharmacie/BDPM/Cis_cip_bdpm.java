package GestionPharmacie.BDPM;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cis_cip_bdpm {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty libeleCommune = new SimpleStringProperty();

    public Cis_cip_bdpm(Integer id, String libeleCommune) {
        setID(id);
        setLibeleCommune(libeleCommune);
    }

    public Cis_cip_bdpm() {
    }

    public Integer getID() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setID(Integer id) {
        this.id.set(id);
    }

    public String getLibeleCommune() {
        return libeleCommune.get();
    }

    public StringProperty libeleCommuneProperty() {
        return libeleCommune;
    }

    public void setLibeleCommune(String libeleCommune) {
        this.libeleCommune.set(libeleCommune);
    }

}
