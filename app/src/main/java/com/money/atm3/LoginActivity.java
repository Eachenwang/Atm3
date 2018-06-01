package com.money.atm3;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //remember userid
        EditText edUserid = findViewById(R.id.ed_userid);
        SharedPreferences setting = getSharedPreferences("atm", MODE_PRIVATE);
        edUserid.setText(setting.getString("PREF_USERID",""));

    }

    public void login(View view){
        EditText edUserid = findViewById(R.id.ed_userid);
        EditText edPasswd = findViewById(R.id.ed_passwd);
        String uid = edUserid.getText().toString();
        String pw = edPasswd.getText().toString();
        if (uid.equals("jack")&&pw.equals("1234")){
            getIntent().putExtra("LOGIN_USERID", uid);
            getIntent().putExtra("LOGIN_PASSWD", pw);

            //send result uid and pw to mainactivity
            setResult(RESULT_OK,getIntent());
            Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();

            //remember userid
            SharedPreferences setting = getSharedPreferences("atm", MODE_PRIVATE);
            setting.edit()
                    .putString("PREF_USERID",uid)
                    .apply();

            finish();
        }else{
            new AlertDialog.Builder(this)
                    .setTitle("Atm")
                    .setMessage("login fail")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

    public void cancel(View view){
    }
}
