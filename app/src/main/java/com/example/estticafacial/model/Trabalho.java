package com.example.estticafacial.model;

public class Trabalho {
    public String nome;
    public String imagem;
    public String video;
    public double preco;
    private String key;

    public Trabalho() {

    }

    public Trabalho(String nome, String imagem, String video, double preco) {
        this.nome = nome;
        this.imagem = imagem;
        this.video = video;
        this.preco = preco;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
