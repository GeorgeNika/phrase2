package il.george_nika.phrase2.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final static String  adminHashPassword = "$2a$10$eV.B2TBN2m3WMcTFmcswZOo1JsO/ZlQuGSZOf38IjuwAxU6b7d4P6";

    public boolean isAdmin(String userName, String userPassword){

        if (BCrypt.checkpw(userPassword, adminHashPassword)){
            return true;
        }

        return false;
    }
}
