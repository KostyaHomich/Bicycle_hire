package epam.project.util;

import epam.project.entity.Bicycle;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.*;
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


        final Runnable updater = () -> {
            try {
                if (checkInternetConnection()) {

                List<Bicycle> parseBicycles = parser.parse();
                List<Bicycle> bicycles = bicycleService.takeAll();

                int size = bicycles.size();
                int parseSize = parseBicycles.size();

                if (bicycles.isEmpty()) {
                    for (Bicycle parseBicycle : parseBicycles) {
                        bicycleService.add(parseBicycle);
                    }
                } else if (parseBicycles.size() > bicycles.size()) {
                    for (int i = size; i < parseSize; i++) {
                        bicycleService.add(parseBicycles.get(i));
                    }
                }
                else if(parseBicycles.size() < bicycles.size()) {
                    bicycles.removeAll(parseBicycles);
                    for (int i = 0; i < bicycles.size(); i++) {
                        bicycleService.delete(bicycles.get(i));
                    }
                }
                }

            } catch (ServiceException e) {
                LOGGER.error("Failed update bicycles", e);
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

    private boolean checkInternetConnection() {
        boolean result = false;
        HttpURLConnection con = null;
        try {
            // HttpURLConnection.setFollowRedirects(false);
            // HttpURLConnection.setInstanceFollowRedirects(false)
            con = (HttpURLConnection) new URL("https://ya.ru").openConnection();
            con.setRequestMethod("HEAD");
            result = (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (IOException e) {
            LOGGER.error("You haven't internet connection.",e);

        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception e) {
                   LOGGER.error("Failed to close connection",e);
                }
            }
        }
        return result;
    }


}
