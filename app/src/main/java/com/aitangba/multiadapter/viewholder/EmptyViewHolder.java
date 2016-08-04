package com.aitangba.multiadapter.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aitangba.multiadapter.ItemType;


/**
 * Created by fhf11991 on 2016/6/28.
 */
public class EmptyViewHolder extends BaseViewHolder {

    public static final int STATUS_NO_INTERNET = 1;
    public static final int STATUS_NO_DATA = 2;
    public static final int STATUS_HTTP_ERROR = 3;

    public EmptyViewHolder(ViewGroup parentView, int emptyViewId) {
        super(getEmptyView(parentView, emptyViewId));
    }

    public EmptyViewHolder(ViewGroup parentView, View emptyView) {
        super(getEmptyView(parentView, emptyView));
    }

    @Override
    public ItemType getItemType() {
        return ItemType.EmptyView;
    }

    public void dealEmptyStatus(int status){

    }

    protected static View getEmptyView(ViewGroup viewGroup, int emptyViewId) {
        View emptyView;
        if(emptyViewId > 0) {
            emptyView = LayoutInflater.from(viewGroup.getContext()).inflate(emptyViewId, viewGroup, false);
        } else {
            emptyView = new View(viewGroup.getContext());
        }
        return getEmptyView(viewGroup, emptyView);
    }

    protected static View getEmptyView(ViewGroup viewGroup, View emptyView) {
        if(emptyView == null) {
            emptyView = new View(viewGroup.getContext());
        }
        int topMargin = 0;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childView = viewGroup.getChildAt(i);
            topMargin = topMargin + childView.getMeasuredHeight();
        }
        int height = viewGroup.getMeasuredHeight() - topMargin;
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        return emptyView;
    }
}
