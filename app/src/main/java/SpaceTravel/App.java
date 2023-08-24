package SpaceTravel;

import SpaceTravel.Clients.ClientCrudService;
import SpaceTravel.DatabaseServices.DatabaseInitService;
import SpaceTravel.Planets.Planet;
import SpaceTravel.Planets.PlanetCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();

        new DatabaseInitService().initDb();

        String name = "George Bush";
        String name3 = "X";
        String newName = "Donald Trump";

        ClientCrudService clientService = new ClientCrudService();
        clientService.newClient(name);
        clientService.newClient(name3);
        LOGGER.info("Client_By_Id = %s".formatted(clientService.getById(10L))); //Client(id=10, name=Oleksandr Karavayev)
        LOGGER.info("Client_By_Name = %s".formatted(clientService.getByName(name))); //Client(id=13, name=George Bush)
        LOGGER.info("All_Clients = %s".formatted(clientService.allClients()));
        clientService.update(13L, newName);
        LOGGER.info("Client_By_Id = %s".formatted(clientService.getById(13L))); //Client(id=13, name=Donald Trump)
        LOGGER.info("All_Clients = %s".formatted(clientService.allClients()));
        clientService.delete(13L);
        LOGGER.info("All_Clients = %s".formatted(clientService.allClients()));

        PlanetCrudService planetService = new PlanetCrudService();
        Planet planet = new Planet();
        planet.setName("Pluto");
        planet.setId("PLU09");
        planetService.create(planet);
        LOGGER.info("Planet_By_Id = %s".formatted(planetService.getById("PLU09"))); // Planet(id=PLU09, name=Pluto)
        LOGGER.info("Planet_By_Name = %s".formatted(planetService.getByName("Pluto"))); // Planet(id=PLU09, name=Pluto)
        LOGGER.info("All_Planets = %s".formatted(planetService.allPlanets()));
        planetService.updateName("PLU09", "Pluton");
        LOGGER.info("Planet_By_Id = %s".formatted(planetService.getById("PLU09"))); // Planet(id=PLU999, name=Pluton)
        LOGGER.info("All_Planets = %s".formatted(planetService.allPlanets()));
        planetService.delete("PLU09");
        LOGGER.info("All_Planets = %s".formatted(planetService.allPlanets()));
    }
}
