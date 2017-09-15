package com.ashwani.recyclerviewdeleteundoanimated;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ashwani.recyclerviewdeleteundoanimated.adapter.MyAdapter;
import com.ashwani.recyclerviewdeleteundoanimated.bean.CustomBean;
import com.ashwani.recyclerviewdeleteundoanimated.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    List<CustomBean> customBeanList = new ArrayList<>();
    List<Integer> deletedPositions = new ArrayList<>();
    MyAdapter myAdapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        myAdapter = new MyAdapter(this, customBeanList, deletedPositions, this);
        recyclerView.setAdapter(myAdapter);

        populateData();
    }

    private void populateData() {
        CustomBean customBean = new CustomBean();
        customBeanList.add(customBean);

        customBean = new CustomBean();
        customBeanList.add(customBean);

        customBean = new CustomBean();
        customBeanList.add(customBean);

        customBean = new CustomBean();
        customBeanList.add(customBean);

        customBean = new CustomBean();
        customBeanList.add(customBean);

        customBean = new CustomBean();
        customBeanList.add(customBean);

        customBean = new CustomBean();
        customBeanList.add(customBean);

        customBean = new CustomBean();
        customBeanList.add(customBean);

        customBean = new CustomBean();
        customBeanList.add(customBean);

        customBean = new CustomBean();
        customBeanList.add(customBean);

        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position, boolean isDeleted) {
        if (!isDeleted)
            deletedPositions.add(position);
        else {
           if(deletedPositions.contains((Integer)position))
               deletedPositions.remove((Integer)position);
        }

        myAdapter.notifyItemChanged(position);
    }
}
