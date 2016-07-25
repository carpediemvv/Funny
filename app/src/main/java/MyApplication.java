import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2016/6/13.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "c4e9104738e2747a6c63855e7d2a9b7d");
    }
}
