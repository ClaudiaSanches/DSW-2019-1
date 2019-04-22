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
import br.ufscar.dc.atv1.dao.TeatroDAO;
import br.ufscar.dc.atv1.model.Teatro;
import java.util.List;

public class DynamicSelectBean {
    
    public List<String> getCidades() {
        TeatroDAO dao = new TeatroDAO();
        return dao.getCity();
    }
    
    public List<Teatro> getTeatros() {
        TeatroDAO dao = new TeatroDAO();
        return dao.getAll();
    }
    
}
