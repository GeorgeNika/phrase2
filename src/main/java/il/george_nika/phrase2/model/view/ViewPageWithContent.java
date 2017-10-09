package il.george_nika.phrase2.model.view;

import lombok.Data;

import java.util.List;

@Data
public class ViewPageWithContent {

    int currentPage;
    int itemsOnPage;
    int totalPages;
    String jsonContent;

}
