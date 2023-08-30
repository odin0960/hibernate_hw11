package SpaceTravel.Clients;

import SpaceTravel.DatabaseServices.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClientCrudService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientCrudService.class);

    public void create(Client client) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        } catch (Exception ex) {
            LOGGER.error("The name length must be greater than 2 character but less than 200 characters", ex);
        }
    }

    public Client getById(long id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            if (session.get(Client.class, id) == null) {
                throw new IllegalArgumentException();
            } else {
                return session.get(Client.class, id);
            }
        } catch (IllegalArgumentException ex) {
            LOGGER.error("The client with such ID is not exist", ex);
        }
        return null;
    }

    public Client getByName(String name) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Query<Client> query = session.createQuery("from Client where name = :name", Client.class);
            query.setParameter("name", name);
            if (query.getSingleResult() == null) {
                throw new IllegalArgumentException();
            } else {
                return query.getSingleResult();
//            return query.stream().findFirst().orElse(null);
            }
        } catch (Exception ex) {
            LOGGER.error("The client with such Name is not exist", ex);
        }
        return null;
    }

    public List<Client> allClients() {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return session.createQuery("from Client", Client.class).list();
        }
    }

    public void update(long id, String newName) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Client updatedClient = getById(id);
            if (updatedClient == null) {
                throw new IllegalArgumentException();
            } else {
                Transaction transaction = session.beginTransaction();
                updatedClient.setName(newName);
                session.merge(updatedClient);
                transaction.commit();
            }
        } catch (Exception ex) {
            LOGGER.error("The client with such ID is not exist", ex);
        }
    }

    public void delete(long id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Client client = getById(id);
            if (client == null) {
                throw new IllegalArgumentException();
            } else {
                Transaction transaction = session.beginTransaction();
                session.remove(client);
                transaction.commit();
            }
        } catch (Exception ex) {
            LOGGER.error("The client with such ID is not exist", ex);
        }
    }
}
