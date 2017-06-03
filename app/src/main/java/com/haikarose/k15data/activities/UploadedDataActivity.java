package com.haikarose.k15data.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.haikarose.k15data.R;
import com.haikarose.k15data.adapters.UploadedDataAdapter;
import com.haikarose.k15data.pojos.UploadedItemPojo;

import java.util.ArrayList;
import java.util.List;

public class UploadedDataActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private UploadedDataAdapter adapter;
    private List<UploadedItemPojo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploaded_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.savedData_title));

        recyclerView=(RecyclerView)findViewById(R.id.resources_recycler_view);
        layoutManager=new LinearLayoutManager(getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        list=getList();
        adapter=new UploadedDataAdapter(getBaseContext(),list);
        recyclerView.setAdapter(adapter);



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==android.R.id.home){
            finish();
        }
        return true;
    }

    public List<UploadedItemPojo> getList(){

        List<UploadedItemPojo> itemPojos=new ArrayList<>();
        List<String> resource=new ArrayList<>();
        resource.add("one");
        resource.add("four");
        resource.add("anotherresource");

        for(int i=0;i<15;i++){
            UploadedItemPojo item=new UploadedItemPojo();
            item.setDate("june 2 , 2017");
            item.setLatitude("123478.987");
            item.setLongitude(("3498289.8797"));
            item.setDescription("This is a fake data for testing!!");
            item.setResources(resource);
            itemPojos.add(item);
        }
        return itemPojos;
    }
}
