package il.george_nika.phrase2.controller;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.adjective.Adjective;
import il.george_nika.phrase2.model.adverb.Adverb;
import il.george_nika.phrase2.model.noun.Noun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.*;
import il.george_nika.phrase2.model.view.adjective.AdjectiveForDetailView;
import il.george_nika.phrase2.model.view.adjective.AdjectiveForListView;
import il.george_nika.phrase2.model.view.adverb.AdverbForDetailView;
import il.george_nika.phrase2.model.view.adverb.AdverbForListView;
import il.george_nika.phrase2.model.view.noun.NounForDetailView;
import il.george_nika.phrase2.model.view.noun.NounForListView;
import il.george_nika.phrase2.model.view.verb.VerbForDetailView;
import il.george_nika.phrase2.model.view.verb.VerbForListView;
import il.george_nika.phrase2.service.PhraseService;
import il.george_nika.phrase2.service.data.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static il.george_nika.phrase2.controller.ControllerConstants.CONNECTION_TYPE_ADMIN;
import static il.george_nika.phrase2.controller.ControllerConstants.SESSION_CONNECTION_TYPE;

@RestController
public class AjaxController {

    private final PhraseService phraseService;
    private final NounService nounService;
    private final VerbService verbService;
    private final AdjectiveService adjectiveService;
    private final AdverbService adverbService;
    private final WordService wordService;

    @Autowired
    public AjaxController(PhraseService phraseService, NounService nounService,
                          VerbService verbService, AdjectiveService adjectiveService,
                          AdverbService adverbService, WordService wordService) {
        this.phraseService = phraseService;
        this.nounService = nounService;
        this.verbService = verbService;
        this.adjectiveService = adjectiveService;
        this.adverbService = adverbService;
        this.wordService = wordService;
    }

    @RequestMapping(value = "/ajax/phrase")
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
    public ViewPageWithContent getVerbsViewPageWithContent (
            @RequestParam int page, @RequestParam int itemsOnPage, @RequestParam String filter){

        if (page<1) {
            page = 1;
        }
        Page<Verb> verbPage = verbService.getVerbsPage(page-1, itemsOnPage, filter);

        List<VerbForListView> verbInfoList = new ArrayList<>();
        List<Integer> allActiveVerbsId = verbService.getAllActionVerbIds();
        for (Verb loopVerb : verbPage.getContent()){
            VerbForListView tempVerbInfo = new VerbForListView(loopVerb);
            if (allActiveVerbsId.contains(loopVerb.getId())) {
                tempVerbInfo.setActionVerb(true);
            }
            verbInfoList.add(tempVerbInfo);
        }

        return new ViewPageWithContent(page, verbPage, verbInfoList);
    }

    @RequestMapping(value = "/ajax/verb/{id}", method = RequestMethod.GET)
    public VerbForDetailView getVerbForDetailView (@PathVariable Integer id){

        return new VerbForDetailView(verbService.getVerbById(id));
    }

    @RequestMapping(value = "/ajax/verb", method = {RequestMethod.POST, RequestMethod.PUT} )
    public Integer  updateVerb (@RequestBody VerbForDetailView verbForDetailView, HttpSession session){

        if (!ControllerUtil.isAdminLogin(session, true)){
            throw new RuntimeException("Access denied");
        }
        return verbService.saveVerbByVerbForDetailView(verbForDetailView);
    }

    @RequestMapping(value = "/ajax/changeActionVerb", method = {RequestMethod.POST, RequestMethod.PUT} )
    public Boolean  changeActionVerb (@RequestBody Integer id, HttpSession session){

        if (!ControllerUtil.isAdminLogin(session, true)){
            throw new RuntimeException("Access denied");
        }
        return verbService.changeActionVerb(id);
    }

