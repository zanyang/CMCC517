package com.example.cmcc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cmcc.util.DebugLog;
import com.example.cmcc.util.StringCaculate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> strList = new ArrayList<>();
    private EditText et_1;
    private EditText et_2;
    private EditText et_3;
    private EditText et_4;

    //定义四个随机数
    int a;
    int b;
    int c;
    int d;
    private Button btn;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = (TextView) findViewById(R.id.tv_result);
        btn = (Button) findViewById(R.id.btn);
        et_1 = (EditText) findViewById(R.id.et_1);
        et_2 = (EditText) findViewById(R.id.et_2);
        et_3 = (EditText) findViewById(R.id.et_3);
        et_4 = (EditText) findViewById(R.id.et_4);

        et_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                a = Integer.parseInt(et_1.getText().toString());
            }
        });
        et_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                b = Integer.parseInt(et_2.getText().toString());
            }
        });
        et_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                c = Integer.parseInt(et_3.getText().toString());
            }
        });
        et_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                d = Integer.parseInt(et_4.getText().toString());
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "+-*/";
                String result = "";
                permutation1(s, result, s.length());


                //输出运算符数组
                for (int i = 0; i < strList.size(); i++) {
                    DebugLog.e(strList.get(i));
                    //截取字符串前三位
                    String x = strList.get(i).substring(0, 1);
                    String y = strList.get(i).substring(1, 2);
                    String z = strList.get(i).substring(2, 3);

                    String strs = a + x + b + y + c + z + d;
                    DebugLog.e(a + "  " + x + "  " + b + "  " + y + "  " + c + "  " + z + "  " + d);
                    StringCaculate caculate = new StringCaculate();
                    caculate.parse(strs);
                    DebugLog.e("计算结果" + caculate.getResult());

                    if (caculate.getResult() != null) {
                        if (caculate.getResult().contains("517")) {
                            DebugLog.e("###################" + a + "  " + x + "  " + b + "  " + y + "  " + c + "  " + z + "  " + d);
                            DebugLog.e("###################" + caculate.getResult());
                            tvResult.setText(strs + "  = 517");
                            return;
                        }
                    }

                }

            }
        });


    }

    public static void combination1() {
            /*全组合：
             * 思路是利用二进制的特性，每次加1即可遍历所有位的不同情况，很好理解
            代码同上
                */
        String arr[] = {"a", "b", "c"};
        int all = arr.length;
        int nbit = 1 << all;
        for (int i = 0; i < nbit; i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < all; j++) {
                if ((i & (1 << j)) != 0) {
                    sb.append(arr[j]);
                }
            }
//            DebugLog.e(">>>>>>>>>>>>>>>"+sb);
        }
    }


    public void permutation1(String str, String result, int len) {
        /* 全排列 递归实现
      递归树：
          str:          a            b                c
                      ab ac         ba   bc         ca   cb
        result:        abc acb        bac    bca      cab   cba
         */

        //结果 开始传入""   空字符进入   len   是这个数的长度
        if (result.length() == len) {            //表示遍历完了一个全排列结果
//            System.out.println(result);
            strList.add(result);
        } else {
            for (int i = 0; i < str.length(); i++) {
                if (result.indexOf(str.charAt(i)) < 0) {    //返回指定字符在此字符串中第一次出现处的索引。
                    //System.out.println("字母："+str.charAt(i));
                    permutation1(str, result + str.charAt(i), len);
                }
            }
        }
    }


}
