package com.itislevel.lyl.mvp.ui.family.writer_letter;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;

/**
 * Created by Administrator on 2018\5\14 0014.
 */

public class LetterWriterContract {
    interface View extends BaseView{
        void save(String msg);
        void looknumLetter(String msg);
    }
    interface  Presenter extends BasePresenter<View>
    {
        void save(String msg);
        void looknumLetter(String msg);
    }
}
