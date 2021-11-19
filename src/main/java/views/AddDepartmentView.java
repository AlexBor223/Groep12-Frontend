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
    private Text StatusText;

    @FXML
    public void initialize(){

    }

    public void addDepartment(){
        String input = DepartmentName.getText();

        if(controller.inputExists(input)) {
            StatusText.setFill(Color.RED);
            StatusText.setText("department bestaat al");
            return;
        }

        if(departmentConfirmation(input)){
            controller.addDepartment(input);
            StatusText.setFill(Color.BLUE);
            StatusText.setText("department is toegevoegd");
        }
        else{return;};

    }
    private Boolean departmentConfirmation(String departmentName){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Department onfirmation");
        alert.setHeaderText("u wilt een department met \""+departmentName+ "\" als naam toevoegen");
        alert.setContentText("klopt de spelling?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }

    }
}
