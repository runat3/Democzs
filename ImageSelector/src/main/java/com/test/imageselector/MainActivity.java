package com.test.imageselector;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends AppCompatActivity
{


    private GridView gv_image;
    private List<String> mImages;
    private ImageAdapter mImgAdapter;
    private RelativeLayout rl_imgDirSelect;
    private TextView tv_imgDir;
    private TextView tv_imgCount;

    private File mCurrentDir;//当前目录
    private int mMaxCount;//当前目录下的图片数量
    private List<FolderBean> mFolderBeans = new ArrayList<FolderBean>();


    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if (msg.what == 0x110)
            {
                data2View();//绑定数据到视图
            }
            super.handleMessage(msg);
        }
    };

    private void data2View()
    {
        if (mCurrentDir == null)
        {
            Toast.makeText(this, "未扫描到任何图片", Toast.LENGTH_SHORT).show();
            return;
        }
        mImages = Arrays.asList(mCurrentDir.list(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                if (name.endsWith(".jpg") || name.endsWith(".jpeg")
                        || name.endsWith(".png")||name.endsWith(".JPG") || name.endsWith(".JPEG")
                        || name.endsWith(".PNG"))
                {
                    return true;
                }
                return false;
            }
        }));
        Collections.reverse(mImages); // 倒序排列
        mImgAdapter = new ImageAdapter(this, mImages, mCurrentDir.getAbsolutePath());
        gv_image.setAdapter(mImgAdapter);
        tv_imgDir.setText(mCurrentDir.getName());
        tv_imgCount.setText(mMaxCount + "");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView()
    {
        gv_image = (GridView) findViewById(R.id.gv_image);
        rl_imgDirSelect = (RelativeLayout) findViewById(R.id.rl_imgDirSelect);
        tv_imgDir = (TextView) findViewById(R.id.tv_imgDir);
        tv_imgCount = (TextView) findViewById(R.id.tv_imgCount);
        final View image_dir_popup = getLayoutInflater().inflate(R.layout.image_dir_popup, null);
        ListView lv_imgDir = (ListView) image_dir_popup.findViewById(R.id.lv_imgDir);
        lv_imgDir.setAdapter(new ImgDirAdapter(mFolderBeans));
        //获得手机屏幕的宽高
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);

        //实例化一个popupwindow
        final PopupWindow imageDirPopupWindow = new PopupWindow(image_dir_popup, ViewGroup.LayoutParams.MATCH_PARENT,
                (int)(displayMetrics.heightPixels*0.6), true);
        imageDirPopupWindow.setOutsideTouchable(true);
        imageDirPopupWindow.setFocusable(true);
        imageDirPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        imageDirPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
        {
            @Override
            public void onDismiss()
            {
                backgroundAlpha(1f);
            }
        });
        rl_imgDirSelect.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                imageDirPopupWindow.showAtLocation(rl_imgDirSelect, Gravity.TOP, 0, 0);
                imageDirPopupWindow.showAsDropDown(rl_imgDirSelect);
                backgroundAlpha(0.3f);
            }
        });
        lv_imgDir.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                mCurrentDir = new File(mFolderBeans.get(position).getDir());
                mImages = Arrays.asList(mCurrentDir.list(new FilenameFilter()
                {
                    @Override
                    public boolean accept(File dir, String name)
                    {
                        if (name.endsWith(".jpg") || name.endsWith(".jpeg")
                                || name.endsWith(".png") || name.endsWith(".JPG") || name.endsWith(".JPEG")
                                || name.endsWith(".PNG"))
                        {
                            return true;
                        }
                        return false;
                    }
                }));
                Collections.reverse(mImages); // 倒序排列
                mImgAdapter = new ImageAdapter(MainActivity.this, mImages, mCurrentDir.getAbsolutePath());
                gv_image.setAdapter(mImgAdapter);
                tv_imgDir.setText(mCurrentDir.getName());
                tv_imgCount.setText(mImages.size() + "");
                imageDirPopupWindow.dismiss();
                //// TODO: 2017/11/23 所有图片-》单独一个适配器
            }
        });
    }

    //窗口背景透明度
    private void backgroundAlpha(float f)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = f; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    private void initData()
    {
        //判断外部存储卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            Toast.makeText(MainActivity.this, "当前存储卡不可用！", Toast.LENGTH_SHORT).show();
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
                        MediaStore.Images.Media.MIME_TYPE + " = ? or " + MediaStore.Images.Media.MIME_TYPE + " = ? or " + MediaStore.Images.Media.MIME_TYPE + " = ? ",
                        new String[]{"image/png", "image/jpg", "image/jpeg"}, MediaStore.Images.Media.DATE_MODIFIED);

                //存储已扫描的父文件路径
                Set<String> mDirPaths = new HashSet<String>();

                while (cursor.moveToNext())
                {
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null)
                    {
                        continue;
                    }
                    String dirPath = parentFile.getAbsolutePath();
                    FolderBean folderBean = null;
                    if (mDirPaths.contains(dirPath))
                    {
                        continue;
                    } else
                    {
                        mDirPaths.add(dirPath);
                        folderBean = new FolderBean();
                        folderBean.setDir(dirPath);
                        folderBean.setFirstImgPath(path);
                    }
                    if (parentFile.list() == null)
                    {
                        continue;
                    }
                    int picSize = parentFile.list(new FilenameFilter()
                    {
                        @Override
                        public boolean accept(File dir, String name)
                        {
                            if (name.endsWith(".jpg") || name.endsWith(".jpeg")
                                    || name.endsWith(".png")||name.endsWith(".JPG") || name.endsWith(".JPEG")
                                    || name.endsWith(".PNG"))
                            {
                                return true;
                            }
                            return false;
                        }
                    }).length;
                    folderBean.setCount(picSize);
                    mFolderBeans.add(folderBean);

                    if (picSize > mMaxCount)
                    {
                        mMaxCount = picSize;
                        mCurrentDir = parentFile;
                    }
                }
                cursor.close();
                mHandler.sendEmptyMessage(0x110);
                super.run();
            }
        }.start();
    }

    private class ImageAdapter extends BaseAdapter
    {

        private String mDirPath;
        private List<String> mImgPaths;
        private LayoutInflater mInflater;

        public ImageAdapter(Context context, List<String> mDatas, String dirPath)
        {
            this.mDirPath = dirPath;
            this.mImgPaths = mDatas;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount()
        {
            return mImgPaths.size();
        }

        @Override
        public Object getItem(int position)
        {
            return mImgPaths.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view;
            if (convertView == null)
            {
                view = mInflater.inflate(R.layout.item_image, parent, false);
            } else
            {
                view = convertView;
            }
            ImageView iv_image = (ImageView) view.findViewById(R.id.iv_image);
            Glide.with(MainActivity.this).load(mDirPath + "/" + mImgPaths.get(position)).into(iv_image);
            return view;
        }
    }


    private class ImgDirAdapter extends BaseAdapter
    {
        private List<FolderBean> folderBeanList;

        public ImgDirAdapter(List<FolderBean> folderBeanList)
        {
            this.folderBeanList = folderBeanList;
        }

        @Override
        public int getCount()
        {
            return folderBeanList.size();
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
                view = getLayoutInflater().inflate(R.layout.item_image_dir_popup, parent, false);
            } else
            {
                view = convertView;
            }
            ImageView iv_imgDir = (ImageView) view.findViewById(R.id.iv_imgDir);
            TextView tv_dirName = (TextView) view.findViewById(R.id.tv_dirName);
            TextView tv_dirCount = (TextView) view.findViewById(R.id.tv_dirCount);
            FolderBean folderBean = folderBeanList.get(position);
            Glide.with(MainActivity.this).load(folderBean.getFirstImgPath()).into(iv_imgDir);
            tv_dirName.setText(folderBean.getName());
            tv_dirCount.setText(folderBean.getCount() + "");
            return view;
        }
    }
}
