package il.george_nika.phrase2.controller;

import il.george_nika.phrase2.model.view.VerbForView;
import il.george_nika.phrase2.model.view.VerbInfo;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.PhraseService;
import il.george_nika.phrase2.service.data.NounService;
import il.george_nika.phrase2.service.phrase_builder.VerbPhraseService;
import il.george_nika.phrase2.service.data.VerbService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static il.george_nika.phrase2.controller.ControllerConstants.CONNECTION_TYPE_ADMIN;
import static il.george_nika.phrase2.controller.ControllerConstants.SESSION_CONNECTION_TYPE;

@Controller
public class AjaxController {

    private PhraseService phraseService;
    private NounService nounService;
    private VerbService verbService;

    @Autowired
    public AjaxController(PhraseService phraseService, NounService nounService, VerbService verbService) {
        this.phraseService = phraseService;
        this.nounService = nounService;
        this.verbService = verbService;
    }

    @RequestMapping(value = "/ajax/phrase")
    @ResponseBody
    public ViewPhrase getAjaxPhrase(@RequestParam String phraseType){
        ViewPhrase result = null;
        while (result == null) {
            try{
                result = phraseService.getPhrase(phraseType);
            } catch (RuntimeException ex){
                result = null;
            }
        }
        return result;
    }

    @RequestMapping(value = "/ajax/password")
    @ResponseBody
    public Integer checkPassword (@RequestParam String password, HttpSession session){

        String  hashPassword = "$2a$10$eV.B2TBN2m3WMcTFmcswZOo1JsO/ZlQuGSZOf38IjuwAxU6b7d4P6";

        if (BCrypt.checkpw(password, hashPassword)){
            session.setAttribute(SESSION_CONNECTION_TYPE, CONNECTION_TYPE_ADMIN);
            return 1;
        }else{
            return 0;
        }
    }

    @RequestMapping(value = "/ajax/verbs")
    @ResponseBody
    public List<VerbInfo> getVerbsInfo (HttpSession session){

        return verbService.getAllVerbInfo();
    }

    @RequestMapping(value = "/ajax/verb/{id}", method = RequestMethod.GET)
    @ResponseBody
    public VerbForView getVerbForView (@PathVariable Integer id, HttpSession session){

        return new VerbForView(verbService.getVerbById(id));
    }

    @RequestMapping(value = "/ajax/verb", method = {RequestMethod.POST, RequestMethod.PUT} )
    @ResponseBody
    public Integer  updateVerb (@RequestBody VerbForView verbForView, HttpSession session){

        if (!MainController.isAdminLogin(session, true)){
            return 0;
        }
        verbService.saveVerbByVerbForView(verbForView);
        return 1;
    }

    @RequestMapping(value = "/ajax/changeActionVerb", method = {RequestMethod.POST, RequestMethod.PUT} )
    @ResponseBody
    public Boolean  changeActionVerb (@RequestBody Integer id, HttpSession session){

        if (!MainController.isAdminLogin(session, true)){
            throw  new RuntimeException("Access denied");
        }
        return verbService.changeActionVerb(id);
    }
}
