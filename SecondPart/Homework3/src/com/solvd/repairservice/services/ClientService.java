package solvd.repairservice.services;

import solvd.repairservice.dao.IClientDAO;
import solvd.repairservice.dao.mySQL.ClientDAO;
import solvd.repairservice.models.Order;
import solvd.repairservice.models.person.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private IClientDAO uDAO=new ClientDAO();

    public Client getClientById(long id) throws SQLException {
        Client u= (Client) uDAO.getEntityById(id);
        return u;
    }

    public Client createClient(String name,String surname,String number){
        Client client=new Client(name,surname,number);
        client=uDAO.createEntity(client);
        return client;
    }

    public long clientIsExist(long id){
        ClientDAO clientDAO=new ClientDAO();
        return clientDAO.getClientIsExist(id);
    }

    public boolean updateClient(long id, Order orders){
        uDAO.updateOrders(id,orders);
        return true;
    }

    public void deleteClientById(long id){
        uDAO.removeEntity(id);
    }

    public Client getClientByPhoneNumber(String number){
        Client u=uDAO.getClientByPhoneNumber(number);
        return u;
    }

    public List<Client> getAllClients(){
        return uDAO.getAllClients();
    }

}
