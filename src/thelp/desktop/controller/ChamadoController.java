package thelp.desktop.controller;

import java.io.File;
import thelp.desktop.dao.ChamadoDAO;
import thelp.desktop.model.ChamadoModel;
import thelp.desktop.session.SessaoUsuario;
import javax.swing.JOptionPane;
import java.util.List;

public class ChamadoController {
    
    private final ChamadoDAO chamadoDAO;
    
    public ChamadoController() 
    { 
        this.chamadoDAO = new ChamadoDAO();
        this.chamadoDAO.testarConexaoETabela();
    }
    
    // Métodos de criação
    public boolean criarChamado(String titulo, String descricao, String categoria, 
                           String prioridade, File arquivoAnexo) {
    
    if (!validarCampos(titulo, descricao, categoria, prioridade)) {
        return false;
    }
    
    if (!SessaoUsuario.isLogado()) {
        mostrarErro("Você precisa estar logado para criar um chamado!");
        return false;
    }
    
    // Valida tamanho do arquivo
    if (arquivoAnexo != null && arquivoAnexo.exists()) {
        long tamanhoMB = arquivoAnexo.length() / (1024 * 1024);
        if (tamanhoMB > 10) {
            mostrarErro("O arquivo é muito grande! Tamanho máximo: 10MB");
            return false;
        }
    }
    
    ChamadoModel chamado = new ChamadoModel(
        titulo.trim(),
        descricao.trim(),
        categoria,
        prioridade,
        SessaoUsuario.getId()
    );
    
    // Busca a organização do usuário
    int idOrganizacao = chamadoDAO.buscarOrganizacaoUsuario(SessaoUsuario.getId());
    
    if (idOrganizacao <= 0) {
        mostrarErro("Usuário não tem organização vinculada!");
        return false;
    }
    
    // Passa a organização junto
    boolean sucesso = chamadoDAO.criarChamado(chamado, arquivoAnexo, idOrganizacao);
    
    if (sucesso) {
        String mensagem = "Chamado criado com sucesso! ID: " + chamado.getIdChamado();
        if (arquivoAnexo != null) {
            mensagem += "\nArquivo anexado: " + arquivoAnexo.getName();
        }
        mostrarSucesso(mensagem);
        return true;
    } else {
        mostrarErro("Erro ao criar chamado. Tente novamente.");
        return false;
    }
}

    public boolean criarChamado(String titulo, String descricao, String categoria, String prioridade) 
    {
        return criarChamado(titulo, descricao, categoria, prioridade, null);
    }
    
    // Métodos de atualização
    public boolean atualizarChamado(ChamadoModel chamado) {
        if (chamado.getIdChamado() <= 0) {
            mostrarErro("ID do chamado inválido!");
            return false;
        }
        
        boolean sucesso = chamadoDAO.atualizarChamado(chamado);
        
        if (sucesso) {
            mostrarSucesso("Chamado atualizado com sucesso!");
            return true;
        } else {
            mostrarErro("Erro ao atualizar chamado.");
            return false;
        }
    }
    
    public boolean fecharChamado(int idChamado) {
        if (!temPermissaoParaFechar()) {
            mostrarErro("Apenas administradores e analistas podem fechar chamados!");
            return false;
        }
        
        boolean sucesso = chamadoDAO.fecharChamado(idChamado);
        
        if (sucesso) {
            mostrarSucesso("Chamado fechado com sucesso!");
            return true;
        } else {
            mostrarErro("Erro ao fechar chamado.");
            return false;
        }
    }
    
    // Métodos de consulta
    public ChamadoModel buscarChamadoPorId(int idChamado) {
        if (idChamado <= 0) {
            return null;
        }
        return chamadoDAO.buscarPorId(idChamado);
    }
    
    public List<ChamadoModel> listarTodosChamados() {
        return chamadoDAO.listarTodos();
    }
    
    public List<ChamadoModel> listarChamadosPorStatus(String status) {
        return chamadoDAO.listarPorStatus(status.toLowerCase());
    }
    
    public List<ChamadoModel> listarChamadosDoUsuarioLogado() {
        if (!SessaoUsuario.isLogado()) {
            return new java.util.ArrayList<>();
        }
        return chamadoDAO.listarPorUsuario(SessaoUsuario.getId());
    }
    
    public List<ChamadoModel> listarChamadosRecentes() {
        return chamadoDAO.listarRecentes();
    }
    
    // Métodos de contagem
    public int contarTotalChamados() {
        return chamadoDAO.contarTotalChamados();
    }
    
    public int contarChamadosPorStatus(String status) {
        return chamadoDAO.contarChamados(status.toLowerCase());
    }
    
    public int contarChamadosAbertos() {
        return contarChamadosPorStatus("aberto");
    }
    
    public int contarChamadosFechados() {
        return contarChamadosPorStatus("fechado");
    }
    
    // Validações
    private boolean validarCampos(String titulo, String descricao, String categoria, String prioridade) {
        if (titulo == null || titulo.trim().isEmpty()) {
            mostrarAviso("O título do chamado é obrigatório!");
            return false;
        }
        
        if (titulo.trim().length() < 5) {
            mostrarAviso("O título deve ter pelo menos 5 caracteres!");
            return false;
        }
        
        if (descricao == null || descricao.trim().isEmpty()) {
            mostrarAviso("A descrição do problema é obrigatória!");
            return false;
        }
        
        if (descricao.trim().length() < 10) {
            mostrarAviso("A descrição deve ter pelo menos 10 caracteres!");
            return false;
        }
        
        if (categoria == null || categoria.isEmpty()) {
            mostrarAviso("Selecione uma categoria!");
            return false;
        }
        
        if (prioridade == null || prioridade.isEmpty()) {
            mostrarAviso("Selecione uma prioridade!");
            return false;
        }
        
        return true;
    }
    
    private boolean temPermissaoParaFechar() {
        return SessaoUsuario.isAdmin() || SessaoUsuario.isAnalista();
    }
    
    // Métodos auxiliares para exibir mensagens
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