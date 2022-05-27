package org.zkl.zklappsdk.activity;

import android.content.Context;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.databinding.ActivityPopupwindowBinding;
import org.zkl.zklappsdk.databinding.ViewPopupwindowBinding;
import org.zkl.zklappsdk.view.MyPopopWindow;

/**
 * PopupWindow位置显示问题解决
 * Created by zkl on 2018/3/27.
 */

public class PopupwindowActivity extends BaseActivity {

    private ActivityPopupwindowBinding bind;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bindView(R.layout.activity_popupwindow);
        mContext = this;
        initEvent();
    }

    private void initEvent() {
        Button button = findViewById(R.id.ib_right_1);
        button.setVisibility(View.VISIBLE);
        button.setText("显示");
        findViewById(R.id.ib_right_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(view);
            }
        });
    }

    private void showPopupWindow(View view) {
        ViewPopupwindowBinding bindContent = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.view_popupwindow, null, false);
        final MyPopopWindow popupWindow = new MyPopopWindow(bindContent.getRoot(), ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAsDropDown(view);
        bindContent.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }
}
