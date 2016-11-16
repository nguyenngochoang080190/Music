package com.example.administrator.myapplicationdemo3;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.administrator.myapplicationdemo3.database.ManagerAllSongs;
import com.example.administrator.myapplicationdemo3.fragment.AlbumFavoriteFragment;
import com.example.administrator.myapplicationdemo3.fragment.LibraryFragment;
import com.example.administrator.myapplicationdemo3.support.MusicPref;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout mDrawerLayout;
    ProgressBar mProgressBar;
    ImageView imageView;
    Boolean IS_EXPANDED = true;
    BottomSheetBehavior mBottomSheetBehavior;
    NavigationView mNavigationView;
    ManagerAllSongs mManagerAllSongs;
    MusicPref mMusicPref;
    LibraryFragment mLibraryFragment;
    AlbumFavoriteFragment mAlbumFavoriteFragment;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLibraryFragment=new LibraryFragment();
        
        mAlbumFavoriteFragment=new AlbumFavoriteFragment();
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_progress_bar);
        imageView = (ImageView) findViewById(R.id.iv_title);
        imageView.setOnClickListener(this);
        mNavigationView = (NavigationView) findViewById(R.id.navi);
        //create database
        mMusicPref = new MusicPref(this);
        if (mMusicPref.get_database() == false) {
            ManagerAllSongs.onUpdateDatabase(this);
            mMusicPref.set_database(true);
        }
        setSupportActionBar(toolbar);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        final Drawable mDrawable = getResources().getDrawable(R.drawable.custom_progress_bar);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.item_library:
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, new LibraryFragment(), null).commit();
                        break;
                    case R.id.item_your_album:
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, new AlbumFavoriteFragment(), null).commit();
                        break;
                    case R.id.item_folder:

                        break;
                    case R.id.item_exit:
                        finish();
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        RelativeLayout mRelativeLayout = (RelativeLayout) findViewById(R.id.bottom_behavior);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coo);
        coordinatorLayout.setOnClickListener(this);
        RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.ll_control_music);
        linearLayout.setOnClickListener(this);
        mBottomSheetBehavior = BottomSheetBehavior.from(mRelativeLayout);
        mBottomSheetBehavior.setPeekHeight(0);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset == 0) {
                    setBottomBehaviorCollapsed();
                    IS_EXPANDED = false;
                }
                if (IS_EXPANDED == false)
                    if (slideOffset > 0.01) {
                        setBottomBehaviorExpanded();
                        IS_EXPANDED = true;
                    }
                if (slideOffset == 1) {
                    IS_EXPANDED = true;
                }
            }
        });
    }

    public void setBottomBehaviorCollapsed() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        RelativeLayout ll = (RelativeLayout) findViewById(R.id.ll_control_music);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height = getResources().getDimensionPixelSize(R.dimen.icon_title__layout_beha);
        layoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.icon_title_margin);
        layoutParams.addRule(RelativeLayout.BELOW, mProgressBar.getId());
        layoutParams.addRule(RelativeLayout.RIGHT_OF, imageView.getId());
        ll.setLayoutParams(layoutParams);
        RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.ll_control_music_1);
        linearLayout.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
    }

    public void setBottomBehaviorExpanded() {
        RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.ll_control_music_1);
        linearLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        RelativeLayout ll = (RelativeLayout) findViewById(R.id.ll_control_music);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height = getResources().getDimensionPixelSize(R.dimen.icon_title__layout_beha);
        layoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.icon_title_margin);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        ll.setLayoutParams(layoutParams);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_title || v.getId() == R.id.ll_control_music)
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void setPeekHeight() {
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            int actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
            mBottomSheetBehavior.setPeekHeight(actionBarHeight);
            IS_EXPANDED = true;
            LinearLayout scrollView = (LinearLayout) findViewById(R.id.scroll_view);
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) scrollView.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, actionBarHeight);
            scrollView.setLayoutParams(layoutParams);
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }
}
