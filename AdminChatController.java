package org.example.studentmangementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminChatController implements Initializable {

    @FXML
    private AnchorPane Chatform;
    @FXML
    private Connection connect;

    @FXML
    private ScrollPane adminChart_Scroll;

    @FXML
    private Button adminChat_SelectedID;

    @FXML
    private TextArea adminChat_Textarea;

    @FXML
    private TextField adminChat_search;

    @FXML
    private Button adminChat_send;

    @FXML
    private Button close;

    @FXML
    private AnchorPane resultGridPane;

    @FXML
    void close(ActionEvent event) {
System.exit(0);
    }
    @FXML
    public void searchStudent() {
        String studentNum = adminChat_search.getText();
        connect = database.connectDB();

        resultGridPane.getChildren().clear();

        String sql = "SELECT * FROM student WHERE studentNum LIKE ?";


        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, studentNum + "%");

            ResultSet result = prepare.executeQuery();

            boolean isFirstMatch = true;


            while (result.next()) {
                Label studentNumLabel = new Label(result.getString("studentNum"));
                Label firstNameLabel = new Label(result.getString("firstName"));

                studentNumLabel.setStyle("-fx-font-size: 14; -fx-text-fill: black;");
                firstNameLabel.setStyle("-fx-font-size: 14; -fx-text-fill: black;");


                double verticalPosition = 20 + (result.getRow() * 30);
                AnchorPane.setTopAnchor(studentNumLabel, verticalPosition);
                AnchorPane.setLeftAnchor(studentNumLabel, 10.0);

                AnchorPane.setTopAnchor(firstNameLabel, verticalPosition);
                AnchorPane.setLeftAnchor(firstNameLabel, 100.0);


                resultGridPane.getChildren().addAll(studentNumLabel, firstNameLabel);

                if (isFirstMatch) {
                    adminChat_SelectedID.setText(result.getString("studentNum"));
                    isFirstMatch = false;
                }
            }

            if (resultGridPane.getChildren().isEmpty()) {
                Label noResults = new Label("No students found.");
                noResults.setStyle("-fx-font-size: 14; -fx-text-fill: red;");
                AnchorPane.setTopAnchor(noResults, 20.0);
                AnchorPane.setLeftAnchor(noResults, 10.0);

                resultGridPane.getChildren().add(noResults);
                adminChat_SelectedID.setText("");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminChat_search.setOnKeyTyped(event -> {
            searchStudent();
        });
    }
}

