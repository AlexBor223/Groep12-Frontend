package controllers;

import dao.DepartmentDao;
import models.Department;

import java.util.ArrayList;

public class DepartmentController {
    private final DepartmentDao departmentDao;

    public DepartmentController() {
        departmentDao = new DepartmentDao();
    }

    public ArrayList<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    public long getDepartmentIdByName(String name) {
        return departmentDao.getDepartmentIdByName(name);
    }

    public ArrayList<String> getAllDepartmentNames() {
        return departmentDao.getAllDepartmentNames();
    }

    public void createDepartment(Department department) { departmentDao.updateDepartment(department);}
}
