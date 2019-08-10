package epam.project.database.dao.impl;

import epam.project.database.dao.AbstractJdbcDao;
import epam.project.database.dao.AutoConnection;
import epam.project.database.dao.BicycleDao;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.exception.DaoException;
import epam.project.dto.PointHireBicycle;
import epam.project.entity.Bicycle;
import epam.project.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BicycleDaoImpl extends AbstractJdbcDao<Bicycle, Integer> implements BicycleDao, EntityDao<Bicycle, Integer> {

    private static final String CREATE_QUERY =
            "insert into bicycle values (NULL ,?,?,?);";

    private static final String CREATE_QUERY_POINT_HIRE_BICYCLE =
            "insert into point_hire_bicycle values (NULL,?,?);";

    private static final String CREATE_QUERY_BEST_BICYCLE =
            "insert into best_bicycle values (NULL,?,?);";

    private static final String DELETE_BEST_BICYCLE =
            "DELETE FROM best_bicycle WHERE id=?";

    private static final String UPDATE_QUERY =
            "update bicycle set link=?, name=?,description=? where id=?";

    private static final String DELETE_QUERY =
            "DELETE FROM bicycle WHERE id=?";

    private static final String SELECT_QUERY =
            "SELECT * FROM bicycle;";

    private static final String SELECT_QUERY_WITHOUT_BEST_BICYCLE_LIMITED_BY_USER_ID =
            " select distinct( bicycle.id),link,name,description from bicycle,best_bicycle,point_hire_bicycle  where bicycle.id not in( " +
                    " SELECT bicycle.id" +
                    " FROM bicycle,best_bicycle,point_hire_bicycle" +
                    " where best_bicycle.id_user=?" +
                    " and best_bicycle.id_point_hire_bicycle=point_hire_bicycle.id" +
                    " and bicycle.id=point_hire_bicycle.id_bicycle) limit ?,?;";


    private static final String CHECK_IF_CONTAINS =
            "SELECT * FROM bicycle WHERE id=?";

    private static final String GET_AMOUNT_BICYCLES =
            "SELECT count(*)  FROM bicycle;";

    private static final String TAKE_BY_BICYCLE_ID_POINT_HIRE_BICYCLE =
            "SELECT * FROM point_hire_bicycle WHERE id_bicycle=?";

    private static final String GET_ALL_BICYCLE_BY_POINT_HIRE =
            "SELECT bicycle.id,name,description,link " +
                    "FROM bicycle,point_hire_bicycle where point_hire_bicycle.id_point_hire=?" +
                    " and bicycle.id=point_hire_bicycle.id_bicycle;";

    private static final String GET_ALL_BEST_BICYCLE_BY_USER =
         "SELECT bicycle.id,name,description,link " +
                 "FROM bicycle,best_bicycle,point_hire_bicycle where best_bicycle.id_user=? " +
                 "and best_bicycle.id_point_hire_bicycle=point_hire_bicycle.id " +
                 "and bicycle.id=point_hire_bicycle.id_bicycle;";

    private static final String GET_BEST_BICYCLE_ID_BY_BICYCLE_ID =
            "SELECT best_bicycle.id FROM best_bicycle,bicycle,point_hire_bicycle where bicycle.id=? and " +
                    "point_hire_bicycle.id_bicycle=bicycle.id and best_bicycle.id_point_hire_bicycle=point_hire_bicycle.id;";

    @Override
    protected List<Bicycle> parseResultSet(ResultSet rs) throws DaoException {
        List<Bicycle> result = new ArrayList<>();
        try {
            setDefaultData(rs, result);
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }

    private void setDefaultData(ResultSet rs, List<Bicycle> result) throws SQLException {
        while (rs.next()) {
            setBicycleData(result, rs);
        }
    }


    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Bicycle object) throws SQLException {

        setStatement(statement, object);

    }

    private void setStatement(PreparedStatement statement, Bicycle object) throws SQLException {
        statement.setString(1, object.getLink());
        statement.setString(2, object.getName());
        statement.setString(3, object.getDescription());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Bicycle object) throws SQLException {

        setStatement(statement, object);
        statement.setInt(4, object.getId());

    }

    @Override
    public String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    public String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @AutoConnection
    @Override
    public void addPointHireBicycle(Bicycle bicycle) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY_POINT_HIRE_BICYCLE)) {
            int counter = 0;
            statement.setInt(++counter, bicycle.getPointHireId());
            statement.setInt(++counter, bicycle.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to insert entity", e);
        }
    }


    private PointHireBicycle getPointHireBicycle(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(TAKE_BY_BICYCLE_ID_POINT_HIRE_BICYCLE)) {
            PointHireBicycle pointHireBicycle = new PointHireBicycle();
            int counter = 0;
            statement.setInt(++counter, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                pointHireBicycle.setId(rs.getInt("id"));
                pointHireBicycle.setBicycleId(rs.getInt("id_bicycle"));
                pointHireBicycle.setPointHireId(rs.getInt("id_point_hire"));
            } else {
                throw new DaoException("This point hire bicycle doesn't exist");
            }
            return pointHireBicycle;
        } catch (SQLException e) {
            throw new DaoException("Failed to get entity", e);
        }
    }

    @AutoConnection
    @Override
    public PointHireBicycle getByBicyclePkPointHireBicycle(int id) throws DaoException {
        return getPointHireBicycle(id);
    }

    @AutoConnection
    @Override
    public boolean containsBicycle(int id) throws DaoException {

        try (PreparedStatement statement = connection.prepareStatement(CHECK_IF_CONTAINS)) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new DaoException("Failed to get entity.", e);
        }

    }

    @AutoConnection
    @Override
    public List<Bicycle> getAllBicycleByPointHirePk(int id) throws DaoException {
        return setBicycleData(id);

    }

    private List<Bicycle> setBicycleData(int id) throws DaoException {
        List<Bicycle> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_BICYCLE_BY_POINT_HIRE)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            setDefaultData(rs, result);
            return result;
        } catch (SQLException e) {
            throw new DaoException("Failed to get entity.", e);
        }
    }




    @AutoConnection
    @Override
    public List<Bicycle> getBicycles(int start, int count, User user) throws DaoException {
        List<Bicycle> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY_WITHOUT_BEST_BICYCLE_LIMITED_BY_USER_ID)) {

            int counter = 0;
            statement.setInt(++counter, user.getId());
            statement.setInt(++counter, start);
            statement.setInt(++counter, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                setBicycleData(result, rs);
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Failed to get entitys", e);
        }
    }

    @AutoConnection
    @Override
    public void addBestBicycle(PointHireBicycle pointHireBicycle, User user) throws DaoException {

        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY_BEST_BICYCLE)) {
            int counter = 0;

            statement.setInt(++counter, user.getId());
            statement.setInt(++counter, pointHireBicycle.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to insert entity", e);
        }
    }

    @AutoConnection
    @Override
    public void deleteBestBicycle(int id) throws DaoException {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE_BEST_BICYCLE)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Cannot delete entity.", e);
        }
    }

    @AutoConnection
    @Override
    public List<Bicycle> getAllBestBicyclesByUserId(int id) throws DaoException {

        List<Bicycle> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_BEST_BICYCLE_BY_USER)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            setDefaultData(rs, result);
            return result;
        } catch (SQLException e) {
            throw new DaoException("Failed to get entity.", e);
        }

    }

    @AutoConnection
    @Override
    public int getBestBicycleIdByBicycleId(int id) throws DaoException {

        try (PreparedStatement statement = connection.prepareStatement(GET_BEST_BICYCLE_ID_BY_BICYCLE_ID)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
          return rs.getInt("best_bicycle.id");
        } catch (SQLException e) {
            throw new DaoException("Failed to get entity.", e);
        }

    }

    @AutoConnection
    @Override
    public int getAmountBicycles() throws DaoException {

        try (PreparedStatement statement = connection.prepareStatement(GET_AMOUNT_BICYCLES)) {
            int amountBicycles=0;
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
             amountBicycles=rs.getInt(1);
            }
            return amountBicycles;
        } catch (SQLException e) {
            throw new DaoException("Failed to get entity.", e);
        }
    }

    private void setBicycleData(List<Bicycle> result, ResultSet rs) throws SQLException {
        Bicycle bicycle = new Bicycle();
        bicycle.setId(rs.getInt("id"));
        bicycle.setLink(rs.getString("link"));
        bicycle.setName(rs.getString("name"));
        bicycle.setDescription(rs.getString("description"));
        result.add(bicycle);
    }

}
