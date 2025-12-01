package thelp.desktop.session;

import thelp.desktop.model.Usuario;
import thelp.desktop.service.AuthService;

public class SessaoUsuario {
    
    private SessaoUsuario() {

    }
    
    public static Usuario getUsuario() {
        return AuthService.getInstance().getUsuarioLogado();
    }
    
    public static int getId() {
        return AuthService.getInstance().getUsuarioLogadoId();
    }
    
    public static String getNome() {
        return AuthService.getInstance().getNomeUsuarioLogado();
    }
    
    public static int getPapel() {
        return AuthService.getInstance().getPapelUsuarioLogado();
    }
    
    public static boolean isLogado() {
        return AuthService.getInstance().isLogado();
    }
    
    public static boolean isAdmin() {
        return AuthService.getInstance().isAdmin();
    }
    
    public static boolean isAnalista() {
        return AuthService.getInstance().isAnalista();
    }
    
    public static boolean isCliente() {
        return AuthService.getInstance().isCliente();
    }
    
    public static boolean temPermissao(int papelId) {
        return AuthService.getInstance().temPermissao(papelId);
    }
    
    public static boolean login(String email, String senha) {
        return AuthService.getInstance().login(email, senha);
    }
    
    public static void logout() {
        AuthService.getInstance().logout();
    }
}