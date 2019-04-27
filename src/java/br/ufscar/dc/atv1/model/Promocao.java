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
    private String peça;
    private double preço;
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
    
       

    public Promocao(String peça, double preço, String diahorario, String site, String teatro) {        
        this.peça = peça;
        this.preço = preço;
        this.diahorario = diahorario;
        this.site = site;
        this.teatro = teatro;
    }
    
    public Promocao(int id, String peça, double preço, String diahorario, String site, String teatro) {        
        this.id = id;
        this.peça = peça;
        this.preço = preço;
        this.diahorario = diahorario;
        this.site = site;
        this.teatro = teatro;
    }

    public String getPeça() {
        return peça;
    }

    public void setPeça(String peça) {
        this.peça = peça;
    }

    public double getPreço() {
        return preço;
    }

    public void setPreço(float preço) {
        this.preço = preço;
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