package cn.oz.mobile.criminalintent.controller;

import android.support.v4.app.Fragment;
import cn.oz.mobile.criminalintent.base.SingleFragmentActivity;

import java.util.UUID;

/**
 * 请使用CrimePagerActivity代替
 *
 * 陋习明细Activity
 *
 * @author hucw
 * @version 1.0.0
 */
@Deprecated
public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}
