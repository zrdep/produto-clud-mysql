package exemplo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscenteDAO {

    public void salvar(Discente discente) throws Exception {
        var sql = "insert into aluno "
                + "(nome, matricula, curso, periodoAtual) values (?, ?, ?, ?)";
        try (var conexao = Conexao.obterConexao();
             var stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, discente.nome());
            stmt.setString(2, discente.matricula());
            stmt.setString(3, discente.curso());
            stmt.setInt(4, discente.periodoAtual());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public List<Discente> buscarTodos() throws Exception {
        var sql = "select * from aluno";
        List<Discente> discentes = new ArrayList<>();
        try (var conexao = Conexao.obterConexao();
             var stmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Discente discente = new Discente(
                            rs.getLong("id"),
                            rs.getString("nome"),
                            rs.getString("matricula"),
                            rs.getString("curso"),
                            rs.getInt("periodoAtual")
                    );
                    discentes.add(discente);
                }
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return discentes;
    }

    public Discente buscarPorId(Long id) throws Exception {
        var sql = "select * from aluno where id = ?";
        Discente discente = null;
        try (var conexao = Conexao.obterConexao();
             var stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    discente = new Discente(
                            rs.getLong("id"),
                            rs.getString("nome"),
                            rs.getString("matricula"),
                            rs.getString("curso"),
                            rs.getInt("periodoAtual")
                    );
                }
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return discente;
    }

    public void atualizar(Discente discente) throws Exception {
        var sql = "update aluno set nome = ?, matricula = ?, curso = ?, periodoAtual = ? where id = ?";
        try (var conexao = Conexao.obterConexao();
             var stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, discente.nome());
            stmt.setString(2, discente.matricula());
            stmt.setString(3, discente.curso());
            stmt.setInt(4, discente.periodoAtual());
            stmt.setLong(5, discente.id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public void excluir(Long id) throws Exception {
        var sql = "delete from aluno where id = ?";
        try (var conexao = Conexao.obterConexao();
             var stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}
