package sample;

import eu.hansolo.medusa.Gauge;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class InfiniteGaugeData implements Runnable {
    private Gauge fuelGauge, speedGauge, RPMGauge, tempGauge;

    public InfiniteGaugeData(Gauge fuelGauge, Gauge speedGauge, Gauge RPMGauge, Gauge tempGauge) {
        this.fuelGauge = fuelGauge;
        this.speedGauge = speedGauge;
        this.RPMGauge = RPMGauge;
        this.tempGauge = tempGauge;
    }

    /*<--------------------> Generate random number from 0 to a 100 - for unit testing - *PROBLEM* <--------------------> */
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
                int rpm = generateRandom(8);

                speedGauge.setValue(speed);
                fuelGauge.setValue(fuel);
                tempGauge.setValue(temp);
                RPMGauge.setValue(rpm);

                System.out.println("Speed: " + speed);
                System.out.println("Fuel: " + fuel);
                System.out.println("Temp: " + temp);
                System.out.println("RPM: " + rpm);
            }
        }, 2000);
    }

    public void start () {

    }
}