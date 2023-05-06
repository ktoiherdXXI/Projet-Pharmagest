package GestionPharmacie.JFx.Controlleurs;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.CardPane;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;

import GestionPharmacie.JFx.models.client.Client;
import GestionPharmacie.JFx.models.client.ModelClientPane;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Classe représentant l'interface graphique de l'application de gestion des
 * clients.
 */
public class ControllerClientPane implements Initializable {

    // Définition des éléments du formulaire de saisie client avec @FXML
    @FXML
    private MFXTextField adresseLabel;

    @FXML
    private MFXTextField allergiesLabel;

    @FXML
    private MFXTextField antecedentsLabel;

    @FXML
    private MFXTextField assuranceLabel;

    @FXML
    private MFXTextField ssnLabel;

    @FXML
    private MFXTextField conditionsLabel;

    @FXML
    private MFXDatePicker dateNaissanceLabel;

    @FXML
    private MFXTextField emailLabel;

    @FXML
    private MFXTextField medicamentsLabel;

    @FXML
    private MFXTextField nomLabel;

    @FXML
    private MFXTextField prenomLabel;

    @FXML
    private MFXTextField telephoneLabel;

    @FXML
    private MFXTextField clientSearchBar;

    // Zone d'affichage des clients
    @FXML
    private VBox vBoxPane;

    // Boutons d'action sur les clients
    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    // Liste des clients
    @FXML
    private ListView<Client> clientListView;

    // Liste observable de clients utilisée pour alimenter le ListView
    private ObservableList<Client> clientList = FXCollections.observableArrayList();

    // Labels d'information sur un client sélectionné
    Label ageLabel = new Label();
    Label adresse = new Label();
    Label email = new Label();
    Label telephone = new Label();

    // Flag indiquant si le formulaire est en mode d'édition ou non
    private boolean isEditMode = false;

    View viewPane = new View();

    private View selectedClientView;

    // Client sélectionné dans le formulaire de saisie
    private Client client = new Client();

    // Client sélectionné dans la liste des clients
    private Client selectedClient;

    // Variable pour stocker l'ID du client sélectionné
    private int selectedClientId;

    // Modèle contenant les opérations de CRUD sur les clients
    ModelClientPane modelClientPane = new ModelClientPane();

    // Liste de tous les clients dans la base de données
    List<Client> clients = modelClientPane.getAllClients();

    // Créer un objet Client à partir des valeurs du formulaire de saisie
    private Client createClientFromLabels() {
        Client updatedClient = new Client();

        // Copier l'ID du client existant pour mettre à jour la même entrée dans la base
        // de données
        updatedClient.setIdClient(client.getIdClient());

        // Récupérer les valeurs des champs du formulaire de saisie
        updatedClient.setAdresse(adresseLabel.getText());
        updatedClient.setAllergies(allergiesLabel.getText());
        updatedClient.setAntecedents(antecedentsLabel.getText());
        updatedClient.setAssurance(assuranceLabel.getText());
        updatedClient.setConditions(conditionsLabel.getText());

        // Récupérer la date de naissance
        updatedClient.setDateNaissance(dateNaissanceLabel.getValue().toString());

        updatedClient.setEmail(emailLabel.getText());
        updatedClient.setSsn(ssnLabel.getText());
        updatedClient.setMedicaments(medicamentsLabel.getText());
        updatedClient.setNom(nomLabel.getText());
        updatedClient.setPrenom(prenomLabel.getText());
        updatedClient.setTelephone(telephoneLabel.getText());
        updatedClient.setDerniereVisite(LocalDate.now());

        return updatedClient;
    }

    // Créer une boîte contenant le nom et prénom d'un client, ainsi que son âge
    /**
     * Crée une boîte contenant les informations d'adresse, d'email et de téléphone
     * du client.
     * 
     * @param adresse   L'étiquette d'adresse du client.
     * @param email     L'étiquette d'email du client.
     * @param telephone L'étiquette de téléphone du client.
     * @return La boîte contenant les informations.
     */

