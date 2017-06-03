package com.haikarose.k15data.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haikarose.k15data.R;
import com.haikarose.k15data.pojos.UploadedItemPojo;

import java.util.List;

/**
 * Created by root on 6/2/17.
 */

public class UploadedDataAdapter extends RecyclerView.Adapter<UploadedDataAdapter.UploadViewHolder> {

    private List<UploadedItemPojo> uploadedItemPojoList;
    private Context context;

    public UploadedDataAdapter(Context context,List<UploadedItemPojo> itemPojos){
        this.context=context;
        this.uploadedItemPojoList=itemPojos;
    }

    @Override
    public UploadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item_view,parent,false);
        UploadViewHolder viewHolder=new UploadViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UploadViewHolder holder, int position) {
        UploadedItemPojo uploadedItemPojo=uploadedItemPojoList.get(position);
        holder.setData(uploadedItemPojo);
    }

    @Override
    public int getItemCount() {
        return uploadedItemPojoList.size();
    }

    public class UploadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private UploadedItemPojo uploadedItemPojo;
        private TextView latitude;
        private TextView longitude;
        private TextView date;
        private TextView count;
        private ImageView menu;
        private TextView preview;


        public UploadViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            latitude=(TextView)view.findViewById(R.id.latitude);
            Typeface typeface= Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Regular.ttf");
            latitude.setTypeface(typeface);
            longitude=(TextView)view.findViewById(R.id.longitude);
            typeface= Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Thin.ttf");
            longitude.setTypeface(typeface);
            date=(TextView)view.findViewById(R.id.date_pub);
            preview=(TextView)view.findViewById(R.id.preview_option);
            count=(TextView)view.findViewById(R.id.counts);
            date=(TextView)view.findViewById(R.id.date_pub);
            menu=(ImageView) view.findViewById(R.id.menu);
            menu.setOnClickListener(this);
            preview.setOnClickListener(this);

        }
        public void setData(UploadedItemPojo item){
            this.uploadedItemPojo=item;
            this.longitude.setText(item.getLongitude());
            this.latitude.setText(item.getLatitude());
            this.date.setText(item.getDate());
            this.count.setText(Integer.toString(item.getResources().size()));
        }

        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.menu){

            }else if(v.getId()==R.id.preview){

            }
        }
    }
}
