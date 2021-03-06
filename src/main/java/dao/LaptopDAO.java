package dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import entity.Laptop;
import util.HibernateUtils;

import java.util.List;

public class LaptopDAO {
    static Logger log = Logger.getLogger(LaptopDAO.class.getName());

    Session session;
    SessionFactory factory;

    public LaptopDAO(){factory= HibernateUtils.getSessionFactory();}

    public Laptop getLaptopById(Long id) {
        log.info("Get book by id: " + id);
        Session session = factory.openSession();
        Laptop laptop = (session.get(Laptop.class, id));
        session.close();
        return laptop;
    }

    public void insertIntoTable(Laptop laptop) {
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(laptop);
        transaction.commit();
        session.close();
    }

    public void deleteFromTable(Long id) {
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(this.getLaptopById(id));
        transaction.commit();
        session.close();
    }

    public void updateLaptop(double price, String name, int voltage, boolean hasGraphicCard, int numbOfPorts, long id) {
        Laptop newLaptop = this.getLaptopById(id);
        newLaptop.setPrice(price);
        newLaptop.setName(name);
        newLaptop.setVoltage(voltage);
        newLaptop.setHasGraphicCard(hasGraphicCard);
        newLaptop.setNumbOfPorts(numbOfPorts);

        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(newLaptop);
        transaction.commit();
        session.close();
    }

    public List<Laptop> getAllLaptops() {
        session = factory.openSession();
        final List<Laptop> resultList = session.createQuery("SELECT b FROM Laptop b", Laptop.class).getResultList();
//        final List resultList = session.createQuery("from book").list();
        session.close();
        return resultList;
    }
}