    private HBox createNameBox(String nom, String prenom, Integer age) {
        // Créer les labels pour afficher le nom et le prénom du client
        Label name = new Label(nom);
        name.setStyle("-fx-text-fill: #454545; -fx-font-weight: bold; -fx-font-family: Roboto; -fx-font-size: 12;");
        Label lastname = new Label(prenom);
        lastname.setStyle("-fx-text-fill: #454545; -fx-font-weight: bold; -fx-font-family: Roboto; -fx-font-size: 12;");

        // Créer le label pour afficher l'âge du client
        Label ageLabel = new Label(age + " ans");
        ageLabel.setStyle("-fx-text-fill: #454545; -fx-font-weight: bold; -fx-font-family: Roboto; -fx-font-size: 12;");

        // Créer une boîte horizontale pour afficher les labels
        HBox nameBox = new HBox();
        nameBox.getChildren().addAll(lastname, name, ageLabel);

        // Retourner la boîte
        return nameBox;
    }

    private HBox createInfoBox(Label adresse, /* Label email, */ Label telephone) {
        // Créer une boîte horizontale pour contenir les informations
        HBox infoBox = new HBox();

        // Ajouter une icône de localisation à la boîte
        MFXFontIcon locationIcon = new MFXFontIcon("mfx-map");
        locationIcon.setColor(Color.rgb(69, 69, 69, 1));
        infoBox.getChildren().addAll(locationIcon, adresse);

        // Si l'étiquette d'email n'est pas nulle et n'est pas vide, ajouter une icône
        // d'email et l'étiquette d'email à la boîte
        if (email != null && !email.getText().isEmpty()) {
            MFXFontIcon emailIcon = new MFXFontIcon("");
            emailIcon.setColor(Color.rgb(69, 69, 69, 1));
            infoBox.getChildren().addAll(new Label("   "), emailIcon, new Label(" "), email);
        }

        // Si l'étiquette de téléphone n'est pas nulle et n'est pas vide, ajouter une
        // icône de téléphone et l'étiquette de téléphone à la boîte
        if (telephone != null && !telephone.getText().isEmpty()) {
            MFXFontIcon phoneIcon = new MFXFontIcon("");
            phoneIcon.setColor(Color.rgb(69, 69, 69, 1));

            if (email != null && !email.getText().isEmpty()) {
                infoBox.getChildren().addAll(new Label("   "), phoneIcon, new Label(" "), telephone);
            } else {
                infoBox.getChildren().addAll(phoneIcon, telephone);
            }
        }

        // Retourner la boîte contenant les informations
        return infoBox;
    }

    /**
     * Met à jour les détails du client enregistré, rafraîchit la liste des clients,
     * désactive l'édition des champs et affiche les clients mis à jour.
     */
    private void updateClientDetails() {
        // Enregistrer les modifications dans la base de données en créant un nouveau
        // client à partir des étiquettes de la vue détaillée.
        modelClientPane.updateClient(createClientFromLabels());

        // Rafraîchir la liste des clients pour inclure le client mis à jour
        refreshClientList();

        // Désactiver l'édition des champs et activer l'état de vue seule.
        setFieldsEditable(false);

        // Désactiver le bouton "Enregistrer"
        // saveButton.setDisable( true );

        // Afficher la liste des clients mise à jour
        afficherClients(clients);

        // Mettre en surbrillance la vue client sélectionnée
        highlightSelectedClientView(selectedClientView);
    }

    /**
     * Met en surbrillance la vue client sélectionnée dans la liste des clients en
     * désélectionnant toutes les autres vues clients et en appliquant un style
     * différent à la vue sélectionnée.
     * 
     * @param selectedClientView La vue client sélectionnée.
     */
    private void highlightSelectedClientView(View selectedClientView) {
        // Désélectionner toutes les autres vues clients en retirant leur style
        for (Node node : vBoxPane.getChildren()) {
            if (node instanceof View) {
                node.setStyle("");
            }
        }
        // Appliquer un style différent à la vue client sélectionnée pour la mettre en
        // surbrillance
        selectedClientView.setStyle("-fx-background-color: #F0F0F0;");
        this.selectedClientView = selectedClientView;
    }

    /**
     * 
     * Affiche les détails du client sélectionné dans la vue détaillée.
     * 
     * @param client Le client sélectionné.
     */
    private void showClientDetails(Client client) {
        if (clientListView != null && clientListView.getSelectionModel() != null) {

            // Afficher les détails du client dans la vue détaillée
            nomLabel.setText(client.getNom());
            prenomLabel.setText(client.getPrenom());

            // dateNaissanceLabel.setValue(LocalDate.parse(client.getDateNaissance()));
            // Convertir la date de naissance en un format lisible et la mettre à jour dans
            // l'interface utilisateur
            String dateString = client.getDateNaissance(); // Date avec décalage horaire
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd X");
            LocalDate date = LocalDate.parse(dateString, formatter);
            dateNaissanceLabel.setValue(date);

            adresseLabel.setText(client.getAdresse());
            emailLabel.setText(client.getEmail());
            telephoneLabel.setText(client.getTelephone());
            ssnLabel.setText(client.getSsn());
            allergiesLabel.setText(client.getAllergies());
            antecedentsLabel.setText(client.getAntecedents());
            assuranceLabel.setText(client.getAssurance());
            conditionsLabel.setText(client.getConditions());
            medicamentsLabel.setText(client.getMedicaments());

            // Désactiver la modification des champs
            setFieldsEditable(false);

        }
        // dateNaissanceLabel.setDisable(true);}

    }

