package com.jiyun.android.fragmenttest4;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements WordsFragment.OnWordSelectedListenr{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState !=null) {
                return;
            }

            WordsFragment firstFragment = new WordsFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();

        }
    }

    public void onWordSelected(int position) {
        DefinitionFragment defFrag =
                (DefinitionFragment)getSupportFragmentManager()
                .findFragmentById(R.id.definition_fragment);

        if (defFrag != null) {
            defFrag.updateDefinitionView(position);
        } else  {
            DefinitionFragment newFragment = new DefinitionFragment();
            Bundle args = new Bundle();
            args.putInt(DefinitionFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}
