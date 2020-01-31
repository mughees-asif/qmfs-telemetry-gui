package sample;

import eu.hansolo.medusa.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import static eu.hansolo.medusa.TickLabelLocation.INSIDE;

public class Main extends Application {
    private Gauge fuelGauge, speedGauge, RPMGauge, tempGauge;
    private FGauge fSpeedGauge;

    private Gauge speedGraph, fuelGraph, RPMGraph, tempGraph;

    @Override
    public void init() {

        /*<--------------------------------------------------> */
        /*<--------------------> Gauges <--------------------> */
        // TODO: Separate into different classes
        /*<--------------------------------------------------> */
        /*<--------------------------------------------------> */

        /*<--------------------> Sections for SpeedGauge <--------------------> */
        Section section1 = SectionBuilder.create()
                .start(30)
                .stop(70)
                .color(Color.rgb(255, 200, 0, 0.7))
                .build();

        Section section2 = SectionBuilder.create()
                .start(70)
                .stop(100)
                .color(Color.rgb(255, 0, 0, 0.7))
                .build();

        /*<--------------------> Markers for SpeedGauge <--------------------> */
        Marker marker1 = MarkerBuilder.create()
                .value(30)
                .text("Marker 1")
                .color(Color.HOTPINK)
                .markerType(Marker.MarkerType.DOT)
                .build();

        Marker marker2 = MarkerBuilder.create()
                .value(70)
                .text("Marker 2")
                .color(Color.CYAN)
                .markerType(Marker.MarkerType.STANDARD)
                .build();

        /*<--------------------> SpeedGauge <--------------------> */
        speedGauge = GaugeBuilder.create()
                .prefSize(125, 175)
                .foregroundBaseColor(Color.WHITE)
                .title("Speedometer")
                .unit("Km/h")
                .decimals(2)
                .lcdVisible(true)
                .lcdDesign(LcdDesign.STANDARD)
                .lcdFont(LcdFont.DIGITAL_BOLD)
                .scaleDirection(Gauge.ScaleDirection.CLOCKWISE)
                .minValue(0)
                .maxValue(100)
                .startAngle(320)
                .angleRange(280)
                .tickLabelDecimals(0)
                .tickLabelLocation(INSIDE)
                .tickLabelOrientation(TickLabelOrientation.ORTHOGONAL)
                .tickLabelSections(section1, section2)
                .tickLabelColor(Color.WHITE)
                .tickMarkSectionsVisible(false)
                .tickMarkSections(section1, section2)
                .majorTickMarksVisible(true)
                .majorTickMarkType(TickMarkType.TRAPEZOID)
                .mediumTickMarksVisible(false)
                .mediumTickMarkType(TickMarkType.LINE)
                .minorTickMarksVisible(true)
                .minorTickMarkType(TickMarkType.LINE)
                .ledVisible(false)
                .ledType(Gauge.LedType.STANDARD)
                .ledColor(Color.rgb(255, 200, 0))
                .ledBlinking(false)
                .needleShape(Gauge.NeedleShape.ANGLED)
                .needleSize(Gauge.NeedleSize.STANDARD)
                .needleColor(Color.CRIMSON)
                .startFromZero(false)
                .returnToZero(false)
                .knobType(Gauge.KnobType.METAL)
                .knobColor(Color.LIGHTGRAY)
                .interactive(true)
                .thresholdVisible(true)
                .threshold(50)
                .thresholdColor(Color.RED)
                .checkThreshold(true)
                .gradientBarEnabled(true)
                .gradientBarStops(new Stop(0.0, Color.BLUE),
                        new Stop(0.25, Color.CYAN),
                        new Stop(0.5, Color.LIME),
                        new Stop(0.75, Color.YELLOW),
                        new Stop(1.0, Color.RED))
                .markersVisible(true)
                .markers(marker1, marker2)
                .animated(true)
                .animationDuration(500)
                .build();

        /*<--------------------> FGauge Skin for SpeedGauge <--------------------> */
        fSpeedGauge = FGaugeBuilder
                .create()
                .prefSize(125, 175)
                .gauge(speedGauge)
                .gaugeDesign(GaugeDesign.METAL)
                .gaugeBackground(GaugeDesign.GaugeBackground.CARBON)
                .foregroundVisible(true)
                .build();

        /*<--------------------> RPM Gauge <--------------------> */
        RPMGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SIMPLE)
                .subTitle("\tx1000 rev/min")
                .prefSize(125,175)
                .sections(new Section(0, 8, "0", Color.web("#FFFFFF")),
                        new Section(8, 10, "1", Color.web("#FFCCCC")),
                        new Section(10, 12, "2", Color.web("#FF9999")),
                        new Section(12, 13, "3", Color.web("#FF6666")),
                        new Section(13, 14, "4", Color.web("#FF3333")),
                        new Section(14, 16, "5", Color.web("#FF0000")))
                .sectionsVisible(true)
                .sectionIconsVisible(true)
                .maxValue(16)
                .title("RPM")
                .threshold(16)
                .animated(true)
                .build();