    /**
     * 
     * Récupère tous les clients depuis le modèle de l'interface utilisateur et les
     * affiche dans la liste des clients.
     */
    private void refreshClientList() {
        clients = modelClientPane.getAllClients();
        afficherClients(clients);
    }

    /**
     * Crée une vue client en utilisant des boîtes VBox, HBox, BorderPane et des
     * labels.
     *
     * @param client Le client pour lequel créer la vue client.
     * @return La vue client créée.
     */
    View createClientView(Client client) {
        // Créer la vue client en utilisant une VBox, une HBox, un BorderPane et les
        // labels correspondants.
        VBox clientBox = new VBox();
        String nom = new String(client.getNom());
        nom = nom.toUpperCase();

        String prenom = new String(client.getPrenom());

        // recuperation la date de naissance dans ce format (10/10/2000 +3) et calcule
        // des années passés jusqu'a présent pour founir l'âge
        String dateString = client.getDateNaissance(); // Date avec décalage horaire
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd X");
        LocalDate date = LocalDate.parse(dateString, formatter);
        LocalDate now = LocalDate.now();
        Period diff = Period.between(date, now);
        int age = diff.getYears();

        HBox nameBox = createNameBox(nom, prenom, age);
        // Ajout spacing namBox
        nameBox.setSpacing(5);

        Label adresse = new Label(client.getAdresse());
        Label email = new Label(client.getEmail());
        Label telephone = new Label(client.getTelephone());
        HBox infoBox = createInfoBox(adresse, /* email, */ telephone);
        // Ajout spacing infoBox
        infoBox.setSpacing(5);

        // Rendre infoBox sélectionnable
        infoBox.setMouseTransparent(false);

        // // Créer un avatar
        // Avatar avatar = new Avatar();
        // avatar.setStyle("-fx-background-color: #ff0000 !important;");
        // avatar.setStyle("-fx-fill: #ff0000;");

        // creer un element circulaire qui pourra contenir les initial du client avec un
        // fond de couleur aléatoir et les initiales toujours bien visible au millieux
        Circle circle = new Circle(30, 30, 20);

        // chaque circle Donnes lui une couleur diffrent et aléatoir
        // mais jamais le blanc

        circle.setFill(
                Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));

        // Créer un icône "angle droit" avec la bibliothèque MFX et lui donner une
        // couleur grise
        MFXFontIcon angle_R_Icon = new MFXFontIcon("mfx-angle-right");
        angle_R_Icon.setColor(Color.rgb(69, 69, 69, 1));

        // Créer un icône "localisation" avec la bibliothèque MFX et lui donner une
        // couleur grise
        MFXFontIcon locationIcon = new MFXFontIcon("mfx-map");
        locationIcon.setColor(Color.rgb(69, 69, 69, 1));

        // Ajouter les éléments à la VBox clientBox
        clientBox.getChildren().addAll(nameBox, infoBox);
        // centrer client box
        clientBox.setAlignment(Pos.CENTER_LEFT);

        // Espacement vertical entre les éléments de la VBox
        clientBox.setSpacing(15);

        // Hauteur préférée de la VBox
        clientBox.setPrefHeight(80);

        // Ajouter l'icône de localisation à la HBox infoBox
        infoBox.getChildren().addAll(locationIcon);

        // Créer un objet View
        View viewPane = new View();

        // Ajouter la VBox clientBox au centre de la vue
        viewPane.setCenter(clientBox);

        // Alligner clientBox au centre
        viewPane.setAlignment(clientBox, Pos.CENTER_LEFT);

        // Ajouter l'avatar à gauche de la vue
        viewPane.setLeft(circle);
        // centres l'avatar
        viewPane.setAlignment(circle, Pos.CENTER);
        // Ajout de margin à l'avatar
        viewPane.setMargin(circle, new Insets(0, 10, 0, 0));

