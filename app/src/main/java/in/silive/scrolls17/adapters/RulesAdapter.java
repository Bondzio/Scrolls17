package in.silive.scrolls17.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import in.silive.scrolls17.R;

/**
 * Created by AKG002
 * thats all.
 */
public class RulesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    String[] topics;
    Context context;
    private int lastPosition;

    public RulesAdapter(Context context, String[] topics) {
        this.context = context;
        this.topics = topics;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            return new RulesAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_num_list,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

             //Toast.makeText(context,Integer.toString(topics.length),Toast.LENGTH_LONG).show();

            ((RulesAdapter.ViewHolder) holder).tvTopic.setText(topics[position]);
        ((ViewHolder) holder).back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                    ((ViewHolder) holder).line.setVisibility(View.VISIBLE);
                    // Do what you want


                return false;
            }
        });

          //  ((RulesAdapter.ViewHolder)holder).itemView.setVisibility(View.INVISIBLE);


    }

   /* @Override
    public int getItemViewType(int position) {
        return position==0?0:1;
    }*/

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
        return topics.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTopic;
        TextView tvNum;
        CardView back;
        View line;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTopic = (TextView)itemView.findViewById(R.id.tv);
            back=(CardView)itemView.findViewById(R.id.back);
            line=(View)itemView.findViewById(R.id.line);
           // tvNum = (TextView)itemView.findViewById(R.id.tvNum);
        }
    }
   /* public class ParallaxVH extends RecyclerView.ViewHolder{
        PEWImageView iv;
        public ParallaxVH(View itemView) {
            super(itemView);
          //  iv = (PEWImageView) itemView.findViewById(R.id.pIVHeader);

        }
    }*/
}
