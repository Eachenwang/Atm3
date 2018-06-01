package com.money.atm3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    boolean logon = false;
    private static final int RC_LOGIN = 1;
    String[] func = {"餘額查詢","交易明細","最新消息","投資理財","離開"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //use ListView
        ListView list = findViewById(R.id.list);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                func);
        list.setAdapter(adapter);


        //without logon into login page
        //get result from login page
        if(!logon){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent,RC_LOGIN);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_LOGIN){
            if (resultCode == RESULT_OK) {
                String uid = data.getStringExtra("LOGIN_USERID");
                String pw = data.getStringExtra("LOGIN_PASSWD");
                Log.d("RESULT", "onActivityResult: "+uid+"/"+pw);
            } else {
                finish();
            }
        }
    }
}
