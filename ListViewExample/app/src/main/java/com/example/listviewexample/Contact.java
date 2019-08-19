package com.example.listviewexample;

public class Contact {

    private String nome;
    private String telefone;
    private String email;
    private int imgID;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    @Override
    public String toString() {
        return getNome() + ": " + getTelefone();
    }

    public String getEmail() {
        if (email == null) {
            return "Não informado!";
        } else {
            return email;
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
