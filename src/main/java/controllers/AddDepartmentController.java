package controllers;

import dao.DepartmentDao;
import models.DepartmentModel;

import java.util.ArrayList;

public class AddDepartmentController {

    private DepartmentDao departmentDao = new DepartmentDao();

    public Boolean inputExists(String input){
        ArrayList<DepartmentModel> departmentList = departmentDao.GetAllDepartments();
        for(int i=0; i<departmentList.size();i++){
            if(departmentList.get(i).getName().equals(input)){
                return true;
            }
        }
        return false;
    }

    public Boolean addDepartment(String name, String abbreviation){
        DepartmentModel departmentModel = new DepartmentModel(name, abbreviation);
        if(departmentDao.addDepartment(departmentModel)){
            return true;
        }
        return false;
    }
}
