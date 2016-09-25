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

import com.fmsirvent.ParallaxEverywhere.PEWImageView;

import in.silive.scrolls16.R;

/**
 * Created by AKG002 on 29-08-2016.
 */
public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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
    public int getItemViewType(int position) {
        return position==0?0:1;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0)
            return new ScheduleAdapter.ParallaxVH(LayoutInflater.from(context).inflate(R.layout.parallax_header, parent, false));
        else
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_schedule, parent, false));
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (position ==0){
            ((ParallaxVH)holder).iv.setImageResource(R.drawable.sch_header);
            ((ParallaxVH)holder).tvMon.setVisibility(View.VISIBLE);
        }
        else {
            ((ScheduleAdapter.ViewHolder)holder).tvDate.setText(dates[position-1]);
            ((ScheduleAdapter.ViewHolder)holder).tvTitle.setText(labels[position-1]);
            ((ScheduleAdapter.ViewHolder)holder).tvDay.setText(days[position-1]);
        }
    }


    @Override
    public int getItemCount() {
        return dates.length+1;
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
    public class ParallaxVH extends RecyclerView.ViewHolder{
        PEWImageView iv;
        TextView tvMon;
        public ParallaxVH(View itemView) {
            super(itemView);
            iv = (PEWImageView) itemView.findViewById(R.id.pIVHeader);
            tvMon= (TextView)itemView.findViewById(R.id.tvMon);

        }
    }
}
