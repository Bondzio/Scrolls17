package in.silive.scrolls17.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.silive.scrolls17.activities.SecondActivity;
import in.silive.scrolls17.fragments.DialogTopics;
import in.silive.scrolls17.R;


/**
 * Created by AKG002 on 03-09-2016.
 */
public class DomainsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String[] domains;
    Context context;
    String[] images;
    SecondActivity secondActivity;
//    ExplosionField explosionField;
    public DomainsAdapter(Context context, String[] domains, String[] images, SecondActivity Activity) {
        this.context = context;
        this.domains = domains;
        this.images = images;
        this.secondActivity=Activity;
    }

    @Override
    public int getItemViewType(int position) {
        return position==0?0:1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new DomainsAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.topicsrow, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        // ((DomainsAdapter.ViewHolder) holder).tvDomain.setText(domains[position]);
        ((DomainsAdapter.ViewHolder) holder).iv.setBackgroundResource(context.getResources().getIdentifier(images[position], "drawable", context.getPackageName()));
        ((DomainsAdapter.ViewHolder) holder).ll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DialogTopics dialogTopics = new DialogTopics();
                switch (position ) {
                    case 1:
                        //explosionField=ExplosionField.attach2Window(secondActivity);
                        //explosionField.explode(((DomainsAdapter.ViewHolder) holder).iv);
                        dialogTopics.setArgs("Computer Science and Engineering", context.getResources().getStringArray(R.array.csit));
                        break;
                    case 2:
                        //explosionField=ExplosionField.attach2Window(secondActivity);
                        //explosionField.explode(((DomainsAdapter.ViewHolder) holder).iv);
                        dialogTopics.setArgs("Electronics and Communication", context.getResources().getStringArray(R.array.ec));
                        break;
                    case 3:
                        //explosionField=ExplosionField.attach2Window(secondActivity);
                        //explosionField.explode(((DomainsAdapter.ViewHolder) holder).iv);
                        dialogTopics.setArgs("Electrical and Electronics", context.getResources().getStringArray(R.array.el));
                        break;
                    case 0:
                        //explosionField=ExplosionField.attach2Window(secondActivity);
                        //explosionField.explode(((DomainsAdapter.ViewHolder) holder).iv);
                        dialogTopics.setArgs("Management Science", context.getResources().getStringArray(R.array.ms));
                        break;
                    case 4:
                        //explosionField=ExplosionField.attach2Window(secondActivity);
                        //explosionField.explode(((DomainsAdapter.ViewHolder) holder).iv);
                        dialogTopics.setArgs("Mechanical Engineering", context.getResources().getStringArray(R.array.me));
                        break;
                    case 5:
                        //explosionField=ExplosionField.attach2Window(secondActivity);
                        //explosionField.explode(((DomainsAdapter.ViewHolder) holder).iv);
                        dialogTopics.setArgs("Civil Engineering", context.getResources().getStringArray(R.array.ce));
                        break;
                }
                dialogTopics.setCancelable(true);
                dialogTopics.show(((AppCompatActivity) context).getSupportFragmentManager(), null);

            }
        });
    }


    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDomain;
        ConstraintLayout ll;
        LinearLayout iv;

        public ViewHolder(View itemView) {
            super(itemView);
            ll = (ConstraintLayout) itemView.findViewById(R.id.topicsback);
            //tvDomain = (TextView) itemView.findViewById(R.id.tvDomain);
            iv = (LinearLayout)itemView.findViewById(R.id.imgtopics);
        }
    }
   /* public class ParallaxVH extends RecyclerView.ViewHolder{
        PEWImageView iv;
        public ParallaxVH(View itemView) {
            super(itemView);
            iv = (PEWImageView) itemView.findViewById(R.id.pIVHeader);

        }
    }*/
}
