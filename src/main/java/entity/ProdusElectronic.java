package entity;

import javax.persistence.*;

public abstract class ProdusElectronic extends Produs {

    @Column(name = "Voltage", length = 100)
    private int voltage;

    public ProdusElectronic(double price, String name, int voltage) {
        super(price, name);
        this.voltage = voltage;
    }

    public ProdusElectronic() {
        super();
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    @Override
    public String toString() {
        return "ProdusElectronic{" +
                "voltage=" + voltage +
                "} " + super.toString();
    }

    @Override
    public String prettyPrint() {
        return super.prettyPrint() +
                "\nVoltage: " + this.voltage;
    }
}
