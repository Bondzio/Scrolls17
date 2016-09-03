package in.silive.scrolls.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.silive.scrolls.R;

/**
 * Created by AKG002 on 03-09-2016.
 */
public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder>{
    String[] topics;
    Context context;

    public TopicsAdapter(Context context, String[] topics) {
        this.context = context;
        this.topics = topics;
    }

    @Override
    public TopicsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_topic,parent,false));
    }

    @Override
    public void onBindViewHolder(TopicsAdapter.ViewHolder holder, int position) {
        holder.tvTopic.setText(topics[position]);
    }

    @Override
    public int getItemCount() {
        return topics.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTopic;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTopic = (TextView)itemView.findViewById(R.id.tvTopic);
        }
    }
}
