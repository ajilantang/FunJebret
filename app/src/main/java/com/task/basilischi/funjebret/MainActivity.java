package com.task.basilischi.funjebret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity implements MaterialTabListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tabHost) MaterialTabHost tabHost;

    private PageMenuFragmentAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // set toolbar
        setSupportActionBar(toolbar);

        menuAdapter = new PageMenuFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(menuAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int tabPosition) {
                tabHost.setSelectedNavigationItem(tabPosition);
            }
        });

        //Set color background tab
        tabHost.setAccentColor(getResources().getColor(R.color.primary_dark));

        //for tab position
        for (int i = 0; i < menuAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(menuAdapter.getPageTitle(i))
                            .setTabListener(this)
            );


            Intent intent = getIntent();
            String status = intent.getStringExtra("loginStatus");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //tab on selected
        @Override
        public void onTabSelected(MaterialTab materialTab) {

            viewPager.setCurrentItem(materialTab.getPosition());
        }

        //tab on reselected
        @Override
        public void onTabReselected(MaterialTab materialTab) {

        }

        //tab on unselected
        @Override
        public void onTabUnselected(MaterialTab materialTab) {

        }
}
