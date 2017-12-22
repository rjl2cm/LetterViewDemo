package com.example.franer.letterviewdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.franer.letterviewdemo.view.LetterView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView mLV_Content;
    private TextView mTV_Dialog;
    private LetterView mLeV_Index;
    private HashMap<String, Integer> mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mLV_Content = $(R.id.listview);
        mTV_Dialog = $(R.id.dialog);
        mLeV_Index = $(R.id.letterview);

        mLeV_Index.setTextDialog(mTV_Dialog);

        //初始化数据
        List<Map<String, String>> datas = initData();
        //数据排个列
        Collections.sort(datas, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                return o1.get("name").toString().compareTo(o2.get("name").toString());
            }
        });
        //生成索引
        mIndex = new HashMap<>();
        for (int i = 0 , len = datas.size(); i < len; i++){
            Map<String, String> map = datas.get(i);
            String charIndex = map.get("name").toString().substring(0,1);
            if (!mIndex.containsKey(charIndex)){
                mIndex.put(charIndex, i);
            }
        }
        mIndex.put("#", datas.size());//最后一个#号的位置

        //建立索引连接
        mLeV_Index.setOnTouchingLetterChangedListener(new LetterView.OnTouchingLetterChangedListener(){

            @Override
            public void onTouchingLetterChanged(String s) {
                if (mIndex.containsKey(s)){
                    int position = mIndex.get(s);
                    mLV_Content.smoothScrollToPosition(position);
                }
            }
        });

        //初始化ListView
        ListSimpleAdapter adapter = new ListSimpleAdapter(this, datas, R.layout.item_view,
                new String[]{"name", "age"}, new int[]{R.id.name, R.id.age});
        mLV_Content.setAdapter(adapter);
    }

    private List<Map<String, String>> initData() {
        List<Map<String, String>> datas = new ArrayList<>();
        Map<String, String> data0 = new HashMap<>();
        data0.put("name", "Alien");
        data0.put("age", "24");
        datas.add(data0);

        Map<String, String> data1 = new HashMap<>();
        data1.put("name", "Green");
        data1.put("age", "21");
        datas.add(data1);

        Map<String, String> data2 = new HashMap<>();
        data2.put("name", "Bill");
        data2.put("age", "32");
        datas.add(data2);

        Map<String, String> data3 = new HashMap<>();
        data3.put("name", "ELLWA");
        data3.put("age", "30");
        datas.add(data3);

        Map<String, String> data4 = new HashMap<>();
        data4.put("name", "TIM");
        data4.put("age", "18");
        datas.add(data4);

        Map<String, String> data5 = new HashMap<>();
        data5.put("name", "FRANK");
        data5.put("age", "20");
        datas.add(data5);

        Map<String, String> data6 = new HashMap<>();
        data6.put("name", "Andid");
        data6.put("age", "17");
        datas.add(data6);

        Map<String, String> data7 = new HashMap<>();
        data7.put("name", "ANS");
        data7.put("age", "71");
        datas.add(data7);

        Map<String, String> data8 = new HashMap<>();
        data8.put("name", "RANZER");
        data8.put("age", "14");
        datas.add(data8);

        Map<String, String> data9 = new HashMap<>();
        data9.put("name", "CARL");
        data9.put("age", "32");
        datas.add(data9);

        Map<String, String> data10 = new HashMap<>();
        data10.put("name", "TIFFANY");
        data10.put("age", "14");
        datas.add(data10);

        Map<String, String> data11 = new HashMap<>();
        data11.put("name", "WANNDER");
        data11.put("age", "41");
        datas.add(data11);

        Map<String, String> data12 = new HashMap<>();
        data12.put("name", "STEVEN");
        data12.put("age", "66");
        datas.add(data12);



        return datas;
    }


    public <T extends View> T $(int rId){
        return (T)findViewById(rId);
    }


    class ListSimpleAdapter extends SimpleAdapter{

        /**
         * Constructor
         *
         * @param context  The context where the View associated with this SimpleAdapter is running
         * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
         *                 Maps contain the data for each row, and should include all the entries specified in
         *                 "from"
         * @param resource Resource identifier of a view layout that defines the views for this list
         *                 item. The layout file should include at least those named views defined in "to"
         * @param from     A list of column names that will be added to the Map associated with each
         *                 item.
         * @param to       The views that should display column in the "from" parameter. These should all be
         *                 TextViews. The first N views in this list are given the values of the first N columns
         */
        public ListSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

    }
}
