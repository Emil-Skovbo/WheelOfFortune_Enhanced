package MeeW.intern.WheelOfFortune.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Winner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne
    private Field field;

    public Winner() {
        super();
    }
}
