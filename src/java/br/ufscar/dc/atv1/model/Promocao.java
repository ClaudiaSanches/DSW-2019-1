/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.atv1.model;

/**
 *
 * @author 743528
 */
public class Promocao {
    private int id;
    private String nome;
    private float preco;
    private String diahorario;
    private String site;
    private String teatro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Promocao(int id) {
        this.id = id;
    }
    
       

    public Promocao(int id, String nome, float preco, String diahorario, String site, String teatro) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.diahorario = diahorario;
        this.site = site;
        this.teatro = teatro;
    }

    public String getPeça() {
        return nome;
    }

    public void setPeça(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }


    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTeatro() {
        return teatro;
    }

    public void setTeatro(String teatro) {
        this.teatro = teatro;
    }

    public String getDiahorario() {
        return diahorario;
    }

    public void setDiahorario(String diahorario) {
        this.diahorario = diahorario;
    }


    
    
}