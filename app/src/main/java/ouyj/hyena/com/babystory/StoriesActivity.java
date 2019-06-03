package ouyj.hyena.com.babystory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ouyj.hyena.com.babystory.adapter.BaseRecycleAdapter;
import ouyj.hyena.com.babystory.adapter.StoryListAdapter;
import ouyj.hyena.com.babystory.db.DbHelper;
import ouyj.hyena.com.babystory.entity.Story;


public class StoriesActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private StoryListAdapter adapter;
    private List<Story> storyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);

        //字串资源文件中取字串
        String title=getResources().getString(R.string.app_title);
        setTitle(title);

        //查找并初始化视图
        initViewData();
    }
    private void initViewData() {
        recyclerView = findViewById(R.id.lst_story);

        //设置回收视图的其内布局
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        //设置回收视图的分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.recyclerview_divider));
        recyclerView.addItemDecoration(divider);

        //创建适配器
        adapter = new StoryListAdapter(this);

        //适配器的点击列表项（实现指定接口）
        adapter.setItemClickListener(new BaseRecycleAdapter.ItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                //转向指定活动
                Intent i = new Intent();
                i.setClass(StoriesActivity.this, DetailActivity.class);

                //从数据源中取指定数据
                i.putExtra("title", storyList.get(position).getTitle());
                i.putExtra("content", storyList.get(position).getContent());
                startActivity(i);
            }
        });

        //适配器的数据源（获取数据）
        storyList = DbHelper.getStoryList(this);
        adapter.resetData(storyList);

        //为回收视图设置适配器
        recyclerView.setAdapter(adapter);
    }
}
