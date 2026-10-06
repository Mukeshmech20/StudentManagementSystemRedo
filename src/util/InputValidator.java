package util;

public class InputValidator {

    private InputValidator() {
        // Utility class - no object creation required
    }

    public static boolean isValidId(int id) {
        return id > 0;
    }

    public static boolean isValidAge(int age) {
        return age > 0 && age <= 120;
    }

    public static boolean isValidName(String name) {
        return name != null
                && !name.trim().isEmpty()
                && name.trim().matches("[a-zA-Z ]+");
    }

    public static boolean isValidCourse(String course) {
        return course != null
                && !course.trim().isEmpty()
                && course.trim().matches("[a-zA-Z0-9 &.-]+");
    }
}