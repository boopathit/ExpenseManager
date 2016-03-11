package com.boopathi.expensemanager.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.boopathi.expensemanager.R;
import com.boopathi.expensemanager.adapter.SessionManagement;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG="SignUpActivity";
    private EditText _nameText;
    private EditText _passwordText;
    private EditText _emailText;
    private Button _signUpButton;
    private TextView _loginLink;
    private SessionManagement session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
        actionBar.setDisplayHomeAsUpEnabled(true);

        session = new SessionManagement(getApplicationContext());
        if(session.isLoggedIn() == true){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(getApplicationContext(), SummaryActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            startActivity(i);
        }

        _nameText = (EditText) findViewById(R.id.input_name);
        _passwordText = (EditText) findViewById(R.id.input_passsword);
        _emailText = (EditText) findViewById(R.id.input_email);
        _signUpButton = (Button) findViewById(R.id.btn_signup);
        _loginLink = (TextView) findViewById(R.id.link_login);

        _signUpButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void signup() {
        Log.d(TAG, "Inside Signup Method...");

        if(!validate()){
            onSignUpFailed();
            return;
        }

        _signUpButton.setEnabled(false);


        final ProgressDialog loginProgressDialog = new ProgressDialog(SignUpActivity.this,R.style.AppTheme_Dark_Dialog);
        loginProgressDialog.setIndeterminate(true);
        loginProgressDialog.setMessage("Creating Account");
        loginProgressDialog.show();
        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {

                    @Override
                    public void run() {
                        onSignUpSuccess();
//                        onSignUpFailed()
                        loginProgressDialog.dismiss();
                    }
                }, 3000);
    }

    private boolean validate() {
        boolean valid = true;

        String name= _nameText.getText().toString();
        String email= _emailText.getText().toString();
        String password = _passwordText.getText().toString();


        if(name.isEmpty() || name.length() <3){
            _nameText.setError("Name Must be atleast 3 Characters");
            valid= false;
        } else {
            _nameText.setError(null);
        }
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _emailText.setError("Enter a Valid Email Address");
            valid= false;
        } else {
            _emailText.setError(null);
        }
        if(email.isEmpty() || password.length() < 4 || password.length() > 10 ){
            _passwordText.setError("Length between 4 and 10 Alphanumeric Characters");
            valid= false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }

    private void onSignUpFailed() {
        Toast.makeText(getBaseContext(), "Sign Up Failed", Toast.LENGTH_LONG).show();
        _signUpButton.setEnabled(true);
    }

    private void onSignUpSuccess() {
        _signUpButton.setEnabled(true);
        finish();
    }

    }

