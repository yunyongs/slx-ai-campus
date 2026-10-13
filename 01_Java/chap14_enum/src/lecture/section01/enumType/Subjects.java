package lecture.section01.enumType;

public enum Subjects {
    JAVA,
    MYSQL,
    JDBC,
    HTML,
    CSS,
    JAVASCRIPT;

    @Override
    public String toString() {
        return "=====" + this.name() + "=====";
    }
}


