package apap.tutorial.belajarbelajar.controller;

import apap.tutorial.belajarbelajar.model.CourseModel;
import apap.tutorial.belajarbelajar.model.PengajarModel;
import apap.tutorial.belajarbelajar.service.CourseService;
import apap.tutorial.belajarbelajar.service.PengajarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class PengajarController {
    @Qualifier("pengajarServiceImpl")
    @Autowired
    private PengajarService pengajarService;

    @Qualifier("courseServiceImpl")
    @Autowired
    private CourseService courseService;

    @GetMapping("/pengajar/add/{code}")
    public String addPengajarFormPage(@PathVariable String code, Model model) {
        PengajarModel pengajar = new PengajarModel();
        CourseModel course = courseService.getCourseByCodeCourse(code);
        pengajar.setCourse(course);
        model.addAttribute("pengajar", pengajar);

        return "form-add-pengajar";
    }

    @PostMapping("/pengajar/add")
    public String addPengajarSubmitPage(@ModelAttribute PengajarModel pengajar, Model model) {
        pengajarService.addPengajar(pengajar);
        model.addAttribute("noPengajar", pengajar.getNoPengajar());
        
        return "add-pengajar";
    }

    @GetMapping("/pengajar/update/{noPengajar}")
    public String updatePengajarFormPage(@PathVariable Long noPengajar, Model model) {
        PengajarModel pengajar = pengajarService.getPengajarByNoPengajarQuery(noPengajar);
        model.addAttribute("course", pengajar.getCourse());
        model.addAttribute("pengajar", pengajar);

        return "form-update-pengajar";
    }

    @PostMapping("/pengajar/update")
    public String updatePengajarSubmitPage(@ModelAttribute PengajarModel pengajar, Model model) {

        LocalDateTime currentTime = LocalDateTime.now();
        CourseModel course = pengajar.getCourse();
    
        if (currentTime.isAfter(course.getTanggalBerakhir())) {
            PengajarModel updatedPengajar = pengajarService.updatePengajar(pengajar);
            model.addAttribute("noPengajar", updatedPengajar.getNoPengajar());
            return "update-pengajar";
        }
        else {
            return "error-course-buka";
        }
    }

    // @GetMapping("/pengajar/delete/{noPengajar}")
    // public String deletePengajar(@PathVariable Long noPengajar, Model model) {
        
    //     LocalDateTime currentTime = LocalDateTime.now();
    //     PengajarModel pengajar = pengajarService.getPengajarByNoPengajarQuery(noPengajar);
    //     CourseModel course = pengajar.getCourse();
    
    //     if (currentTime.isAfter(course.getTanggalBerakhir())) {
    //         PengajarModel deletedPengajar = pengajarService.deletePengajar(pengajar);
    //         model.addAttribute("noPengajar", deletedPengajar.getNoPengajar());
    //         return "delete-pengajar";
    //     }
    //     else {
    //         return "error-course-buka";
    //     }
    // }

    @PostMapping("/pengajar/delete")
    public String deletePengajarSubmit(@ModelAttribute CourseModel course, Model model) {
        if (courseService.isClosed(course.getTanggalDimulai(), course.getTanggalBerakhir())) {
            for (PengajarModel pengajar : course.getListPengajar()) {
                pengajarService.deletePengajar(pengajar);
            }
            model.addAttribute("code", course.getCode());
            return "delete-pengajar";
        }
        return "error-course-buka";
    }
}