    @RequestMapping(value = "/ajax/nouns")
    public ViewPageWithContent getNounsViewPageWithContent (
            @RequestParam int page, @RequestParam int itemsOnPage, @RequestParam String filter){

        if (page<1) {
            page = 1;
        }
        Page<Noun> nounPage = nounService.getNounsPage(page-1, itemsOnPage, filter);

        List<NounForListView> nounInfoList = nounPage.getContent().stream()
                .map(NounForListView::new).collect(Collectors.toList());

        return new ViewPageWithContent(page, nounPage, nounInfoList);
    }

    @RequestMapping(value = "/ajax/noun/{id}", method = RequestMethod.GET)
    public NounForDetailView getNounForDetailView (@PathVariable Integer id){

        return new NounForDetailView(nounService.getNounById(id));
    }

    @RequestMapping(value = "/ajax/noun", method = {RequestMethod.POST, RequestMethod.PUT} )
    public Integer  updateNoun (@RequestBody NounForDetailView nounForDetailView, HttpSession session){

        if (!ControllerUtil.isAdminLogin(session, true)){
            throw new RuntimeException("Access denied");
        }
        return nounService.saveNounByNounForDetailView(nounForDetailView);
    }

    @RequestMapping(value = "/ajax/adjectives")
    public ViewPageWithContent getAdjectivesViewPageWithContent (
            @RequestParam int page, @RequestParam int itemsOnPage, @RequestParam String filter){

        if (page<1) {
            page = 1;
        }
        Page<Adjective> adjectivePage = adjectiveService.getAdjectivesPage(page-1, itemsOnPage, filter);

        List<AdjectiveForListView> adjectiveInfoList = adjectivePage.getContent().stream()
                .map(AdjectiveForListView::new).collect(Collectors.toList());

        return new ViewPageWithContent(page, adjectivePage, adjectiveInfoList);
    }

    @RequestMapping(value = "/ajax/adjective/{id}", method = RequestMethod.GET)
    public AdjectiveForDetailView getAdjectiveForDetailView (@PathVariable Integer id){

        return new AdjectiveForDetailView(adjectiveService.getAdjectiveById(id));
    }

    @RequestMapping(value = "/ajax/adjective", method = {RequestMethod.POST, RequestMethod.PUT} )
    public Integer  updateAdjective (@RequestBody AdjectiveForDetailView adjectiveForDetailView, HttpSession session){

        if (!ControllerUtil.isAdminLogin(session, true)){
            throw new RuntimeException("Access denied");
        }
        return adjectiveService.saveAdjectiveByAdjectiveForDetailView(adjectiveForDetailView);
    }

    @RequestMapping(value = "/ajax/adverbs")
    public ViewPageWithContent getAdverbsViewPageWithContent (
            @RequestParam int page, @RequestParam int itemsOnPage, @RequestParam String filter){

        if (page<1) {
            page = 1;
        }
        Page<Adverb> adverbPage = adverbService.getAdverbsPage(page-1, itemsOnPage, filter);

        List<AdverbForListView> adverbInfoList = adverbPage.getContent().stream()
                .map(AdverbForListView::new).collect(Collectors.toList());

        return new ViewPageWithContent(page, adverbPage, adverbInfoList);
    }

    @RequestMapping(value = "/ajax/adverb/{id}", method = RequestMethod.GET)
    public AdverbForDetailView getAdverbForDetailView (@PathVariable Integer id){

        return new AdverbForDetailView(adverbService.getAdverbById(id));
    }

    @RequestMapping(value = "/ajax/adverb", method = {RequestMethod.POST, RequestMethod.PUT} )
    public Integer  updateAdverb (@RequestBody AdverbForDetailView adverbForDetailView, HttpSession session){

        if (!ControllerUtil.isAdminLogin(session, true)){
            throw new RuntimeException("Access denied");
        }
        return adverbService.saveAdverbByAdverbForDetailView(adverbForDetailView);
    }

    @RequestMapping(value = "/ajax/word/{wordType}/{wordId}", method = RequestMethod.GET)
    public List<LanguageUnit> getWordInfoView (@PathVariable String wordType, @PathVariable Integer wordId){

        return wordService.getWordInfo(wordType, wordId);
    }


}
