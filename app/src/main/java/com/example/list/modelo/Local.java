package com.example.list.modelo;

import java.io.Serializable;

public class Local implements Serializable {
    private int id;
    private String Nome;
    private String Bairro;
    private String Cidade;
    private int CapacidadeMaxima;

    public Local(int id, String nome, String bairro, String cidade, int capacidadeMaxima) {
        this.id = id;
        Nome = nome;
        Bairro = bairro;
        Cidade = cidade;
        CapacidadeMaxima = capacidadeMaxima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public int getCapacidadeMaxima() {
        return CapacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        CapacidadeMaxima = capacidadeMaxima;
    }

    public String toString() {
        return Nome + " - " + Bairro + " - " + Cidade + " - " + CapacidadeMaxima;
    }
}
