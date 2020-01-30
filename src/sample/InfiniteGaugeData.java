package sample;

import eu.hansolo.medusa.Gauge;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class InfiniteGaugeData implements Runnable {
    private Gauge fuelGauge, speedGauge, RPMGauge, tempGauge, fuelGraph, speedGraph, RPMGraph, tempGraph;

    InfiniteGaugeData(Gauge fuelGauge, Gauge speedGauge, Gauge RPMGauge, Gauge tempGauge, Gauge fuelGraph, Gauge speedGraph, Gauge RPMGraph, Gauge tempGraph) {
        this.fuelGauge = fuelGauge;
        this.speedGauge = speedGauge;
        this.RPMGauge = RPMGauge;
        this.tempGauge = tempGauge;
        this.fuelGraph = fuelGraph;
        this.speedGraph = speedGraph;
        this.RPMGraph = RPMGraph;
        this.tempGraph = tempGraph;

    }

    /*<--------------------> Generate random number from 0 to a 100 - for unit testing <--------------------> */
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

                System.out.println("Speed: " + speed);
                System.out.println("Fuel: " + fuel);
                System.out.println("Temp: " + temp);
                System.out.println("RPM: " + rpm);
            }
        }, 0);
    }

    public void stop() {
        System.exit(0);
    }

    void start() {
    }
}