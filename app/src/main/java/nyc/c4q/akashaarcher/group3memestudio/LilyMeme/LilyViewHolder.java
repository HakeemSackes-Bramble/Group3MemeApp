package nyc.c4q.akashaarcher.group3memestudio.LilyMeme;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import nyc.c4q.akashaarcher.group3memestudio.R;
import nyc.c4q.akashaarcher.group3memestudio.Model.Thumbnails;

/**
 * Created by Millochka on 1/20/17.
 */
public class LilyViewHolder extends RecyclerView.ViewHolder {

    View mView;
    ImageView mLily;
    public LilyViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        mView=itemView;
    }

    public static View inflateView(ViewGroup parent){

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());

        return inflater.inflate(R.layout.lily_frame,parent,false);

    }

    public void onBind(Thumbnails Lily){

        mLily=(ImageView) mView.findViewById(R.id.lily_picture);
        mLily.setImageResource(Lily.getImageResource());



    }
}
