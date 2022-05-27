package org.zkl.zklappsdk.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.databinding.ActivityDatabindingBinding;
import org.zkl.zklappsdk.model.DataBinding;

/**
 * databinding 的双向数据绑定
 * Created by zkl on 2017/12/1.
 */

public class DataBindingActivity extends BaseActivity {
    DataBinding binding = new DataBinding();
    private ActivityDatabindingBinding bind;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bindView(R.layout.activity_databinding);
        initEvent();
    }

    @SuppressLint("NewApi")
    private void initEvent() {
        mToolbar = findViewById(R.id.mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.setUsername("我是张三");
        bind.setUser(binding);
        bind.setPresenter(new Presenter());
    }

    public class Presenter {
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            binding.setUsername(s.toString());
            bind.setUser(binding);
        }

        public void onClick(View view) {
            switch (view.getId()){
                case R.id.mTxtBindingText:
                    Toast.makeText(DataBindingActivity.this, "点到我啦", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        public void onClickBinding(DataBinding binding){
            Toast.makeText(DataBindingActivity.this, "我把里面的数据拿过来啦："+binding.getUsername(), Toast.LENGTH_SHORT).show();
        }
    }
}
