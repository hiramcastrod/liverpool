package hiram.liverpool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.view.View;

import java.util.ArrayList;

import hiram.liverpool.databinding.ActivityMainBinding;
import hiram.liverpool.model.Item;
import hiram.liverpool.presenter.SearchPresenter;

public class MainActivity extends AppCompatActivity implements SearchInterface.View {
    ActivityMainBinding binding;
    public SearchPresenter presenter;
    private SearchRecentSuggestions suggestions;
    String query = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new SearchPresenter(this);
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.onSearch(query);
                query = binding.searchView.getQuery().toString();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    SuggestionProvider.AUTHORITY, SuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);
            
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    SuggestionProvider.AUTHORITY, SuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);


        }
    }

    @Override
    public void showLoader() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.recyclerViewProducts.setVisibility(View.GONE);
        binding.textViewEmpty.setVisibility(View.GONE);
    }

    @Override
    public void setView(ArrayList<Item> items) {
        binding.textViewEmpty.setVisibility(View.GONE);
        binding.recyclerViewProducts.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        binding.recyclerViewProducts.setLayoutManager(layoutManager);
        SearchAdapter adapter = new SearchAdapter();
        adapter.submitItems(items);
        binding.recyclerViewProducts.setAdapter(adapter);
        binding.recyclerViewProducts.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setNotFound() {
        binding.progressBar.setVisibility(View.GONE);
        binding.textViewEmpty.setVisibility(View.VISIBLE);
    }
}
