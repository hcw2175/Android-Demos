package cn.oz.mobile.criminalintent.controller;

import android.support.v4.app.Fragment;
import cn.oz.mobile.criminalintent.base.SingleFragmentActivity;


/**
 * 陋习列表管理主要Activity
 *
 * @author hucw
 * @version 1.0.0
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
