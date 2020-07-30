package com.example.jeovannelugo.phishbook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class AccountsList extends AppCompatActivity {

    ListView list;
    String strID;
    Long id;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Accounts :) ");
        list = (ListView) findViewById(R.id.lv);
        LoadListView();


           list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, final View view, int i, final long l) {





                       AlertDialog.Builder alert = new AlertDialog.Builder(AccountsList.this);
                       alert.setCancelable(false);
                       alert.setTitle("Confirm Delete");
                       alert.setMessage("Are you sure ?");
                       alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               dialogInterface.cancel();
                           }
                       });
                       alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               id = l;


                               database db = new database(AccountsList.this , id.toString());
                               db.setID(id.toString());
                               db.Delete();
                               Snackbar.make(view, "Removed" , Snackbar.LENGTH_SHORT).show();
                               LoadListView();
                           }
                       });
               AlertDialog builder = alert.create();
               builder.getWindow().setGravity(Gravity.CENTER);
               builder.show();






               return false;
           }
       });
        //
    }
    void LoadListView()
    {
        database user = new database(this,null);
        final SimpleCursorAdapter cursorAdapter = user.GetAllRecords();
        list.setAdapter(cursorAdapter);

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}


