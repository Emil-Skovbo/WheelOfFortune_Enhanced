package MeeW.intern.WheelOfFortune.entities;

import lombok.Data;

import javax.persistence.*;

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

}
