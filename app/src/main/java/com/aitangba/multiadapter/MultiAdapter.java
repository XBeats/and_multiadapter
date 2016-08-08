package com.aitangba.multiadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.aitangba.multiadapter.viewholder.BaseViewHolder;
import com.aitangba.multiadapter.viewholder.CommonViewHolder;
import com.aitangba.multiadapter.viewholder.EmptyViewHolder;
import com.aitangba.multiadapter.viewholder.FooterViewHolder;
import com.aitangba.multiadapter.viewholder.HeaderViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fhf11991 on 2016/6/27.
 */
public abstract class MultiAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{

    private List<T> mList;
    private int mHeadViewSize;

    protected boolean mIsAutoLoadMore = false;
    protected boolean mIsUseEmptyView = true;
    protected OnLoadMoreListener mOnLoadMoreListener;

    protected int mEmptyViewStatus = EmptyViewHolder.STATUS_NO_DATA;
    protected int mFooterViewStatus = FooterViewHolder.STATUS_LOADING_MORE;

    public MultiAdapter() {
        mList = new ArrayList<>(0);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

    public void setEmptyViewStatus(int emptyViewStatus) {
        setEmptyViewStatus(emptyViewStatus, false);
    }

    public void setEmptyViewStatus(int emptyViewStatus, boolean refresh) {
        mEmptyViewStatus = emptyViewStatus;
        if(!refresh)return;
        if(mList != null) {
            mList.clear();
        }
        setFooterViewStatus(FooterViewHolder.STATUS_LOADING_MORE);
        notifyDataSetChanged();
    }

    public void setFooterViewStatus(int footerViewStatus) {
        setFooterViewStatus(footerViewStatus, false);
    }

    public void setFooterViewStatus(int footerViewStatus, boolean refresh) {
        mFooterViewStatus = footerViewStatus;
        if(!refresh)return;
        notifyDataSetChanged();
    }

    public void setAutoLoadMore(boolean autoLoadMore) {
        mIsAutoLoadMore = autoLoadMore;
    }

    public void setUseEmptyView(boolean useEmptyView) {
        mIsUseEmptyView = useEmptyView;
    }

    public int getHeadViewSize() {
        return mHeadViewSize;
    }

    public void setHeadViewSize(int headViewSize) {
        mHeadViewSize = headViewSize;
    }

    public void setData(List<T> list) {
        if(mList == null) {
            mList = new ArrayList<>();
        } else {
            mList.clear();
        }
        mList.addAll(list);

        if(mList.size() == 0 && mIsUseEmptyView) {
            setEmptyViewStatus(EmptyViewHolder.STATUS_NO_DATA);
        } else if(mList.size() == 0 && !mIsUseEmptyView) {
            setFooterViewStatus(FooterViewHolder.STATUS_NO_MORE);
        } else {
            setFooterViewStatus(FooterViewHolder.STATUS_NONE);
        }
        notifyDataSetChanged();
    }

    public void addToFoot(List<T> list) {
        if(mList == null) {
            mList = new ArrayList<>();
        }
        mList.addAll(list);
        setFooterViewStatus(FooterViewHolder.STATUS_NONE);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        final int headViewSize = mHeadViewSize;
        final int listSize = mList == null ? 0 : mList.size();
        final int footerViewSize = 1;
        final int emptyViewSize = 1;
        final boolean hasEmptyView = mIsUseEmptyView;
        final boolean hasFooterView = mIsAutoLoadMore;

        int count;
        if(listSize <= 0 && hasFooterView && hasEmptyView) {
            count = headViewSize + listSize + emptyViewSize;
        } else if(listSize <= 0 && hasFooterView && !hasEmptyView) {
            count = headViewSize + listSize + footerViewSize;
        } else if(listSize > 0 && hasFooterView) {
            count = headViewSize + listSize + footerViewSize;
        } else if(listSize > 0 && !hasFooterView) {
            count = headViewSize + listSize;
        } else {
            count = headViewSize + listSize + footerViewSize;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        ItemType itemType;

        final boolean hasEmptyView = mIsUseEmptyView;
        final boolean hasFooterView = mIsAutoLoadMore;
        final boolean isLastPosition = position == getItemCount() - 1;
        final boolean isListEmpty = mList == null ? true : mList.size() == 0;

        if(position < mHeadViewSize) {
            itemType = ItemType.HeaderView;
        } else if(position == mHeadViewSize && !isListEmpty) {
            itemType = ItemType.CommonView;
        } else if(position == mHeadViewSize && isListEmpty && hasEmptyView) {
            itemType = ItemType.EmptyView;
        } else if(position == mHeadViewSize && isListEmpty && !hasEmptyView) {
            itemType = hasFooterView ? ItemType.FooterView : ItemType.CommonView;
        } else if(position == mHeadViewSize && !hasEmptyView && isLastPosition) {
            itemType = hasFooterView ? ItemType.FooterView : ItemType.CommonView;
        } else if(position == mHeadViewSize && !hasEmptyView && !isLastPosition) {
            itemType = ItemType.CommonView;
        } else if(position > mHeadViewSize && isLastPosition) {
            itemType = hasFooterView ? ItemType.FooterView : ItemType.CommonView;
        } else if(position > mHeadViewSize && !isLastPosition) {
            itemType = ItemType.CommonView;
        } else {
            itemType = ItemType.CommonView;
        }

        int itemViewType = itemType.itemType | getViewType(itemType, position);
        return itemViewType;
    }

    public int getViewType(ItemType itemType, int position) {
        return itemType.itemType;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final int itemViewType = viewType & 0xffff0000;
        final int realViewType = viewType & 0x0000ffff;
        final ItemType itemType = ItemType.getItemType(itemViewType);

        return onCreateViewHolder(parent, itemType, realViewType);
    }

    protected BaseViewHolder onCreateViewHolder(ViewGroup parent, ItemType itemType, int viewType) {
        BaseViewHolder baseViewHolder = null;
        switch (itemType){
            case HeaderView:
                //HeaderViewHolder 可以具有多样性
                baseViewHolder = new HeaderViewHolder(new View(parent.getContext()));
                break;
            case FooterView:
                // FooterViewHolder 具有唯一性
                baseViewHolder = new FooterViewHolder(new View(parent.getContext()), mOnLoadMoreListener);
                break;
            case EmptyView:
                // EmptyViewHolder 具有唯一性
                baseViewHolder = new EmptyViewHolder(parent, new View(parent.getContext()));
                break;
            case CommonView:
                //CommonViewHolder 具有多样性
                baseViewHolder = onCreateCommonViewHolder(parent, viewType);
            default:
                break;
        }
        return baseViewHolder;
    }

    protected abstract CommonViewHolder onCreateCommonViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        final ItemType itemType = holder.getItemType();
        switch (itemType) {
            case HeaderView:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.onBindViewHolder(position);
                break;
            case FooterView:
                FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
                footerViewHolder.onBindViewHolder(mFooterViewStatus, mHeadViewSize);
                break;
            case EmptyView:
                EmptyViewHolder emptyViewHolder = (EmptyViewHolder) holder;
                emptyViewHolder.dealEmptyStatus(mEmptyViewStatus);
                break;
            case CommonView:
                CommonViewHolder<T> commonViewHolder = (CommonViewHolder<T>) holder;
                commonViewHolder.onBindViewHolder(mList.get(position - mHeadViewSize));
                break;
            default:break;
        }
    }

    public interface OnLoadMoreListener {
        void onLoad();
    }
}