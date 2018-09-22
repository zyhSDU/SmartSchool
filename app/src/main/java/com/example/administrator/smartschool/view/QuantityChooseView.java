package com.example.administrator.smartschool.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.smartschool.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明：数值选择view，可以选择数量，并且在回调里处理具体逻辑
 * 功能简介：可以在xml里或者代码中设置左右view的图片、默认库存大小、当前数值、文本大小、文本颜色以及背景边框
 * 使用注意：
 * 1）需要在attr里面写入下面代码
 * <declare-styleable name="QuantityChooseView">
 * <attr name="maxQuantity" format="integer"/>
 * <attr name="quantity" format="integer"/>
 * <attr name="rightViewEnableSrc" format="integer"/>
 * <attr name="rightViewDisableSrc" format="integer"/>
 * <attr name="leftViewEnableSrc" format="integer"/>
 * <attr name="leftViewDisableSrc" format="integer"/>
 * <attr name="quantityTextSize" format="integer"/>
 * <attr name="quantityTextColor" format="color"/>
 * </declare-styleable>
 * 2）默认左右图片ic_launcher
 * 3）本view也需要下面strings
 * <string name="msg_null_number">Please input number!</string>
 * <string name="msg_error_onlyOneAbove">Can only enter one above!</string>
 * <string name="dialog_title_quantity">Input Quantity</string>
 * <string name="cancel">Cancel</string>
 * <string name="confirm">Confirm</string>
 * 4) dialog需要引用MaterialDialog
 * <p/>
 * by tc 2015/01/08
 * version 1.0
 */
public class QuantityChooseView extends LinearLayout {
    public static final String TAG = QuantityChooseView.class.getSimpleName();
    /**
     * 监听数量变化
     */
    private OnQuantityChangeListener mOnQuantityChangeListener;
    /**
     * 默认库存大小
     */
    private static final int DEFAULT_QUANTITY = 99999;
    /**
     * 最大数量
     */
    private int mMaxQuantity = DEFAULT_QUANTITY;
    /**
     * 数量
     */
    private int mQuantity = 1;

    /**
     * 右侧按钮图片
     */
    private ImageView mIvRight;
    /**
     * 右侧按钮图片数据源--可操作
     */
    private int mIvRightEnableSrc = -1;
    /**
     * 右侧按钮图片数据源--不可操作
     */
    private int mIvRightDisableSrc = -1;
    /**
     * 左侧按钮图片
     */
    private ImageView mIvLeft;
    /**
     * 左侧按钮图片数据源--可操作
     */
    private int mIvLeftEnableSrc = -1;
    /**
     * 左侧按钮图片数据源--不可操作
     */
    private int mIvLeftDisableSrc = -1;

    /**
     * 数量文本
     */
    private TextView mTvQuantity;
    /**
     * 数量文本的颜色
     */
    private int mQuantityTextColor = -1;
    /**
     * 选中数量的字体大小
     */
    private float mQuantityTextSize = 0;
    /**
     * 数字选择弹框
     */
    private MaterialDialog mMaterialDialog;
    /**
     * 上一次选择的数值
     */
    private int mLastSelectQuantity;

    public QuantityChooseView(Context context) {
        super(context);
        init(context, null);
    }

