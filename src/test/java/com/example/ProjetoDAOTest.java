package com.example;

import com.example.infra.ProjetoDAO;
import com.example.model.ProjetoSustentavel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjetoDAOTest {

    private ProjetoDAO projetoDAO = new ProjetoDAO();

    @Test
    public void testListarProjetos() {
        List<ProjetoSustentavel> projetos = projetoDAO.listarProjetos();
        assertNotNull(projetos, "A lista de projetos não deve ser nula");
        assertTrue(projetos.size() > 0, "Deve retornar ao menos um projeto da base de dados");
    }
}
