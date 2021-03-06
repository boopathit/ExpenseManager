package com.boopathi.expensemanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.boopathi.expensemanager.model.Category;
import com.boopathi.expensemanager.model.Trans;

import java.util.ArrayList;
import java.util.Arrays;

public class DBHelper extends SQLiteOpenHelper {

//    Logcat Logger
    private static final String TAG = "DatabaseHelper";

//    Database Version
    private static final int DB_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "expenseManager";

//    Table Names
    private static final String TABLE_TRANS="Trans";
    private static final String TABLE_CATEGORY="Category";

//    Common Column Names
    private static final String KEY_ID="id";

//    Trans Table Columns Name
    private static final String KEY_TYPE = "mode";
    private static final String KEY_FROM="from";
    private static final String KEY_TO="to";
    private static final String KEY_CAT_ID="cat_id";
    private static final String KEY_AMT="amt";
    private static final String KEY_DATE="ondate";
    private static final String KEY_NOTE="note";

//    Category Table Column Names
    private static final String KEY_NAME="name";
    private static final String KEY_MODE="mode";


//    Cretate Table Trans SStatement
    private static final String CREATE_TABLE_TRANS = " CREATE TABLE "
        + TABLE_TRANS
        + " ( '" + KEY_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT, "
        + KEY_TYPE + " INTEGER , '"
        + KEY_FROM + "' TEXT , '"
        + KEY_TO + "' TEXT , "
        + KEY_CAT_ID + " INTEGER , "
        + KEY_AMT + " INTEGER , '"
        + KEY_DATE + "' DATETIME , '"
        + KEY_NOTE + "' TEXT );";


    //    Cretate Table Category Statement
    private static final String CREATE_TABLE_CATEGORY = " CREATE TABLE "
            + TABLE_CATEGORY
            + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, '"
            + KEY_NAME + "' TEXT, '"
            + KEY_MODE + "' TEXT );";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TRANS);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Breakfast','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Bus Fare','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Car','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Cigarettes','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Coffee','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Cookies','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Courier','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Daily Care','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Dinner','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Donation','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Drinks','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Electricity','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Entertainment','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Fast Food','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Fruits','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Gas','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Gifts','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Lunch','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Maintenance','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Mobile','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Pets','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Prepaid Wallet','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Rent/Mortgage','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Shopping','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Stationary','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Tax','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Transport','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Travel','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Water','Spend');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Gifts','Income');");
        db.execSQL("Insert into "+TABLE_CATEGORY+" ( "+KEY_NAME+" , "+KEY_MODE+" ) values ('Loan','Income');");
        db.execSQL("Insert into " + TABLE_CATEGORY + " ( " + KEY_NAME + " , " + KEY_MODE + " ) values ('Others','Income');");
        db.execSQL("Insert into " + TABLE_CATEGORY + " ( " + KEY_NAME + " , " + KEY_MODE + " ) values ('Salary','Income');");
        db.execSQL("Insert into "
                + TABLE_TRANS+ " ( "
                + KEY_TYPE + " , '"
                + KEY_FROM + "' , '"
                + KEY_TO + "' , "
                + KEY_CAT_ID + " , "
                + KEY_AMT + " , '"
                + KEY_DATE + "' , '"
                + KEY_NOTE + "' )" +
                " values " +
                "( '1'," +
                "'Vagus Tech'," +
                "'null'," +
                "33," +
                "3000," +
                "'2016-04-06 17:00:00'," +
                "'nottes');");
        db.execSQL("Insert into "
                + TABLE_TRANS+ " ( "
                + KEY_TYPE + " , '"
                + KEY_FROM + "' , '"
                + KEY_TO + "' , "
                + KEY_CAT_ID + " , "
                + KEY_AMT + " , '"
                + KEY_DATE + "' , '"
                + KEY_NOTE + "' )" +
                " values " +
                "( '0'," +
                "'null'," +
                "'Katy'," +
                "1," +
                "200," +
                "'2016-04-06 17:10:00'," +
                "'nottes');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        onCreate(db);
    }

