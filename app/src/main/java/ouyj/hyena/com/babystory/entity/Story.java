package ouyj.hyena.com.babystory.entity;

/**
 * 故事实体类
 */
public class Story {

    private int id;
    private String title;
    private String content;
    private String count;

    //增加文件名,图片,书ID


    public Story() { }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
}
