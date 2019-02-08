package org.pursuit.app_hw_benrabaa_mohamed;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ResultDataBaseHelper extends SQLiteOpenHelper {

    private static  ResultDataBaseHelper myResultDataBaseHelper;
    private static final int SCHEMA_VERSION=1;
    private static final String DATABASE_NAME="result_dataBase.db";
    private static final String TABLE_NAME="RESULT";




    private ResultDataBaseHelper( Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }


    //this method  called static factory method
    public static synchronized ResultDataBaseHelper getInstance(Context context){
        if(myResultDataBaseHelper==null){
            myResultDataBaseHelper=new ResultDataBaseHelper(context.getApplicationContext());
        }

        return myResultDataBaseHelper;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME +
                        " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "pass BOOLEAN, percentage REAL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addReasult(Result myReasult){
          getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME + "(pass, percentage) VALUES('" +
                    myReasult.getPass() + "', '" +
                    myReasult.getPercentage() + "');");

    }

    public int trueCounter() {
        Cursor trueCountCursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE pass = '" + true + "';", null);
        if(trueCountCursor!=null) {
            return trueCountCursor.getCount();
        }else{
            return 0;

        }
    }

    public int totalCounter(){
        Cursor totalCountCursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME +" ;", null);

        if(totalCountCursor!=null) {
            return totalCountCursor.getCount();
        }
        return totalCountCursor.getCount();
    }

    public void deleteAllRecords(){
        getWritableDatabase().execSQL("DELETE FROM "+ TABLE_NAME+";");
    }

    public double getLastpercentage(){

        Cursor percentageCursor = getReadableDatabase().rawQuery(
 //               "SELECT percentage FROM " + TABLE_NAME +" ;", null);
//
               " SELECT percentage FROM " + TABLE_NAME + " WHERE _id = ( SELECT MAX(_id) from " + TABLE_NAME + ");",null);
             //   "SELECT * FROM " + TABLE_NAME +" ;", null);
        //"SELECT MAX(_id) from " + TABLE_NAME + ";",null);

        if(percentageCursor!=null) {
           // if(totalCountCursor) {
                //deleteAllRecords();
            //if(percentageCursor.()) {
            percentageCursor.moveToFirst();
                return percentageCursor.getDouble(percentageCursor.getColumnIndex("percentage"));
               // return  percentageCursor.getInt(percentageCursor.getColumnIndex("_id"));

               // return percentageCursor.getDouble(percentageCursor.getColumnIndex("_id"));
                //return percentageCursor.getCount();
            }
           // }
       // }
        return 99.99;
    }
}
