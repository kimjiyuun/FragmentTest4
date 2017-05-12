package com.jiyun.android.fragmenttest4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 608 on 2017-05-11.
 */

public class DefinitionFragment extends Fragment {
final static String ARG_POSITION = "position" ;
    int mCurrentPosition = -1;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle ssavedInstanceState) {

        if (ssavedInstanceState != null){
            mCurrentPosition = ssavedInstanceState.getInt(ARG_POSITION);
        }

        return  inflater.inflate(R.layout.definition_view, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args =getArguments();
        if (args != null) {
            updateDefinitionView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1){
            updateDefinitionView(mCurrentPosition);
        }
    }

    public void updateDefinitionView(int position){
        TextView def = (TextView) getActivity().findViewById(R.id.definition);
        def.setText(Data.definitins[position]);
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}
