package br.com.projetoMVC.DAO;

import br.com.projetoMVC.model.ConnectionFactory;
import br.com.projetoMVC.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAOImpl implements GenericDAO {

    private Connection connection;

    public ProdutoDAOImpl() throws Exception {
        try {
            this.connection = ConnectionFactory.getConnection();
            System.out.println("Conectado com Sucesso!");
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    @Override
    public List<Object> listarTodos() {
        List<Object> list = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM produto ORDER BY descricao";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setDescricao(resultSet.getString("descricao"));
                list.add(produto);
            }
        } catch (SQLException sqlException) {
            System.out.println("Problemas no DAO ao listar os produtos! Erro: " + sqlException.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
            } catch (Exception exception) {
                System.out.println("Problemas ao fechar a conexão! Erro: " + exception.getMessage());
            }
        }
        return list;
    }

    @Override
    public Object listarPorId(int id) {
        Produto produto = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM produto WHERE id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setDescricao(resultSet.getString("descricao"));
            }
        } catch (SQLException ex) {
            System.out.println("Problemas na DAO ao carregar Produto! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(connection, statement, resultSet);
            } catch (Exception e) {
                System.out.println("Problemas ao fechar conexão! Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return produto;
    }

    @Override
    public boolean cadastrar(Object object) {
        Produto produto = (Produto) object;
        PreparedStatement statement = null;
        String sql = "INSERT INTO produto (descricao) VALUES (?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, produto.getDescricao());
            statement.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas na DAO ao cadastrar Produto! Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(connection, statement);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexão! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }


    @Override
    public boolean alterar(Object object) {
        Produto produto = (Produto) object;
        PreparedStatement statement = null;
        String sql = "UPDATE produto SET descricao = ? WHERE id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, produto.getDescricao());
            statement.setInt(2, produto.getId());
            statement.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas na DAO ao alterar Produto! Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(connection, statement);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexão! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void excluir(int id) {
        PreparedStatement statement = null;
        String sql = "DELETE FROM produto WHERE id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Problemas na DAO ao excluir Produto! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(connection, statement);
            } catch (Exception e) {
                System.out.println("Problemas ao fechar conexão! Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
