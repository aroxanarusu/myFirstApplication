package dao;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import entity.Produs;
import util.HibernateUtils;

import java.util.List;

public class ProdusDAO {

    static Logger log = Logger.getLogger(LaptopDAO.class.getName());

    Session session;
    SessionFactory factory;

    private static ProdusDAO produsDAO;

    public static ProdusDAO getInstance() {

        if (produsDAO == null) {
            produsDAO = new ProdusDAO();
        }
        return produsDAO;
    }

    public ProdusDAO(){factory= HibernateUtils.getSessionFactory();}

    public List<Produs> getAllProducts() {
        session = factory.openSession();
        final List<Produs> resultList = session.createQuery("SELECT b FROM Products b", Produs.class).getResultList();
        session.close();
        return resultList;
    }

    public Produs getProductById(Long id) {
        log.info("Get book by id: " + id);
        Session session = factory.openSession();
        Produs prod = (session.get(Produs.class, id));
        session.close();
        return prod;
    }

    public void deleteFromTable(Long id) {
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(this.getProductById(id));
        transaction.commit();
        session.close();
    }
}
