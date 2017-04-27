package br.com.luan.clubeprime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ConvidarActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private android.widget.TextView textView4;
    private android.widget.ImageView imageView5;
    private android.widget.TextView textView3;
    private android.widget.ImageView imageView6;
    private android.widget.RelativeLayout email;
    private android.widget.ImageView imageView11;
    private android.widget.TextView textView5;
    private android.widget.ImageView imageView12;
    private android.widget.RelativeLayout wpp;
    private android.widget.ImageView imageView13;
    private android.widget.TextView textView6;
    private android.widget.ImageView imageView14;
    private android.widget.RelativeLayout face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convidar);
        this.face = (RelativeLayout) findViewById(R.id.face);
        this.imageView14 = (ImageView) findViewById(R.id.imageView14);
        this.textView6 = (TextView) findViewById(R.id.textView6);
        this.imageView13 = (ImageView) findViewById(R.id.imageView13);
        this.wpp = (RelativeLayout) findViewById(R.id.wpp);
        this.imageView12 = (ImageView) findViewById(R.id.imageView12);
        this.textView5 = (TextView) findViewById(R.id.textView5);
        this.imageView11 = (ImageView) findViewById(R.id.imageView11);
        this.email = (RelativeLayout) findViewById(R.id.email);
        this.imageView6 = (ImageView) findViewById(R.id.imageView6);
        this.textView3 = (TextView) findViewById(R.id.textView3);
        this.imageView5 = (ImageView) findViewById(R.id.imageView5);
        this.textView4 = (TextView) findViewById(R.id.textView4);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        setSupportActionBar(toolbar);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "www.google.com");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.google.android.gm");
                startActivity(sendIntent);
            }
        });
            ;


        wpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "www.google.com");
                    sendIntent.setType("text/plain");
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                } catch (Exception e) {
                    Toast.makeText(ConvidarActivity.this, "Você precisa ter o WhatsApp instalado.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String mimeType = "text/plain";
//
//// contentUri points to the content being shared to Messenger
//                Uri uri = Uri.parse("android.resource://your.package.here/drawable/diogobig");
//                ShareToMessengerParams shareToMessengerParams =
//                        ShareToMessengerParams.newBuilder(uri, mimeType)
//                                .build();
//
//// Sharing from an Activity
//                MessengerUtils.shareToMessenger(
//                        ConvidarActivity.this,
//                        999,
//                        shareToMessengerParams);

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "www.google.com");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.facebook.orca");
                startActivity(sendIntent);
            }
        });


    }
}
