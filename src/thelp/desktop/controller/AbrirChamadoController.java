package thelp.desktop.controller;

import thelp.desktop.dao.ChamadoDAO;
import thelp.desktop.model.ChamadoModel;

import java.util.List;

public class AbrirChamadoController {

    private ChamadoDAO dao;

    public AbrirChamadoController() {
        dao = new ChamadoDAO();
    }

    public List<ChamadoModel> listarChamados(String status) {
        if (status == null || status.equalsIgnoreCase("Todos")) {
            return dao.listarTodos();
        } else {
            return dao.listarPorStatus(status.toLowerCase());
        }
    }

    public ChamadoModel buscarPorId(int idChamado) {
        return dao.buscarPorId(idChamado);
    }
}
