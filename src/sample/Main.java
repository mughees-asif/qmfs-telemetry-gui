package sample;

import eu.hansolo.medusa.*;
import eu.hansolo.medusa.Gauge.*;
import eu.hansolo.medusa.GaugeDesign.GaugeBackground;
import eu.hansolo.medusa.Marker.MarkerType;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.util.Random;
import java.util.stream.IntStream;

public class Main extends Application {
    private static final Random RND = new Random();
    private Gauge fuelGauge, speedGauge, RPMGauge, tempGauge;
    private FGauge fSpeedGauge;

    @Override
    public void init() {
        /*<--------------------> Sections for SpeedGauge <--------------------> */
        Section section1 = SectionBuilder.create()
                .start(50)
                .stop(75)
                .color(Color.rgb(255, 200, 0, 0.7))
                .onSectionEntered(sectionEvent -> System.out.println("Entered Section 1"))
                .onSectionLeft(sectionEvent -> System.out.println("Left Section 1"))
                .build();

        Section section2 = SectionBuilder.create()
                .start(75)
                .stop(100)
                .color(Color.rgb(255, 0, 0, 0.7))
                .onSectionEntered(sectionEvent -> System.out.println("Entered Section 2"))
                .onSectionLeft(sectionEvent -> System.out.println("Left Section 2"))
                .build();

        /*<--------------------> Markers for SpeedGauge <--------------------> */
        Marker marker1 = MarkerBuilder.create()
                .value(25)
                .text("Marker 1")
                .color(Color.HOTPINK)
                .markerType(MarkerType.DOT)
                .onMarkerPressed(markerEvent -> System.out.println("Marker 1 pressed"))
                .onMarkerReleased(markerEvent -> System.out.println("Marker 1 released"))
                .build();

        Marker marker2 = MarkerBuilder.create()
                .value(75)
                .text("Marker 2")
                .color(Color.CYAN)
                .markerType(MarkerType.STANDARD)
                .onMarkerPressed(markerEvent -> System.out.println("Marker 2 pressed"))
                .onMarkerReleased(markerEvent -> System.out.println("Marker 2 released"))
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
                .scaleDirection(ScaleDirection.CLOCKWISE)
                .minValue(0)
                .maxValue(100)
                .startAngle(320)
                .angleRange(280)
                .tickLabelDecimals(0)
                .tickLabelLocation(TickLabelLocation.INSIDE)
                .tickLabelOrientation(TickLabelOrientation.ORTHOGONAL)
                .onlyFirstAndLastTickLabelVisible(false)
                .tickLabelSectionsVisible(false)
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
                .ledType(LedType.STANDARD)
                .ledColor(Color.rgb(255, 200, 0))
                .ledBlinking(false)
                .needleShape(NeedleShape.ANGLED)
                .needleSize(NeedleSize.STANDARD)
                .needleColor(Color.CRIMSON)
                .startFromZero(false)
                .returnToZero(false)
                .knobType(KnobType.METAL)
                .knobColor(Color.LIGHTGRAY)
                .interactive(true)
                .onButtonPressed(buttonEvent -> System.out.println("Knob pressed"))
                .onButtonReleased(buttonEvent -> System.out.println("Knob released"))
                .thresholdVisible(true)
                .threshold(50)
                .thresholdColor(Color.RED)
                .checkThreshold(true)
                .onThresholdExceeded(thresholdEvent -> System.out.println("Threshold exceeded"))
                .onThresholdUnderrun(thresholdEvent -> System.out.println("Threshold underrun"))
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
                .gaugeBackground(GaugeBackground.CARBON)
                .foregroundVisible(true)
                .build();

        /*<--------------------> RPM Gauge <--------------------> */
        RPMGauge = GaugeBuilder.create()
                .skinType(SkinType.SIMPLE_DIGITAL)
                .prefSize(350, 350)
                .title("RPM")
                .unit("x1000 rev/min")
                .animated(true)
                .minValue(0)
                .maxValue(8)
                .thresholdVisible(true)
                .threshold(5.50)
                .sections(new Section(4.5, 6))
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
                .skinType(SkinType.HORIZONTAL)
                .prefSize(300, 250)
                .knobColor(Color.rgb(0, 0, 0))
                .foregroundBaseColor(Color.BLACK)
                .animated(true)
                .shadowsEnabled(true)
                .valueVisible(false)
                .needleColor(Color.rgb(255, 10, 1))
                .needleShape(NeedleShape.ROUND)
                .needleSize(NeedleSize.THICK)
                .minorTickMarksVisible(false)
                .mediumTickMarksVisible(false)
                .sectionsVisible(true)
                .sections(new Section(0, 0.2, Color.rgb(255, 10, 1)))
                .minValue(0)
                .maxValue(1)
                .angleRange(90)
                .customTickLabelsEnabled(true)
                .customTickLabels("Empty", "", "", "", "", "1/2", "", "", "", "", "Full")
                .build();

        /*<--------------------> Temperature Gauge <--------------------> */
         tempGauge = GaugeBuilder.create()
                 .skinType(SkinType.LCD)
                 .animated(true)
                 .title("Temperature")
                 .subTitle("Engine")
                 .unit("\u00B0C")
                 .lcdDesign(LcdDesign.BLACK_RED)
                 .thresholdVisible(true)
                 .threshold(50)
                 .build();
    }

