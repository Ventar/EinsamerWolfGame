package net.atos.wolf.services;

public class BattleValue {


    private int f;
    private int ew;


    public BattleValue(int f, int ew) {
        this.f = f;
        this.ew = ew;
    }

    @Override
    public String toString() {
        return "BattleValue{" +
                "f=" + f +
                ", ew=" + ew +
                '}';
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getEw() {
        return ew;
    }

    public void setEw(int ew) {
        this.ew = ew;
    }
}
