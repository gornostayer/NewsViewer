package com.example.NewsViewer;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.NewsViewer.data.VkResponse;
import com.example.NewsViewer.network.VkService;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String GROUP_NAME = "GROUP_NAME";
    private String groupName = "";
    private RecyclerView rvNews;
    private NewNewsAdapter adapter;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvNews = findViewById(R.id.rv_activity_main_news);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient cliet = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.vk.com/method/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(cliet)
                .build();
        adapter = new NewNewsAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNews.setLayoutManager(linearLayoutManager);
        rvNews.setAdapter(adapter);
        loadGroupName();
        getNews(groupName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_custom_group:
                openCustomGroupDialog();
                return true;
            case R.id.menu_custom_group_2:
                Toast.makeText(this, "menu item 2", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveGrpoupName(String newGroupName){
        getPreferences(MODE_PRIVATE).edit().putString(GROUP_NAME, newGroupName).apply();
    }

    private void loadGroupName(){
        groupName = getPreferences(MODE_PRIVATE).getString(GROUP_NAME, "pikabu");
    }

    private void openCustomGroupDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_custom_group, null, false);
        final EditText etGrpoup = view.findViewById(R.id.dialog_custom_group_et_group);
        new AlertDialog.Builder(this)
                .setView(view)
                .setTitle("Добавьте название группы")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        groupName = etGrpoup.getText().toString();
                        getNews(groupName);
                        saveGrpoupName(groupName);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("DK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    private void getNews(String newGropName){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("domain", newGropName);
        map.put("count", "10");
        map.put("filter", "owner");
        map.put("extended", "0");
        map.put("v", "5.100");
        map.put("access_token", "293265a0293265a0293265a0d8295909bc22932293265a0742b992ec3a28b7cfcada41c");
        retrofit.create(VkService.class).getNews(map).enqueue(new Callback<VkResponse>() {
            @Override
            public void onResponse(Call<VkResponse> call, Response<VkResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    adapter.addNews(response.body().getResponse().getNews());
                } else {
                    showError("Response error");
                }
            }

            @Override
            public void onFailure(Call<VkResponse> call, Throwable t) {
                showError(t.getLocalizedMessage());
                Log.d("onFailure", "onFailure: "+t.getLocalizedMessage());
            }
        });
    }

    private void showError(String message){
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }
}

