package il.george_nika.phrase2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static il.george_nika.phrase2.controller.ControllerConstants.*;

@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/password")
    public String password(){
        return "password";
    }

    @RequestMapping(value = "/password/guest")
    public String passwordPreview(HttpSession session){
        session.setAttribute(SESSION_CONNECTION_TYPE, CONNECTION_TYPE_PREVIEW);
        return "redirect:/admin/prog";
    }

    @RequestMapping(value = "/password/logout")
    public String passwordLogout(HttpSession session){

        session.setAttribute(SESSION_CONNECTION_TYPE, "");
        return "redirect:/";
    }

    @RequestMapping(value = "/admin")
    public String admin(){
        return "redirect:/admin/prog";
    }

    @RequestMapping(value = "/admin/prog")
    public String adminProg(HttpSession session){
        if (ControllerUtil.isAdminLogin(session, false)){
            return "admin";
        }
        return "redirect:/password";
    }

    @RequestMapping(value = "/admin/prog/**")
    public String admin2(){
        return "redirect:/admin/prog";
    }

}
