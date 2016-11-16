package com.example.administrator.myapplicationdemo3.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplicationdemo3.R;
import com.example.administrator.myapplicationdemo3.adapter.ViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 11/5/2016.
 */

public class LibraryFragment extends Fragment implements View.OnClickListener {
    TabLayout mTabLayout;
    ViewPager mViewPager;
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        View contentView = inflater.inflate(R.layout.library_layout, viewGroup, false);
        mTabLayout=(TabLayout) contentView.findViewById(R.id.tabLayout);
        mViewPager=(ViewPager) contentView.findViewById(R.id.viewPager);
        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(AllSongFragment.getInstance());
        fragments.add(new AlbumFragment());
        fragments.add(new ArtistFragment());
        String[] titles=getActivity().getResources().getStringArray(R.array.tabLayoutTitle);
        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(),fragments,titles));
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                mTabLayout.setupWithViewPager(mViewPager);
            }
        });
        return contentView;
    }
    @Override
    public void onClick(View v) {

    }
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        ///
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
