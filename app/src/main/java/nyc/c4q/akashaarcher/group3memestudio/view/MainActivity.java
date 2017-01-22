package nyc.c4q.akashaarcher.group3memestudio.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import nyc.c4q.akashaarcher.group3memestudio.R;
import nyc.c4q.akashaarcher.group3memestudio.ThumbnailAdapter;
import nyc.c4q.akashaarcher.group3memestudio.customView.ColorPicker;

public class MainActivity extends AppCompatActivity implements ThumbnailAdapter.Listener {


    private static final String FIRST_IMAGE = "passImage";
    private Context mContext;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private int PICK_IMAGE_REQUEST = 1;
    public static ColorPicker colorPicker;
    private static Bitmap mBitmap;
    private Button button;
    private static RelativeLayout mPlaceHolder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,

                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        colorPicker = (ColorPicker) findViewById(R.id.tempLayout);
        colorPicker.setVisibility(View.INVISIBLE);
        recyclerView = (RecyclerView) findViewById(R.id.thumbnail_rv);
        mPlaceHolder = (RelativeLayout) findViewById(R.id.placeholder);
        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ThumbnailAdapter(mPlaceHolder.getWidth(), this));



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


                MediaStore.Images.Media.insertImage(getContentResolver(), getBitmapFromView(mPlaceHolder), "", "");
                break;

            case R.id.share_btn:

                Intent shareIntent = new Intent();
                mPlaceHolder.setDrawingCacheEnabled(true);
                mPlaceHolder.buildDrawingCache(true);

                Bitmap bitmap = Bitmap.createBitmap(mPlaceHolder.getDrawingCache());
                shareIntent.setAction(Intent.ACTION_SEND);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(getContentResolver(),
                        bitmap, "Title", null);
                Uri imageUri = Uri.parse(path);
                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                shareIntent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareIntent, "Share image using"));


                break;
        }
    }



    @Override
    protected void onStart() {
        super.onStart();

        //recyclerView.setAdapter(new ThumbnailAdapter(mPlaceHolder.getWidth(), this));

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                mBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                Drawable d = new BitmapDrawable(getResources(), mBitmap);

                mPlaceHolder.setBackground(d);
                mPlaceHolder.removeAllViews();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    @Override
    public void addHoneyBun() {
        mPlaceHolder.setDrawingCacheEnabled(true);
        mPlaceHolder.buildDrawingCache(true);

        Bitmap b = Bitmap.createBitmap(mPlaceHolder.getDrawingCache());
        Drawable d = new BitmapDrawable(getResources(), b);

        ImageView picture = new ImageView(this);
        EditText textView = new EditText(this);

        Toast.makeText(this,"It's working", Toast.LENGTH_LONG).show();

        RelativeLayout.LayoutParams layoutParams1=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                300);
        layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        textView.setLayoutParams(layoutParams1);
        mPlaceHolder.addView(textView);

        textView.setTextColor(Color.parseColor("black"));
        textView.setTextSize(30);
        textView.getBackground().setColorFilter(Color.parseColor("white"), PorterDuff.Mode.SRC_IN);
        try {
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(textView, R.drawable.cursor_color);
        } catch (Exception ignored) {
        }

        textView.setHint("Please type your text here");
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(mPlaceHolder.getWidth()*2,
                mPlaceHolder.getHeight()-300);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        picture.setLayoutParams(layoutParams);
        mPlaceHolder.addView(picture);

        picture.setImageDrawable(d);
        mPlaceHolder.setBackgroundColor(Color.parseColor("white"));

    }

    @Override
    public void addRecView(RecyclerView recyclerView) {
        mPlaceHolder.addView(recyclerView);

    }




    public static RelativeLayout getmPlaceHolder() {
        return mPlaceHolder;
    }

    public static ColorPicker getColorPicker() {
        return colorPicker;
    }


    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }


    public static Bitmap getmBitmap() {
        return mBitmap;
    }

}

