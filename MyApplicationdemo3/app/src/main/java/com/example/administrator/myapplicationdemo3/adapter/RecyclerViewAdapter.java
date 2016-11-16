package com.example.administrator.myapplicationdemo3.adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplicationdemo3.R;
import com.example.administrator.myapplicationdemo3.model.Song;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 9/16/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    public Context mContext;
    protected ArrayList<Song> mSongs;
    public RecyclerViewAdapter(Context context, ArrayList<Song> songs) {
        this.mContext=context;
        this.mSongs=songs;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView=LayoutInflater.from(mContext).inflate(R.layout.it_all_song,parent,false);
        return new MyViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.songName.setText(mSongs.get(position).getDISPLAY_NAME().substring(0,mSongs.get(position).getDISPLAY_NAME().length()-4));
        holder.album.setText("Album: "+mSongs.get(position).getALBUM());
        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public static class  MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView songName;
        TextView album;
        ImageView menu;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView =(ImageView) itemView.findViewById(R.id.iv_avatar);
            songName =(TextView) itemView.findViewById(R.id.tv_song_name);
            album =(TextView) itemView.findViewById(R.id.tv_album);
            menu =(ImageView) itemView.findViewById(R.id.iv_context_menu);
        }
    }
    public void showPopUpMenu(View view)
    {
        PopupMenu menu=new PopupMenu(mContext,view);
        menu.getMenuInflater().inflate(R.menu.conttext_menu,menu.getMenu());
        menu.show();
    }
}

