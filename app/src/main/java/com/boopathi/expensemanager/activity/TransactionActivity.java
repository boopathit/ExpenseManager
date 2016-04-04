package com.boopathi.expensemanager.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.boopathi.expensemanager.R;
import com.boopathi.expensemanager.db.DBHelper;
import com.boopathi.expensemanager.model.Trans;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    private AlertDialog alertMode;
    private AlertDialog alertCat;
    private AlertDialog.Builder builderMode;
    private AlertDialog.Builder builderCat;
    private String[] modesArrayCat;

    private DatePickerDialog editTextDateDialog;

    private SimpleDateFormat dateFormatter;
    private Date currDate;
    private Calendar currCal;

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

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        builderMode = new AlertDialog.Builder(this);
        builderMode.setTitle("Make your selection");
        builderMode.setItems(modes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                editTextMode.setText(modes[item]);
            }
        });

        alertMode = builderMode.create();
        alertMode.show();
        modesArrayCat= dbHelper.getModeCategories(editTextMode.getText().toString()).toArray(new String[0]);
        builderCat = new AlertDialog.Builder(this);
        builderCat.setTitle("Select Category");
        builderCat.setItems(modesArrayCat, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                editTextCat.setText(modesArrayCat[item]);
                alertCat.dismiss();
            }
        });
        alertCat = builderCat.create();
        Log.e("Transaction Activity", Arrays.deepToString(modesArrayCat));
        setDateTimeField();
        currDate=getDateTime();
        editTextDate.setText(dateFormatter.format(currDate));

        editTextMode.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                alertMode.show();
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


                alertCat.show();

                return true;
            }
        });
        editTextDate.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editTextDateDialog.show();
                return false;
            }
        });
    }

    private void setDateTimeField() {


        Calendar newCalendar = Calendar.getInstance();
        editTextDateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editTextDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        currCal=Calendar.getInstance();
        currCal.setTime(currDate);
        currCal.add(Calendar.DATE,1);
        editTextDateDialog.getDatePicker().setMaxDate(currCal.getTimeInMillis());
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

            if(validateAll()){
                trans.setAmt(Integer.parseInt(editTextAmount.getText().toString()));
                trans.setType(editTextMode.getText().toString().equals("Income") ? 1 : 0);
                if(editTextMode.getText().toString().equals("Income")){
                    trans.setFrom(editTextTo.getText().toString());
                    trans.setTo(null);
                } else{
                    trans.setTo(editTextTo.getText().toString());
                    trans.setFrom(null);
                }
                trans.setCat_id(dbHelper.getCategoryId(editTextCat.getText().toString()).getId());
                trans.setDate(dateFormatter.format(currDate));
                trans.setNote(editTextNotes.getText().toString());
                dbHelper.insertTransaction(trans);
                finish();

            }


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean validateAll() {
        if (editTextAmount.getText().toString().matches("")) {
            editTextAmount.setError("Enter a Valid Amount");
            return false;
        }
        if (editTextTo.getText().toString().matches("")) {
            editTextAmount.setError("Enter Valid Sender/Receiver");
            return false;
        }
        if (editTextCat.getText().toString().matches("")) {
            editTextAmount.setError("Select a Category");
            return false;
        }
        return true;
    }

    private Date getDateTime() {
        Date date = new Date();
        return date;
    }

}
