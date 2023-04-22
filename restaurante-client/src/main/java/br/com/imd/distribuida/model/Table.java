package br.com.imd.distribuida.model;

import java.io.*;
import java.util.*;

public class Table implements Serializable {
    private int numberOfSeats;

    private boolean isReserved;

    private String reservedBy;

    public Table(int numberOfSeats, boolean isReserved, String reservedBy) {
        this.numberOfSeats = numberOfSeats;
        this.isReserved = isReserved;
        this.reservedBy = reservedBy;
    }

    public Table() {}

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public boolean isNotReserved(){return !isReserved;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(reservedBy, table.reservedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservedBy);
    }
}