    /*<--------------------> Generate random number from 0 to a 100 - for unit testing - *PROBLEM* <--------------------> */
    private void generateRandom() {
        long start_time = System.currentTimeMillis();
        long wait_time = 2000;
        long end_time = start_time + wait_time;

        while (System.currentTimeMillis() < end_time) {
            IntStream rand = new Random().ints(1, 0, 101);
            System.out.println(rand);
        }
    }

    @Override
    public void start(Stage stage) {
        /*<--------------------> Gauges - HBox <--------------------> */
        HBox hBoxGauges = new HBox();

        hBoxGauges.getChildren().addAll(fuelGauge, fSpeedGauge, RPMGauge, tempGauge);
        hBoxGauges.setPadding(new Insets(15, 12, 15, 0));
        hBoxGauges.setSpacing(10);

        HBox.setHgrow(fSpeedGauge, Priority.ALWAYS);
        HBox.setHgrow(fuelGauge, Priority.ALWAYS);
        HBox.setHgrow(tempGauge, Priority.ALWAYS);
        HBox.setHgrow(RPMGauge, Priority.ALWAYS);
        hBoxGauges.setAlignment(Pos.CENTER);

        /*<--------------------> Test Buttons - HBox <--------------------> */
        // clean up repeated code
        // use generateRandom function to randomly keep generating 0 to a 100 values
        /*<------------------------------------------------------------> */
        HBox hBoxTestButtons = new HBox();

        Button testButton = new Button("Testing");
        testButton.addEventHandler(ActionEvent.ACTION,
                (event) -> speedGauge.setValue(
                        RND.nextDouble() * speedGauge.getRange() + speedGauge.getMinValue()));
        testButton.addEventHandler(ActionEvent.ACTION,
                (event) -> fuelGauge.setValue(
                        RND.nextDouble() * fuelGauge.getRange() + fuelGauge.getMinValue()));
        testButton.addEventHandler(ActionEvent.ACTION,
                (event) -> tempGauge.setValue(
                        RND.nextDouble() * tempGauge.getRange() + tempGauge.getMinValue()));
        testButton.addEventHandler(ActionEvent.ACTION,
                (event) -> RPMGauge.setValue(
                        RND.nextDouble() * RPMGauge.getRange() + RPMGauge.getMinValue()));

        Button cancelButton = new Button("Cancel");
        cancelButton.addEventHandler(ActionEvent.ACTION, (event) -> speedGauge.setValue(0));
        cancelButton.addEventHandler(ActionEvent.ACTION, (event) -> fuelGauge.setValue(0));
        cancelButton.addEventHandler(ActionEvent.ACTION, (event) -> tempGauge.setValue(0));
        cancelButton.addEventHandler(ActionEvent.ACTION, (event) -> RPMGauge.setValue(0));

        hBoxTestButtons.setPadding(new Insets(10, 10, 10, 10));
        hBoxTestButtons.setSpacing(10);
        hBoxTestButtons.getChildren().addAll(testButton, cancelButton);

        /*<--------------------> Parameters displayed in text format - VBox <--------------------> */
        // yet to be completed
        // will be bottom of the BorderPane
        // Three or more fields showing the GUI parameters in text
        /*<------------------------------------------------------------> */
        VBox vBoxParameterTextDisplay = new VBox();

        /*<--------------------> Analysing graph - StackPane <--------------------> */
        // yet to be completed
        // will be right of the BorderPane
        // Constantly updating graph to allow analysing of the data
        /*<------------------------------------------------------------> */
        StackPane paneAnalysingGraph = new StackPane();

        /*<--------------------> Main layout - BorderPane <--------------------> */
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBoxTestButtons);
        borderPane.setCenter(hBoxGauges);
        borderPane.setRight(paneAnalysingGraph);
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