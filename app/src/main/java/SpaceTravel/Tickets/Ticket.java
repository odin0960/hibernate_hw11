package SpaceTravel.Tickets;

import SpaceTravel.Clients.Client;
import SpaceTravel.Planets.Planet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.System.lineSeparator;

@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "created_at")
//    private LocalDateTime createdAt;
    private String createdAt;

    @Getter
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Getter
    @ManyToOne
    @JoinColumn(name = "from_planet_id", nullable = false)
    private Planet fromPlanet;

    @Getter
    @ManyToOne
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet toPlanet;

    public Ticket() {
    }

    public Ticket(Client client, Planet fromPlanet, Planet toPlanet) {
        try {
            if (fromPlanet.equals(toPlanet) || client == null || fromPlanet == null || toPlanet == null) {
                throw new IllegalArgumentException();
            }
            this.createdAt = ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            this.createdAt = LocalDateTime.now(ZoneId.of("UTC"));
            this.client = client;
            this.fromPlanet = fromPlanet;
            this.toPlanet = toPlanet;
        } catch (IllegalArgumentException ex) {
            System.out.println("the point of departure and the point of arrival cannot be the same or null!!! Please, choose  other planets.");
        }
    }

//    public void setClient(Client client) {
//        this.client = client;
//    }

    @Override
    public String toString() {
        return "Ticket { ID: " + this.getId() + "," + lineSeparator()
                + "Date: " + this.getCreatedAt() + "," + lineSeparator()
                + "Client: " + this.getClient() + "," + lineSeparator()
                + "From planet: " + this.getFromPlanet() + "," + lineSeparator()
                + "To planet: " + this.getToPlanet() + lineSeparator()
                + "}";
    }
}