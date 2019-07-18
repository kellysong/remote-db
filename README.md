# remote-db
 remote-db是一款轻量级、高性能的Android端JDBC库，基于DBUtils扩展而来，保留了DBUtils的所有功能，还具备以下功能特点：

1. 封装了常用CRUD 及批量CUD,支持sql查询自动映射成实体、集合（List,Map）
2. 自定义了数据库连接池（负责分配、管理和释放数据库连接），支持配置多个数据源（连接不同的数据库）
3. 支持同步和异步执行sql语句，支持注解注入dao实例（可配置单例还是多例），更方便地执行sql语句 
4. 只要引入对应的数据库驱动包，即可连接mysql、sqlserver、oracle等数据库
# 效果图

<img src="https://github.com/kellysong/remote-db/blob/master/screenshot/example.png" width="30%" alt="加载中..."/>
# 引用

##Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }

##Step 2. Add the dependency

	dependencies {
		        implementation 'com.github.kellysong:remote-db:1.0.0'
 				implementation 'mysql:mysql-connector-java:5.0.8'//mysql驱动包
		}



## 使用步骤

### 1.初始化数据源(只需要调用一次)
		    DbConfig dbConfig = new DbConfig();
	        dbConfig.setDbName("mysql");//dbName是数据源名字，可以自定义，主要用于区分不同的数据源
	        dbConfig.setActive(true);
	        dbConfig.setDriverClass("driverClass");
	        dbConfig.setJdbcUrl("jdbc:mysql://localhost:3306/test_db");
	        dbConfig.setUsername("xxxx");
	        dbConfig.setPassword("xxxx");
	        dbConfig.setInitialPoolSize(3);
	        dbConfig.setMaxPoolSize(6);
	        dbConfig.setKeepAliveTime(5000);
	
	        RemoteDb.get().initDataSource(dbConfig, new DbCallback() {
	            @Override
	            public void onSuccess() {
	               //必须在数据源初始化后，获取DaoExecutor;
	            }
	
	            @Override
	            public void onFailed(Throwable e) {
	                LogUtils.e("初始化数据源异常", e);
	            }
	        });


### 2.获取DaoExecutor
	
方法1：

（1）在需要使用DaoExecutor的地方使用@InjectDao注入DaoExecutor

	@InjectDao(dbName = AppConstant.DB_MYSQL, isAsync = false)
    SyncDaoExecutor syncDaoExecutor;//同步dao

	@InjectDao(dbName = "mysql")
    AsyncDaoExecutor asyncDaoExecutor;//异步dao

（2）处理注解	 

	RemoteDb.get().inject(this);//参数可以任意实例，包括Activity,Fragment,Dao层，MVP的P层等

or 方法2：

	SyncDaoExecutor syncDaoExecutor = RemoteDb.get().getSyncDaoExecutor("mysql");//同步dao
        
	AsyncDaoExecutor asyncDaoExecutor =RemoteDb.get().getAsyncDaoExecutor("mysql");//异步dao


### 3.使用DaoExecutor进行CRUD

由于数据库连接请求也属于网络请求，故使用异步asyncDaoExecutor
		
		//将结果查询为Bean
     	asyncDaoExecutor.queryBean(...){...}
        //将结果查询为BeanList
		asyncDaoExecutor.queryBeanList(...){...}
		//将结果查询为Map
        asyncDaoExecutor.queryBeanForMap(...){...}
		//将结果查询为ListMap
        asyncDaoExecutor.queryBeanForListMap(...){...}
		//更新、修改，删除
		asyncDaoExecutor.update(...){...}
        //更新、修改，删除（带事务）
		asyncDaoExecutor.updateInTx(...){...}
        //批量更新、修改，删除（带事务）
		asyncDaoExecutor.batchDeleteInTx(...){...}


### 5.关闭数据源

	 RemoteDb.get().close();

更多用法参考博客：

[https://blog.csdn.net/u011082160/article/details/96303046](https://blog.csdn.net/u011082160/article/details/96303046 "https://blog.csdn.net/u011082160/article/details/96303046")

# License

    Copyright 2019 Song Jiali
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.