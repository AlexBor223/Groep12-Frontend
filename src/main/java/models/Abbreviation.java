package models;

public class Abbreviation {

    private String letters;
    private String meaning;
    private String department;
    private long id;


    public Abbreviation(){

    }



    public Abbreviation(String letters, String meaning, String department, Integer likes, long id){
        this.letters = letters;
        this.meaning = meaning;
        this.department = department;
        this.id = id;
    }

    public Abbreviation(String letters, String meaning, String department, Integer likes){
        this.letters = letters;
        this.meaning = meaning;
        this.department = department;
    }



    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
