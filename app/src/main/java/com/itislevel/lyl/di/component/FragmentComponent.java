package com.itislevel.lyl.di.component;

import android.app.Activity;


import com.itislevel.lyl.di.module.FragmentModule;
import com.itislevel.lyl.di.scope.FragmentScope;
import com.itislevel.lyl.mvp.ui.dynamicmyperson.childpersonfragment.Dynamic_personFragment;
import com.itislevel.lyl.mvp.ui.dynamicmyperson.childpersonfragment.Family_personFragment;
import com.itislevel.lyl.mvp.ui.family.childhomefragment.dfind.FFindFragment;
import com.itislevel.lyl.mvp.ui.family.childhomefragment.dfollow.FFollowFragment;
import com.itislevel.lyl.mvp.ui.family.childhomefragment.toncity.FTonCityFragment;
import com.itislevel.lyl.mvp.ui.family.giftchildfragment.GiftChildFragment;
import com.itislevel.lyl.mvp.ui.main.childfragment.ChildFragment;
import com.itislevel.lyl.mvp.ui.main.customer.CustomerFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.DynamicFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind.DFindFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfollow.DFollowFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.toncity.DTonCityFragment;
import com.itislevel.lyl.mvp.ui.main.home.HomeFragment;
import com.itislevel.lyl.mvp.ui.main.mine.MineFragment;
import com.itislevel.lyl.mvp.ui.property.childfragment.ChildFragmentHome;
import com.itislevel.lyl.mvp.ui.property.childfragment.ChildFragmentUser;
import com.itislevel.lyl.mvp.ui.property.complaint.childfragment.PropertyComplaintFragment1;
import com.itislevel.lyl.mvp.ui.property.complaint.childfragment.PropertyComplaintFragment2;
import com.itislevel.lyl.mvp.ui.special.OrderAllFragment;
import com.itislevel.lyl.mvp.ui.special.OrderCompleteFragment;
import com.itislevel.lyl.mvp.ui.special.OrderNoPayFragment;
import com.itislevel.lyl.mvp.ui.special.OrderNoSendFragment;
import com.itislevel.lyl.mvp.ui.special.OrderRefundFragment;
import com.itislevel.lyl.mvp.ui.special.OrderWaitingReceiveFragment;

import dagger.Component;

/**
 * **********************
 * 功 能:为fragment注入依赖
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 17:43
 * 修改人:itisi
 * 修改时间: 2017/7/5 17:43
 * 修改内容:itisi
 * *********************
 */
@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();
    //主页
    void inject(HomeFragment fragment);
    void inject(CustomerFragment fragment);
    void inject(MineFragment fragment);
    void inject(DynamicFragment fragment);
    void inject(DFollowFragment fragment);//关注
    void inject(DFindFragment fragment);//发现
    void inject(DTonCityFragment fragment);//同城模块
    void inject(Dynamic_personFragment fragment);//同城模块
    void inject(OrderAllFragment fragment);
    void inject(OrderCompleteFragment fragment);
    void inject(OrderNoPayFragment fragment);
    void inject(OrderNoSendFragment fragment);
    void inject(OrderRefundFragment fragment);
    void inject(OrderWaitingReceiveFragment fragment);
    void inject(FFindFragment fragment);
    void inject(FFollowFragment fragment);
    void inject(FTonCityFragment fragment);
    void inject(Family_personFragment fragment);

    //咨询
    void inject(ChildFragment fragment);

    void inject(GiftChildFragment fragment);

    void inject(ChildFragmentHome fragmentHome);
    void inject(ChildFragmentUser fragmentHome);

    void inject(PropertyComplaintFragment1 fragment1);
    void inject(PropertyComplaintFragment2 fragment2);
}