        // Ajouter les initial du cleint en maj dans le centre de le cercle
        Text initials = new Text(nom.substring(0, 1) + prenom.substring(0, 1).toUpperCase());
        initials.setFill(Color.WHITE);

        // Mets les initials en majuscule

        // Donnes une couleur claire et diffrent aux initials de chaque client
        // Random rand = new Random();
        // int r = rand.nextInt(255);
        // int g = rand.nextInt(255);
        // int b = rand.nextInt(255);

        // initials.setFill(Color.rgb(r, g, b, 1));

        initials.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        initials.setTextAlignment(TextAlignment.CENTER);
        initials.setWrappingWidth(60);
        initials.setTextOrigin(VPos.CENTER);
        initials.setTextAlignment(TextAlignment.CENTER);
        // initials.setTranslateX(0);
        // initials.setTranslateY(0);

        // Centrer les initiales dans le cercle
        initials.setTranslateX(circle.getCenterX() - initials.getBoundsInLocal().getWidth() / 1.5);
        initials.setTranslateY(circle.getCenterY() + initials.getBoundsInLocal().getHeight() / 2);
        viewPane.getChildren().add(initials);

        // mettre initials dans le centre de le cercle
        // viewPane.setAlignment(initials, Pos.CENTER);

        // L'avatar prend une couleur de fond aléatoir

        // Ajouter l'icône "angle droit" à droite de la vue
        viewPane.setRight(angle_R_Icon);
        // Centrer l'icône "angle droit"
        viewPane.setAlignment(angle_R_Icon, Pos.CENTER_RIGHT);

        // Changer le curseur de la vue lorsqu'on passe la souris dessus en Hand👆🏾
        viewPane.setCursor(Cursor.HAND);