        /*<--------------------> Fuel Gauge <--------------------> */
        fuelGauge = GaugeBuilder.create()
                .title("Fuel")
                .skinType(Gauge.SkinType.HORIZONTAL)
                .prefSize(150, 200)
                .foregroundBaseColor(Color.WHITESMOKE)
                .animated(true)
                .shadowsEnabled(true)
                .valueVisible(false)
                .needleColor(Color.rgb(255, 10, 1))
                .needleShape(Gauge.NeedleShape.ANGLED)
                .needleSize(Gauge.NeedleSize.THICK)
                .minorTickMarksVisible(false)
                .mediumTickMarksVisible(false)
                .sectionsVisible(true)
                .sections(new Section(0, 2, Color.rgb(255, 10, 1)))
                .minValue(0)
                .maxValue(10)
                .angleRange(90)
                .customTickLabelsEnabled(true)
                .customTickLabels("Empty", "", "", "", "", "1/2", "", "", "", "", "Full")
                .build();

        /*<--------------------> Temperature Gauge <--------------------> */
         tempGauge = GaugeBuilder.create()
                 .skinType(Gauge.SkinType.LCD)
                 .prefSize(15,5)
                 .animated(true)
                 .title("Temperature")
                 .subTitle("Engine")
                 .unit("\u00B0C")
                 .lcdDesign(LcdDesign.GREEN_BLACK)
                 .thresholdVisible(true)
                 .threshold(50)
                 .build();

        /*<------------------------------------------------------------> */
        /*<--------------------> Analysing Graphs <--------------------> */
        // TODO: Separate into different classes
        /*<------------------------------------------------------------> */
        /*<------------------------------------------------------------> */

        /*<--------------------> Speed graph <--------------------> */
        speedGraph = GaugeBuilder.create()
                .skinType(Gauge.SkinType.TILE_SPARK_LINE)
                .foregroundBaseColor(Color.WHITE)
                .animated(true)
                .maxValue(100)
                .minValue(0)
                .threshold(50)
                .title("Speed")
                .build();

        /*<--------------------> Fuel Graph <--------------------> */
        fuelGraph = GaugeBuilder.create()
                .skinType(Gauge.SkinType.TILE_SPARK_LINE)
                .foregroundBaseColor(Color.WHITE)
                .needleColor(Color.RED)
                .animated(true)
                .threshold(75)
                .foregroundBaseColor(Color.BLACK)
                .backgroundPaint(Color.LIGHTBLUE)
                .title("Fuel")
                .build();

        /*<--------------------> RPM Graph <--------------------> */
        RPMGraph = GaugeBuilder.create()
                .skinType(Gauge.SkinType.TILE_SPARK_LINE)
                .foregroundBaseColor(Color.WHITE)
                .needleColor(Color.RED)
                .animated(true)
                .threshold(75)
                .title("RPM")
                .build();

        /*<--------------------> Temperature Graph <--------------------> */
        tempGraph = GaugeBuilder.create()
                .skinType(Gauge.SkinType.TILE_SPARK_LINE)
                .foregroundBaseColor(Color.WHITE)
                .needleColor(Color.RED)
                .animated(true)
                .threshold(75)
                .foregroundBaseColor(Color.BLACK)
                .backgroundPaint(Color.LIGHTBLUE)
                .title("Temperature")
                .build();

    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        /*<--------------------> Gauges - HBox <--------------------> */
        HBox hBoxGauges = new HBox(15);

