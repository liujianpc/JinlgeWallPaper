package com.jingle.wallpaperswitcher;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.WindowManager;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyIntentService extends IntentService {

    private Handler mHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 0:
                /*
				 * Toast.makeText(getApplicationContext(), "�����������",
				 * Toast.LENGTH_SHORT).show();
				 */
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                            getApplicationContext());
                    alertDialog.setTitle("提示ʾ");
                    alertDialog.setMessage("网络错误！");
                    alertDialog.setCancelable(false);
                    alertDialog.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    // TODO Auto-generated method stub
                                    Intent intent = new Intent(
                                            android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            });
                    alertDialog.setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    // TODO Auto-generated method stub

                                }
                            });
                    final AlertDialog dialog = alertDialog.create();
                    dialog.getWindow().setType(
                            WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);
                    break;
                case 1:
                    Toast.makeText(getApplicationContext(), "IO读写错误",
                            Toast.LENGTH_SHORT).show();
                    break;
               

                default:
                    break;
            }
        }

    };

    public MyIntentService() {
        super("MyIntentService");
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onHandleIntent(Intent arg0) {
        // TODO Auto-generated method stub
        Document document = null;
        int resourceId = arg0.getIntExtra("resourceId", 0);
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        String imageUrl = null;
        Bitmap bitmap = null;
        Message message = new Message();
        Message message2 = new Message();
        switch (resourceId) {
            case 0:
                int index = (int) (Math.random() * 4000);
                imageUrl = "http://img.infinitynewtab.com/wallpaper/"
                        + String.valueOf(index) + ".jpg";

                break;
            case 1:// bing��ֽ
                // ���·���Ϊʹ��API������json���������
                // String response = null;
                // String url1 =
                // "http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&nc=1361089515117&FORM=HYLH1";
                // HttpURLConnection connection = null;
                // try {
                // URL url_1 = new URL(url1);
                // connection = (HttpURLConnection) url_1.openConnection();
                // connection.setRequestMethod("GET");
                // connection.setReadTimeout(60000);
                // connection.setConnectTimeout(60000);
                // connection.setDoInput(true);
                // InputStream in = connection.getInputStream();
                // BufferedReader bufferedReader = new BufferedReader(
                // new InputStreamReader(in, "utf-8"));
                // StringBuilder responseBuilder = new StringBuilder();
                // String line;
                // while ((line = bufferedReader.readLine()) != null) {
                // responseBuilder.append(line);
                // }
                // response = responseBuilder.toString();
                //
                // } catch (Exception e) {
                // // TODO: handle exception
                // Log.e("exception", "�������");
                // message.what = 0;
                // mHandler.sendMessage(message);
                // } finally {
                // if (connection != null) {
                // try {
                // connection.disconnect();
                // } catch (Exception e2) {
                // // TODO: handle exception
                // Log.e("exception", "��Դ�رմ���");
                // message.what = 2;
                // mHandler.sendMessage(message);
                // }
                // }
                //
                // }
                //
                // String relative_imgUrl = null;
                // try {
                // JSONObject jsonObject = new JSONObject(response);
                // JSONArray jsonArray = jsonObject.getJSONArray("images");
                // JSONObject jsonObject2 = jsonArray.getJSONObject(0);
                // relative_imgUrl = jsonObject2.getString("url");
                //
                // } catch (JSONException e1) {
                // // TODO Auto-generated catch block
                // Log.e("Exception", "JsonException");
                // message.what = 3;
                // mHandler.sendMessage(message);
                // }
                //
                // String imgUrl = "http://cn.bing.com" + relative_imgUrl;
                // WallpaperManager wallpaperManager1 = WallpaperManager
                // .getInstance(this);
                // Bitmap bitmap1 = getImageBitmap(imgUrl);
                // if (bitmap1 == null) {
                // message.what = 0;
                // mHandler.sendMessage(message);
                // return;
                // }
                // try {
                // wallpaperManager1.setBitmap(bitmap1);
                // } catch (IOException e) {
                // // TODO Auto-generated catch block
                // message.what = 1;
                // mHandler.sendMessage(message);
                // }
                //
                // time = 1 * 60 * 1000;

			/*
			 * ��jsoup����ȡ
			 */

                try {
                    document = Jsoup.connect("https://bing.ioliu.cn/").get();
                    Elements links = document.select("a.thumbnail");
                    List<String> urlList = new ArrayList<>();
                    for (Element link : links) {
                        urlList.add(link.attr("href"));
                    }
                    int index1 = (int) (Math.random() * 4);
                    imageUrl = urlList.get(index1);
                } catch (IOException e) {
                    message.what = 0;
                    mHandler.sendMessage(message);
                } catch (Exception e) {
                    // TODO: handle exception
                    message.what = 0;
                    mHandler.sendMessage(message);
                }

                break;

            case 2:

                String chanyouSite = "http://chanyouji.com/?page="
                        + String.valueOf(((int) (Math.random() * 361)));
                try {
                    document = Jsoup.connect(chanyouSite).get();
                    Elements linkElements = document.select("a:has(img[alt=288])");
                    Element link = linkElements.get(((int) (Math.random() * 20)));
                    String absLink = link.absUrl("href");
                    Document childDocument = Jsoup.connect(absLink).get();
                    Elements imgElements = childDocument
                            .select("div.social-share-button");
                    imageUrl = imgElements.get(0).attr("data-img");
                } catch (IOException e) {
                    // TODO: handle exception
                    message.what = 0;
                    mHandler.sendMessage(message);
                } catch (Exception e1) {
                    // TODO: handle exception
                    message.what = 0;
                    mHandler.sendMessage(message);
                }

                break;
            case 3:
                String wallHeaven = "https://alpha.wallhaven.cc/latest?page="
                        + String.valueOf(((int) (Math.random() * 11545 + 1)));
                try {
                    document = Jsoup.connect(wallHeaven).get();
                    Elements imageElements = document.select("img[alt=loading]");
                    Element imageElement = imageElements
                            .get(((int) (Math.random() * 24)));
                    String smallImgaUrl = imageElement.attr("data-src").replace(
                            "alpha", "wallpapers");
                    imageUrl = smallImgaUrl.replace("thumb/small/th",
                            "full/wallhaven");
                } catch (IOException e) {
                    // TODO: handle exception
                    message.what = 0;
                    mHandler.sendMessage(message);
                } catch (Exception e) {
                    // TODO: handle exception
                    message.what = 0;
                    mHandler.sendMessage(message);
                }
                break;
            case 4:
                String simpleDesktops = "http://simpledesktops.com/browse/"
                        + String.valueOf(((int) (Math.random() * 48 + 1))) + "/";
                try {
                    document = Jsoup.connect(simpleDesktops).get();
                    Elements linksElements = document.select("img[title]");
                    Element linkElement = linksElements
                            .get(((int) (Math.random() * 28)));
                    String tempImageUrl = linkElement.attr("src");
                    imageUrl = tempImageUrl.replaceAll(".295x184_q100.png", "");

                } catch (IOException e) {
                    // TODO: handle exception
                    message.what = 0;
                    mHandler.sendMessage(message);
                } catch (Exception e) {
                    // TODO: handle exception
                    message.what = 0;
                    mHandler.sendMessage(message);
                }
                break;
            case 5:
                int year = 2008 + (int) (Math.random() * 9);
                String urlString = "http://desktopography.net/exhibition-"
                        + String.valueOf(year) + "/";
                try {
                    document = Jsoup.connect(urlString).get();
                    Elements links2 = document.select("div.overlay-background");
                    int links_size = links2.size();
                    int link_random = (int) (Math.random() * links_size);
                    Element link2 = links2.get(link_random);
                    Document document_child = null;
                    try {
                        document_child = Jsoup.connect(link2.attr("href")).get();
                    } catch (IOException e) {
                        message.what = 0;
                        mHandler.sendMessage(message);
                    }
                    Elements a_links = document_child
                            .select("a:contains(1920x1080)");
                    Element a_link = a_links.get(0);
                    imageUrl = a_link.attr("href");

                } catch (IOException e) {
                    message.what = 0;
                    mHandler.sendMessage(message);
                } catch (Exception e) {
                    // TODO: handle exception
                    message.what = 0;
                    mHandler.sendMessage(message);
                }

                break;
            case 6:
                String urlString2 = "http://www.vladstudio.com/zh/wallpapers/?skip="
                        + String.valueOf((int) (Math.random() * 21 * 24));
                try {
                    document = Jsoup.connect(urlString2).get();
                    Elements imgElements = document.select("img.framed");
                    Element imgElement = imgElements
                            .get(((int) (Math.random() * 24)));
                    String imgStringShort = imgElement.attr("src");
                    imageUrl = imgStringShort
                            .replace("480x320", "1280x1024_signed");
                } catch (IOException e1) {
                    message.what = 0;
                    mHandler.sendMessage(message);
                } catch (Exception e) {
                    // TODO: handle exception
                    message.what = 0;
                    mHandler.sendMessage(message);
                }
                break;
            case 7:
                try {
                    imageUrl = getImageUrl("meinv", 26);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    message.what = 0;
                    mHandler.sendMessage(message);
                }
                break;
            case 8:
                try {
                    imageUrl = getImageUrl("chemo", 2);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    message.what = 0;
                    mHandler.sendMessage(message);
                }
                break;
            case 9:
                try {
                    imageUrl = getImageUrl("tiyu", 13);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    message.what = 0;
                    mHandler.sendMessage(message);
                }
                break;
            case 10:
                try {
                    imageUrl = getImageUrl("dongman", 22);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    message.what = 0;
                    mHandler.sendMessage(message);
                }
                break;
            case 11:
                try {
                    imageUrl = getImageUrl("youxi", 14);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    message.what = 0;
                    mHandler.sendMessage(message);
                }
                break;
            case 12:
                try {
                    imageUrl = getImageUrl("yingshi", 17);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    message.what = 0;
                    mHandler.sendMessage(message);
                }
            case 13:
                try {
                    imageUrl = getImageUrl("keai", 2);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    message.what = 0;
                    mHandler.sendMessage(message);
                }
            case 14:
                try {
                    imageUrl = getImageUrl("mingxing", 25);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    message.what = 0;
                    mHandler.sendMessage(message);
                }
                break;

            case 15:
                String[] urls = {
                        "http://www.nationalgeographic.com.cn/photography/photo_of_the_day/",
                        "http://www.nationalgeographic.com.cn/photography/photo_tips/"};
                switch ((int) (Math.random() * 2)) {

                    case 0:
                        try {
                            int rand = (int) (Math.random() * 3);
                            document = Jsoup.connect(urls[0]).get();
                            Elements topics = document.select("dt > a");
                            Element topic = topics.get(0);
                            String linkString = topic.absUrl("href");
                            Document childDocument = Jsoup.connect(linkString).get();
                            if (rand == 0) {
                                imageUrl = childDocument.select("img[rel]").get(0)
                                        .attr("src");
                            } else if (rand == 1) {
                                String getPic = childDocument.select("a.next").get(0)
                                        .absUrl("href");
                                imageUrl = Jsoup.connect(getPic).get()
                                        .select("img[rel]").attr("src");
                            } else if (rand == 2) {
                                String getPic = childDocument.select("a.up").get(0)
                                        .absUrl("href");
                                imageUrl = Jsoup.connect(getPic).get()
                                        .select("img[rel]").attr("src");
                            }

                        } catch (IOException e) {
                            // TODO: handle exception
                            message.what = 0;
                            mHandler.sendMessage(message);
                        } catch (Exception e) {
                            // TODO: handle exception
                            message.what = 0;
                            mHandler.sendMessage(message);
                        }
                        break;
                    case 1:
                        try {
                            document = Jsoup.connect(urls[1]).get();
                            Elements topics = document.select("dt > a");
                            Element topic = topics.get(((int) (Math.random() * topics
                                    .size())));
                            Document childDocument = Jsoup
                                    .connect(topic.absUrl("href")).get();
                            Elements pics = childDocument
                                    .select("div[style=text-align: center;] > img");
                            imageUrl = pics.get(((int) (Math.random() * pics.size())))
                                    .attr("src");

                        } catch (IOException e) {
                            // TODO: handle exception
                            message.what = 0;
                            mHandler.sendMessage(message);
                        } catch (Exception e) {
                            // TODO: handle exception
                            message.what = 0;
                            mHandler.sendMessage(message);
                        }
                        break;

                    default:
                        break;
                }

                break;
            default:
                break;
        }

        bitmap = getImageBitmap(imageUrl);

        if (bitmap == null) {

            message2.what = 0;
            mHandler.sendMessage(message2);
            return;
        }
        try {
            wallpaperManager.setBitmap(bitmap);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            message.what = 1;
            mHandler.sendMessage(message);
        }

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int time = arg0.getIntExtra("timeMinutes", 60) * 60 * 1000;
        long triggerTime = SystemClock.elapsedRealtime() + time;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, pi);

    }

    private Bitmap getImageBitmap(String url) {
        URL imgUrl = null;
        Bitmap bitmap = null;
        try {
            imgUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imgUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public String getImageUrl(String content, int range) throws Exception {
        String meinv = "http://desk.zol.com.cn/" + content + "/1920x1080/hot_"
                + String.valueOf((((int) (Math.random() * range)))) + ".html";
        Document document = Jsoup.connect(meinv).get();
        Elements imgElements = document.select("a.pic");
        Element imgElement = imgElements.get(((int) (Math.random() * 15)));
		/*
		 * int size = Integer.parseInt(imgElement.child(1).text() .replace("��)",
		 * "").replace("(", ""));
		 */
        String imageString = imgElement.absUrl("href");
        int index1 = Integer.parseInt(imageString.split("_")[1]);
        String requestUrl = imageString.split("_")[0] + "_"
                + String.valueOf(index1 + ((int) (Math.random() * 3))) + "_"
                + imageString.split("_")[2];
        Document childDocument = Jsoup.connect(requestUrl).get();
        Element childImgElement = childDocument.select("a#1920x1080").get(0);
        String requeString = childImgElement.absUrl("href");
        Document grandSonDocument = Jsoup.connect(requeString).get();
        Elements imgreal = grandSonDocument.select("img");
        String imageUrl = imgreal.get(0).attr("src");

        return imageUrl;

    }

}
