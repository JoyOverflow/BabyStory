package ouyj.hyena.com.babystory;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {


    protected RelativeLayout mLayoutMain;
    protected ViewGroup layoutContent;
    protected RelativeLayout titleBack;
    protected RelativeLayout mLayoutTitleRight;
    protected ImageView mImageViewRight;
    protected RelativeLayout mLayoutTitleRightSub;
    protected ImageView mImageViewRightSub;
    protected TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        //将状态栏设置为沉浸模式
        setStatusBar();
        //根视图和加载子视图的父视图
        mLayoutMain = findViewById(R.id.layout_main);
        layoutContent = findViewById(R.id.layout_content);
        //查找其他视图
        mLayoutTitleRight = findViewById(R.id.title_right);
        mImageViewRight = findViewById(R.id.img_title_right);
        mLayoutTitleRightSub = findViewById(R.id.title_right_sub);
        mImageViewRightSub = findViewById(R.id.img_title_right_sub);

        //标题栏文字
        txtTitle = findViewById(R.id.txt_title_center);
        //点击则关闭当前活动
        titleBack = findViewById(R.id.title_left);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    /**
     * 加载FrameLayout子视图布局
     * @param resource
     */
    @Override
    public void setContentView(int resource) {
        LayoutInflater mInflater=LayoutInflater.from(this);
        layoutContent.addView(
                mInflater.inflate(resource, null, false),
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );
    }
    /**
     * 设置标题栏文字
     */
    protected void setTitle(String str) {
        txtTitle.setText(str);
    }
    /**
     * 状态栏沉浸模式
     */
    private void setStatusBar() {

        //当前设备是否大于等于Android5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window win= getWindow();

            //禁止将状态和导航栏设置为透明色（注部分机型会有默认的背景色，并不是透明）
            win.clearFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            );
            //win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            //开启全屏模式
            win.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );

            //只有先加入此Flag才能调用setStatusBarColor来设置状态栏颜色
            win.addFlags(
                    WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
            );
            //为状态栏设置背景色（只能用于Android5.0及以上）
            win.setStatusBarColor(
                    ContextCompat.getColor(this, R.color.color_green)
            );

            getSupportActionBar().hide();
        }
    }



    /**
     * 隐藏返回按钮
     */
    public void hideBackImage() {
        titleBack.setVisibility(View.GONE);
    }
    public void hideRightText() {
        mImageViewRight.setVisibility(View.GONE);
    }
    public void hideTitleBar() {
        RelativeLayout titleLayout = findViewById(R.id.title_bar);
        mLayoutMain.removeView(titleLayout);
    }
    protected void setRightImage(int resId) {
        mImageViewRight.setVisibility(View.VISIBLE);
        mImageViewRight.setImageResource(resId);
    }
    protected void setRightSubImage(int resId) {
        mImageViewRightSub.setVisibility(View.VISIBLE);
        mImageViewRightSub.setImageResource(resId);
    }

    /**
     * 弹出提示栏
     * @param str
     */
    protected void Toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
