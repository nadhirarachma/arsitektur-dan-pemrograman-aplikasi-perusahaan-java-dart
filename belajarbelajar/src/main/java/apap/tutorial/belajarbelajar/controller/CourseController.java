package apap.tutorial.belajarbelajar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tutorial.belajarbelajar.model.CourseModel;
import apap.tutorial.belajarbelajar.service.CourseService;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    //Routing URL yang diinginkan
    @RequestMapping("course/add")
    public String addCourse(
        @RequestParam(value = "code", required = true) String code,
        @RequestParam(value = "nameCourse", required = true) String nameCourse,
        @RequestParam(value = "description", required = true) String description,
        @RequestParam(value = "jumlahSks", required = true) String jumlahSks,
        Model model) {

        //Membuat Objek CourseModel
        CourseModel course = new CourseModel(code, nameCourse, description, Integer.parseInt(jumlahSks));
        
        //Memeriksa apakah course sudah pernah didaftarkan
        boolean duplicate = false;
        List<CourseModel> listCourse = courseService.getListCourse();
        for (int i = 0; i < listCourse.size(); i++) {
            if (listCourse.get(i).getCode().equals(code)) {
                duplicate = true;
            }
        }

        //Add variabel code course ke 'code' untuk dirender di thymeleaf
        model.addAttribute("code", code);

        //Add variabel name course ke 'nameCourse' untuk dirender di thymeleaf
        model.addAttribute("nameCourse", nameCourse);

        if (duplicate == true) {
            return "registered-course";
        }
        else {
            //Memanggil Service addCourse
            courseService.addCourse(course);
        }
        return "add-course";
    }

    @RequestMapping("course/viewAll")
    public String listCourse(Model model) {
        //Mendapatkan semua CourseModel
        List<CourseModel> listCourse = courseService.getListCourse();

        //Add variabel semua courseModel ke "ListCourse" untuk dirender pada thymeleaf
        model.addAttribute("listCourse", listCourse);
        return "viewall-course";
    } 

    @RequestMapping("course/view")
    public String detailCourse(@RequestParam(value = "code") String code, Model model) {
        //Mendapatkan CourseModel sesuai dengan code
        CourseModel course = courseService.getCourseByCodeCourse(code);

        //Add variabel semua courseModel ke "course" untuk dirender pada thymeleaf
        model.addAttribute("course", course);

        //Memeriksa apakah code diberikan atau ditemukan
        if (code == null || course == null) {
            return "error";
        }
        return "view-course";
    }

    @RequestMapping("/course/view/code-course/{code}")
    public String viewCourse(@PathVariable String code, Model model) {
        //Mendapatkan CourseModel sesuai dengan code
        CourseModel course = courseService.getCourseByCodeCourse(code);

        //Add variabel semua courseModel ke "course" untuk dirender pada thymeleaf
        model.addAttribute("course", course);

        //Memeriksa apakah code diberikan atau ditemukan
        if (code == null || course == null) {
            return "error";
        }
        return "view-course";
    }

    @RequestMapping("course/update/code-course/{code}/jumlah-sks/{jumlahSks}")
    public String updateSKSCourse(@PathVariable String code, @PathVariable int jumlahSks, Model model) {
        //Mendapatkan CourseModel sesuai dengan code
        CourseModel course = courseService.getCourseByCodeCourse(code);

        //Memeriksa apakah code diberikan atau ditemukan
        if (code == null || course == null) {
            return "error";
        }

        //Mengubah jumlah SKS
        course.setJumlahSks(jumlahSks);

        //Add variabel semua courseModel ke "course" untuk dirender pada thymeleaf
        model.addAttribute("course", course);
        return "update-sks-course";
    }

    @RequestMapping("course/delete/code-course/{code}")
    public String deleteCourse(@PathVariable String code, Model model) {
        //Mendapatkan CourseModel sesuai dengan code
        CourseModel course = courseService.getCourseByCodeCourse(code);

        //Memeriksa apakah code diberikan atau ditemukan
        if (code == null || course == null) {
            return "error";
        }

        //Menghapus course
        courseService.deleteCourse(course);

        //Add variabel semua courseModel ke "course" untuk dirender pada thymeleaf
        model.addAttribute("course", course);
        return "delete-course";
    }
}
