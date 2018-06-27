package com.itislevel.lyl.mvp.model.db;


import javax.inject.Inject;


/**
 * **********************
 * 功 能:Realm 数据库 操作
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 17:27
 * 修改人:itisi
 * 修改时间: 2017/7/5 17:27
 * 修改内容:itisi
 * *********************
 */

public class RealmHelper implements DBHelper {

   // private Realm mRealm;

    @Inject
    public RealmHelper() {
//        mRealm = Realm.getInstance(
//                new RealmConfiguration.Builder()
//                .deleteRealmIfMigrationNeeded()
//                .name(Constants.DB_NAME)
//                .build());

    }

    @Override
    public boolean queryNewsId(int id) {
        return false;
    }

    @Override
    public void closeDB() {
//        if (mRealm!=null&&!mRealm.isClosed()){
//            mRealm.close();
//        }
    }

    @Override
    public int test_insert() {
//        mRealm.beginTransaction();
//        String uuid=UUID.randomUUID().toString().replaceAll("-", "");
//        int anInt = new Random().nextInt();
//        Test_Personal personal=new Test_Personal(uuid,"itisi"+anInt,anInt);
//        Test_Personal personal1 = mRealm.copyToRealm(personal);
//
////        Logger.i("getId:"+personal1.getId());
////        mRealm.deleteAll();  //该死 数据被清空了 难过一直查询不到数据
//        mRealm.commitTransaction();·
        return 0;
    }

    @Override
    public int test_delete() {
//        String id="1367ef01ed694785b2f49cd92356c6c0";
//        Test_Personal personal = mRealm.where(Test_Personal.class).equalTo("id", id).findFirst();
//        mRealm.beginTransaction();
//        personal.deleteFromRealm();
//        mRealm.commitTransaction();
//        test_select();
        return 0;
    }

    @Override
    public int test_update() {
//        String id="1367ef01ed694785b2f49cd92356c6c0";
//        Test_Personal personal = mRealm.where(Test_Personal.class).equalTo("id", id).findFirst();
//        mRealm.beginTransaction();
//        personal.setAge(18);
//        mRealm.commitTransaction();
//
//        Test_Personal personal1 = mRealm.where(Test_Personal.class).equalTo("id", id).findFirst();
//        Logger.i("age:"+personal1.getAge());
        return 0;
    }

    @Override
    public int test_select() {
//        RealmResults<Test_Personal> personals = mRealm.where(Test_Personal.class).findAll();
//        for (Test_Personal item:personals){
//            Logger.i(item.getId()+"=="+item.getName()+"==="+item.getAge());
//        }
        return 0;
    }

    public int test_select(String id){
//        Test_Personal personal = mRealm.where(Test_Personal.class).equalTo("id", id).findFirst();
//        Logger.i("getName:"+personal.getName());
        return 0;
    }
}
