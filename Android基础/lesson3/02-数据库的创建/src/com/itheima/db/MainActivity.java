package com.itheima.db;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        MySqliteHelper helper = new MySqliteHelper(MainActivity.this);
        
//        helper.getWritableDatabase();
        helper.getReadableDatabase();
    }

}
