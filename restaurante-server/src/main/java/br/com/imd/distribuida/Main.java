package br.com.imd.distribuida;

import br.com.imd.distribuida.interfaces.*;
import br.com.imd.distribuida.model.*;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class Main {
    public static void main(String[] args) {
        try {
            String name = "restaurante-server";
            Restaurant restaurant = new Restaurant(10);
            Registry registry = LocateRegistry.createRegistry(1098);
            Naming.rebind("rmi://0.0.0.0:1098/restaurante-server", restaurant);
        } catch (Exception e) {
            System.err.println("Restaurant-server exception:");
            e.printStackTrace();
        }
    }
}