package nyc.c4q.akashaarcher.group3memestudio;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.akashaarcher.group3memestudio.model.Thumbnails;

/**
 * Created by akashaarcher on 1/9/17.
 */

public class ThumbnailViewHolder extends RecyclerView.ViewHolder {

    private final View view;
    private final ImageView  memePic;
    private final TextView memeTitle;


    public ThumbnailViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        view = itemView;
        memePic = (ImageView) view.findViewById(R.id.iv_meme);
        memeTitle = (TextView) view.findViewById(R.id.tv_title);
    }

    private static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.thumbnail_layout, parent, false);
    }

    public void bind(Thumbnails thumbnail) {
        memeTitle.setText(thumbnail.getTitle());
        Integer resource = thumbnail.getImageResource();
        Picasso.with(view.getContext())
                .load(resource)
                .resize(125, 125)
                .into(memePic);
        if (resource != null) {
            memePic.setImageResource(resource);
        }
    }


    /*
     /*
    Picasso.with(context).load(R.drawable.drawableName).into(imageView);

     */

}
