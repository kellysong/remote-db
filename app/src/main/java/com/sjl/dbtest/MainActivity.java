package com.sjl.dbtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjl.remotedb.RemoteDb;
import com.sjl.remotedb.annotation.InjectDao;
import com.sjl.remotedb.dao.AsyncDaoExecutor;
import com.sjl.remotedb.dao.ExecutorCallback;
import com.sjl.remotedb.dao.SyncDaoExecutor;
import com.sjl.remotedb.db.DbCallback;
import com.sjl.remotedb.db.DbPoolManager;
import com.sjl.remotedb.page.MysqlSqlPageHandleImpl;
import com.sjl.remotedb.page.Page;
import com.sjl.remotedb.page.SqlPageHandle;
import com.sjl.remotedb.util.CollectionUtils;
import com.sjl.util.ByteUtils;
import com.sjl.util.LogUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author song
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_msg)
    TextView textView;
    @BindView(R.id.iv_head)
    ImageView iv;

    private TestTableDao testTableDao;
    private TestTableAnnotationDao testTableAnnotationDao;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
        initData();
    }


    protected void initView() {
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }

    protected void initListener() {
    }

    protected void initData() {

    }

    public void initDBConn(View view) {

        //网络加载
       /*   RemoteDb.get().initDataSource("http://192.168.0.88:8080/test/db-config.xml", new DbCallback() {
            @Override
            public void onSuccess() {
                initMysqlExecutor();
                  //数据源初始化完毕后处理注解
                processAnnotations();
            }

            @Override
            public void onFailed(Throwable e) {
                LogUtils.e("初始化数据源异常", e);
            }
        });*/
        //从本地配置文件加载
        RemoteDb.get().initDataSource(this, new DbCallback() {
            @Override
            public void onSuccess() {
                initDaoExecutor();
                //数据源初始化完毕后处理注解
                processAnnotations();
            }

            @Override
            public void onFailed(Throwable e) {
                LogUtils.e("初始化数据源异常", e);
            }
        });
        //手动初始化配置
        /*DbConfig dbConfig = new DbConfig.Builder()
                .setDbName("mysql")
                .setActive(true)
                .setDriverClass("driverClass")
                .setJdbcUrl("jdbc:mysql://192.168.0.45:3306/test_db")
                .setUsername("root")
                .setPassword("mysqladmin")
                .setInitialPoolSize(3)
                .setMaxPoolSize(6)
                .setKeepAliveTime(5000).build();

        RemoteDb.get().initDataSource(dbConfig, new DbCallback() {
            @Override
            public void onSuccess() {
                initDaoExecutor();
                //数据源初始化完毕后处理注解
                processAnnotations();
            }

            @Override
            public void onFailed(Throwable e) {
                LogUtils.e("初始化数据源异常", e);
            }
        });*/

//
//        Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.ic_launcher);
//        String s = bitmapToHexStr(bitmap);
//        System.out.println(s);//复制16进制字符串，把字符导入到表中

    }

    /**
     * 处理注解
     */
    private void processAnnotations() {
        testTableAnnotationDao = new TestTableAnnotationDao();
        try {
            RemoteDb.get().inject(this, testTableAnnotationDao);
            LogUtils.i("asyncDaoExecutor:" + asyncDaoExecutor);
        } catch (Exception e) {
            LogUtils.e("注解处理失败", e);
        }
    }

    private static String bitmapToHexStr(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bt = stream.toByteArray();
        StringBuffer sb = new StringBuffer();
        String s = "";
        for (int n = 0; n < bt.length; n++) {
            s = Integer.toHexString(bt[n] & 0XFF);
            if (s.length() == 1) {
                sb.append("0" + s);
            } else {
                sb.append(s);
            }
        }
        return sb.toString();
    }


    /**
     * 原始方法查询
     *
     * @param view
     */
    public void testDBData(View view) {
        //对指定ip使用账密授权
        //GRANT ALL PRIVILEGES ON *.* TO 'root'@'192.168.0.45' IDENTIFIED BY 'mysqladmin' WITH GRANT OPTION;
        //对任何ip使用账密授权
        //GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'mysqladmin' WITH GRANT OPTION;
        //FLUSH   PRIVILEGES;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection conn = DbPoolManager.getInstance().getConnection(AppConstant.DB_MYSQL);
                    if (conn == null) {
                        return;
                    }
                    LogUtils.i("conn:" + conn);
                    PreparedStatement statement = conn.prepareStatement("select test_name,test_img from test_table");
                    ResultSet resultSet = statement.executeQuery();//ResultSet结果集，索引从1开始
                    final StringBuilder sb = new StringBuilder();
                    while (resultSet.next()) {
                        String testName = resultSet.getString(1);
                        sb.append(testName + ",");
                        Blob picture = resultSet.getBlob(2);//得到Blob对象
                        if (picture == null) {
                            continue;
                        }
                        InputStream in = picture.getBinaryStream();
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = in.read(buffer)) != -1) {
                            out.write(buffer, 0, len);
                        }
                        byte[] orgBytes = out.toByteArray();
                        out.close();
                        in.close();
                        showImg(orgBytes);
                    }
                    showMsg(System.currentTimeMillis() + ":" + sb.toString());
                    //关闭
                    resultSet.close();
                    statement.close();
                    DbPoolManager.getInstance().closeConnection(AppConstant.DB_MYSQL, conn);
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        }).start();


    }

    int count = 0;

    private Object object = new Object();

    /**
     * 查询(set方式实例化SyncDaoExecutor)，自动映射成bean
     *
     * @param view
     */
    public void testDBData2(View view) {
        if (testTableDao == null) {
            return;
        }
        count = 0;
        //多线程查询
        for (int i = 0; i < 10; i++) {//并发测试
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<TestTable> target = testTableDao.query(1);
                        if (target != null && !target.isEmpty()) {
                            for (TestTable testTable : target) {
                                LogUtils.i(testTable.toString());
                            }
                            showMsg(target.toString());
                            if (count == 0) {
                                TestTable testTable = target.get(0);
                                byte[] testImg = testTable.getTestImg();//这个是我通过工具存了图片（16进制字符串，对工具来说就是文本）的16进制文本数据
                                showImg(testImg);

                            }
                            synchronized (object) {
                                ++count;
                                LogUtils.i("成功次数：" + count);
                            }

                        } else {
                            LogUtils.i("查询失败");
                        }
                    } catch (Exception e) {
                        LogUtils.e("查询失败", e);
                    }
                }
            }).start();
        }
    }


    private void initDaoExecutor() {
        testTableDao = new TestTableDao();
        /**
         *  set方式初始化SyncDaoExecutor
         */
        SyncDaoExecutor syncDaoExecutor = SyncDaoExecutor.init(AppConstant.DB_MYSQL);
        testTableDao.setSyncDaoExecutor(syncDaoExecutor);
        /**
         *  set方式初始化AsyncDaoExecutor
         */
        AsyncDaoExecutor asyncDaoExecutor = AsyncDaoExecutor.init(syncDaoExecutor);
        testTableDao.setAsyncDaoExecutor(asyncDaoExecutor);
        //或者
      /*  SyncDaoExecutor syncDaoExecutor = RemoteDb.get().getSyncDaoExecutor(AppConstant.DB_MYSQL);
        AsyncDaoExecutor asyncDaoExecutor = RemoteDb.get().getAsyncDaoExecutor(AppConstant.DB_MYSQL);*/
    }

    /**
     * 查询(注解方式初始化SyncDaoExecutor)，自动映射成bean
     *
     * @param view
     */
    public void testDBData3(View view) {
        if (testTableAnnotationDao == null) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LogUtils.e("getSyncDaoExecutor：" + (testTableAnnotationDao.getSyncDaoExecutor() != null ? "注入成功" : "注入失败"));
                    List<TestTable> target = testTableAnnotationDao.query(1);
                    LogUtils.i("target:" + target);
                    showMsg(target.toString());
                } catch (Exception e) {
                    LogUtils.e("注解处理失败", e);
                }
            }
        }).start();
    }

    public void testDBData4(View view) {
        if (testTableDao == null) {
            return;
        }
        testTableDao.queryAsync(textView, 1);
    }

    /**
     * 查询(注解方式初始化AsyncDaoExecutor)，自动映射成bean
     *
     * @param view
     */
    public void testDBData5(View view) {
        if (testTableAnnotationDao == null) {
            return;
        }
        LogUtils.e("getAsyncDaoExecutor：" + (testTableAnnotationDao.getAsyncDaoExecutor() != null ? "注入成功" : "注入失败"));
        AsyncDaoExecutor asyncDaoExecutor = testTableAnnotationDao.getAsyncDaoExecutor();
        asyncDaoExecutor.queryBeanList("select * from test_table  limit  0,1", TestTable.class
                , new ExecutorCallback<List<TestTable>>() {
                    @Override
                    public void onSuccess(List<TestTable> testTables) {
                        LogUtils.i("testTables:" + testTables);
                        showMsg(testTables.toString());
                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });
    }


    private void showImg(byte[] testImg) {
        String hexString = new String(testImg);//转换为文本，即是16进制
        byte[] bytes = ByteUtils.hexStringToByteArr(hexString);
        final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                iv.setImageBitmap(bitmap);
            }
        });
    }


    private void showMsg(final String s) {
        if (isMainThread()) {
            textView.setText(s);
        } else {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(s);
                }
            });
        }

    }

    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RemoteDb.get().close();
        if (unbinder != null) {
            unbinder.unbind();
        }

    }


    /*
     * 在Activity里面注入异步BaseDaoExecutor
     */
    @InjectDao(dbName = AppConstant.DB_MYSQL)
    AsyncDaoExecutor asyncDaoExecutor;

    //以下操作涉及传参的都使用通配符操作

    public void addData(View view) {
        if (asyncDaoExecutor == null) {
            return;
        }
        //采用sql语句操作，当然也可以封装成实体操作，暂时不实现
        String sql = "INSERT INTO test_table(test_id1,test_name,test_age1,test_my_remark) VALUES(?,?,?,?)";
        Object[] params = {99, "李四", 99, "快100岁了"};
        asyncDaoExecutor.update(sql, new ExecutorCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                LogUtils.i("result:" + result);
                if (result) {
                    textView.setText("添加成功");
                } else {
                    textView.setText("添加失败");
                }
            }

            @Override
            public void onFailed(Throwable e) {
                LogUtils.e("addData", e);
            }
        }, params);
    }

    public void modifyData(View view) {
        if (asyncDaoExecutor == null) {
            return;
        }
        String sql = "UPDATE test_table SET test_age1 = test_age1+1 WHERE test_name=? and test_id1 = ?";
        Object[] params = {"李四", 99};
        asyncDaoExecutor.update(sql, new ExecutorCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                LogUtils.i("result:" + result);
                if (result) {
                    textView.setText("修改成功");
                } else {
                    textView.setText("修改失败");
                }
            }

            @Override
            public void onFailed(Throwable e) {
                LogUtils.e("modifyData", e);
            }
        }, params);
    }

    public void deleteData(View view) {
        if (asyncDaoExecutor == null) {
            return;
        }
        String sql = "DELETE FROM test_table WHERE  test_name=? and test_id1 = ?";
        Object[] params = {"李四", 99};
        asyncDaoExecutor.update(sql, new ExecutorCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                LogUtils.i("result:" + result);
                if (result) {
                    textView.setText("删除成功");
                } else {
                    textView.setText("删除失败");
                }
            }

            @Override
            public void onFailed(Throwable e) {
                LogUtils.e("deleteData", e);
            }
        }, params);

    }

    public void queryMap(View view) {
        if (asyncDaoExecutor == null) {
            return;
        }

        String sql = "select * from test_table";
        asyncDaoExecutor.queryBeanForMap(sql, new ExecutorCallback<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {//
                stringObjectMap.remove("test_img");//删除二进制图片，方便观察
                CollectionUtils.mapKeyToCamelCaseName(stringObjectMap);//由于，以数据库的列名为Key，列值为Value，将key转为驼峰命名
                showMsg(stringObjectMap.toString());
            }

            @Override
            public void onFailed(Throwable e) {
                LogUtils.e("queryMap", e);
            }
        });
    }


    public void queryListMap(View view) {
        if (asyncDaoExecutor == null) {
            return;
        }

        String sql = "select * from test_table";
        asyncDaoExecutor.queryBeanForListMap(sql, new ExecutorCallback<List<Map<String, Object>>>() {
            @Override
            public void onSuccess(List<Map<String, Object>> maps) {
                showMsg(maps.toString());
            }

            @Override
            public void onFailed(Throwable e) {
                LogUtils.e("queryListMap", e);
            }
        });
    }

    private int pageNo = 1;

    public void queryByPage(View view) {
        if (asyncDaoExecutor == null) {
            return;
        }
        if (pageNo > 10) {
            pageNo = 1;
        }
        String sql = "select * from test_table where test_name like ?";
        Object[] params = {"%李四%"};

        SqlPageHandle sqlPageHandle = new MysqlSqlPageHandleImpl(sql, pageNo, 1);
        asyncDaoExecutor.queryPagination(sqlPageHandle, TestTable.class, new ExecutorCallback<Page<TestTable>>() {
            @Override
            public void onSuccess(Page<TestTable> page) {
                showMsg(System.currentTimeMillis() + "分页查询成功:" + page.getResultList().toString());
                pageNo++;
            }

            @Override
            public void onFailed(Throwable e) {
                LogUtils.e("queryPagination", e);
            }
        }, params);
    }
}