    public QuantityChooseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);


    }

    public QuantityChooseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public QuantityChooseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.removeAllViews();
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.QuantityChooseView);
            mMaxQuantity = a.getInt(R.styleable.QuantityChooseView_maxQuantity, DEFAULT_QUANTITY);
            mQuantity = a.getInt(R.styleable.QuantityChooseView_quantity, 1);
            mIvRightEnableSrc = a.getResourceId(R.styleable.QuantityChooseView_rightViewEnableSrc, -1);
            mIvRightDisableSrc = a.getResourceId(R.styleable.QuantityChooseView_rightViewDisableSrc, -1);
            mIvLeftEnableSrc = a.getResourceId(R.styleable.QuantityChooseView_leftViewEnableSrc, -1);
            mIvLeftDisableSrc = a.getResourceId(R.styleable.QuantityChooseView_leftViewDisableSrc, -1);
            mQuantityTextSize = a.getInt(R.styleable.QuantityChooseView_quantityTextSize, 0);
            mQuantityTextColor = a.getColor(R.styleable.QuantityChooseView_quantityTextColor, -1);
            a.recycle();
        }
        mMaterialDialog = new MaterialDialog(context);

        mIvRight = new ImageView(context);
        mTvQuantity = new TextView(context);
        mIvLeft = new ImageView(context);

        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        this.setOrientation(HORIZONTAL);
        mIvRight.setBackgroundDrawable(null);
        mIvLeft.setBackgroundDrawable(null);

        mIvRight.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mTvQuantity.setLayoutParams(new ViewGroup.LayoutParams(dip2px(context, 50), ViewGroup.LayoutParams.MATCH_PARENT));
        mTvQuantity.setGravity(Gravity.CENTER);
        mIvLeft.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        JudgeNumberAndShowDifferentView(false);
        if (mQuantityTextSize <= 0) {
            mQuantityTextSize = 10;
        }
        mTvQuantity.setTextSize(mQuantityTextSize);
        if (mQuantityTextColor != -1) {
            mTvQuantity.setTextColor(mQuantityTextColor);
        } else {
            mTvQuantity.setTextColor(Color.BLACK);
        }
        this.addView(mIvLeft);
        this.addView(mTvQuantity);
        this.addView(mIvRight);
        initEvent();

    }


    /**
     * 初始化左右view的点击事件
     */
    private void initEvent() {
        mIvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuantity++;
                JudgeNumberAndShowDifferentView(true);
            }
        });
        mIvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuantity--;
                JudgeNumberAndShowDifferentView(true);
            }
        });
        mTvQuantity.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                final EditText et = new EditText(getContext());
                et.setTextSize(14);
                et.setInputType(InputType.TYPE_CLASS_NUMBER);
                et.setTextColor(getContext().getResources().getColor(
                        R.color.blue_new));
                et.setSingleLine(true);
                et.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
                et.setGravity(Gravity.CENTER);
                et.setWidth(dip2px(getContext(), 20));
                et.setText(String.valueOf(mQuantity));
                et.setSelection(et.getText().toString().length());
                et.setBackgroundResource(R.drawable.linearlayout_border_blue);
                mMaterialDialog = new MaterialDialog(getContext());
                mMaterialDialog.setTitle(getContext().getResources().getString(
                        R.string.dialog_title_quantity));
                mMaterialDialog.setContentView(et);
                mMaterialDialog.setTitle(getContext().getResources().getString(
                        R.string.dialog_title_quantity));
                mMaterialDialog.setPositiveButton(R.string.confirm, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mQuantity = toInt(et.getText().toString());
                        submitNumber(et);
                    }
                });
                mMaterialDialog.setNegativeButton(R.string.cancel, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                });
                mMaterialDialog.setCanceledOnTouchOutside(true);
                mMaterialDialog.show();
                mMaterialDialog.showSoftInput();
            }
        });
    }

    /**
     * 弹出数值选择对话框的数值校验
     *
     * @param et
     */
    private void submitNumber(EditText et) {
        String intStr = et.getText().toString();
        if (TextUtils.isEmpty(intStr)) {
            Toast.makeText(getContext(), getContext().getResources().getString(R.string.msg_null_number), Toast.LENGTH_LONG).show();
            return;
        } else if (intStr.equals("0")) {
            Toast.makeText(getContext(), getContext().getResources().getString(R.string.msg_error_onlyOneAbove), Toast.LENGTH_LONG).show();
            return;
        } else if (toInt(intStr) > mMaxQuantity) {// 如果大于库存
            mQuantity = mMaxQuantity;

        } else {
            mQuantity = toInt(intStr);// 先格式化，避免用户输入01这种格式的数字
        }
        JudgeNumberAndShowDifferentView(true);
        mMaterialDialog.dismiss();

    }

    /**
     * 校验数值以及显示不同的左右图片
     */
    private void JudgeNumberAndShowDifferentView(boolean isInformToQuantityChangeListener) {
        if (mQuantity <= 1) {
            if (mQuantity >= mMaxQuantity) {//选择的数量等于库存
                mQuantity = mMaxQuantity;
                setRightAddEnable(false);
                setLeftSubtractEnable(false);
            } else {
                mQuantity = 1;
                setRightAddEnable(true);
                setLeftSubtractEnable(false);
            }
        } else {
            if (mQuantity >= mMaxQuantity) {//选择的数量大于等于库存
                mQuantity = mMaxQuantity;
                setRightAddEnable(false);
                setLeftSubtractEnable(true);
            } else {
                setRightAddEnable(true);
                setLeftSubtractEnable(true);
            }

        }
        mTvQuantity.setText(String.valueOf(mQuantity));
        if (isInformToQuantityChangeListener) {
            if (mLastSelectQuantity != mQuantity)
                if (mOnQuantityChangeListener != null) {
                    mOnQuantityChangeListener.onQuantityChange(mQuantity, mLastSelectQuantity);
                }
        }
        mLastSelectQuantity = mQuantity;
    }


    //---------------get,set------------------
    private void setRightAddEnable(boolean isEnable) {
        if (isEnable) {
            mIvRightEnableSrc = mIvRightEnableSrc != -1 ? mIvRightEnableSrc : R.mipmap.ic_launcher;
            mIvRight.setImageResource(mIvRightEnableSrc);
        } else {
            mIvRightDisableSrc = mIvRightDisableSrc != -1 ? mIvRightDisableSrc : R.mipmap.ic_launcher;
            mIvRight.setImageResource(mIvRightDisableSrc);
        }
    }

    private void setLeftSubtractEnable(boolean isEnable) {
        if (isEnable) {
            mIvLeftEnableSrc = mIvLeftEnableSrc != -1 ? mIvLeftEnableSrc : R.mipmap.ic_launcher;
            mIvLeft.setImageResource(mIvLeftEnableSrc);
        } else {
            mIvLeftDisableSrc = mIvLeftDisableSrc != -1 ? mIvLeftDisableSrc : R.mipmap.ic_launcher;
            mIvLeft.setImageResource(mIvLeftDisableSrc);
        }
    }

    public void setOnQuantityChangeListener(OnQuantityChangeListener mOnQuantityChangeListener) {
        this.mOnQuantityChangeListener = mOnQuantityChangeListener;
    }

    public int getMaxQuantity() {
        return mMaxQuantity;
    }

    public void setMaxQuantity(int mMaxQuantity) {
        this.mMaxQuantity = mMaxQuantity;
        invalidate();
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
        JudgeNumberAndShowDifferentView(false);
    }


    public void setIvRightEnableSrc(int mIvRightEnableSrc) {
        this.mIvRightEnableSrc = mIvRightEnableSrc;
    }


    public void setIvRightDisableSrc(int mIvRightDisableSrc) {
        this.mIvRightDisableSrc = mIvRightDisableSrc;
    }


    public void setIvLeftEnableSrc(int mIvLeftEnableSrc) {
        this.mIvLeftEnableSrc = mIvLeftEnableSrc;
    }


    public void setIvLeftDisableSrc(int mIvLeftDisableSrc) {
        this.mIvLeftDisableSrc = mIvLeftDisableSrc;
    }


    public void setQuantityTextColor(int mQuantityTextColor) {
        this.mQuantityTextColor = mQuantityTextColor;
    }

    public void setQuantityTextSize(float mQuantityTextSize) {
        this.mQuantityTextSize = mQuantityTextSize;
    }
//---------------get,set------------------


    public interface OnQuantityChangeListener {
        /**
         * 数量变更的监听
         *
         * @param currentSelectQuantity 当前选择的数量
         * @param lastSelectQuantity    上一次选择的数量
         */
        void onQuantityChange(int currentSelectQuantity, int lastSelectQuantity);
    }

//-----------------工具方法，为了避免过多引用util，所以本view自带下面方法----------------------------

    /**
     * 转换为整形
     *
     * @param value value
     * @return value
     */
    public static int toInt(String value) {
        int defaultValue = 0;
        if (!isNumeric(value)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 判断是否为数字
     *
     * @param value value
     * @return true
     */
    public static boolean isNumeric(String value) {
        if (TextUtils.isEmpty(value))
            return false;
        Pattern pattern = Pattern.compile("^([-\\+]?[1-9]([0-9]*)(\\.[0-9]+)?)|(^0$)$");
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(Context context, final float dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

}
