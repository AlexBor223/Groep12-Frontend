package models;

public class Abbreviation {
    private long id;
    private long departmentId;

    private String letters;
    private String meaning;

    private int likes;
    private boolean approved = false;

    public Abbreviation() {}

    public Abbreviation(long id, long departmentId, String letters, String meaning, int likes) {
        this.id = id;
        this.departmentId = departmentId;
        this.letters = letters;
        this.meaning = meaning;
        this.likes = likes;
    }

    public Abbreviation(long departmentId, String letters, String meaning, int likes) {
        this.departmentId = departmentId;
        this.letters = letters;
        this.meaning = meaning;
        this.likes = likes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean equals(Abbreviation abbreviation) {
        return id == abbreviation.getId() &&
               departmentId == abbreviation.getDepartmentId() &&
               likes == abbreviation.getLikes() &&
               approved == abbreviation.isApproved() &&
               letters.equals(abbreviation.getLetters()) &&
               meaning.equals(abbreviation.getMeaning());
    }
}
