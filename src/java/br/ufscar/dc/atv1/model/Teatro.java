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
public class Teatro {
    private String email;
    private String CNPJ;
    private String nome;
    private String cidade;

    public Teatro(String email, String CNPJ, String nome, String cidade) {
        this.email = email;
        this.CNPJ = CNPJ;
        this.nome = nome;
        this.cidade = cidade;
    }

    public Teatro(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    
    
}