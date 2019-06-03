package ouyj.hyena.com.babystory.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ouyj.hyena.com.babystory.R;
import ouyj.hyena.com.babystory.entity.Story;

public class StoryListAdapter extends BaseRecycleAdapter<Story>{
    /**
     * 构造方法
     * @param ctx
     */
    public StoryListAdapter(Context ctx) {
        super(ctx);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载项视图
        View view = mInflater.inflate(R.layout.story_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        //为每一视图项设置值和事件
        ViewHolder vh = (ViewHolder) holder;

        Story story = mList.get(position);
        vh.name.setText((position + 1) + "  " + story.getTitle());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListener.onRecyclerItemClick(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * 创建内部ViewHolder类
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
        }
    }
}
