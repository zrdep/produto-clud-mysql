package exemplo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    private static final BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int opcao = -1;
        do {
            exibirMenu();
            String linha = console.readLine();
            if (linha == null || linha.isBlank()) {
                System.out.println("Opção inválida.");
                continue;
            }
            opcao = Integer.parseInt(linha);
            switch (opcao) {
                case 0 -> salvarProduto();
                case 1 -> buscarTodosProdutos();
                case 2 -> buscarProdutoPorId();
                case 3 -> atualizarProduto();
                case 4 -> excluirProduto();
                case 5 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

    private static void exibirMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("0 - Salvar novo Produto");
        System.out.println("1 - Buscar Todos os Produtos");
        System.out.println("2 - Buscar Produto por ID");
        System.out.println("3 - Atualizar Produto");
        System.out.println("4 - Excluir Produto");
        System.out.println("5 - Sair do Programa");
        System.out.print("Escolha uma opção: ");
    }

    private static void salvarProduto() {
        System.out.println("\n### Criar Novo Produto ###");
        try {
            System.out.print("Nome: ");
            String nome = console.readLine();
            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(console.readLine());
            System.out.print("Valor: ");
            double valor = Double.parseDouble(console.readLine());

            Produto produto = new Produto(nome, quantidade, valor);
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.salvar(produto);
            System.out.println("Produto salvo com sucesso!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void buscarTodosProdutos() {
        System.out.println("\n### Buscar Todos os Produtos ###");
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            List<Produto> produtos = produtoDAO.buscarTodos();
            if (produtos != null && !produtos.isEmpty()) {
                System.out.println("Lista de Produtos:");
                for (Produto produto : produtos) {
                    System.out.println("ID: " + produto.id() + " | Nome: " + produto.nome() + " | Quantidade: " + produto.quantidade() + " | Valor: " + produto.valor());
                }
            } else {
                System.out.println("Nenhum produto encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void buscarProdutoPorId() {
        System.out.println("\n### Buscar Produto por ID ###");
        try {
            System.out.print("Digite o ID do produto: ");
            Long id = Long.parseLong(console.readLine());
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = produtoDAO.buscarPorId(id);
            if (produto != null) {
                System.out.println("Produto encontrado:");
                System.out.println("ID: " + produto.id());
                System.out.println("Nome: " + produto.nome());
                System.out.println("Quantidade: " + produto.quantidade());
                System.out.println("Valor: " + produto.valor());
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void atualizarProduto() {
        System.out.println("\n### Atualizar Produto ###");
        try {
            System.out.print("Digite o ID do produto que deseja atualizar: ");
            Long id = Long.parseLong(console.readLine());
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produtoExistente = produtoDAO.buscarPorId(id);

            if (produtoExistente != null) {
                System.out.println("Nome atual: " + produtoExistente.nome());
                System.out.print("Novo nome: ");
                String nome = console.readLine();

                System.out.println("Quantidade atual: " + produtoExistente.quantidade());
                System.out.print("Nova quantidade: ");
                int quantidade = Integer.parseInt(console.readLine());

                System.out.println("Valor atual: " + produtoExistente.valor());
                System.out.print("Novo valor: ");
                double valor = Double.parseDouble(console.readLine());

                Produto produtoAtualizado = new Produto(id, nome, quantidade, valor);
                produtoDAO.atualizar(produtoAtualizado);
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void excluirProduto() {
        System.out.println("\n### Excluir Produto ###");
        try {
            System.out.print("Digite o ID do produto que deseja excluir: ");
            Long id = Long.parseLong(console.readLine());
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produtoExistente = produtoDAO.buscarPorId(id);

            if (produtoExistente != null) {
                produtoDAO.excluir(produtoExistente.id());
                System.out.println("Produto excluído com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

