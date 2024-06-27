package apap.tutorial.belajarbelajar.service;
import apap.tutorial.belajarbelajar.model.CourseModel;
import java.util.List;
import java.time.LocalDateTime;

public interface CourseService {
    //Method untuk menambahkan course
    void addCourse(CourseModel course);

    //Method untuk mendapatkan daftar course yang telah tersimpan
    List<CourseModel> getListCourse();

    //Method untuk mendapatkan data course berdasarkan code course
    CourseModel getCourseByCodeCourse(String code);

    CourseModel getCourseByCodeCourseQuery(String code);

    CourseModel updateCourse(CourseModel course);

    //Method untuk menghapus course
    CourseModel deleteCourse(CourseModel course);

    boolean isClosed(LocalDateTime tanggalDimulai, LocalDateTime tanggalBerakhir);
}
