package in.silive.scrolls16.Adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import in.silive.scrolls16.Models.NavigationDrawerItem;
import in.silive.scrolls16.R;

/**
 * Created by akriti on 20/8/16.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.SViewHolder> {
    List<NavigationDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private int lastPosition;

    public NavigationDrawerAdapter(Context context, List<NavigationDrawerItem> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }
    class SViewHolder extends RecyclerView.ViewHolder {
        TextView label_title;
        ImageView label_image;
        public SViewHolder(View itemView) {
            super(itemView);
            label_title = (TextView) itemView.findViewById(R.id.stitle);
            label_image = (ImageView)itemView.findViewById(R.id.simage);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public NavigationDrawerAdapter.SViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.navigation_drawer_row, parent, false);
        SViewHolder holder = new SViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final NavigationDrawerAdapter.SViewHolder holder, final int position) {
        NavigationDrawerItem current = data.get(position);
        holder.label_title.setText(current.getTitle());
        holder.label_image.setImageResource(current.getImage());
        holder.itemView.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                holder.itemView.setVisibility(View.VISIBLE);
                setAnimation(holder.itemView,position);
            }
        }, (int)(100+100*position));

    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
