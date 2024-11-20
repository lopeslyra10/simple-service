package com.example.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public ConnectionFactory() {
    }

    public static Connection getConnection() {
        String urlDeConexao = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
        String login = "rm558209";
        String senha = "230206";

        try {
            return DriverManager.getConnection(urlDeConexao, login, senha);
        } catch (SQLException var5) {
            throw new RuntimeException(var5);
        }
    }
}