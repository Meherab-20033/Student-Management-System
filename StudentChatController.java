package org.example.studentmangementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentChatController {

    @FXML
    private AnchorPane StudentInfo_MessageScrollPane;

    @FXML
    private TextField StudentInfo_MessageSearch;

    @FXML
    private Label StudentInfo_MessageSelectedID;

    @FXML
    private Button StudentInfo_MessageSend;

    @FXML
    private TextArea StudentInfo_MessageTextArea;

    @FXML
    private AnchorPane StudentInfo_messegeForm;

    @FXML
    private Button close;

    @FXML
    void close(ActionEvent event) {
System.exit(0);
    }

}
