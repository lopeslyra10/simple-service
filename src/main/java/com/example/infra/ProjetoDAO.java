package com.example.infra;

import com.example.controller.ConnectionFactory;
import com.example.model.ProjetoSustentavel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {

    public List<ProjetoSustentavel> listarProjetos() {
        List<ProjetoSustentavel> projetos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM projetos";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProjetoSustentavel projeto = new ProjetoSustentavel();
                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setTipoFonte(rs.getString("tipoFonte"));
                projeto.setRegiao(rs.getString("regiao"));
                projeto.setCusto(rs.getDouble("custo"));
                projeto.setStatus(rs.getString("status"));
                projetos.add(projeto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projetos;
    }

    public void inserirProjeto(ProjetoSustentavel projeto) {
        String sql = "INSERT INTO projetos (nome, descricao, tipoFonte, regiao, custo, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setString(3, projeto.getTipoFonte());
            stmt.setString(4, projeto.getRegiao());
            stmt.setDouble(5, projeto.getCusto());
            stmt.setString(6, projeto.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarProjeto(ProjetoSustentavel projeto) {
        String sql = "UPDATE projetos SET nome = ?, descricao = ?, tipoFonte = ?, regiao = ?, custo = ?, status = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setString(3, projeto.getTipoFonte());
            stmt.setString(4, projeto.getRegiao());
            stmt.setDouble(5, projeto.getCusto());
            stmt.setString(6, projeto.getStatus());
            stmt.setInt(7, projeto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarProjeto(int id) {
        String sql = "DELETE FROM projetos WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
