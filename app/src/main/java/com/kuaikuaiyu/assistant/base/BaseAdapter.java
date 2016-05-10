package com.kuaikuaiyu.assistant.base;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by binlly
 * <p>
 * date: 2016/4/28 16:51
 * desc: Base Adapter
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    private List<T> mList;

    public BaseAdapter() {
    }

    public BaseAdapter(List<T> list) {
        mList = list;
    }

    public List<T> getData() {
        return mList;
    }

    public void setData(List<T> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getEachView(position, convertView, parent);
    }

    public abstract View getEachView(int position, View convertView, ViewGroup parent);
}
