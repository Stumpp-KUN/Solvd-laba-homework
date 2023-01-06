package navigator.DB;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DBConnect {

    public Country getCountry(int x,int y){
        SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Country.class).buildSessionFactory();
        Session session=factory.getCurrentSession();
        session.beginTransaction();
        List<Country> us=session.createQuery("from Country "+"where x='"+x+"'"+"AND y='"+y+"'").getResultList();
        session.getTransaction().commit();
        factory.close();
        return us.get(0);
    }
}
