package nyc.c4q.akashaarcher.group3memestudio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import nyc.c4q.akashaarcher.group3memestudio.model.Thumbnails;

/**
 * Created by akashaarcher on 1/9/17.
 */

public class ThumbnailAdapter extends RecyclerView.Adapter {


    public static String getFirstImage() {
        return FIRST_IMAGE;
    }

    private static final String FIRST_IMAGE = "getFirstImage";

    Intent innerIntent;
    private String TAG = "Adapter";
    private ImageView ivHoneyBun;

    Context context;
    private List<Thumbnails> thumbnails = Arrays.asList(
            new Thumbnails(R.drawable.demotivation_thumb, "Demotivate"),
            new Thumbnails(R.drawable.honey_bun_thumb, "Honey Bun"),
            new Thumbnails(R.drawable.inner_me_thumb, "Inner Me"),
            new Thumbnails(R.drawable.ms_paint_thumb, "Painting"),
            new Thumbnails(R.drawable.lily_thumb, "Lily!")
    );

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThumbnailViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ThumbnailViewHolder viewHolder = (ThumbnailViewHolder) holder;
        final Context context = holder.itemView.getContext();
        final Thumbnails thumbnail = thumbnails.get(position);
        viewHolder.bind(thumbnail);
        viewHolder.getMemePic().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (thumbnails.get(position).getTitle()) {

                    case "Honey Bun":
                        Intent honeyBunIntent = new Intent(view.getContext(), HoneyBunActivity.class);
                        view.getContext().startActivity(honeyBunIntent);
                        break;

                    case "Lily!":
                        ImageView myImage = new ImageView(view.getContext());
                        MainActivity.getmPlaceHolder().addView(myImage);
                        myImage.setImageResource(R.drawable.lily_thumb);
                        myImage.setScaleX((float) 0.2);
                        myImage.setScaleY((float) 0.2);
                        break;

                    case "Demotivate":

                        break;
                    case "Inner Me":
                        innerIntent = new Intent(context, InnerMeActivity.class);
                        ((Activity) context).startActivityForResult(innerIntent, 6);
                        break;
                }
            }
    }

    );
}


    @Override
    public int getItemCount() {
        return thumbnails.size();
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }


}
