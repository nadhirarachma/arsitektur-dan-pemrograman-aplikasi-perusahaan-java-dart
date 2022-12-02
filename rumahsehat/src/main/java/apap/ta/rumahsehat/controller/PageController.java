package apap.ta.rumahsehat.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import apap.ta.rumahsehat.model.UserModel;
import apap.ta.rumahsehat.security.xml.Attributes;
import apap.ta.rumahsehat.security.xml.ServiceResponse;
import apap.ta.rumahsehat.service.UserService;
import apap.ta.rumahsehat.setting.Setting;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    ServerProperties serverProperties;

    private WebClient webClient = WebClient.builder().build();

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
       return "login";
    }

    @GetMapping("/validate-ticket")
    public ModelAndView adminLoginSSO(
        @RequestParam(value = "ticket", required = false) String ticket,
        HttpServletRequest request,
        RedirectAttributes redirectAttrs)
    {
        ServiceResponse serviceResponse = this.webClient.get().uri(
                String.format(
                        Setting.SERVER_VALIDATE_TICKET,
                        ticket,
                        Setting.CLIENT_LOGIN
                )
        ).retrieve().bodyToMono(ServiceResponse.class).block();

        Attributes attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
        String username = serviceResponse.getAuthenticationSuccess().getUser();

        UserModel user = userService.getUserByUsername(username);

        List<String> whitelist = new ArrayList<>();
        Collections.addAll(whitelist, "al.ghifari01", "azhar.rahmatilah", "alviona.retno", "helga.syahda", "nadhira.rachma");

        if (whitelist.contains(username)) {
            if (user == null) {
                user = new UserModel();
                user.setEmail(username + "@ui.ac.id");
                user.setNama(attributes.getNama());
                user.setPassword("rumahsehat");
                user.setUsername(username);
                user.setIsSso(true);
                user.setRole("Admin");
                userService.addUser(user);
            }
        }
        else {
            redirectAttrs.addFlashAttribute("error", "Mohon maaf, Anda tidak memiliki akses sebagai Admin.");
            return new ModelAndView("redirect:/login");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "rumahsehat");
        
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
        
        return new ModelAndView("redirect:/");
    }

    @GetMapping(value = "/login-sso")
    public ModelAndView loginSSO() {
        return new ModelAndView("redirect:" + Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
    }

    @GetMapping(value = "/logout-sso")
    public ModelAndView logoutSSO(Principal principal) {
        UserModel user = userService.getUserByUsername(principal.getName());
        if (user.getIsSso() == false) {
            return new ModelAndView("redirect:/logout");
        }
        return new ModelAndView("redirect:" + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
    }
}

