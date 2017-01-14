package nyc.c4q.akashaarcher.group3memestudio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wesniemarcelin on 1/13/17.
 */

public class InnerMeActivity extends AppCompatActivity {
    TextView innerTitle;
    TextView innerSubHeading;
    ImageView photoView;
    Button selectPhotos;
    Button saveNewPhoto;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inner_me_frag_layout);
        innerTitle = (TextView)findViewById(R.id.inner_tv);
        innerSubHeading = (TextView) findViewById(R.id.inner_sub_head);
        photoView = (ImageView) findViewById(R.id.inner_iv);
        selectPhotos = (Button) findViewById(R.id.inner_gallery_btn);
        saveNewPhoto = (Button) findViewById(R.id.save_photo);


        selectPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);
            }
        });
    }
}
