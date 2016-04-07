package com.boopathi.expensemanager.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boopathi.expensemanager.R;
import com.boopathi.expensemanager.adapter.IncomeTransAdapter;
import com.boopathi.expensemanager.db.DBHelper;
import com.boopathi.expensemanager.model.Trans;

import java.util.ArrayList;

/**
 * Created by Boopathi on 22-03-2016.
 */
public class FragmentIncome extends Fragment {


    private Toolbar toolbar;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Trans> data;
    public static View.OnClickListener myOnClickListener;
    private DBHelper dbHelper;

    public FragmentIncome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Income");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.content_income,container,false);


        myOnClickListener = new MyOnClickListener(this.getContext());

        recyclerView = (RecyclerView) layout.findViewById(R.id.transIncomeRecycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        dbHelper = new DBHelper(this.getContext());
        data = dbHelper.getModeTrans(1);

        adapter = new IncomeTransAdapter(data,getContext());
        recyclerView.setAdapter(adapter);
        dbHelper.close();

        return layout;
    }


    public static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
        }


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        changeData();
    }
    public void changeData(){
        dbHelper = new DBHelper(getContext());
        data = dbHelper.getModeTrans(1);
        adapter = new IncomeTransAdapter(data,getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        dbHelper.close();

    }
}
