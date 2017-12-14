package hai.com.myapp.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/6/8.
 */

public class ImageUtil {

    public static Bitmap load(String pathName) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;

        //暴力获取
        Bitmap bitmap;
        int i = 1;
        while (true) {
            try {
                options.inSampleSize = i;
                bitmap = BitmapFactory.decodeFile(pathName, options);
                break;
            } catch (Throwable e) {
                i *= 2;
            }
        }

        return bitmap;
    }

    public static Bitmap load(String pathName, AppCompatActivity compatActivity) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        //只读图片的信息，不实质解码图片
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);

        Point point = new Point();
        compatActivity.getWindowManager().getDefaultDisplay().getSize(point);

        System.out.println("width: " + point.x + "\theight: " + point.y);

        if (options.outWidth > point.x || options.outHeight > point.y) {
            int widthIndex = Math.round((float) options.outWidth / (float) point.x);
            int heightIndex = Math.round((float) options.outHeight / (float) point.y);
            //设置缩放比例
            options.inSampleSize = Math.max(widthIndex, heightIndex);
        }

        //记得计算完inSampleSize后设置为false，否则一直都不会解码图片
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(pathName, options);
    }

    public static Bitmap copy(String pathName, AppCompatActivity compatActivity) {
        Bitmap bitmap = BitmapFactory.decodeResource(compatActivity.getResources(), android.R.mipmap.sym_def_app_icon);

        Bitmap copybm = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        //准备画布
        Canvas canvas = new Canvas(copybm);
        Matrix matrix = new Matrix();
        Paint paint = new Paint();
        //画图
        canvas.drawBitmap(bitmap, matrix, paint);

        return copybm;
    }
}
