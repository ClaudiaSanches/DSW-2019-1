/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.atv1;

/**
 *
 * @author Ellen-
 */
import br.ufscar.dc.atv1.dao.PromocaoDAO;
import br.ufscar.dc.atv1.dao.SiteDAO;
import br.ufscar.dc.atv1.dao.TeatroDAO;
import br.ufscar.dc.atv1.model.Promocao;
import br.ufscar.dc.atv1.model.Site;
import br.ufscar.dc.atv1.model.Teatro;
import java.util.List;

public class DynamicSelectBean {
    
    public List<String> getCidades() {
        TeatroDAO dao = new TeatroDAO();
        return dao.getCity();
    }
    
    public List<Teatro> getTeatrosByCity(String city){        
        TeatroDAO dao = new TeatroDAO();
        return dao.getByCity(city);        
    }
    
    public List<Teatro> getTeatros() {               
        TeatroDAO dao = new TeatroDAO();
        return dao.getAll();        
    }
    
    public List<Site> getSites() {        
        SiteDAO dao = new SiteDAO();
        return dao.getAll();        
    }
    
    public List<Promocao> getPromocoes() {
        PromocaoDAO dao = new PromocaoDAO();
        return dao.getAll();
    }
    
    public List<Promocao> getPromocoesByCnpj(String cnpj) {
        PromocaoDAO dao = new PromocaoDAO();
        return dao.getByCnpj(cnpj);
    }
    
    public String getTeatroNome(String cnpj){
        TeatroDAO dao = new TeatroDAO();
        return dao.get(cnpj).getNome();
    }
    
    public String getSiteNome(String url) {
        SiteDAO dao = new SiteDAO();
        return dao.get(url).getNome();
    }
    
}