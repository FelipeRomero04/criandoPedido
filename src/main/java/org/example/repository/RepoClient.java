package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepoClient {
    private final Connection conn;

    public RepoClient(Connection conn){
        this.conn = conn;
    }

    public boolean isExist(int id_client){
        String query = "SELECT COUNT(*) FROM clients WHERE id = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id_client);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("id") > 0;
                }
            }
        }catch(SQLException e){
            System.err.println("Cliente não existe!");
        }
        return false;
    }

}
