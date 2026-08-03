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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

    public class LoginController implements Initializable {

        @FXML
        private Button close;

        @FXML
        Button login;
        @FXML
        Button studentLogin;

        @FXML
        BorderPane login_form;

        @FXML
        AnchorPane man_form;
        @FXML
        AnchorPane user_form;

        @FXML
         PasswordField password;

        @FXML
        Button si_createAccountBtn;

        @FXML
        TextField username;
        @FXML
        Connection connect ;
        @FXML
        PreparedStatement prepare;
        @FXML
        ResultSet result;
        public void close(){
            System.exit(0);
        }
        private double x=0;
        private double y=0;
        public void loginAdmin() {
            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
            connect = database.connectDB();
            try{
                Alert alert;

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, username.getText());
                prepare.setString(2, password.getText());

                result = prepare.executeQuery();
                if(username.getText().isEmpty() || password.getText().isEmpty()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields");
                    alert.showAndWait();
                }else{
                    if(result.next()){


                        getData.username = username.getText();

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Login!");
                        alert.showAndWait();

                        login.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                        Stage stage = new Stage();
                        Scene scene = new Scene(root);

                        root.setOnMousePressed((MouseEvent event) ->{
                            x = event.getSceneX();
                            y = event.getSceneY();
                        });

                        root.setOnMouseDragged((MouseEvent event) ->{
                            stage.setX(event.getScreenX() - x);
                            stage.setY(event.getScreenY() - y);
                        });

                        stage.initStyle(StageStyle.TRANSPARENT);

                        stage.setScene(scene);
                        stage.show();

                    }else{
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Wrong Username/Password");
                        alert.showAndWait();
                    }
                }
            }catch(Exception e){e.printStackTrace();}

        }

        public void studentLogin(ActionEvent e) throws IOException {
            if(e.getSource().equals(studentLogin)){
                studentLogin.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("student.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event)->{
                    x=event.getSceneX();
                    y=event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event)->{
                    stage.setX(event.getSceneX()-x);
                    stage.setY(event.getScreenY()-y);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
        }
    }

