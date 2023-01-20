package solvd.repairservice.dao.mySQL;

import solvd.repairservice.dao.IWorkerLicenceDAO;
import solvd.repairservice.models.Car;
import solvd.repairservice.models.WorkerLicence;
import solvd.repairservice.models.person.Courier;
import solvd.repairservice.models.person.Worker;
import solvd.repairservice.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WorkerLicenceDAO extends MySQLDAO implements IWorkerLicenceDAO {
    private Connection connection;
    private ResultSet rs;
    private final static String GETWORKERLICENCE="SELECT * FROM `workerlicence` WHERE id=?";
    {
        MySQLConnection mySQLConnection=new MySQLConnection();
        connection=mySQLConnection.getConnection();
    }

    @Override
    public WorkerLicence createEntity(WorkerLicence entity) {
        return null;
    }

    @Override
    public WorkerLicence getEntityById(long id) {
        try(PreparedStatement ps=connection.prepareStatement(GETWORKERLICENCE)){
            WorkerLicence workerLicence=new WorkerLicence();
            ps.setLong(1,id);
            rs= ps.executeQuery();
            while (rs.next()){
                workerLicence.setId(rs.getLong("id"));
                workerLicence.setCountry(rs.getString("country"));
            }
            rs.close();
            return workerLicence;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public boolean updateEntity(WorkerLicence entity) {
        return false;
    }

    @Override
    public void removeEntity(long id) {

    }

    @Override
    public List<WorkerLicence> getAllWorkerLicence() {
        return null;
    }

    @Override
    public List<WorkerLicence> getAllWorkerLicenceByCountry(String country) {
        return null;
    }

    @Override
    public WorkerLicence getLicenceByWorkerID(long id) {
        return null;
    }
}
