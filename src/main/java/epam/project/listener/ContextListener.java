package epam.project.listener;

import epam.project.database.dao.exception.ConnectionPoolException;
import epam.project.database.pool.ConnectionPoolFactory;
import epam.project.database.pool.ConnectionPoolImpl;
import epam.project.service.DatabaseUpdater;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    private static Logger LOGGER = Logger.getLogger(ContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
         ConnectionPoolImpl connectionPool= (ConnectionPoolImpl) ConnectionPoolFactory.getInstance().getConnectionPool();
         connectionPool.init();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Failed to init connection pool",e);
        }
        DatabaseUpdater databaseUpdater=new DatabaseUpdater();
        databaseUpdater.updateDB();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPoolImpl connectionPool= (ConnectionPoolImpl) ConnectionPoolFactory.getInstance().getConnectionPool();
            connectionPool.destroyPool();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Failed to init destroy connection pool",e);
        }
    }
}
