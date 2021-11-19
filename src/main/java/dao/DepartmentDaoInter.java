package dao;

import models.DepartmentModel;

import java.util.ArrayList;

public interface DepartmentDaoInter {
    ArrayList<DepartmentModel> GetAllDepartments();
    Boolean addDepartment(DepartmentModel departmentModel);
}
