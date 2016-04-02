package com.boopathi.expensemanager.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.boopathi.expensemanager.R;
import com.boopathi.expensemanager.adapter.CategoryListAdapter;
import com.boopathi.expensemanager.db.DBHelper;
import com.boopathi.expensemanager.model.Category;

import java.util.List;

public class SelectCategoryActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private CategoryListAdapter adapter;
    private View containerView;
    private static String[] titles = null;
    private String mode="";
    private static DBHelper db;



    public static List<Category> getData(){
        List<Category> data;
        data=db.getAllCategories();
        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        db=new DBHelper(getApplicationContext());

        Intent previousIntent= getIntent();
        mode = previousIntent.getStringExtra("mode");



        recyclerView =(RecyclerView) findViewById(R.id.categoryList);
        adapter = new CategoryListAdapter(getApplicationContext(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }


    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }


    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
