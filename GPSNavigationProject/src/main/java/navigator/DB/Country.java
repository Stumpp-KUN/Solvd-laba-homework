package navigator.DB;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="countrys")
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name="country")
    private String country;

    @Column(name="x")
    private int x;

    @Column
    private int y;

    public Country(String country, int x, int y) {
        this.country = country;
        this.x = x;
        this.y = y;
    }

    public Country() {
    }
}
