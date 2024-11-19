package com.example;

import com.example.infra.ProjectDAO;
import com.example.model.ProjetoSustentavel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjetoDAOTest {

    private ProjectDAO projetoDAO = new ProjectDAO();

    @Test
    public void testListarProjetos() {
        List<ProjetoSustentavel> projetos = projetoDAO.listarProjetos();
        assertNotNull(projetos, "A lista de projetos não deve ser nula");
        assertTrue(projetos.size() > 0, "Deve retornar ao menos um projeto da base de dados");
    }
}
