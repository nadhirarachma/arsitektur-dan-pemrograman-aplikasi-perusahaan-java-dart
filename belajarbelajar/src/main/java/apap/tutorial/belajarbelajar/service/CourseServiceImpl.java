package apap.tutorial.belajarbelajar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import apap.tutorial.belajarbelajar.model.CourseModel;

@Service
public class CourseServiceImpl implements CourseService {
    private List<CourseModel> listCourse;

    public CourseServiceImpl() {
        listCourse = new ArrayList<>();
    }

    @Override
    public void addCourse(CourseModel course) {
        listCourse.add(course);
    }

    @Override
    public List<CourseModel> getListCourse() {
        return listCourse;
    }

    @Override
    public CourseModel getCourseByCodeCourse(String code) {
        for (int i = 0; i < listCourse.size(); i++) {
            if (listCourse.get(i).getCode().equals(code)) {
                return listCourse.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteCourse(CourseModel course) {
        listCourse.remove(course);
    }
}
