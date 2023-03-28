package com.example.databasemanagementsystem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHandler extends SQLiteOpenHelper {
    private static final String db_name = "SQL";
    private static final String Table_name = "user";
    private static final String Key_id = "id";
    private static final String Key_name = "name";
    private static final String Key_loc = "location";
    private static final String Key_desig = "designation";


    public DBHandler(Context context) {
        super(context, db_name, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Create_Table = "Create Table " +
                Table_name + " ( " +
                Key_id + " Integer" +
                Key_name + "Text" +
                Key_desig + "Text" + ")";
        sqLiteDatabase.execSQL(Create_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_table = "Drop Table if exists " + Table_name;
        sqLiteDatabase.execSQL(drop_table);
        onCreate(sqLiteDatabase);

    }

    public void insertUser(String name, String location, String designation) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Key_name, name);
        contentValues.put(Key_loc, location);
        contentValues.put(Key_desig, designation);
        database.insert(Table_name, null, contentValues);
        database.close();

    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getUser() {
        SQLiteDatabase database = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userlist = new ArrayList<>();
        String query = "Select " +
                "" + Key_id + "," +
                "" + Key_name + "," +
                "" + Key_loc + "," +
                "" + Key_desig + " From " +
                Table_name;
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put(Key_id, cursor.getString(cursor.getColumnIndex(Key_id)));

            user.put(Key_name, cursor.getString(cursor.getColumnIndex(Key_name)));
            user.put(Key_loc, cursor.getString(cursor.getColumnIndex(Key_loc)));
            user.put(Key_desig, cursor.getString(cursor.getColumnIndex(Key_desig)));
            userlist.add(user);


        }
        return userlist;
    }

    public void delete(int userid) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(Table_name, Key_id + "=)", new String[]{String.valueOf(userid)});
        database.close();
    }

    public int updateuser(String loc, String desig, int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Key_loc, loc);
        values.put(Key_desig, desig);
        int count = database.update(Table_name, values, Key_id + "=)", new String[]{String.valueOf(id)});
        return count;


    }


}
