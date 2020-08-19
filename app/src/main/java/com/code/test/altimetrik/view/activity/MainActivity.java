package com.code.test.altimetrik.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.code.test.altimetrik.R;
import com.code.test.altimetrik.controller.AsyncCompleteListener;
import com.code.test.altimetrik.controller.Task;
import com.code.test.altimetrik.model.AlbumData;
import com.code.test.altimetrik.model.Result;
import com.code.test.altimetrik.view.LoadingDialog;
import com.code.test.altimetrik.view.adapter.AlbumViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.album_list)
    RecyclerView albumList;

    private LoadingDialog loadingDialog;

    private Task apiTask;

    private List<Result> albumResult = new ArrayList<>();

    private AlbumViewAdapter albumViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loadingDialog = new LoadingDialog(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                searchView.setQuery("", false);
                searchView.setIconified(true);

                if(TextUtils.isEmpty(query)) return false;

                loadingDialog.show();
                apiTask = new Task(response -> {

                    loadingDialog.dismiss();

                    if(response == null) {
                        findViewById(R.id.no_txt).setVisibility(View.VISIBLE);
                        albumList.setVisibility(View.GONE);
                        return;
                    }

                    findViewById(R.id.no_txt).setVisibility(View.GONE);
                    albumList.setVisibility(View.VISIBLE);

                    AlbumData data = (AlbumData) response;

                    if(data.getResultCount() == 0)
                        return;

                    albumResult = data.getResults();

                    albumViewAdapter = new AlbumViewAdapter(albumResult);
                    albumList.setAdapter(albumViewAdapter);

                });
                apiTask.getAlbumData(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}