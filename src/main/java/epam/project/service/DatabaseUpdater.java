package epam.project.service;

import epam.project.entity.Bicycle;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.MINUTES;


public class DatabaseUpdater {

    private static Logger LOGGER = Logger.getLogger(DatabaseUpdater.class.getName());
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public void updateDB() {

        Parser parser = new Parser();
        BicycleService bicycleService = new BicycleService();

        final Runnable updater = new Runnable() {
            public void run() {
                try {

                    List<Bicycle> parseBicycles = parser.parse();
                    List<Bicycle> bicycles = bicycleService.takeAll();

                    int size = bicycles.size();
                    int parseSize = parseBicycles.size();

                    if (bicycles.isEmpty()) {
                        for (Bicycle parseBicycle : parseBicycles) {
                            bicycleService.add(parseBicycle);
                        }
                    } else if (parseBicycles.size() != bicycles.size()) {
                        for (int i = size; i < parseSize; i++) {
                            bicycleService.add(parseBicycles.get(i));
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error("Failed update bicycles", e);
                }
            }
        };
        Thread thread = new Thread(updater);
        thread.setDaemon(true);
        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(thread, 0, 5, MINUTES);

        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
            }
        }, 60 * 60, DAYS);

    }


}