        // Ajouter un gestionnaire d'événements à la vue
        viewPane.setOnMouseClicked(event -> {
            // Vérifier si le bouton de la souris cliqué est le bouton gauche
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                // Sélectionner le client associé à cette vue client
                this.client = client;
                this.selectedClient = client;

                // Mettre à jour les champs de texte avec les détails du client sélectionné
                showClientDetails(client);

                // Mettre en surbrillance la vue client sélectionnée
                highlightSelectedClientView(viewPane);

                // Mettre à jour la variable selectedClientView avec la vue client sélectionnée
                selectedClientView = viewPane;

                // Afficher l'id du client sélectionné
                System.out.println("Client ID: " + client.getIdClient());
            }
        });

        // Retourner la vue client créée
        return viewPane;
    }

    /**
     * Rend tous les champs éditables ou non éditables en fonction de la valeur du
     * paramètre.
     * 
     * @param editable - true si les champs doivent être éditables, false sinon.
     */
    private void setFieldsEditable(boolean editable) {
        nomLabel.setEditable(editable);
        prenomLabel.setEditable(editable);
        adresseLabel.setEditable(editable);
        telephoneLabel.setEditable(editable);
        emailLabel.setEditable(editable);
        ssnLabel.setEditable(editable);
        antecedentsLabel.setEditable(editable);
        allergiesLabel.setEditable(editable);
        medicamentsLabel.setEditable(editable);
        conditionsLabel.setEditable(editable);
        assuranceLabel.setEditable(editable);
        dateNaissanceLabel.setEditable(editable);
    }

    /**
     * Crée un nouveau client à partir des données dans les champs d'entrée.
     * 
     * @return un objet Client avec les données entrées.
     */
    private Client createClient() {
        Client client = new Client();
        client.setNom(nomLabel.getText());
        client.setPrenom(prenomLabel.getText());
        // client.setGenre(genreLabel.getValue());
        client.setDateNaissance(dateNaissanceLabel.getValue().toString());
        client.setAdresse(adresseLabel.getText());
        client.setTelephone(telephoneLabel.getText());
        client.setEmail(emailLabel.getText());
        client.setSsn(ssnLabel.getText());
        client.setAntecedents(antecedentsLabel.getText());
        client.setAllergies(allergiesLabel.getText());
        client.setMedicaments(medicamentsLabel.getText());
        client.setConditions(conditionsLabel.getText());
        client.setAssurance(assuranceLabel.getText());
        client.setDerniereVisite(LocalDate.now());

        return client;
    }

    /**
     * Réinitialise les champs d'entrée et désélectionne le client actuellement
     * sélectionné.
     */
    private void resetFields() {
        nomLabel.setText("");
        prenomLabel.setText("");
        // genreLabel.setValue("");
        dateNaissanceLabel.setValue(null);
        adresseLabel.setText("");
        telephoneLabel.setText("");
        emailLabel.setText("");
        ssnLabel.setText("");
        antecedentsLabel.setText("");
        allergiesLabel.setText("");
        medicamentsLabel.setText("");
        conditionsLabel.setText("");
        assuranceLabel.setText("");
        // dateNaissanceLabel.setValue(null);

        // Désélectionner le client
        client = null;
    }

    /**
     * Affiche la liste de clients donnée dans l'interface graphique.
     * 
     * @param clients - une liste de clients à afficher.
     */
    private void afficherClients(List<Client> clients) {
        clientList.clear();
        vBoxPane.getChildren().clear(); // Efface les vues existantes
        for (Client client : clients) {
            View clientView = createClientView(client);
            clientList.add(client);
            vBoxPane.getChildren().add(clientView);
        }
    }

    /**
     * Filtrage des clients dans la liste en fonction d'une chaîne de recherche.
     * À partir de la bare de recherche client
     * 
     * @param query - une chaîne de recherche pour filtrer la liste de clients.
     */
    private void filtrerClients(String query) {
        List<Client> filteredClients = clients.stream()
                .filter(client -> client.getNom().toLowerCase().contains(query.toLowerCase())
                        || client.getPrenom().toLowerCase().contains(query.toLowerCase())
                        || client.getEmail().toLowerCase().contains(query.toLowerCase())
                        || client.getTelephone().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        afficherClients(filteredClients);
    }

    /**
     * 
     * Initialise le bouton d'ajout de client.
     * 
     * Si l'état d'édition est activé, vérifie si les champs sont valides et demande
     * une confirmation à l'utilisateur avant d'ajouter le client à la base de
     * données.
     * 
     * Si l'état d'édition est désactivé, active l'état d'édition et réinitialise
     * les champs.
     */
    private void setupAddButton() {
        addButton.setOnAction(event -> {
            if (isEditMode) {
                // Vérifier si les champs sont valides
                // Si l'état d'édition est activé, vérifie si les champs sont valides
                // if (!isInputValid()) {
                // return;
                // }

                // Demande une confirmation à l'utilisateur avant d'ajouter le client à la base
                // de données
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Les données du patient sont-ils correctes ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Ajoute le client à la base de données
                    modelClientPane.addClient(createClient());
                    // Met à jour la liste des clients
                    refreshClientList();

                    // Désactive l'état d'édition des champs
                    setFieldsEditable(false);

                    // Réinitialise les champs et désélectionne le client
                    resetFields();

                    // Change l'apparence du bouton
                    addButton.getStyleClass().remove("active");
                    addButton.setText("Ajouter");
                    isEditMode = false;

                } else if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                    // Si l'utilisateur annule, désactive l'état d'édition des champs, réinitialise
                    // les champs et désélectionne le client
                    setFieldsEditable(false);
                    addButton.getStyleClass().remove("active");
                    addButton.setText("Ajouter");
                    isEditMode = false;
                    resetFields();
                    return;
                }
                setFieldsEditable(true);

            } else {
                // Si l'état d'édition est désactivé, active l'état d'édition et réinitialise
                // les champs
                addButton.getStyleClass().add("active");
                addButton.setText("Ajouter");
                isEditMode = true;
                resetFields();
                setFieldsEditable(true);

                // Vider les champs et désélectionner le client
                resetFields();

                // Activer l'état d'édition des champs
                setFieldsEditable(true);
            }
        });
    }

    private boolean isInputValid() {

        boolean isInputValid = false;

        String errorMessage = "";

        if (nomLabel.getText() == null || nomLabel.getText().length() == 0) {
            errorMessage += "Nom invalide!";

        }
        if (prenomLabel.getText() == null || prenomLabel.getText().length() == 0) {
            errorMessage += "Prenom invalide!";

        }
        if (adresseLabel.getText() == null || adresseLabel.getText().length() == 0) {
            errorMessage += "Adresse invalide!";

        }
        if (telephoneLabel.getText() == null || telephoneLabel.getText().length() == 0) {
            errorMessage += "Telephone invalide!";

        }

        if (emailLabel.getText() == null || emailLabel.getText().length() == 0) {
            errorMessage += "Email invalide!";

        }

        if (ssnLabel.getText() == null || ssnLabel.getText().length() == 0) {
            errorMessage += "SSN invalide!";

        }

        if (antecedentsLabel.getText() == null || antecedentsLabel.getText().length() == 0) {
            errorMessage += "Antecedents invalide!";

        }

        if (allergiesLabel.getText() == null || allergiesLabel.getText().length() == 0) {
            errorMessage += "Allergies invalide!";

        }

        if (medicamentsLabel.getText() == null || medicamentsLabel.getText().length() == 0) {
            errorMessage += "Medicaments invalide!";

        }

        if (conditionsLabel.getText() == null || conditionsLabel.getText().length() == 0) {
            errorMessage += "Conditions invalide!";

        }

        return isInputValid;

    }

    /**
     * 
     * Initialise le bouton de modification de client.
     * 
     * Si l'état d'édition est activé, demande une confirmation à l'utilisateur
     * avant de mettre à jour les valeurs du client dans la base de données.
     * 
     * Si l'état d'édition est désactivé, active l'état d'édition et change
     * l'apparence du bouton pour "Enregistrer".
     */
    private void setupEditButton() {
        // Ajouter un événement de clic au bouton
        editButton.setOnAction(event -> {
            if (isEditMode) {
                // Afficher un message de confirmation
                // Si l'état d'édition est activé, demande une confirmation à l'utilisateur
                // avant de mettre à jour les valeurs du client dans la base de données
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Êtes-vous sûr de vouloir enregistrer les modifications ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Mettre à jour les valeurs du client
                    updateClientDetails();

                    // Mettre à jour la liste des clients
                    afficherClients(clients);

                    // Changer l'apparence du bouton
                    editButton.getStyleClass().remove("active");
                    editButton.setText("Modifier");
                    isEditMode = false;
                } else if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                    // Annuler les modifications
                    showClientDetails(client);

                    // Désactiver l'état d'édition des champs
                    setFieldsEditable(false);

                    // Changer l'apparence du bouton
                    editButton.getStyleClass().remove("active");
                    editButton.setText("Modifier");
                    isEditMode = false;
                }
            } else {
                // Activer la modification des champs
                setFieldsEditable(true);

                // Changer l'apparence du bouton avec une animation CSS
                editButton.getStyleClass().add("active");
                editButton.setText("Enregistrer");
                isEditMode = true;
            }
        });
    }

    /**
     * 
     * Set up the delete button to remove a selected client from the database and
     * refresh the client list.
     * 
     * If no client is selected, change the appearance of the button to indicate
     * that it has been clicked.
     */
    private void setupDeleteButton() {
        deleteButton.setOnAction(event -> {
            if (selectedClient != null) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce client ?");
                Optional<ButtonType> result = alert.showAndWait();
                // Changer l'apparence du bouton
                addButton.getStyleClass().remove("active");
                addButton.setText("Supprimé");
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // isEditMode = true;
                    // Vérifier que l'objet Client sélectionné correspond à celui qui est passé à la
                    // méthode deleteClient
                    if (selectedClient.equals(client)) {
                        // Supprimer le client sélectionné de la base de données
                        modelClientPane.deleteClient(client);

                        // Rafraîchir la liste des clients
                        refreshClientList();
                    } else {
                        System.out.println(
                                "L'objet Client sélectionné ne correspond pas à l'objet Client passé à la méthode deleteClient");
                    }
                } else {
                    System.out.println("Aucun client sélectionné");
                }
            } else {
                // Changer l'apparence du bouton
                addButton.getStyleClass().add("active");
                addButton.setText("Supprimé");
                // isEditMode = true;
            }

        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupAddButton();
        setupEditButton();
        setupDeleteButton();
        setFieldsEditable(true);

        // Initialiser la liste des clients à afficher
        clients = modelClientPane.getAllClients();
        afficherClients(clients);

        // Ajouter un écouteur sur la barre de recherche
        clientSearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrerClients(newValue);
        });

        // Initialiser la liste des clients si elle n'a pas déjà été initialisée
        if (clientListView == null) {
            clientListView = new ListView<>();
            vBoxPane.getChildren().add(clientListView);
        }

        // Ajouter un écouteur sur la liste des clients
        clientListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedClientView = createClientView(newValue);
                showClientDetails(newValue);
                highlightSelectedClientView(selectedClientView);
            }
        });

        Platform.runLater(() -> {
            // List<Client> clients = clientDao.lister();
            afficherClients(clients);
            // Activer l'état d'édition des champs

        });
        // throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

}