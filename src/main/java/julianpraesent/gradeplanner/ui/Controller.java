package julianpraesent.gradeplanner.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import julianpraesent.gradeplanner.helper.DataHandler;
import julianpraesent.gradeplanner.model.Course;
import julianpraesent.gradeplanner.model.GradeEnum;
import julianpraesent.gradeplanner.model.TypeEnum;
import lombok.var;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Controller {

    @FXML
    private ListView lv_courses;

    @FXML
    private Label lbl_courseTitle;

    @FXML
    private Label lbl_type;

    @FXML
    private Label lbl_title;

    @FXML
    private Label lbl_ects;

    @FXML
    private Label lbl_grade;

    @FXML
    private Label lbl_avg;

    @FXML
    private Label lbl_totalEcts;

    @FXML
    private CheckBox chb_lock;

    @FXML
    private CheckBox chb_graded;

    @FXML
    private ChoiceBox choicebox_type;

    @FXML
    private TextField tf_title;

    @FXML
    private TextField tf_ects;

    @FXML
    private TextField tf_grade;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_newCourse;

    @FXML
    private Button btn_import;

    @FXML
    private Button btn_export;

    @FXML
    protected void exportCourses(ActionEvent event) {
        this.lv_courses = new ListView();
        Course[] courses = (Course[]) lv_courses.getItems().toArray();
        ArrayList<Course> courseList = (ArrayList<Course>) Arrays.asList(courses);

        // TODO: remove after testing
        Course helper = Course.builder()
                .id(UUID.randomUUID().toString())
                .title("Test Course")
                .type(TypeEnum.VU)
                .ects(10)
                .grade(GradeEnum.GUT)
                .graded(false)
                .locked(false)
                .build();
        courseList.add(helper);

        DataHandler.writeFile(courseList, "data.csv");
    }

    @FXML
    protected void importCourses(ActionEvent event) {
        var importedCourses = DataHandler.loadFile("data.csv");
        ObservableList<Course> courses = FXCollections.observableArrayList(importedCourses);

        this.lv_courses = new ListView();
        this.lv_courses.setItems(courses);
    }
}