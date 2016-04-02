package com.boopathi.expensemanager.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.boopathi.expensemanager.R;
import com.boopathi.expensemanager.db.DBHelper;
import com.boopathi.expensemanager.model.Trans;

public class TransactionActivity extends AppCompatActivity {

    DBHelper dbHelper;
    final CharSequence[] modes = { "Spend", "Income" };

    private Trans trans = new Trans();
    private EditText editTextAmount;
    private EditText editTextMode;
    private EditText editTextTo;
    private EditText editTextCat;
    private EditText editTextDate;
    private EditText editTextNotes;
    private AlertDialog alert;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        dbHelper = new DBHelper(getApplicationContext());

        editTextAmount = (EditText) findViewById(R.id.editTextAmount);
        editTextMode = (EditText) findViewById(R.id.editTextMode);
        editTextTo = (EditText) findViewById(R.id.editTextTo);
        editTextCat = (EditText) findViewById(R.id.editTextCat);
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextNotes = (EditText) findViewById(R.id.editTextNotes);

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Make your selection");
        builder.setItems(modes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                editTextMode.setText(modes[item]);
            }
        });
        alert = builder.create();
        alert.show();

        editTextMode.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                alert.show();
                return true;
            }
        });
        editTextTo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                int actionX, actionY;
                actionX = (int) event.getRawX();
                actionY = (int) event.getRawY();
                Drawable drawableBottom = editTextTo.getCompoundDrawables()[DRAWABLE_RIGHT];

                if(event.getAction() == MotionEvent.ACTION_UP) {

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (editTextTo.getRight() - editTextTo.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()+150)) {
                            editTextTo.getText().clear();

                            return true;
                        }
                    }
                }
                return false;
            }
        });
        editTextCat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(getApplicationContext(),SelectCategoryActivity.class);
                intent.putExtra("mode",editTextTo.getText().toString());
                startActivityForResult(intent,5252);
                return true;
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trans, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
