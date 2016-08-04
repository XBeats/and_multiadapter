package com.aitangba.multiadapter.viewholder;

import android.view.View;

import com.aitangba.multiadapter.ItemType;


/**
 * Created by fhf11991 on 2016/6/28.
 */
public class HeaderViewHolder extends BaseViewHolder {

    public HeaderViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public ItemType getItemType() {
        return ItemType.HeaderView;
    }

    public void onBindViewHolder(int position){

    }
}
