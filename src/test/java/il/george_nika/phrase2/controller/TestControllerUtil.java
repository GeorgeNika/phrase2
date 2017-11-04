package il.george_nika.phrase2.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import static il.george_nika.phrase2.controller.ControllerConstants.CONNECTION_TYPE_ADMIN;
import static il.george_nika.phrase2.controller.ControllerConstants.CONNECTION_TYPE_PREVIEW;
import static il.george_nika.phrase2.controller.ControllerConstants.SESSION_CONNECTION_TYPE;

public class TestControllerUtil {

    @Test
    public void isAdminTest(){
        MockHttpSession mockHttpSession = new MockHttpSession();

        Assert.assertFalse(ControllerUtil.isAdminLogin(mockHttpSession, false));
        Assert.assertFalse(ControllerUtil.isAdminLogin(mockHttpSession, true));

        mockHttpSession.putValue(SESSION_CONNECTION_TYPE,"");
        Assert.assertFalse(ControllerUtil.isAdminLogin(mockHttpSession, false));
        Assert.assertFalse(ControllerUtil.isAdminLogin(mockHttpSession, true));

        mockHttpSession.putValue(SESSION_CONNECTION_TYPE,CONNECTION_TYPE_PREVIEW);
        Assert.assertTrue(ControllerUtil.isAdminLogin(mockHttpSession, false));
        Assert.assertFalse(ControllerUtil.isAdminLogin(mockHttpSession, true));

        mockHttpSession.putValue(SESSION_CONNECTION_TYPE,CONNECTION_TYPE_ADMIN);
        Assert.assertTrue(ControllerUtil.isAdminLogin(mockHttpSession, false));
        Assert.assertTrue(ControllerUtil.isAdminLogin(mockHttpSession, true));
    }
}