//    Adding a Transaction into Trans Table
    public long insertTransaction(Trans trans){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TYPE,trans.getType());
        values.put("'"+KEY_FROM+"'",trans.getFrom());
        values.put("'"+KEY_TO+"'",trans.getTo());
        values.put(KEY_CAT_ID,trans.getCat_id());
        values.put(KEY_AMT,trans.getAmt());
        values.put("'"+KEY_DATE+"'",trans.getDate());
        values.put("'" + KEY_NOTE + "'", trans.getNote());

//        inserting a new row into Trans Table
        long trans_row_id = db.insert(TABLE_TRANS,null,values);
        Log.d(TAG, "Data Inserted with ID : " + trans_row_id);

        return trans_row_id;
    }


    //    Adding a Category into Category Table
    public long insertCategory(String cat_name,String mode){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("'" + KEY_NAME + "'", cat_name);
        values.put("'" + KEY_MODE + "'", mode);

//        inserting a new row into Trans Table
        long category_row_id = db.insert(TABLE_CATEGORY,null,values);

        return category_row_id;
    }

//    fetching from the trans table
    public Trans getTrans(long trans_id){
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery= "SELECT * FROM "    + TABLE_TRANS + " WHERE "
                                                + KEY_ID  + " = " + trans_id;
        Log.d(TAG, "Select Query : " + selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c!= null){
            c.moveToFirst();
        }
        Trans tr=new Trans();
        tr.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        tr.setType(c.getInt(c.getColumnIndex(KEY_TYPE)));
        tr.setFrom(c.getString(c.getColumnIndex(KEY_FROM)));
        tr.setTo(c.getString(c.getColumnIndex(KEY_FROM)));
        tr.setCat_id(c.getInt(c.getColumnIndex(KEY_CAT_ID)));
        tr.setAmt(c.getInt(c.getColumnIndex(KEY_AMT)));
        tr.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
        tr.setNote(c.getString(c.getColumnIndex(KEY_NOTE)));
        return tr;
    }

