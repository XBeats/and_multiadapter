package com.aitangba.multiadapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aitangba.multiadapter.ItemType;


/**
 * Created by fhf11991 on 2016/6/28.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract ItemType getItemType();

}
