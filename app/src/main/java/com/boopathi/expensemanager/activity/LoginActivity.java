package com.boopathi.expensemanager.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.boopathi.expensemanager.R;
import com.boopathi.expensemanager.adapter.SessionManagement;
import com.boopathi.expensemanager.model.PrefUtils;
import com.boopathi.expensemanager.model.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static final String TAG_CANCEL = "Cancel";
    private static final String TAG_ERROR = "Error";
    private LoginButton loginButton;
    private static CallbackManager callbackManager;
    private ProgressDialog progressDialog;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize SDK before setContentView(Layout ID)
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        // Initialize layout button
        setContentView(R.layout.activity_login_social);
        if (PrefUtils.getCurrentUser(LoginActivity.this) != null) {

            Intent homeIntent = new Intent(LoginActivity.this, SummaryActivity.class);
            startActivity(homeIntent);
            finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();


        callbackManager=CallbackManager.Factory.create();

        loginButton= (LoginButton)findViewById(R.id.fb_login_button);

        loginButton.setReadPermissions("public_profile", "email","user_friends");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progressDialog.setMessage("Loading...");
                progressDialog.show();

                loginButton.performClick();

                loginButton.setPressed(true);

                loginButton.invalidate();

                loginButton.registerCallback(callbackManager, mCallBack);

                loginButton.setPressed(false);

                loginButton.invalidate();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            progressDialog.dismiss();

            // App code
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {

                            Log.e("response: ", response + "");
                            try {
                                user = new User();
                                user.facebookID = object.getString("id").toString();
                                user.email = object.getString("email").toString();
                                user.name = object.getString("name").toString();
                                user.gender = object.getString("gender").toString();
                                PrefUtils.setCurrentUser(user,LoginActivity.this);

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            Toast.makeText(LoginActivity.this,"welcome "+user.name, Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(LoginActivity.this,SummaryActivity.class);
                            startActivity(intent);
                            finish();

                        }

                    });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {
            progressDialog.dismiss();
        }

        @Override
        public void onError(FacebookException e) {
            progressDialog.dismiss();
        }
    };

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
        if (id == R.id.action_add_trans) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
