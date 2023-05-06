package GestionPharmacie.JFx.models.client;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {

    private IntegerProperty idClient = new SimpleIntegerProperty();

    private StringProperty nom = new SimpleStringProperty();
    private StringProperty prenom = new SimpleStringProperty();
    private StringProperty dateNaissance = new SimpleStringProperty();
    private StringProperty adresse = new SimpleStringProperty();
    private StringProperty telephone = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty ssn = new SimpleStringProperty();
    private StringProperty assurance = new SimpleStringProperty();
    private StringProperty allergies = new SimpleStringProperty();
    private StringProperty conditions = new SimpleStringProperty();
    private StringProperty medicaments = new SimpleStringProperty();
    private StringProperty antecedents = new SimpleStringProperty();
    private ObjectProperty<LocalDate> derniereVisite = new SimpleObjectProperty();

    public Client(Integer idClient, String nom, String prenom, String dateNaissance, String adresse,
            String telephone, String email,
            String ssn, String assurance, String allergies, String conditions, String medicaments, String antecedents,
            LocalDate derniereVisite) {

        setIdClient(idClient);
        setNom(nom);
        setPrenom(prenom);
        setDateNaissance(dateNaissance);
        setAdresse(adresse);
        setTelephone(telephone);
        setEmail(email);
        setSsn(ssn);
        setAssurance(assurance);
        setAllergies(allergies);
        setConditions(conditions);
        setMedicaments(medicaments);
        setAntecedents(antecedents);
        setDerniereVisite(derniereVisite);

    }

    public Client() {
    }

    // Getters and setters

    public Integer getIdClient() {
        return idClient.get();
    }

    public IntegerProperty IdClientProperty() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient.set(idClient);
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }

    public StringProperty prenomProperty() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getDateNaissance() {
        return dateNaissance.get();
    }

    public StringProperty dateNaissanceProperty() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance.set(dateNaissance);
    }

    public String getAdresse() {
        return adresse.get();
    }

    public StringProperty adresseProperty() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public String getTelephone() {
        return telephone.get();
    }

    public StringProperty telephoneProperty() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getSsn() {
        return ssn.get();
    }

    public StringProperty ssnProperty() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn.set(ssn);
    }

    public String getAssurance() {
        return assurance.get();
    }

    public StringProperty assuranceProperty() {
        return assurance;
    }

    public void setAssurance(String assurance) {
        this.assurance.set(assurance);
    }

    public String getAllergies() {
        return allergies.get();
    }

    public StringProperty allergiesProperty() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies.set(allergies);
    }

    public String getConditions() {
        return conditions.get();
    }

    public void setConditions(String conditions) {
        this.conditions.set(conditions);
    }

    public String getMedicaments() {
        return medicaments.get();
    }

    public StringProperty medicamentsProperty() {
        return medicaments;
    }

    public void setMedicaments(String medicaments) {
        this.medicaments.set(medicaments);
    }

    public String getAntecedents() {
        return antecedents.get();
    }

    public StringProperty antecedentsProperty() {
        return antecedents;
    }

    public void setAntecedents(String antecedents) {
        this.antecedents.set(antecedents);
    }

    public LocalDate getDerniereVisite() {
        return derniereVisite.get();
    }

    public ObjectProperty derniereVisiteProperty() {
        return derniereVisite;
    }

    public void setDerniereVisite(LocalDate derniereVisite) {
        this.derniereVisite.set(derniereVisite);
    }


    public String getCity() {
        return null;
    }

}
