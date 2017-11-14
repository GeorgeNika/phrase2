package il.george_nika.phrase2.controller;

import com.google.gson.Gson;
import il.george_nika.phrase2.model.noun.Noun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.*;
import il.george_nika.phrase2.model.view.noun.NounForDetailView;
import il.george_nika.phrase2.model.view.noun.NounForListView;
import il.george_nika.phrase2.model.view.verb.VerbForDetailView;
import il.george_nika.phrase2.model.view.verb.VerbForListView;
import il.george_nika.phrase2.service.PhraseService;
import il.george_nika.phrase2.service.data.NounService;
import il.george_nika.phrase2.service.data.VerbService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public ViewPageWithContent getVerbsViewPageWithContent (
            @RequestParam int page, @RequestParam int itemsOnPage, @RequestParam String filter){

        if (page<1) {
            page = 1;
        }
        Page<Verb> verbPage = verbService.getVerbsOnPage(page-1, itemsOnPage, filter);

        List<VerbForListView> verbInfoList = new ArrayList<>();
        List<Integer> allActiveVerbsId = verbService.getAllActionVerbIds();
        for (Verb loopVerb : verbPage.getContent()){
            VerbForListView tempVerbInfo = new VerbForListView(loopVerb);
            if (allActiveVerbsId.contains(loopVerb.getId())) {
                tempVerbInfo.setActionVerb(true);
            }
            verbInfoList.add(tempVerbInfo);
        }

        ViewPageWithContent result = new ViewPageWithContent();
        result.setCurrentPage(page);
        result.setItemsOnPage(verbPage.getSize());
        result.setTotalPages(verbPage.getTotalPages());
        result.setJsonContent( new Gson().toJson(verbInfoList));
        return result;
    }

    @RequestMapping(value = "/ajax/verb/{id}", method = RequestMethod.GET)
    @ResponseBody
    public VerbForDetailView getVerbForDetailView (@PathVariable Integer id){

        return new VerbForDetailView(verbService.getVerbById(id));
    }

    @RequestMapping(value = "/ajax/verb", method = {RequestMethod.POST, RequestMethod.PUT} )
    @ResponseBody
    public Integer  updateVerb (@RequestBody VerbForDetailView verbForDetailView, HttpSession session){

        if (!ControllerUtil.isAdminLogin(session, true)){
            throw new RuntimeException("Access denied");
        }
        return verbService.saveVerbByVerbForView(verbForDetailView);
    }

    @RequestMapping(value = "/ajax/changeActionVerb", method = {RequestMethod.POST, RequestMethod.PUT} )
    @ResponseBody
    public Boolean  changeActionVerb (@RequestBody Integer id, HttpSession session){

        if (!ControllerUtil.isAdminLogin(session, true)){
            throw new RuntimeException("Access denied");
        }
        return verbService.changeActionVerb(id);
    }

    @RequestMapping(value = "/ajax/nouns")
    @ResponseBody
    public ViewPageWithContent getNounsViewPageWithContent (
            @RequestParam int page, @RequestParam int itemsOnPage, @RequestParam String filter){

        if (page<1) {
            page = 1;
        }
        Page<Noun> nounPage = nounService.getNounsOnPage(page-1, itemsOnPage, filter);

        List<NounForListView> nounInfoList = nounPage.getContent().stream()
                .map(noun -> new NounForListView(noun)).collect(Collectors.toList());

        ViewPageWithContent result = new ViewPageWithContent();
        result.setCurrentPage(page);
        result.setItemsOnPage(nounPage.getSize());
        result.setTotalPages(nounPage.getTotalPages());
        result.setJsonContent( new Gson().toJson(nounInfoList));
        return result;
    }

    @RequestMapping(value = "/ajax/noun/{id}", method = RequestMethod.GET)
    @ResponseBody
    public NounForDetailView getNounForDetailView (@PathVariable Integer id){

        return new NounForDetailView(nounService.getNounById(id));
    }

    @RequestMapping(value = "/ajax/noun", method = {RequestMethod.POST, RequestMethod.PUT} )
    @ResponseBody
    public Integer  updateNoun (@RequestBody NounForDetailView nounForDetailView, HttpSession session){

        if (!ControllerUtil.isAdminLogin(session, true)){
            throw new RuntimeException("Access denied");
        }
        return nounService.saveNounByNounForView(nounForDetailView);
    }
}
