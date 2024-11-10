package dao;

import db.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.JenisMember;

public class JenisMemberDao {
    private static final Logger logger = Logger.getLogger(JenisMemberDao.class.getName());

    // Fungsi untuk menambahkan jenis member
    public int insert(JenisMember jenisMember) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            String sql = "INSERT INTO jenis_member (id, nama) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, jenisMember.getId());
            statement.setString(2, jenisMember.getNama());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during insert operation", e);
        }
        return result;
    }

    // Fungsi untuk memperbarui jenis member
    public int update(JenisMember jenisMember) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            String sql = "UPDATE jenis_member SET nama = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, jenisMember.getNama());
            statement.setString(2, jenisMember.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during update operation", e);
        }
        return result;
    }

    // Fungsi untuk menghapus jenis member berdasarkan id
    public int delete(JenisMember jenisMember) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            String sql = "DELETE FROM jenis_member WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, jenisMember.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during delete operation", e);
        }
        return result;
    }

    // Fungsi untuk mengambil semua jenis member
    public List<JenisMember> findAll() {
        List<JenisMember> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM jenis_member")) {

            while (resultSet.next()) {
                JenisMember jenisMember = new JenisMember();
                jenisMember.setId(resultSet.getString("id"));
                jenisMember.setNama(resultSet.getString("nama"));
                list.add(jenisMember);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during findAll operation", e);
        }
        return list;
    }

    // Fungsi untuk mencari jenis member berdasarkan id
    public JenisMember findById(String id) {
        JenisMember jenisMember = null;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            String sql = "SELECT * FROM jenis_member WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                jenisMember = new JenisMember();
                jenisMember.setId(resultSet.getString("id"));
                jenisMember.setNama(resultSet.getString("nama"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during findById operation", e);
        }
        return jenisMember;
    }
}
