package com.boopathi.expensemanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boopathi.expensemanager.R;
import com.boopathi.expensemanager.model.Category;

import java.util.Collections;
import java.util.List;

/**
 * Created by Boopathi on 10-03-2016.
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.MyViewHolder> {
    List<Category> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public CategoryListAdapter(Context context, List<Category> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_list_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Category current = data.get(position);
        holder.title.setText(current.getCat_name());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.categoryRow);
        }
    }
}