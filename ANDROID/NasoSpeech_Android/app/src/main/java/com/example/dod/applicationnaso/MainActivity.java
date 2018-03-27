package com.example.dod.applicationnaso;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.sql.BatchUpdateException;

public class MainActivity extends Activity {
     private Button button;
    private Button forgotpassbutton;
    private Button signupbutton;

    Button signupactivity;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
      button = (Button) findViewById(R.id.buttonone);
       forgotpassbutton = (Button) findViewById(R.id.buttontwo) ;
       signupbutton = (Button) findViewById(R.id.buttonthree) ;
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,doctor_dashboard.class);
               startActivity(intent);
           }
       });
       forgotpassbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,Forgot_Password.class);
               startActivity(intent);
           }
       });
       signupbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,signup.class);
               startActivity(intent);
           }
       });


    }
}
