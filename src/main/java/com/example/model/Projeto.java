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

    public void inserirProjeto(ProjetoSustentavel projeto) {
        projeto.setId(contadorId++); // Atribui um ID único
        projetos.add(projeto);
    }

    // Atualizar um projeto existente
    public void atualizarProjeto(ProjetoSustentavel projetoAtualizado) {
        for (int i = 0; i < projetos.size(); i++) {
            if (projetos.get(i).getId() == projetoAtualizado.getId()) {
                projetos.set(i, projetoAtualizado);
                return;
            }
        }
        throw new IllegalArgumentException("Projeto com ID " + projetoAtualizado.getId() + " não encontrado.");
    }

    public ProjetoSustentavel buscarPorId(int id) {
        return dao.listarProjetoPorId(id);
    }


    // Deletar um projeto por ID
    public void deletarProjeto(int id) {
        projetos.removeIf(p -> p.getId() == id);
    }

    // Filtrar projetos por status
    public List<ProjetoSustentavel> listarPorStatus(String status) {
        List<ProjetoSustentavel> filtrados = new ArrayList<>();
        for (ProjetoSustentavel projeto : listarProjetos()) {
            if (projeto.getStatus() != null && projeto.getStatus().equalsIgnoreCase(status)) {
                filtrados.add(projeto);
            }
        }
        return filtrados;
    }

    // Filtrar projetos por tipo de fonte
    public List<ProjetoSustentavel> listarPorTipoFonte(String tipoFonte) {
        List<ProjetoSustentavel> filtrados = new ArrayList<>();
        for (ProjetoSustentavel projeto : listarProjetos()) {
            if (projeto.getTipoFonte() != null && projeto.getTipoFonte().equalsIgnoreCase(tipoFonte)) {
                filtrados.add(projeto);
            }
        }
        return filtrados;
    }
}

