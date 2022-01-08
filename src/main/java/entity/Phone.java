package entity;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("Phone")
public class Phone extends ProdusElectronic {

    @Column(name = "NumberOfSims", length = 100)
    private int numbOfSims;

    @Column(name = "HasTouch")
    private boolean hasTouch;

    public Phone() {
        super();
    }

    public Phone(double price, String name, int numbOfSims, boolean hasTouch, int voltage) {
        super(price, name, voltage);
        this.numbOfSims = numbOfSims;
        this.hasTouch = hasTouch;
    }

    public int getNumbOfSims() {
        return numbOfSims;
    }

    public void setNumbOfSims(int numbOfSims) {
        this.numbOfSims = numbOfSims;
    }

    public boolean isHasTouch() {
        return hasTouch;
    }

    public void setHasTouch(boolean hasTouch) {
        this.hasTouch = hasTouch;
    }

    @Override
    public String prettyPrint() {
        return super.prettyPrint() +
                "\nNumber of sims: " + this.numbOfSims +
                "\nHas touch?: " + this.hasTouch;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "numbOfSims=" + numbOfSims +
                ", hasTouch=" + hasTouch +
                "} " + super.toString();
    }
}
