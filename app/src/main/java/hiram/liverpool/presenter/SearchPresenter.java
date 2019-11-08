package hiram.liverpool.presenter;

import java.util.ArrayList;

import hiram.liverpool.SearchInterface;
import hiram.liverpool.domain.GetItemsUseCase;
import hiram.liverpool.domain.ResultInterface;
import hiram.liverpool.model.Item;
import hiram.liverpool.model.PlpResults;

public class SearchPresenter implements SearchInterface.Presenter{
    private SearchInterface.View view;
    private SearchInterface.Model model;

    public SearchPresenter(SearchInterface.View view){
        this.view = view;
    }

    @Override
    public void onSearch(String query) {
        view.showLoader();

        model = new GetItemsUseCase(new ResultInterface.Model<ArrayList<Item>>(){

            @Override
            public void onSucess(ArrayList<Item> var) {
                view.setView(var);
            }

            @Override
            public void onFailure(ArrayList<Item> var) {
                view.setNotFound();
            }
        });

        model.getItems(query);
    }

    @Override
    public void onDestroy() {

    }
}
