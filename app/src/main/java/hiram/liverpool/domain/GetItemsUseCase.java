package hiram.liverpool.domain;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hiram.liverpool.SearchInterface;
import hiram.liverpool.model.Item;
import hiram.liverpool.model.MappersKt;
import hiram.liverpool.model.PlpResults;
import hiram.liverpool.model.Record;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetItemsUseCase implements SearchInterface.Model {
    private ResultInterface.Model<List<Item>> output;

    public GetItemsUseCase(ResultInterface.Model output){
        this.output = output;
    }

    @Override
    public ArrayList<PlpResults> getItems(String query) {
        EndPoints service = RetrofitClientInstance.getRetrofitInstance().create(EndPoints.class);
        Call<hiram.liverpool.model.Response> call  = service.getFindItems(query);
        call.enqueue(new Callback<hiram.liverpool.model.Response>() {
            @Override
            public void onResponse(Call<hiram.liverpool.model.Response> call, Response<hiram.liverpool.model.Response> response) {
                if(!response.isSuccessful()){
                    return;
                }
                Log.d("RESPONSE", "onResponse() called with: call = [" + call + "], response = [" + response.body().getPlpResults() + "]");
                PlpResults plpResults = response.body().plpResults;

                List<Item> itemsFound = new ArrayList<>();
                for ( Record record : plpResults.getRecords()) {
                    itemsFound.add(MappersKt.toItem(record));
                }
                if(itemsFound.size() > 0) {
                    output.onSucess(itemsFound);
                } else {
                    output.onFailure(null);
                }
            }

            @Override
            public void onFailure(Call<hiram.liverpool.model.Response> call, Throwable t) {
                output.onFailure(null);
                Log.d("FAILURE", "onFailure() called with: call = [" + call + "], t = [" + t + "]");

            }
        } );


/*        ArrayList<Item> items = generateDuplicates();
output.onSucess(items);*/

        return null;
    }

    private ArrayList<Item> generateDuplicates() {
        ArrayList<Item> branchoffices = new ArrayList<>();
        branchoffices.add(new Item("XBOX", "$35,000", "IMAGE"));
        branchoffices.add(new Item("XBOX", "$35,000", "IMAGE"));
        branchoffices.add(new Item("XBOX", "$35,000", "IMAGE"));
        branchoffices.add(new Item("XBOX", "$35,000", "IMAGE"));
        branchoffices.add(new Item("XBOX", "$35,000", "IMAGE"));
        return branchoffices;
    }

}
