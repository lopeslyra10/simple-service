package com.example.infra;

import com.example.model.ProjetoSustentavel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {

    public ProjetoDAO() {
    }

    public List<ProjetoSustentavel> listarProjetos() {
        List<ProjetoSustentavel> projetos = new ArrayList<>();
        String sql = "SELECT * FROM TB_PROJETOS";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProjetoSustentavel projeto = new ProjetoSustentavel();
                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setTipoFonte(rs.getString("tipoFonte"));
                projeto.setRegiao(rs.getString("regiao"));
                projeto.setCusto(rs.getDouble("custo"));
                projeto.setStatus(rs.getString("status"));
                projeto.setEmissoesCarbono(rs.getDouble("emissoesCarbono"));
                projetos.add(projeto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projetos;
    }


    public void inserirProjeto(ProjetoSustentavel projeto) {
        String sql = "INSERT INTO TB_PROJETOS (nome, descricao, tipoFonte, regiao, custo, status) VALUES (?, ?, ?, ?, ?, ?)";
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
        String sql = "UPDATE TB_PROJETOS SET nome = ?, descricao = ?, tipoFonte = ?, regiao = ?, custo = ?, status = ? WHERE id = ?";
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
        String sql = "DELETE FROM TB_PROJETOS WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProjetoSustentavel> listarProjetosPorStatus(String status) {
        List<ProjetoSustentavel> projetos = new ArrayList<>();
        String sql = "SELECT * FROM TB_PROJETOS WHERE status = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
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

    public List<ProjetoSustentavel> listarProjetosPorTipoFonte(String tipoFonte) {
        List<ProjetoSustentavel> projetos = new ArrayList<>();
        String sql = "SELECT * FROM TB_PROJETOS WHERE tipoFonte = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipoFonte);
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
}
