MultiAdapter
====================================  
ListView是比较常用的控件之一，几乎所有应用都会用到；但V7包中RecyclerView出现后，其简单的api和多种炫酷的功能，又让人不得不重新考虑替换ListView这个元老级的控件。RecyclerView自带了ViewHolder这个类，但是对于多样式的实现，还是需要去优化的。
Features
--------
1. 将所有ItemView类型区分为HeaderView，CommonView,FooterView和常常被忽略的EmptyView
2. ItemType有HeaderViewHolder，CommonViewHolder,FooterViewHolder和EmptyViewHolder,并自动区分ItemType
3. 在多种类型中，需要设计每种的item子类型  
![image](https://raw.githubusercontent.com/XBeats/and_multiadapter/master/screenshot/ItemType.png)  
![image](https://raw.githubusercontent.com/XBeats/and_multiadapter/master/screenshot/type.png)  

Usage
--------

``` java

    private static class HeadText1ViewHolder extends HeaderViewHolder {
        public static final int TYPE = 1;
        private TextView mItemView;
        public HeadText1ViewHolder(View itemView) {
            super(itemView);
            mItemView = (TextView) itemView;
            mItemView.setText("HeadText1ViewHolder");
        }
    }

    private static class HeadText2ViewHolder extends HeaderViewHolder {
        public static final int TYPE = 2;
        private TextView mItemView;
        public HeadText2ViewHolder(View itemView) {
            super(itemView);
            mItemView = (TextView) itemView;
            mItemView.setText("HeadText2ViewHolder");
        }
    }

```