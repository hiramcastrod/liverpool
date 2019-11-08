package hiram.liverpool;

import java.util.ArrayList;

import hiram.liverpool.model.Item;
import hiram.liverpool.model.PlpResults;
import hiram.liverpool.model.Record;

public interface SearchInterface {

    interface View{
        void showLoader();
        void setView(ArrayList<Item> items);
        void setNotFound();
    }

    interface Presenter{
        void onSearch(String query);

        void onDestroy();
    }

    interface Model{
        ArrayList<PlpResults> getItems(String query);
    }
}
