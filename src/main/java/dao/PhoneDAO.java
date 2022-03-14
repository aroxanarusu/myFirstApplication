package dao;

import entity.Laptop;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import entity.Phone;
import util.HibernateUtils;

import java.util.List;

public class PhoneDAO {

    static Logger log = Logger.getLogger(LaptopDAO.class.getName());

    Session session;
    SessionFactory factory;

    public PhoneDAO(){factory= HibernateUtils.getSessionFactory();}

    public Phone getPhoneById(Long id) {
        log.info("Get book by id: " + id);
        Session session = factory.openSession();
        Phone phone = (session.get(Phone.class, id));
        session.close();
        return phone;
    }

    public void insertIntoTable(Phone phone) {
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(phone);
        transaction.commit();
        session.close();
    }

    public void deleteFromTable(Long id) {
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(this.getPhoneById(id));
        transaction.commit();
        session.close();
    }

    public void updatePhone(double price, String name, int voltage, boolean hasTouch, int numbOfSims, long id) {
        Phone phone = this.getPhoneById(id);
        phone.setPrice(price);
        phone.setName(name);
        phone.setVoltage(voltage);
        phone.setHasTouch(hasTouch);
        phone.setNumbOfSims(numbOfSims);

        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(phone);
        transaction.commit();
        session.close();
    }

    public List<Phone> getAllPhones() {
        session = factory.openSession();
        final List<Phone> resultList = session.createQuery("SELECT b FROM Phone b",Phone.class).getResultList();
//        final List resultList = session.createQuery("from book").list();
        session.close();
        return resultList;
    }
}
