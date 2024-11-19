package com.example.model;

import com.example.infra.ProjetoDAO;
import java.util.List;

public class Projeto {
    private ProjetoDAO dao = new ProjetoDAO();

    public List<ProjetoSustentavel> listarProjetos() {
        return dao.listarProjetos();
    }

    public void inserirProjeto(ProjetoSustentavel projeto) {
    }

    public void atualizarProjeto(ProjetoSustentavel projeto) {
    }

    public void deletarProjeto(int id) {
    }
    /**
     * Calcula a eficiência energética de um projeto.
     * @param projeto ProjetoSustentavel com informações como custo e tipo de fonte.
     * @return Um valor de eficiência entre 0 e 1, onde valores mais altos representam maior eficiência.
     */
    public double calcularEficiênciaEnergetica(ProjetoSustentavel projeto) {
        double eficiencia = 1.0;

        // Ajuste com base no custo: projetos de custo alto são penalizados
        if (projeto.getCusto() > 500000) {
            eficiencia -= 0.2; // Reduz eficiência em 20% para projetos acima de R$ 500.000,00
        } else if (projeto.getCusto() < 100000) {
            eficiencia += 0.1; // Aumenta eficiência em 10% para projetos abaixo de R$ 100.000,00
        }

        // Ajuste com base no tipo de fonte de energia
        String tipoFonte = projeto.getTipoFonte();
        if (tipoFonte != null) {
            tipoFonte = tipoFonte.toLowerCase();
            if ("solar".equals(tipoFonte) || "eólica".equals(tipoFonte)) {
                eficiencia += 0.2; // Aumenta eficiência em 20% para fontes renováveis
            }
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