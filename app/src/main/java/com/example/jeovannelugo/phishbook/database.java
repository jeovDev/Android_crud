package com.example.jeovannelugo.phishbook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;

/**
 * Created by JeovanneLugo on 10/3/2018.
 */

public class database {
    private String myID;
    private String myUsername;
    private String myPassword;
    private Context myContext;

    public void setUsername(String value){myUsername = value;}
    public String getUsername(){return myUsername;}
    public void setPassword(String value){myPassword = value;}
    public String getPassword(){return myPassword;}
    public void setID(String value){myID = value;}
    public String getID(){return myID;}

    public void setContext(Context value){myContext = value;}
    public Context getContext(){return myContext;}

    //constructors
    public database() {}

    public database(Context context, String id){
        if (context != null)
            setContext(context);
        if (id != null)
            setID(id);
    }

    public SQLiteDatabase OpenDB(){
        SQLiteDatabase sqlDB = null;
        sqlDB = this.getContext().openOrCreateDatabase("dbUsers.db",this.getContext().MODE_PRIVATE, null);
        sqlDB.execSQL("CREATE TABLE IF NOT EXISTS tblUsers1(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Usr_Username VARCHAR(50),Usr_Password VARCHAR(50))");
        return sqlDB;
    }

    public SimpleCursorAdapter GetAllRecords(){
        SimpleCursorAdapter cursorAdapter = null;
        SQLiteDatabase sqLiteDatabase = OpenDB();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tblUsers1 ORDER BY Usr_Username", null);
        cursorAdapter = new SimpleCursorAdapter(this.getContext(),
                android.R.layout.simple_expandable_list_item_2, cursor,
                new String[]{"Usr_Username", "Usr_Password"}, new int[]{android.R.id.text1, android.R.id.text2}, 0);

        return cursorAdapter;
    }

    public void Save(){
        SQLiteDatabase sqLiteDatabase = OpenDB();
        if (this.getID() == null){
            String SQL = "INSERT INTO tblUsers1(Usr_Username,Usr_Password) VALUES(?,?)";
            sqLiteDatabase.execSQL(SQL,
                    new String[] {this.getUsername(), this.getPassword()});
        }else{
            String SQL = "UPDATE tblUsers1 SET Usr_Username = ?, Usr_Password = ? WHERE _id = ?";
            sqLiteDatabase.execSQL(SQL,
                    new String[] {this.getUsername(), this.getPassword(), this.getID()});
        }
        sqLiteDatabase.close();
    }

    public void Delete(){
        SQLiteDatabase sqLiteDatabase = OpenDB();
        String SQL = "DELETE FROM tblUsers1 WHERE _id = ?";
        sqLiteDatabase.execSQL(SQL, new String[] {this.getID()});
        sqLiteDatabase.close();
    }

    public void getRecord()
    {
        SQLiteDatabase sqLiteDatabase = OpenDB();
        String SQL = "SELECT * FROM tblUsers1 WHERE _id = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, new String[]{this.getID()});
        cursor.moveToFirst();
        this.setUsername(cursor.getString(cursor.getColumnIndex("Usr_Username")));
        this.setPassword(cursor.getString(cursor.getColumnIndex("Usr_Password")));
        cursor.close();
        cursor = null;
    }
}
