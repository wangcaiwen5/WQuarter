package com.example.baseutils.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.baseutils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wangcaiwen
 * Time:2017/11/18.
 * Description:
 */

public class XViewPager extends FrameLayout implements ViewPager.OnPageChangeListener {

    private Context context;
    private List<String> mUrls;
    private List<ImageView> mViewPagers;
    private List<ImageView> mDotImages;
    private int size;
    private int margin;
    private int count;
    private LinearLayout ll;
    private ViewPager viewPager;
    private int currentItem;
    Handler handler = new Handler();


    public XViewPager(@NonNull Context context) {
        super(context);
        init(context, null);
    }


    public XViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public XViewPager(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 初始化数据
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        this.context = context;

        //图片地址集合
        mUrls = new ArrayList<>();
        //图片集合
        mViewPagers = new ArrayList<>();
        //圆点集合
        mDotImages = new ArrayList<>();

        //拿到自定义的属性数组
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Banner);

        //得到数组里的自定义size
        size = typedArray.getDimensionPixelSize(R.styleable.Banner_size, 10);
        margin = typedArray.getDimensionPixelSize(R.styleable.Banner_size, 10);

        typedArray.recycle();

        View view = LayoutInflater.from(context).inflate(R.layout.banner_layout, this, true);

        viewPager = view.findViewById(R.id.vp_img);

        ll = view.findViewById(R.id.LL);

        viewPager.addOnPageChangeListener(this);

    }

    /**
     * 绘制自定义view的所有元素
     */
    public void display() {
        //绘制viewpager
        drawViewPager();

    }


    /**
     * 传入url
     */
    public void loadData(List<String> mUrls) {
        this.mUrls = mUrls;
        this.count = mUrls.size();

    }


    //绘制ViewPager
    private void drawViewPager() {
        for (int i = 0; i < count; i++) {

            ImageView iv = new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mViewPagers.add(iv);

        }
        if (mViewPagers != null) {
            viewPager.setAdapter(new MyViewPager());

        }

    }

    /**
     * 动画监听
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class MyViewPager extends PagerAdapter {

        @Override
        public int getCount() {
            return mViewPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            ImageView imageView = mViewPagers.get(position);
            container.addView(imageView);

           return imageView;
        }

    }
}
