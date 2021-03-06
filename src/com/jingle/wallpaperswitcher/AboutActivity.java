package com.jingle.wallpaperswitcher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.about_activity);
        Button rightButton = (Button) findViewById(R.id.title_right);
        rightButton.setClickable(false);
        rightButton.setText("    ");
        Button alipay = (Button) findViewById(R.id.alipay);
        /*
		 * ImageView imageView = (ImageView) findViewById(R.id.wechat);
		 * imageView
		 * .setImageDrawable(getResources().getDrawable(R.drawable.wechat));
		 */
        alipay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                try {

                    Uri uri = Uri
                            .parse("alipayqr://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=https://qr.alipay.com/a6x08806v4ggobgudh3xede");
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(uri);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(AboutActivity.this,
                            "没有安装支付宝", Toast.LENGTH_SHORT)
                            .show();
                }
            }

        });

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
        if (id == R.id.action_about) {
            Intent intent = new Intent(AboutActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_exit) {
            ActivityCollector.finishAllActivity();
        }
        return super.onOptionsItemSelected(item);
    }
}
