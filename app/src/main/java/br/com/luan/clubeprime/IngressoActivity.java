package br.com.luan.clubeprime;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.Map;

public class IngressoActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private ImageView foto;
    private TextView title;
    private LinearLayout linearLayout2;
    private ImageView imageView8;
    private TextView data;
    private ImageView imageView9;
    private TextView local;
    private ImageView imageView10;
    private TextView hora;
    private ImageView barcode;
    private TextView code;
    private android.support.v7.widget.CardView view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresso);
        this.view2 = (CardView) findViewById(R.id.view2);
        this.code = (TextView) findViewById(R.id.code);
        this.barcode = (ImageView) findViewById(R.id.barcode);
        this.hora = (TextView) findViewById(R.id.hora);
        this.imageView10 = (ImageView) findViewById(R.id.imageView10);
        this.local = (TextView) findViewById(R.id.local);
        this.imageView9 = (ImageView) findViewById(R.id.imageView9);
        this.data = (TextView) findViewById(R.id.data);
        this.imageView8 = (ImageView) findViewById(R.id.imageView8);
        this.linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        this.title = (TextView) findViewById(R.id.title);
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

        // barcode data
        String barcode_data = "12345678901234567890";

        // barcode image
        Bitmap bitmap = null;

        try {

            bitmap = encodeAsBitmap(barcode_data, BarcodeFormat.CODE_128, 510, 100);
            barcode.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }


        //barcode text

        code.setGravity(Gravity.CENTER_HORIZONTAL);
        code.setText(barcode_data);


    }


    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    public Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException {
        String contentsToEncode = contents;
        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contentsToEncode, format, img_width, img_height, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    public static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }



}
