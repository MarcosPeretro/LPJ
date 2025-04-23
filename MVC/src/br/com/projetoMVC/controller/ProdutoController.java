package br.com.projetoMVC.controller;

import br.com.projetoMVC.DAO.GenericDAO;
import br.com.projetoMVC.DAO.ProdutoDAOImpl;
import br.com.projetoMVC.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    public List<Produto> listarTodos(){
        try{
            GenericDAO dao = new ProdutoDAOImpl();
            List<Produto> lista = new ArrayList<Produto>();

            for (Object o : dao.listarTodos()) {
                lista.add((Produto) o);
            }
            return lista;
        } catch (Exception e) {
            System.out.println("Problema ao listar produtos! Erro : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Produto listarPorId(int id) {
        try {
            GenericDAO dao = new ProdutoDAOImpl();
            return (Produto) dao.listarPorId(id);
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar produto! Erro: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public void cadastrar(Produto produto) {
        try {
            GenericDAO dao = new ProdutoDAOImpl();
            if (dao.cadastrar(produto)) {
                System.out.println("Produto cadastrado com sucesso!");
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar produto! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void alterar(Produto produto) {
        try {
            GenericDAO dao = new ProdutoDAOImpl();
            if (dao.alterar(produto)) {
                System.out.println("Produto alterado com sucesso!");
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar produto! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void excluir(int id){
        try{
            GenericDAO dao = new ProdutoDAOImpl();
            dao.excluir(id);
        } catch (Exception e) {
            System.out.println("Problemas ao excluir produto! Erro: "  + e.getMessage());
            e.printStackTrace();
        }
    }
}
