package main.java.db;

import main.java.core.ServerConfig;
import main.java.entities.publications.MediaPublication;

import java.sql.*;

import static main.java.core.Configuration.getServerConfig;

public class DuoyulongDao {
    public static void main(String[] args){
        cleanTestUsers();
    }


    public static Connection connect() {
        ServerConfig config = getServerConfig();
        String url = "jdbc:postgresql://" + config.getDbHost() + ":5432/duoyulong";
        String user = "";
        String password = "";
        String dbName = "duoyulong";

        try {
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(url, user, password);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void cleanTestPublications() {
        String SQL = "DELETE FROM media_publications WHERE name LIKE '%TA';" +
                "DELETE FROM content_items_content_publications WHERE content_publication_id IN (SELECT cicp.content_publication_id FROM content_publications cp JOIN content_items_content_publications cicp ON cp.id = cicp.content_publication_id WHERE cp.name LIKE '%TA'); DELETE FROM content_publications WHERE name LIKE '%TA'; COMMIT;";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()){
             stmt.executeQuery(SQL);
            System.out.println("Test Publications are terminated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void cleanTestUsers(){
        String sql = "DELETE FROM user_tokens WHERE user_id IN (SELECT cicp.user_id FROM users cp JOIN user_tokens cicp ON cp.id = cicp.user_id WHERE cp.email LIKE '%autotest.com');" +
                "DELETE FROM users WHERE email LIKE '%autotest.com'";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()){
            stmt.executeQuery(sql);
            System.out.println("Test Users are terminated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void createMediaPublication(MediaPublication mediaPublication){

        String sql = "INSERT INTO media_publications(id, license_id, language_id, name, description, created_at, updated_at, status, deleted_at, author, source_link, license_notes) VALUES(uuid_generate_v4(), ?, ?, ?, ?, now(), now(), '0', NULL,?, ?, ?)";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setObject(1, mediaPublication.license, 1111); // Bullshit but works https://stackoverflow.com/questions/11284359/persisting-uuid-in-postgresql-using-jpa
            pstmt.setObject(2, mediaPublication.language, 1111);
            pstmt.setString(3, mediaPublication.name);
            pstmt.setString(4, mediaPublication.description);
            pstmt.setString(5, mediaPublication.author);
            pstmt.setString(6, mediaPublication.sourceLink);
            pstmt.setString(7, mediaPublication.licenseNotes);
            pstmt.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
