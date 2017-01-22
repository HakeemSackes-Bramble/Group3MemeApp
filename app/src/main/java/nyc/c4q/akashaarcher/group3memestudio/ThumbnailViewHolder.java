package nyc.c4q.akashaarcher.group3memestudio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.akashaarcher.group3memestudio.Model.ItemSelected;
import nyc.c4q.akashaarcher.group3memestudio.Model.Thumbnails;
import nyc.c4q.akashaarcher.group3memestudio.view.MainActivity;

/**
 * Created by akashaarcher on 1/9/17.
 */

public class ThumbnailViewHolder extends RecyclerView.ViewHolder implements ItemSelected {

    private static final String TAG = "DEBUG TOOL";
    private final View view;
    private final ImageView  memePic;
    private final TextView memeTitle;
    private Context context;

    public interface OnItemClickListener {
        void onItemSelected(Thumbnails thumbnail);
    }


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

    public void bind(final Thumbnails thumbnail) {
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

        switch (thumbnail.getTitle()){
            case "Demotivate":
                break;
            case "Honey Bun":
                break;
            case "Inner Me":
                break;
            case "Painting":
                thumbnail.setIsActiveFilter(thumbnail.getIsActiveFilter()*-1);
                if(thumbnail.getIsActiveFilter() > 0) {
                    MainActivity.getColorPicker().setVisibility(View.VISIBLE);
                }else {
                    MainActivity.getColorPicker().setVisibility(View.INVISIBLE);
                }
                break;
            case "Lily!":
                ImageView myImage = new ImageView(view.getContext());
                MainActivity.getmPlaceHolder().addView(myImage);
                myImage.setImageResource(R.drawable.lily_thumb);
                myImage.setScaleX((float)0.2);
                myImage.setScaleY((float)0.2);
                break;
        }
    }




}
