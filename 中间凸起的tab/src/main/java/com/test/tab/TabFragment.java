package com.test.tab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends Fragment
{


    public TabFragment()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        Bundle bundle = getArguments();
        int type = bundle.getInt("type");
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        TextView tv_fg = (TextView) view.findViewById(R.id.tv_fg);
        tv_fg.setText("Hello TabFragment--"+type);

        return view;
    }

}
