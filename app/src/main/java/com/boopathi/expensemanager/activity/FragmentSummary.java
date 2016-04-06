package com.boopathi.expensemanager.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boopathi.expensemanager.R;
import com.boopathi.expensemanager.db.DBHelper;

/**
 * Created by Boopathi on 22-03-2016.
 */
public class FragmentSummary extends Fragment {


    private TextView amtText;
    private DBHelper dbHelper;
    private  int netAmt;

    public FragmentSummary() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("Summary");


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.content_summary,container,false);
        amtText = (TextView) layout.findViewById(R.id.netBalAmt);
        dbHelper = new DBHelper(this.getContext());
        netAmt= dbHelper.getSummaryAmount();
        amtText.setText(Integer.toString(netAmt));
        dbHelper.close();
        return layout;
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
        dbHelper = new DBHelper(this.getContext());
        netAmt= dbHelper.getSummaryAmount();
        amtText.setText(Integer.toString(netAmt));
        dbHelper.close();
    }
}
