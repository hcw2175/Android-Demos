package cn.oz.mobile.criminalintent.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 陋习发生时间选择对话框
 *
 * @author hucw
 * @version 1.0.0
 */
public class DatePickerFragment extends DialogFragment {
    public static final String EXTRA_CRIME_DATE = "cn.oz.mobile.criminalintent.model.crime.date";

    private Date mDate;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedIntanceState) {
        mDate = (Date)getArguments().getSerializable(EXTRA_CRIME_DATE);

        // 获取时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
        DatePicker datePicker = (DatePicker)v.findViewById(R.id.dialog_date_datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDate = new GregorianCalendar(year, month, dayOfMonth).getTime();
                getArguments().putSerializable(EXTRA_CRIME_DATE, mDate);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.title_crime_date_picker)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .create();
    }

    /**
     * 实例化DatePickerFragment方法，以回传用户选的的日期
     *
     * @param date 日期
     * @return DatePickerFragment示例
     */
    public static DatePickerFragment newInstance(Date date){
        Bundle bundle  = new Bundle();
        bundle.putSerializable(EXTRA_CRIME_DATE, date);

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(bundle);
        return datePickerFragment;
    }

    /**
     * 附加日期数据作为参数附加到intent
     * @param resultCode 回传标识符
     */
    private void sendResult(int resultCode){
        if(getTargetFragment() == null)
            return;

        Intent intent = new Intent();
        intent.putExtra(EXTRA_CRIME_DATE, mDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
