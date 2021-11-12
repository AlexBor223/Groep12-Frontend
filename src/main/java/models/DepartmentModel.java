package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepartmentModel {

    private Long department;
    private String name;
    private String abbreviation;

    public Long getId() {
        System.out.println(department);
        return department;
    }

    public String getName() {
        return name;
    }
}
