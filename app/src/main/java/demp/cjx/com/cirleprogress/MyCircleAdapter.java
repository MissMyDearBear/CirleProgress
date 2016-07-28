package demp.cjx.com.cirleprogress;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * title
 * Created by Bear on 2016/7/28.
 */
public class MyCircleAdapter extends BaseAdapter {
    private Context mContext;
    private List<HashMap<String, Object>> mList;

    public MyCircleAdapter(Context context, List<HashMap<String, Object>> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewHolder holder = null;
        if (holder == null) {
            holder = new GridViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item, null);
            holder.dataUsedTv = (TextView) convertView.findViewById(R.id.tv_data_used);
            holder.dataTotalTv = (TextView) convertView.findViewById(R.id.tv_data_total);
            holder.comboNameTv = (TextView) convertView.findViewById(R.id.tv_combo_name);
            holder.comboRv = (RingView) convertView.findViewById(R.id.rv_data);
            convertView.setTag(holder);
        } else {
            holder = (GridViewHolder) convertView.getTag();
        }
        HashMap<String, Object> info = mList.get(position);
        String name = info.get("name").toString();
        String total = info.get("total").toString();
        String left = info.get("left").toString();
        String color = info.get("color").toString();
        holder.dataTotalTv.setText(total);
        holder.comboNameTv.setText(name);
        holder.dataUsedTv.setTextColor(Color.parseColor(color));
        int totalCount = Integer.parseInt(total);
        int leftCount = Integer.parseInt(left);
        float percent = (float) ((float) leftCount * 1.0 / totalCount * 1.0);
        holder.comboRv.setPercentage(percent);
        holder.comboRv.setChangeText(holder.dataUsedTv, leftCount, color);


        return convertView;
    }

    class GridViewHolder {
        public TextView dataUsedTv;
        public TextView dataTotalTv;
        public TextView comboNameTv;
        public RingView comboRv;


    }
}
