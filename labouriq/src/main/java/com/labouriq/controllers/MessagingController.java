package com.labouriq.controllers;

import com.labouriq.dao.MessageDAO;
import com.labouriq.model.Message;
import com.labouriq.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MessagingController {

    @FXML private ListView<String> chatList;
    @FXML private TextField messageField;
    @FXML private Button sendButton;

    private final MessageDAO messageDAO = new MessageDAO();

    // TEMP: chatting with selected user
    private int receiverId = 2; // later from job/employer selection

    @FXML
    public void initialize() {
        loadMessages();
    }

    @FXML
    private void onSendMessage() {
        String text = messageField.getText().trim();
        if (text.isEmpty()) return;

        try {
            Message msg = new Message(Session.getUserId(), receiverId, text);
            messageDAO.sendMessage(msg);
            messageField.clear();
            loadMessages();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMessages() {
        try {
            chatList.getItems().clear();

            for (Message m : messageDAO.getConversation(Session.getUserId(), receiverId)) {
                String prefix = (m.getSenderId() == Session.getUserId()) ? "You: " : "Them: ";
                chatList.getItems().add(prefix + m.getMessageText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
