package nyc.c4q.akashaarcher.group3memestudio.LilyMeme;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import nyc.c4q.akashaarcher.group3memestudio.Model.Thumbnails;

/**
 * Created by Millochka on 1/20/17.
 */

public class LilyAdapter  extends RecyclerView.Adapter {

    private Thumbnails mLily;

    private int mWidth;

    public LilyAdapter(Thumbnails picture, int width){

        this.mLily = picture;
        this.mWidth=width;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LilyViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        LilyViewHolder lilyViewHolder=(LilyViewHolder) holder;
        lilyViewHolder.onBind(mLily);

    }

    @Override
    public int getItemCount() {

        return 8;
    }
}
