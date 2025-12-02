package thelp.desktop.service;

import thelp.desktop.dao.UsuarioDAO;
import thelp.desktop.model.Usuario;

public class AuthService {
    private static AuthService instance;
    private Usuario usuarioLogado;
    private UsuarioDAO usuarioDAO;
    
    private AuthService() {
        usuarioDAO = new UsuarioDAO();
    }
    
    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }
    
    // Método de login
    public boolean login(String email, String senha) {
        Usuario usuario = usuarioDAO.autenticar(email, senha);
        
        if (usuario != null) {
            this.usuarioLogado = usuario;
            
            salvarPreferenciasLogin(email, senha);
            
            return true;
        }
        
        return false;
    }
    
    // Logout
    public void logout() {
        this.usuarioLogado = null;
        limparPreferenciasLogin();
    }
    
    // Verificar se usuário está logado
    public boolean isLogado() {
        return usuarioLogado != null;
    }
    
    // Obter usuário logado
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
    
    // Obter ID do usuário logado
    public int getUsuarioLogadoId() {
        return usuarioLogado != null ? usuarioLogado.getIdUsuario() : 0;
    }
    
    // Obter nome do usuário logado
    public String getNomeUsuarioLogado() {
        return usuarioLogado != null ? usuarioLogado.getUsuNome() : "Visitante";
    }
    
    // Obter papel do usuário logado
    public int getPapelUsuarioLogado() {
        return usuarioLogado != null ? usuarioLogado.getIdPapel() : 0;
    }
    
    // Verificar se usuário tem papel específico
    public boolean temPermissao(int papelId) {
        return usuarioLogado != null && usuarioLogado.getIdPapel() == papelId;
    }
    
    // Verificar se é administrador (papel 1)
    public boolean isAdmin() {
        return temPermissao(1);
    }
    
    // Verificar se é analista (papel 2)
    public boolean isAnalista() {
        return temPermissao(2);
    }
    
    // Verificar se é cliente (papel 3)
    public boolean isCliente() {
        return temPermissao(3);
    }
    
    // Métodos para gerenciar preferências de login
    private void salvarPreferenciasLogin(String email, String senha) {
        // java.util.prefs.Preferences prefs = java.util.prefs.Preferences.userRoot().node(this.getClass().getName());
        // prefs.put("email", email);
        // prefs.putBoolean("lembrar", true);
    }
    
    private void limparPreferenciasLogin() {
        // java.util.prefs.Preferences prefs = java.util.prefs.Preferences.userRoot().node(this.getClass().getName());
        // prefs.remove("email");
        // prefs.remove("lembrar");
    }
    
    public boolean carregarLoginSalvo() {
        return false;
    }
}