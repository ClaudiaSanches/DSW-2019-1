/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.atv1;

import br.ufscar.dc.atv1.dao.TeatroDAO;
import br.ufscar.dc.atv1.model.Teatro;
import java.util.List;

/**
 *
 * @author 743528
 */
public class Lista {
    public List<Teatro> getTeatros() {
        TeatroDAO dao = new TeatroDAO();
        return dao.getAll();
    }
    
}
