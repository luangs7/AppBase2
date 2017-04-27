package br.com.luan.clubeprime;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import br.com.luan.clubeprime.extras.Camera;

public class PerfilActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private android.widget.ImageView imageView4;
    private android.widget.EditText nome;
    private android.widget.EditText email;
    private android.widget.EditText telefone;
    private android.widget.EditText cel;
    private android.widget.ImageView imageView5;
    private android.widget.TextView textView3;
    private android.widget.ImageView imageView6;
    private android.widget.ImageView imageView7;
    private android.widget.Switch notification;
    private android.widget.Button btnSalvar;
    private android.widget.TextView textView;
    private android.widget.ScrollView scrollView3;

    Camera camera;
    String path = "";
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        this.scrollView3 = (ScrollView) findViewById(R.id.scrollView3);
        this.textView = (TextView) findViewById(R.id.textView);
        this.btnSalvar = (Button) findViewById(R.id.btnSalvar);
        this.notification = (Switch) findViewById(R.id.notification);
        this.imageView7 = (ImageView) findViewById(R.id.imageView7);
        this.imageView6 = (ImageView) findViewById(R.id.imageView6);
        this.textView3 = (TextView) findViewById(R.id.textView3);
        this.imageView5 = (ImageView) findViewById(R.id.imageView5);
        this.cel = (EditText) findViewById(R.id.cel);
        this.telefone = (EditText) findViewById(R.id.telefone);
        this.email = (EditText) findViewById(R.id.email);
        this.nome = (EditText) findViewById(R.id.nome);
        this.foto = (ImageView) findViewById(R.id.foto);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);


        toolbar.setNavigationIcon(R.drawable.menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        setSupportActionBar(toolbar);


        if (camera == null)
            camera = new Camera(this);

        if (path.length() > 0) {

            try {
                Uri uri = Uri.parse(path);
                this.foto.setImageURI(uri);
            } catch (Exception ex) {

            }
        }



        //foto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openBottomSheetImagemCamera();
//            }
//        });


    }


    public void openBottomSheetImagemCamera() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_camera, null);
        TextView txtGaleria = (TextView) view.findViewById(R.id.txt_galeria);
        TextView txtcamera = (TextView) view.findViewById(R.id.txt_camera);


        final Dialog mBottomSheetDialog = new Dialog(this, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();


        txtGaleria.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dispatchGetPictureIntent();
                mBottomSheetDialog.dismiss();
            }
        });

        txtcamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Instantiate the camera
                camera.deleteImage();
                camera.builder()
                        .setDirectory("pics")
                        .setName("helper_" + System.currentTimeMillis())
                        .setImageFormat(Camera.IMAGE_JPEG)
                        .setCompression(75)
                        .setImageHeight(500);
                camera.takePicture();
                mBottomSheetDialog.dismiss();
            }
        });
    }


    private void dispatchGetPictureIntent() {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 888);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Camera.REQUEST_TAKE_PHOTO) {
            Bitmap bitmap = camera.getCameraBitmap();
            if (bitmap != null) {
                Uri uri = new Uri.Builder().scheme("file").path(camera.getCameraBitmapPath()).build();
                path = uri.toString();
                //picFrame.setImageBitmap(bitmap);
                foto.setImageURI(uri);
//                imageView.setVisibility(View.GONE);
//                cirleimageView.setVisibility(View.VISIBLE);

            }
        } else if (requestCode == 888 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            Log.e("onActivityResult", selectedImage.toString());

            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Uri uri = new Uri.Builder().scheme("file").path(picturePath).build();
            path = uri.toString();
            foto.setImageURI(uri);
//            imageView.setVisibility(View.GONE);
//            cirleimageView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (super.onCreateOptionsMenu(menu)) {
            getMenuInflater().inflate(R.menu.menu_perfil, menu);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if(id == R.id.camera){
            openBottomSheetImagemCamera();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


}
