package com.zhs.mymusicplayerdemo;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {


    private Button collectionBtn;
    private Button searchBtn;
    private Button myMusciBtn;

    private ViewPager viewPager;

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    ArrayList<View> viewContainter = new ArrayList<View>();
    private CollectionFragment mCollectionFragm;
    private SearchFragment mSearchFragm;
    private MusicFragment mMusicFragm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collectionBtn = (Button)findViewById(R.id.collectionBtn);
        searchBtn = (Button)findViewById(R.id.searchBtn);
        myMusciBtn = (Button)findViewById(R.id.myMusicBtn);

        viewPager= (ViewPager)findViewById(R.id.viewpager);

        mCollectionFragm = new CollectionFragment();
        mSearchFragm = new SearchFragment();
        mMusicFragm = new MusicFragment();

        mFragmentList.add(mMusicFragm);
        mFragmentList.add(mSearchFragm);
        mFragmentList.add(mCollectionFragm);

        mFragmentAdapter =new FragmentAdapter(this.getSupportFragmentManager(),mFragmentList);

        viewPager.setAdapter(mFragmentAdapter);


       searchBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               viewPager.setCurrentItem(1);
           }
       });

        myMusciBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });

        collectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });



    }
}
