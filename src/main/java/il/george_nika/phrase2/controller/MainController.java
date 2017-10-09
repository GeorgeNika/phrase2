package il.george_nika.phrase2.controller;

import il.george_nika.phrase2.service.phrase_builder.VerbPhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static il.george_nika.phrase2.controller.ControllerConstants.*;

@Controller
public class MainController {

    private VerbPhraseService phraseService;

    @Autowired
    public MainController(VerbPhraseService phraseService) {
        this.phraseService = phraseService;
    }

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
        if (isAdminLogin(session, false)){
            return "admin";
        }
        return "redirect:/password";
    }

    @RequestMapping(value = "/admin/prog/**")
    public String admin2(){
        return "redirect:/admin/prog";
    }

    public static boolean isAdminLogin(HttpSession session, boolean strong){
        Object connectionType = session.getAttribute(SESSION_CONNECTION_TYPE);
        if (connectionType == null){
            return false;
        }
        if (connectionType.equals(CONNECTION_TYPE_ADMIN) || connectionType.equals(CONNECTION_TYPE_PREVIEW)) {
            if (strong && !connectionType.equals(CONNECTION_TYPE_ADMIN)){
                return false;
            }
            return true;
        }
        return false;
    }
}
