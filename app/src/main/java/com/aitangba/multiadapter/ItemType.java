package com.aitangba.multiadapter;

/**
 * Created by fhf11991 on 2016/6/28.
 */
public enum ItemType {
    HeaderView(1<<16), CommonView(2<<16), FooterView(3<<16), EmptyView(4<<16);
    public final int itemType;
    ItemType (int itemType){
        this.itemType = itemType;
    }

    public static ItemType getItemType(int viewType) {
        ItemType itemType;
        if(viewType == ItemType.HeaderView.itemType) {
            itemType = ItemType.HeaderView;
        } else if(viewType == ItemType.EmptyView.itemType) {
            itemType = ItemType.EmptyView;
        } else if(viewType == ItemType.FooterView.itemType) {
            itemType = ItemType.FooterView;
        } else if(viewType == ItemType.CommonView.itemType){
            itemType = ItemType.CommonView;
        } else {
            throw new RuntimeException("The viewType " + viewType + " can not be identified !!!");
        }
        return itemType;
    }
}
