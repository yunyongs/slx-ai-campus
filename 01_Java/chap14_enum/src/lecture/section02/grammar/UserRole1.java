package lecture.section02.grammar;

import java.util.Locale;

public enum UserRole1 {
    GUEST,
    CONSUMER,
    PRODUCER,
    ADMIN;

    UserRole1() {
        System.out.println(this.name());

    }
    public String getNameToLowerCase() {
        return this.name().toLowerCase();
    }
}
