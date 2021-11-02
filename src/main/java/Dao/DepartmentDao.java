package Dao;

import kong.unirest.JsonNode;
import models.DepartmentModel;
import services.HttpService;

import java.util.ArrayList;

public class DepartmentDao implements DepartmentDaoInter {
    services.HttpService HttpService = new HttpService();
    private final String AbrPath = "/api/Department";

    @Override
    public ArrayList<DepartmentModel> GetAllDepartments() {
        ArrayList list = new ArrayList();
        try {
            JsonNode Json = HttpService.SearchObject(AbrPath);
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
