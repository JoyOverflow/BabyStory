package ouyj.hyena.com.babystory;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        //将状态栏设置为沉浸模式
        setStatusBar();


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
}
