package nyc.c4q.akashaarcher.group3memestudio;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;

import nyc.c4q.akashaarcher.group3memestudio.model.Thumbnails;

/**
 * Created by akashaarcher on 1/9/17.
 */

public class ThumbnailAdapter extends RecyclerView.Adapter {

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
        final Thumbnails thumbnail = thumbnails.get(position);
        viewHolder.bind(thumbnail);
        viewHolder.getMemePic().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (thumbnails.get(position).getTitle()) {

                    case "Honey Bun":
//                        final LinearLayout memeLayout = (LinearLayout) view.findViewById(R.id.placeholder);
////                        memeLayout.setPadding(10, 30, 10, 10);
//
//
////                        getmPlaceHolder().setBackgroundColor(0xFFFF00);
////                       getmPlaceHolder().bringToFront();
//                        int currWidth = getmPlaceHolder().getLayoutParams().width;
//                        int currHeight = getmPlaceHolder().getLayoutParams().height;
//
//                        int width = memeImg.getIntrinsicWidth();
//                        int height = memeImg.getIntrinsicHeight();
//
//                        Log.i(TAG, "dimensions " + width + " and " + height);
//
                        Drawable memeImg = MainActivity.getmPlaceHolder().getBackground();
                        int width = memeImg.getIntrinsicWidth();
                        int height = memeImg.getIntrinsicHeight();
                        Log.i(TAG, "dimensions " + width + " and " + height);


                        ImageView memeImage = new ImageView(view.getContext());
                        MainActivity.getmPlaceHolder().setVisibility(View.GONE);
                        MainActivity.getmPlaceHolder().addView(memeImage);
                        memeImage.setImageResource(R.drawable.honey_bun_thumb);
                        memeImage.setScaleX((float)0.2);
                        memeImage.setScaleY((float)0.2);


//                        ivHoneyBun = (ImageView) view.findViewById(R.id.honeybun_img);
////
//                        Picasso.with(view.getContext())
//                                .load(String.valueOf(memeImg))
//                                .resize(125, 125)
//                                .into(ivHoneyBun);



                        //   Drawable memeImg = MainActivity.getmPlaceHolder().getBackground();
//                        Bitmap bitmap = ((BitmapDrawable) memeImg).getBitmap();
//                        Drawable draw = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 250, 250, true));
//                        MainActivity.getmPlaceHolder().setBackground(draw);


//                        params.height = newHeight;
//                        params.width=newWidth;
//                       memeImg.setLayoutParams(params);
//                        ImageView memeImg = new ImageView(view.getContext());
//                        memeImg.getLayoutParams().width = newWidth;
//                        memeImg.getLayoutParams().height = newHeight;
//                        memeImg.setScaleType(ImageView.ScaleType.FIT_XY);

                        break;

                    case "Lily!":
                        ImageView myImage = new ImageView(view.getContext());
                        MainActivity.getmPlaceHolder().addView(myImage);
                        myImage.setImageResource(R.drawable.lily_thumb);
                        myImage.setScaleX((float)0.2);
                        myImage.setScaleY((float)0.2);
                        break;


                    case "Demotivate":


                        break;
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return thumbnails.size();
    }


}
