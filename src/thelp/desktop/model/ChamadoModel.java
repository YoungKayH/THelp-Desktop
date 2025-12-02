package thelp.desktop.model;

import java.time.LocalDateTime;

public class ChamadoModel 
{
    private int idChamado;
    private String titulo;
    private String descricao;
    private String categoria;
    private String prioridade;
    private String status;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private LocalDateTime finalizadoEm;
    private String anexo;
    private int idUsuarioAbertura;


    // Construtor completo
    public ChamadoModel(int idChamado, String titulo, String descricao, String categoria,
                        String prioridade, String status, LocalDateTime criadoEm,
                        LocalDateTime atualizadoEm, LocalDateTime finalizadoEm, String anexo) {
        this.idChamado = idChamado;
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.prioridade = prioridade;
        this.status = status;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.finalizadoEm = finalizadoEm;
        this.anexo = anexo;
    }

    // Construtor simplificado (para listagens)
    public ChamadoModel(int idChamado, String titulo, String status) 
    {
        this.idChamado = idChamado;
        this.titulo = titulo;
        this.status = status;
    }

    // Construtor para criação
    public ChamadoModel(String titulo, String descricao, String categoria,
                       String prioridade) 
    {
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.prioridade = prioridade;
        this.status = "aberto";
        this.criadoEm = LocalDateTime.now();
    }
    

    // Getters e Setters
    public int getIdChamado() { return idChamado; }
    public void setIdChamado(int idChamado) { this.idChamado = idChamado; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(LocalDateTime atualizadoEm) { this.atualizadoEm = atualizadoEm; }

    public LocalDateTime getFinalizadoEm() { return finalizadoEm; }
    public void setFinalizadoEm(LocalDateTime finalizadoEm) { this.finalizadoEm = finalizadoEm; }

    public String getAnexoPath() { return anexo; }
    public void setAnexoPath(String anexoPath) { this.anexo = anexoPath; }
    
    public int getId() {
        return getIdChamado();
    }
}