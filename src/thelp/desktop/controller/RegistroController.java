package thelp.desktop.controller;

import thelp.desktop.service.RegistroService;

public class RegistroController 
{
    
    private RegistroService registroService;
    
    public RegistroController() {
        this.registroService = new RegistroService();
    }
    
    // Processar registro do formulário
    public boolean processarRegistro(String nome, String email, String senha, String confirmarSenha, int idPapel) {
        
        System.out.println("=== REGISTRO CONTROLLER ===");
        
        // Validação básica
        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }
        
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        if (senha == null || senha.trim().isEmpty()) {
            return false;
        }
        
        if (!senha.equals(confirmarSenha)) {
            return false;
        }
        
        // Chamar o service
        return registroService.registrarUsuario(nome, email, senha, idPapel);
    }
    
    // Método simplificado para usar diretamente no formulário
    public boolean registrar(String nome, String email, String senha, String confirmarSenha, int idPapel) {
        return processarRegistro(nome, email, senha, confirmarSenha, idPapel);
    }
}