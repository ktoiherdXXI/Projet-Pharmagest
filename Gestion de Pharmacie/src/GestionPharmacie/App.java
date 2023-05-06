package GestionPharmacie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import java.util.Scanner;

import GestionPharmacie.BDPM.Cis_presentation_bdpm;

public class App extends Application {

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, IOException {

        // insertUsersDataByConsol();
        // insertUsersDataByFile();
        launch(args);

    }

    public static void insertUsersDataByConsol() throws SQLException {
        boolean willContinue = true;

        // start of the program
        System.out.println("Voici un exemple :");
        while (willContinue) {
            System.out.println(
                    "Que voulais vous faire? (Appuyer A => Ajouter, U => update, D=>delete, L => Users-List, Q=>quit");
            char choice = scanner.nextLine().charAt(0);

            // choose mode
            CRUDMode mode = choice == 'A' ? CRUDMode.ADD
                    : choice == 'U' ? CRUDMode.UPDATE
                            : choice == 'D' ? CRUDMode.DELETE
                                    : choice == 'Q' ? CRUDMode.QUIT
                                            : choice == 'L' ? CRUDMode.LISTALLSTUDENTS : CRUDMode.INVALID;

            switch (mode) {
                case ADD:
                    System.out.println("Add!");
                    System.out.println("Mon choix est " + CRUDMode.ADD.CRUDModeNme);
                    System.out.println("Le numero d'énumération est  " + CRUDMode.ADD.ordinal());

                    System.out.println("Le Type choisie = est : " + CRUDMode.ADD.valueOf("ADD"));

                    DBUtil.addPharmacien(retrieveInput(mode));

                    break;
                case UPDATE:
                    System.out.println("Update!");
                    DBUtil.updatePharmacien(retrieveInput(mode));
                    break;
                case DELETE:
                    System.out.println("Delete!");
                    DBUtil.deletePharmacien(retrieveInput(mode));
                    break;
                case QUIT:
                    System.out.println("GoodBye!");
                    willContinue = false;
                    break;
                case LISTALLSTUDENTS:
                    System.out.println("List of Students:");
                    System.out.println(DBUtil.getAllPharmacien());
                    break;
                case INVALID:
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }

    private static Pharmacien retrieveInput(CRUDMode mode) {
        Pharmacien pharmacien = new Pharmacien();

        System.out.println("Fill up details:");

        /*
         * This is redundant. Is there a better way of doing this?
         * Please refactor the way input is being done
         * Leave the return statement as is
         */
        if (mode.equals(CRUDMode.ADD) || mode.equals(CRUDMode.UPDATE)) {

            if (mode.equals(CRUDMode.UPDATE)) {
                System.out.println("Id:");
                pharmacien.setIdPharmacien(scanner.nextInt());
            }
            // System.out.println("Id:");
            // pharmacien.setIdPharmacien(scanner.nextInt());

            System.out.println("Nom:");
            pharmacien.setNom(scanner.nextLine());

            System.out.println("Prénom:");
            pharmacien.setPrenom(scanner.nextLine());

            System.out.println("Fonction:");
            pharmacien.setFonction(scanner.nextLine());

            System.out.println("Adresse:");
            pharmacien.setAdresse(scanner.nextLine());

            System.out.println("Telephone:");
            pharmacien.setTelephone(scanner.nextLine());

            System.out.println("Mail:");
            pharmacien.setMail(scanner.nextLine());

            System.out.println("Password:");
            pharmacien.setPassword(scanner.nextLine());

        } else if (mode.equals(CRUDMode.DELETE)) {
            System.out.println("Id:");
            pharmacien.setIdPharmacien(scanner.nextInt());
        }
        return pharmacien;
    }

    // inserer chaque ligne d'un fichier txt contenant les donnés pour remplir une
    // table séparés par une Tabulation

    // public static void insertUsersDataByFile() throws IOException, SQLException {
    // File file = new File(

    // "C:\\Users\\El Nadje\\OneDrive\\Documents\\BTS
    // SIO\\2eme\\Back\\WorkSpace\\App-pharmaGest\\Gestion de
    // Pharmacie\\src\\Ressources\\generiques-fichiers-2022-11\\fic02grp.txt");
    // // fic01den.txt"); // Fichier 1 : Médicament Name ☝🏿
    // BufferedReader br = new BufferedReader(new FileReader(file));
    // String st;
    // while ((st = br.readLine()) != null) {
    // String[] data = st.split("\t");
    // Cis_cip_bdpm cis_cip_bdpm = new Cis_cip_bdpm();
    // cis_cip_bdpm.setId(Integer.parseInt(data[0]));
    // cis_cip_bdpm.setLibeleCommune(data[1]);

    // DBUtil.addCis_cip_bdpm(cis_cip_bdpm);
    // }
    // br.close();
    // }

    // public static void insertUsersDataByFile() throws IOException, SQLException {
    // File file = new File(
    // "C:\\Users\\El Nadje\\OneDrive\\Documents\\BTS
    // SIO\\2eme\\Back\\WorkSpace\\App-pharmaGest\\Gestion de
    // Pharmacie\\src\\Ressources\\generiques-fichiers-2022-11\\fic02grp.txt");
    // // fic01den.txt"); // Fichier 1 : Médicament Name ☝🏿
    // BufferedReader br = new BufferedReader(new FileReader(file));
    // String st;
    // while ((st = br.readLine()) != null) {

    // String[] data = st.split("\t");

    // Cis_grp_bdpm cis_grp_bdpm = new Cis_grp_bdpm();

    // cis_grp_bdpm.setId_cis(Integer.parseInt(data[0]));
    // cis_grp_bdpm.setId_cis_grp(Integer.parseInt(data[1]));
    // cis_grp_bdpm.setVoie_administration(data[2]);
    // cis_grp_bdpm.setLibele_grp_gen(data[3]);

    // DBUtil.addCis_grp_bdpm(cis_grp_bdpm);
    // }
    // br.close();
    // }

    // public static void insertUsersDataByFile() throws IOException, SQLException {
    // File file = new File(
    // "C:\\Users\\El Nadje\\OneDrive\\Documents\\BTS
    // SIO\\2eme\\Back\\WorkSpace\\App-pharmaGest\\Gestion de
    // Pharmacie\\src\\Ressources\\generiques-fichiers-2022-11\\fic03spe.txt");
    // BufferedReader br = new BufferedReader(new FileReader(file));
    // String st;
    // while ((st = br.readLine()) != null) {

    // String[] data = st.split("\t");

    // Cis_Pharm_bdpm cis_pharm_bdpm = new Cis_Pharm_bdpm();

    // cis_pharm_bdpm.setId_cis_grp(Integer.parseInt(data[0]));

    // cis_pharm_bdpm.setCode_cis(Integer.parseInt(data[1]));

    // cis_pharm_bdpm.setStatus_Specialite(data[2].charAt(0));

    // cis_pharm_bdpm.setLibelePharmaceutique(data[3]);

    // cis_pharm_bdpm.setMention_Change(data[4]);

    // DBUtil.addCis_pharm_bdpm(cis_pharm_bdpm);
    // }
    // br.close();
    // }

    // public static void insertUsersDataByFile() throws IOException, SQLException {
    // File file = new File(
    // "C:\\Users\\El Nadje\\OneDrive\\Documents\\BTS
    // SIO\\2eme\\Back\\WorkSpace\\App-pharmaGest\\Gestion de
    // Pharmacie\\src\\Ressources\\generiques-fichiers-2022-11\\fic04sub.txt");
    // BufferedReader br = new BufferedReader(new FileReader(file));
    // String st;
    // while ((st = br.readLine()) != null) {

    // String[] data = st.split("\t");

    // Cis_complement_bdpm cis_complement_bdpm = new Cis_complement_bdpm();

    // cis_complement_bdpm.setCode_cis(Integer.parseInt(data[0]));

    // cis_complement_bdpm.setCode_cis_sub(Integer.parseInt(data[1]));

    // DBUtil.addCis_complement_bdpm(cis_complement_bdpm);
    // }
    // br.close();
    // }

    public static void insertUsersDataByFile() throws IOException, SQLException {
        File file = new File(
                "C:\\Users\\El Nadje\\OneDrive\\Documents\\BTS SIO\\2eme\\Back\\WorkSpace\\App-pharmaGest\\Gestion de Pharmacie\\src\\Ressources\\generiques-fichiers-2022-11\\fic05cip.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {

            String[] data = st.split("\t");

            Cis_presentation_bdpm cis_presentation_bdpm = new Cis_presentation_bdpm();

            cis_presentation_bdpm.setCode_cis_pharm(Integer.parseInt(data[0]));

            cis_presentation_bdpm.setCode_cip((data[1]));

            cis_presentation_bdpm.setNo_used(data[2]);

            cis_presentation_bdpm.setLibele_presentation(data[3]);

            DBUtil.addCis_presentation_bdpm(cis_presentation_bdpm);
        }
        br.close();
    }

    // private static Stage stg;
    @Override
    public void start(Stage connexionPage) throws Exception {
        // stg = connexionPage;
        Parent authentification = FXMLLoader.load(
                // // getClassLoader().
                getClass().getResource(
                        //"./FxmlVue/DashboardFrame.fxml"));
                        "./FxmlVue/login.fxml"));
        // "./FxmlVue/dashboardPane.fxml"));
        // "./FxmlVue/Containers/rapportVentePane.fxml")); // À intégrer !!!!!
        // "./FxmlVue/Containers/listeGroupMedicamentPane.fxml")); // A intégrer !!!!!
        // "./FxmlVue/Containers/detailMedicamentPane.fxml")); // À intégrer !!!!!

        // "./FxmlVue/Containers/listeMedicamentPane.fxml"));
        // "./FxmlVue/Containers/ajoutMedicamentPane.fxml"));
        // "./FxmlVue/Containers/inventairePane.fxml"));
        // "./FxmlVue/Containers/configPane.fxml"));
        // "./FxmlVue/NewLogin.fxml"));
        // "./FxmlVue/Containers/rapportPane.fxml"));
        // "./FxmlVue/Containers/groupMedicamentPane.fxml"));
        // "ConnectFrame.fxml"));
        Scene scene = new Scene(authentification);
        connexionPage.setTitle("PharmaGest ⚕ Votre Pharmacie");
        connexionPage.setScene(scene);
        connexionPage.setResizable(true);
        connexionPage.centerOnScreen();
        connexionPage.show();
    }

    // public void changeScene(String fxml) throws IOException {
    // Parent pane = FXMLLoader.load(getClass().getResource(fxml));
    // // stg.getScene().setRoot(pane);

    // }

}

// @Override
// double X, Y = 8;
// public void start(Stage dashboardStage) throws Exception {
// Parent DashboardFrame = FXMLLoader.load(
// // getClassLoader().
// getClass().getResource(
// "DashboardFrame.fxml"));
// // dashboardStage.initStyle(StageStyle.UNDECORATED);
// // DashboardFrame.setOnMousePressed(Event -> {
// Scene scene = new Scene(DashboardFrame);
// dashboardStage.setTitle("Dashboard Pharmacy");
// dashboardStage.setScene(scene);
// dashboardStage.setResizable(false);
// dashboardStage.centerOnScreen();
// dashboardStage.show();
// };
// X = Event.getSceneX();
// Y = Event.getSceneY();
// DashboardFrame.setOnMouseDragged(Event -> {
// dashboardStage.setX(Event.getScreenX() - X);
// dashboardStage.setX(Event.getScreenY() - Y);
// });
// public void start(Stage RegisterPage) throws Exception {
// Parent RegisterFrame = FXMLLoader.load(
// // getClassLoader().
// getClass().getResource(
// "DashboardFrame.fxml"));
// Scene scene = new Scene(RegisterFrame);
// RegisterPage.setTitle("Dashboard Pharmacy "); /* Connexion / Inscription */
// RegisterPage.setScene(scene);
// RegisterPage.setResizable(false);
// RegisterPage.centerOnScreen();
// RegisterPage.show();
// };.
