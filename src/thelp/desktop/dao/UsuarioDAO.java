package thelp.desktop.dao;

import thelp.desktop.model.Usuario;
import thelp.desktop.Database.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    // Autenticar usuário
    public Usuario autenticar(String email, String senha) {
        String sql = "SELECT id_usuario, usu_nome, usu_email, usu_senha, usu_ativo, " +
                     "id_papel, id_organizacao FROM usuario " +
                     "WHERE usu_email = ? AND usu_senha = ? AND usu_ativo = true";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null) return null;
            
            ps.setString(1, email);
            ps.setString(2, senha); // Em produção, use hash da senha
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsuNome(rs.getString("usu_nome"));
                usuario.setUsuEmail(rs.getString("usu_email"));
                usuario.setUsuSenha(rs.getString("usu_senha"));
                usuario.setUsuAtivo(rs.getBoolean("usu_ativo"));
                usuario.setIdPapel(rs.getInt("id_papel"));
                usuario.setIdOrganizacao(rs.getInt("id_organizacao"));
                
                return usuario;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    // Buscar usuário por ID
    public Usuario buscarPorId(int id) {
        String sql = "SELECT id_usuario, usu_nome, usu_email, usu_senha, usu_ativo, " +
                     "id_papel, id_organizacao FROM usuario WHERE id_usuario = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null) return null;
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsuNome(rs.getString("usu_nome"));
                usuario.setUsuEmail(rs.getString("usu_email"));
                usuario.setUsuSenha(rs.getString("usu_senha"));
                usuario.setUsuAtivo(rs.getBoolean("usu_ativo"));
                usuario.setIdPapel(rs.getInt("id_papel"));
                usuario.setIdOrganizacao(rs.getInt("id_organizacao"));
                
                return usuario;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    // Listar todos os usuários
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, usu_nome, usu_email, usu_senha, usu_ativo, " +
                     "id_papel, id_organizacao FROM usuario ORDER BY usu_nome";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (conn == null) return usuarios;
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsuNome(rs.getString("usu_nome"));
                usuario.setUsuEmail(rs.getString("usu_email"));
                usuario.setUsuSenha(rs.getString("usu_senha"));
                usuario.setUsuAtivo(rs.getBoolean("usu_ativo"));
                usuario.setIdPapel(rs.getInt("id_papel"));
                usuario.setIdOrganizacao(rs.getInt("id_organizacao"));
                
                usuarios.add(usuario);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return usuarios;
    }
    
    // Criar novo usuário
    public boolean criar(Usuario usuario) {
        String sql = "INSERT INTO usuario (usu_nome, usu_email, usu_senha, usu_ativo, " +
                     "id_papel, id_organizacao) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null) return false;
            
            ps.setString(1, usuario.getUsuNome());
            ps.setString(2, usuario.getUsuEmail());
            ps.setString(3, usuario.getUsuSenha());
            ps.setBoolean(4, usuario.isUsuAtivo());
            ps.setInt(5, usuario.getIdPapel());
            ps.setInt(6, usuario.getIdOrganizacao());
            
            int rows = ps.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    // Atualizar usuário
    public boolean atualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET usu_nome = ?, usu_email = ?, usu_senha = ?, " +
                     "usu_ativo = ?, id_papel = ?, id_organizacao = ? WHERE id_usuario = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null) return false;
            
            ps.setString(1, usuario.getUsuNome());
            ps.setString(2, usuario.getUsuEmail());
            ps.setString(3, usuario.getUsuSenha());
            ps.setBoolean(4, usuario.isUsuAtivo());
            ps.setInt(5, usuario.getIdPapel());
            ps.setInt(6, usuario.getIdOrganizacao());
            ps.setInt(7, usuario.getIdUsuario());
            
            int rows = ps.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    // Excluir usuário (soft delete)
    public boolean desativar(int id) {
        String sql = "UPDATE usuario SET usu_ativo = false WHERE id_usuario = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null) return false;
            
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    // Verificar se email já existe
    public boolean emailExiste(String email) {
        String sql = "SELECT COUNT(*) as total FROM usuario WHERE usu_email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null) return false;
            
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}