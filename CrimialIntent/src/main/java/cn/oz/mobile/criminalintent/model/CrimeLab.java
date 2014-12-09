package cn.oz.mobile.criminalintent.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 陋习数据列表对象
 *
 * @author hucw
 * @version 1.0.0
 */
public class CrimeLab {

    // fields ================================
    private static CrimeLab sCrimeLab;      // 静态变量
    private Context mAppcontext;            // Android上下文

    private List<Crime> mCrimes;            // 陋习列表

    // constructor =========================
    public CrimeLab(Context appContext){
        mAppcontext = appContext;
        mCrimes = new ArrayList<Crime>();

        // 模拟生成100个陋习
        for(int i=0; i<100; i++){
            Crime crime = new Crime();
            crime.setmTitle("第" + (i+1) + "条陋习");
            crime.setmSolved( i % 2 == 0);
            mCrimes.add(crime);

        }
    }

    // methods =============================
    public static CrimeLab get(Context appContext){
        if(null == sCrimeLab){
            sCrimeLab = new CrimeLab(appContext.getApplicationContext());
        }
        return sCrimeLab;
    }

    public List<Crime> getmCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID uuid){
        if(mCrimes == null)
            return null;

        for(Crime crime: mCrimes){
            if(crime.getmId().equals(uuid))
                return crime;
        }
        return null;
    }
}
