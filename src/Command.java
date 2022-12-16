public enum Command {
    EXIT(0), ADMIN(1), STUDENT(2), COURSE_LIST(1), TEACHER_LIST(2),
    STUDENT_LIST(3), CHANGE_TERMINAL(4), ENGLISH(1), HISTORY(2), MATH(3),
    BACK_OPTION(4), REMOVE_STUDENT(1), ADD_STUDENT(2), REMOVE_TEACHER(3),
    ADD_TEACHER(4);

    private final int value;

    private Command(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
