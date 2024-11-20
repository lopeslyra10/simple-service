package com.example;

import com.example.infra.ConnectionFactory;
import com.example.infra.ProjetoDAO;
import com.example.model.ProjetoSustentavel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjetoDAOTest {

    private ProjetoDAO projetoDAO;
    private Connection conexao;

    @BeforeEach
    public void setUp() throws SQLException {
        // Inicializa a conexão e o DAO antes de cada teste
        conexao = ConnectionFactory.getConnection();
        projetoDAO = new ProjetoDAO();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Fecha a conexão após cada teste
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }

    @Test
    public void testAdicionarProjeto() {
        ProjetoSustentavel novoProjeto = new ProjetoSustentavel("Projeto Solar",
                "Energia solar em comunidades",
                "Solar",
                "Sudeste",
                300000.0,
                "Em andamento",
                800.0
        );

        // Tenta adicionar um projeto
        assertDoesNotThrow(() -> projetoDAO.inserirProjeto(novoProjeto), "Não deve lançar exceções ao adicionar um projeto");

        // Verifica se o projeto foi adicionado com sucesso
        List<ProjetoSustentavel> projetos = projetoDAO.listarProjetos();
        assertTrue(projetos.stream().anyMatch(p -> p.getNome().equals("Projeto Solar")),
                "O projeto 'Projeto Solar' deve estar na lista de projetos");
    }

    @Test
    public void testListarProjetos() {
        // Verifica se listar projetos funciona corretamente
        List<ProjetoSustentavel> projetos = projetoDAO.listarProjetos();
        assertNotNull(projetos, "A lista de projetos não deve ser nula");
        assertTrue(projetos.size() > 0, "Deve retornar ao menos um projeto da base de dados");
    }

    @Test
    public void testAtualizarProjeto() {
        List<ProjetoSustentavel> projetos = projetoDAO.listarProjetos();
        assertFalse(projetos.isEmpty(), "A lista de projetos não deve estar vazia para atualizar");

        ProjetoSustentavel projeto = projetos.get(0);
        projeto.setNome("Projeto Atualizado");

        // Atualiza o projeto
        assertDoesNotThrow(() -> projetoDAO.atualizarProjeto(projeto), "Não deve lançar exceções ao atualizar um projeto");

        // Verifica se o projeto foi atualizado
        List<ProjetoSustentavel> projetosAtualizados = projetoDAO.listarProjetos();
        assertTrue(projetosAtualizados.stream().anyMatch(p -> p.getNome().equals("Projeto Atualizado")),
                "O projeto deve ter sido atualizado com o novo nome");
    }

    @Test
    public void testDeletarProjeto() {
        List<ProjetoSustentavel> projetos = projetoDAO.listarProjetos();
        assertFalse(projetos.isEmpty(), "A lista de projetos não deve estar vazia para deletar");

        ProjetoSustentavel projeto = projetos.get(0);

        // Remove o projeto
        assertDoesNotThrow(() -> projetoDAO.deletarProjeto(projeto.getId()), "Não deve lançar exceções ao deletar um projeto");

        // Verifica se o projeto foi removido
        List<ProjetoSustentavel> projetosAtualizados = projetoDAO.listarProjetos();
        assertFalse(projetosAtualizados.stream().anyMatch(p -> p.getId() == projeto.getId()),
                "O projeto deve ter sido removido da lista");
    }
}
