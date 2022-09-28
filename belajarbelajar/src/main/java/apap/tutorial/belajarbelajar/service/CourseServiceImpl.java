package apap.tutorial.belajarbelajar.service;

import apap.tutorial.belajarbelajar.model.CourseModel;
import apap.tutorial.belajarbelajar.repository.CourseDb;
import apap.tutorial.belajarbelajar.repository.PengajarDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseDb courseDb;

    @Autowired
    PengajarDb pengajarDb;

    @Override
    public void addCourse(CourseModel course) {
        courseDb.save(course);
    }

    @Override
    public List<CourseModel> getListCourse() {
        return courseDb.findByNameCourseUsingQuery();
    }

    @Override
    public CourseModel getCourseByCodeCourse(String code) {
        Optional<CourseModel> course = courseDb.findByCode(code);
        if (course.isPresent()) {
            return course.get();
        }
        else return null;
    }

    @Override 
    public CourseModel getCourseByCodeCourseQuery(String code) {
        Optional<CourseModel> course = courseDb.findByCodeUsingQuery(code);
        if (course.isPresent()) {
            return course.get();
        }
        else return null;
    }

    @Override
    public CourseModel updateCourse(CourseModel course) {
        courseDb.save(course);
        return course;
    }

    @Override
    public CourseModel deleteCourse(CourseModel course) {
        courseDb.delete(course);
        return course;
    }
}
