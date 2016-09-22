package com.carpediem.vv.funny.bean.VideoBean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/9/22.
 */

public class VideoBean extends BmobObject {
    public String getVideolink() {
        return videolink;
    }

    public void setVideolink(String videolink) {
        this.videolink = videolink;
    }

    private String videolink;
}
