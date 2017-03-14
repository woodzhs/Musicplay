package com.zhs.mymusicplayerdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CollectionFragment extends Fragment {
    @Override  
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){  
        super.onCreateView(inflater, container, savedInstanceState);  
        View chatView = inflater.inflate(R.layout.collection, container,false);
        return chatView;      
    }  
    @Override  
    public void onActivityCreated(Bundle savedInstanceState){  
        super.onActivityCreated(savedInstanceState);  
    }  
}  