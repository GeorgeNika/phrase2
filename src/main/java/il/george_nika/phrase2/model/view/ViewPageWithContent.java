package il.george_nika.phrase2.model.view;

import com.google.gson.Gson;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class ViewPageWithContent {

    int currentPage;
    int itemsOnPage;
    int totalPages;
    String jsonContent;

    public ViewPageWithContent() {
    }

    public ViewPageWithContent(int currentPage, Page page, List data) {
        this.currentPage = currentPage;
        this.itemsOnPage = page.getSize();
        this.totalPages = page.getTotalPages();
        this.jsonContent = new Gson().toJson(data);
    }
}
