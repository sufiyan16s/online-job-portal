package com.labouriq.dao;

import com.labouriq.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    private final String URL = "jdbc:mysql://localhost:3306/labouriq_db";
    private final String USER = "root";
    private final String PASS = "Sufi$&8416@";

    // SAVE MESSAGE
    public void sendMessage(Message msg) throws Exception {
        String sql = "INSERT INTO messages (sender_id, receiver_id, message_text) VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, msg.getSenderId());
            ps.setInt(2, msg.getReceiverId());
            ps.setString(3, msg.getMessageText());
            ps.executeUpdate();
        }
    }

    // LOAD CHAT
    public List<Message> getConversation(int user1, int user2) throws Exception {
        List<Message> list = new ArrayList<>();

        String sql = """
            SELECT * FROM messages
            WHERE (sender_id=? AND receiver_id=?)
               OR (sender_id=? AND receiver_id=?)
            ORDER BY sent_at
        """;

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, user1);
            ps.setInt(2, user2);
            ps.setInt(3, user2);
            ps.setInt(4, user1);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Message(
                        rs.getInt("id"),
                        rs.getInt("sender_id"),
                        rs.getInt("receiver_id"),
                        rs.getString("message_text"),
                        rs.getTimestamp("sent_at").toLocalDateTime()
                ));
            }
        }
        return list;
    }
}
