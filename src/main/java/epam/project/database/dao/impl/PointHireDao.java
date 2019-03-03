package epam.project.database.dao.impl;

import epam.project.database.dao.AbstractJdbcDao;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.exception.PersistException;
import epam.project.entity.PointHire;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class PointHireDao  extends AbstractJdbcDao<PointHire, Integer> implements EntityDao<PointHire, Integer>{

    private static final String CREATE_QUERY =
            "insert into point_hire values (NULL ,?,?,?)";
    private static final String UPDATE_QUERY =
            "update point_hire set location=?,telephone=?,description=? where id=?";
    private static final String DELETE_QUERY =
            "DELETE FROM point_hire WHERE id=?";
    private static final String SELECT_QUERY =
            "SELECT * FROM point_hire";
    private static final String CHECK_IF_CONTAINS =
            "SELECT id FROM point_hire WHERE id=?";
    private static final String TAKE_BY_ID =
            "SELECT * FROM bicycle_hire.point_hire WHERE id=?";


    @Override
    protected List<PointHire> parseResultSet(ResultSet rs) throws SQLException, PersistException {
        List<PointHire> result = new ArrayList<>();
        try {
            while (rs.next()) {
                PointHire pointHire = new PointHire();
                pointHire.setId(rs.getInt("id"));
                pointHire.setLocation(rs.getString("location"));
                pointHire.setTelephone(rs.getString("telephone"));
                pointHire.setDescription(rs.getString("description"));
                result.add(pointHire);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, PointHire object) throws SQLException {

            statement.setString(1, object.getLocation());
            statement.setString(2, object.getTelephone());
            statement.setString(3, object.getDescription());

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, PointHire object) throws SQLException {

            statement.setString(1, object.getLocation());
            statement.setString(2, object.getTelephone());
            statement.setString(3, object.getDescription());
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




}
