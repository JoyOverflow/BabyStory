package ouyj.hyena.com.babystory.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecycleAdapter <T>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected Context ctx;
    public LayoutInflater mInflater;
    protected final List<T> mList = new ArrayList<T>();


    //接口成员字段
    protected ItemClickListener itemListener;
    /**
     * 接口成员字段初始化
     * @param listener
     */
    public void setItemClickListener(ItemClickListener listener) {
        this.itemListener = listener;
    }
    /**
     * 创建内嵌接口
     */
    public interface ItemClickListener {
        void onRecyclerItemClick(int position);
    }


    /**
     * 构造方法
     * @param ctx
     */
    public BaseRecycleAdapter(Context ctx) {
        this.ctx = ctx;
        if (ctx != null) {
            this.mInflater = LayoutInflater.from(ctx);
        }
    }
    /**
     * 刷新（重新设定）数据
     * @param list
     */
    public void resetData(List<T> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }
    /**
     * 清空数据
     */
    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }
}
