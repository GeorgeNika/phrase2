package il.george_nika.phrase2.controller;

import il.george_nika.phrase2.service.AdminService;
import il.george_nika.phrase2.service.PhraseService;
import il.george_nika.phrase2.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class ApiV2Controller {

    private final PhraseService phraseService;
    private final TokenService tokenService;
    private final AdminService adminService;

    @Autowired
    public ApiV2Controller(PhraseService phraseService, TokenService tokenService, AdminService adminService) {
        this.phraseService = phraseService;
        this.tokenService = tokenService;
        this.adminService = adminService;
    }

    @RequestMapping(value = "/getToken")
    @ResponseBody
    public String getToken(@RequestParam (defaultValue = "") String name,
                           @RequestParam (defaultValue = "") String password){

        return tokenService.createJWT(name, adminService.isAdmin(name, password));
    }
}
