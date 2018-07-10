package com.itislevel.lyl.mvp.ui.main.home.HomeAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.recyclew.DataConVerter;
import com.itislevel.lyl.adapter.recyclew.ItemType;
import com.itislevel.lyl.adapter.recyclew.MultipViewHolder;
import com.itislevel.lyl.adapter.recyclew.MultipleFields;
import com.itislevel.lyl.adapter.recyclew.MultipleItemEntity;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.AdviserBean;
import com.itislevel.lyl.mvp.model.bean.HeadlinesBean;
import com.itislevel.lyl.mvp.model.bean.HeathBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.HouseKeepBean;
import com.itislevel.lyl.mvp.model.bean.MainBean;
import com.itislevel.lyl.mvp.model.bean.ModularBean;
import com.itislevel.lyl.mvp.ui.housekeep.HouseKeepActivity;
import com.itislevel.lyl.mvp.ui.housekeep.HouseKeepDetailActivity;
import com.itislevel.lyl.mvp.ui.main.cwebfragactivity.CWebActivity;
import com.itislevel.lyl.mvp.ui.main.home.HomeModel.HomeFields;
import com.itislevel.lyl.mvp.ui.main.home.notice.ENoticeModel;
import com.itislevel.lyl.mvp.ui.main.home.notice.ENoticeView;
import com.itislevel.lyl.mvp.ui.main.home.notice.NoticeAdapter;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.widget.TOPImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.CITY_ID;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.CITY_NAME;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.P_ID;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.P_NAME;
import static com.itislevel.lyl.utils.DateUtil.timeSpanToDate;
import static com.itislevel.lyl.utils.DateUtil.timeSpanToDate_han;


/**
 * Created by microtech on 2017/11/20.主界面的Adapter
 */

public class  MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity,MultipViewHolder> implements BaseQuickAdapter.SpanSizeLookup{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */

    //装入获取到的view
    private List<View> view_list = null;
    private  int home_two_layout = R.layout.home_rv_two_item;
    private ViewPager viewPager = null;
    private    Context mContext = null;
    private Activity mActivity=null;
    private ENoticeView eNoticeView = null;

    private List<AppCompatTextView> list_head_two = null;
    List<HomeBean.ToutiaoBean> head_list;//头条测试数据
    List<HomeBean.HomemakingBean> adv_list;//头条测试数据
    List<HomeBean.HealthyBean> heath_list;//头条测试数据
    //确保初始化一次banner  避免重复加载;
    private boolean mIsBanner = false;
    private LinearLayoutCompat linear1 ;
    private LinearLayoutCompat linear2 ;
    private  LinearLayoutCompat linear3 ;

    private TOPImageView image1 ;
    private TOPImageView image2 ;
    private  TOPImageView image3 ;

    private AppCompatTextView comment1;
    private AppCompatTextView comment2;
    private AppCompatTextView comment3;

    private AppCompatTextView title1;
    private AppCompatTextView title2;
    private AppCompatTextView title3;

    private AppCompatTextView time1;
    private AppCompatTextView time2;
    private AppCompatTextView time3;

    private int refresh_three_falge =0;

    private LinearLayoutCompat refresh_three;

    private AppCompatImageView refresh_image;
    private AppCompatTextView refresh_text;
    //设置图片的加载测曰
   /* private static final RequestOptions RECYCLER_OPTIONS=
            new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();
*/
    protected MultipleRecyclerAdapter(Activity activity,Context context,List<MultipleItemEntity> data) {
        super(data);
        this.mContext = context;
        this.mActivity = activity;
        init();
    }

