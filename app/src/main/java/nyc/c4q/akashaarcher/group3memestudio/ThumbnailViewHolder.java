package nyc.c4q.akashaarcher.group3memestudio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import nyc.c4q.akashaarcher.group3memestudio.model.Thumbnails;

/**
 * Created by akashaarcher on 1/9/17.
 */

public class ThumbnailViewHolder extends RecyclerView.ViewHolder implements ThumbnailAdapter.OnItemClickListener {

    private final View view;
    private final ImageView  memePic;
    private final TextView memeTitle;
    private Context context;


    public ThumbnailViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        view = itemView;
        memePic = (ImageView) view.findViewById(R.id.iv_meme);
        memeTitle = (TextView) view.findViewById(R.id.tv_title);
        context = parent.getContext();
    }

    private static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.thumbnail_layout, parent, false);
    }

    public ImageView getMemePic() {
        return memePic;
    }

    public TextView getMemeTitle() {
        return memeTitle;
    }

    public void bind(final Thumbnails thumbnail, final ThumbnailAdapter.OnItemClickListener listener) {
        memeTitle.setText(thumbnail.getTitle());
        Integer resource = thumbnail.getImageResource();
        Picasso.with(view.getContext())
                .load(resource)
                .resize(125, 125)
                .into(memePic);
        if (resource != null) {
            memePic.setImageResource(resource);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemSelected(thumbnail);
            }
        });
    }

    @Override
    public void onItemSelected(Thumbnails thumbnail) {
        Toast.makeText(context, thumbnail.getTitle(), Toast.LENGTH_SHORT).show();

        switch (thumbnail.getTitle()){
            case "Lily!":
                // Lily logic here
        }


    }



    /*
     /*
    Picasso.with(context).load(R.drawable.drawableName).into(imageView);

     */

}
