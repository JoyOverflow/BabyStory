package ouyj.hyena.com.babystory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 详细的一则故事页
 */
public class DetailActivity extends BaseActivity {

    private String title;
    private String content;

    private TextView mTvText;
    private ImageView mIvSelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        setTitle(title);

        mTvText = findViewById(R.id.txt_content);
        mTvText.setText(content);


        //查找选择图像按钮
        mIvSelect = findViewById(R.id.img_select);
        mIvSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }
}
