package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepoProduct {
    private final Connection conn;

    public RepoProduct(Connection conn){
        this.conn = conn;
    }

    public boolean isExist(int id) {
        String query = "SELECT COUNT(id) FROM product WHERE id = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery(query)) {
                if (rs.next()) {
                    return rs.getInt("id") > 0;
                }
            }
        }catch (SQLException e){
            System.err.println("Esse produto não existe.");
        }
        return false;
    }

}
