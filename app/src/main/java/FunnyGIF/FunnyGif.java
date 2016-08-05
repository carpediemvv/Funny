package FunnyGIF;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2016/7/30.
 */
public class FunnyGif extends BmobObject {
    private String textContent;
    private BmobFile gifContent;

    public BmobFile getGifContent() {
        return gifContent;
    }

    public void setGifContent(BmobFile gifContent) {
        this.gifContent = gifContent;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }


}
