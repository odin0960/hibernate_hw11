package SpaceTravel;

import SpaceTravel.Clients.Client;
import SpaceTravel.Clients.ClientCrudService;
import SpaceTravel.DatabaseServices.DatabaseInitService;
import SpaceTravel.DatabaseServices.HibernateUtil;
import SpaceTravel.Planets.Planet;
import SpaceTravel.Planets.PlanetCrudService;
import SpaceTravel.Tickets.Ticket;
import SpaceTravel.Tickets.TicketCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

@Slf4j
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();

        new DatabaseInitService().initDb();

        ClientCrudService clientService = new ClientCrudService();
        PlanetCrudService planetService = new PlanetCrudService();
        TicketCrudService ticketService = new TicketCrudService();

        Client client = clientService.getById(8L);
        Planet fromPlanet = planetService.getById("EAR03");
        Planet toPlanet = planetService.getById("MER01");

        Ticket ticket = new Ticket(client, fromPlanet, toPlanet);
        client.setTickets(Set.of(ticket));
        ticketService.create(ticket);
        System.out.println(ticket);

        Client newClient = new Client("John Smith");
        Planet newPlanet = new Planet("PLU09", "Pluto");
        Ticket newTicket = new Ticket(newClient, fromPlanet, newPlanet);
        client.setTickets(Set.of(newTicket));
        ticketService.create(newTicket);
        System.out.println(newTicket);

        LOGGER.info("Ticket_By_Id = %s".formatted(ticketService.getById(5L))); //
        LOGGER.info("All_Tickets = %s".formatted(ticketService.allTickets()));
        LOGGER.info("Tickets_By_Client = %s".formatted(ticketService.ticketsByClient(client)));

        ticketService.delete(ticketService.getById(23L));

        Ticket ticketUpdated = ticketService.getById(18L);
        clientService.create(newClient);
        planetService.create(newPlanet);
        ticketUpdated.setClient(newClient);
        ticketUpdated.setFromPlanet(newPlanet);
        ticketUpdated.setToPlanet(toPlanet);
        ticketService.update(ticketUpdated);

//        HibernateUtil.getInstance().close();
    }
}
