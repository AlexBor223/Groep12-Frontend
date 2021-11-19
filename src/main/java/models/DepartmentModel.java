package models;

public class DepartmentModel {

    private Long department;
    private String name;

    private String letters;

    public Long getId() {
        System.out.println(department);
        return department;
    }

    public String getName() {
        return name;
    }

    public DepartmentModel(String name){
        this.name =name;
        this.letters = name;
    }
}
