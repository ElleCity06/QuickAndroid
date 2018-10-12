package com.ellecity06.quickjar.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.ellecity06.quickjar.QuickAndroid;
import com.ellecity06.quickjar.image.support.CircleBorderTransformation;
import com.ellecity06.quickjar.image.support.IImageManager;
import com.ellecity06.quickjar.image.support.ImageConfig;
import com.ellecity06.quickjar.image.support.ImageListener;
import com.ellecity06.quickjar.image.support.LoadOption;
import com.ellecity06.quickjar.other.ElleCityLog;
import com.ellecity06.quickjar.util.FileUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Glide图片加载管理者,3.x和4.x有一些区别，我是用的是4.x的版本
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/26 11:38
 */

public class GlideManager implements IImageManager {

    private Context mContext;
    private ImageConfig mImageConfig;
    private ExecutorService cacheThreadPool;

    @Override
    public void init(Context context, ImageConfig imageConfig) {
        mContext = context;
        mImageConfig = imageConfig;
    }

    @Override
    public void loadNet(String url, ImageView imageView) {
        load(Glide.with(imageView.getContext()).load(url), null).into(imageView);
    }

    /**
     * 如果设置了isgif之后会判断这个图片是不是gif不是的话会显示设置了的错误图片，没设置就显示空白,<br>
     * 在不设置isGif值的时候也可以加载gif，只是不会去对这个图片进行判断而已，什么图片都会加载
     *
     * @param url
     * @param imageView
     * @param loadOption
     */
    @Override
    public void loadNet(String url, ImageView imageView, LoadOption loadOption) {
        if (loadOption.isGif()) {
            load(Glide.with(imageView.getContext()).asGif().load(url), loadOption).into(imageView);
            return;
        }
        load(Glide.with(imageView.getContext()).load(url), loadOption).into(imageView);
    }

    @Override
    public void loadRes(int resId, ImageView imageView) {
        load(Glide.with(imageView.getContext()).load(resId), null).into(imageView);
    }

    /**
     * 如果设置了isgif之后会判断这个图片是不是gif不是的话会显示设置了的错误图片，没设置就显示空白<br>
     * 在不设置isGif值的时候也可以加载gif，只是不会去对这个图片进行判断而已，什么图片都会加载
     *
     * @param resId
     * @param imageView
     * @param loadOption
     */
    @Override
    public void loadRes(int resId, final ImageView imageView, LoadOption loadOption) {
        if (loadOption.isGif()) {
            load(Glide.with(imageView.getContext()).asGif().load(resId), loadOption).into(imageView);

        }
        load(Glide.with(imageView.getContext()).load(resId), loadOption).into(imageView);
    }

    @Override
    public void loadAsset(String assetName, ImageView imageView) {
        load(Glide.with(imageView.getContext()).load("file:///android_asset/" + assetName), null).into(imageView);
    }

    @Override
    public void loadAsset(String assetName, ImageView imageView, LoadOption loadOption) {
        load(Glide.with(imageView.getContext()).load("file:///android_asset/" + assetName), loadOption).into(imageView);
    }

    @Override
    public void loadFile(File file, ImageView imageView) {
        load(Glide.with(imageView.getContext()).load(file), null).into(imageView);
    }

    @Override
    public void loadFile(File file, ImageView imageView, LoadOption loadOption) {
        load(Glide.with(imageView.getContext()).load(file), loadOption).into(imageView);
    }


    @Override
    public void preLoad(String url) {
        Glide.with(mContext).load(url).preload();
    }

