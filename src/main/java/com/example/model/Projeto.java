package com.example.model;

import com.example.infra.ProjetoDAO;

import java.util.ArrayList;
import java.util.List;

public class Projeto {
    private final List<ProjetoSustentavel> projetos = new ArrayList<>();
    private int contadorId = 1;
    private ProjetoDAO dao = new ProjetoDAO();

    /**
     * Lista todos os projetos sustentáveis no banco de dados.
     * @return Lista de projetos sustentáveis.
     */
    public List<ProjetoSustentavel> listarProjetos() {
        return dao.listarProjetos();
    }

    /**
     * Insere um novo projeto sustentável no banco de dados.
     * @param projeto O projeto a ser inserido.
     */
    public void inserirProjeto(ProjetoSustentavel projeto) {
        projeto.setId(contadorId++); // Atribui um ID único para controle interno
        projetos.add(projeto);
        dao.inserirProjeto(projeto); // Persiste o projeto no banco de dados
    }

    /**
     * Atualiza as informações de um projeto sustentável existente.
     * @param projetoAtualizado O projeto com as informações atualizadas.
     */
    public void atualizarProjeto(ProjetoSustentavel projetoAtualizado) {
        for (int i = 0; i < projetos.size(); i++) {
            if (projetos.get(i).getId() == projetoAtualizado.getId()) {
                projetos.set(i, projetoAtualizado);
                dao.atualizarProjeto(projetoAtualizado); // Atualiza o banco de dados
                return;
            }
        }
        throw new IllegalArgumentException("Projeto com ID " + projetoAtualizado.getId() + " não encontrado.");
    }

    /**
     * Remove um projeto sustentável do banco de dados e da lista interna.
     * @param id O ID do projeto a ser removido.
     */
    public void deletarProjeto(int id) {
        projetos.removeIf(p -> p.getId() == id);
        dao.deletarProjeto(id); // Remove o projeto do banco de dados
    }

    /**
     * Calcula a eficiência energética de um projeto sustentável.
     * @param projeto O projeto cujos dados serão usados no cálculo.
     * @return Um valor de eficiência energética entre 0 e 1.
     */
    public double calcularEficienciaEnergetica(ProjetoSustentavel projeto) {
        double eficiencia = 1.0;

        // Ajuste com base no custo
        if (projeto.getCusto() > 500000) {
            eficiencia -= 0.2; // Penaliza projetos de alto custo
        } else if (projeto.getCusto() < 100000) {
            eficiencia += 0.1; // Beneficia projetos de baixo custo
        }

        // Ajuste com base no tipo de fonte de energia
        String tipoFonte = projeto.getTipoFonte();
        if (tipoFonte != null && ("solar".equalsIgnoreCase(tipoFonte) || "eólica".equalsIgnoreCase(tipoFonte))) {
            eficiencia += 0.2; // Beneficia fontes renováveis
        }

        // Ajuste com base nas emissões de carbono
        double emissoesCarbono = projeto.getEmissoesCarbono();
        if (emissoesCarbono < 1000) {
            eficiencia += 0.1; // Beneficia projetos com baixas emissões
        } else if (emissoesCarbono > 5000) {
            eficiencia -= 0.2; // Penaliza projetos com altas emissões
        }

        // Garante que a eficiência esteja dentro dos limites
        return Math.max(0, Math.min(eficiencia, 1));
    }
}
