package ouyj.hyena.com.babystory;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ouyj.hyena.com.babystory.entity.Story;


public class StoriesActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private StoryListAdapter mAdapter;
    private List<Story> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);

        //字串资源文件中取字串
        String title=getResources().getString(R.string.app_title);
        setTitle(title);

        //查找并初始化视图
        initView();
    }
    private void initView() {
        recyclerView = findViewById(R.id.lst_story);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_divider));
        recyclerView.addItemDecoration(divider);

        mAdapter = new StoryListAdapter(this);
        recyclerView.setAdapter(mAdapter);

    }


}
