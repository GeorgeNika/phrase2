package il.george_nika.phrase2.controller;

import il.george_nika.phrase2.service.data.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static il.george_nika.phrase2.controller.ControllerConstants.*;

@Controller
public class MainController {

    private final WordService wordService;

    @Autowired
    public MainController(WordService wordService) {
        this.wordService = wordService;
    }

    @RequestMapping(value = "/")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/word/{type}/{wordId}")
    public String showWord(@PathVariable String type, @PathVariable Integer wordId, Model model){
        model.addAttribute("word_type", type);
        model.addAttribute("word_id", wordId);
        model.addAttribute("wordInfoList", wordService.getWordInfo(type, wordId));
        return "word";
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
    public String adminProgRedirect(){
        return "redirect:/admin/prog";
    }

}
