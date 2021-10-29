package models;

public class Abbreviation {

    private String letters;
    private String meaning;
    private Integer likes;
    private long id;

    Abbreviation(String letters, String meaning, Integer likes, long id){
        this.letters = letters;
        this.meaning = meaning;
        this.likes = likes;
        this.id = id;
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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
