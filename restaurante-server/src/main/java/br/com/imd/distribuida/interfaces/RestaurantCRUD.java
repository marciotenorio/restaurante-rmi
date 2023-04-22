package br.com.imd.distribuida.interfaces;

import br.com.imd.distribuida.model.*;

import java.rmi.*;
import java.util.*;

public interface RestaurantCRUD extends Remote {

    Table findByCustomer(String customer) throws RemoteException;
    boolean reserve(String customer, int numberOfSeats) throws RemoteException;
    boolean updateReserve(String customer, int numberOfSeats) throws RemoteException;
    boolean removeReserve(String customer) throws RemoteException;
    List<Table> findAll() throws RemoteException;
}
