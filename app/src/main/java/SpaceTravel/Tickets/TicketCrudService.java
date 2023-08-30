package SpaceTravel.Tickets;

import SpaceTravel.Clients.Client;
import SpaceTravel.Clients.ClientCrudService;
import SpaceTravel.DatabaseServices.HibernateUtil;
import SpaceTravel.Planets.PlanetCrudService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TicketCrudService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketCrudService.class);

    public boolean create(Ticket ticket) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            if (validateTicket(ticket)) {
                Transaction transaction = session.beginTransaction();
                session.persist(ticket);
                transaction.commit();
                return true;
            }
        } catch (Exception ex) {
            LOGGER.error("Invalid ticket data", ex);
            ex.printStackTrace();
        }
        return false;
    }

    public Ticket getById(long id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            if (session.get(Ticket.class, id) == null) {
                throw new IllegalArgumentException();
            } else {
                return session.get(Ticket.class, id);
            }
        } catch (IllegalArgumentException ex) {
            LOGGER.error("The ticket with such ID is not exist", ex);
        }
        return null;
    }

    public List<Ticket> allTickets() {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        }
    }

    public List<Ticket> ticketsByClient(Client client) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Query<Ticket> query = session.createQuery("from Ticket where client = :client_id", Ticket.class);
            query.setParameter("client_id", client);
            return query.list();
        } catch (Exception ex) {
            LOGGER.error("The client is not exist", ex);
        }
        return null;
    }

    public boolean update(Ticket ticket) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            if (validateTicket(ticket)) {
                Transaction transaction = session.beginTransaction();
                session.merge(ticket);
                transaction.commit();
                return true;
            }
        } catch (Exception ex) {
            LOGGER.error("Invalid ticket data", ex);
            ex.printStackTrace();
        }
        return false;
    }

    public void delete(Ticket ticket) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            if (validateTicket(ticket)) {
                Transaction transaction = session.beginTransaction();
                session.remove(ticket);
                transaction.commit();
            }
        } catch (Exception ex) {
            LOGGER.error("Invalid ticket data", ex);
            ex.printStackTrace();
        }
    }


    public boolean validateTicket(Ticket ticket) {
        ClientCrudService clientService = new ClientCrudService();
        PlanetCrudService planetService = new PlanetCrudService();

        boolean ticketStatus = ticket != null
                && ticket.getClient() != null
                && ticket.getFromPlanet() != null
                && ticket.getToPlanet() != null
                && !ticket.getFromPlanet().equals(ticket.getToPlanet())
                && clientService.getById(ticket.getClient().getId()) != null
                && planetService.getById(ticket.getToPlanet().getId()) != null
                && planetService.getById(ticket.getFromPlanet().getId()) != null;

        try {
            if (!ticketStatus) throw new IllegalArgumentException();
            return ticketStatus;
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Invalid ticket data", ex);
        }
        return false;
    }
}