    public MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
    }

    public void refresh(List<MultipleItemEntity> data, View view) {
        getData().clear();
        setNewData(data);
        notifyDataSetChanged();
    }

    public void refresh(List<MultipleItemEntity> data) {
        getData().clear();
        setNewData(data);
        notifyDataSetChanged();
    }
    public static MultipleRecyclerAdapter create(Activity activity, Context context, List<MultipleItemEntity> data){
        return new MultipleRecyclerAdapter(activity,context,data);
    }

    public static MultipleRecyclerAdapter create(Activity activity,Context context,DataConVerter verter){
        return new MultipleRecyclerAdapter(activity,context,verter.CONVERT());
    }

    @Override
    protected void convert(MultipViewHolder helper, MultipleItemEntity item){//布局设置
        switch (helper.getItemViewType())
        {
          case ItemType.HOME_VIEW_ONE:
              eNoticeView = helper.getView(R.id.noticeview);
              //data1 = new ArrayList<>();
              head_list = new ArrayList<>();
              head_list = item.getField(HomeFields.LIST_HEAD);//获取到头条数据

             /// item.getField(OrderQianFields.AGE).toString()
           /*   //测试数据
              ENoticeModel eNoticeModel1 = new ENoticeModel("贵阳友邦友网络科技有限公司","专注于APP开发，网站建设");
              ENoticeModel eNoticeModel2 = new ENoticeModel("贵阳房价4月份持续上涨","全款变首付");
              ENoticeModel eNoticeModel3 = new ENoticeModel("白云区大山洞交通堵塞","你在路上了吗？");
              data1.add(eNoticeModel2);
              data1.add(eNoticeModel3);*/
              onClick_notice();
              //  AppCompatTextView name = helper.getView(R.id.qian_name);

             //   if(item.getField(MultipleFields.SEX).toString().equals("1"))
                break;

          case ItemType.HOME_VIEW_TWO:
              adv_list = new ArrayList<>();
               viewPager = helper.getView(R.id.home_two);
               helper.addOnClickListener(R.id.head_two_more);
               AppCompatTextView  head_two_more =  helper.getView(R.id.head_two_more);
               head_two_more.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                      String   title = "家政服务";
                      Bundle bundle = new Bundle();
                       bundle.putString(Constants.PROVINCE_TITLE, title);
                       bundle.putString(Constants.CITY_TITLE, title);
                       bundle.putString(Constants.COUNTY_TITLE, title);
                       bundle.putBoolean(Constants.IS_SHOW_COUNTY, true);

                       bundle.putString(Constants.PROVINCE_ID, P_ID);
                       bundle.putString(Constants.PROVINCE_NAME, P_NAME);
                       bundle.putString(Constants.CITY_ID, CITY_ID);
                       bundle.putString(Constants.CITY_NAME, CITY_NAME);

                       bundle.putInt(Constants.ACTIVITY_TARGET, Constants.ACTIVITY_HOUSE);
                       ActivityUtil.getInstance().openActivity(mActivity, HouseKeepActivity.class,
                               bundle);
                   }
               });
              adv_list = item.getField(HomeFields.LIST_ADVISER);//获取到头条数据
              String image_url = item.getField(HomeFields.START_IM_URL);//获取到头条数据
              AppCompatTextView rv_two_name ;
              AppCompatTextView rv_two_address ;
              AppCompatTextView tv_two_renqi ;
              CircleImageView  iv_two_head ;
              AppCompatTextView tv_two_create;
              AppCompatTextView tv_two_content ;
              int size = adv_list.size();
              view_list = new ArrayList<>();
              for (int i = 0; i <size ; i++){//添加功能节目
                  View v = View.inflate(mContext,home_two_layout,null);
                  rv_two_name=v.findViewById(R.id.rv_two_name);
                  rv_two_address=v.findViewById(R.id.rv_two_address);
                  iv_two_head=v.findViewById(R.id.iv_two_head);
                  tv_two_create = v.findViewById(R.id.tv_two_create);
                  tv_two_content = v.findViewById(R.id.tv_two_content);
                  tv_two_content.setText(adv_list.get(i).getCompanyrem());
                  tv_two_create.setText(timeSpanToDate_han(adv_list.get(i).getCreatedtime())+"成立");
                  rv_two_name.setText(adv_list.get(i).getCompanyname());
                  rv_two_address.setText(adv_list.get(i).getProvname()+""+adv_list.get(i).getCityname());
                  Glide.with(mContext)
                          .load(image_url+adv_list.get(i).getCompanylogo())
                          .asBitmap()
                          .error(R.mipmap.test)
                          .diskCacheStrategy(DiskCacheStrategy.ALL)
                          .into(iv_two_head);
                  view_list.add(v);
                }
                viewPager.setAdapter(new MyViewPageViewAdapter(mContext,view_list));
                viewPager.setPageMargin(30);
                break;
            case ItemType.HOME_VIEW_THREE:
                refresh_three_falge = 0;
                heath_list = new ArrayList<>();
                heath_list = item.getField(HomeFields.LIST_HEATH);//
                String image_url2 = item.getField(HomeFields.START_IM_URL);//获取到头条数据

                 refresh_image=  helper.getView(R.id.refresh_image);
                 refresh_text=  helper.getView(R.id.refresh_text);

                 linear1 =  helper.getView(R.id.linear1);
                 linear2 =  helper.getView(R.id.linear2);
                 linear3 =  helper.getView(R.id.linear3);

                 image1 =  helper.getView(R.id.image1);
                 image2 =  helper.getView(R.id.image2);
                 image3 =  helper.getView(R.id.image3);

                 comment1=  helper.getView(R.id.comment1);
                 comment2=  helper.getView(R.id.comment2);
                 comment3=  helper.getView(R.id.comment3);

                 title1=  helper.getView(R.id.title1);
                 title2=  helper.getView(R.id.title2);
                 title3=  helper.getView(R.id.title2);

                 time1=  helper.getView(R.id.time1);
                 time2=  helper.getView(R.id.time2);
                 time3=  helper.getView(R.id.time3);
                refresh_three = helper.getView(R.id.refresh_three);
                int heath_size = heath_list.size();
                if(heath_size%3==0) {
                    if (heath_size != 0)
                    {
                        intt_data(refresh_three_falge,image_url2);
                    }
                }
                refresh_three.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Animation operatingAnim = AnimationUtils.loadAnimation(mContext, R.anim.xuanzhuang);
                        LinearInterpolator lin = new LinearInterpolator();
                        operatingAnim.setInterpolator(lin);
                        refresh_image.setAnimation(operatingAnim);
                        refresh_text.setText("加载中");
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                refresh_three_falge = refresh_three_falge+3;
                                if(refresh_three_falge>=9)
                                {
                                    refresh_three_falge = 0;
                                }
                                if(refresh_three_falge<=(heath_size-3))
                                {
                                    intt_data(refresh_three_falge,image_url2);
                                }
                                refresh_image.clearAnimation();
                                refresh_text.setText("换一批");
                            }
                        },500);
                    }
                });
                linear1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url",heath_list.get(refresh_three_falge).getFlat_info_url());
                        bundle.putString("id",heath_list.get(refresh_three_falge).getId()+"");
                        bundle.putString("pointnum",heath_list.get(refresh_three_falge).getPointnum()+"");
                        bundle.putString("nosense",heath_list.get(refresh_three_falge).getNosense()+"");
                        bundle.putString("looknum",heath_list.get(refresh_three_falge).getLooknum()+"");
                        bundle.putString("commentnum",0+"");
                        bundle.putString("descript",heath_list.get(refresh_three_falge).getTitle()+"");
                        bundle.putString("share_image",heath_list.get(refresh_three_falge).getLogo()+"");
                        bundle.putString("publisher",heath_list.get(refresh_three_falge).getPublisher()+"");
                        ActivityUtil.getInstance().openActivity(mActivity, CWebActivity.class,bundle);
                    }
                });
                linear2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url",heath_list.get(refresh_three_falge+1).getFlat_info_url());
                        bundle.putString("id",heath_list.get(refresh_three_falge+1).getId()+"");
                        bundle.putString("pointnum",heath_list.get(refresh_three_falge+1).getPointnum()+"");
                        bundle.putString("nosense",heath_list.get(refresh_three_falge+1).getNosense()+"");
                        bundle.putString("looknum",heath_list.get(refresh_three_falge+1).getLooknum()+"");
                        bundle.putString("commentnum",0+"");
                        bundle.putString("descript",heath_list.get(refresh_three_falge+1).getTitle()+"");
                        bundle.putString("share_image",heath_list.get(refresh_three_falge+1).getLogo()+"");
                        bundle.putString("publisher",heath_list.get(refresh_three_falge+1).getPublisher()+"");
                        ActivityUtil.getInstance().openActivity(mActivity, CWebActivity.class,bundle);
                    }
                });
                linear3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url",heath_list.get(refresh_three_falge+2).getFlat_info_url());
                        bundle.putString("id",heath_list.get(refresh_three_falge+2).getId()+"");
                        bundle.putString("pointnum",heath_list.get(refresh_three_falge+2).getPointnum()+"");
                        bundle.putString("nosense",heath_list.get(refresh_three_falge+2).getNosense()+"");
                        bundle.putString("looknum",heath_list.get(refresh_three_falge+2).getLooknum()+"");
                        bundle.putString("commentnum",0+"");
                        bundle.putString("descript",heath_list.get(refresh_three_falge+2).getTitle()+"");
                        bundle.putString("share_image",heath_list.get(refresh_three_falge+2).getLogo()+"");
                        bundle.putString("publisher",heath_list.get(refresh_three_falge+2).getPublisher()+"");
                        ActivityUtil.getInstance().openActivity(mActivity, CWebActivity.class,bundle);
                    }
                });
                break;
            default:
                break;
        }
    }
    private void intt_data(int postion,String image_url) {
        int heath_size = heath_list.size();
            Glide.with(mContext)
                    .load(image_url+heath_list.get(postion).getLogo())
                    .asBitmap()
                    .error(R.mipmap.person_head)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image1);
            Glide.with(mContext)
                    .load(image_url+heath_list.get(postion+1).getLogo())
                    .asBitmap()
                    .error(R.mipmap.person_head)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image2);
            Glide.with(mContext)
                    .load(image_url+heath_list.get(postion+2).getLogo())
                    .asBitmap()
                    .error(R.mipmap.person_head)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image3);

        comment1.setText(heath_list.get(postion).getTitle());
        comment2.setText(heath_list.get(postion+1).getTitle());
        comment3.setText(heath_list.get(postion+2).getTitle());

        title1.setText(heath_list.get(postion).getPublisher());
        title2.setText(heath_list.get(postion+1).getPublisher());
        title3.setText(heath_list.get(postion+2).getPublisher());

        time1.setText(timeSpanToDate(heath_list.get(postion).getCreatedtime()));
        time2.setText(timeSpanToDate(heath_list.get(postion+1).getCreatedtime()));
        time3.setText(timeSpanToDate(heath_list.get(postion+2).getCreatedtime()));
    }
    private void onClick_notice() {
        //今日头条滚动模块
        eNoticeView.setAdapter(new NoticeAdapter(){
            @Override
            public int getCount() {
                return (head_list.size()/2);
            }
            @Override
            public View getView(Context context, int position) {
                View view = View.inflate(context, R.layout.hone_enoticeview, null);
                ((AppCompatTextView) view.findViewById(R.id.one_title_content)).setText(head_list.get(position).getTitle());
                ((AppCompatTextView) view.findViewById(R.id.one_title)).setText(head_list.get(position).getCatename());

                    ((AppCompatTextView) view.findViewById(R.id.two_title )).setText(head_list.get(head_list.size()-position-1).getCatename());
                    ((AppCompatTextView) view.findViewById(R.id.two_title_content)).setText(head_list.get(head_list.size()-position-1).getTitle());

                return view;
            }
        });
        eNoticeView.setOnItemClickListener(new ENoticeView.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                EventBus.getDefault().post(new MainBean("1"));
             /*   String[] a = new String[4];
                a[0] = data1.get(position).getUrl();
                a[1] = data1.get(position).getNoticeName2();
             Bundle bundle = new Bundle();
                bundle.putStringArray("two_data", a);//传数据集合
                mActivity.pushFragment(new TwoFragment(), true, bundle);*/
            }
        });
    }

    private void init()//设置不同的布局
    {
        view_list = new ArrayList<>();
        addItemType(ItemType.HOME_VIEW_ONE, R.layout.homefragment_rv_item_one);
        addItemType(ItemType.HOME_VIEW_TWO, R.layout.homefragment_rv_item_two);
        addItemType(ItemType.HOME_VIEW_THREE, R.layout.homefragment_rv_item_three);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        //多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected MultipViewHolder createBaseViewHolder(View view) {//传入现有的viewholder
        return MultipViewHolder.create(view);
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

    public class MyViewPageViewAdapter extends PagerAdapter {
        private List<View> mList;
        private LayoutInflater layoutInflater;

        public MyViewPageViewAdapter(Context context,  List<View> view_list) {
            super();
            this.mList = view_list;
            layoutInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 页面宽度所占ViewPager测量宽度的权重比例，默认为1
         */
        @Override
        public float getPageWidth(int position) {
            return (float) 0.7;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            ((ViewPager) container).removeView(view);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = view_list.get(position);
            container.addView(v);
            init_onclick_two_detail(mList,position);
            return v;
        }
        private void init_onclick_two_detail(List<View> mList, final int position) {
            mList.get(position).findViewById(R.id.lienar_detail_linear).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    HomeBean.HomemakingBean item =adv_list.get(position);
                    bundle.putString("company_name", item.getCompanyname());
                    bundle.putString("company_desc", item.getCompanyrem());
                    bundle.putString("company_addr", item.getAddress());
                    bundle.putString("company_phone", item.getContactphone());
                    bundle.putString("company_img", item.getCompanyimge());
                    bundle.putString("company_contact", item.getContactname());
                    bundle.putString("company_logo", item.getCompanylogo());
                    ActivityUtil.getInstance().openActivity(mActivity, HouseKeepDetailActivity.class, bundle);
                }
            });
        }
    }

}
