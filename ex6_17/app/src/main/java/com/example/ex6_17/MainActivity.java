package com.example.ex6_17;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.google.android.material.tabs.TabLayout;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        TabSpec tabSpecSong = tabHost.newTabSpec("SONG").setIndicator("음악별");
            tabSpecSong.setContent(R.id.tabSong);
            tabHost.addTab(tabSpecSong);

        TabSpec tabSpecArtist = tabHost.newTabSpec("ARTIST").setIndicator("가수별");
            tabSpecArtist.setContent(R.id.tabArtist);
            tabHost.addTab(tabSpecArtist);

        TabSpec tabSpecAlbum = tabHost.newTabSpec("ALBUM").setIndicator("앨범별");
            tabSpecAlbum.setContent(R.id.tabAlbum);
            tabHost.addTab(tabSpecAlbum);

            tabHost.setCurrentTab(0);
    }
}