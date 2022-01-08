package entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name="Products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="product_type",
//        discriminatorType = DiscriminatorType.STRING)
public abstract class Produs implements Serializable {

    private static final long serialVersionUID = 7084896588259376416L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Price", length = 100)
    private double price;

    @Column(name = "Name", length =100)
    private String name;

    public Produs(long id, double price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public Produs() {

    }

    public Produs(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    public String prettyPrint() {
        return "ID: " + this.id +
                "\nPrice: " + this.price +
                "\nProduct name: " + this.name;
    }
}
