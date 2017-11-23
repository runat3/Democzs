package com.test.imageselector;

/**
 * Created by Administrator on 2017/11/22.
 */

public class FolderBean
{
    private String dir;//当前文件夹的路径
    private String firstImgPath;//当前文件夹的第一张图片的路径
    private String name;//文件夹的名称
    private int count;//文件夹内图片的数量

    public String getDir()
    {
        return dir;
    }

    public void setDir(String dir)
    {
        this.dir = dir;
        int lastIndexOf = this.dir.lastIndexOf("/");
        this.name= this.dir.substring(lastIndexOf);
    }

    public String getFirstImgPath()
    {
        return firstImgPath;
    }

    public void setFirstImgPath(String firstImgPath)
    {
        this.firstImgPath = firstImgPath;
    }

    public String getName()
    {
        return name;
    }

//    public void setName(String name)
//    {
//        this.name = name;
//    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
}
