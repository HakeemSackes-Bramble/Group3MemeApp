package nyc.c4q.akashaarcher.group3memestudio;

import android.Manifest;
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
import android.widget.RelativeLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements ThumbnailAdapter.Listener {


    private Context mContext;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap mBitmap;
    private  RelativeLayout mPlaceHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        mPlaceHolder=(RelativeLayout) findViewById(R.id.placeholder);
        recyclerView = (RecyclerView) findViewById(R.id.thumbnail_rv);
        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


    }

    public void selectSaveFromGallery(View view){

        switch (view.getId()){
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
                MediaStore.Images.Media.insertImage(getContentResolver(), b, "" , "");
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
                Uri imageUri =  Uri.parse(path);
                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                shareIntent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareIntent, "Share image using"));


                break;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView.setAdapter(new ThumbnailAdapter(recyclerView.getWidth(),this));
    }

            @Override
            protected void onActivityResult ( int requestCode, int resultCode, Intent data){
                super.onActivityResult(requestCode, resultCode, data);

                if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

                    Uri uri = data.getData();

                    try {
                        mBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);


                        Drawable d = new BitmapDrawable(getResources(), mBitmap);
                        mPlaceHolder.setBackground(d);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }



    @Override
    public void addRecView(RecyclerView recyclerView) {

        mPlaceHolder.addView(recyclerView);

    }
}

