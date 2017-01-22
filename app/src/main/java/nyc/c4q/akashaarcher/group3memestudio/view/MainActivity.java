package nyc.c4q.akashaarcher.group3memestudio.view;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.IOException;

import nyc.c4q.akashaarcher.group3memestudio.R;
import nyc.c4q.akashaarcher.group3memestudio.customView.ColorPicker;
import nyc.c4q.akashaarcher.group3memestudio.model.ThumbnailAdapter;


public class MainActivity extends AppCompatActivity {


    private static final String FIRST_IMAGE = "passImage";
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private int PICK_IMAGE_REQUEST = 1;
    public static ColorPicker colorPicker;
    private static Bitmap mBitmap;
    private Button button;
    private static RelativeLayout mPlaceHolder;
    private static RelativeLayout mPhotoLayout;
    Bitmap bmp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,

        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        colorPicker = (ColorPicker) findViewById(R.id.tempLayout);
         colorPicker.setVisibility(View.INVISIBLE);
        recyclerView = (RecyclerView) findViewById(R.id.thumbnail_rv);
        mPhotoLayout = (RelativeLayout) findViewById(R.id.placeholder);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ThumbnailAdapter());
//        insertFragment();
    }

    public void placeInnerPhoto() {
        Drawable d23 = new BitmapDrawable(getResources(), mBitmap);
        mPlaceHolder.setBackground(d23);
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
//         //   Intent intent = new Intent();
//            intent.setType("image/*");
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
//            break;

//            case R.id.finished_btn:
//            mPlaceHolder.setDrawingCacheEnabled(true);
//            mPlaceHolder.buildDrawingCache(true);
//            Bitmap b = Bitmap.createBitmap(mPlaceHolder.getDrawingCache());
//            MediaStore.Images.Media.insertImage(getContentResolver(), b, "" , "");
//            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

                    try {
                        mBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        mPlaceHolder = (RelativeLayout) findViewById(R.id.placeholder);
                        Drawable d = new BitmapDrawable(getResources(), mBitmap);
                        mPlaceHolder.setBackground(d);
//                      ImageView imageView = (ImageView) findViewById(R.id.placeholder);
//                      imageView.setImageBitmap(mBitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static RelativeLayout getmPlaceHolder() {
        return mPlaceHolder;
    }

    public static ColorPicker getColorPicker() {
        return colorPicker;
    }


    public void insertFragment(){
       android.app.FragmentManager fm = getFragmentManager();
       FragmentTransaction ft = fm.beginTransaction();
       Fragment frag = new ColorPickerFragment();

       ft.replace(R.id.tempLayout, frag).commit();

   }


    public void showOrNotshowFragment(int booleanInt){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment frag = new ColorPickerFragment();

        if(booleanInt > 0){
            ft.add(R.id.tempLayout, frag);
        }else
            ft.hide(frag);

        ft.commit();
    }

    public static Bitmap getmBitmap() {
        return mBitmap;
    }

    public static void setmBitmap(Bitmap mBitmap) {
        MainActivity.mBitmap = mBitmap;
    }
}

