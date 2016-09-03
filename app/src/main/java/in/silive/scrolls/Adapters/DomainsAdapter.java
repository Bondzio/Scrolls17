package in.silive.scrolls.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_domain,parent,false));
    }

    @Override
    public void onBindViewHolder(DomainsAdapter.ViewHolder holder, int position) {
            holder.tvDomain.setText(domains[position]);
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return domains.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvDomain;
        LinearLayout ll;
        public ViewHolder(View itemView) {
            super(itemView);
            ll = (LinearLayout)itemView.findViewById(R.id.ll);
            tvDomain = (TextView)itemView.findViewById(R.id.tvDomain);
        }
    }
}
