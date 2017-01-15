package nyc.c4q.akashaarcher.group3memestudio;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
    private int PICK_IMAGE_REQUEST = 1;
    TextView innerTitle;
    TextView innerSubHeading;
    ImageView photoView;
    Button selectPhotos;
    Button saveNewPhoto;
    private Bitmap mBitmap;
    private Bitmap mBitMap2;
    private static LinearLayout mPlaceHolder;
    private static LinearLayout mSecondImage;
    private String[] imagesPath;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inner_me_frag_layout);
        innerTitle = (TextView) findViewById(R.id.inner_tv);
        innerSubHeading = (TextView) findViewById(R.id.inner_sub_head);
        photoView = (ImageView) findViewById(R.id.inner_iv);
        selectPhotos = (Button) findViewById(R.id.inner_gallery_btn);
        saveNewPhoto = (Button) findViewById(R.id.save_photo);
        imagesPath=new String[2];


        selectPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        data= getIntent();
        ClipData clipdata = data.getClipData();

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
//            for (int i = 0; i < clipdata.getItemCount(); i++) {
//                ClipData.Item item = clipdata.getItemAt(i);
//                Uri uri2 = item.getUri();
//                String path = getRealPathFromURI(getApplicationContext(), uri);
//
//            }
//            if (requestCode == PICK_IMAGE_REQUEST) {
                imagesPath = data.getStringExtra("data").split("\\|");
                try {
                    mBitMap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(imagesPath[0]));
                    mBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(imagesPath[1]));

                    mPlaceHolder = (LinearLayout) findViewById(R.id.inner_image_one);
                    mSecondImage = (LinearLayout) findViewById(R.id.inner_image_two);
                    Drawable d = new BitmapDrawable(getResources(), mBitmap);
                    Drawable d2 = new BitmapDrawable(getResources(), mBitMap2);
                    mPlaceHolder.setBackground(d);
                    mSecondImage.setBackground(d2);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
//            try {
//                mBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//
////                        ImageView imageView = (ImageView) findViewById(R.id.placeholder);
////                        imageView.setImageBitmap(mBitmap);
//                mPlaceHolder = (LinearLayout) findViewById(R.id.inner_image_one);
//                mSecondImage = (LinearLayout) findViewById(R.id.inner_image_two);
//                Drawable d = new BitmapDrawable(getResources(), mBitmap);
//                mPlaceHolder.setBackground(d);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
//    }

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

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null,
                    null, null);
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
