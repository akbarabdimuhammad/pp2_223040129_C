package dao;

import db.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.JenisMember;
import model.Member;

public class MemberDao {
    private static final Logger logger = Logger.getLogger(MemberDao.class.getName());

    // Menambah member baru
    public int insert(Member member) {
        int result = -1;
        String sql = "INSERT INTO member (id, nama, jenis_member_id) VALUES (?, ?, ?)";
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, member.getId());
            statement.setString(2, member.getNama());
            statement.setString(3, member.getJenisMember().getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during insert operation", e);
        }
        return result;
    }

    // Memperbarui data member
    public int update(Member member) {
        int result = -1;
        String sql = "UPDATE member SET nama = ?, jenis_member_id = ? WHERE id = ?";
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, member.getNama());
            statement.setString(2, member.getJenisMember().getId());
            statement.setString(3, member.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during update operation", e);
        }
        return result;
    }

    // Menghapus member
    public int delete(Member member) {
        int result = -1;
        String sql = "DELETE FROM member WHERE id = ?";
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, member.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during delete operation", e);
        }
        return result;
    }

    // Mengambil semua data member beserta jenis membernya
    public List<Member> findAll() {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT member.id, member.nama, jenis_member.id AS jenis_member_id, jenis_member.nama AS jenis_member_nama " +
                     "FROM member JOIN jenis_member ON jenis_member.id = member.jenis_member_id";
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getString("id"));
                member.setNama(resultSet.getString("nama"));

                // Mengambil jenis member yang terkait dengan member ini
                JenisMember jenisMember = new JenisMember();
                jenisMember.setId(resultSet.getString("jenis_member_id"));
                jenisMember.setNama(resultSet.getString("jenis_member_nama"));

                member.setJenisMember(jenisMember);
                list.add(member);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during findAll operation", e);
        }
        return list;
    }

    // Mencari member berdasarkan ID
    public Member findById(String id) {
        Member member = null;
        String sql = "SELECT member.id, member.nama, jenis_member.id AS jenis_member_id, jenis_member.nama AS jenis_member_nama " +
                     "FROM member JOIN jenis_member ON jenis_member.id = member.jenis_member_id WHERE member.id = ?";
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                member = new Member();
                member.setId(resultSet.getString("id"));
                member.setNama(resultSet.getString("nama"));

                JenisMember jenisMember = new JenisMember();
                jenisMember.setId(resultSet.getString("jenis_member_id"));
                jenisMember.setNama(resultSet.getString("jenis_member_nama"));

                member.setJenisMember(jenisMember);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during findById operation", e);
        }
        return member;
    }
}
