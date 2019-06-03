package ouyj.hyena.com.babystory;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setStatusBar();
    }

    private void setStatusBar() {

        //getSupportActionBar().hide();
    }
}
