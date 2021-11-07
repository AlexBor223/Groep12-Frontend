package dao;

import models.DepartmentModel;
import services.HttpService;

import java.util.ArrayList;

/**
 * makes abbreviation data accessible for front-end
 */

public class DepartmentDao implements DepartmentDaoInter {
    services.HttpService HttpService = new HttpService();
    private final String AbrPath = "/api/departments";

    /**
     * get all departments from back-end
     * @return a list of all departments
     */
    @Override
    public ArrayList<DepartmentModel> GetAllDepartments() {

        ArrayList list = new ArrayList();
        try {
            list = (ArrayList) HttpService.GetAllDepartments(AbrPath);
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return list;
        }
    }
}
