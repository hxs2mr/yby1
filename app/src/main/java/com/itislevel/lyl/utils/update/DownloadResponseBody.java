package com.itislevel.lyl.utils.update;


import com.itislevel.lyl.utils.rxbus.RxBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
/**
 ***********************
 * 功 能:必填必填必填必填必填必填
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/28 17:11
 * 修改人:itisi
 * 修改时间: 2017/7/28 17:11
 * 修改内容:itisi
 * *********************
 */
class DownloadResponseBody extends ResponseBody {
    private final ResponseBody mResponseBody;
    private BufferedSource mBufferedSource;

    public DownloadResponseBody(ResponseBody responseBody) {
        mResponseBody = responseBody;
    }

    @Override
    public MediaType contentType() {
        return mResponseBody.contentType();
    }

    @Override
    public long contentLength() {
        return mResponseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (mBufferedSource == null) {
            mBufferedSource = Okio.buffer(source(mResponseBody.source()));
        }
        return mBufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            private long mProgress = 0;
            private long mLastSendTime = 0;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                mProgress += bytesRead == -1 ? 0 : bytesRead;
                if (System.currentTimeMillis() - mLastSendTime > 500) {
                    RxBus.getInstance().post(new DownloadProgress(contentLength(), mProgress));
                    mLastSendTime = System.currentTimeMillis();
                } else if (mProgress == contentLength()) {
                    Observable.just(mProgress).delaySubscription(500, TimeUnit.MILLISECONDS, Schedulers.io()).subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(@NonNull Long aLong) throws Exception {
                            RxBus.getInstance().post(new DownloadProgress(contentLength(), mProgress));
                        }
                    });
                }
                return bytesRead;
            }
        };
    }

}