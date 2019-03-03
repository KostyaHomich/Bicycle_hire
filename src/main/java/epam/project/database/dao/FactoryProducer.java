package epam.project.database.dao;

import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.impl.JdbcDaoFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FactoryProducer {
    private static FactoryProducer instance;
    private static Lock lock = new ReentrantLock();
    private FactoryProducer() {}

    public static FactoryProducer getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new FactoryProducer();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    public static DaoFactory getDaoFactory(DaoFactoryType type) throws DaoException {

      switch (type) {
          case JDBC:return JdbcDaoFactory.getInstance();
          default: throw new DaoException("Factory doesn't exist");
      }

    }
}
