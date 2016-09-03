package in.silive.scrolls.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.silive.scrolls.R;

/**
 * Created by AKG002 on 29-08-2016.
 */
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    String[] dates;
    String[] labels;
    Context context;

    public ScheduleAdapter(Context context, String[] dates, String[] labels) {
        this.context = context;
        this.dates = dates;
        this.labels = labels;
    }

    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_schedule,parent,false));
    }

    @Override
    public void onBindViewHolder(ScheduleAdapter.ViewHolder holder, int position) {
            if (position == 0)
                holder.topStrip.setVisibility(View.INVISIBLE);
             if (position == (getItemCount()-1))
                holder.bottomStrip.setVisibility(View.INVISIBLE);
        holder.tvDate.setText(dates[position]);
        holder.tvTitle.setText(labels[position]);
    }

    @Override
    public int getItemCount() {
        return dates.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View topStrip,bottomStrip;
        TextView tvDate,tvTitle,tvSubtitle;
        public ViewHolder(View itemView) {
            super(itemView);
            topStrip = itemView.findViewById(R.id.top_strip);
            bottomStrip = itemView.findViewById(R.id.bottom_strip);
            tvDate = (TextView)itemView.findViewById(R.id.tvDate);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            tvSubtitle = (TextView)itemView.findViewById(R.id.tvSubTitle);
        }
    }
}