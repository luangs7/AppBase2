package br.com.luan.clubeprime;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import br.com.luan.clubeprime.extras.Camera;
import de.hdodenhof.circleimageview.CircleImageView;

public class CadastroActivity extends AppCompatActivity {

    Camera camera;
    String path = "";
    private android.support.v7.widget.Toolbar toolbar;
    private de.hdodenhof.circleimageview.CircleImageView cirleimageView;
    private android.widget.ImageView imageView;
    private android.widget.EditText nome;
    private android.widget.EditText email;
    private android.widget.EditText telefone;
    private android.widget.EditText cel;
    private android.widget.EditText senha;
    private android.widget.EditText confirmasenha;
    private android.widget.Button btnCadastro;
    private android.widget.ScrollView scrollView2;
    private LinearLayout layoutmain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.layoutmain = (LinearLayout) findViewById(R.id.layoutmain);
        this.scrollView2 = (ScrollView) findViewById(R.id.scrollView2);
        this.btnCadastro = (Button) findViewById(R.id.btnCadastro);
        this.confirmasenha = (EditText) findViewById(R.id.confirmasenha);
        this.senha = (EditText) findViewById(R.id.senha);
        this.cel = (EditText) findViewById(R.id.cel);
        this.telefone = (EditText) findViewById(R.id.telefone);
        this.email = (EditText) findViewById(R.id.email);
        this.nome = (EditText) findViewById(R.id.nome);
        this.imageView = (ImageView) findViewById(R.id.imageView);
        this.cirleimageView = (CircleImageView) findViewById(R.id.cirleimageView);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        if (camera == null)
            camera = new Camera(this);

        if (path.length() > 0) {

            try {
                Uri uri = Uri.parse(path);
                this.cirleimageView.setImageURI(uri);
            } catch (Exception ex) {

            }
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBottomSheetImagemCamera();
            }
        });

        cirleimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBottomSheetImagemCamera();
            }
        });

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
                cirleimageView.setImageURI(uri);
                imageView.setVisibility(View.GONE);
                cirleimageView.setVisibility(View.VISIBLE);

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
            cirleimageView.setImageURI(uri);
            imageView.setVisibility(View.GONE);
            cirleimageView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

}
