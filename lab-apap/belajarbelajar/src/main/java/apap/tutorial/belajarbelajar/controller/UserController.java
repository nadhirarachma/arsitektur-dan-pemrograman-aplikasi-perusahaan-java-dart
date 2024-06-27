package apap.tutorial.belajarbelajar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tutorial.belajarbelajar.model.UserModel;
import apap.tutorial.belajarbelajar.model.RoleModel;
import apap.tutorial.belajarbelajar.service.RoleService;
import apap.tutorial.belajarbelajar.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/add")
    private String addUserFormPage(Model model) {
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        return "form-add-user";
    }

    @PostMapping(value = "/add") 
    private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
        user.setIsSso(false);

        UserModel newUser = userService.addUser(user);

        if (newUser.equals(user)) {
            model.addAttribute("user", user);
            return "redirect:/";
        }
        else {
            List<RoleModel> listRole = roleService.findAll();
            model.addAttribute("listRole", listRole);
            model.addAttribute("validasi", "Password harus mengandung angka, huruf besar, huruf kecil, dan simbol, serta minimal memiliki 8 karakter. Mohon input kembali.");
            return "form-add-user";
        }
    }
    
    @GetMapping("/viewall")
    public String listUser(Model model) {
        List<UserModel> listUser = userService.getListUser();
        model.addAttribute("listUser", listUser);
        
        return "viewall-user";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username, Model model) {
        UserModel user = userService.getUserByUsername(username);

        UserModel deletedUser = userService.deleteUser(user);
        model.addAttribute("user", deletedUser.getUsername());

        return "delete-user";
    }

    @GetMapping("/updatepassword/{username}")
    public String updatePasswordFormPage(@PathVariable String username, Model model) {
        UserModel user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        
        return "form-update-password";
    }

    @PostMapping("/updatepassword/{username}")
    public String updatePasswordSubmitPage(@PathVariable String username, Model model, 
        @RequestParam(value = "oldPassword") String oldPassword,
        @RequestParam(value = "newPassword") String newPassword,
        @RequestParam(value = "confirmedNewPassword") String confirmedNewPassword) {

        UserModel user = userService.getUserByUsername(username);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())){
            model.addAttribute("validasi", "Password lama salah, mohon input kembali.");
            return "form-update-password";
        }
        else if (!newPassword.equals(confirmedNewPassword)){
            model.addAttribute("validasi", "Password baru dan konfirmasi password baru tidak sama, mohon input kembali.");
            return "form-update-password";
        }
        else if (passwordEncoder.matches(oldPassword, user.getPassword())){
            String updateUserPasswordResult = userService.updateUserPassword(username, newPassword);
            if (updateUserPasswordResult.equals("tidak sesuai ketentuan")) {
                model.addAttribute("validasi", "Password harus mengandung angka, huruf besar, huruf kecil, dan simbol, serta minimal memiliki 8 karakter. Mohon input kembali.");
                return "form-update-password";
            }
            else {
                model.addAttribute("username", username);
                return "update-password";
            }
        }
        else {
            return "error/404";
        }
    }
}
