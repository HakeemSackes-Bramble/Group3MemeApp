package nyc.c4q.akashaarcher.group3memestudio;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.Arrays;
import java.util.List;

import nyc.c4q.akashaarcher.group3memestudio.LilyMeme.LilyAdapter;
import nyc.c4q.akashaarcher.group3memestudio.Model.Thumbnails;

/**
 * Created by akashaarcher on 1/9/17.
 */

public class ThumbnailAdapter extends RecyclerView.Adapter {

    private int mWidth;

    private Listener mListener;

    public ThumbnailAdapter(int width, Listener listener){

        this.mWidth=width;
        this.mListener=listener;

    }

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

                    RecyclerView mRecViewTop = new RecyclerView(view.getContext());
                    RecyclerView mRecViewBottom = new RecyclerView(view.getContext());
                    mListener.addRecView(mRecViewTop);
                    mListener.addRecView(mRecViewTop);
                    RelativeLayout.LayoutParams layoutParams1=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                    LinearLayoutManager layoutManager1
                            = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
                    mRecViewTop.setLayoutManager(layoutManager1);
                    mRecViewTop.setAdapter(new LilyAdapter(thumbnails.get(position),mWidth));
                    mRecViewTop.setLayoutParams(layoutParams1);
                    RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
                     layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    LinearLayoutManager layoutManager2
                            = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
                    mRecViewBottom.setAdapter(new LilyAdapter(thumbnails.get(position),mWidth));
                    mRecViewBottom.setLayoutManager(layoutManager2);
                    mRecViewBottom.setLayoutParams(layoutParams);
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

    public interface Listener{

        void addRecView(RecyclerView recyclerView);

    }



}