//    getting all the transaction
    public ArrayList<Trans> getAllTrans(){
        ArrayList<Trans> trans =  new ArrayList<>();
        String selectAllQuery = "SELECT * FROM " + TABLE_TRANS;
        Log.d(TAG, "Select All Query : " + selectAllQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectAllQuery, null);

        if(c!=null){
            c.moveToFirst();
           do{
               Trans tr=new Trans();
               tr.setId(c.getInt(c.getColumnIndex(KEY_ID)));
               tr.setType(c.getInt(c.getColumnIndex(KEY_TYPE)));
               tr.setFrom(c.getString(c.getColumnIndex(KEY_FROM)));
               tr.setTo(c.getString(c.getColumnIndex(KEY_FROM)));
               tr.setCat_id(c.getInt(c.getColumnIndex(KEY_CAT_ID)));
               tr.setAmt(c.getInt(c.getColumnIndex(KEY_AMT)));
               tr.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
               tr.setNote(c.getString(c.getColumnIndex(KEY_NOTE)));
               trans.add(tr);

           }while(c.moveToNext());
        }
        return trans;
    }


    //    getting all the mode transaction
    public ArrayList<Trans> getModeTrans(int mode){
        ArrayList<Trans> trans =  new ArrayList<>();
        String selectAllQuery = "SELECT * FROM " + TABLE_TRANS+" WHERE "+KEY_TYPE+" = "+mode+" order by "+KEY_DATE+" DESC;";
        Log.d(TAG, "Select All Query : " + selectAllQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectAllQuery, null);

        if(c!=null){
            c.moveToFirst();
            do{
                Trans tr=new Trans();
                tr.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                tr.setType(c.getInt(c.getColumnIndex(KEY_TYPE)));
                tr.setFrom(c.getString(c.getColumnIndex(KEY_FROM)));
                tr.setTo(c.getString(c.getColumnIndex(KEY_TO)));
                tr.setCat_id(c.getInt(c.getColumnIndex(KEY_CAT_ID)));
                tr.setAmt(c.getInt(c.getColumnIndex(KEY_AMT)));
                tr.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
                tr.setNote(c.getString(c.getColumnIndex(KEY_NOTE)));
                trans.add(tr);

            }while(c.moveToNext());
        }
        return trans;
    }



    //get net balance amount
    public int getSummaryAmount() {
        String selectAmtQuery = " SELECT (SELECT SUM("+KEY_AMT+") FROM "+TABLE_TRANS+" where "+KEY_MODE+"=1) - (SELECT SUM("+KEY_AMT+") FROM "+TABLE_TRANS+" where "+KEY_MODE+"=0) ";
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d(TAG, "Select Query : " + selectAmtQuery);
        int totAmt=0;
        Cursor c = db.rawQuery(selectAmtQuery, null);
        Log.d(TAG, Arrays.toString(c.getColumnNames()));
        if (c != null) {
            c.moveToFirst();
            do {
                totAmt = c.getInt(0);
            } while (c.moveToNext());

        }
        Log.d(TAG, "NetAmt : " + totAmt);
        return totAmt;
    }


    //    fetching from the Category table
    public Category getCategory(int category_id){
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery= "SELECT * FROM "    + TABLE_CATEGORY + " WHERE "
                + KEY_ID  + " = '" + category_id + "';";
        Log.d(TAG, "Select Query : " + selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c!= null){
            c.moveToFirst();
        }
        Category category = new Category();
        category.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        category.setCat_name(c.getString(c.getColumnIndex(KEY_NAME)));
        category.setMode(c.getString(c.getColumnIndex(KEY_MODE)));
        return category;
    }


    //    fetching from the Category table
    public Category getCategoryId(String catName){
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery= "SELECT * FROM "    + TABLE_CATEGORY + " WHERE "
                + KEY_NAME  + " = '" + catName + "';";
        Log.d(TAG, "Select Query : " + selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c!= null){
            c.moveToFirst();
        }
        Category category = new Category();
        category.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        category.setCat_name(c.getString(c.getColumnIndex(KEY_NAME)));
        category.setMode(c.getString(c.getColumnIndex(KEY_MODE)));
        return category;
    }

    //    getting all the Categories
    public ArrayList<Category> getAllCategories(){
        ArrayList<Category> categoryArrayList =  new ArrayList<>();
        String selectAllQuery = "SELECT * FROM " + TABLE_CATEGORY +" order by "+KEY_MODE+";";
        Log.d(TAG,"Select All Query : " + selectAllQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectAllQuery,null);

        if(c!=null){
            c.moveToFirst();
            do{
                Category ct = new Category();
                ct.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                ct.setCat_name(c.getString(c.getColumnIndex(KEY_NAME)));
                ct.setMode(c.getString(c.getColumnIndex(KEY_MODE)));
                categoryArrayList.add(ct);

            }while(c.moveToNext());
        }
        return categoryArrayList;
    }

    //    getting all the Categories for a particular mode
    public ArrayList<String> getModeCategories(String mode){
        ArrayList<String> categoryArrayList =  new ArrayList<>();
        String selectAllQuery = "SELECT * FROM " + TABLE_CATEGORY +" WHERE " + KEY_MODE + " = '"+mode+"';";
        Log.d(TAG,"Select All Query : " + selectAllQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectAllQuery,null);

        if(c!=null){
            c.moveToFirst();
            do{
                categoryArrayList.add(c.getString(c.getColumnIndex(KEY_NAME)));

            }while(c.moveToNext());
        }
        return categoryArrayList;
    }

//    Update Transaction

    public int updateTransaction(Trans trans){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, trans.getType());
        values.put(KEY_FROM,trans.getFrom());
        values.put(KEY_TO,trans.getTo());
        values.put(KEY_CAT_ID,trans.getCat_id());
        values.put(KEY_AMT,trans.getAmt());
        values.put(KEY_DATE,trans.getDate());
        values.put(KEY_NOTE, trans.getNote());

        return  db.update(TABLE_TRANS,values,KEY_ID + " = ?",new String [] {String.valueOf(trans.getId())});
    }


//    Update Category

    public int updateCategory(Category category){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, category.getId());
        values.put(KEY_NAME, category.getCat_name());
        values.put(KEY_MODE, category.getMode());

        return  db.update(TABLE_CATEGORY,values,KEY_ID + " = ?",new String [] {String.valueOf(category.getId())});
    }

//    deleting a record in the Trans Table
    public void deleteTrans(long trans_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRANS, KEY_ID + " = ? ", new String[] { String.valueOf(trans_id) } );
    }

//    deleting a Category in Category Table
    public void deleteCategory(long category_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORY,KEY_ID +" = ? ",new String[] { String.valueOf(category_id)});
    }
}




