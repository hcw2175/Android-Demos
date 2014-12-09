package cn.oz.mobile.criminalintent.model;

import java.util.Date;
import java.util.UUID;

/**
 * 陋习管理
 *
 * @author hucw
 * @version 1.0.0
 */
public class Crime {

    // fields ===============================
    private UUID mId;               // 陋习id
    private String mTitle;          // 陋习描述
    private Date mDate;             // 记录时间
    private boolean mSolved;        // 是否已解决

    // constructor ==========================
    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    @Override
    public String toString() {
        return mTitle;
    }

    // setter/getter =========================
    public String getmTitle() {
        return mTitle;
    }
    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public UUID getmId() {
        return mId;
    }
    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public boolean ismSolved() {
        return mSolved;
    }
    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    public Date getmDate() {
        return mDate;
    }
    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}