        Image image = new Image(new FileInputStream("C:/Users/fluxw/OneDrive/Pictures/QMFSLogo.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setX(50);
        imageView.setY(50);
        imageView.setFitHeight(250);
        imageView.setFitWidth(250);
        imageView.setPreserveRatio(true);

        VBox vBoxImageFuel = new VBox(75);
        vBoxImageFuel.getChildren().addAll(imageView, fuelGauge);
        vBoxImageFuel.setPadding(new Insets(0, 20, 0, 20));

        hBoxGauges.getChildren().addAll(vBoxImageFuel, fSpeedGauge, RPMGauge, tempGauge);
        hBoxGauges.setPadding(new Insets(0, 20, 0, 20));
        HBox.setHgrow(fSpeedGauge, Priority.ALWAYS);
        HBox.setHgrow(fuelGauge, Priority.ALWAYS);
        HBox.setHgrow(tempGauge, Priority.ALWAYS);
        HBox.setHgrow(RPMGauge, Priority.ALWAYS);
        hBoxGauges.setAlignment(Pos.CENTER);

        /*<--------------------> Read Button - HBox <--------------------> */
        HBox hBoxButton = new HBox(15);

        Button readButton = new Button("Read");
        readButton.addEventHandler(ActionEvent.ACTION, (event) -> {
            InfiniteGaugeData randomData = new InfiniteGaugeData(
                    fuelGauge, speedGauge, RPMGauge, tempGauge,
                    fuelGraph, speedGraph, RPMGraph, tempGraph);
            randomData.start();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    randomData.run();
                }
            }, 0, 1000);
        });

        Button exitButton = new Button("Exit");
        exitButton.addEventHandler(ActionEvent.ACTION, (event) -> {
            stop();
        });

        readButton.getStyleClass().add("buttons");
        exitButton.getStyleClass().add("buttons");
        hBoxButton.setPadding(new Insets(20, 20, 0, 0));
        HBox.setHgrow(hBoxButton, Priority.ALWAYS);

        hBoxButton.getChildren().addAll(readButton, exitButton);
        hBoxButton.setAlignment(Pos.CENTER_RIGHT);

        /*<--------------------> Analysing graphs - StackPane <--------------------> */
        // TODO: will be bottom of the BorderPane
        /*<------------------------------------------------------------------------> */
        VBox bottomVBox = new VBox(10);

        HBox analysingGraphsHBox = new HBox(110);
        analysingGraphsHBox.getChildren().addAll(fuelGraph, speedGraph, RPMGraph, tempGraph);
        analysingGraphsHBox.setAlignment(Pos.CENTER);

        bottomVBox.getChildren().addAll(analysingGraphsHBox);
        analysingGraphsHBox.setPadding(new Insets(20,20,20,20));
        analysingGraphsHBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(fuelGraph, Priority.ALWAYS);
        HBox.setHgrow(speedGraph, Priority.ALWAYS);
        HBox.setHgrow(RPMGraph, Priority.ALWAYS);
        HBox.setHgrow(tempGraph, Priority.ALWAYS);

        /*<--------------------> Main layout - BorderPane <--------------------> */
        BorderPane borderPane = new BorderPane();

        borderPane.setTop(hBoxButton);
        borderPane.setCenter(hBoxGauges);
        borderPane.setBottom(bottomVBox);
        borderPane.getStyleClass().add("borderpane");

        /*<--------------------> Main Scene <--------------------> */
        // TODO: fix main icon
        /*<------------------------------------------------------> */
        Scene scene = new Scene(borderPane, 1500, 750);
        scene.getStylesheets().add("sample/styles.css");
        stage.setTitle("Data Viewer - Queen Mary Formula Student\u00a9");
//        stage.getIcons().add(new Image("C:/Users/fluxw/OneDrive/Pictures/QMFSLogo.jpg"));
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        launch(args);
    }
}