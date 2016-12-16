package com.example.admin.news_app.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.admin.news_app.R;
import com.example.admin.news_app.frament.ChatFrament;
import com.example.admin.news_app.frament.GifFrament;
import com.example.admin.news_app.frament.ImageFrament;
import com.example.admin.news_app.frament.NewsFrament;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar_main_activity)
    Toolbar toolbar;
    @BindView(R.id.nv_main_activity)
    NavigationView nvMainActivity;
    @BindView(R.id.drawer_layout_activity_main)
    DrawerLayout drawerLayout;
    @BindView(R.id.fl_main_activity)
    FrameLayout flMainActivity;
    private FragmentManager FragmentManager;
    private List<Fragment> fragments;
    private Fragment curfragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("新闻");
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        //获取fragmentManager管理者
        FragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        fragments.add(new NewsFrament());
        fragments.add(new ImageFrament());
        fragments.add(new GifFrament());
        fragments.add(new ChatFrament());

        //默认选择新闻
        nvMainActivity.setCheckedItem(R.id.item_news_nav_main);
        curfragments = fragments.get(0);
        FragmentManager.beginTransaction().replace(R.id.fl_main_activity, curfragments).commit();

        //切换
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();
        nvMainActivity.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int index = 0;
                switch (item.getItemId()) {
                    case R.id.item_news_nav_main:
                        toolbar.setTitle("新闻");
                        index = 0;
                        break;
                    case R.id.item_image_nav_main:
                        toolbar.setTitle("图片");
                        index = 1;
                        break;
                    case R.id.item_collection_nav_main:
                        toolbar.setTitle("收藏");
                        index = 2;
                        break;
                    case R.id.item_chat_main_activity:
                        toolbar.setTitle("聊天");
                        index = 3;
                        break;

                }

                //打开管理事务
                FragmentTransaction transaction = FragmentManager.beginTransaction();
                //切换fragment
                Fragment nextfragments = fragments.get(index);
                if (nextfragments != curfragments) {
                    //isAdded是否已经添加 ，如果添加过了，就隐藏原先的，展示新的界面
                    if (!nextfragments.isAdded()) {
                        //判断是否有原先的界面，如果有就隐藏原来的界面，在添加一个新的界面
                        if (curfragments != null) {
                            transaction.hide(curfragments);
                        }
                        transaction.add(R.id.fl_main_activity, nextfragments);
                    } else {
                        if (curfragments != null) {
                            transaction.hide(curfragments);
                        }
                        transaction.show(nextfragments);
                    }
                    curfragments = nextfragments;
                }
                transaction.commit();
                //关闭侧拉菜单
                drawerLayout.closeDrawers();


                return true;
            }
        });

    }


}
