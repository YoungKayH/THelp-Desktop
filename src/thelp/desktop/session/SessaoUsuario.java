package thelp.desktop.session;

import thelp.desktop.service.AuthService;

public class SessaoUsuario {
    
    private static AuthService authService = AuthService.getInstance();
    
    public static boolean isLogado() {
        return authService.isLogado();
    }
    
    public static int getId() {
        return authService.getUsuarioLogadoId();
    }
    
    public static String getNome() {
        return authService.getNomeUsuarioLogado();
    }
    
    public static boolean isAdmin() {
        return authService.isAdmin();
    }
    
    public static boolean isAnalista() {
        return authService.isAnalista();
    }
    
    public static boolean isCliente() {
        return authService.isCliente();
    }
    
    // Método para compatibilidade (se usado em outros lugares)
    public static void setUsuario(int id, String nome) {
        // Não faz nada - agora gerido pelo AuthService
        System.out.println("SessaoUsuario.setUsuario() deprecated - Use AuthService");
    }
}