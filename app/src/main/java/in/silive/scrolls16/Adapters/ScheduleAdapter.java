package in.silive.scrolls16.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.fmsirvent.ParallaxEverywhere.PEWImageView;

import in.silive.scrolls16.Activities.SecondActivity;
import in.silive.scrolls16.R;

/**
 * Created by AKG002 on 29-08-2016.
 */
public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String[] dates;
    String[] labels;
    String[] days;
    Context context;
    Typeface typeface;
    SecondActivity secondActivity;
    private int lastPosition;

    public ScheduleAdapter(Context context, String[] dates, String[] labels, String[] days, SecondActivity activity) {
        this.context = context;
        this.dates = dates;
        this.labels = labels;
        this.secondActivity=activity;
        this.days = days;
    }

    @Override
    public int getItemViewType(int position) {
        return position==0?0:1;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0)
            return new ScheduleAdapter.ParallaxVH(LayoutInflater.from(context).inflate(R.layout.parallaxheader, parent, false));
        else
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.schedulerow, parent, false));
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (position ==0){
            ((ParallaxVH)holder).iv.setImageResource(R.drawable.scheduleimage);
            ((ParallaxVH)holder).tvMon.setVisibility(View.GONE);
        }
        else {
            Typeface typeface= Typeface.createFromAsset(secondActivity.getAssets(),"fonts/boldj.ttf");
            Typeface typefaces= Typeface.createFromAsset(secondActivity.getAssets(),"fonts/fonterb.ttf");
            ((ScheduleAdapter.ViewHolder)holder).tvDate.setTypeface(typeface);
            ((ScheduleAdapter.ViewHolder)holder).tvDate.setText(dates[position-1]);
            ((ScheduleAdapter.ViewHolder)holder).tvTitle.setTypeface(typefaces);
            ((ScheduleAdapter.ViewHolder)holder).tvTitle.setText(labels[position-1]);
            ((ScheduleAdapter.ViewHolder)holder).tvDay.setText(days[position-1]);
            ((ViewHolder)holder).constraintLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ((ViewHolder)holder).line.setVisibility(View.VISIBLE);
                    return false;
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return dates.length+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDate,tvTitle,tvDay;
        CardView constraintLayout;
        View line;
        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = (TextView)itemView.findViewById(R.id.tvDate);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            tvDay = (TextView)itemView.findViewById(R.id.tvWeekDay);
            constraintLayout=(CardView)itemView.findViewById(R.id.back);
             line=itemView.findViewById(R.id.line);
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