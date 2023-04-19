package br.com.imd.distribuida.model;

import java.util.*;

public class Restaurant {

    private List<Table> tables;

    public Restaurant(final int MAX_TABLES) {
        this.tables = new ArrayList<>(MAX_TABLES);
        initiliazeTables(this.tables);
    }

    private void initiliazeTables(List<Table> tables) {
        for(int i=0; i<tables.toArray().length; i++){

        }
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }


}
