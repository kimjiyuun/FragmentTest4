package com.jiyun.android.fragmenttest4;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by 608 on 2017-05-11.
 */

public class WordsFragment extends ListFragment {
    OnWordSelectedListenr mCallback;

    public interface OnWordSelectedListenr {
        public void onWordSelected(int position);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
                ? android.R.layout.simple_list_item_activated_1
                : android.R.layout.simple_expandable_list_item_1;

        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Data.words));

    }

    @Override
    public void onStart() {
        super.onStart();

        if (getFragmentManager().findFragmentById(R.id.definition_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnWordSelectedListenr) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
            "must implement OnWordSelectedListener");
        }
    }
    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        mCallback.onWordSelected(position);
        getListView().setItemChecked(position, true);
    }
}
