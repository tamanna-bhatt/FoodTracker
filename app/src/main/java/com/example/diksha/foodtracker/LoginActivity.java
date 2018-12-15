package com.example.diksha.foodtracker;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.diksha.foodtracker.trackerdatabase.FoodTrackerDatabase;
import com.example.diksha.foodtracker.pojos.AllLogin;
import com.example.diksha.foodtracker.pojos.SharedPrefrences;


public class LoginActivity extends AppCompatActivity {

    private TextView login,tvSignUp,tvForgotPassword;
    private EditText etEmail,etPassword;
    private FoodTrackerDatabase foodTrackerDatabase;
    private SQLiteDatabase sqLiteDatabase;

    private Context context = LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        foodTrackerDatabase = new FoodTrackerDatabase(LoginActivity.this);
        sqLiteDatabase = foodTrackerDatabase.getWritableDatabase();
        login=  (TextView)findViewById(R.id.login);
        tvSignUp=  (TextView)findViewById(R.id.tvSignUp);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword= (EditText)findViewById(R.id.etPassword);
        tvForgotPassword= (TextView) findViewById(R.id.tvForgotPassword);


        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(LoginActivity.this, CreateAccountActivity.class);
              //  it.putExtra("mobile", etMobile.getText().toString());
                startActivity(it);
                finish();

            }
        });




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (etEmail.getText().toString().equalsIgnoreCase("") || etPassword.getText().toString().equalsIgnoreCase("")){

                    Toast.makeText(LoginActivity.this, "Pleasefill the details", Toast.LENGTH_SHORT).show();
                }else {
                    AllLogin allLogin =  foodTrackerDatabase.getLoginId(etEmail.getText().toString(), etPassword.getText().toString());

                    if (allLogin.getId() != -1){

                        SharedPrefrences.setUserId(allLogin.getId() + "", LoginActivity.this);
                        SharedPrefrences.setName(allLogin.getName() + "", LoginActivity.this);

                        Intent it = new Intent(LoginActivity.this, MainActivity.class);
                        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        it.putExtra("EXIT", true);
                        startActivity(it);
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "Incorrect email or password! Please try again!", Toast.LENGTH_SHORT).show();

                    }

                }




            }
        });
    }


}
