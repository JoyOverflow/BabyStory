package ouyj.hyena.com.babystory.db;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import ouyj.hyena.com.babystory.entity.Story;

/**
 * 数据库操作类
 */
public class DbHelper {
    final static String FILEPATH = "data/data/ouyj.hyena.com.babystory/databases/story.db";
    final static String TAG = "DbHelper";


    /**
     * 获取故事列表
     * @param context
     * @return
     */
    public static List<Story> getStoryList(Context context) {
        List<Story> list = new ArrayList<>();

        //打开数据库
        SQLiteDatabase db = openDatabase(context);
        //打开并遍历游标
        Cursor cursor = db.rawQuery("select * from story", new String[]{});
        while (cursor.moveToNext()) {
            Story s = new Story();
            s.setId(cursor.getInt(cursor.getColumnIndex("id")));
            s.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            s.setContent(cursor.getString(cursor.getColumnIndex("content")));
            s.setCount(cursor.getString(cursor.getColumnIndex("count")));
            //加入集合中
            list.add(s);
        }
        cursor.close();
        db.close();

        return list;
    }
    /**
     * 打开数据库
     * @param context
     * @return
     */
    public static SQLiteDatabase openDatabase(Context context) {
        //判断数据库文件是否存在
        File file = new File(FILEPATH);
        if (file.exists()) {
            //返回打开的数据库
            return SQLiteDatabase.openOrCreateDatabase(file,null);
        } else {

            //文件不存在时（先创建目录路径）
            String dirPath=FILEPATH.substring(0,FILEPATH.lastIndexOf("\\")+1);
            File dir = new File(dirPath);
            if (!dir.exists()) {
                if (dir.mkdir())
                    Log.i(TAG, "创建数据库成功！");
                else
                    Log.i(TAG, "创建数据库失败！");
            }

            try {
                //得到资源管理器（将文件从Assets拷贝到应用数据库存储位置）
                AssetManager am = context.getAssets();
                InputStream stream = am.open("story.db");

                //写文件
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int count;
                while ((count = stream.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.flush();
                fos.close();
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            //目前数据库文件已生成（再次执行此方法）
            return openDatabase(context);
        }
    }
    /**
     * 插入一篇故事
     * @param context
     * @param title
     * @param content
     */
    public static void insertStory(Context context, String title, String content) {
        SQLiteDatabase db = openDatabase(context);
        Cursor cursor = db.rawQuery(
                "insert into story (title,content,count) values(?,?,?)",
                new String[]{title, content, "0"}
        );
        cursor.close();
        db.close();
    }



}
