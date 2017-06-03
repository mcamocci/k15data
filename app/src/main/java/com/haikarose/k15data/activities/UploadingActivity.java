package com.haikarose.k15data.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haikarose.k15data.CommonInformation.CommonInformation;
import com.haikarose.k15data.R;
import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.define.Define;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.UUID;

public class UploadingActivity extends AppCompatActivity {

    private ImageView imageSelector;
    private ArrayList<Uri> path=new ArrayList<>();
    private TextView uploadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploading);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.upload_title));
        imageSelector=(ImageView)findViewById(R.id.imageSelector);

        uploadButton=(TextView)findViewById(R.id.upload_data);
        uploadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                 //Emmanuel the start activity for result may serve someone.
                Location location = null;
                try {
                    LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                    location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                 upload();
            }
        });


        imageSelector.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                path.clear();
                FishBun.with(UploadingActivity.this)
                        .setMaxCount(5)
                        .setMinCount(1)
                        .setPickerSpanCount(6)
                        .setActionBarColor(Color.parseColor("#37474F"), Color.parseColor("#263238"), false)
                        .setActionBarTitleColor(Color.parseColor("#ffffff"))
                        .setArrayPaths(path)
                        .setAlbumSpanCount(2, 4)
                        .setButtonInAlbumActivity(false)
                        .setCamera(true)
                        .setReachLimitAutomaticClose(false)
                        .setHomeAsUpIndicatorDrawable(ContextCompat.getDrawable(v.getContext(),R.drawable.ic_close))
                        .setOkButtonDrawable(ContextCompat.getDrawable(v.getContext(), R.drawable.ic_action_tick))
                        .setAllViewTitle("All images")
                        .setActionBarTitle("Select images ")
                        .textOnImagesSelectionLimitReached("Limit Reached!")
                        .textOnNothingSelected("Nothing Selected")
                        .startAlbum();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            finish();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Define.ALBUM_REQUEST_CODE:
                if (resultCode == RESULT_OK) {

                    path = data.getParcelableArrayListExtra(Define.INTENT_PATH);
                    Toast.makeText(getBaseContext(),Integer.toString(path.size()),Toast.LENGTH_SHORT).show();
                    break;
                }
        }
    }

    public void upload(){
        String uploadId = UUID.randomUUID().toString();

        UploadNotificationConfig uploadNotificationConfig=new UploadNotificationConfig();
        uploadNotificationConfig.setTitle("uploading image data");
        uploadNotificationConfig.setCompletedIconColor(android.R.color.holo_red_dark);
        uploadNotificationConfig.setCompletedMessage("The upload process completed");
        //Creating a multi part request
        try {

            MultipartUploadRequest uploadRequest=new MultipartUploadRequest(this, uploadId, CommonInformation.UPLOAD_URL)
                    .addParameter("user_name","")
                    .setNotificationConfig(uploadNotificationConfig)
                    .setMaxRetries(2);

            for(Uri uri:path){
                uploadRequest.addFileToUpload(uri.getPath(), "data[]");
            }
            uploadRequest.startUpload();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
