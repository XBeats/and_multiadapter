package com.aitangba.multiadapter.ui;

import android.view.ViewGroup;
import android.widget.TextView;

import com.aitangba.multiadapter.ItemType;
import com.aitangba.multiadapter.MultiAdapter;
import com.aitangba.multiadapter.R;
import com.aitangba.multiadapter.viewholder.BaseViewHolder;
import com.aitangba.multiadapter.viewholder.EmptyViewHolder;
import com.aitangba.multiadapter.viewholder.FooterViewHolder;
import com.aitangba.multiadapter.viewholder.HeaderViewHolder;

/**
 * Created by fhf11991 on 2016/6/28.
 */
public abstract class CustomAdapter<T> extends MultiAdapter<T> {

    @Override
    protected BaseViewHolder onCreateViewHolder(ViewGroup parent, ItemType itemType, int viewType) {
        BaseViewHolder baseViewHolder;
        switch (itemType) {
            case HeaderView:
                TextView headTextView = new TextView(parent.getContext());
                headTextView.setText("我是头部");
                baseViewHolder = new HeaderViewHolder(headTextView);
                break;
            case FooterView:
                TextView footerTextView = new TextView(parent.getContext());
                footerTextView.setText("我是尾部");
                baseViewHolder = new FooterViewHolder(footerTextView, mOnLoadMoreListener);
                break;
            case EmptyView:
                baseViewHolder = new EmptyViewHolder(parent, R.layout.view_empty);
                break;
            default:
                baseViewHolder = super.onCreateViewHolder(parent, itemType, viewType);
                break;
        }
        return baseViewHolder;
    }

}
