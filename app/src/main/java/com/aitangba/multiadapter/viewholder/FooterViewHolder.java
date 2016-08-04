package com.aitangba.multiadapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.aitangba.multiadapter.ItemType;
import com.aitangba.multiadapter.MultiAdapter;


/**
 * Created by fhf11991 on 2016/6/28.
 * 测试情况：
 *   1.第一次加载就无数据
 *   2.第一次加载数据不足20
 *   3.第一次加载数据20个，但第二次没有更多数据
 *   4.第一次加载数据20个，第二次加载数据不足20个
 *   5.第一次加载数据20个，第二次加载数据20个
 *   6.第一次加载数据不足20个，然后进行刷新操作
 *   7.第一次加载数据20个，然后进行刷新操作
 */
public class FooterViewHolder extends BaseViewHolder {

    public static final int STATUS_NONE = 1; //准备状态不显示
    public static final int STATUS_LOADING_MORE = 2; //正在加载中
    public static final int STATUS_NO_MORE = 3;  //没有更多数据

    private MultiAdapter.OnLoadMoreListener mOnLoadMoreListener;

    private int mCurrentStatus = STATUS_NONE;

    public FooterViewHolder(View itemView, MultiAdapter.OnLoadMoreListener onLoadMoreListener) {
        super(itemView);
        mOnLoadMoreListener = onLoadMoreListener;
    }

    public void setOnLoadMoreListener(MultiAdapter.OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.FooterView;
    }

    public void onBindViewHolder(int status, int headViewSize){
        final int position = getAdapterPosition();
        final int dataSize = position - headViewSize;
        final boolean hasMore = dataSize % 20 == 0 && mCurrentStatus != STATUS_NO_MORE;
        if(status == STATUS_NO_MORE) {
            status = STATUS_NO_MORE;
        } else if(dataSize == 0) {
            status = STATUS_NONE;
        } else if(hasMore) {
            if(status == STATUS_NONE)mCurrentStatus = STATUS_NONE;//adapter通知holder需要更新状态了
            status = STATUS_LOADING_MORE;
        } else {
            status = STATUS_NO_MORE;
        }

        if((mCurrentStatus == status))return;
        mCurrentStatus = status;
        dealStatus(mCurrentStatus);
    }

    protected void dealStatus(int status) {
        itemView.setVisibility(View.VISIBLE);
        if(status == STATUS_NONE) {
            itemView.setVisibility(View.GONE);
        } else if(status == STATUS_LOADING_MORE) {
            if(mOnLoadMoreListener != null)mOnLoadMoreListener.onLoad();
        } else if(status == STATUS_NO_MORE) {
            if(itemView instanceof TextView) {
                ((TextView)itemView).setText("没有更多数据了");
            }
        } else {}
    }
}
