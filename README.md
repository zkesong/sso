# 单点登录系统
一个基于dubbo、redis、springboot开发的单点登录系统，前端通过layui实现
此工程下包含三个模块：登录dubbo服务接口api及相应的dubbo和springboot依赖、登录服务端模块（配置好直接运行即可）、测试模块（可运行测试登录）


## 使用方式
1.准备mysql数据库，创建用户表名为t_user,字段：id、usrname、passwd（必须一致）

2.配置，只需修改其中的application-prod.yml文件，如下：
server:
  port: 9999 #sso服务运行端口
sso:
  host: localhost #用户自定义应用主机
  port: 9999 #sso服务运行端口
  clientPort: 8888 #用用户自定义应用端口
  cookieKey: sessionId #用户自定义保持会话id的cookie键
  indexUrl: /index #用户自定义系统首页
  loginErrorUrl: /loginError #用户自定义登录错误跳转页
 # 数据源
spring:
  datasource:
    username: root
    password: 
    url: jdbc:mysql://ip:3306/qydx?useUnicode=true&characterEncoding=utf8
    driver-class-name: com.mysql.cj.jdbc.Driver
  redis:
    database: 0
    host: ip
    port: 6379
    password: 
    lettuce:
      pool:
        max-active: 200
        max-idle: 10
        max-wait: -1
        min-idle: 0
        timeout: 1000
  
  ## 如何调用登录接口
  sso-api中提供的SsoService接口有相关方法
  如：doLogin、doLogout、getUserInfo（判断是否登录)
  ### 如何跳转到登录页面
  通过调用SsoService的getLoginUrl即可获得跳转的绝对url（即通过前面配置文件中sso下的配置得到）
