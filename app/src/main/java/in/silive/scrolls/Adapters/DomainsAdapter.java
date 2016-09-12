package in.silive.scrolls.Adapters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.silive.scrolls.Fragments.DialogTopics;
import in.silive.scrolls.R;

/**
 * Created by AKG002 on 03-09-2016.
 */
public class DomainsAdapter extends RecyclerView.Adapter<DomainsAdapter.ViewHolder> {
    String[] domains;
    Context context;

    public DomainsAdapter(Context context, String[] domains) {
        this.context = context;
        this.domains = domains;
    }

    @Override
    public DomainsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_domain, parent, false));
    }

    @Override
    public void onBindViewHolder(DomainsAdapter.ViewHolder holder, final int position) {
        holder.tvDomain.setText(domains[position]);
        holder.ll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DialogTopics dialogTopics = new DialogTopics();
                switch (position) {
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

    @Override
    public int getItemCount() {
        return domains.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDomain;
        RelativeLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            ll = (RelativeLayout) itemView.findViewById(R.id.ll);
            tvDomain = (TextView) itemView.findViewById(R.id.tvDomain);
        }
    }
}
