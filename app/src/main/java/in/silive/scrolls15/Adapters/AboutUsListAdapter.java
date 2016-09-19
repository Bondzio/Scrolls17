package in.silive.scrolls15.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import in.silive.scrolls15.R;

/**
 * Created by akriti on 14/9/16.
 */
public class AboutUsListAdapter extends BaseAdapter {
    public static String names[];
    public static String designation[];
    public static Integer pic[];
    public static String head;
    LayoutInflater inflater;
    Context c;

    public AboutUsListAdapter(Context context, String result_names[], String result_designatin[], Integer result_pic[]) {
        names = result_names;
        designation = result_designatin;
        pic = result_pic;
        //head = s;
        c = context;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public class Holder {
        ImageView member_pic;
        TextView member_name, member_desig, heading;

    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View sview = inflater.inflate(R.layout.about_us_list_adapter, null);
        Holder holder = new Holder();
        holder.member_pic = (ImageView) sview.findViewById(R.id.member_pic);
        holder.member_name = (TextView) sview.findViewById(R.id.member_name);
        holder.member_desig = (TextView) sview.findViewById(R.id.member_desig);
        // holder.heading = (TextView)sview.findViewById(R.id.heading);
        holder.member_pic.setImageResource(pic[i]);
        holder.member_name.setText(names[i]);
        holder.member_desig.setText(designation[i]);
        if (designation[i].matches("^-?\\d+$")) {
            holder.member_desig.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + designation[i]));
                    if (ActivityCompat.checkSelfPermission(c, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        ActivityCompat.requestPermissions((Activity) c,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                1234);
                        return;
                    }
                    c.startActivity(intent);
                }
            });
        }
        //holder.heading.setText(head);
        return sview;
    }
}
