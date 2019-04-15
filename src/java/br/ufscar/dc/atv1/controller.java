package br.ufscar.dc.atv1;

import br.ufscar.dc.atv1.model.Promoção;
import br.ufscar.dc.atv1.model.Site;
import br.ufscar.dc.atv1.model.Teatro;
import br.ufscar.dc.atv1.dao.SiteDAO;
import br.ufscar.dc.atv1.dao.PromoçãoDAO;
import br.ufscar.dc.atv1.dao.TeatroDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/")
public class controller extends HttpServlet {
    
    private SiteDAO sitedao;
    private TeatroDAO teatrodao;
    private PromoçãoDAO promocaodao;
    
    @Override
    public void init() {
        sitedao = new SiteDAO();
    }
    
    @Override
    public void init() {
        teatrodao = new TeatroDAO();
    }
    
    @Override
    public void init() {
        promocaodao = new PromoçãoDAO();
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
                case "/cadastroSite":
                    apresentaFormCadastroSite(request, response);
                    break;
                case "/cadastroTeatro":
                    apresentaFormCadastroTeatro(request, response);
                    break;
                case "/cadastroPromocao":
                    apresentaFormCadastroPromocao(request, response);
                    break;
                case "/insercaoSite":
                    insereSite(request, response);
                    break;
                case "/insercaoTeatro":
                    insereTeatro(request, response);
                    break;
                case "/insercaoPromocao":
                    inserePromocao(request, response);
                    break;
                case "/remocaoSite":
                    removeSite(request, response);
                    break;
                case "/remocaoTeatro":
                    removeTeatro(request, response);
                    break;
                case "/remocaoPromocao":
                    removePromocao(request, response);
                    break;
                case "/edicaoSite":
                    apresentaFormEdicaoSite(request, response);
                    break;
                case "/edicaoTeatro":
                    apresentaFormEdicaoTeatro(request, response);
                    break;
                case "/edicaoPromocao":
                    apresentaFormEdicaoPromocao(request, response);
                    break;
                case "/atualizacaoSite":
                    atualizeSite(request, response);
                    break;
                case "/atualizacaoTeatro":
                    atualizeTeatro(request, response);
                    break;
                case "/atualizacaoPromocao":
                    atualizePromocao(request, response);
                    break;
                case "/listaPromocoes":
                    listaPromocoes(request, response);
                    break;
                default:
                    listaTeatros(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void listaTeatros(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Teatro> listaTeatros = teatrodao.getAll();
        request.setAttribute("listaTeatros", listaTeatros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("teatro/listaTeatro.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listaPromocoes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Promocao> listaPromocoes = promocaodao.getAll();
        request.setAttribute("listaPromocoes", listaPromocoes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("promocao/listaPromocoes.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastroSite(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("site/formulario.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormCadastroTeatro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("teatro/formulario.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormCadastroPromocao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("promocao/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicaoSite(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getParameter("url");
        Site site = sitedao.get(url);
        RequestDispatcher dispatcher = request.getRequestDispatcher("site/formulario.jsp");
        request.setAttribute("site", site);
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormEdicaoTeatro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cnpj = request.getParameter("cnpj");
        Teatro teatro = teatrodao.get(cnpj);
        RequestDispatcher dispatcher = request.getRequestDispatcher("teatro/formulario.jsp");
        request.setAttribute("teatro", teatro);
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormEdicaoPromocao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Promocao promocao = promocaodao.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("promocao/formulario.jsp");
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

        Site site = new Site(email, senha, url, nome, telefone);
        sitedao.insert(site);
    }
    
    private void insereTeatro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("titulo");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String cidade = request.getParameter("cidade");

        Teatro teatro = new Teatro(email, senha, cnpj, nome, cidade);
        teatrodao.insert(teatro);
        response.sendRedirect("listaTeatro");
    }
    
    private void inserePromocao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = request.getParameter("url");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        Float preco = Float.parseFloat(request.getParameter("preco"));
        String diahorario = request.getParameter("diahorario");

        Promocao promocao = new Promocao(url, cnpj, nome, preco, diahorario);
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

        Site site = new Site(email, senha, url, nome, telefone);
        sitedao.update(site);
    }
    
    private void atualizeTeatro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("titulo");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String cidade = request.getParameter("cidade");

        Teatro teatro = new Teatro(email, senha, cnpj, nome, cidade);
        teatrodao.update(teatro);
        response.sendRedirect("listaTeatro");
    }
    
    private void atualizePromocao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String url = request.getParameter("url");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        Float preco = Float.parseFloat(request.getParameter("preco"));
        String diahorario = request.getParameter("diahorario");

        Promocao promocao = new Promocao(url, cnpj, nome, preco, diahorario);
        promocaodao.update(promocao);
        response.sendRedirect("listaPromocao");
    }

    private void removeSite(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String url = request.getParameter("url");

        Site site = new Site(url);
        sitedao.delete(site);
    }
    
    private void removeTeatro(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String cnpj = request.getParameter("cnpj");

        Teatro theater = new Teatro(cnpj);
        teatrodao.delete(theater);
        response.sendRedirect("listaTeatro");
    }
    
    private void removePromocao(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Promocao sale = new Promocao(id);
        promocaodao.delete(sale);
        response.sendRedirect("listaPromocao");
    }
}