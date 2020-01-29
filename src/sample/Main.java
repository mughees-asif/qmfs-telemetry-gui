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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {
    private static final Random RND = new Random();
    private Gauge fuelGauge, speedGauge, RPMGauge, tempGauge;
    private FGauge fSpeedGauge;

    @Override
    public void init() {

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

        // RPM Gauge parameters
        speedGauge = GaugeBuilder.create()
                .prefSize(400, 400)                                                                  // Set the preferred size of the control
                // Related to Foreground Elements
                .foregroundBaseColor(Color.WHITE)                                                   // Defines a color for title, subtitle, unit, value, tick label, tick mark, major tick mark, medium tick mark and minor tick mark
                // Related to Title Text
                .title("Speedometer")                                                                     // Set the text for the title
                // Related to Unit Text
                .unit("Km/h")                                                                       // Set the text for the unit
                // Related to Value Text
                .decimals(2)                                                                        // Set the number of decimals for the value/lcd text
                // Related to LCD
                .lcdVisible(true)                                                                   // Display a LCD instead of the plain value text
                .lcdDesign(LcdDesign.STANDARD)                                                      // Set the design for the LCD
                .lcdFont(LcdFont.DIGITAL_BOLD)                                                      // Set the font for the LCD (STANDARD, LCD, SLIM, DIGITAL_BOLD, ELEKTRA)
                // Related to scale
                .scaleDirection(ScaleDirection.CLOCKWISE)                                           // Define the direction of the Scale (CLOCKWISE, COUNTER_CLOCKWISE)
                .minValue(0)                                                                        // Set the start value of the scale
                .maxValue(100)                                                                      // Set the end value of the scale
                .startAngle(320)                                                                    // Set the start angle of your scale (bottom -> 0, direction -> CCW)
                .angleRange(280)                                                                    // Set the angle range of your scale starting from the start angle
                // Related to Tick Labels
                .tickLabelDecimals(0)                                                               // Set the number of decimals for the tick labels
                .tickLabelLocation(TickLabelLocation.INSIDE)                                        // Define whether the tick labels should be inside or outside the scale (INSIDE, OUTSIDE)
                .tickLabelOrientation(TickLabelOrientation.ORTHOGONAL)                              // Define the orientation of the tick labels (ORTHOGONAL,  HORIZONTAL, TANGENT)
                .onlyFirstAndLastTickLabelVisible(false)                                            // Define if only the first and last tick label should be visible
                .tickLabelSectionsVisible(false)                                                    // Define if sections for tick labels should be visible
                .tickLabelSections(section1, section2)                                              // Define sections to color tick labels
                .tickLabelColor(Color.WHITE)                                                        // Define the color for tick labels (overridden by tick label sections)
                // Related to Tick Marks
                .tickMarkSectionsVisible(false)                                                     // Define if sections for tick marks should be visible
                .tickMarkSections(section1, section2)                                               // Define sections to color tick marks
                // Related to Major Tick Marks
                .majorTickMarksVisible(true)                                                        // Define if major tick marks should be visible
                .majorTickMarkType(TickMarkType.TRAPEZOID)                                           // Define the tick mark type for major tick marks (LINE, DOT, TRAPEZOID, TICK_LABEL)
                // Related to Medium Tick Marks
                .mediumTickMarksVisible(false)                                                      // Define if medium tick marks should be visible
                .mediumTickMarkType(TickMarkType.LINE)                                              // Define the tick mark type for medium tick marks (LINE, DOT, TRAPEZOID)
                // Related to Minor Tick Marks
                .minorTickMarksVisible(true)                                                        // Define if minor tick marks should be visible
                .minorTickMarkType(TickMarkType.LINE)                                               // Define the tick mark type for minor tick marks (LINE, DOT, TRAPEZOID)
                // Related to LED
                .ledVisible(false)                                                                  // Defines if the LED should be visible
                .ledType(LedType.STANDARD)                                                          // Defines the type of the LED (STANDARD, FLAT)
                .ledColor(Color.rgb(255, 200, 0))                                                   // Defines the color of the LED
                .ledBlinking(false)                                                                 // Defines if the LED should blink
                // Related to Needle
                .needleShape(NeedleShape.ANGLED)                                                    // Defines the shape of the needle (ANGLED, ROUND, FLAT)
                .needleSize(NeedleSize.STANDARD)                                                    // Defines the size of the needle (THIN, STANDARD, THICK)
                .needleColor(Color.CRIMSON)                                                         // Defines the color of the needle
                // Related to Needle behavior
                .startFromZero(false)                                                               // Defines if the needle should start from the 0 value
                .returnToZero(false)                                                                // Defines if the needle should always return to the 0 value (only makes sense when animated==true)
                // Related to Knob
                .knobType(KnobType.METAL)                                                           // Defines the type for the center knob (STANDARD, PLAIN, METAL, FLAT)
                .knobColor(Color.LIGHTGRAY)                                                         // Defines the color that should be used for the center knob
                .interactive(true)                                                                 // Defines if it should be possible to press the center knob
                .onButtonPressed(buttonEvent -> System.out.println("Knob pressed"))                 // Defines a handler that will be triggered when the center knob was pressed
                .onButtonReleased(buttonEvent -> System.out.println("Knob released"))               // Defines a handler that will be triggered when the center knob was released
                // Related to Threshold
                .thresholdVisible(true)                                                            // Defines if the threshold indicator should be visible
                .threshold(50)                                                                      // Defines the value for the threshold
                .thresholdColor(Color.RED)                                                          // Defines the color for the threshold
                .checkThreshold(true)                                                              // Defines if each value should be checked against the threshold
                .onThresholdExceeded(thresholdEvent -> System.out.println("Threshold exceeded"))    // Defines a handler that will be triggered if checkThreshold==true and the threshold is exceeded
                .onThresholdUnderrun(thresholdEvent -> System.out.println("Threshold underrun"))    // Defines a handler that will be triggered if checkThreshold==true and the threshold is underrun
                // Related to Gradient Bar
                .gradientBarEnabled(true)                                                         // Defines if a gradient filled bar should be visible to visualize a range
                .gradientBarStops(new Stop(0.0, Color.BLUE),// Defines a conical color gradient that will be use to color the gradient bar
                        new Stop(0.25, Color.CYAN),
                        new Stop(0.5, Color.LIME),
                        new Stop(0.75, Color.YELLOW),
                        new Stop(1.0, Color.RED))
                // Related to Markers
                .markersVisible(true)                                                               // Defines if markers will be visible
                .markers(marker1, marker2)                                                          // Defines markers that will be drawn
                // Related to Value
                .animated(true)                                                                     // Defines if the needle will be animated
                .animationDuration(500)                                                             // Defines the speed of the needle in milliseconds (10 - 10000 ms)
                .build();

        fSpeedGauge = FGaugeBuilder
                .create()
                .prefSize(400, 400)
                .gauge(speedGauge)
                .gaugeDesign(GaugeDesign.METAL)
                .gaugeBackground(GaugeBackground.CARBON)
                .foregroundVisible(true)
                .build();

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

//    private void generateRandom() {
//        long start_time = System.currentTimeMillis();
//        long wait_time = 2000;
//        long end_time = start_time + wait_time;
//
//        while (System.currentTimeMillis() < end_time) {
//            IntStream rand = new Random().ints(1, 0, 101);
//            System.out.println(rand);
//        }
//    }

    @Override
    public void start(Stage stage) {

        BorderPane borderPane = new BorderPane();

        /*<--------------------> Gauges HBox <--------------------> */
        HBox hboxGauges = new HBox();

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

        hboxGauges.getChildren().addAll(fuelGauge, fSpeedGauge, RPMGauge, tempGauge);
        hboxGauges.setPadding(new Insets(15, 12, 15, 0));
        hboxGauges.setSpacing(10);
        hboxGauges.setStyle("-fx-background-color: indianred");

        HBox.setHgrow(fSpeedGauge, Priority.ALWAYS);
        HBox.setHgrow(fuelGauge, Priority.ALWAYS);
        HBox.setHgrow(tempGauge, Priority.ALWAYS);
        HBox.setHgrow(RPMGauge, Priority.ALWAYS);
        HBox.setHgrow(testButton, Priority.ALWAYS);
        HBox.setHgrow(cancelButton, Priority.ALWAYS);

        hboxGauges.setAlignment(Pos.CENTER);

        /*<--------------------> Test buttons HBox <--------------------> */
        HBox hboxTestButtons = new HBox();
        hboxTestButtons.setSpacing(10);
        hboxTestButtons.getChildren().addAll(testButton, cancelButton);
        hboxGauges.setStyle("-fx-background-color: indianred");

        borderPane.setCenter(hboxGauges);
        borderPane.setTop(hboxTestButtons);

        Scene scene = new Scene(borderPane);
        stage.setTitle("Telemetry System");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        setUserAgentStylesheet(STYLESHEET_MODENA);
        launch(args);
    }
}