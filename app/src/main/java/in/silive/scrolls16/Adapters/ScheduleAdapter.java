package in.silive.scrolls16.Adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import in.silive.scrolls16.R;

/**
 * Created by AKG002 on 29-08-2016.
 */
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    String[] dates;
    String[] labels;
    String[] days;
    Context context;
    private int lastPosition;

    public ScheduleAdapter(Context context, String[] dates, String[] labels,String[] days) {
        this.context = context;
        this.dates = dates;
        this.labels = labels;
        this.days = days;
    }

    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_schedule,parent,false));
    }

    @Override
    public void onBindViewHolder(final ScheduleAdapter.ViewHolder holder, final int position) {

        holder.tvDate.setText(dates[position]);
        holder.tvTitle.setText(labels[position]);
        holder.tvDay.setText(days[position]);
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

    @Override
    public int getItemCount() {
        return dates.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDate,tvTitle,tvDay;
        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = (TextView)itemView.findViewById(R.id.tvDate);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            tvDay = (TextView)itemView.findViewById(R.id.tvWeekDay);
        }
    }
}
