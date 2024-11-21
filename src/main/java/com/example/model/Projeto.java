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

    /*
      Calcula a eficiência energética de um projeto.
      @param projeto ProjetoSustentavel com informações como custo e tipo de fonte.
      @return Um valor de eficiência entre 0 e 1, onde valores mais altos representam maior eficiência.
    */
    public double calcularEficienciaEnergetica(ProjetoSustentavel projeto) {
        double eficiencia = 1.0;

        // Ajuste com base no custo: projetos de custo alto são penalizados
        if (projeto.getCusto() > 500000) {
            eficiencia -= 0.2; // Reduz eficiência em 20% para projetos acima de R$ 500.000,00
        } else if (projeto.getCusto() < 100000) {
            eficiencia += 0.1; // Aumenta eficiência em 10% para projetos abaixo de R$ 100.000,00
        }

        // Ajuste com base no tipo de fonte de energia
        String tipoFonte = projeto.getTipoFonte();
        if (tipoFonte != null && (tipoFonte.equalsIgnoreCase("solar") || tipoFonte.equalsIgnoreCase("eólica"))) {
            eficiencia += 0.2; // Aumenta eficiência em 20% para fontes renováveis
        }

        double emissoesCarbono = projeto.getEmissoesCarbono();
        if (emissoesCarbono < 1000) {
            eficiencia += 0.1; // Aumenta eficiência em 10% para baixas emissões
        } else if (emissoesCarbono > 5000) {
            eficiencia -= 0.2; // Reduz eficiência em 20% para altas emissões
        }

        // Garante que a eficiência esteja entre 0 e 1
        if (eficiencia < 0) eficiencia = 0;
        if (eficiencia > 1) eficiencia = 1;

        return eficiencia;
    }
}
