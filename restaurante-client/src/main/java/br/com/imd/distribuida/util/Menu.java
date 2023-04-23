package br.com.imd.distribuida.util;

import br.com.imd.distribuida.interfaces.*;
import br.com.imd.distribuida.model.*;

import java.io.*;
import java.rmi.*;
import java.util.*;

public class Menu {

    private RestaurantCRUD restaurant;
    private Scanner scanner = new Scanner(System.in);

    public Menu(RestaurantCRUD restaurante) {
        this.restaurant = restaurante;
    }

    public void menu(){
        int op = -1;

        while(op != 0){
            System.out.println("-------- Bem-vindo ao Restaurante Distribuído --------");
            System.out.println("Digite o número correspondente da ação: ");
            System.out.println("1. Para fazer uma reserva");
            System.out.println("2. Para atualizar uma reserva");
            System.out.println("3. Para remover uma reserva");
            System.out.println("4. Para encontrar uma mesa e o número de cadeiras");
            System.out.println("5. Para mostrar todas as reservas");
            System.out.println("0. Para encerrar o programa");

            op = scanner.nextInt();

            switch (op){
                case 1 -> reserva();
                case 2 -> update();
                case 3 -> remove();
                case 4 -> findByCustomer();
                case 5 -> findAll();
                default -> System.out.println("Encerrando o cliente...");
            }
        }

    }

    private void reserva() {

            String customer;
            int numberOfSeats;

            System.out.println("Nome do dono da reserva: ");
            scanner.nextLine();
            customer = scanner.nextLine();

            System.out.println("Quantidade de lugares a mesa deve ter: ");
            numberOfSeats = scanner.nextInt();

        boolean result = false;
        try {
            result = restaurant.reserve(customer, numberOfSeats);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        if(result){
                System.out.println("Reserva feita com sucesso.");
            }
            else{
                System.out.println("Não existe mesa disponível.");
            }
    }

    private void update(){
        try{
            String customer;
            int numberOfSeats;

            System.out.println("Nome do dono da reserva: ");
            scanner.nextLine();
            customer = scanner.nextLine();

            System.out.println("Nova quantidade de lugares da mesa: ");
            numberOfSeats = scanner.nextInt();

            boolean result = restaurant.updateReserve(customer, numberOfSeats);

            if(result){
                System.out.println("Atualização da reserva feita com sucesso.");
            }
            else{
                System.out.println("Não existe mesa disponível.");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void remove(){
        String customer;

        System.out.println("Digite o nome da reserva para remoção: ");
        scanner.nextLine();
        customer = scanner.nextLine();

        try {
            boolean result = restaurant.removeReserve(customer);

            if(!result) System.out.println("Não foi possível concluir a remoção.");
            else System.out.println("Remoção concluída com sucesso.");

            menu();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    private void findByCustomer(){
        String customer;

        System.out.println("Digite o nome da reserva para encontrar a mesa: ");
        scanner.nextLine();
        customer = scanner.nextLine();

        try {
            Table table = restaurant.findByCustomer(customer);

            if(table != null){
                System.out.println("Nome da reserva: " + table.getReservedBy());
                System.out.println("Número de lugares da mesa: " + table.getNumberOfSeats());
            }

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private void findAll(){
        try {
            List<Table> tables = restaurant.findAll();

            System.out.println("------ Exibindo todas as mesas ------");

            for(Table table: tables){
                System.out.println("Nome da reserva: " + table.getReservedBy()
                        + ". Número de lugares: " + table.getNumberOfSeats());
            }

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
