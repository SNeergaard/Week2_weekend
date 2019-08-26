package com.example.week2_weekend.model.datasource.local.database;

import java.sql.Statement;

public class CelebDBContract {
    public static final String DATABASE_NAME = "celeb_db";
    public static final String TABLE_NAME = "celebrity_table";
    public static final int DATABASE_VERSION = 1;
    public static final String COL_NAME = "name";
    public static final String COL_AGE = "age";
    public static final String COL_GENDER = "gender";
    public static final String COL_TYPE = "type";
    public static final String COL_ID = "id";

    public static final String QUERY_CREATE_TABLE =
        String.format("CREATE TABLE %s(%s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)",
                TABLE_NAME, COL_NAME, COL_AGE, COL_GENDER, COL_TYPE, COL_ID);

    public static final String QUERY_SELECT_ALL = String.format("SELECT * FROM %s", TABLE_NAME);

    public static String QUERY_SELECT_BY_ID(String id) {
        return String.format("SELECT * FROM %s WHERE %s = \'%s\'", TABLE_NAME, COL_ID, id);
    }

    public static  final String DROP_TABLE = String.format("DROP TABLE %s", TABLE_NAME);
}
