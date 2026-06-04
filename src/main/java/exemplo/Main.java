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
                case 0 -> salvarDiscente();
                case 1 -> buscarTodosDiscentes();
                case 2 -> buscarDiscentePorId();
                case 3 -> atualizarDiscente();
                case 4 -> excluirDiscente();
                case 5 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

    private static void exibirMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("0 - Salvar novo Discente");
        System.out.println("1 - Buscar Todos os Discentes");
        System.out.println("2 - Buscar Discente por ID");
        System.out.println("3 - Atualizar Discente");
        System.out.println("4 - Excluir Discente");
        System.out.println("5 - Sair do Programa");
        System.out.print("Escolha uma opção: ");
    }

    private static void salvarDiscente() {
        System.out.println("\n### Criar Novo Discente ###");
        try {
            System.out.print("Nome: ");
            String nome = console.readLine();
            System.out.print("Matrícula: ");
            String matricula = console.readLine();
            System.out.print("Curso: ");
            String curso = console.readLine();
            System.out.print("Período Atual: ");
            int periodoAtual = Integer.parseInt(console.readLine());

            Discente discente = new Discente(nome, matricula, curso, periodoAtual);
            DiscenteDAO discenteDAO = new DiscenteDAO();
            discenteDAO.salvar(discente);
            System.out.println("Discente salvo com sucesso!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void buscarTodosDiscentes() {
        System.out.println("\n### Buscar Todos os Discentes ###");
        DiscenteDAO discenteDAO = new DiscenteDAO();
        try {
            List<Discente> discentes = discenteDAO.buscarTodos();
            if (discentes != null && !discentes.isEmpty()) {
                System.out.println("Lista de Discentes:");
                for (Discente discente : discentes) {
                    System.out.println("ID: " + discente.id() + " | Nome: " + discente.nome() + " | Matrícula: " + discente.matricula() + " | Curso: " + discente.curso() + " | Período Atual: " + discente.periodoAtual());
                }
            } else {
                System.out.println("Nenhum discente encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void buscarDiscentePorId() {
        System.out.println("\n### Buscar Discente por ID ###");
        try {
            System.out.print("Digite o ID do discente: ");
            Long id = Long.parseLong(console.readLine());
            DiscenteDAO discenteDAO = new DiscenteDAO();
            Discente discente = discenteDAO.buscarPorId(id);
            if (discente != null) {
                System.out.println("Discente encontrado:");
                System.out.println("ID: " + discente.id());
                System.out.println("Nome: " + discente.nome());
                System.out.println("Matrícula: " + discente.matricula());
                System.out.println("Curso: " + discente.curso());
                System.out.println("Período Atual: " + discente.periodoAtual());
            } else {
                System.out.println("Discente não encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void atualizarDiscente() {
        System.out.println("\n### Atualizar Discente ###");
        try {
            System.out.print("Digite o ID do discente que deseja atualizar: ");
            Long id = Long.parseLong(console.readLine());
            DiscenteDAO discenteDAO = new DiscenteDAO();
            Discente discenteExistente = discenteDAO.buscarPorId(id);

            if (discenteExistente != null) {
                System.out.println("Nome atual: " + discenteExistente.nome());
                System.out.print("Novo nome: ");
                String nome = console.readLine();

                System.out.println("Matrícula atual: " + discenteExistente.matricula());
                System.out.print("Nova matrícula: ");
                String matricula = console.readLine();

                System.out.println("Curso atual: " + discenteExistente.curso());
                System.out.print("Novo curso: ");
                String curso = console.readLine();

                System.out.println("Período atual: " + discenteExistente.periodoAtual());
                System.out.print("Novo período atual: ");
                int periodoAtual = Integer.parseInt(console.readLine());

                Discente discenteAtualizado = new Discente(id, nome, matricula, curso, periodoAtual);
                discenteDAO.atualizar(discenteAtualizado);
                System.out.println("Discente atualizado com sucesso!");
            } else {
                System.out.println("Discente não encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void excluirDiscente() {
        System.out.println("\n### Excluir Discente ###");
        try {
            System.out.print("Digite o ID do discente que deseja excluir: ");
            Long id = Long.parseLong(console.readLine());
            DiscenteDAO discenteDAO = new DiscenteDAO();
            Discente discenteExistente = discenteDAO.buscarPorId(id);

            if (discenteExistente != null) {
                discenteDAO.excluir(discenteExistente.id());
                System.out.println("Discente excluído com sucesso!");
            } else {
                System.out.println("Discente não encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

