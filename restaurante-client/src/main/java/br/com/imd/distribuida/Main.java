package br.com.imd.distribuida;

import br.com.imd.distribuida.interfaces.*;
import br.com.imd.distribuida.model.*;
import br.com.imd.distribuida.util.*;

import java.net.*;
import java.rmi.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        RestaurantCRUD restaurant = (RestaurantCRUD)
                Naming.lookup("rmi://0.0.0.0:1098/restaurante-server");

        Menu menu = new Menu(restaurant);

        menu.menu();
    }
}