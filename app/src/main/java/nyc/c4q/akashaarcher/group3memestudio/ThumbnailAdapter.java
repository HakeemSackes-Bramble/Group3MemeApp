package nyc.c4q.akashaarcher.group3memestudio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import nyc.c4q.akashaarcher.group3memestudio.LilyMeme.LilyAdapter;
import nyc.c4q.akashaarcher.group3memestudio.Model.Thumbnails;

/**
 * Created by akashaarcher on 1/9/17.
 */

public class ThumbnailAdapter extends RecyclerView.Adapter {

    private int mWidth;
    private int mHigth;

    private Listener mListener;

    public static String getFirstImage() {
        return FIRST_IMAGE;
    }

    private static final String FIRST_IMAGE = "getFirstImage";

    Intent innerIntent;
    private String TAG = "Adapter";
    private ImageView ivHoneyBun;

    Context context;

    public ThumbnailAdapter(int width, int hight, Listener listener){

        if(mHigth>mWidth){
            this.mWidth=hight;

        }else {
            this.mWidth=width;

        }



        this.mListener=listener;

    }

    public static List<Thumbnails> getThumbnails() {
        return thumbnails;
    }

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ThumbnailViewHolder viewHolder = (ThumbnailViewHolder) holder;
        final Context context = holder.itemView.getContext();
        final Thumbnails thumbnail = thumbnails.get(position);
        viewHolder.bind(thumbnail);
        viewHolder.getMemePic().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (thumbnails.get(position).getTitle()) {

                case "Lily!":

                    RecyclerView mRecViewTop = new RecyclerView(view.getContext());
                    RecyclerView mRecViewBottom = new RecyclerView(view.getContext());
                    RecyclerView mRecViewLeft = new RecyclerView(view.getContext());
                    RecyclerView mRecViewRight = new RecyclerView(view.getContext());

                    mListener.addRecView(mRecViewTop);
                    mListener.addRecView(mRecViewBottom);
                    mListener.addRecView(mRecViewLeft);
                    mListener.addRecView(mRecViewRight);


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
                    RelativeLayout.LayoutParams layoutParams3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams3.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    layoutParams3.addRule(RelativeLayout.BELOW,mRecViewTop.getId());
                    LinearLayoutManager layoutManager3= new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
                    mRecViewLeft.setAdapter(new LilyAdapter(thumbnails.get(position),mWidth));
                    mRecViewLeft.setLayoutManager(layoutManager3);
                    mRecViewLeft.setLayoutParams(layoutParams3);

                    RelativeLayout.LayoutParams layoutParams4=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    layoutParams4.addRule(RelativeLayout.BELOW,mRecViewTop.getId());
                    LinearLayoutManager layoutManager4= new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
                    mRecViewRight.setAdapter(new LilyAdapter(thumbnails.get(position),mWidth));
                    mRecViewRight.setLayoutManager(layoutManager4);
                    mRecViewRight.setLayoutParams(layoutParams4);


                    break;

                    case "Demotivate":


                    break;
                    case "Honey Bun":
                        mListener.addHoneyBun();
//
//                        Intent honeyBunIntent = new Intent(view.getContext(), HoneyBunActivity.class);
//                        view.getContext().startActivity(honeyBunIntent);
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

    public interface Listener{
        void addHoneyBun();

        void addRecView(RecyclerView recyclerView);

    }


    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }


}
