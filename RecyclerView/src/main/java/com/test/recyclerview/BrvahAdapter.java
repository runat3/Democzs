package com.test.recyclerview;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/9/28.
 */

public class BrvahAdapter extends BaseQuickAdapter<Person,BaseViewHolder>
{
    public BrvahAdapter(@LayoutRes int layoutResId, @Nullable List<Person> data)
    {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Person item)
    {
        helper.setText(R.id.tv_tel,item.getTel());
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_age,item.getAge()+"");
    }
}
