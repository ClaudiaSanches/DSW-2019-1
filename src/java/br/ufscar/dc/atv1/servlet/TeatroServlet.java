package br.ufscar.dc.atv1.servlet;

import br.ufscar.dc.atv1.DynamicSelectBean;
import br.ufscar.dc.atv1.model.Promocao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/buscaPorTeatro"})
public class TeatroServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        response.setContentType("text/html;charset=UTF-8");
        String teatro = request.getParameter("teatro");
        String buffer = "";
        List<Promocao> promocoes;
        if("null".equals(teatro)){
            promocoes = new DynamicSelectBean().getPromocoes();
        }
        else{
            promocoes = new DynamicSelectBean().getPromocoesByCnpj(teatro);
        }
        for (Promocao promocao : promocoes) {
            buffer = buffer + "<tr><td>"+promocao.getPeça()+"</td><td>"+Double.toString(promocao.getPreço())+"</td><td>"+promocao.getDiahorario()+"</td><td>"+new DynamicSelectBean().getSiteNome(promocao.getSite())+"</td><td>"+new DynamicSelectBean().getTeatroNome(promocao.getTeatro())+"</td></tr>";
        }   
        response.getWriter().println(buffer);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
