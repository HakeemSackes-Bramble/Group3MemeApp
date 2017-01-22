package nyc.c4q.akashaarcher.group3memestudio;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

import nyc.c4q.akashaarcher.group3memestudio.view.MainActivity;

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
    private Bitmap mBitmapTwo;
    private Bitmap mBitMapOne;
    private static Bitmap mInnerBitmap;
    private static LinearLayout mPlaceHolder;
    private static LinearLayout mSecondImage;
    private static LinearLayout mInnerPhoto;
    private static LinearLayout innerTextBack;
    private static LinearLayout mPhotoBackground;
    private static EditText secondText;
    private static EditText firstText;
    private ShareActionProvider mShareActionProvider;
    private Bundle mIntent;
    private static final String FIRST_IMAGE = "getFirstImage";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inner_me_frag_layout);
        innerTitle = (TextView) findViewById(R.id.inner_tv);
        innerSubHeading = (TextView) findViewById(R.id.inner_sub_head);
        selectPhotos = (Button) findViewById(R.id.inner_gallery_btn);
        saveNewPhoto = (Button) findViewById(R.id.finished_btn);
        mIntent = getIntent().getExtras();
        mBitMapOne = MainActivity.getmBitmap();
        mPlaceHolder = (LinearLayout) findViewById(R.id.inner_image_two);
        mInnerPhoto = (LinearLayout) findViewById(R.id.inner_image_one);
        mPhotoBackground = (LinearLayout) findViewById(R.id.inner_photo);
        innerTextBack = (LinearLayout) findViewById(R.id.inner_text_bacnkground);
        secondText = (EditText) findViewById(R.id.second_pic_text);
        firstText = (EditText) findViewById(R.id.first_pic_text);

        Drawable d1 = new BitmapDrawable(getResources(), mBitMapOne);
        Log.d("Drawables", d1.toString());

        mInnerPhoto.setBackground(d1);


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
                mBitmapTwo = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                mSecondImage = (LinearLayout) findViewById(R.id.inner_image_two);
                mSecondImage.setVisibility(View.VISIBLE);

                Drawable d2 = new BitmapDrawable(getResources(), mBitmapTwo);
                Log.d("Placeholder", "new placeholder processed");
                d2.setColorFilter(Color.parseColor("grey"), PorterDuff.Mode.DARKEN);
                mSecondImage.setBackground(d2);
                firstText.setVisibility(View.VISIBLE);
                secondText.setVisibility(View.VISIBLE);
                innerTextBack.setBackgroundColor(Color.parseColor("white"));
                firstText.setTextColor(Color.parseColor("black"));
                secondText.setTextColor(Color.parseColor("black"));
                innerTextBack.setVisibility(View.VISIBLE);


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

                mPhotoBackground.setDrawingCacheEnabled(true);
                mPhotoBackground.buildDrawingCache(true);

                Bitmap b = Bitmap.createBitmap(mPhotoBackground.getDrawingCache());
                Log.d("Bitmap created: ", b.toString());


                MediaStore.Images.Media.insertImage(getContentResolver(), b, "", "");
                break;
        }
    }

    public static Bitmap getmBitmap() {
        return mInnerBitmap;
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

}
