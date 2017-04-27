package br.com.luan.clubeprime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import br.com.luan.clubeprime.debug.FBKeyHash;

public class LoginActivity extends AppCompatActivity {

    private android.widget.EditText editTextEmail;
    private android.widget.EditText editTextPass;
    private android.widget.TextView textRecupera;
    private android.widget.Button btnEntrar;
    private android.widget.TextView textView2;
    private android.widget.ImageView imageView3;
    private android.widget.RelativeLayout btnFacebook;
    private android.widget.TextView cadastro;
    private android.widget.LinearLayout linearLayout;
    private android.widget.ScrollView scrollView;
    private android.widget.RelativeLayout layoutmain;

    public boolean isFacebook;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FBKeyHash.getHash(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.layoutmain = (RelativeLayout) findViewById(R.id.layoutmain);
        this.scrollView = (ScrollView) findViewById(R.id.scrollView);
        this.linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        this.cadastro = (TextView) findViewById(R.id.cadastro);
        this.btnFacebook = (RelativeLayout) findViewById(R.id.btnFacebook);
        this.imageView3 = (ImageView) findViewById(R.id.imageView3);
        this.textView2 = (TextView) findViewById(R.id.textView2);
        this.btnEntrar = (Button) findViewById(R.id.btnEntrar);
        this.textRecupera = (TextView) findViewById(R.id.textRecupera);
        this.editTextPass = (EditText) findViewById(R.id.editTextPass);
        this.editTextEmail = (EditText) findViewById(R.id.editTextEmail);


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),MainActivity.class));
            }
        });

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),CadastroActivity.class));

            }
        });


        FacebookSdk.sdkInitialize(getApplicationContext());

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFacebook = true;
                loginfacebook();
            }
        });
    }

    public void loginfacebook(){
        //if(MyApplication.getInstance().isNetworkConnected()){
//getData("email@email.com","123");
            callbackManager = CallbackManager.Factory.create();
            LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {

                                    JSONObject jsonObject1 = jsonObject;
                                    String id = "";
                                    String mail = "";
                                    String name = "";
                                    String username = "";
                                    String link = "";
                                    String gender = "";
                                    String avatar = "";
                                    try {
                                        id = jsonObject.getString("id");

                                        mail = id + "@facebook.com";
                                        if (jsonObject1.has("email")) {
                                            mail = jsonObject1.getString("email");
                                        }

                                        username = id;
                                        if (jsonObject1.has("username")) {
                                            username = jsonObject1.getString("username");
                                        }

                                        if (jsonObject1.has("name")) {
                                            name = jsonObject1.getString("name");
                                        }
                                        link = "facebook.com/" + id;
                                        if (jsonObject1.has("link")) {
                                            link = jsonObject1.getString("link");
                                        }

                                        avatar = "https://graph.facebook.com/" + id + "/picture?type=large";
                                        if (jsonObject1.has("avatar")) {
                                            avatar = jsonObject1.getString("avatar");
                                        }
                                        startActivity(new Intent(getBaseContext(),MainActivity.class));
//                                        getdata(mail,"",id,name,link,avatar);
                                       // getdata(name,mail,id,avatar,link);
                                    } catch (JSONException e) {
                                        Toast.makeText(getBaseContext(), "Não foi possivel logar com facebook", Toast.LENGTH_LONG).show();
                                        e.printStackTrace();
                                        LoginManager.getInstance().logOut();
                                    }
                                }
                            });
                            Bundle parameters = new Bundle();
                            parameters.putString("fields", "id,name,email,link,picture");
                            graphRequest.setParameters(parameters);
                            graphRequest.executeAsync();
                        }

                        @Override
                        public void onCancel() {
                            LoginManager.getInstance().logOut();
                            Toast.makeText(getBaseContext(), "Não foi possível logar com o Facebook. Por favor, tente novamente.", Toast.LENGTH_LONG).show();
                            Log.e("oncacel", "oncacel");
                        }

                        @Override
                        public void onError(FacebookException exception) {
                            LoginManager.getInstance().logOut();
                            Toast.makeText(getBaseContext(), "Não foi possível logar com o Facebook. Por favor, tente novamente.", Toast.LENGTH_LONG).show();
                        }
                    });

            LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
            // LoginManager.getInstance().logInWithPublishPermissions(PerfilFragment.this, Arrays.asList("public_profile", "email", "user_about_me"));

//        }else{
//            Toast.makeText(getBaseContext(), "Não foi possível logar com o Facebook. Por favor, tente novamente.", Toast.LENGTH_LONG).show();
//        }
    }


}
