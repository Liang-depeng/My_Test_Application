package com.bh.ldp.lib_base.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;

/**
 * created by Da Peng at 2019/8/7
 */
public class ViewUtils {

    /**
     * view -> bitmap 方法 1
     * <p>
     * 这个方法适用于view 已经显示在界面上了，可以获得view 的宽高实际大小，进而通过DrawingCache 保存为bitmap
     *
     * @param view view
     * @return bitmap
     */
    public static Bitmap createBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /**
     * view -> bitmap 方法 2
     * <p>
     * 如果要截取的view 没有在屏幕上显示完全的，例如要截取的是超过一屏的 scrollview ，通过上面这个方法是获取不到bitmap的，
     * 需要使用下面方法，传的view 是scrollview 的子view（LinearLayout）等， 当然完全显示的view（第一种情况的view）也可以使用这个方法截取。
     *
     * @param v view
     * @return bitmap
     */
    public static Bitmap createBitmap2(View v) {
        Bitmap bmp = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        c.drawColor(Color.WHITE);
        v.draw(c);
        return bmp;
    }

    /**
     * view -> bitmap 方法 3
     * <p>
     * 还有一种 是view完全没有显示在界面上，通过inflate 转化的view，这时候
     * 通过 DrawingCache 是获取不到bitmap 的，也拿不到view 的宽高，以上两
     * 种方法都是不可行的。第三种方法通过measure、layout 去获得view 的实际尺寸
     * <p>
     * View view = LayoutInflater.from(this).inflate(R.layout.view_inflate, null, false);
     * //这里传值屏幕宽高，得到的视图即全屏大小
     * createBitmap3(view, getScreenWidth(), getScreenHeight())
     *
     * @param v      view
     * @param width  screenWidth
     * @param height screenHeight
     * @return bitmap
     */
    public static Bitmap createBitmap3(View v, int width, int height) {
        v.layout(0, 0, width, height);
        //测量使得view指定大小
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        v.measure(measuredWidth, measuredHeight);

        //调用layout方法布局后，可以得到view的尺寸大小
        Bitmap bmp = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        c.drawColor(Color.WHITE);
        v.draw(c);
        return bmp;
    }

    /**
     * 保存图片
     *
     * @param bitmap bitmap
     */
    public static void saveBitmap(Bitmap bitmap) {
        FileOutputStream fos;
        try {
            File root = Environment.getExternalStorageDirectory();
            File file = new File(root, System.currentTimeMillis() + "test.png");
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ScrollView（Listview）嵌套ListView只显示一条数据的解决方法
     * @param listView listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }


}

//    /**
//     * 通过view生成bitmap
//     *
//     * @param v
//     * @return
//     */
//    public Bitmap createViewBitmap(View v) {
//        v.setDrawingCacheEnabled(true);
//        v.buildDrawingCache();  //启用DrawingCache并创建位图
//        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
//                Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        v.draw(canvas);
//        return bitmap;
//    }
