package com.boopathi.expensemanager.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.boopathi.expensemanager.R;
import com.boopathi.expensemanager.adapter.CategoryListAdapter;
import com.boopathi.expensemanager.adapter.SimpleSectionedRecyclerViewAdapter;
import com.boopathi.expensemanager.db.DBHelper;
import com.boopathi.expensemanager.model.Category;
import com.boopathi.expensemanager.model.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    final CharSequence[] modes = {"Spend", "Income"};
    private RecyclerView mRecyclerView;
    private CategoryListAdapter mAdapter;
    private DBHelper dbHelper;
    private ArrayList<String> categoryIncomeList;
    private ArrayList<String> categorySpendList;
    private ArrayList<Category> categoryList;

    String[] title;
    String spinner_item;

    SpinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);



        //Your RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.catList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));


        setCategoryList();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_cat_list) {
            final Dialog dialog = new Dialog(CategoryActivity.this);
            dialog.setTitle("Add Category");
            dialog.setContentView(R.layout.row_spinner);

            dialog.setCancelable(true);

            // set the custom dialog components - text, image and button
            final Spinner spinner = (Spinner) dialog
                    .findViewById(R.id.spinner1);
            final EditText edittext = (EditText) dialog
                    .findViewById(R.id.editTextCatNameList);
            Button button = (Button) dialog.findViewById(R.id.buttonAddCat);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent,
                                           View view, int position, long id) {
                    // TODO Auto-generated method stub
                    spinner_item = title[position];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub

                }
            });

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                    Toast.makeText(
                            getApplicationContext(),
                            spinner_item + " - "
                                    + edittext.getText().toString().trim(),
                            Toast.LENGTH_LONG).show();
                    DBHelper dbHelperIn = new DBHelper(getApplicationContext());
                    dbHelperIn.insertCategory(edittext.getText().toString(),spinner_item);
                    dbHelper.close();
                    setCategoryList();
                }
            });
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SpinnerAdapter extends BaseAdapter {
        Context context;
        private LayoutInflater mInflater;

        public SpinnerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ListContent holder;
            View v = convertView;
            if (v == null) {
                mInflater = (LayoutInflater) context
                        .getSystemService(context.LAYOUT_INFLATER_SERVICE);
                v = mInflater.inflate(R.layout.row_textview, null);
                holder = new ListContent();
                holder.text = (TextView) v.findViewById(R.id.textViewRow);

                v.setTag(holder);
            } else {

                holder = (ListContent) v.getTag();
            }

            holder.text.setText(title[position]);

            return v;
        }
    }

    static class ListContent {

        TextView text;
    }
    private void setCategoryList() {
        //DBHelper
        dbHelper = new DBHelper(getApplicationContext());

        //Your RecyclerView.Adapter

        categoryIncomeList = dbHelper.getModeCategories("Income");
        categoryList= dbHelper.getAllCategories();
        mAdapter = new CategoryListAdapter(this,categoryList);
        dbHelper.close();


        //This is the code to provide a sectioned list
        List<SimpleSectionedRecyclerViewAdapter.Section> sections =
                new ArrayList<>();

        //Sections
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0,"Income"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(categoryIncomeList.size(),"Spend"));

        //Add your adapter to the sectionAdapter
        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new
                SimpleSectionedRecyclerViewAdapter(this,R.layout.section,R.id.section_text,mAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        mRecyclerView.setAdapter(mSectionedAdapter);

        title = new String[modes.length];
        int i=0;
        for(CharSequence ch: modes){
            title[i++] = ch.toString();
        }
        adapter=new SpinnerAdapter(getApplicationContext());

    }

}