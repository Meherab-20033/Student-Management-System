package org.example.studentmangementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.scene.image.Image;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.example.studentmangementsystem.StudentLoginController.currentLoggedInStudent;

public class StudentDashboardController implements Initializable {

    @FXML
    private Button StudentInfoBtn;

    @FXML
    private AnchorPane StudentInfo_HomeForm;

    @FXML
    private ImageView StudentInfo_Image;

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
    private Label StudentInfo_dep;

    @FXML
    private Button StudentInfo_messageBtn;

    @FXML
    private AnchorPane StudentInfo_messegeForm;

    @FXML
    private Label StudentInfo_name;

    @FXML
    private Label StudentInfo_result;

    @FXML
    private Label StudentInfo_status;

    @FXML
    private Label StudentInfo_year;

    @FXML
    private ImageView Student_Image;

    @FXML
    private Label SutdentInfo_id;

    @FXML
    private Button close;

    @FXML
    private Button logout;

    @FXML
    private Label username;


    @FXML
    void close(ActionEvent event) {
System.exit(0);
    }

    private double x=0;
    private double y=0;
    public void logout(){
        try{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();


                Parent root = FXMLLoader.load(getClass().getResource("student.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    void switchForm(ActionEvent event) {
        if(event.getSource().equals(StudentInfoBtn)){
            StudentInfo_messegeForm.setVisible(false);
            StudentInfo_HomeForm.setVisible(true);
            
        } else if (event.getSource().equals(StudentInfo_messageBtn)) {
            StudentInfo_messegeForm.setVisible(false);
            StudentInfo_HomeForm.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StudentInfo_name.setText(currentLoggedInStudent.getFirstName());
        SutdentInfo_id.setText(String.valueOf(currentLoggedInStudent.getStudentNum()));
        StudentInfo_dep.setText(currentLoggedInStudent.getCourse());
        StudentInfo_year.setText(currentLoggedInStudent.getYear());
        StudentInfo_result.setText(String.valueOf(currentLoggedInStudent.getFinals()));
        StudentInfo_status.setText(currentLoggedInStudent.getStatus());

        String imagePath = currentLoggedInStudent.getImage();
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                Image studentImage = new Image(new FileInputStream(imagePath), 200, 195, false, true);
                StudentInfo_Image.setImage(studentImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            StudentInfo_Image.setImage(null);
        }
    }

}
