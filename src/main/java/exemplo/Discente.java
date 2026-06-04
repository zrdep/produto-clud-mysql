package exemplo;

public record Discente(Long id, String nome, String matricula, String curso, Integer periodoAtual) {

    public Discente(String nome, String matricula, String curso, Integer periodoAtual) {
        this(null, nome, matricula, curso, periodoAtual);
    }
}
