package com.example.administrator.myapplicationdemo3.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myapplicationdemo3.R;
import com.example.administrator.myapplicationdemo3.adapter.RecyclerViewAdapter;
import com.example.administrator.myapplicationdemo3.database.ManagerAllSongs;
import com.example.administrator.myapplicationdemo3.model.Song;
import com.example.administrator.myapplicationdemo3.util.DividerItemDecoration;
import com.example.administrator.myapplicationdemo3.util.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 11/5/2016.
 */

public class AllSongFragment extends Fragment {
    RecyclerView mRecyclerView;
    static ArrayList<Song> mSongs;
    ManagerAllSongs mManagerAllSongs;
    static AllSongFragment mAllSongFragment = new AllSongFragment();
    RecyclerViewAdapter mRecyclerViewAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        View contentView = inflater.inflate(R.layout.all_song_fragment_layout, viewGroup, false);
        mManagerAllSongs = new ManagerAllSongs(getContext());
        mManagerAllSongs.openManagerAllSongs();
        mSongs = mManagerAllSongs.getArrayListSongs(ManagerAllSongs.GET_ALL);
        mManagerAllSongs.closeDatabase();
        mRecyclerViewAdapter = new RecyclerViewAdapter(getContext(), mSongs);
        mRecyclerView = (RecyclerView) contentView.findViewById(R.id.rv_all_song);
        LinearLayoutManager ll = new LinearLayoutManager(getContext());
        ll.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(ll);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.notifyDataSetChanged();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mRecyclerViewAdapter.notifyDataSetChanged();
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position, MotionEvent e) {
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return contentView;
    }

    public AllSongFragment() {
    }

    public static AllSongFragment getInstance() {
        return mAllSongFragment;
    }

    public static ArrayList<Song> getSong() {
        return mSongs;
    }

}
