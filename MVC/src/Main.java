import br.com.projetoMVC.controller.ProdutoController;
import br.com.projetoMVC.model.ConnectionFactory;
import br.com.projetoMVC.model.Produto;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
//        Connection connection = ConnectionFactory.getConnection();
//
//        if (connection != null) {
//            System.out.println("A conexão foi estabelecida!");
//        } else {
//            System.out.println("Ocorreu um erro!");
//        }
//        connection.close();

        int continuar = 1;
        Scanner read = new Scanner(System.in);
        Produto novoProduto = new Produto();
        ProdutoController produto = new ProdutoController();

        int idEscolhido = 0;
        while (continuar != 0) {
            System.out.println("" +
                    "0 - SAIR " +
                    "\n1 - listar todos os cadastrados" +
                    "\n2 - listar por id" +
                    "\n3 - cadastrar" +
                    "\n4 - alterar" +
                    "\n5 - excluir"
            );
            continuar = read.nextInt();

            switch (continuar) {
                case 1:
                    System.out.println("Aqui estão todos os produtos");
                    produto.listarTodos();
                    break;
                case 2:
                    System.out.println("Qual id do produto que deseja listar?");
                    idEscolhido = read.nextInt();
                    produto.listarPorId(idEscolhido);
                    break;
                case 3:
                    System.out.println("Digite o que voce quer cadastrar");
                    String produtoCadastrado = read.next();
                    novoProduto.setDescricao(produtoCadastrado);
                    produto.cadastrar(novoProduto);
                    break;
                case 4:
                    System.out.println("Digite o id do produto que deseja alterar");
                    idEscolhido = read.nextInt();
                    novoProduto = produto.listarPorId(idEscolhido);
                    if (novoProduto != null) {
                        System.out.println("Digite a nova descrição do produto escolhido");
                        String descricaoNova = read.next();
                        novoProduto.setDescricao(descricaoNova);
                        produto.alterar(novoProduto);
                    }else {
                        System.out.println("Produto não encontrado");
                    }

                    break;
                case 5:
                    System.out.println("Qual id do produto que deseja excluir?");
                    idEscolhido = read.nextInt();
                    produto.excluir(idEscolhido);
                    break;
                case 0 :
                    continue;
                default:
                    System.out.println("Número inválido");
            }
        }
        System.out.println("Fim do loop");
    }
}