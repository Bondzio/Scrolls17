package in.silive.scrolls_16.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import in.silive.scrolls_16.Models.NavigationDrawerItem;
import in.silive.scrolls_16.R;

/**
 * Created by akriti on 20/8/16.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.SViewHolder> {
    List<NavigationDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

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
    public void onBindViewHolder(NavigationDrawerAdapter.SViewHolder holder, int position) {
        NavigationDrawerItem current = data.get(position);
        holder.label_title.setText(current.getTitle());
        holder.label_image.setImageResource(current.getImage());
    }
}
