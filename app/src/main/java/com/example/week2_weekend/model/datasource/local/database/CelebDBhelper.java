package com.example.week2_weekend.model.datasource.local.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.week2_weekend.model.celebrity.Celebrity;

import java.util.ArrayList;

public class CelebDBhelper extends SQLiteOpenHelper {
    public CelebDBhelper(Context context) {
        super(context, CelebDBContract.DATABASE_NAME, null, CelebDBContract.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL((CelebDBContract.QUERY_CREATE_TABLE));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CelebDBContract.DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Celebrity> getAllCelebs(){
        ArrayList<Celebrity> returnCelebList = new ArrayList<>();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();

        Cursor cursor = readableDatabase.rawQuery(CelebDBContract.QUERY_SELECT_ALL, null);
        if(cursor.moveToFirst()){
            do{
                Celebrity currentCeleb = new Celebrity();
                currentCeleb.setCelebrityName(cursor.getString(cursor.getColumnIndex(CelebDBContract.COL_NAME)));
                currentCeleb.setCelebrityAge(cursor.getString(cursor.getColumnIndex(CelebDBContract.COL_AGE)));
                currentCeleb.setCelebrityGender(cursor.getString(cursor.getColumnIndex(CelebDBContract.COL_GENDER)));
                currentCeleb.setCelebrityType(cursor.getString(cursor.getColumnIndex(CelebDBContract.COL_TYPE)));
                currentCeleb.setId(cursor.getString(cursor.getColumnIndex(CelebDBContract.COL_ID)));
                returnCelebList.add(currentCeleb);
            }while (cursor.moveToNext());
        }

        return returnCelebList;
    }

    public Celebrity getCelebById(String id) {
        Celebrity currentCeleb = new Celebrity();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(CelebDBContract.QUERY_SELECT_BY_ID(id), null);

        if(cursor.moveToFirst()){
            currentCeleb.setCelebrityName(cursor.getString(cursor.getColumnIndex(CelebDBContract.COL_NAME)));
            currentCeleb.setCelebrityAge(cursor.getString(cursor.getColumnIndex(CelebDBContract.COL_AGE)));
            currentCeleb.setCelebrityGender(cursor.getString(cursor.getColumnIndex(CelebDBContract.COL_GENDER)));
            currentCeleb.setCelebrityType(cursor.getString(cursor.getColumnIndex(CelebDBContract.COL_TYPE)));
            currentCeleb.setId(cursor.getString(cursor.getColumnIndex(CelebDBContract.COL_ID)));
        }

        return currentCeleb;
    }

    public void insertCelebIntoBD(Celebrity celebToSave){
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CelebDBContract.COL_NAME, celebToSave.getCelebrityName());
        contentValues.put(CelebDBContract.COL_AGE, celebToSave.getCelebrityAge());
        contentValues.put(CelebDBContract.COL_GENDER, celebToSave.getCelebrityGender());
        contentValues.put(CelebDBContract.COL_TYPE, celebToSave.getCelebrityType());

        writableDatabase.insert(CelebDBContract.TABLE_NAME, null, contentValues);
    }

    public void updateCelebinDB(String id, Celebrity passedCelebInfo){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CelebDBContract.COL_NAME, passedCelebInfo.getCelebrityName());
        contentValues.put(CelebDBContract.COL_AGE, passedCelebInfo.getCelebrityAge());
        contentValues.put(CelebDBContract.COL_GENDER, passedCelebInfo.getCelebrityGender());
        contentValues.put(CelebDBContract.COL_TYPE, passedCelebInfo.getCelebrityType());

        sqLiteDatabase.update(CelebDBContract.TABLE_NAME, contentValues, "ID = ?", new String[]{id});
    }

    public void deleteCelebInDB(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(CelebDBContract.TABLE_NAME, "ID = ?", new String[]{id});
    }
}
