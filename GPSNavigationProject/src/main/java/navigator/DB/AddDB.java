package navigator.DB;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AddDB {

    public boolean addCountry(String name,int x,int y){
        SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Country.class).buildSessionFactory();
        Session session=factory.getCurrentSession();
        session.beginTransaction();
        Country country=new Country(name,x,y);
        session.save(country);
        session.getTransaction().commit();
        factory.close();
        return true;
    }
}
