package demp.cjx.com.cirleprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private List<HashMap<String, Object>> mData = new ArrayList<>();
    private MyCircleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.rv_circle_list);
        initData();
        adapter = new MyCircleAdapter(this, mData);
        lv.setAdapter(adapter);

    }

    private void initData() {
        HashMap<String, Object> params1 = new HashMap<>();
        params1.put("name", "流量/MB");
        params1.put("color", "#3F51B5");
        params1.put("total", "1000");
        params1.put("left", "200");
        mData.add(params1);
        HashMap<String, Object> params2 = new HashMap<>();
        params2.put("name", "通话/min");
        params2.put("color", "#303F9F");
        params2.put("total", "100");
        params2.put("left", "55");
        mData.add(params2);
        HashMap<String, Object> params3 = new HashMap<>();
        params3.put("name", "短信/条");
        params3.put("color", "#FF4081");
        params3.put("total", "500");
        params3.put("left", "99");
        mData.add(params3);
    }
}
