package com.kuaikuaiyu.assistant.ui.account.bindbank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.utils.CityUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by kky on 15-7-21.
 */
public class ChooseCityActivity extends BaseActivity {

    @Bind(R.id.ib_back)
    ImageButton ib_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.lv_province_list)
    ListView lv_provincelist;
    @Bind(R.id.lv_city_list)
    ListView lv_citylist;

    private List<String> provinceNameList = new ArrayList<>();
    //    private List<String> provinceIdList=new ArrayList<>();
    private List<String> cityNameList = new ArrayList<>();
    private int selectIndex = 0;
    private CityAdapter mCityAdapter;
    private ProvinceAdapter mProvinceAdapter;


    @Override
    protected void initComponent() {

    }

    @Override
    protected int getRootView() {
        return R.layout.activity_choose_city;
    }

    @Override
    protected void setListener() {
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv_provincelist.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectProvince(position);
            }
        });

        lv_citylist.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = getIntent();
                intent.putExtra("province", provinceNameList.get(selectIndex));
                intent.putExtra("city", cityNameList.get(position));
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText("选择省市");
        provinceNameList = CityUtil.getProvinceNameList();
//        provinceIdList = CityUtils.getProvinceIDArray();
        mProvinceAdapter = new ProvinceAdapter();
        lv_provincelist.setAdapter(mProvinceAdapter);
        mCityAdapter = new CityAdapter();
        lv_citylist.setAdapter(mCityAdapter);
        selectProvince(0);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }


    private void selectProvince(int index) {
        selectIndex = index;
        cityNameList = CityUtil.getCityNameList(provinceNameList.get(index));
        mProvinceAdapter.notifyDataSetChanged();
        mCityAdapter.notifyDataSetChanged();
    }


    class ProvinceAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return provinceNameList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            TextView holderTextView;
            if (convertView != null) {
                view = convertView;
                holderTextView = (TextView) view.getTag();
            } else {
                view = UIUtil.inflate(R.layout.list_item_province_list);
                holderTextView = (TextView) view.findViewById(R.id.tv_item_province_chooseshopcity);
                view.setTag(holderTextView);
            }
            if (position == selectIndex) {
                holderTextView.setBackgroundResource(R.mipmap.list_city_sel);
            } else {
                holderTextView.setBackgroundResource(R.drawable.list_city_selector);
            }
            holderTextView.setText(provinceNameList.get(position));
            return view;
        }
    }


    class CityAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return cityNameList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            TextView holderTextView;
            if (convertView != null) {
                view = convertView;
                holderTextView = (TextView) view.getTag();
            } else {
                view = UIUtil.inflate(R.layout.list_item_city_list);
                holderTextView = (TextView) view.findViewById(R.id.tv_item_city_chooseshopcity);
                view.setTag(holderTextView);
            }
            holderTextView.setText(cityNameList.get(position));
            return view;
        }
    }
}
