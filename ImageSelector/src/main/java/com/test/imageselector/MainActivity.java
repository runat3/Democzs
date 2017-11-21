package com.test.imageselector;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements View.OnClickListener
{


    private GridView gv_image;
    private List<ImageModel> imgList = new ArrayList<ImageModel>();
    private MyImageAdapter myImageAdapter;
    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if (msg.what == 0x110)
            {
                myImageAdapter.notifyDataSetChanged();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv_image = (GridView) findViewById(R.id.gv_image);
        myImageAdapter = new MyImageAdapter();
        gv_image.setAdapter(myImageAdapter);
        initData();
    }

    private void initData()
    {

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            Toast.makeText(MainActivity.this, "SdCard unused", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread()
        {
            @Override
            public void run()
            {
                Uri imgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(imgUri, null,
                        MediaStore.Images.Media.MIME_TYPE + " = ? or " + MediaStore.Images.Media.MIME_TYPE + " = ?",
                        new String[]{"image/png", "image/jpeg"}, MediaStore.Images.Media.DATE_MODIFIED);
                while (cursor.moveToNext())
                {
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    ImageModel imageModel = new ImageModel();
                    imageModel.setImgPath(path);
                    imgList.add(imageModel);
                    //// TODO: 2017/11/21 getPathDirName
                }
                cursor.close();
                mHandler.sendEmptyMessage(0x110);
                super.run();
            }
        }.start();
    }

    @Override
    public void onClick(View v)
    {

    }


    private class MyImageAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            Log.e("size",imgList.size()+"");
            return imgList.size();
        }

        @Override
        public Object getItem(int position)
        {
            return null;
        }

        @Override
        public long getItemId(int position)
        {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view;
            if (convertView == null)
            {
                view = getLayoutInflater().inflate(R.layout.item_image, null);
            }else
            {
                view = convertView;
            }

            ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
            Glide.with(MainActivity.this).load(imgList.get(position).getImgPath()).into(imageView);
            return view;
        }
    }


}
