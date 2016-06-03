package com.itheima.lifecycle;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
/*
 *  activity 的生命周期 
 *  	任何一个对象 从 创建 到最后销毁 , 整个 过程 称之为生命周期 
 *  
 *  	而生命周期的方法指的是一个对象 从 最开 创建, 到最后 销毁整个 过程中,特定的时间段 会
 *  	执行的方法叫做 生命 周期的方法 
 *  
 * 	
 * 	 servlet的生命周期的方法 ,是 服务器 调用的
 *   android中 ,四大 组件的 生命周期的方法 , 是android 系统调用 
 * 	
 * 	  但凡 涉及 到 组件的  方法 只要是 以  onXxx打头 的, 这些方法 都不是你调用的, 是有 系统去 调用的 
 * 
 */
public class LifeCycleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        
        System.out.println("  onCreate  调用了  ...");
    }
    
    @Override
    protected void onDestroy() {
    	System.out.println("onDestroy  调用了 ");
    	super.onDestroy();
    }
    
    //当 activity 可见的时候 会被调用 
    @Override
    protected void onStart() {
    	super.onStart();
    	System.out.println("onstart");
    }
    
    //当 activity 不 可见的时候 会被调用 
    @Override
    protected void onStop() {
    	super.onStop();
    	System.out.println("onStop");
    }
    
    // 当 界面 失去 焦点的 时候 被调用 
    // 失去 焦点的 时候 
    @Override
    protected void onPause() {
    	super.onPause();
    	System.out.println("onpause ");
    }
    
    //当 界面 获得焦点的时候 被调用 
    @Override
    protected void onResume() {
    	super.onResume();
    	System.out.println("onResume  ");
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();
    	System.out.println("onRestart  ");
    }
    
    
/*
   
   
    
    
    
   
    */
}
