package sample;

import eu.hansolo.medusa.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

import static eu.hansolo.medusa.TickLabelLocation.INSIDE;

public class Main extends Application {
    private Gauge fuelGauge, speedGauge, RPMGauge, tempGauge;
    private FGauge fSpeedGauge;

    @Override
    public void init() {

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
                .prefSize(400, 400)
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
                .prefSize(400, 400)
                .gauge(speedGauge)
                .gaugeDesign(GaugeDesign.METAL)
                .gaugeBackground(GaugeDesign.GaugeBackground.CARBON)
                .foregroundVisible(true)
                .build();

        /*<--------------------> RPM Gauge <--------------------> */
        RPMGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SIMPLE_DIGITAL)
                .prefSize(350, 350)
                .title("RPM")
                .unit("x1000 rev/min")
                .animated(true)
                .minValue(0)
                .maxValue(16)
                .thresholdVisible(true)
                .threshold(11)
                .sections(new Section(9, 12))
                .gradientBarEnabled(true)
                .gradientBarStops(new Stop(0.0, Color.BLUE),
                        new Stop(0.25, Color.CYAN),
                        new Stop(0.5, Color.LIME),
                        new Stop(0.75, Color.YELLOW),
                        new Stop(1.0, Color.RED))
                .animated(true)
                .animationDuration(500)
                .build();

        /*<--------------------> Fuel Gauge <--------------------> */
        fuelGauge = GaugeBuilder.create()
                .title("Fuel")
                .skinType(Gauge.SkinType.HORIZONTAL)
                .prefSize(300, 250)
                .knobColor(Color.rgb(0, 0, 0))
                .foregroundBaseColor(Color.BLACK)
                .animated(true)
                .shadowsEnabled(true)
                .valueVisible(false)
                .needleColor(Color.rgb(255, 10, 1))
                .needleShape(Gauge.NeedleShape.ROUND)
                .needleSize(Gauge.NeedleSize.THICK)
                .minorTickMarksVisible(false)
                .mediumTickMarksVisible(false)
                .sectionsVisible(true)
                .sections(new Section(0, 0.2, Color.rgb(255, 10, 1)))
                .minValue(0)
                .maxValue(10)
                .angleRange(90)
                .customTickLabelsEnabled(true)
                .customTickLabels("Empty", "", "", "", "", "1/2", "", "", "", "", "Full")
                .build();

        /*<--------------------> Temperature Gauge <--------------------> */
         tempGauge = GaugeBuilder.create()
                 .skinType(Gauge.SkinType.LCD)
                 .animated(true)
                 .title("Temperature")
                 .subTitle("Engine")
                 .unit("\u00B0C")
                 .lcdDesign(LcdDesign.BLACK_RED)
                 .thresholdVisible(true)
                 .threshold(50)
                 .build();
    }

    @Override
    public void start(Stage stage) {
        /*<--------------------> Gauges - HBox <--------------------> */
        HBox hBoxGauges = new HBox(15);

        hBoxGauges.getChildren().addAll(fuelGauge, fSpeedGauge, RPMGauge, tempGauge);
        hBoxGauges.setPadding(new Insets(5, 5, 5, 0));

        HBox.setHgrow(fSpeedGauge, Priority.ALWAYS);
        HBox.setHgrow(fuelGauge, Priority.ALWAYS);
        HBox.setHgrow(tempGauge, Priority.ALWAYS);
        HBox.setHgrow(RPMGauge, Priority.ALWAYS);
        hBoxGauges.setAlignment(Pos.CENTER);

        /*<--------------------> Test Button - HBox <--------------------> */
        // TODO: use generateRandom function to randomly keep generating 0 to a 100
        /*<------------------------------------------------------------> */
        HBox hBoxTestButtons = new HBox(10);

        Button testButton = new Button("Test");
        testButton.addEventHandler(ActionEvent.ACTION, (event) -> {
            InfiniteGaugeData randomData = new InfiniteGaugeData(fuelGauge, speedGauge, RPMGauge, tempGauge);
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

        hBoxTestButtons.setPadding(new Insets(10, 10, 10, 10));
        hBoxTestButtons.getChildren().addAll(testButton, exitButton);

        /*<--------------------> Parameters displayed in text format - VBox <--------------------> */
        // TODO: will be bottom of the BorderPane
        // TODO: Three or more fields showing the GUI parameters in text
        /*<------------------------------------------------------------> */
        VBox vBoxParameterTextDisplay = new VBox();


        /*<--------------------> Analysing graphs + Switch button - StackPane <--------------------> */
        // TODO: will be right of the BorderPane
        // TODO: Constantly updating graphs to allow analysing of the data
        // TODO: Implement a checkbox to change between gauges and graphs
        /*<------------------------------------------------------------> */
        StackPane paneAnalysingGraphs = new StackPane();

        HBox switchGraphButton = new HBox(10);

        CheckBox switchCheckBox = new CheckBox("Graphs On/Off");
        switchGraphButton.getChildren().addAll(switchCheckBox);
        switchGraphButton.setAlignment(Pos.CENTER);
        HBox.setHgrow(switchCheckBox, Priority.ALWAYS);
        switchGraphButton.setPadding(new Insets(10,10,10,10));

        /*<--------------------> Main layout - BorderPane <--------------------> */
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBoxTestButtons);
        borderPane.setCenter(hBoxGauges);
        borderPane.setRight(switchCheckBox);
        borderPane.setBottom(vBoxParameterTextDisplay);

        /*<--------------------> Main Scene <--------------------> */
        Scene scene = new Scene(borderPane);
        stage.setTitle("Telemetry System - QMFS");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        setUserAgentStylesheet(STYLESHEET_MODENA);
        launch(args);
    }
}