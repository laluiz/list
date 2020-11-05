package com.example.list.modelo;


import java.io.Serializable;

public class Evento implements Serializable {
    private int id;
    private String nomeEvento;
    private String dataEvento;
    private Local local;

    public Evento(int id, String nomeEvento, String dataEvento, Local local) {
        this.id = id;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public Local getLocal() { return local; }

    public void setLocal(Local local) { this.local = local; }

    public String toString() {
        return id + " - " + nomeEvento ;
    }
}
