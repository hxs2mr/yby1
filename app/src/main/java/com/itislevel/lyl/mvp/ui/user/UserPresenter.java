package com.itislevel.lyl.mvp.ui.user;


import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MyReceiveAddrBean;
import com.itislevel.lyl.mvp.model.bean.RegistBean;
import com.itislevel.lyl.mvp.model.bean.SmsBean;
import com.itislevel.lyl.mvp.model.bean.UserInfoBean;
import com.itislevel.lyl.mvp.model.http.exception.ApiException;
import com.itislevel.lyl.mvp.model.http.response.GankResponse;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;
import okhttp3.MultipartBody;

/**
 * **********************
 * 功 能:主页-主页
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/7 13:58
 * 修改人:itisi
 * 修改时间: 2017/7/7 13:58
 * 修改内容:itisi
 * *********************
 */
public class UserPresenter extends RxPresenter<UserContract.View> implements UserContract
        .Presenter {

    private DataManager mDataManager;

    @Inject
    public UserPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void loadData(int num, int page) {
        mDataManager.getMeiZiList(num, page)
                .compose(RxUtil.<GankResponse<List<MeiZiBean>>>rxSchedulerObservableHelper())
                .compose(RxUtil.<List<MeiZiBean>>handlerGankResult())
                .subscribeWith(new ResourceObserver<List<MeiZiBean>>() {
                    @Override
                    public void onNext(@NonNull List<MeiZiBean> meiZiBeen) {
                        mView.showContent("itisi:" + meiZiBeen.get(0).getCreatedAt());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.i("错误信息:" + e.getMessage());
                        if (mView!=null){
                            mView.stateError(e);

                        }
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("complete");
                    }
                });

    }

    @Override
    public void login(String action) {
        mDataManager.login(action)
                .compose(RxUtil.<LYLResponse<UserInfoBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<UserInfoBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<UserInfoBean>() {
                    @Override
                    public void onNext(UserInfoBean response) {
                        mView.login(response);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Logger.e("错误出现咯。。。", e);
//                        ApiException apiException = (ApiException) e;
//                        mView.showErrorMsg("帐号或密码错误");
                        if (mView!=null){
                            mView.stateError(e);
                        }
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getValidateCode(String action) {
        mDataManager.getValidateCode(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String validateCode) {
                        mView.getValidateCode(validateCode);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("错误出现咯:"+e.getMessage(), e);

                        if (mView!=null){
                            mView.stateError(e);

                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getSSMCode(String action) {
        mDataManager.getSSMCode(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String msg) {
                        mView.getSSMCode(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (mView!=null){
                            mView.stateError(e);

                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    @Override
    public void regist(String action) {
        mDataManager.register(action)
                .compose(RxUtil.<LYLResponse<RegistBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<RegistBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<RegistBean>(){
                    @Override
                    public void onNext(RegistBean response) {
                        mView.regist(response);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Logger.e("错误出现咯。。。", e);
//                        ApiException apiException = (ApiException) e;
//                        mView.showErrorMsg(apiException.getCode() + "--" + apiException
//                                .getMessage());

                        if (mView!=null){
                            mView.stateError(e);

                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void uploadHeader( MultipartBody.Part file) {
        mDataManager.uploadHeader(file)
                .compose(RxUtil.<LYLResponse<FileUploadBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FileUploadBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FileUploadBean>() {
                    @Override
                    public void onNext(@NonNull FileUploadBean fileUploadBean) {
                        mView.uploadHeader(fileUploadBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e("错误信息:" + e.getMessage());
                        if (mView!=null){
                            mView.stateError(e);

                        }
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("complete");
                    }
                });
    }

    @Override
    public void userPerfectPersonal(String action) {
        mDataManager.userPerfectPersonal(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String response) {
                        mView.userPerfectPersonal(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("错误出现咯。。。", e);
//                        ApiException apiException = (ApiException) e;
//                        mView.showErrorMsg(apiException.getCode() + "--" + apiException
//                                .getMessage());
                        mView.userPerfectPersonal(e.getMessage());
                        if (mView!=null){
                            mView.stateError(e);

                        }

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void userModifyPassword(String action) {
        mDataManager.userModifyPassword(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String response) {
                        mView.userModifyPassword(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("错误出现咯。。。", e);
//                        ApiException apiException = (ApiException) e;
//                        mView.showErrorMsg(apiException.getCode() + "--" + apiException
//                                .getMessage());

                        if (mView!=null){
                            mView.stateError(e);

                        }
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void userModifyNickname(String action) {
        mDataManager.userModifyNickname(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String response) {
                        mView.userModifyNickname(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("错误出现咯。。。", e);
                        if (mView!=null){
                            mView.stateError(e);

                        }

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void userFindRecAddress(String action) {
        mDataManager.userFindRecAddress(action)
                .compose(RxUtil.<LYLResponse<MyReceiveAddrBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<MyReceiveAddrBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<MyReceiveAddrBean>() {
                    @Override
                    public void onNext(MyReceiveAddrBean response) {
                        mView.userFindRecAddress(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("错误出现咯。。。", e);
                        if (mView!=null){
                            mView.stateError(e);

                        }

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void userUpdateRecAddress(String action) {
        mDataManager.userUpdateRecAddress(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String response) {
                        mView.userUpdateRecAddress(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("错误出现咯。。。", e);
                        if (mView!=null){
                            mView.stateError(e);

                        }

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void userGiftmyAdd(String action) {
        mDataManager.userGiftAdd(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String response) {
                        mView.userGiftmy(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("错误出现咯。。。", e);
                        if (mView!=null){
                            mView.stateError(e);

                        }

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void userModifyHeader(String action) {
        mDataManager.userModifyHeader(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String response) {
                        mView.userModifyHeader(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("错误出现咯。。。", e);
                        if (mView!=null){
                            mView.stateError(e);

                        }

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void userForgetPassword(String action) {
        mDataManager.userForgetPasswrord(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String response) {
                        mView.userForgetPassword(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("错误出现咯。。。", e);
                        if (mView!=null){
                            mView.stateError(e);

                        }

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }


}
