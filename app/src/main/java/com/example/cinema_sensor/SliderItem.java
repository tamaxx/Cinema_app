package com.example.cinema_sensor;

public class SliderItem {

    private int image;
    private String nome;
    private String generos;
    private String diretor;
    private String sinopse;

    SliderItem(int image, String nome, String generos, String diretor, String sinopse)
    {
        this.image = image;
        this.nome = nome;
        this.generos = generos;
        this.diretor = diretor;
        this.sinopse = sinopse;
    }

    public int getImage(){
        return image;
    }
    public String getNome(){
        return nome;
    }
    public String getGeneros(){
        return generos;
    }
    public String getDiretor(){
        return  diretor;
    }
    public String getSinopse(){
        return sinopse;
    }
}
