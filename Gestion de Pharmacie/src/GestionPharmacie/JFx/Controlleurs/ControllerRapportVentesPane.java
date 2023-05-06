package GestionPharmacie.JFx.Controlleurs;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.mvc.View;

import GestionPharmacie.JFx.models.ListeGrpMedicament;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Stop;
import javafx.scene.paint.Color;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.chart.AreaChart;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import eu.hansolo.tilesfx.Tile;

// import jfxtras.labs.map.tile.Tile;

import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.Tile.ChartType;
import eu.hansolo.tilesfx.chart.TilesFXSeries;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;

import GestionPharmacie.JFx.models.Model;

public class ControllerRapportVentesPane implements Initializable {

        private static final double TILE_WIDTH = 526;
        private static final double TILE_HEIGHT = 378;

        @FXML
        private AreaChart<?, ?> AreaChart;

        @FXML
        private View viewPane;

        @FXML
        private MFXTableView<ListeGrpMedicament> listCmd;

        private Tile areaChartTile;

        private void setupTable() {
                MFXTableColumn<ListeGrpMedicament> nameColumn = new MFXTableColumn<>("Nom Groupe", true,
                                Comparator.comparing(ListeGrpMedicament::getName));
                MFXTableColumn<ListeGrpMedicament> surnameColumn = new MFXTableColumn<>("Nombre de Médicament", true,
                                Comparator.comparing(ListeGrpMedicament::getSurname));
                MFXTableColumn<ListeGrpMedicament> ageColumn = new MFXTableColumn<>("Action", true,
                                Comparator.comparing(ListeGrpMedicament::getAge));
                // Declaration de Variable grpMedicament à cette emplacement même.
                // Une variable simple Pour faire fonctionner le code
                // =============================👇🏾 ⚠ C'est different de GrpMedicament⚠
                nameColumn.setRowCellFactory(grpMedicament -> new MFXTableRowCell<>(ListeGrpMedicament::getName));
                surnameColumn.setRowCellFactory(grpMedicament -> new MFXTableRowCell<>(ListeGrpMedicament::getSurname));
                ageColumn.setRowCellFactory(grpMedicament -> new MFXTableRowCell<>(ListeGrpMedicament::getAge) {
                        {
                                setAlignment(Pos.CENTER_RIGHT);
                        }
                });
                ageColumn.setAlignment(Pos.CENTER_RIGHT);

                listCmd.getTableColumns().addAll(nameColumn, surnameColumn, ageColumn);
                listCmd.getFilters().addAll(
                                new StringFilter<>("Name", ListeGrpMedicament::getName),
                                new StringFilter<>("Surname", ListeGrpMedicament::getSurname),
                                new IntegerFilter<>("Age", ListeGrpMedicament::getAge));
                listCmd.setItems(Model.people);
        }

        @Override
        public void initialize(URL arg0, ResourceBundle arg1) {

                setupTable();

                listCmd.autosizeColumnsOnInitialization();

                // XYChart.Series series = new XYChart.Series();
                // series.getData().add(new XYChart.Data("Jan", 23));
                // series.getData().add(new XYChart.Data("Feb", 14));
                // series.getData().add(new XYChart.Data("Mar", 15));
                // series.getData().add(new XYChart.Data("Apr", 24));
                // series.getData().add(new XYChart.Data("May", 34));
                // series.getData().add(new XYChart.Data("Jun", 36));
                // series.getData().add(new XYChart.Data("Jul", 22));
                // series.getData().add(new XYChart.Data("Aug", 45));
                // series.getData().add(new XYChart.Data("Sep", 43));
                // series.getData().add(new XYChart.Data("Oct", 17));
                // series.getData().add(new XYChart.Data("Nov", 29));
                // series.getData().add(new XYChart.Data("Dec", 25));
                // AreaChart.getData().addAll(series);

                // series.getNode().setStyle("-fx-stroke: #fff;");

                // AreaChart Data
                XYChart.Series<String, Number> series1 = new XYChart.Series();
                series1.setName("Whatever");
                series1.getData().add(new XYChart.Data("1 Dec", 16));
                // series1.getData().add(new XYChart.Data("", 20));
                // series1.getData().add(new XYChart.Data("", 25));
                // series1.getData().add(new XYChart.Data("", 22));
                series1.getData().add(new XYChart.Data("8 Dec", 15));
                series1.getData().add(new XYChart.Data("16 Dec", 35));
                // series1.getData().add(new XYChart.Data("", 32));
                // series1.getData().add(new XYChart.Data("", 17));
                // series1.getData().add(new XYChart.Data("", 26));
                series1.getData().add(new XYChart.Data("31 Dec", 30));
                // series1.getData().add(new XYChart.Data("FR", 24));
                // series1.getData().add(new XYChart.Data("SA", 22));
                // series1.getData().add(new XYChart.Data("SU", 20));

                areaChartTile = TileBuilder.create()
                                .skinType(SkinType.SMOOTHED_CHART)
                                // .backgroundColor(Color.web("rgba(255, 255, 255, 1)"))
                                // .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                // use max whith
                                // .maxWidth(Double.MAX_VALUE)
                                // .maxHeight(Double.MAX_VALUE)
                                // .animated(true) // Ne pas activer, ça consomme plus de mémmoir et peut faire
                                // rammer l'appli.
                                .title("Ventes effectués")
                                .chartType(ChartType.AREA)
                                .smoothing(true)
                                .tooltipTimeout(1000)
                                .tilesFxSeries(new TilesFXSeries<>(series1,
                                                Tile.BLUE,
                                                new LinearGradient(0, 0, 0, 1,
                                                                true, CycleMethod.NO_CYCLE,
                                                                new Stop(0, Tile.BLUE),
                                                                new Stop(1, Color.TRANSPARENT))))
                                .build();
                viewPane.setPadding(new Insets(5));
                viewPane.setCenterShape(true);
                viewPane.setBackground(
                                new Background(new BackgroundFill(Color.web("#FFFFFF"), CornerRadii.EMPTY,
                                                Insets.EMPTY)));
                viewPane.setCenter(areaChartTile);
                // View.setAlignment(areaChartTile, Pos.CENTER);
        }

}