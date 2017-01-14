package nyc.c4q.akashaarcher.group3memestudio;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
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

    Intent innerIntent;
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
            switch (thumbnails.get(position).getTitle()){

                case "Lily!":
                    ImageView myImage = new ImageView(view.getContext());
                    MainActivity.getmPlaceHolder().addView(myImage);
                    myImage.setImageResource(R.drawable.lily_thumb);
                    myImage.setScaleX((float)0.2);
                    myImage.setScaleY((float)0.2);
                    break;

                case "Demotivate":


                    break;
                case "Inner Me":
                    ImageView innerImage = new ImageView(view.getContext());
                    MainActivity.getmPlaceHolder().addView(innerImage);
                     innerIntent = new Intent(view.getContext(), InnerMeActivity.class);
                    view.getContext().startActivity(innerIntent);
            }
            }
        });



    }


    @Override
    public int getItemCount() {
        return thumbnails.size();
    }



}
