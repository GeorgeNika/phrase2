package il.george_nika.phrase2.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static il.george_nika.phrase2.controller.ControllerConstants.CONNECTION_TYPE_ADMIN;
import static il.george_nika.phrase2.controller.ControllerConstants.CONNECTION_TYPE_PREVIEW;
import static il.george_nika.phrase2.controller.ControllerConstants.SESSION_CONNECTION_TYPE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestMainController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void pagesTest() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk());
        this.mockMvc.perform(get("/notExistPage")).andExpect(status().is4xxClientError());
        this.mockMvc.perform(get("/password")).andExpect(status().isOk());
        this.mockMvc.perform(get("/password/guest"))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/admin/prog"));
        this.mockMvc.perform(get("/password/logout"))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));

        this.mockMvc.perform(get("/admin"))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/admin/prog"));

        HashMap<String, Object> sessionattr = new HashMap<>();
        sessionattr.put(SESSION_CONNECTION_TYPE, "");
        this.mockMvc.perform(get("/admin/prog").sessionAttrs(sessionattr))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/password"));

        this.mockMvc.perform(get("/admin/prog/**"))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/admin/prog"));
    }

    @Test
    public void adminProgTest() throws Exception{
        HashMap<String, Object> sessionattr = new HashMap<>();
        sessionattr.put(SESSION_CONNECTION_TYPE, "");

        this.mockMvc.perform(get("/admin/prog").sessionAttrs(sessionattr))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/password"));

        sessionattr.put(SESSION_CONNECTION_TYPE,CONNECTION_TYPE_PREVIEW);
        this.mockMvc.perform(get("/admin/prog").sessionAttrs(sessionattr)).andExpect(status().isOk());

        sessionattr.put(SESSION_CONNECTION_TYPE,CONNECTION_TYPE_ADMIN);
        this.mockMvc.perform(get("/admin/prog").sessionAttrs(sessionattr)).andExpect(status().isOk());
    }

}
