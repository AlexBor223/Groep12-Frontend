package views;

import controllers.AddDepartmentController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import dao.DepartmentDao;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import models.DepartmentModel;

import java.util.ArrayList;
import java.util.Optional;

public class AddDepartmentView {

        AddDepartmentController controller = new AddDepartmentController();

    @FXML
    private Button AbrToApp;
    @FXML
    private TextField DepartmentName;
    @FXML
    private TextField Abbreviation;

    @FXML
    private Text StatusText;

    @FXML
    public void initialize(){

    }

    public void addDepartment(){
        String input1 = DepartmentName.getText();
        String input2 = Abbreviation.getText();

        if(controller.inputExists(input1)) {
            StatusText.setFill(Color.RED);
            StatusText.setText("department bestaat al");
            return;
        }

        if(departmentConfirmation(input1, input2)){
            controller.addDepartment(input1, input2);
            StatusText.setFill(Color.BLUE);
            StatusText.setText("department is toegevoegd");
        }
        else{return;};

    }
    private Boolean departmentConfirmation(String departmentName, String afkorting){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Department onfirmation");
        alert.setHeaderText("u wilt een department met \""+departmentName+ "\" als naam en \""+afkorting+" \" toevoegen");
        alert.setContentText("klopt de spelling?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }

    }
}