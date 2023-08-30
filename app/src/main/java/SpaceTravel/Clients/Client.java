package SpaceTravel.Clients;

import SpaceTravel.Tickets.Ticket;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import static java.lang.System.lineSeparator;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @OneToMany(mappedBy="client")
//    @OneToMany(mappedBy="client", fetch = FetchType.EAGER)
    private Set<Ticket> tickets;

    public Client() {
    }

    public Client (String name) {
        try {
            if (name == null || name.length() > 200 || name.length() < 3) {
                throw new IllegalArgumentException();
            } else {
                this.name = name;
            }
        } catch (Exception ex) {
            System.out.println("The name length must be greater than 2 character but less than 200 characters");
        }
    }

    public void setName(String name) {
        try {
            if (name == null || name.length() > 200 || name.length() < 3) {
                throw new IllegalArgumentException();
            } else {
                this.name = name;
            }
        } catch (Exception ex) {
            System.out.println("The name length must be greater than 2 character but less than 200 characters");
        }
    }

//    public void addTicket(Ticket ticket) {
//        tickets.add(ticket);
//        ticket.setClient(this);
//    }
//
//    public void removeTicket(Ticket ticket) {
//        tickets.remove(ticket);
//        ticket.setClient(null);
//    }

    @Override
    public String toString() {
        return "Client { ID: " + this.getId() + ", name: " + name + "}";
    }
}
