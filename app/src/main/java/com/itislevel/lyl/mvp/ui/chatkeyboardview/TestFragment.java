package com.itislevel.lyl.mvp.ui.chatkeyboardview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itislevel.lyl.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends BaseFragment {


    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_test, container, false);
        TextView tv= (TextView) rootView.findViewById(R.id.tv_test);
        tv.setText(args.getString("Interge"));
        return rootView;
    }

}
