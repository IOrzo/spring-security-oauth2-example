### Spring Security Oauth2 示例项目

#### 所需环境
nacos注册中心, 下载地址 https://nacos.io/zh-cn/docs/quick-start.html <br/>
redis <br/>
mysql <br/>


#### 模块介绍

application -> 应用模块(具体业务应用) <br/>
application - web -> 上层应用 <br/>
auth -> 认证模块 <br/>
auth-common -> 认证公共模块 <br/>
auth-resource -> 资源服务器模块 <br/>
auth-server -> 认证服务器模块 <br/>
common -> 公共模块 <br/>
provider -> 基础服务模块(为上层应用提供服务) <br/>

#### 启动项目

1. 将document文件夹中sql导入到数据库中
2. 修改application -> web,  auth-resource, auth-server, provider-base 中配置文件, 将nacos、redis、mysql
地址改为自己服务器地址
3. 依次启动base、auth-server、web服务

#### 数据说明
##### mysql

six_base_user -> 用户表, 用户密码均为admin <br/>
oauth_client_details -> oauth2客户端表, 客户端密码均为web <br/>

#### 接口测试

* 密码登录
http://127.0.0.1:8812/oauth/token?username=admin&password=admin&grant_type=password&scope=web&client_id=web&client_secret=web

* 授权码模式
浏览器中访问http://127.0.0.1:8812/oauth/authorize?response_type=code&redirect_uri=https://www.baidu.com&client_id=web <br/>
在登录页面中输入账户密码, 会跳转到百度页面, 地址栏上会返回code, 如: https://www.baidu.com/?code=1OOpOg <br/>
根据code请求token, http://127.0.0.1:8812/oauth/token?code=1OOpOg&redirect_uri=https://www.baidu.com&grant_type=authorization_code&scope=web&client_id=web&client_secret=web <br/>
注: 若使用Baisc认证, 请求参数中可不带client_id和client_secret, 这里是方便测试


#### 自定义授权模式(自定义登录逻辑)

1. 自定义登录逻辑接口, 模仿UserDetailsService接口
2. 自定义Token, 模仿UsernamePasswordAuthenticationToken, 继承AbstractAuthenticationToken
3. 自定义Provider, 模仿DaoAuthenticationProvider, 实现AuthenticationProvider
4. 自定义Granter, 模仿ResourceOwnerPasswordTokenGranter, 继承AbstractTokenGranter
5. 自定义整合TokenGranter, 模仿CompositeTokenGranter,实现TokenGranter
6. 在AuthorizationServerConfigurerAdapter配置中添加自定义的tokenGranter