    @Override
    public void getBitmap(Context context, String url, final ImageListener<Bitmap> imageListener) {
        Glide.with(context).asBitmap().load(url).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                if (imageListener != null) {
                    imageListener.onFail(e);
                }
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                if (imageListener != null) {
                    imageListener.onSuccess(resource);
                }
                return false;
            }
        }).into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }


    /**
     * 播放gif图片，通过loadOption设置播放次数，同时监听播放完成播放次数默认为1
     *
     * @param url
     * @param imageView
     * @param loadOption
     */
    @Override
    public void loadGifListener(String url, final ImageView imageView, final LoadOption loadOption, final GifListener gifListener) {
        Glide.with(imageView.getContext()).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                try {

                    // TODO 记得使用混淆 #Glide
                    //-keep class com.bumptech.glide.** {*;}
                    GifDrawable gifDrawable = (GifDrawable) resource;
                    Field gifStateField = GifDrawable.class.getDeclaredField("state");
                    gifStateField.setAccessible(true);
                    Class gifStateClass = Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable$GifState");
                    Field gifFrameLoaderField = gifStateClass.getDeclaredField("frameLoader");
                    gifFrameLoaderField.setAccessible(true);
                    Class gifFrameLoaderClass = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader");
                    Field gifDecoderField = gifFrameLoaderClass.getDeclaredField("gifDecoder");
                    gifDecoderField.setAccessible(true);
                    Class gifDecoderClass = Class.forName("com.bumptech.glide.gifdecoder.GifDecoder");
                    Object gifDecoder = gifDecoderField.get(gifFrameLoaderField.get(gifStateField.get(resource)));
                    Method getDelayMethod = gifDecoderClass.getDeclaredMethod("getDelay", int.class);
                    getDelayMethod.setAccessible(true);
                    //设置只播放一次
                    gifDrawable.setLoopCount(loadOption.getGifPlayCount());
                    //获得总帧数
                    final int count = gifDrawable.getFrameCount() * loadOption.getGifPlayCount();

                    int delay = 0;


                    for (int i = 0; i < count; i++) {
                        //计算每一帧所需要的时间进行累加
                        delay += (int) getDelayMethod.invoke(gifDecoder, i);
                        //                        delay+= getDelayMethod.getDelay(i);
                    }
                    ElleCityLog.d("一帧播放的时间==" + delay);
                    ElleCityLog.d("一帧播放的次数==" + count);
                    imageView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (gifListener != null) {
                                gifListener.gifPlayComplete();
                            }
                        }
                    }, delay * loadOption.getGifPlayCount());
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }


                return false;
            }
        }).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                if (drawable instanceof GifDrawable) {
                    GifDrawable gifDrawable = (GifDrawable) drawable;
                    gifDrawable.setLoopCount(loadOption.getGifPlayCount());
                    imageView.setImageDrawable(drawable);
                    gifDrawable.start();
                }
            }
        });

    }

    /**
     * Gif播放完毕回调
     */
    public interface GifListener {
        void gifPlayComplete();
    }

    @Override
    public void downLoadImage(final Context context, final String url, final File targetFile, final ImageListener<File> imageListener) {
        if (cacheThreadPool == null) {
            cacheThreadPool = Executors.newCachedThreadPool();
        }

        cacheThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    File sourceFile = Glide.with(context).asFile().load(url).submit().get();
                    if (FileUtil.copyFile(sourceFile, targetFile) && imageListener != null) {
                        imageListener.onSuccess(targetFile);//回调在后台线程
                    }
                } catch (Exception exception) {
                    if (imageListener != null) {
                        imageListener.onFail(exception);//回调在后台线程
                    }
                }
            }
        });
    }

    @Override
    public void clearMemoryCache() {
        //Glide要求清除内存缓存需在主线程执行
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Glide.get(mContext).clearMemory();
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Glide.get(mContext).clearMemory();
                }
            });
        }
    }

    @Override
    public void clearDiskCache() {
        //Glide要求清除内存缓存需在后台程执行
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Glide.get(mContext).clearDiskCache();
                }
            }).start();
        } else {
            Glide.get(mContext).clearDiskCache();
        }
    }


    private RequestBuilder load(RequestBuilder requestBuilder, LoadOption loadOption) {

        RequestOptions requestOptions = new RequestOptions();

        mImageConfig = QuickAndroid.elleCityComponent().imageConfig();
        //使用全局的配置进行设置
        if (loadOption == null) {
            if (mImageConfig.isShowTransition()) {
                requestBuilder.transition(DrawableTransitionOptions.withCrossFade(600));
            }

            if (mImageConfig.getLoadingResId() > 0) {
                requestOptions.placeholder(mImageConfig.getLoadingResId());
            }

            if (mImageConfig.getErrorResId() > 0) {
                requestOptions.error(mImageConfig.getErrorResId());
            }
        }
        //使用临时的配置进行设置
        else {
            if (loadOption.isShowTransition()) {
                requestBuilder.transition(DrawableTransitionOptions.withCrossFade(600));
            }

            if (loadOption.getLoadingResId() > 0) {
                requestOptions.placeholder(loadOption.getLoadingResId());
            }

            if (loadOption.getErrorResId() > 0) {
                requestOptions.error(loadOption.getErrorResId());
            }

            CircleBorderTransformation circleTransformation = null;
            //            CropCircleTransformation circleTransformation = null;
            RoundedCornersTransformation roundedCornersTransformation = null;
            BlurTransformation blurTransformation = null;
            GrayscaleTransformation grayscaleTransformation = null;

            if (loadOption.isCircle()) {
                //                circleTransformation = new CropCircleTransformation();
                int borderWidth = loadOption.getBorderWidth();
                int borderColor = loadOption.getBorderColor();
                if (borderWidth > 0 && borderColor != 0) {
                    circleTransformation = new CircleBorderTransformation(borderWidth, borderColor);
                } else {
                    circleTransformation = new CircleBorderTransformation();
                }
            } else if (loadOption.getRoundRadius() > 0) {
                roundedCornersTransformation = new RoundedCornersTransformation(loadOption.getRoundRadius(), 0);
            }

            if (loadOption.getBlurRadius() > 0) {
                blurTransformation = new BlurTransformation(loadOption.getBlurRadius());
            }

            if (loadOption.isGray()) {
                grayscaleTransformation = new GrayscaleTransformation();
            }

            MultiTransformation multiTransformation = getMultiTransformation(circleTransformation, roundedCornersTransformation, blurTransformation, grayscaleTransformation);
            if (multiTransformation != null)
                requestOptions.transform(multiTransformation);
        }
        return requestBuilder.apply(requestOptions);
    }

    private MultiTransformation getMultiTransformation(Transformation... transformations) {
        List<Transformation> list = new ArrayList<>();

        for (int i = 0; i < transformations.length; i++) {
            if (transformations[i] != null)
                list.add(transformations[i]);
        }

        if (list.isEmpty()) {
            return null;
        } else {
            return new MultiTransformation(list);
        }
    }


}
