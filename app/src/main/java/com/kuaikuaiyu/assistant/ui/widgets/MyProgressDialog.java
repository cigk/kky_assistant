package com.kuaikuaiyu.assistant.ui.widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.utils.UIUtil;

public class MyProgressDialog extends Dialog {
    private TextView info;
    private Context context;

    public MyProgressDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.dialog_progress, null);
        this.info = (TextView) view.findViewById(R.id.progress_info);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(UIUtil.dp2px(100),
                UIUtil.dp2px(100));
        this.setContentView(view, params);
    }

//    @Override
//    public void dismiss() {
//        if(this.isShowing() && !((Activity)context).isFinishing())
//            super.dismiss();
//    }

    public void setTextInfo(String info) {
        this.info.setText(info);
    }
}

