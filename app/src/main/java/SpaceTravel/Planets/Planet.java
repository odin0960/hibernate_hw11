package SpaceTravel.Planets;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @Column (name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false, length = 500)
    private String name;
}
