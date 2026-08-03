package org.example.studentmangementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StudentLoginController {
    @FXML
    private Button close;

    @FXML
    Button login;

    @FXML
    BorderPane login_form;
    @FXML
    Button adminLogin;
    @FXML
    AnchorPane man_form;
    @FXML
    AnchorPane user_form;

    @FXML
    TextField password;

    @FXML
    Button si_createAccountBtn;

    @FXML
    TextField username;
    @FXML
    Connection connect;
    @FXML
    PreparedStatement prepare;
    @FXML
    ResultSet result;

    public void close() {
        System.exit(0);
    }

    private double x = 0;
    private double y = 0;
    public static studentData currentLoggedInStudent;

    public void loginAdmin() {
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
            return;
        }

        String sql = "SELECT s.studentNum, s.firstName, s.course, s.year, s.status, g.final, s.image " +
                "FROM student s " +
                "JOIN student_grade g ON s.studentNum = g.studentNum " +
                "WHERE s.studentNum = ? AND s.password = ?";

        connect = database.connectDB();
        try {
            Alert alert;
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            result = prepare.executeQuery();

            if (result.next()) {
                currentLoggedInStudent = new studentData(
                        result.getInt("studentNum"),
                        result.getString("firstName"),
                        result.getString("course"),
                        result.getString("year"),
                        result.getDouble("final"),
                        result.getString("image"),
                        result.getString("status")
                );

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Logged In!");
                alert.showAndWait();

                login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("studentdashboard.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Username/Password");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void adminlogin(ActionEvent e) throws IOException {
        if (e.getSource().equals(adminLogin)) {
            adminLogin.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getSceneX() - x);
                stage.setY(event.getScreenY() - y);
            });

            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();
        }
    }

    private void setCurrentLoggedInStudent(String username) {
        String query = "SELECT s.studentNum, s.firstName, s.course, s.year, s.status, s.image, g.final " +
                "FROM student s " +
                "JOIN student_grade g ON s.studentNum = g.studentNum " +
                "WHERE s.studentNum = ?";

        connect = database.connectDB();
        try {
            studentData studentD;
            prepare = connect.prepareStatement(query);
            prepare.setString(1, username);
            result = prepare.executeQuery();

            if (result.next()) {
                int studentNum = result.getInt("studentNum");
                String firstNameDB = result.getString("firstName");
                String course = result.getString("course");
                String year = result.getString("year");
                double finals = result.getDouble("final");
                String image = result.getString("image");
                String status = result.getString("status");

                currentLoggedInStudent = new studentData(
                        studentNum,
                        firstNameDB,
                        course,
                        year,
                        finals,
                        image,
                        status
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}





