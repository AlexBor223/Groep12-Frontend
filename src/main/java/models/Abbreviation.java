package models;


public class Abbreviation {

    private String letters;
    private String meaning;
    private Long department;
    private Integer likes;
    private long id;
    private Boolean approved = false;


    public Abbreviation() {

    }


    public Abbreviation(String letters, String meaning, Long department, Integer likes, long id) {
        this.letters = letters;
        this.meaning = meaning;
        this.department = department;
        this.likes = likes;
        this.id = id;
    }

    public Abbreviation(String letters, String meaning, Long department, Integer likes) {
        this.letters = letters;
        this.meaning = meaning;
        this.department = department;
        this.likes = likes;
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

    /**
     * gets likes from abbreviation
     *
     * @return like count
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     * set likes count of abbreviation
     *
     * @param likes amount of likes being set
     */
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getDepartment() {
        return department;
    }

    /**
     * checks if the abbreviation is approved
     *
     * @return if the abbreviation is approved or not
     */
    public Boolean isApproved() {
        return approved;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }


}
