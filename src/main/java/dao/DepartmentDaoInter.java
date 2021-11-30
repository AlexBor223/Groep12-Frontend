package dao;

import models.Department;

import java.util.ArrayList;

public interface DepartmentDaoInter {
    ArrayList<Department> getAllDepartments();

    ArrayList<String> getAllDepartmentNames();

    long getDepartmentIdByName(String name);

    void updateDepartment(Department department);
}
