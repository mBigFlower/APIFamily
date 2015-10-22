package com.flowerfat.apilibrary.healthCook;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.flowerfat.apilibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 明明大美女 on 2015/10/21.
 */
public class CookAdapter extends RecyclerView.Adapter<CookAdapter.ViewHolder> {

    private List<Cook> mDatas = new ArrayList<>();
    private onItemClickListener mInterface;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView titleTV, descriptionTV;
        public SimpleDraweeView cookImg;

        public ViewHolder(View v) {
            super(v);
            titleTV = (TextView) v.findViewById(R.id.itemCook_title);
            descriptionTV = (TextView) v.findViewById(R.id.itemCook_description);
            cookImg = (SimpleDraweeView) v.findViewById(R.id.itemCook_img);
        }
    }

    public CookAdapter() {
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CookAdapter(List<Cook> myDataset) {
        mDatas = myDataset;
    }

    @Override
    public CookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cookname, parent, false);

        ViewHolder vh = new ViewHolder(rowView);
        return vh;
    }

    @Override
    public void onBindViewHolder(CookAdapter.ViewHolder holder, final int position) {
        holder.titleTV.setText(mDatas.get(position).getName());
        holder.descriptionTV.setText(mDatas.get(position).getDescription());
        Log.i("imgUrl", ApiCook.getImgUrl(mDatas.get(position).getImg()));
        Uri uri = Uri.parse(ApiCook.getImgUrl(mDatas.get(position).getImg()));
        holder.cookImg.setImageURI(uri);

        holder.cookImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInterface != null){
                    mInterface.onClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 自定义的两个添加数据的方法之一
     * 设置所有的数据
     *
     * @param mDatas
     */
    public void makeDatas(List<Cook> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    /**
     * 自定义的两个添加数据的方法之一
     * 添加一条天气数据
     *
     * @param data
     */
    public void addData(Cook data) {
        mDatas.add(data);
    }


    /**
     * 因为RecyclerView没有提供ItemOnclick的接口
     * 所以这里自己写一个
     */
    public interface onItemClickListener {
        void onClick(View v, int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.mInterface = listener;
    }
}
