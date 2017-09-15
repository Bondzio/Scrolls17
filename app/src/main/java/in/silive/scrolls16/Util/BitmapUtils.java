package in.silive.scrolls16.Util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;




public class BitmapUtils {
    private final Context context;

    public BitmapUtils(Context context) {
        this.context=context;
        
    }

    public  Bitmap getBitmapFromAssets(String filepath) {
        AssetManager assetManager = context.getAssets();
        InputStream istr = null;
        Bitmap bitmap = null;

        try {
            istr = assetManager.open(filepath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (istr != null) {
                try {
                    istr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    public  Bitmap[] getBitmapsFromSprite(Bitmap bitmap, int NB_FRAMES, int COUNT_X, int COUNT_Y, int FRAME_H, int FRAME_W) {
        int currentFrame = 0;
        Bitmap[] bitmaps = new Bitmap[NB_FRAMES];
        int k=0;
        for (int i = 0; i < COUNT_Y; i++) {
            for (int j = 0; j < COUNT_X; j++) {
                bitmaps[currentFrame] = Bitmap.createBitmap(bitmap, FRAME_W
                        * j, FRAME_H *i, FRAME_W, FRAME_H);


                if (++currentFrame >= NB_FRAMES) {
                    break;
                }
            }
        }
        return bitmaps;
    }
}
