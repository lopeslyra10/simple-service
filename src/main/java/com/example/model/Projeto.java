package com.example.model;

import com.example.infra.ProjetoDAO;

import java.util.ArrayList;
import java.util.List;

public class Projeto {
    private final List<ProjetoSustentavel> projetos = new ArrayList<>();
    private int contadorId = 1;
    private ProjetoDAO dao = new ProjetoDAO();

    public List<ProjetoSustentavel> listarProjetos() {
        return dao.listarProjetos();
    }
}

