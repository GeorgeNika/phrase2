package il.george_nika.phrase2.controller;

import il.george_nika.phrase2.service.NounService;
import il.george_nika.phrase2.service.PhraseService;
import il.george_nika.phrase2.service.VerbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static il.george_nika.phrase2.controller.ControllerConstants.*;

@Controller
public class MainController {

    private PhraseService phraseService;

    @Autowired
    public MainController(PhraseService phraseService, NounService nounService, VerbService verbService) {
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
    public String admin(HttpSession session, ModelMap model){
        return "redirect:/admin/prog";
    }

    @RequestMapping(value = "/admin/prog")
    public String adminProg(HttpSession session, ModelMap model){
        if (isAdminLogin(session, false)){
            return "admin";
        }
        return "redirect:/password";
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
