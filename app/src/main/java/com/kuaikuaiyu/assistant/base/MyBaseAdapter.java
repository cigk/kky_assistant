package com.kuaikuaiyu.assistant.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.sys.ThreadManager;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import java.util.List;

public abstract class MyBaseAdapter<T> extends BaseAdapter {

    public List<T> mDatas;
    private BaseHolder holder;

    public MyBaseAdapter(List<T> mDatas) {
        setData(mDatas);
    }

    public void setData(List<T> mDatas) {
        this.mDatas = mDatas;

    }

    public List<T> getData() {
        return mDatas;
    }

    @Override
    public int getCount() {
        //        if (hasMore() && mDatas.size() >= getListLimit() && mDatas.size() %
        // getListLimit() == 0)
        if (hasMore())
            return mDatas.size() + 1;
        else
            return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public final int MORE_VIEW_TYPE = 1;
    public final int ITEM_VIEW_TYPE = 0;
    private MoreHolder moreHolder;

    @Override
    public int getItemViewType(int position) {
        //        if (hasMore() && mDatas.size() >= getListLimit() && mDatas.size() %
        // getListLimit() == 0) {
        if (hasMore()) {
            if (position == getCount() - 1) {
                return MORE_VIEW_TYPE;
            } else {
                return getItemViewTypeInner(position);
            }
        } else {
            return getItemViewTypeInner(position);
        }

    }

    public int getItemViewTypeInner(int position) {
        return ITEM_VIEW_TYPE;
    }

    @Override
    public int getViewTypeCount() {
        //        if (hasMore() && mDatas.size() >= getListLimit() && mDatas.size() %
        // getListLimit() == 0)
        if (hasMore())
            return super.getViewTypeCount() + 1;
        else
            return super.getViewTypeCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            holder = (BaseHolder) convertView.getTag();
        } else {
            if (MORE_VIEW_TYPE == getItemViewType(position)) {
                holder = getMoreHolder();
            } else {
                holder = getHolder(position);
            }
        }
        if (ITEM_VIEW_TYPE == getItemViewType(position)) {
            holder.setData(mDatas.get(position));
        }
        return holder.getRootView();
    }

    //表示更多的数据
    private BaseHolder getMoreHolder() {
        if (moreHolder == null) {
            moreHolder = new MoreHolder(this, hasMore());
        }
        return moreHolder;
    }

    public boolean hasMore() {
        return true;
    }

    public abstract BaseHolder getHolder(int position);

    private boolean is_load = false;

    /**
     * 加载更多的数据
     */
    public void loadMore() {
        if (!is_load) {
            is_load = true;
            ThreadManager.getLongPool().execute(new Runnable() {

                @Override
                public void run() {
                    final List list = onLoadMore();
                    UIUtil.runInMainThread(new Runnable() {

                        @Override
                        public void run() {
                            if (list == null) {
                                getMoreHolder().setData(MoreHolder.ERROR);
                            } else if (list.size() < getListLimit()) {
                                getMoreHolder().setData(MoreHolder.NO_MORE);
                            } else {
                                getMoreHolder().setData(MoreHolder.HAS_MORE);
                            }

                            if (null != list) {
                                if (null != mDatas) {
                                    mDatas.addAll(list);
                                } else {
                                    setData(list);
                                }
                                notifyDataSetChanged();
                            }
                            is_load = false;
                        }
                    });
                }
            });
        }
    }

    protected abstract List onLoadMore();

    /**
     * 下拉刷新时数据更新完毕后通知Adapter更新UI
     */
    public void refresh() {
        this.notifyDataSetChanged();
        setHasMore();
    }

    //设置可以加载更多
    public void setHasMore() {
        if (mDatas.size() >= getListLimit() && mDatas.size() % getListLimit() == 0)
            getMoreHolder().setData(MoreHolder.HAS_MORE);
    }


    /**
     * 获取ListView一页显示的条目
     *
     * @return 条目数
     */
    protected int getListLimit() {
        return AppConfig.LIST_LIMIT;
    }
}

