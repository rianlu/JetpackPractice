package com.example.contentprovidertest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyCOntentProvider extends ContentProvider {

    private MySQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private static final int USER_CODE = 1;

    private static final UriMatcher matcher;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI("com.example.contentprovidertest", "user", 1);
    }

    @Override
    public boolean onCreate() {
        helper = new MySQLiteOpenHelper(getContext(), "test_database", null, 1);
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put("name", "hhh");
        db.insert("user", null, values);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String tableName = getTableName(uri);
        if (tableName != null) {
            return db.query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String tableName = getTableName(uri);
        if (tableName != null) {
            db.insert(tableName, null, values);
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    private String getTableName(Uri uri) {

        switch (matcher.match(uri)) {
            case USER_CODE:
                return "user";
        }
        return null;
    }
}
