package br.ufscar.dc.atv1;

import br.ufscar.dc.atv1.model.Promocao;
import br.ufscar.dc.atv1.model.Site;
import br.ufscar.dc.atv1.model.Teatro;
import br.ufscar.dc.atv1.dao.SiteDAO;
import br.ufscar.dc.atv1.dao.PromocaoDAO;
import br.ufscar.dc.atv1.dao.TeatroDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/")
public class Controller extends HttpServlet {
    
    private SiteDAO sitedao;
    private TeatroDAO teatrodao;
    private PromocaoDAO promocaodao;
    
    @Override
    public void init() {
        try {
            sitedao = new SiteDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            teatrodao = new TeatroDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        promocaodao = new PromocaoDAO();
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/admin/listaSites":
                    listaSites(request, response);
                    break;  
                case "/admin/cadastroSite":
                    apresentaFormCadastroSite(request, response);
                    break;
                case "/admin/insercaoSite":
                    insereSite(request, response);
                    break;
                case "/admin/remocaoSite":
                    removeSite(request, response);
                    break;
                case "/admin/edicaoSite":
                    apresentaFormEdicaoSite(request, response);
                    break;
                case "/admin/atualizacaoSite":
                    atualizeSite(request, response);
                    break;
                case "/admin/cadastroTeatro":
                    apresentaFormCadastroTeatro(request, response);
                    break;
                case "/admin/insercaoTeatro":
                    insereTeatro(request, response);
                    break;
                case "/admin/remocaoTeatro":
                    removeTeatro(request, response);
                    break;
                case "/admin/edicaoTeatro":
                    apresentaFormEdicaoTeatro(request, response);
                    break;
                case "/admin/atualizacaoTeatro":
                    atualizeTeatro(request, response);
                    break;
                case "/admin/listaTeatros":
                    listaTeatros(request, response);                    
                    break;                                 
                case "/teatro/cadastroPromocao":
                    apresentaFormCadastroPromocao(request, response);
                    break;                                
                case "/teatro/insercaoPromocao":
                    inserePromocao(request, response);
                    break;                                
                case "/teatro/remocaoPromocao":
                    removePromocao(request, response);
                    break;                                
                case "/teatro/edicaoPromocao":
                    apresentaFormEdicaoPromocao(request, response);
                    break;                                
                case "/teatro/atualizacaoPromocao":
                    atualizePromocao(request, response);
                    break;
                case "/site/listaPromocoesSite":
                    listaPromocoesSite(request, response);
                    break;     
                case "/teatro/listaPromocoesTeatro":
                    listaPromocoesTeatro(request, response);
                    break;
                case "/listaTodasPromocoes":
                    apresentaListaPromocoes(request, response);                    
                    break;
                case "/listaTodosTeatros":
                    apresentaListaTeatros(request, response);                    
                    break;                               
                default:    
                    openIndex(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }  

    private void openIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listaTeatros(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if("admin@admin".equals(request.getUserPrincipal().getName())){
            List<Teatro> listaTeatros = teatrodao.getAll();
            request.setAttribute("listaTeatros", listaTeatros);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/gerenciaTeatro.jsp");
            dispatcher.forward(request, response);
        }
        else{
            //tratar
        }
    }
        
    private void listaPromocoesTeatro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getUserPrincipal().getName();
        List<Promocao> listaPromocoes;
        if (email == null){
            listaPromocoes = promocaodao.getAll();
            //tratar erro
        }
        else{
            listaPromocoes = promocaodao.getByEmailTeatro(email);
        }
        request.setAttribute("listaPromocoes", listaPromocoes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("teatro/gerenciaPromocao.jsp");
        dispatcher.forward(request, response);
    }
    private void listaPromocoesSite(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getUserPrincipal().getName();
        List<Promocao> listaPromocoes;
        if (email == null){
            listaPromocoes = promocaodao.getAll();
            //tratar erro
        }
        else{
            listaPromocoes = promocaodao.getByEmailSite(email);
        }
        request.setAttribute("listaPromocoes", listaPromocoes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("site/listaPromocoesSite.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaListaTeatros(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("listaTeatro.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaListaPromocoes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("listaPromocoes.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastroSite(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/formulario_site.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormCadastroTeatro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/formulario_teatro.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormCadastroPromocao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("teatro/formulario_promocao.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicaoSite(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getParameter("id");
        Site site = sitedao.get(url);
        String senha = sitedao.getSenha(url);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/formulario_site.jsp");
        request.setAttribute("site", site);
        request.setAttribute("senha", senha);
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormEdicaoTeatro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cnpj = request.getParameter("id");
        Teatro teatro = teatrodao.get(cnpj);
        String senha = teatrodao.getSenha(cnpj);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/formulario_teatro.jsp");
        request.setAttribute("teatro", teatro);
        request.setAttribute("senha", senha);
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormEdicaoPromocao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Promocao promocao = promocaodao.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("teatro/formulario_promocao.jsp");
        request.setAttribute("promocao", promocao);
        dispatcher.forward(request, response);
    }

    private void insereSite(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String url = request.getParameter("url");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");

        Site site = new Site(email, url, nome, telefone);
        sitedao.insert(site,senha);
        response.sendRedirect("listaSites");
    }
    
    private void insereTeatro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String cidade = request.getParameter("cidade");

        Teatro teatro = new Teatro(email, cnpj, nome, cidade);
        teatrodao.insert(teatro,senha);
        response.sendRedirect("listaTeatros");
    }
    
    private void inserePromocao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String url = request.getParameter("url");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        Float preco = Float.parseFloat(request.getParameter("preco"));
        String diahorario = request.getParameter("diahorario");

        Promocao promocao = new Promocao(id, nome, preco, diahorario, url, cnpj);
        promocaodao.insert(promocao);
        response.sendRedirect("listaPromocao");
    }

    private void atualizeSite(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String url = request.getParameter("url");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");

        Site site = new Site(email, nome, url, telefone);
        sitedao.update(site,senha);
    }
    
    private void atualizeTeatro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String cidade = request.getParameter("cidade");

        Teatro teatro = new Teatro(email, cnpj, nome, cidade);
        teatrodao.update(teatro,senha);
        response.sendRedirect("listaTeatros");
    }
    
    private void atualizePromocao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String url = request.getParameter("url");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        Float preco = Float.parseFloat(request.getParameter("preco"));
        String diahorario = request.getParameter("diahorario");

        Promocao promocao = new Promocao(id, nome, preco, diahorario, url, cnpj);
        promocaodao.update(promocao);
        response.sendRedirect("listaPromocao");
    }

    private void removeSite(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String url = request.getParameter("id");

        Site site = new Site(url);
        sitedao.delete(site);
        response.sendRedirect("listaSites");

    }
    
    private void removeTeatro(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String cnpj = request.getParameter("id");

        Teatro theater = new Teatro(cnpj);
        teatrodao.delete(theater);
        response.sendRedirect("listaTeatros");
    }
    
    private void removePromocao(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Promocao sale = new Promocao(id);
        promocaodao.delete(sale);
        response.sendRedirect("listaPromocao");
    }

    private void listaSites(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getUserPrincipal().getName().equals("admin@admin")){
            List<Site> listaSites = sitedao.getAll();
            request.setAttribute("listaSites", listaSites);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/gerenciaSite.jsp");
            dispatcher.forward(request, response);
        }
        else{
            //tratar erro
        }
        
    }
    
}