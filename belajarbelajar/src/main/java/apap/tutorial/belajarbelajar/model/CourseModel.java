package apap.tutorial.belajarbelajar.model;

public class CourseModel {
    private String code;
    private String nameCourse;
    private String description;
    private int jumlahSks;

    public CourseModel(String code, String nameCourse, String description, int jumlahSks) {
        this.code = code;
        this.nameCourse = nameCourse;
        this.description = description;
        this.jumlahSks = jumlahSks;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public void setNameCourse (String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public void setJumlahSks (int jumlahSks) {
        this.jumlahSks = jumlahSks;
    }

    public String getCode() {
        return code;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public String getDescription() {
        return description;
    }

    public int getJumlahSks() {
        return jumlahSks;
    }
}
