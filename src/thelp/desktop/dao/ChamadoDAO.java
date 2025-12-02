package thelp.desktop.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import thelp.desktop.Database.DatabaseConnection;
import thelp.desktop.model.ChamadoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChamadoDAO {

    // CRUD Básico
    public boolean criarChamado(ChamadoModel chamado, File arquivo, int idOrganizacao) 
{
    System.out.println("=== DEBUG DAO: Iniciando criação de chamado ===");
    System.out.println("Título: " + chamado.getTitulo());
    System.out.println("Organização ID: " + idOrganizacao);
    
    // SQL CORRIGIDA - incluindo id_organizacao
    String sql = "INSERT INTO chamado (cha_titulo, cha_descricao, cha_categoria, " +
                "cha_prioridade, cha_status, id_organizacao, id_usuario_abertura, " +
                "id_usuario_atribuido, cha_anexo, cha_criado_em) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, NULL, ?, CURRENT_TIMESTAMP)";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
        System.out.println("SQL: " + sql);
        
        ps.setString(1, chamado.getTitulo());
        ps.setString(2, chamado.getDescricao());
        ps.setString(3, chamado.getCategoria());
        ps.setString(4, chamado.getPrioridade());
        ps.setString(5, chamado.getStatus());
        ps.setInt(6, idOrganizacao);
        
        // Se tiver anexo, salva o caminho
        if (arquivo != null && arquivo.exists()) {
            String caminhoAnexo = salvarArquivoNoServidor(arquivo);
            ps.setString(8, caminhoAnexo);
        } else {
            ps.setNull(8, Types.VARCHAR);
        }
        
        int rows = ps.executeUpdate();
        System.out.println("Linhas afetadas: " + rows);
        
        if (rows > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                chamado.setIdChamado(rs.getInt(1));
            }
            return true;
        }
        
    } catch (SQLException e) {
        System.err.println("ERRO SQL: " + e.getMessage());
        e.printStackTrace();
    }
    return false;
}
    public int buscarOrganizacaoUsuario(int idUsuario) 
    {
    String sql = "SELECT id_organizacao FROM usuario WHERE id_usuario = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, idUsuario);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            int idOrg = rs.getInt("id_organizacao");
            System.out.println("Organização do usuário " + idUsuario + ": " + idOrg);
            return idOrg;
        }
        
    } catch (SQLException e) {
        System.err.println("Erro ao buscar organização: " + e.getMessage());
    }
    
    System.out.println("Usuário sem organização, usando valor padrão 1");
    return 1; // Valor padrão se não encontrar
}
    public void testarConexaoETabela() {
    System.out.println("=== TESTANDO CONEXÃO E ESTRUTURA ===");
    
    try (Connection conn = DatabaseConnection.getConnection()) {
        System.out.println("Conexão OK: " + (conn != null));
        
        // Para PostgreSQL
        String sql = "SELECT column_name, data_type, is_nullable " +
                    "FROM information_schema.columns " +
                    "WHERE table_name = 'chamado' " +
                    "ORDER BY ordinal_position";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        System.out.println("COLUNAS DA TABELA CHAMADO:");
        System.out.println("----------------------------");
        int count = 1;
        while (rs.next()) {
            String coluna = rs.getString("column_name");
            String tipo = rs.getString("data_type");
            String nulo = rs.getString("is_nullable");
            System.out.println(count + ". " + coluna + " [" + tipo + "] - Nullable: " + nulo);
            count++;
        }
        System.out.println("Total de colunas: " + (count-1));
        System.out.println("===========================");
        
    } catch (SQLException e) {
        System.err.println("Erro ao testar conexão: " + e.getMessage());
    }
}
    private String salvarArquivoNoServidor(File arquivo) 
    {
        try {
            // Cria diretório para anexos se não existir
            String diretorioBase = "anexos_chamados";
            Path diretorio = Paths.get(diretorioBase);
            if (!Files.exists(diretorio)) {
                Files.createDirectories(diretorio);
            }
            
            // Cria subdiretório por usuário
            Path diretorioUsuario = diretorio.resolve("usuario_");
            if (!Files.exists(diretorioUsuario)) {
                Files.createDirectories(diretorioUsuario);
            }
            
            // Gera nome único para o arquivo
            String nomeOriginal = arquivo.getName();
            String extensao = "";
            int pontoIndex = nomeOriginal.lastIndexOf('.');
            if (pontoIndex > 0) {
                extensao = nomeOriginal.substring(pontoIndex);
                nomeOriginal = nomeOriginal.substring(0, pontoIndex);
            }
            
            String nomeUnico = nomeOriginal + "_" + System.currentTimeMillis() + extensao;
            Path destino = diretorioUsuario.resolve(nomeUnico);
            
            // Copia o arquivo
            Files.copy(arquivo.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
            
            // Retorna o caminho relativo
            return diretorioUsuario.resolve(nomeUnico).toString();
            
        } catch (IOException e) {
            return null;
        }
    }
    public String buscarCaminhoAnexo(int idChamado) 
    {
        String sql = "SELECT cha_anexo FROM chamado WHERE id_chamado = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idChamado);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getString("cha_anexo");
            }
            
        } catch (SQLException e) {
        }
        return null;
    }
    public boolean atualizarChamado(ChamadoModel chamado) 
    {
        String sql = "UPDATE chamado SET cha_titulo = ?, cha_descricao = ?, cha_categoria = ?, " +
                    "cha_prioridade = ?, cha_status = ?, cha_atualizado_em = CURRENT_TIMESTAMP " +
                    "WHERE id_chamado = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, chamado.getTitulo());
            ps.setString(2, chamado.getDescricao());
            ps.setString(3, chamado.getCategoria());
            ps.setString(4, chamado.getPrioridade());
            ps.setString(5, chamado.getStatus());
            ps.setInt(6, chamado.getIdChamado());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
        }
        return false;
    }
    
    public boolean fecharChamado(int idChamado) {
        String sql = "UPDATE chamado SET cha_status = 'fechado', " +
                    "cha_finalizado_em = CURRENT_TIMESTAMP, " +
                    "cha_atualizado_em = CURRENT_TIMESTAMP " +
                    "WHERE id_chamado = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idChamado);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
        }
        return false;
    }
    
    public boolean excluirChamado(int idChamado) {
        String sql = "DELETE FROM chamado WHERE id_chamado = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idChamado);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
        }
        return false;
    }
    
    // Consultas
    public ChamadoModel buscarPorId(int idChamado) {
        String sql = "SELECT * FROM chamado WHERE id_chamado = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idChamado);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return mapearResultSetParaChamado(rs);
            }
            
        } catch (SQLException e) {
        }
        return null;
    }
    
    public List<ChamadoModel> listarTodos() {
        return listarComFiltro(null, null);
    }

    public List<ChamadoModel> listarPorStatus(String status) {
    return listarComFiltro("LOWER(cha_status) = ?", status.toLowerCase());
}


    public List<ChamadoModel> listarPorUsuario(int idUsuario) {
        return listarComFiltro("id_usuario = ?", String.valueOf(idUsuario));
    }

    public List<ChamadoModel> listarPorPrioridade(String prioridade) {
        return listarComFiltro("cha_prioridade = ?", prioridade);
    }
    
    private List<ChamadoModel> listarComFiltro(String condicao, String valor) {
    List<ChamadoModel> lista = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM chamado");

    if (condicao != null && !condicao.isEmpty()) {
        sql.append(" WHERE ").append(condicao);
    }
    sql.append(" ORDER BY cha_criado_em DESC");

    System.out.println("SQL executada: " + sql); // debug
    System.out.println("Valor do filtro: " + valor); // debug

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql.toString())) {

        if (condicao != null && !condicao.isEmpty() && valor != null) {
            ps.setString(1, valor.toLowerCase()); // garante minúscula
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(mapearResultSetParaChamado(rs));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}
    
    // Métodos existentes (mantidos para compatibilidade)
    public int contarChamados(String status) {
        String sql = "SELECT COUNT(*) FROM chamado WHERE cha_status = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) { e.printStackTrace(); }

        return 0;
    }

    public List<ChamadoModel> listarRecentes() {
        String sql = "SELECT id_chamado, cha_titulo, cha_status FROM chamado ORDER BY id_chamado DESC LIMIT 20";

        List<ChamadoModel> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new ChamadoModel(
                        rs.getInt("id_chamado"),
                        rs.getString("cha_titulo"),
                        rs.getString("cha_status")
                ));
            }

        } catch (Exception e) { e.printStackTrace(); }

        return lista;
    }
    
    // Método auxiliar para mapear ResultSet
    private ChamadoModel mapearResultSetParaChamado(ResultSet rs) throws SQLException {
    return new ChamadoModel(
        rs.getInt("id_chamado"),
        rs.getString("cha_titulo"),
        rs.getString("cha_descricao"),
        rs.getString("cha_categoria"),
        rs.getString("cha_prioridade"),
        rs.getString("cha_status"), // <- importante
        rs.getTimestamp("cha_criado_em") != null ? rs.getTimestamp("cha_criado_em").toLocalDateTime() : null,
        rs.getTimestamp("cha_atualizado_em") != null ? rs.getTimestamp("cha_atualizado_em").toLocalDateTime() : null,
        rs.getTimestamp("cha_finalizado_em") != null ? rs.getTimestamp("cha_finalizado_em").toLocalDateTime() : null,
        rs.getString("cha_anexo")
    );
}
    
    // Métodos adicionais úteis
    public List<ChamadoModel> buscarPorTitulo(String termo) {
        List<ChamadoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM chamado WHERE cha_titulo LIKE ? ORDER BY cha_criado_em DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + termo + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                lista.add(mapearResultSetParaChamado(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public int contarTotalChamados() {
        String sql = "SELECT COUNT(*) FROM chamado";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<ChamadoModel> listarChamadosAbertos() {
        return listarPorStatus("aberto");
    }
    
    public List<ChamadoModel> listarChamadosFechados() {
        return listarPorStatus("fechado");
    }
}