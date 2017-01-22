package nyc.c4q.akashaarcher.group3memestudio;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

/**
 * Created by akashaarcher on 1/20/17.
 */

public class HoneyBunActivity extends Activity {


    Context context;

    private String TAG = "HoneyBunActivity";

    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap mBitmap;
    private static LinearLayout mHoneyMemePlaceHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honey_bun);
        ActivityCompat.requestPermissions(HoneyBunActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

//        Drawable memeImg = MainActivity.getmPlaceHolder().getBackground();

    }


    public void selectSaveFromGallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

                Uri selectedImageURI = data.getData();

                Picasso.with(HoneyBunActivity.this).load(selectedImageURI).noPlaceholder().centerCrop().fit()
                        .into((ImageView) findViewById(R.id.honey_bun_img));
            }

        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//
//            Uri uri = data.getData();
//
//            try {
//                mBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//
//                mHoneyMemePlaceHolder = (LinearLayout) findViewById(R.id.honey_meme_placeholder);
//
//                //  ImageView honeyBunImg = (ImageView) findViewById(R.id.honey_bun_img);
//                //   honeyBunImg.setImageResource(imgSrc);
//
//
//                Drawable imgSrc = new BitmapDrawable(getResources(), mBitmap);
//                mHoneyMemePlaceHolder.setBackground(imgSrc);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static LinearLayout getmHoneyBunPlaceHolder() {
        return mHoneyMemePlaceHolder;
    }


}
