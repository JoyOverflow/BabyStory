package ouyj.hyena.com.babystory;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 详细的一则故事页
 */
public class DetailActivity extends BaseActivity {

    private String title;
    private String content;
    private int selectedNum = 0;
    private String voicer = "nannan";
    private String[] mCloudVoicersEntries;
    private String[] mCloudVoicersValue;

    private TextView mTvText;
    private ImageView mIvSelect;
    private ImageView mIvPlay;
    private ImageView mIvPause;
    private ImageView mIvStop;

    //当前播放是否被暂停
    private boolean isPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //设置标题和内容
        mTvText = findViewById(R.id.txt_content);
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        super.setTitle(title);
        mTvText.setText(content);


        mCloudVoicersEntries = getResources().getStringArray(R.array.voicer_cloud_entries);
        mCloudVoicersValue = getResources().getStringArray(R.array.voicer_cloud_values);

        //查找选择图像按钮
        mIvSelect = findViewById(R.id.img_select);
        mIvSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        mIvPlay = findViewById(R.id.img_play);
        mIvPause = findViewById(R.id.img_pause);
        mIvStop = findViewById(R.id.img_stop);

        mIvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mIvPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPause)
                    isPause = true;
                else
                    isPause = false;
            }
        });
        mIvStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    /**
     * 弹出对话框并选择一项
     */
    private void showDialog() {
        new AlertDialog.Builder(this).setTitle("发音人")
                .setSingleChoiceItems(mCloudVoicersEntries,
                        selectedNum, // 默认值
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                voicer = mCloudVoicersValue[which];
                                selectedNum = which;
                                dialog.dismiss();
                            }
                        }).show();
    }
}
