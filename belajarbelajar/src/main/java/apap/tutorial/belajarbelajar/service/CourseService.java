package apap.tutorial.belajarbelajar.service;
import apap.tutorial.belajarbelajar.model.CourseModel;
import java.util.List;

public interface CourseService {
    //Method untuk menambahkan course
    void addCourse(CourseModel course);

    //Method untuk mendapatkan daftar course yang telah tersimpan
    List<CourseModel> getListCourse();

    //Method untuk mendapatkan data course berdasarkan code course
    CourseModel getCourseByCodeCourse(String code);

    //Method untuk menghapus course
    void deleteCourse(CourseModel course);
}
