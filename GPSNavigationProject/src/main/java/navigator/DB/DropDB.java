package navigator.DB;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.List;

public class DropDB {

    public boolean dropCountry(String name){
            SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Country.class).buildSessionFactory();
            Session session=factory.getCurrentSession();
            session.beginTransaction();
            List<Country> us=session.createQuery("from Country "+"where country='"+name+"'").getResultList();
            if(us.isEmpty()){
                session.getTransaction().commit();
                factory.close();
                return false;
            }
            else{
                session.createQuery(" delete Country "+"where country='"+name+"'").executeUpdate();
                session.getTransaction().commit();
                factory.close();
                return true;
            }
    }

    public boolean dropCountry(int x,int y){
        SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Country.class).buildSessionFactory();
        Session session=factory.getCurrentSession();
        session.beginTransaction();
        List<Country> us=session.createQuery("from Country "+"where x='"+x+"'"+"AND y='"+y+"'").getResultList();
        if(us.isEmpty()){
            session.getTransaction().commit();
            factory.close();
            return false;
        }
        else{
            session.createQuery("delete Country "+"where x='"+x+"'"+"AND y='"+y+"'").executeUpdate();
            session.getTransaction().commit();
            factory.close();
            return true;
        }
    }
}
