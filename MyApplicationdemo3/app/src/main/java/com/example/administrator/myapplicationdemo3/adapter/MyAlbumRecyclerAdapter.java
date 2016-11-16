package com.example.administrator.myapplicationdemo3.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplicationdemo3.R;
import com.example.administrator.myapplicationdemo3.model.Album;
import com.example.administrator.myapplicationdemo3.util.DpToPx;

import java.util.ArrayList;

/**
 * Created by Administrator on 13/11/2016.
 */

public class MyAlbumRecyclerAdapter extends RecyclerView.Adapter<MyAlbumRecyclerAdapter.myViewHolder> {
    ArrayList<Album> album;
    Context mContext;

    public MyAlbumRecyclerAdapter(Context context, ArrayList<Album> album) {
        mContext = context;
        this.album = album;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.it_ablum, parent, false);
        return new MyAlbumRecyclerAdapter.myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.album_name.setText(album.get(position).getFAVORITE_ALBUM_NAME());
        holder.quanlity.setText(mContext.getResources().getString(R.string.quanlity) + " " + album.get(position).getSIZE());


//            if(position==album.size())
//            {
//                holder.cardView.setAlpha(0.5f);
//                holder.album_name.setText("+");
//                holder.album_name.setTypeface(null, Typeface.BOLD);
//                holder.album_name.setTextSize(DpToPx.convertDpToPixel(20,mContext));
//                holder.quanlity.setVisibility(View.GONE);
//                holder.iv_context_menu.setVisibility(View.GONE);
//            }
//        else
//            {
//                holder.album_name.setText(album.get(position).getFAVORITE_ALBUM_NAME());
//                holder.quanlity.setText(mContext.getResources().getString(R.string.quanlity)+" "+album.get(position).getSIZE());
//            }

    }

    @Override
    public int getItemCount() {
        return album.size();
    }

    public void setDataSource(ArrayList<Album> dataSource) {
        this.album = dataSource;
        notifyDataSetChanged();
    }


    public static class myViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView album_name;
        TextView quanlity;
        ImageView iv_context_menu;
        ImageView iv_avatar;

        public myViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_card_view);
            album_name = (TextView) itemView.findViewById(R.id.tv_ablum_name);
            quanlity = (TextView) itemView.findViewById(R.id.tv_album_count);
            iv_context_menu = (ImageView) itemView.findViewById(R.id.iv_context_menu);
            iv_avatar = (ImageView) itemView.findViewById(R.id.iv_album_avatar);
            iv_context_menu.setVisibility(View.VISIBLE);
        }
    }


}
