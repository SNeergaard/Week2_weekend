package com.example.week2_weekend.model.datasource.local.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.week2_weekend.model.datasource.local.database.CelebDBhelper;

import static com.example.week2_weekend.model.datasource.local.contentprovider.CelebProviderContract.CONTENT_AUTHORITY;
import static com.example.week2_weekend.model.datasource.local.contentprovider.CelebProviderContract.PATH_CELEB;
import static com.example.week2_weekend.model.datasource.local.database.CelebDBContract.COL_ID;
import static com.example.week2_weekend.model.datasource.local.database.CelebDBContract.TABLE_NAME;

public class CelebContentProvider extends ContentProvider {
    public static final int CELEBRITY = 100;
    public static final int CELEBRITY_ID = 101;
    private CelebDBhelper dbHelper;
    private UriMatcher uriMatcher = buildUriMatcher();

    @Override
    public boolean onCreate() {
        dbHelper= new CelebDBhelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor returnCurser = null;
        switch (uriMatcher.match(uri)){
            case CELEBRITY:
                returnCurser = sqLiteDatabase.query(TABLE_NAME, strings, s, strings1, null, null, s1);
                break;
            case CELEBRITY_ID:
                returnCurser = sqLiteDatabase.query(TABLE_NAME, strings, CelebProviderContract.CelebEntry._ID + " = ?", new String[]{String.valueOf((ContentUris.parseId(uri)))}, null, null, s1);
            break;
        }

        return returnCurser;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case CELEBRITY:
                return CelebProviderContract.CelebEntry.CONTENT_TYPE;
            case CELEBRITY_ID:
                return CelebProviderContract.CelebEntry.CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        Uri returnUri = CelebProviderContract.CelebEntry.buildPhoneUri(id);
        getContext().getContentResolver().notifyChange(returnUri, null);

        return returnUri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int id = sqLiteDatabase.delete(TABLE_NAME, COL_ID + " = ?", strings);
        return id;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int id = sqLiteDatabase.update(TABLE_NAME, contentValues, COL_ID + " = ?", strings);
        return id;
    }
    public UriMatcher buildUriMatcher(){
            UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
            uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CELEB, CELEBRITY);
            uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CELEB + "/#", CELEBRITY_ID);
            return uriMatcher;
    }
}
