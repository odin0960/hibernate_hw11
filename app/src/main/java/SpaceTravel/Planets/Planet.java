package SpaceTravel.Planets;

import SpaceTravel.Tickets.Ticket;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static java.lang.System.lineSeparator;

@Getter
@Setter
@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false, length = 500)
    private String name;

    @OneToMany(mappedBy = "fromPlanet")
//    @OneToMany(mappedBy = "fromPlanet", fetch = FetchType.EAGER)
    private Set<Ticket> ticketsFrom;

    @OneToMany(mappedBy = "toPlanet")
    private Set<Ticket> ticketsTo;

    public Planet() {
    }

    public void setId(String id) {
        try {
            if (id.matches("^[A-Z0-9]+$")) {
                this.id = id;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception ex) {
            System.out.println("The Planet ID has to consist only of digits and upper-case characters");
        }
    }

    public Planet(String id, String name) {
        try {
            if (id.matches("^[A-Z0-9]+$")) {
                this.id = id;
                this.name = name;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception ex) {
            System.out.println("The Planet ID has to consist only of digits and upper-case characters");
        }
    }

//    public void addTicketFrom(Ticket ticket) {
//        ticketsFrom.add(ticket);
//        ticket.setFromPlanet(this);
//    }
//
//    public void removeTicketFrom(Ticket ticket) {
//        ticketsFrom.add(ticket);
//        ticket.setFromPlanet(null);
//    }
//
//    public void addTicketTo(Ticket ticket) {
//        ticketsTo.add(ticket);
//        ticket.setToPlanet(this);
//    }
//
//    public void removeTicketTo(Ticket ticket) {
//        ticketsTo.add(ticket);
//        ticket.setToPlanet(null);
//    }

    @Override
    public String toString() {
        return "Planet {ID: " + this.getId() + ", name: " + name + "}";
    }
}
