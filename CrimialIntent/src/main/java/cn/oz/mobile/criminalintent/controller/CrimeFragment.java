package cn.oz.mobile.criminalintent.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import cn.oz.mobile.criminalintent.model.Crime;
import cn.oz.mobile.criminalintent.model.CrimeLab;

import java.util.Date;
import java.util.UUID;

/**
 * 陋习管理Fragment控制器，用于显示陋习明细
 *
 * @author hucw
 * @version 1.0.0
 */
public class CrimeFragment extends Fragment {
    public static final String EXTRA_CRIME_ID = "cn.oz.mobile.criminalintent.model.crime.id";
    public static final String DIALOG_DATE = "crime.date.picker";
    public static final int REQUEST_DATE = 0;

    // 陋习实例对象
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolovedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID uuid = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(uuid);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 通过activity代码添加视图，传入fragment_crime生成并视图
        View view = inflater.inflate(R.layout.fragment_crime, container, false);

        // 实例化陋习说明输入框，同时监听内容改变事件
        mTitleField = (EditText)view.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getmTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // nothing to do
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // CharSequence s：用户输入字符串
                mCrime.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // nothing to do
            }
        });

        // 发生时间
        mDateButton = (Button)view.findViewById(R.id.crime_date);
        mDateButton.setText(DateFormat.format("yyyy-MM-dd HH:mm", mCrime.getmDate()).toString());
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mCrime.getmDate());

                // 设置DatePickerFragment目标Fragment，以回传日期
                datePickerFragment.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                datePickerFragment.show(getActivity().getSupportFragmentManager(), DIALOG_DATE);
            }
        });

        // 是否解决
        mSolovedCheckBox = (CheckBox)view.findViewById(R.id.crime_solved);
        mSolovedCheckBox.setChecked(mCrime.ismSolved());
        mSolovedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setmSolved(isChecked);
            }
        });
        return view;
    }

    /**
     * 陋习id，
     *
     * @param crimeId
     * @return
     */
    public static CrimeFragment newInstance(UUID crimeId){
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_CRIME_ID, crimeId);

        CrimeFragment crimeFragment = new CrimeFragment();
        crimeFragment.setArguments(bundle);
        return crimeFragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK)
            return;
        if(requestCode == REQUEST_DATE){
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_CRIME_DATE);
            mDateButton.setText(DateFormat.format("yyyy-MM-dd HH:mm", date).toString());
            mCrime.setmDate(date);
        }
    }
}
