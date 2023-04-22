package br.com.imd.distribuida.model;

import br.com.imd.distribuida.exception.*;
import br.com.imd.distribuida.interfaces.*;

import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;

public class Restaurant extends UnicastRemoteObject implements RestaurantCRUD{

    private List<Table> tables;

    private final int MAX_TABLES;

    public Restaurant(final int MAX_TABLES) throws RemoteException {
        super();
        this.MAX_TABLES = MAX_TABLES;
        this.tables = new ArrayList<>(MAX_TABLES + 1);
        initializeTables(this.tables);
    }

    /**
     *
     * @param tables
     */
    private void initializeTables(List<Table> tables) {
        for(int i=0; i<MAX_TABLES; i++){
            int seats = ThreadLocalRandom.current().nextInt(1, 11) % 2 == 0 ? 4 : 8;
            tables.add(new Table(seats, false, "None"));
        }
    }

    public Restaurant() throws RemoteException {super(); MAX_TABLES = 10;}

    /**
     *
     * @return all tables
     */
    public List<Table> findAll(){
        Logger.getLogger("Restaurant").info("Get all tables.");
        return this.tables;
    }

    /**
     *
     * @param customer
     * @return a table that have provided customer
     * @throws RestaurantException
     */
    public Table findByCustomer(String customer) throws RestaurantException {
        return tables.stream().filter(table -> table.getReservedBy().equals(customer))
                .findFirst()
                .orElseThrow(() -> new RestaurantException("Customer not found."));
    }

    /**
     *
     * @param customer
     * @param numberOfSeats
     * @return if is reserved
     */
    public boolean reserve(String customer, int numberOfSeats){
        for(Table table: tables){
            if(table.isNotReserved() && table.getNumberOfSeats() == numberOfSeats){
                table.setReserved(true);
                table.setReservedBy(customer);
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param customer
     * @param numberOfSeats
     * @return if was updated
     */
    public boolean updateReserve(String customer, int numberOfSeats){
        Optional<Table> customerTableOptional = tables.stream()
                .filter(table -> table.getReservedBy().equals(customer))
                .findFirst();

        if(customerTableOptional.isEmpty())
            throw new RestaurantException("Customer don't exists.");

        if(customerTableOptional.get().getNumberOfSeats() == numberOfSeats)
            throw new RestaurantException("This table already have this number of seats.");

        Table customerTable = customerTableOptional.get();

        for(Table table: tables){
            if(table.getNumberOfSeats() == numberOfSeats && table.isNotReserved()){
                table.setReserved(true);
                table.setReservedBy(customerTable.getReservedBy());

                customerTable.setReservedBy("");
                customerTable.setReserved(false);

                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param customer
     * @return if remove are successful
     */
    public boolean removeReserve(String customer){
        Optional<Table> customerTable = tables.stream()
                .filter(table -> table.getReservedBy().equals(customer))
                .findFirst();

        return customerTable.map(table -> tables.remove(table)).orElse(false);
    }

    /**
     *
     * @return all tables
     */
    public List<Table> getTables() {
        return tables;
    }
}
