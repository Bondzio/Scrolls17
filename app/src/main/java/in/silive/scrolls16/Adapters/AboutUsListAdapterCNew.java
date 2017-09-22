package in.silive.scrolls16.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import in.silive.scrolls16.R;

import static in.silive.scrolls16.Fragments.UploadDoc.context;

/**
 * Created by root on 4/9/17.
 */

public class AboutUsListAdapterCNew extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    public String names[];
    public String designation[];
    public Integer pic[];

    public AboutUsListAdapterCNew(Context context, String result_names[], String result_designatin[], Integer result_pic[]) {
        names = result_names;
        designation = result_designatin;
        pic = result_pic;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AboutUsListAdapterCNew.ViewHolder(LayoutInflater.from(context).inflate(R.layout.scrollscteam, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((AboutUsListAdapterCNew.ViewHolder)holder).member_pic.setImageResource(pic[position]);
        ((AboutUsListAdapterCNew.ViewHolder)holder).member_name.setText(names[position]);
         ((AboutUsListAdapterCNew.ViewHolder)holder).member_desig.setText(designation[position]);
        /*if (designation[position].matches("^-?\\d+$")) {
            ((AboutUsListAdapterNew.ViewHolder)holder).member_desig.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:+91" + designation[position]));
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                1234);
                        return;
                    }
                    context.startActivity(intent);
                }
            });*/
    }



    @Override
    public int getItemCount() {
        return names.length;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        ImageView member_pic;
        TextView member_name, member_desig, heading;

        public ViewHolder(View sview) {
            super(sview);
            member_pic = (ImageView) sview.findViewById(R.id.member_pic);
            member_name = (TextView) sview.findViewById(R.id.member_name);
            member_desig = (TextView) sview.findViewById(R.id.member_desig);

        }
    }
}
