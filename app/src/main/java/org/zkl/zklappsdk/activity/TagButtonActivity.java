package org.zkl.zklappsdk.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.databinding.ActivityTagButtonBinding;
import org.zkl.zklappsdk.view.SegmentedGroup;

/**
 * 快捷的标签点击切换
 * Created by zkl on 2017/12/13.
 */

public class TagButtonActivity extends BaseActivity {

    private ActivityTagButtonBinding bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bindView(R.layout.activity_tag_button);
        initEvent();
    }

    private void initEvent() {
        bind.button11.setChecked(true);

    }

    private void addButton(SegmentedGroup group) {
        RadioButton radioButton = (RadioButton) getLayoutInflater().inflate(R.layout.radio_button_item, null);
        radioButton.setText("Button " + (group.getChildCount() + 1));
        group.addView(radioButton);
        group.updateBackground();
    }

    public void add(View view) {
        addButton(bind.mSegment);
    }

    public void remove(View view) {
        removeButton(bind.mSegment);
    }

    private void removeButton(SegmentedGroup group) {
        if (group.getChildCount() < 1) return;
        group.removeViewAt(group.getChildCount() - 1);
        group.updateBackground();

        //Update margin for last item
        if (group.getChildCount() < 1) return;
        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 0);
        group.getChildAt(group.getChildCount() - 1).setLayoutParams(layoutParams);
    }
}
