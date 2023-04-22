package br.com.imd.distribuida;

import br.com.imd.distribuida.interfaces.*;
import br.com.imd.distribuida.model.*;

import java.net.*;
import java.rmi.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        RestaurantCRUD restaurant = (RestaurantCRUD)
                Naming.lookup("rmi://0.0.0.0:1098/restaurante-server");

        System.out.println(restaurant.reserve("Márcio", 4));
        List<Table> all = restaurant.findAll();

        for (Table table: all) System.out.println(table.getReservedBy());
    }
}