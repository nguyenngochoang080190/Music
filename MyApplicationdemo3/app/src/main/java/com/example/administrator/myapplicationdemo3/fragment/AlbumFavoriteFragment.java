package com.example.administrator.myapplicationdemo3.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.myapplicationdemo3.R;
import com.example.administrator.myapplicationdemo3.adapter.MyAlbumRecyclerAdapter;
import com.example.administrator.myapplicationdemo3.database.BaseDataBase;
import com.example.administrator.myapplicationdemo3.database.ManagerFavoriteAlbums;
import com.example.administrator.myapplicationdemo3.model.Album;
import com.example.administrator.myapplicationdemo3.util.CurrentTime;
import com.example.administrator.myapplicationdemo3.util.GridSpacingItemDecoration;
import com.example.administrator.myapplicationdemo3.util.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 11/5/2016.
 */

public class AlbumFavoriteFragment extends Fragment {
    ArrayList<Album> albums;
    MyAlbumRecyclerAdapter myAlbumRecyclerAdapter;
    ManagerFavoriteAlbums managerFavoriteAlbums;
    RecyclerView recyclerView;
    ImageView imageView;
    mDialog myDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        View contentView = inflater.inflate(R.layout.album_fragment_layout, viewGroup, false);
        recyclerView = (RecyclerView) contentView.findViewById(R.id.rv_album);
        albums = new ArrayList<>();
        myDialog = new mDialog(getContext());
        managerFavoriteAlbums = new ManagerFavoriteAlbums(getContext());
        managerFavoriteAlbums.openManagerFavoriteAlbums();
        albums = managerFavoriteAlbums.getArrayListAlbums(BaseDataBase.GET_ALL);
        myAlbumRecyclerAdapter = new MyAlbumRecyclerAdapter(getContext(), albums);
        recyclerView.setAdapter(myAlbumRecyclerAdapter);
        myAlbumRecyclerAdapter.notifyDataSetChanged();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 10, true));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position, MotionEvent e) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return contentView;
    }

    public class mDialog extends Dialog implements View.OnClickListener {
        EditText name;
        Button positiveButton, negativeButton;

        public mDialog(Context context) {
            super(context);
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            View view = layoutInflater.inflate(R.layout.create_album_layout, null);
            Rect displayRectangle = new Rect();
            Window window = getActivity().getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
            view.setMinimumWidth((int) (displayRectangle.width() * 0.9));
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(view);
            name = (EditText) findViewById(R.id.edt_input_name);
            positiveButton = (Button) findViewById(R.id.bt_positive);
            negativeButton = (Button) findViewById(R.id.bt_negative);
            positiveButton.setOnClickListener(this);
            negativeButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.bt_positive) {
                if (!name.getText().toString().isEmpty()) {
                    managerFavoriteAlbums.openManagerFavoriteAlbums();
                    Album album = new Album();
                    album.setFAVORITE_ALBUM_NAME(name.getText().toString());
                    album.setSIZE(0);
                    album.setDATE_ADDED(String.valueOf(CurrentTime.getTimeInMillis()));
                    album.setDATE_MODIFIED(album.getDATE_ADDED());
                    managerFavoriteAlbums.insertAlbum(album);
                    managerFavoriteAlbums.closeDatabase();
                    albums.add(0, album);
                    myAlbumRecyclerAdapter.notifyDataSetChanged();
                    dismiss();

                }

            } else if (v.getId() == R.id.bt_negative)
                dismiss();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.favorite_album_option_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favorite) {
            myDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}