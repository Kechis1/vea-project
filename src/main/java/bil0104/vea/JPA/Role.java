package bil0104.vea.JPA;

public enum Role {
    ADMIN, TEACHER, STUDENT;

    public boolean isAdmin() {
        return this.name().equals(ADMIN.toString());
    }
}
