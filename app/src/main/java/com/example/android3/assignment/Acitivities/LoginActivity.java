package com.example.android3.assignment.Acitivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android3.assignment.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    public static final int RC_GOOGLE = 0;
    private static final String EMAIL = "email";
    private static final String NAME = "name";
    private static final String ID = "id";
    ImageView imgCloud, imgStar;
    GoogleSignInClient mGoogleSignInClient;
    Animation animCloud, animStar;
    Button btnLogin;
    CallbackManager callbackManager;
    SignInButton btnGoogle;
    LoginButton btnFaceBook;
    EditText edtUserName, edtPass;
    String name, email, id, imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView() {
        imgCloud = (ImageView) findViewById(R.id.login_ImgCloud);
        imgStar = (ImageView) findViewById(R.id.login_ImgStar);

        edtUserName = (EditText) findViewById(R.id.login_EdtUsername);
        edtPass = (EditText) findViewById(R.id.login_EdtPassword);
        btnLogin = (Button) findViewById(R.id.login_BtnLogin);
        btnGoogle = (SignInButton) findViewById(R.id.login_BtnGoogle);
        btnFaceBook = (LoginButton) findViewById(R.id.login_BtnFaceBook);

        btnGoogle.setSize(SignInButton.SIZE_STANDARD);
        FacebookSdk.sdkInitialize(getApplicationContext());
        btnFaceBook.setReadPermissions(Arrays.asList(EMAIL));
        callbackManager = CallbackManager.Factory.create();
        animCloud = AnimationUtils.loadAnimation(this, R.anim.animcloud);
        animStar = AnimationUtils.loadAnimation(this, R.anim.animstar);
        imgCloud.startAnimation(animCloud);
        imgStar.startAnimation(animStar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUserName.getText().toString().isEmpty() ||
                        edtPass.getText().toString().isEmpty()) {
                    toast("Vui lòng điền đầy đủ thông tin");
                } else {
                    String userName = edtUserName.getText().toString();
                    String pass = edtPass.getText().toString();
                    if (userName.equalsIgnoreCase("ps10674") &&
                            pass.equalsIgnoreCase("123456")) {
                       startActivity(new Intent(LoginActivity.this,MainActivity.class));
                       finish();
                    }

                }
            }
        });


        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login_BtnGoogle:
                        signIn();
                        break;
                }
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnFaceBook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
               resultFaceBook();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {

            }
        });


    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_GOOGLE);
    }
    public void resultFaceBook(){
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    name = object.getString("name");
                    email = object.getString("email");
                    id = object.getString("id");
//                  imgUrl = "https://graph.facebook.com/" + id + "/picture?type=normal";
                    Bundle bundle = new Bundle();
                    bundle.putString("name",name);
                    bundle.putString("email",email);
                    bundle.putString("id",id);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    toast("Login successfully");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameter = new Bundle();
        parameter.putString("fields","name,email,first_name");
        graphRequest.setParameters(parameter);
        graphRequest.executeAsync();
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            toast("Login successfully");
            startActivity(intent);
        } catch (ApiException e) {

        }
    }
    public void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GOOGLE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

}
