package com.example.administrator.myapplicationdemo3.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplicationdemo3.R;
import com.example.administrator.myapplicationdemo3.fragment.AlbumFragment;
import com.example.administrator.myapplicationdemo3.util.DpToPx;

import java.util.ArrayList;

/**
 * Created by Administrator on 13/11/2016.
 */

public class AlbumRecyclerAdapter extends RecyclerView.Adapter<AlbumRecyclerAdapter.myViewHolder> {
    ArrayList<AlbumFragment.Album> album;
    Context mContext;

    public AlbumRecyclerAdapter(Context context, ArrayList<AlbumFragment.Album> album) {
        mContext = context;
        this.album = album;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.it_ablum,parent,false);
        return new AlbumRecyclerAdapter.myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
            holder.album_name.setText(album.get(position).getName());
            holder.quanlity.setText(mContext.getResources().getString(R.string.quanlity)+" "+album.get(position).getQuanlity());

    }

    @Override
    public int getItemCount() {
        return album.size();
    }


    public static class myViewHolder extends RecyclerView.ViewHolder {
        TextView album_name;
        TextView quanlity;
        public myViewHolder(View itemView) {
            super(itemView);
            album_name = (TextView) itemView.findViewById(R.id.tv_ablum_name);
            quanlity = (TextView) itemView.findViewById(R.id.tv_album_count);
        }
    }


}
