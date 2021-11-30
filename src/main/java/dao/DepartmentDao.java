package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Department;
import services.HttpService;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class DepartmentDao implements DepartmentDaoInter {
    private final HttpService httpService;
    private final String departmentPath;

    public DepartmentDao() {
        httpService = HttpService.getInstance();
        departmentPath = "/api/departments";
    }

    private ArrayList<Department> jsonToAbbreviationList(String json) {
        Gson gson = new Gson();
        Type departmentListType = new TypeToken<ArrayList<Department>>(){}.getType();
        return gson.fromJson(json, departmentListType);
    }

    @Override
    public ArrayList<Department> getAllDepartments() {
        HttpResponse<String> response = httpService.getResponse(departmentPath);

        if (response != null)
            return (response.statusCode() == 200) ? jsonToAbbreviationList(response.body()) : new ArrayList<>();

        return new ArrayList<>();
    }

    @Override
    public ArrayList<String> getAllDepartmentNames() {
        ArrayList<Department> departments = getAllDepartments();
        ArrayList<String> departmentNames = new ArrayList<>();

        if (departments.isEmpty())
            return departmentNames;

        departments.forEach((Department department) -> departmentNames.add(department.getName()));

        return departmentNames;
    }

    @Override
    public long getDepartmentIdByName(String name) {
        ArrayList<Department> departments = getAllDepartments();

        for (Department department : departments) {
            if (department.getName().equals(name))
                return department.getId();
        }

        return -1;
    }

    @Override
    public Boolean addDepartment(DepartmentModel departmentModel) {

            return HttpService.AddOrUpdateObject(AbrPath, departmentModel);

    }
}
