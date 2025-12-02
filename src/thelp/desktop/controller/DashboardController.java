package thelp.desktop.controller; 

import thelp.desktop.dao.ChamadoDAO; 
import thelp.desktop.model.ChamadoModel; 
import java.util.List; 

public class DashboardController 
{ 
    private final ChamadoDAO dao; 
    public DashboardController() 
    { 
        dao = new ChamadoDAO(); 
    } 
    public int getAbertos() 
    { 
        return dao.contarChamados("aberto"); 
    } public int getFechados() 
    { 
        return dao.contarChamados("fechado"); 
    } 
    public int getPendentes() 
    { 
        return dao.contarChamados("em_andamento"); 
    } 
    public List<ChamadoModel> getChamadosRecentes() 
    { 
        return dao.listarRecentes(); 
    } 
}