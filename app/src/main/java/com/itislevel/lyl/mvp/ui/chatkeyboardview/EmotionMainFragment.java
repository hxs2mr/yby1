package com.itislevel.lyl.mvp.ui.chatkeyboardview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.RootCancleFragment;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmotionMainFragment extends BaseFragment {

    private static final String TAG = "EmotionMainFragment";
    //是否绑定当前Bar的编辑框的flag
    public static final String BIND_TO_EDITTEXT = "bind_to_edittext";
    //是否隐藏bar上的编辑框和发生按钮
    public static final String HIDE_BAR_EDITTEXT_AND_BTN = "hide bar's editText and btn";

    //当前被选中底部tab
    private static final String CURRENT_POSITION_FLAG = "CURRENT_POSITION_FLAG";
    private int CurrentPosition = 0;
    //底部水平tab
    private RecyclerView recyclerview_horizontal;
    private HorizontalRecyclerviewAdapter horizontalRecyclerviewAdapter;
    //表情面板
    private EmotionKeyboard mEmotionKeyboard;

    private EditText bar_edit_text;//编辑框
    //    private ImageView bar_image_add_btn;
    private Button bar_btn_send;//发送按钮
    private ImageView bar_iv_emotion;//表情按钮
    private ImageView bar_iv_extend;//扩展菜单按钮
    private LinearLayout rl_editbar_bg;

    //bar_btn_send
    //bar_iv_extend
    //bar_iv_emotion
    //需要绑定的内容view
    private View contentView;

    //不可横向滚动的ViewPager
    private NoHorizontalScrollerViewPager viewPager;

    //是否绑定当前Bar的编   辑框,默认true,即绑定。
    //false,则表示绑定contentView,此时外部提供的contentView必定也是EditText
    private boolean isBindToBarEditText = true;

    //是否隐藏bar上的编辑框和发生按钮,默认不隐藏
    private boolean isHidenBarEditTextAndBtn = false;

    List<Fragment> fragments = new ArrayList<>();
    private View rootView;

    public EmotionMainFragment() {
    }

    public void hideExtionLayout() {
        mEmotionKeyboard.hideEmotionLayout(true);
    }

    public void showSoftInput() {
        mEmotionKeyboard.showSoftInput();
    }

    /**
     * 创建与Fragment对象关联的View视图时调用
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_emotion_main, container, false);
        isHidenBarEditTextAndBtn = args.getBoolean(EmotionMainFragment.HIDE_BAR_EDITTEXT_AND_BTN);
        //获取判断绑定对象的参数
        isBindToBarEditText = args.getBoolean(EmotionMainFragment.BIND_TO_EDITTEXT);
        initView(rootView);
        bar_iv_extend = (ImageView) rootView.findViewById(R.id.bar_iv_extend);
        mEmotionKeyboard = EmotionKeyboard.with(getActivity())
                .setEmotionView(rootView.findViewById(R.id.ll_emotion_layout))//绑定表情面板
                .bindToContent(contentView)//绑定内容view
                .bindToEditText(!isBindToBarEditText ? ((EditText) contentView) : ((EditText)
                        rootView.findViewById(R.id.bar_edit_text)))//判断绑定那种EditView
                 //.bindToEmotionButton(bar_iv_emotion)//绑定表情按钮
                .bindToExtentionButton(bar_iv_extend)//绑定扩展按钮
                .bindToEmotionButton(rootView.findViewById(R.id.emotion_button))//绑定表情按钮
                .setExtendView(rootView.findViewById(R.id.ll_extend_layout))//绑定扩展菜单布局
                .build();
        initListener();
        initDatas();
        //创建全局监听
        GlobalOnItemClickManagerUtils globalOnItemClickManager = GlobalOnItemClickManagerUtils
                .getInstance(getActivity());

        if (isBindToBarEditText) {
            //绑定当前Bar的编辑框
            globalOnItemClickManager.attachToEditText(bar_edit_text);
        } else {
            // false,则表示绑定contentView,此时外部提供的contentView必定也是EditText
            globalOnItemClickManager.attachToEditText((EditText) contentView);
            mEmotionKeyboard.bindToEditText((EditText) contentView);
        }
        return rootView;

    }

    public void setRootViewIsVisiable(boolean isVisiable) {
        if (isVisiable) {
            rootView.setVisibility(View.VISIBLE);
        } else {
            rootView.setVisibility(View.GONE);
        }

    }


    /**
     * 绑定内容view
     *
     * @param contentView
     * @return
     */
    public void bindToContentView(View contentView) {
        this.contentView = contentView;
    }

    private void initView(View rootView) {

        viewPager = (NoHorizontalScrollerViewPager) rootView.findViewById(R.id.vp_emotionview_layout);
        recyclerview_horizontal = (RecyclerView) rootView.findViewById(R.id.recyclerview_horizontal);
        bar_edit_text = (EditText) rootView.findViewById(R.id.bar_edit_text);
        bar_btn_send = (Button) rootView.findViewById(R.id.bar_btn_send);
        rl_editbar_bg = (LinearLayout) rootView.findViewById(R.id.rl_editbar_bg);
        if (isHidenBarEditTextAndBtn) {//隐藏
            bar_edit_text.setVisibility(View.GONE);
            bar_btn_send.setVisibility(View.GONE);
            rl_editbar_bg.setBackgroundResource(R.color.bg_edittext_color);
        } else {
            bar_edit_text.setVisibility(View.VISIBLE);
            bar_btn_send.setVisibility(View.GONE);
            rl_editbar_bg.setBackgroundResource(R.drawable.shape_bg_reply_edittext);
        }

    }

    public void setEdittextGone() {
        rl_editbar_bg.setVisibility(View.GONE);
    }

    private void initListener() {

    }

    private void initDatas() {
        replaceFragment();
        List<ImageEmotionModel> list = new ArrayList<>();
        for (int i = 0; i < fragments.size(); i++) {
            if (i == 0) {
                ImageEmotionModel model1 = new ImageEmotionModel();
                model1.icon = getResources().getDrawable(R.drawable.ic_emotion);
                model1.flag = "经典笑脸";
                model1.isSelected = true;
                list.add(model1);
            } else {
                ImageEmotionModel model = new ImageEmotionModel();
                model.icon = getResources().getDrawable(R.drawable.ic_plus);
                model.flag = "其他笑脸" + i;
                model.isSelected = false;
                list.add(model);
            }
        }

        //记录底部默认选中第一个
        CurrentPosition = 0;
        SharedPreferencedUtils.setInt(CURRENT_POSITION_FLAG, CurrentPosition);

        //底部tab
        horizontalRecyclerviewAdapter = new HorizontalRecyclerviewAdapter(getActivity(), list);
        recyclerview_horizontal.setHasFixedSize(true);//使RecyclerView保持固定的大小,这样会提高RecyclerView的性能
        recyclerview_horizontal.setAdapter(horizontalRecyclerviewAdapter);
        recyclerview_horizontal.setLayoutManager(new GridLayoutManager(getActivity(), 1,
                GridLayoutManager.HORIZONTAL, false));
        //初始化recyclerview_horizontal监听器
        horizontalRecyclerviewAdapter.setOnClickItemListener(new HorizontalRecyclerviewAdapter
                .OnClickItemListener() {
            @Override
            public void onItemClick(View view, int position, List<ImageEmotionModel> datas) {
                //获取先前被点击tab
                int oldPosition = SharedPreferencedUtils.getInt(CURRENT_POSITION_FLAG, 0);
                //修改背景颜色的标记
                datas.get(oldPosition).isSelected = false;
                //记录当前被选中tab下标
                CurrentPosition = position;
                datas.get(CurrentPosition).isSelected = true;
                SharedPreferencedUtils.setInt(CURRENT_POSITION_FLAG, CurrentPosition);
                //通知更新，这里我们选择性更新就行了
                horizontalRecyclerviewAdapter.notifyItemChanged(oldPosition);
                horizontalRecyclerviewAdapter.notifyItemChanged(CurrentPosition);
                //viewpager界面切换
                viewPager.setCurrentItem(position, false);
            }

            @Override
            public void onItemLongClick(View view, int position, List<ImageEmotionModel> datas) {
            }
        });

    }

    /**
     * 创建多fragments
     */
    private void replaceFragment() {
        //创建fragment的工厂类
        FragmentFactory factory = FragmentFactory.getSingleFactoryInstance();
        //创建修改实例
        EmotiomComplateFragment f1 = (EmotiomComplateFragment) factory.getFragment(EmotionUtils
                .EMOTION_CLASSIC_TYPE);
        fragments.add(f1);
        Bundle b = null;
        for (int i = 0; i <1; i++) {
            b = new Bundle();
           b.putString("Interge", "Fragment-" + i);
           TestFragment fg = TestFragment.newInstance(TestFragment.class, b);
            EmotiomComplateFragment f2 = (EmotiomComplateFragment) factory.getFragment(EmotionUtils
                    .EMOTION_CLASSIC_TYPE);
            fragments.add(f2);
        }

        NoHorizontalScrollerVPAdapter adapter = new NoHorizontalScrollerVPAdapter(getActivity()
                .getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    /**
     * 是否拦截返回键操作，如果此时表情布局未隐藏，先隐藏表情布局
     *
     * @return true则隐藏表情布局，拦截返回键操作
     * false 则不拦截返回键操作
     */
    public boolean isInterceptBackPress() {
        return mEmotionKeyboard.interceptBackPress();
    }

    /**
     * 关闭表情布局
     * 关闭扩展菜单
     */
    public void hideEmotionLayoutoOrExtenLayout() {
        if (mEmotionKeyboard != null) {
            mEmotionKeyboard.hideEmotionLayout(false);
            mEmotionKeyboard.hideExtionLayout(false);
        }
    }

    public void hideEmotionLayoutoAndExtenLayout() {
        if (mEmotionKeyboard != null)
            mEmotionKeyboard.hideEmotionLayout();

    }


    public void showEmotionLayoutoAndExtenLayout() {
        if (mEmotionKeyboard != null)
            mEmotionKeyboard.showEmotionLayout();

    }


    public boolean isAtLeastShow() {
        return mEmotionKeyboard.isAtLeastShow();
    }

}
