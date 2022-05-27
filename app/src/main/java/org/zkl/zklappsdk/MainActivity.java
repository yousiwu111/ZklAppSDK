package org.zkl.zklappsdk;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.zkl.zklappsdk.activity.BaseActivity;
import org.zkl.zklappsdk.activity.DemoListActivity;
import org.zkl.zklappsdk.databinding.ActivityMainBinding;

/**
 * 首页
 */
public class MainActivity extends BaseActivity {

    private ActivityMainBinding bind;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        bind = bindView(R.layout.activity_main);
        mContext = this;
        initEvent();
    }

    private void initEvent() {
        bind.mTxtDemoButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.mTxtDemoButton:
                startActivity(new Intent(mContext, DemoListActivity.class));
                break;
        }
    }
}
