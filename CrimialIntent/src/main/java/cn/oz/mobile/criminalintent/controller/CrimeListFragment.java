package cn.oz.mobile.criminalintent.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import cn.oz.mobile.criminalintent.model.Crime;
import cn.oz.mobile.criminalintent.model.CrimeLab;

import java.util.List;

/**
 * 陋习列表管理Fragment控制器，用于显示陋习明细
 *
 * @author hucw
 * @version 1.0.0
 */
public class CrimeListFragment extends ListFragment{
    private static final String TAG = "CrimeListFragment";

    private List<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.title_crime_fragment_list);
        mCrimes = CrimeLab.get(getActivity()).getmCrimes();

        CrimeAdapter crimeAdapter = new CrimeAdapter(mCrimes);
        setListAdapter(crimeAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime crime = ((CrimeAdapter)getListAdapter()).getItem(position);
        Log.d(TAG, crime.getmTitle() + " was clicked.");

        // 启动Crime明细Activity
        // Intent intent  = new Intent(getActivity(), CrimeActivity.class);

        // 启动CrimePagerActivity代替CrimeActity
        Intent intent  = new Intent(getActivity(), CrimePagerActivity.class);
        intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getmId());
        startActivity(intent);
    }

    // CrimeAdapter内部类，用于与Crime交互
    public class CrimeAdapter extends ArrayAdapter<Crime>{

        // 超类构造函数，传入0以达到使用目标布局效果
        public CrimeAdapter(List<Crime> crimes){
           super(getActivity(), 0, crimes);
        }

        /**
         * 覆写getView方法，以返回指定的布局
         *
         * @param position    位置
         * @param convertView 覆盖的视图
         * @param parent      父视图
         * @return 新的布局视图
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);

            // 对list_item_crime布局中的组件赋值
            Crime crime = getItem(position);
            TextView titleTextView = (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(crime.getmTitle());

            TextView dateTextView = (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(DateFormat.format("yyyy-MM-dd HH:mm", crime.getmDate()));

            CheckBox solvedCheckBox = (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckbox);
            solvedCheckBox.setChecked(crime.ismSolved());

            return convertView;
        }
    }
}
