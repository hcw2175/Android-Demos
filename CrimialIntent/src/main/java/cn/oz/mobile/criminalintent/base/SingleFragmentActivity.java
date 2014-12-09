package cn.oz.mobile.criminalintent.base;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import cn.oz.mobile.criminalintent.controller.R;

/**
 * 托管Fragment通用Activity
 *
 * @author hucw
 * @version 1.0.0
 */
public abstract class SingleFragmentActivity extends FragmentActivity{

    // 子类回传Fragment
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // 获取FragmentManager管理Fragment的队列
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentContainer = fragmentManager.findFragmentById(R.id.fragmentContainer);

        // 判断fragmentContainer是否已存在于FragmentManager队列中
        if(null == fragmentContainer){
            fragmentContainer = createFragment();

            // 若Fragment为空，则使用fragmentManager重新开启一个事务
            // FragmentTransaction使用add方法把新的Fragment添加到目标视图R.id.fragmentContainer中，最后提交事务
            fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragmentContainer).commit();
        }
    }
}
