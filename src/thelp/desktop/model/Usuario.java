package thelp.desktop.model;

import java.sql.Timestamp;

public class Usuario 
{
    private int idUsuario;
    private String usuNome;
    private String usuEmail;
    private String usuSenha;
    private boolean usuAtivo;
    private int idPapel;
    private int idOrganizacao;
    private Timestamp criadoEm;
    private Timestamp atualizadoEm;
    
    public Usuario() 
    {
        
    }
   
    public Usuario(int idUsuario, String usuNome, String usuEmail, String usuSenha, 
                   boolean usuAtivo, int idPapel, int idOrganizacao) 
    {
        this.idUsuario = idUsuario;
        this.usuNome = usuNome;
        this.usuEmail = usuEmail;
        this.usuSenha = usuSenha;
        this.usuAtivo = usuAtivo;
        this.idPapel = idPapel;
        this.idOrganizacao = idOrganizacao;
    }
    
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    
    public String getUsuNome() { return usuNome; }
    public void setUsuNome(String usuNome) { this.usuNome = usuNome; }
    
    public String getUsuEmail() { return usuEmail; }
    public void setUsuEmail(String usuEmail) { this.usuEmail = usuEmail; }
    
    public String getUsuSenha() { return usuSenha; }
    public void setUsuSenha(String usuSenha) { this.usuSenha = usuSenha; }
    
    public boolean isUsuAtivo() { return usuAtivo; }
    public void setUsuAtivo(boolean usuAtivo) { this.usuAtivo = usuAtivo; }
    
    public int getIdPapel() { return idPapel; }
    public void setIdPapel(int idPapel) { this.idPapel = idPapel; }
    
    public int getIdOrganizacao() { return idOrganizacao; }
    public void setIdOrganizacao(int idOrganizacao) { this.idOrganizacao = idOrganizacao; }
    
    public Timestamp getCriadoEm() { return criadoEm; }
    public void setCriadoEm(Timestamp criadoEm) { this.criadoEm = criadoEm; }
    
    public Timestamp getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(Timestamp atualizadoEm) { this.atualizadoEm = atualizadoEm; }
    
    @Override
    public String toString() {
        return usuNome + " (" + usuEmail + ")";
    }
}