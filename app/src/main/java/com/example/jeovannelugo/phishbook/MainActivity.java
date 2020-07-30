package com.example.jeovannelugo.phishbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button Login;
    EditText username , password;
    String strID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login = (Button) findViewById(R.id.btnLogin);
        username = (EditText)findViewById(R.id.etusername);
        password = (EditText)findViewById(R.id.etpassword);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //
               String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("administrator") && pass.equals("@dmin12three"))
                {
                    Intent intent = new Intent(MainActivity.this, AccountsList.class);
                     startActivity(intent);
                   // Clear();

                }else if(user.equals("jeovanne.lugo") && pass.equals("@dminone23!"))
                {
                    Intent intent = new Intent(MainActivity.this, My_Facebook.class);
                    startActivity(intent);
                   // Clear();

                }else if(user.equals("") && pass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Incorrect username and password", Toast.LENGTH_LONG).show();

                }

                else {


                    database db = new database(MainActivity.this, strID);
                    db.setUsername(username.getText().toString());
                    db.setPassword(password.getText().toString());
                    db.Save();
                    //user = null;

                    Toast.makeText(MainActivity.this, "Incorrect username and password", Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    @Override
    public void onBackPressed() {
      MainActivity.this.finish();
        super.onBackPressed();
    }
}
