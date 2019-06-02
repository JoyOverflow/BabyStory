package ouyj.hyena.com.babystory;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    private final int SLEEP_TIME = 2000;
    private final int MSG_WELCOME = 104259;

    //重定向到主活动
    Handler redirect = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_WELCOME:

                    //发送意图并启动指定活动
                    Intent intent = new Intent();
                    intent.setClass(
                            SplashActivity.this,
                            StoriesActivity.class
                    );
                    startActivity(intent);

                    //关闭当前活动
                    finish();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //隐藏动作条
        getSupportActionBar().hide();
        //视图全屏（去除状态栏）
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        sendDelay();
    }

    /**
     * 延时（利用线程休眠）发送消息
     */
    protected void sendDelay() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //休眠
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //发送消息
                Message msg = new Message();
                msg.what = MSG_WELCOME;
                redirect.sendMessage(msg);
            }
        }).start();
    }
}
