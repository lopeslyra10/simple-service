package com.example;

import com.example.model.ProjetoSustentavel;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyResourceTest {

    private HttpServer server;
    private WebTarget target;

    @BeforeEach
    public void setUp() throws Exception {
        // Start the server
        server = Main.startServer();
        // Create the client
        Client client = ClientBuilder.newClient();
        target = client.target(Main.BASE_URI);
    }

    @AfterEach
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }

    /**
     * Test to verify the list of projects endpoint.
     */
    @Test
    public void testListarProjetos() {
        Response response = target.path("projetos").request().get();
        assertEquals(200, response.getStatus(), "Deve retornar o status 200");
        // Adicionar verificação da resposta JSON se aplicável
    }

    /**
     * Test to verify creating a new project.
     */
    @Test
    public void testCreateProjeto() {
        ProjetoSustentavel newProjeto = new ProjetoSustentavel();
        newProjeto.setNome("Novo Projeto Sustentável");
        newProjeto.setDescricao("Descrição do projeto");
        newProjeto.setCusto(300000.0);

        Response response = target.path("projetos").request().post(jakarta.ws.rs.client.Entity.json(newProjeto));
        assertEquals(201, response.getStatus(), "Deve retornar o status 201");
    }
    @Test
    public void testListarProjetosPorStatus() {
        Response response = target.path("projetos/status/Em andamento").request().get();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testDeletarProjetosPorTipoFonte() {
        Response response = target.path("projetos/tipoFonte/Solar").request().delete();
        assertEquals(204, response.getStatus());
    }

}
