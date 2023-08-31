package SpaceTravel.Planets;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Override
    public String toString() {
        return "Planet {ID: " + this.getId() + ", name: " + name + "}";
    }
}
