package com.aitangba.multiadapter.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.aitangba.multiadapter.R;

/**
 * Created by fhf11991 on 2016/6/27.
 */
public class EmptyView extends LinearLayout {
    private static final String TAG = "EmptyView";

    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_empty, this);
    }
}
