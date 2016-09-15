package in.silive.scrolls.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import in.silive.scrolls.R;

/**
 * Created by akriti on 14/9/16.
 */
public class AboutUsListAdapter extends BaseAdapter {
    public static String names[];
    public static String designation[];
    public static Integer pic[];
    LayoutInflater inflater;

    public AboutUsListAdapter(Context context,String result_names[],String result_designatin[],Integer result_pic[]) {
        names = result_names;
        designation = result_designatin;
        pic = result_pic;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public class Holder{
        ImageView member_pic;
        TextView member_name,member_desig;

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View sview = inflater.inflate(R.layout.about_us_list_adapter,null);
        Holder holder = new Holder();
        holder.member_pic = (ImageView)sview.findViewById(R.id.member_pic);
        holder.member_name = (TextView)sview.findViewById(R.id.member_name);
        holder.member_desig = (TextView)sview.findViewById(R.id.member_desig);
        holder.member_pic.setImageResource(pic[i]);
        holder.member_name.setText(names[i]);
        holder.member_desig.setText(designation[i]);
        return sview;
    }
}
