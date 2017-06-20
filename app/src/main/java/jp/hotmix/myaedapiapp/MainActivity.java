package jp.hotmix.myaedapiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<AedModel> mainListData;
    MainListAdapter adapter;
    AedApi service;
    Spinner spinner;

    static final String[] cities = {"徳島市", "阿南市","阿波市","鳴門市"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        spinnerAdapter.addAll(cities);
        spinner.setAdapter(spinnerAdapter);

        mainListData = new ArrayList<>();

        final ListView mainList = (ListView)findViewById(R.id.mainList);
        adapter = new MainListAdapter(this, mainListData);
        mainList.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://aed.azure-mobile.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(AedApi.class);

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onClickAddList(View view){
        String selectedCity = (String)spinner.getSelectedItem();
        Toast.makeText(this, "データを取得します", Toast.LENGTH_SHORT).show();
        Call<List<AedModel>> aedListCall = service.apiGetAedList("徳島県", selectedCity);
        aedListCall.enqueue(new Callback<List<AedModel>>() {
            @Override
            public void onResponse(Call<List<AedModel>> call, Response<List<AedModel>> response) {
               Log.d("MyAedApiApp", "onResponse: " + response.body().get(0).getLocationName());
                mainListData.clear();
                mainListData.addAll(response.body());
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "通信完了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<AedModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "通信に失敗しました", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
