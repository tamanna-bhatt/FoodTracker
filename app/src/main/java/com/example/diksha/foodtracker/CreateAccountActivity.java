package com.example.diksha.foodtracker;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.diksha.foodtracker.trackerdatabase.FoodTrackerDatabase;
import com.example.diksha.foodtracker.pojos.AllLogin;
import com.example.diksha.foodtracker.pojos.SharedPrefrences;


/**
 * Created by jaydeep on 13/7/17.
 */

public class CreateAccountActivity extends AppCompatActivity {


    private EditText etMobile,etEmail,etName,etPassword,etRetype;

    private TextView signUp;
    private ImageView ivBack;

    private FoodTrackerDatabase foodTrackerDatabase;
    private SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        foodTrackerDatabase = new FoodTrackerDatabase(CreateAccountActivity.this);
        sqLiteDatabase = foodTrackerDatabase.getWritableDatabase();

        initComponent();



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etName.getText().toString().equals("") || etEmail.getText().toString().equals("")
                        || etMobile.getText().toString().equals("") || etPassword.getText().toString().equals("")) {

                    Toast.makeText(CreateAccountActivity.this, "Fil the data", Toast.LENGTH_SHORT).show();
                }else {

                    if (foodTrackerDatabase.isEmailAvailable(etEmail.getText().toString())){

                        Toast.makeText(CreateAccountActivity.this, "Email id already available", Toast.LENGTH_SHORT).show();
                    }else {
                        AllLogin allLogin = new AllLogin(etName.getText().toString(),
                                etEmail.getText().toString(), etMobile.getText().toString()
                        , etPassword.getText().toString(), etRetype.getText().toString());

                      foodTrackerDatabase.insertLoginData(allLogin);

                        AllLogin allLogin1 =  foodTrackerDatabase.getLoginIdForSignuUp(etEmail.getText().toString(), etPassword.getText().toString());


                        SharedPrefrences.setUserId(allLogin1.getId() + "", CreateAccountActivity.this);
                        SharedPrefrences.setName(allLogin.getName() + "", CreateAccountActivity.this);

                        Intent it = new Intent(CreateAccountActivity.this, MainActivity.class);
                        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        it.putExtra("EXIT", true);
                        startActivity(it);
                        finish();
                    }


//                    Intent it = new Intent(SignupActivity.this, NavigationActivity.class);
//                    startActivity(it);

//                    sendSignUpData(etName.getText().toString(),etEmail.getText().toString(),etMobile.getText().toString(),
//                            etPassword.getText().toString());
                }





            }
        });




        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }

    private void initComponent() {

        etMobile = (EditText)findViewById(R.id.etMobile);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etName = (EditText)findViewById(R.id.etName);
        etPassword = (EditText)findViewById(R.id.etPassword);
        signUp=  (TextView)findViewById(R.id.signUp);
        etRetype = (EditText)findViewById(R.id.etRetype);
        ivBack= (ImageView) findViewById(R.id.ivBack);

    }





}
