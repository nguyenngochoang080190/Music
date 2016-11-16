package com.example.administrator.myapplicationdemo3.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.administrator.myapplicationdemo3.R;
import com.example.administrator.myapplicationdemo3.adapter.AlbumRecyclerAdapter;
import com.example.administrator.myapplicationdemo3.model.Song;
import com.example.administrator.myapplicationdemo3.util.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 11/5/2016.
 */

public class AlbumFragment extends Fragment {
    ArrayList<Song> songs;
    Map<String,Integer> map;
    RecyclerView recyclerView;
    AlbumRecyclerAdapter albumRecyclerAdapter;
    ArrayList<Album> albums;
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        View contentView = inflater.inflate(R.layout.album_fragment_layout, viewGroup, false);
        recyclerView=(RecyclerView) contentView.findViewById(R.id.rv_album);
        map=new HashMap<>();
        songs=AllSongFragment.getSong();
        if(songs!=null)
        {
            for (Song m:songs
                    ) {
                if(map.get(m.getALBUM())==null)
                    map.put(m.getALBUM(),0);
                map.put(m.getALBUM(),Integer.parseInt(""+map.get(m.getALBUM()))+1);
            }
        }
        albums=new ArrayList<>();
        for(String s:map.keySet())
        {
            Album al=new Album(s,map.get(s));
            albums.add(al);
        }
        albumRecyclerAdapter=new AlbumRecyclerAdapter(getContext(),albums);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(albumRecyclerAdapter);
        albumRecyclerAdapter.notifyDataSetChanged();
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3,20,true));
        return contentView;
    }


    public static class Album {
        String name;
        int quanlity;

        public Album(String name, int quanlity) {
            this.name=name;
            this.quanlity=quanlity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuanlity() {
            return quanlity;
        }

        public void setQuanlity(int quanlity) {
            this.quanlity = quanlity;
        }
    }
}
