package com.jingle.wallpaperswitcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
	
	private static boolean isChecked = false;
    private static String[] resouces = {"ifinityֽ", "微软必应ֽ", "蝉游记",
            "WallpaperHeaven", "simpleDesktops", "desktopography",
            "vladstudio", "美女", "车模", "体育", "动漫", "游戏", "影视", "萌宠", "明星",
            "国家地理"};
    private static String[] times = {"30分钟", "1小时", "2小时", "4小时", "6小时",
            "8小时", "10小时", "12小时", "24小时"};
    private Spinner spinnerBizhi, spinnerTime;
    private ArrayAdapter<String> adapterBizhi, adapterTime;
    private int resouceId;
    private int timeMinutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        spinnerBizhi = (Spinner) findViewById(R.id.bizhi);
        spinnerTime = (Spinner) findViewById(R.id.time);
        adapterBizhi = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, resouces);
        adapterTime = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, times);
        adapterBizhi
                .setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        adapterTime
                .setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerBizhi.setAdapter(adapterBizhi);
        spinnerTime.setAdapter(adapterTime);
        spinnerBizhi.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                switch (arg2) {
                    case 0:
                        resouceId = 0;
                        break;
                    case 1:
                        resouceId = 1;
                        Toast.makeText(MainActivity.this, "微软必应每天一张壁纸",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        resouceId = 2;
                        break;
                    case 3:
                        resouceId = 3;
                        break;
                    case 4:
                        resouceId = 4;
                        break;
                    case 5:
                        resouceId = 5;
                        break;
                    case 6:
                        resouceId = 6;
                        break;
                    case 7:
                        resouceId = 7;
                        break;
                    case 8:
                        resouceId = 8;
                        break;
                    case 9:
                        resouceId = 9;
                        break;
                    case 10:
                        resouceId = 10;
                        break;
                    case 11:
                        resouceId = 11;
                        break;
                    case 12:
                        resouceId = 12;
                        break;
                    case 13:
                        resouceId = 13;
                        break;
                    case 14:
                        resouceId = 14;
                        break;
                    case 15:
                        resouceId = 15;
                        break;

                    default:
                        resouceId = 0;
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spinnerTime.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                switch (arg2) {
                    case 0:
                        timeMinutes = 30;

                        break;
                    case 1:
                        timeMinutes = 60;
                        break;
                    case 2:
                        timeMinutes = 120;
                        break;
                    case 3:
                        timeMinutes = 240;
                        break;
                    case 4:
                        timeMinutes = 360;
                        break;
                    case 5:
                        timeMinutes = 480;
                        break;
                    case 6:
                        timeMinutes = 600;
                        break;
                    case 7:
                        timeMinutes = 720;

                        break;
                    case 8:
                        timeMinutes = 1440;
                        break;

                    default:
                        timeMinutes = 30;
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spinnerBizhi.setVisibility(View.VISIBLE);
        spinnerTime.setVisibility(View.VISIBLE);

        SwitchButton switchButton = (SwitchButton) findViewById(R.id.switch_button);
        switchButton.setChecked(isChecked);
        switchButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this,
                        MyIntentService.class);
                if (arg1 == true) {
                	isChecked = true;
                    Toast.makeText(MainActivity.this, "已经设置自动更新壁纸ֽ", Toast.LENGTH_SHORT).show();
                    intent.putExtra("resourceId", resouceId);
                    intent.putExtra("timeMinutes", timeMinutes);
                    startService(intent);
                } else {
                	isChecked = false;
                    Toast.makeText(MainActivity.this, "已经关闭自动更新壁纸ֽ", Toast.LENGTH_SHORT).show();
                    stopService(intent);
                }
            }
        });

		/*
         * Button switchButton = (Button) findViewById(R.id.switchButton);
		 * Button stopButton = (Button) findViewById(R.id.stop);
		 * switchButton.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { // TODO Auto-generated
		 * method stub Intent intent = new Intent(MainActivity.this,
		 * MyIntentService.class); intent.putExtra("resourceId", resouceId);
		 * intent.putExtra("timeMinutes", timeMinutes); startService(intent);
		 * 
		 * } });
		 * 
		 * stopButton.setOnClickListener(new OnClickListener() { Intent intent =
		 * new Intent(MainActivity.this, MyIntentService.class);
		 * 
		 * @Override public void onClick(View arg0) { // TODO Auto-generated
		 * method stub stopService(intent); } });
		 */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_about:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
