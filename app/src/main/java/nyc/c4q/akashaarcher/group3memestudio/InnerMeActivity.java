package nyc.c4q.akashaarcher.group3memestudio;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by wesniemarcelin on 1/13/17.
 */

public class InnerMeActivity extends AppCompatActivity {
    private int PICK_IMAGE_REQUEST = 2;
    TextView innerTitle;
    TextView innerSubHeading;
    ImageView photoView;
    Button selectPhotos;
    Button saveNewPhoto;
    private Bitmap mBitmap;
    private Bitmap mBitMap2;
    private static LinearLayout mPlaceHolder;
    private static LinearLayout mSecondImage;
    private Bundle mIntent;
    private static final String FIRST_IMAGE ="getFirstImage" ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inner_me_frag_layout);
        innerTitle = (TextView) findViewById(R.id.inner_tv);
        innerSubHeading = (TextView) findViewById(R.id.inner_sub_head);
        photoView = (ImageView) findViewById(R.id.inner_iv);
        selectPhotos = (Button) findViewById(R.id.inner_gallery_btn);
        saveNewPhoto = (Button) findViewById(R.id.save_photo);
        mIntent = getIntent().getExtras();
        mBitMap2 = MainActivity.getmBitmap();
        mPlaceHolder = (LinearLayout) findViewById(R.id.inner_image_two);
        Drawable d2 = new BitmapDrawable(getResources(), mBitMap2);
        Log.d("Drawables", d2.toString());

        mPlaceHolder.setBackground(d2);


        selectPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();


            try {
                mBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                mSecondImage = (LinearLayout) findViewById(R.id.inner_image_one);

                Drawable d = new BitmapDrawable(getResources(), mBitmap);
                Log.d("Placeholder", "new placeholder processed");
                mSecondImage.setBackground(d);

                Log.d("Last one", "Success!");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static LinearLayout getmPlaceHolder() {
        return mPlaceHolder;
    }


    public void selectSaveFromGallery(View view) {

        switch (view.getId()) {
            case R.id.gallery_btn:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                break;
            case R.id.finished_btn:

                mPlaceHolder.setDrawingCacheEnabled(true);
                mPlaceHolder.buildDrawingCache(true);

                Bitmap b = Bitmap.createBitmap(mPlaceHolder.getDrawingCache());
                MediaStore.Images.Media.insertImage(getContentResolver(), b, "", "");
                break;
        }

    }

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}
