package cn.oz.mobile.criminalintent.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import cn.oz.mobile.criminalintent.model.Crime;
import cn.oz.mobile.criminalintent.model.CrimeLab;

import java.util.List;
import java.util.UUID;

/**
 * 陋习明细Pager Activity,支持左右滑动
 *
 * @author hucw
 * @version 1.0.0
 */
public class CrimePagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置viewPager
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        // 获取陋习数组
        mCrimes = CrimeLab.get(this).getmCrimes();

        // 获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // 设置Adapter为FragmentStatePagerAdapter匿名示例
        // FragmentStatePagerAdapter为代理，负责管理与ViewPager的对话并协同工作
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                Crime crime = mCrimes.get(i);
                return CrimeFragment.newInstance(crime.getmId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        // 设置ViewPager显示点击的Crime，而不是第一条的信息
        final UUID crimeId = (UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for(int i=0; i < mCrimes.size(); i++){
            if(crimeId.equals(mCrimes.get(i).getmId())){
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        // 当页面发生变化后，同步设置标题栏
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                // do nothing
            }

            @Override
            public void onPageSelected(int i) {
                Crime crime = mCrimes.get(i);
                setTitle(crime.getmTitle());
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                // do nothing
            }
        });
    }
}
