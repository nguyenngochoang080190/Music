package com.example.administrator.myapplicationdemo3.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplicationdemo3.R;
import com.example.administrator.myapplicationdemo3.fragment.AlbumFragment;
import com.example.administrator.myapplicationdemo3.fragment.ArtistFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 13/11/2016.
 */

public class ArtistRecyclerAdapter extends RecyclerView.Adapter<ArtistRecyclerAdapter.myViewHolder> {
    ArrayList<ArtistFragment.Artist> artists;
    Context mContext;

    public ArtistRecyclerAdapter(Context context,ArrayList<ArtistFragment.Artist> artists) {
        mContext = context;
        this.artists = artists;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.it_ablum,parent,false);
        return new ArtistRecyclerAdapter.myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
            holder.album_name.setText(artists.get(position).getName());
            holder.quanlity.setText(mContext.getResources().getString(R.string.quanlity)+" "+artists.get(position).getQuanlity());

    }

    @Override
    public int getItemCount() {
        return artists.size();
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
