package models;

public class AbbreviationCloner {
    public Abbreviation clone(Abbreviation abbreviation) {
        return new Abbreviation(
                abbreviation.getId(),
                abbreviation.getDepartmentId(),
                abbreviation.getLetters(),
                abbreviation.getMeaning(),
                abbreviation.getLikes(),
                abbreviation.isApproved()
        );
    }
}