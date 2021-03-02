package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    EditText Username;
    EditText Password;
    Button btnlogin;
    Button btnsignup;
    Button createacct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();

        if (ParseUser.getCurrentUser()!=null){  //if someone exits app and is still logged in, go into app
            goMainActivity();
            Toast.makeText(LoginActivity.this,"Logged in as " + ParseUser.getCurrentUser(),Toast.LENGTH_LONG).show();
        }

        Username=findViewById(R.id.username);
        Password=findViewById(R.id.password);
        btnlogin=findViewById(R.id.LoginButton);
        btnsignup=findViewById(R.id.SignUpButton);  //handle later
        createacct=findViewById(R.id.createbutton);

        btnsignup.setOnClickListener(new View.OnClickListener() {   //I added this whole block
            @Override
            public void onClick(View v) {
                createacct.setVisibility(View.VISIBLE);
                btnlogin.setVisibility(View.INVISIBLE);
                btnsignup.setVisibility(View.INVISIBLE);

                createacct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ParseUser user = new ParseUser();
                        // Set core properties
                        user.setUsername(Username.getText().toString());
                        user.setPassword(Password.getText().toString());
                        //user.setEmail("email@example.com");
                        // Set custom properties
                        //user.put("phone", "650-253-0000");
                        // Invoke signUpInBackground
                        user.signUpInBackground(new SignUpCallback() {
                            public void done(ParseException e) {
                                if (e == null) {
                                    Toast.makeText(LoginActivity.this,"Account Created",Toast.LENGTH_LONG).show();
                                    goMainActivity();
                                }
                                else {
                                    Toast.makeText(LoginActivity.this,"Could not create account",Toast.LENGTH_LONG).show();

                                }
                            }
                        });

                    }
                });


            }
        });




        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UserName=Username.getText().toString();
                String PassWord=Password.getText().toString();
                loginuser(UserName,PassWord);
            }
        });


                //logininbackground preferred over login. logins should be in background
    }
    private void loginuser(String USERNAME, String PWord){
        ParseUser.logInInBackground(USERNAME, PWord, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {        //if succeeds, e is null
                if (e!=null) {
                    Toast.makeText(LoginActivity.this,"Incorrect Username or Password",Toast.LENGTH_LONG).show();
                    Log.e("error", "issue with login");
                    return;
                }

                Toast.makeText(LoginActivity.this,"Login success",Toast.LENGTH_SHORT).show();
                goMainActivity();
            }
        });
    }

    private void goMainActivity() {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();       //now when user tries to go back the app just exits which is better
    }

}