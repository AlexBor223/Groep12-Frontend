package models;

public class Abbreviation {
    private long id;
    private String letters;
    private String meaning;
    private int likes;

    public Abbreviation(long id, String letters, String meaning, int likes) {
        this.id = id;
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
}
