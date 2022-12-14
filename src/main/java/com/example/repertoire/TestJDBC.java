package com.example.repertoire;
import java.sql.*;

public class TestJDBC {
    public static void main(String[] args) throws SQLException {
        Connection conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Chargement du driver réussi");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/repertoire","root","");
            System.out.println("Connexion à la base de données réussi");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erreur de chargement du driver : "+e);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la connextion à la base de données : "+e);
        }

        Statement statement = conn.createStatement();
        ResultSet res = statement.executeQuery("SELECT * FROM contact");
        while(res.next())
        {
            int id = res.getInt("id");
            String nom = res.getString("nom");
            String prenom = res.getString("prenom");
            String adresse = res.getString("adresse");
            String ville = res.getString("ville");
            String cp = res.getString("cp");
            String tel = res.getString("tel");
            String email = res.getString("email");

            System.out.println("id:"+id+"\tnom:"+nom+"\tprenom:"+prenom+"\tadresse:"+adresse+"\tville:"+ville+"\tcp:"+cp+"\ttel:"+tel+"\temail:"+email);
        }
    }
}
