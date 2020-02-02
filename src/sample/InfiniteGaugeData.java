package sample;

import eu.hansolo.medusa.Gauge;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class InfiniteGaugeData implements Runnable {
    private Gauge fuelGauge, speedGauge, RPMGauge, tempGauge,
            fuelGraph, speedGraph, RPMGraph, tempGraph;

    InfiniteGaugeData(Gauge fuelGauge, Gauge speedGauge, Gauge RPMGauge, Gauge tempGauge,
                      Gauge fuelGraph, Gauge speedGraph, Gauge RPMGraph, Gauge tempGraph) {
        this.fuelGauge = fuelGauge;
        this.speedGauge = speedGauge;
        this.RPMGauge = RPMGauge;
        this.tempGauge = tempGauge;
        this.fuelGraph = fuelGraph;
        this.speedGraph = speedGraph;
        this.RPMGraph = RPMGraph;
        this.tempGraph = tempGraph;

    }

    /*<--------------------> Generate random number from 0 to a 100 - for testing <--------------------> */
    private int generateRandom(int limit) {
        return new Random().nextInt(limit);
    }

    public void run() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                int speed = generateRandom(100);
                int fuel = generateRandom(10);
                int temp = generateRandom(100);
                int rpm = generateRandom(16);

                speedGauge.setValue(speed);
                speedGraph.setValue(speed);

                fuelGauge.setValue(fuel);
                fuelGraph.setValue(fuel);

                tempGauge.setValue(temp);
                tempGraph.setValue(temp);

                RPMGauge.setValue(rpm);
                RPMGraph.setValue(rpm);

                SimpleDateFormat liveDate = new SimpleDateFormat(" h:mm:s a, EEEE dd MMMM YYYY");
                String date = liveDate.format(new Date());
                try {
                    Logger.log("*************" + date + "*************\n");
                    Logger.log("Speed: " + speed + "\n");
                    Logger.log("Fuel: " + fuel + "\n");
                    Logger.log("Temp: " + temp + "\n");
                    Logger.log("RPM: " + rpm + "\n");
                    Logger.log("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
    }

    void start() {
    }
}