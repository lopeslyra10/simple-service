package com.example;

import com.example.model.Projeto;
import com.example.model.ProjetoSustentavel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjetoTest {

    private Projeto projetoBO = new Projeto();

    @Test
    public void testCalcularEficienciaEnergetica() {
        ProjetoSustentavel projeto = new ProjetoSustentavel();
        projeto.setCusto(500000.00);

        double eficiencia = projetoBO.calcularEficienciaEnergetica(projeto);

        // Supondo que queremos eficiência > 0 para projetos com custo alto
        assertTrue(eficiencia > 0, "A eficiência energética deve ser positiva para projetos sustentáveis.");
    }
}
