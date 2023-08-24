package SpaceTravel.Planets;

import SpaceTravel.DatabaseServices.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PlanetCrudService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlanetCrudService.class);

    public void create(Planet planet) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
        } catch (Exception ex) {
            LOGGER.error("The Planet name has to consist only digits and upper case", ex);
        }
    }

    public Planet getById(String id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            if (session.get(Planet.class, id) == null) {
                throw new IllegalArgumentException();
            } else {
                return session.get(Planet.class, id);
            }
        } catch (Exception ex) {
            LOGGER.error("The planet with such ID is not exist", ex);
        }
        return null;
    }

    public Planet getByName(String name) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Query<Planet> query = session.createQuery("from Planet where name = :name", Planet.class);
            query.setParameter("name", name);
            if (query.getSingleResult() == null) {
                throw new IllegalArgumentException();
            } else {
                return query.getSingleResult();
//            return query.stream().findFirst().orElse(null);
            }
        } catch (Exception ex) {
            LOGGER.error("The planet with such Name is not exist", ex);
        }
        return null;
    }

    public List<Planet> allPlanets() {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return session.createQuery("from Planet", Planet.class).list();
        }
    }

    public void updateName(String id, String newName) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Planet updatedPlanet = getById(id);
            if (updatedPlanet == null) {
                throw new IllegalArgumentException();
            } else {
                Transaction transaction = session.beginTransaction();
                updatedPlanet.setName(newName);
                session.merge(updatedPlanet);
                transaction.commit();
            }
        } catch (Exception ex) {
            LOGGER.error("The planet with such ID is not exist", ex);
        }
    }

    public void delete(String id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Planet planet = getById(id);
            if (planet == null) {
                throw new IllegalArgumentException();
            } else {
                Transaction transaction = session.beginTransaction();
                session.remove(planet);
                transaction.commit();
            }
        } catch (Exception ex) {
            LOGGER.error("The planet with such ID is not exist", ex);
        }
    }
}