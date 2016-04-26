package com.boopathi.expensemanager.adapter;

/**
 * Created by Boopathi on 4/7/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boopathi.expensemanager.R;
import com.boopathi.expensemanager.activity.FragmentSpent;
import com.boopathi.expensemanager.db.DBHelper;
import com.boopathi.expensemanager.model.Trans;

import java.util.ArrayList;

public class SpentTransAdapter extends RecyclerView.Adapter<SpentTransAdapter.MyViewHolder> {

    private ArrayList<Trans> transData;
    private DBHelper dbHelper;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewSpentTo;
        TextView textViewSpentAmt;
        TextView textViewCat;
        TextView textViewDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewSpentTo = (TextView) itemView.findViewById(R.id.spentTo);
            this.textViewSpentAmt = (TextView) itemView.findViewById(R.id.spentAmt);
            this.textViewCat = (TextView) itemView.findViewById(R.id.spentCat);
            this.textViewDate = (TextView) itemView.findViewById(R.id.spentDate);
        }
    }

    public SpentTransAdapter(ArrayList<Trans> transData, Context context) {
        this.transData = transData;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trans_spent_card, parent, false);

        view.setOnClickListener(FragmentSpent.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        dbHelper = new DBHelper(context);
        TextView textViewSpentTo = holder.textViewSpentTo;
        TextView textViewSpentAmt = holder.textViewSpentAmt;
        TextView textViewCat = holder.textViewCat;
        TextView textViewDate = holder.textViewDate;

        textViewSpentTo.setText(transData.get(listPosition).getTo());
        textViewSpentAmt.setText(Integer.toString(transData.get(listPosition).getAmt()));
        textViewCat.setText(dbHelper.getCategory(transData.get(listPosition).getCat_id()).getCat_name());
        textViewDate.setText(transData.get(listPosition).getDate());
        dbHelper.close();

    }

    @Override
    public int getItemCount() {
        return transData.size();
    }
}