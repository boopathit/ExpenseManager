package com.boopathi.expensemanager.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.boopathi.expensemanager.R;
import com.boopathi.expensemanager.adapter.SessionManagement;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private EditText _eMailText ;
    private EditText _passwordText;
    private Button _loginButton;
    private TextView _forgotPasswordLink;
    private TextView _signUpLink;
    private SessionManagement session;
    private Context _context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        _eMailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_passsword);
        _loginButton = (Button) findViewById(R.id.btn_login);
        _forgotPasswordLink = (TextView) findViewById(R.id.link_forgot_password);
        _signUpLink = (TextView) findViewById(R.id.link_signup);


        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signUpLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(signUpIntent, REQUEST_SIGNUP);
            }
        });

    }

    private void login() {
        Log.d(TAG, "Login Method");

        if(!validate()){
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);
        final ProgressDialog loginProgressDialog = new ProgressDialog(LoginActivity.this,R.style.AppTheme_Dark_Dialog);
        loginProgressDialog.setIndeterminate(true);
        loginProgressDialog.setMessage("Authenticating Login");
        loginProgressDialog.show();
        final String email = _eMailText.getText().toString();
        final String password = _passwordText.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {

                    @Override
                    public void run() {

                        if (email.equals("booptheboss007@gmail.com") && password.equals("Pappu12345")) {
                            onLoginSuccess();
                        }
                        else{
                            Log.e(TAG,email+"----"+password);
                            onLoginFailed();
                        }

                        loginProgressDialog.dismiss();
                    }
                }, 3000);
    }

    private boolean validate() {
        boolean valid = true;

        String email= _eMailText.getText().toString();
        String password = _passwordText.getText().toString();

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _eMailText.setError("Enter a Valid Email Address");
            valid= false;
        } else {
            _eMailText.setError(null);
        }
        if(email.isEmpty() || password.length() < 4 || password.length() > 10 ){
            _passwordText.setError("Length between 4 and 10 Alphanumeric Characters");
            valid= false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }

    private void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    private void onLoginSuccess() {
        // Creating user login session
        session.createLoginSession("ExpenseManager", _eMailText.getText().toString());
        _loginButton.setEnabled(true);
        Intent intent = new Intent(getApplicationContext(),SummaryActivity.class);
        startActivity(intent);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_SIGNUP){
            if(resultCode == RESULT_OK) {
//                Implementing Sign up Logic Here
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
