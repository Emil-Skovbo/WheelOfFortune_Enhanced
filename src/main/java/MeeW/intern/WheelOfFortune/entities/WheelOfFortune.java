package MeeW.intern.WheelOfFortune.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class WheelOfFortune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public WheelOfFortune() {
        super();
    }

   /* @OneToMany
    @ToString.Exclude
    private List<Field> fields;
*/
}
