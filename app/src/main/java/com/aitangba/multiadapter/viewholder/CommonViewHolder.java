package com.aitangba.multiadapter.viewholder;

import android.view.View;

import com.aitangba.multiadapter.ItemType;


/**
 * Created by fhf11991 on 2016/6/28.
 */
public abstract class CommonViewHolder<Data> extends BaseViewHolder {

    public CommonViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBindViewHolder(Data date);

    @Override
    public ItemType getItemType() {
        return ItemType.CommonView;
    }

}
