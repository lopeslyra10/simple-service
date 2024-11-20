package com.example.infra;

import com.example.model.Consumo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsumoDAO {
    public List<Consumo> listarConsumos() {
        List<Consumo> consumos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM consumos";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consumo consumo = new Consumo(
                        rs.getInt("id"),
                        rs.getString("tipoDispositivo"),
                        rs.getDouble("nivelConsumo"),
                        rs.getTimestamp("horario").toLocalDateTime()
                );
                consumos.add(consumo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consumos;
    }

    public void inserirConsumo(Consumo consumo) {
        String sql = "INSERT INTO consumos (tipoDispositivo, nivelConsumo, horario) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, consumo.getTipoDispositivo());
            stmt.setDouble(2, consumo.getNivelConsumo());
            stmt.setTimestamp(3, Timestamp.valueOf(consumo.getHorario()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarConsumo(Consumo consumo) {
        String sql = "UPDATE consumos SET tipoDispositivo = ?, nivelConsumo = ?, horario = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, consumo.getTipoDispositivo());
            stmt.setDouble(2, consumo.getNivelConsumo());
            stmt.setTimestamp(3, Timestamp.valueOf(consumo.getHorario()));
            stmt.setInt(4, consumo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarConsumo(int id) {
        String sql = "DELETE FROM consumos WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}