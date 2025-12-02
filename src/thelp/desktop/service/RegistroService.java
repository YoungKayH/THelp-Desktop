package thelp.desktop.service;

import thelp.desktop.dao.UsuarioDAO;
import thelp.desktop.model.Usuario;
import javax.swing.JOptionPane;
import java.security.MessageDigest;

public class RegistroService {
    
    private UsuarioDAO usuarioDAO;
    
    public RegistroService() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    // Registrar novo usuário
    public boolean registrarUsuario(String nome, String email, String senha, int idPapel) {
        
        System.out.println("=== REGISTRO SERVICE ===");
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Papel ID: " + idPapel);
        
        // Validações
        if (!validarCampos(nome, email, senha)) {
            return false;
        }
        
        // Verificar se email já existe
        if (usuarioDAO.emailExiste(email)) {
            mostrarErro("Este email já está cadastrado no sistema!");
            return false;
        }
        
        // Hash da senha (opcional, mas recomendado)
        String senhaHash = hashSenha(senha);
        
        // Criar objeto Usuario
        Usuario usuario = new Usuario();
        usuario.setUsuNome(nome.trim());
        usuario.setUsuEmail(email.trim());
        usuario.setUsuSenha(senhaHash); // ou usar senha direta: senha.trim()
        usuario.setIdPapel(idPapel);
        usuario.setUsuAtivo(true);
        usuario.setIdOrganizacao(1); // Organização padrão
        
        System.out.println("Criando usuário no banco...");
        
        // Salvar no banco
        boolean sucesso = usuarioDAO.criar(usuario);
        
        if (sucesso) {
            String papel = getPapelNome(idPapel);
            String mensagem = "Usuário registrado com sucesso!\n\n" +
                            "Nome: " + nome + "\n" +
                            "Email: " + email + "\n" +
                            "Papel: " + papel + "\n\n" +
                            "Você já pode fazer login com suas credenciais.";
            
            mostrarSucesso(mensagem);
            return true;
        } else {
            mostrarErro("Erro ao registrar usuário no banco de dados.");
            return false;
        }
    }
    
    // Validar campos
    private boolean validarCampos(String nome, String email, String senha) {
        if (nome == null || nome.trim().isEmpty()) {
            mostrarAviso("O nome é obrigatório!");
            return false;
        }
        
        if (nome.trim().length() < 3) {
            mostrarAviso("O nome deve ter pelo menos 3 caracteres!");
            return false;
        }
        
        if (email == null || email.trim().isEmpty()) {
            mostrarAviso("O email é obrigatório!");
            return false;
        }
        
        if (!validarEmail(email)) {
            mostrarAviso("Email inválido! Digite um email válido.");
            return false;
        }
        
        if (senha == null || senha.trim().isEmpty()) {
            mostrarAviso("A senha é obrigatória!");
            return false;
        }
        
        if (senha.length() < 6) {
            mostrarAviso("A senha deve ter pelo menos 6 caracteres!");
            return false;
        }
        
        return true;
    }
    
    // Validar formato de email
    private boolean validarEmail(String email) {
        return email.contains("@") && email.contains(".") && email.length() > 5;
    }
    
    // Hash da senha (SHA-256)
    private String hashSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(senha.getBytes("UTF-8"));
            
            // Converter bytes para hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (Exception e) {
            System.err.println("Erro ao hashear senha: " + e.getMessage());
            return senha; // Retorna senha sem hash em caso de erro
        }
    }
    
    // Converter ID do papel para nome
    private String getPapelNome(int idPapel) {
        switch(idPapel) {
            case 1: return "Admin";
            case 2: return "Analista";
            case 3: return "Cliente";
            default: return "Usuário";
        }
    }
    
    // Métodos para exibir mensagens
    private void mostrarSucesso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    private void mostrarAviso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}