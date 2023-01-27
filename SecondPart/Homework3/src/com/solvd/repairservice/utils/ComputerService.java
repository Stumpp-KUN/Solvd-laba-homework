package solvd.repairservice.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import solvd.repairservice.dao.mySQL.*;
import solvd.repairservice.models.Car;
import solvd.repairservice.models.Order;
import solvd.repairservice.models.person.Client;
import solvd.repairservice.models.person.Courier;
import solvd.repairservice.models.person.Person;
import solvd.repairservice.models.person.Worker;
import solvd.repairservice.services.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComputerService {
    private final static Logger logger= LogManager.getRootLogger();
    public static void main(String[] args) throws SQLException, JAXBException, IOException, ParserConfigurationException, SAXException {
        Scanner scanner=new Scanner(System.in);
        uT();
        //logger.info("1 - add new, 2 - get all info about, 3 - delete, 4 - update worker's orders, 5 - update Car_CourierID , 6 - make an order");
        //makeAChoise(scanner,scanner.nextInt());
    }

    private static void uT() throws JAXBException, IOException, ParserConfigurationException, SAXException {
        Courier courier=new Courier(3,"io","pl");
        Car p=new Car(5,"test","test",courier);
        JAXBContext c =JAXBContext.newInstance(Car.class);
        Marshaller m=c.createMarshaller();
        Unmarshaller t=c.createUnmarshaller();
        m.marshal(p,new File("car.xml"));
        Parsers parsers=new Parsers();
        parsers.parseStAX("car.xml");
        logger.info("---------");
        parsers.parserSAX("car.xml");
        p=(Car) t.unmarshal(new FileReader("car.xml"));
        logger.info(p.getCourier().getName()+" "+p.getModelName());
    }


    private static boolean makeAChoise(Scanner scanner,int k) throws SQLException {
        switch (k){
            case 6:{
                logger.info("Enter description, Client_id, Worker_name");
                ClientService clientService=new ClientService();
                Client client=new Client();
                WorkerService workerService=new WorkerService();
                Worker worker=new Worker();
                String temp=scanner.next();
                ClientDAO clientDAO=new ClientDAO();
                client=clientDAO.getEntityById(scanner.nextLong());
                worker=workerService.getWorkerByName(scanner.next());
                OrderService orderService=new OrderService();
                clientService.updateClient(client.getId(),orderService.createOrder(temp,client,worker));
                return true;
            }

            case 1:{
                logger.info("1 - add client, 2 - add courier, 3 - add car, 4 - add worker, 5 - exit");
                switch (scanner.nextInt()){
                    case 1:{
                        logger.info("Enter name,surname,phoneNumber");
                        ClientService clientService=new ClientService();
                        clientService.createClient(scanner.next(),scanner.next(),scanner.next());
                        logger.info("client created");
                    }
                    break;
                    case 2:{
                        logger.info("Enter name, surname");
                        CourierService courierService=new CourierService();
                        courierService.createCourier(scanner.next(), scanner.next());
                        logger.info("Courier is created");
                    }
                    break;
                    case 3:{
                        logger.info("Enter carModel and carType, courier_ID");
                        CarService carService=new CarService();
                        CourierService courierService=new CourierService();
                        Courier courier=new Courier();
                        String temp1= scanner.next();
                        String temp2= scanner.next();
                        courier = courierService.getCourierById(scanner.nextLong());
                        carService.createCar(temp1,temp2,courier);
                        logger.info("car is created");
                    }
                    break;
                    case 4:{
                        logger.info("Enter name,surname, workerLicence_id");
                        WorkerService workerService=new WorkerService();
                        WorkerLicenceDAO workerLicenceDAO=new WorkerLicenceDAO();
                        workerService.createWorker(scanner.next(), scanner.next(), workerLicenceDAO.getEntityById(scanner.nextLong()),null);
                        logger.info("success");
                    }
                    break;
                }
                return true;
            }
            case 2:{
                logger.info("1 - client, 2 - car, 3 - courier, 4-allClients, 5-allCars, 6-allCouriers");
                takeInfo(scanner,scanner.nextInt());
                return true;
            }
            case 3:{
                logger.info("What u want to delete: 1 - client, 2 - car, 3 - courier, 4 - order");
                return deleteMethod(scanner,scanner.nextInt());
            }
            case 4:{
                WorkerDAO workerDAO=new WorkerDAO();
                List<Order> orders=new ArrayList<>();
                logger.info("insert how many orders");
                int j=scanner.nextInt();
                System.out.println("Enter orders id");
                for(int i=0;i<j;i++) {
                    orders.add(new Order(scanner.nextInt()));
                }
                logger.info("enter worker id");
                workerDAO.updateOrders(scanner.nextLong(),orders);
                return true;
            }
            case 5:{
                CarService carService=new CarService();
                CourierService courierService=new CourierService();
                logger.info("Enter carId, Courier_id");
                carService.updateCar(scanner.nextLong(),courierService.getCourierById(scanner.nextLong()));
                return true;
            }
            default:{
                return false;
            }
        }
    }

    private static void takeInfo(Scanner scanner,int xx) throws SQLException {
        switch (xx){
            case 4:{
                ClientService clientService=new ClientService();
                List<Client> clients=clientService.getAllClients();
                for (int i=0;i<clients.size();i++)
                    logger.info(clients.get(i).toString());
            }
            break;
            case 1:{
                ClientService clientService=new ClientService();
                logger.info("Enter id");
                ClientDAO clientDAO=new ClientDAO();
                Client client=clientDAO.getEntityById(scanner.nextLong());
                logger.info(client.toString());
            }
            break;
            case 5:{
                CarService carService=new CarService();
                List<Car> cars=carService.getAllCars();
                for (int i=0;i<cars.size();i++)
                    logger.info(cars.get(i).toString());
            }
            break;
            case 2:{
                CarService carService=new CarService();
                logger.info("Enter id");
                logger.info(carService.getCarByID(scanner.nextLong()));
            }
            break;
            case 6:{
                CourierService courierService=new CourierService();
                List<Courier> couriers=courierService.getAllCourier();
                for (int i=0;i<couriers.size();i++)
                    logger.info(couriers.get(i).getName());
            }
            break;
            case 3:{
                CourierService courierService=new CourierService();
                logger.info("Enter id");
                Courier courier=courierService.getCourierById(scanner.nextLong());
                logger.info(courier.getName()+" "+courier.getSurname());
            }
            break;
            default: logger.warn("Error of number");
        }
    }


    private static boolean deleteMethod(Scanner scanner,int k){
        switch (k){
            case 1:{
                logger.info("Enter client id");
                ClientService clientService=new ClientService();
                clientService.deleteClientById(scanner.nextLong());
                logger.info("Succesfully deleted");
                return true;
            }
            case 2:{
                logger.info("Enter car id");
                CarService carService=new CarService();
                carService.deleteCar(scanner.nextLong());
                logger.info("Succesfully deleted");
                return true;
            }
            case 3:{
                logger.info("Enter courier id");
                CourierService courierService=new CourierService();
                courierService.deleteCourierById(scanner.nextLong());
                logger.info("Succesfully deleted");
                return true;
            }
            case 4:{

            }
            default:return false;
        }
    }


}
