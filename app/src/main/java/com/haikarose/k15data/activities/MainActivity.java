package com.haikarose.k15data.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.haikarose.k15data.R;

public class MainActivity extends AppCompatActivity {

    private CardView upload;
    private CardView preview;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        upload=(CardView)findViewById(R.id.upload);
        preview=(CardView)findViewById(R.id.preview);


        upload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent=new Intent(getBaseContext(),UploadingActivity.class);
                startActivity(intent);
            }
        });

        preview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent=new Intent(getBaseContext(),UploadedDataActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            intent=new Intent(getBaseContext(),AboutActivity.class);
            startActivity(intent);
        }else if(id==android.R.id.home){
            finish();
        }
        return true;
    }
}
