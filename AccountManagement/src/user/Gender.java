package user;

public enum Gender {
    MALE,
    FEMALE;

    public static Gender parse(String gender) {
        if (gender.startsWith("F")){
            return Gender.FEMALE;
        }else if (gender.startsWith("M")){
            return Gender.MALE;
        }
        return null;
    }
}
