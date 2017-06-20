package jp.hotmix.myaedapiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hotmix on 2017/06/14.
 */

public class MainListAdapter extends BaseAdapter {
    List<AedModel> listData;
    LayoutInflater inflater;

    MainListAdapter(Context context, List<AedModel> data){
        super();
        listData = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public AedModel getItem(int position) {

        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {

        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.main_list_column, null);
            holder = new ViewHolder();
            holder.idText = (TextView)convertView.findViewById(R.id.textView);
            holder.locationText = (TextView)convertView.findViewById(R.id.textView2);
            holder.perfuctureText = (TextView)convertView.findViewById(R.id.textView3);
            holder.cityText = (TextView)convertView.findViewById(R.id.textView4);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        AedModel aed = getItem(position);
        holder.idText.setText(aed.getId()+"");
        holder.locationText.setText(aed.getLocationName());
        holder.perfuctureText.setText(aed.getPerfecture());
        holder.cityText.setText(aed.getCity());

        return convertView;
    }

    private static class ViewHolder {
        public TextView idText;
        public TextView locationText;
        public TextView perfuctureText;
        public TextView cityText;
    }
}
