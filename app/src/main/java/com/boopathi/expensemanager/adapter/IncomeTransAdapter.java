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
import com.boopathi.expensemanager.activity.FragmentIncome;
import com.boopathi.expensemanager.db.DBHelper;
import com.boopathi.expensemanager.model.Trans;

import java.util.ArrayList;

public class IncomeTransAdapter extends RecyclerView.Adapter<IncomeTransAdapter.MyViewHolder> {

    private ArrayList<Trans> transData;
    private DBHelper dbHelper;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewIncomeFrom;
        TextView textViewIncomeAmt;
        TextView textViewCat;
        TextView textViewDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewIncomeFrom = (TextView) itemView.findViewById(R.id.incomeFrom);
            this.textViewIncomeAmt = (TextView) itemView.findViewById(R.id.incomeAmt);
            this.textViewCat = (TextView) itemView.findViewById(R.id.incomeCat);
            this.textViewDate = (TextView) itemView.findViewById(R.id.incomeDate);
        }
    }

    public IncomeTransAdapter(ArrayList<Trans> transData,Context context) {
        this.transData = transData;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trans_income_card, parent, false);

        view.setOnClickListener(FragmentIncome.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        dbHelper = new DBHelper(context);
        TextView textViewIncomeFrom = holder.textViewIncomeFrom;
        TextView textViewIncomeAmt = holder.textViewIncomeAmt;
        TextView textViewCat = holder.textViewCat;
        TextView textViewDate = holder.textViewDate;

        textViewIncomeFrom.setText(transData.get(listPosition).getFrom());
        textViewIncomeAmt.setText(Integer.toString(transData.get(listPosition).getAmt()));
        textViewCat.setText(dbHelper.getCategory(transData.get(listPosition).getCat_id()).getCat_name());
        textViewDate.setText(transData.get(listPosition).getDate());
        dbHelper.close();

    }

    @Override
    public int getItemCount() {
        return transData.size();
    }
}