package com.cj.toastlibrary;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 自定义Toast，可设置背景，设置图片
 */
public class CustomToast {

    private static CustomToast mToast;//土司对象

    private WindowManager.LayoutParams params;//窗口管理器的布局参数
    private static WindowManager windowManager;

    private static LinearLayout linearLayout;
    private static View preView;

    private static TextView textView;

    private static Context mContext;

    private boolean imageFlag = false;


    //图片位置
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int RIGHT = 2;
    public static final int BUTTOM = 3;

    private Handler mHandler = new Handler(Looper.getMainLooper());


    private Drawable drawable;
    private int duration = -1;
    private GradientDrawable gd;
    private float defalutSize;

    private static boolean isMainThread = true;


    private CustomToast(Context context, String msg) {
        initView(context, msg);
        initParams();
    }

    /**
     * @param context 上下文
     * @param msg     toast显示文本
     * @return
     */
    public static CustomToast makeToast(Context context, final String msg) {
        mContext = context;
        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        if (!(context instanceof Application)) {
            throw new IllegalAccessError("Please use ApllicationContext");
        }

        isMainThread = Looper.getMainLooper() == Looper.myLooper();

        if (mToast == null) {
            synchronized (CustomToast.class) {
                if (mToast == null) {
                    mToast = new CustomToast(context, msg);
                }
            }
        }
        preView = linearLayout;//处理单例bug


        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(msg);
            }
        });


        return mToast;
    }

    private void modifiUI(Runnable runnable){
        textView.post(runnable);
    }

    /**
     * 初始化Toast布局
     *
     * @param context
     */
    private void initView(Context context, final String msg) {

        linearLayout = new LinearLayout(context);
        textView = new TextView(context);

        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        float textSize = textView.getTextSize();
        defalutSize = textSize / (mContext.getResources().getDisplayMetrics().density);


        textView.setText(msg);

        textView.setGravity(Gravity.CENTER);
        //创建drawable
        gd = new GradientDrawable();

        setDefaultConfigure();
        linearLayout.addView(textView, layoutParams);

    }


    /**
     * 默认配置
     */
    private void setDefaultConfigure() {
        modifiUI(new Runnable() {
            @Override
            public void run() {
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(defalutSize);
                textView.setCompoundDrawables(null, null, null, null);
            }
        });
        int defaultPadding  = DpUtil.px2dip(mContext,8);

        setPadding(defaultPadding, defaultPadding, defaultPadding, defaultPadding);
        setBackgroundColor(Color.parseColor("#88000000"));
        setBackgroundRadius(DpUtil.px2dip(mContext,20));
    }

    /**
     * 设置图片,如果不调整图片大小，该方法最好在设置字体大小后进行设置
     *
     * @param resId 图片资源id
     * @return
     */
    public CustomToast setTextImage(int resId) {
        drawable = mContext.getResources().getDrawable(resId);
        modifiUI(new Runnable() {
            @Override
            public void run() {
                int boundsSize = DpUtil.px2dip(mContext,(int) textView.getTextSize());
                drawable.setBounds(0, 0, boundsSize, boundsSize);
                textView.setCompoundDrawables(drawable, null, null, null);
            }
        });

        return mToast;
    }

    /**
     * 设置图片的大小
     *
     * @param width
     * @param high
     * @return
     */
    public CustomToast setTextImageSize(final int width,final int high) {
        if (drawable == null) {
            throw new IllegalAccessError("Please use the method of setTextImage first");
        }

        modifiUI(new Runnable() {
            @Override
            public void run() {
                drawable.setBounds(0, 0, DpUtil.px2dip(mContext,width), DpUtil.px2dip(mContext,high));
            }
        });
        imageFlag = true;
        return mToast;
    }

    /**
     * 设置文字和图片之间的距离
     * @param pad
     * @return
     */
    public CustomToast setImagePadding(final int pad) {
        modifiUI(new Runnable() {
            @Override
            public void run() {
                textView.setCompoundDrawablePadding(DpUtil.px2dip(mContext,pad));
            }
        });

        return mToast;
    }

    /**
     * 如果要设置图片的大小，先设置图片的大小再调用该方法
     * @param imageLocation 图片位置
     * @return
     */
    public CustomToast setTextImageLocation(final int imageLocation) {

        if (!imageFlag && drawable.getIntrinsicWidth() != textView.getTextSize()
                && drawable.getIntrinsicWidth() != textView.getTextSize()) {
            throw new IllegalAccessError("Please use the method of setTextImageSize first");
        }

        imageFlag = false;
        modifiUI(new Runnable() {
            @Override
            public void run() {
                if (imageLocation == LEFT) {
                    textView.setCompoundDrawables(drawable, null, null, null);
                } else if (imageLocation == TOP) {
                    textView.setCompoundDrawables(null, drawable, null, null);
                } else if (imageLocation == RIGHT) {
                    textView.setCompoundDrawables(null, null, drawable, null);
                } else if (imageLocation == BUTTOM) {
                    textView.setCompoundDrawables(null, null, null, drawable);
                }
            }
        });

        return mToast;
    }

    /**
     * 设置Toast显示时长
     * @param duration
     * @return
     */
    public CustomToast setDuration(int duration) {
        if (duration == Toast.LENGTH_SHORT) {
            this.duration = 1000;
        } else if (duration == Toast.LENGTH_LONG) {
            this.duration = 5000;
        } else {
            this.duration = duration;
        }
        return mToast;
    }

    /**
     * 显示Toast
     */
    public void showMToast() {
        if (isMainThread){
            show();
        }else{
            Looper.prepare();
            if (show()) return;
            Looper.loop();
        }
    }

    private boolean show() {
        if (linearLayout.getParent() != null && preView == linearLayout) return true;

        windowManager.addView(linearLayout, params);

        fixDuration();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (linearLayout != null) {
                    if (linearLayout.getParent() != null) {
                        windowManager.removeView(linearLayout);
                    }
                }
            }
        }, duration);
        return false;
    }


    /**
     * 修正显示时间
     */
    private void fixDuration() {
        if (duration == -1) {
            CharSequence text = textView.getText();
            if (!TextUtils.isEmpty(text)) {
                int length = text.toString().length();
                if (length < 15) {
                    duration = 1000;
                } else {
                    duration = 5000;
                }
            } else {
                duration = 1000;
            }
        }
    }

    /**
     * 清除之前设置
     *
     * @return
     */
    public CustomToast clearSetting() {
        //说明当前有Toast正在显示，先将它移除，在清空做操作
        if (linearLayout.getParent() != null) windowManager.removeView(linearLayout);

        duration = -1;
        drawable = null;
        modifiUI(new Runnable() {
            @Override
            public void run() {
                setDefaultConfigure();
                setGravity(Gravity.CENTER, 0, 0);
            }
        });

        return mToast;
    }


    /**
     * 设置Toast显示位置
     *
     * @param gravity
     * @param xOffset
     * @param yOffset
     * @return
     */
    public CustomToast setGravity(int gravity, int xOffset, int yOffset) {
        params.gravity = gravity;
        params.x = xOffset;
        params.y = yOffset;
        return mToast;
    }


    /**
     * 设置背景颜色
     *
     * @param color
     * @return
     */
    public CustomToast setBackgroundColor(final int color) {
        modifiUI(new Runnable() {
            @Override
            public void run() {
                gd.setColor(color);
                linearLayout.setBackgroundDrawable(gd);
            }
        });

        return mToast;
    }

    /**
     * 设置背景圆角
     *
     * @param roundRadius
     * @return
     */
    public CustomToast setBackgroundRadius(final int roundRadius) {
        modifiUI(new Runnable() {
            @Override
            public void run() {
                gd.setCornerRadius(roundRadius);
                linearLayout.setBackgroundDrawable(gd);
            }
        });

        return mToast;
    }

    /**
     * 设置背景的透明度
     * @param alpha
     * @return
     */
    public CustomToast setBackgroundAlpha(final int alpha){
        if (alpha >100 || alpha < 0) return mToast;
        modifiUI(new Runnable() {
            @Override
            public void run() {
                gd.setAlpha(alpha);
                linearLayout.setBackgroundDrawable(gd);
            }
        });
        return mToast;
    }

    /**
     * 设置字体颜色
     *
     * @param color
     * @return
     */
    public CustomToast setTextColor(final int color){
        modifiUI(new Runnable() {
            @Override
            public void run() {
                textView.setTextColor(color);
            }
        });
        return mToast;
    }

    /**
     * 设置字体大小
     *
     * @param size
     * @return
     */
    public CustomToast setTextSize(final int size) {
        modifiUI(new Runnable() {
            @Override
            public void run() {
                textView.setTextSize(DpUtil.px2dip(mContext,size));
            }
        });

        return mToast;
    }

    /**
     * 设置Toast的内边距
     *
     * @param left   左边距
     * @param top    上边距
     * @param right  右边距
     * @param bottom 下边距
     * @return
     */
    public CustomToast setPadding(final int left, final int top, final int right,final int bottom) {
        modifiUI(new Runnable() {
            @Override
            public void run() {
                linearLayout.setPadding(DpUtil.px2dip(mContext,left), DpUtil.px2dip(mContext,top),
                        DpUtil.px2dip(mContext,right), DpUtil.px2dip(mContext,bottom));
            }
        });
        return mToast;
    }

    /**
     * 设置窗口管理器参数
     */
    private void initParams() {
        params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;
        params.gravity = Gravity.CENTER;//设置默认坐标原点为左上角

        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
    }
}
