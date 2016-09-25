package in.silive.scrolls16.Adapters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fmsirvent.ParallaxEverywhere.PEWImageView;

import in.silive.scrolls16.Fragments.DialogTopics;
import in.silive.scrolls16.R;

/**
 * Created by AKG002 on 03-09-2016.
 */
public class DomainsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String[] domains;
    Context context;
    String[] images;

    public DomainsAdapter(Context context, String[] domains,String[] images) {
        this.context = context;
        this.domains = domains;
        this.images = images;
    }

    @Override
    public int getItemViewType(int position) {
        return position==0?0:1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType ==0)
            return new DomainsAdapter.ParallaxVH(LayoutInflater.from(context).inflate(R.layout.parallax_header,parent,false));
        else
        return new DomainsAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_domain, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (position ==0){
            ((ParallaxVH)holder).iv.setImageResource(R.drawable.topics_header);
        }
        else {
            ((DomainsAdapter.ViewHolder) holder).tvDomain.setText(domains[position-1]);
            ((DomainsAdapter.ViewHolder) holder).iv.setImageResource(context.getResources().getIdentifier(images[position-1], "drawable", context.getPackageName()));
            ((DomainsAdapter.ViewHolder) holder).ll.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    DialogTopics dialogTopics = new DialogTopics();
                    switch (position - 1) {
                        case 1:
                            dialogTopics.setArgs("Computer Science and Information Technology",
                                    context.getResources().getStringArray(R.array.csit));
                            break;
                        case 2:
                            dialogTopics.setArgs("Electronics and Communication", context.getResources().getStringArray(R.array.ec));
                            break;
                        case 3:
                            dialogTopics.setArgs("Electrical and Electronics", context.getResources().getStringArray(R.array.el));
                            break;
                        case 0:
                            dialogTopics.setArgs("Management Science", context.getResources().getStringArray(R.array.ms));
                            break;
                        case 4:
                            dialogTopics.setArgs("Mechanical Engineering", context.getResources().getStringArray(R.array.me));
                            break;
                        case 5:
                            dialogTopics.setArgs("Civil Engineering", context.getResources().getStringArray(R.array.ce));
                            break;
                    }
                    dialogTopics.show(((AppCompatActivity) context).getSupportFragmentManager(), null);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return domains.length+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDomain;
        RelativeLayout ll;
        ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            ll = (RelativeLayout) itemView.findViewById(R.id.ll);
            tvDomain = (TextView) itemView.findViewById(R.id.tvDomain);
            iv = (ImageView)itemView.findViewById(R.id.iv);
        }
    }
    public class ParallaxVH extends RecyclerView.ViewHolder{
        PEWImageView iv;
        public ParallaxVH(View itemView) {
            super(itemView);
            iv = (PEWImageView) itemView.findViewById(R.id.pIVHeader);

        }
    }
}
