package org.example.studentmangementsystem;

import javafx.scene.image.Image;

import java.sql.Date;

public class studentData {
    private Integer studentNum;
    private String year;
    private String course;
    private String firstName;
    private String lastName;
    private String gender;
    private String status;
    private Date birth;
    private String image;
    private String password;
    private Double firstSem;
    private Double secondSem;
    private Double finals;

    public studentData(Integer studentNum, String year, String course, String firstName, String lastName, String gender, Date birth,String status, String image,String password) {
        this.studentNum = studentNum;
        this.year = year;
        this.course = course;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.status = status;
        this.birth = birth;
        this.image = image;
        this.password=password;
    }
    public studentData(Integer studentNum, String year, String course, Double firstSem, Double secondSem, Double finals) {
        this.studentNum = studentNum;
        this.year = year;
        this.course = course;
        this.firstSem = firstSem;
        this.secondSem = secondSem;
        this.finals = finals;
    }
    public  studentData(Integer studentNum,String firstName,String course,String year,Double finals,String image,String status){
        this.studentNum=studentNum;
        this.firstName=firstName;
        this.course=course;
        this.year=year;
        this.finals=finals;
        this.image=image;
        this.status=status;
    }



    public Integer getStudentNum() {
        return studentNum;
    }

    public String getYear() {
        return year;
    }

    public String getCourse() {
        return course;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

    public Date getBirth() {
        return birth;
    }
    public String getPassword(){
        return password;
    }

    public String getImage() {
        return image;
    }
    public Double getFirstSem() {
        return firstSem;
    }

    public Double getSecondSem() {
        return secondSem;
    }

    public Double getFinals() {
        return finals;
    }

    public void setStudentNum(Integer studentNum) { this.studentNum = studentNum; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setCourse(String course) { this.course = course; }
    public void setYear(String year) { this.year = year; }
    public void setFinals(Double finals) { this.finals = finals; }
    public void setImage(String image) { this.image = image; }
    public void setStatus(String status) { this.status = status; }
}


