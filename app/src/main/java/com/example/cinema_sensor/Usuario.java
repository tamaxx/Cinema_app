package com.example.cinema_sensor;

public class Usuario {

    private int id;
    private String nome;
    private String senha;

    public Usuario(){}

    public Usuario(int id, String nome, String senha){
        this.setId(id);
        this.setNome(nome);
        this.setSenha(senha);
    }

    public Usuario(String nome, String senha){
        this.nome = this.nome;
        this.senha = this.senha;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

}
