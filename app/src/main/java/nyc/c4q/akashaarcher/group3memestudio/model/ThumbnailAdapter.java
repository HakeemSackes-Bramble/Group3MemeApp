package nyc.c4q.akashaarcher.group3memestudio.model;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import nyc.c4q.akashaarcher.group3memestudio.R;

/**
 * Created by akashaarcher on 1/9/17.
 */

public class ThumbnailAdapter extends RecyclerView.Adapter {


    private static List<Thumbnails> thumbnails = Arrays.asList(
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ThumbnailViewHolder viewHolder = (ThumbnailViewHolder) holder;
        Thumbnails thumbnail = thumbnails.get(position);
        viewHolder.bind(thumbnail);

    }

    @Override
    public int getItemCount() {
        return thumbnails.size();
    }

    public static List<Thumbnails> getThumbnails() {
        return thumbnails;
    }

}
