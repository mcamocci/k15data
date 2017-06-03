package com.haikarose.k15data.HaikaroseViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by root on 6/3/17.
 */

public class HaikaroseTextView extends android.support.v7.widget.AppCompatTextView {

    public HaikaroseTextView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"/fonts/Roboto-Regular.ttf"));
    }
}
