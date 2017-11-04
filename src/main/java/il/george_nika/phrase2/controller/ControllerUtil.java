package il.george_nika.phrase2.controller;

import javax.servlet.http.HttpSession;

import static il.george_nika.phrase2.controller.ControllerConstants.CONNECTION_TYPE_ADMIN;
import static il.george_nika.phrase2.controller.ControllerConstants.CONNECTION_TYPE_PREVIEW;
import static il.george_nika.phrase2.controller.ControllerConstants.SESSION_CONNECTION_TYPE;

public class ControllerUtil {

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
