### Spring Security Oauth2 示例项目

#### 所需环境
nacos注册中心, 下载地址 https://nacos.io/zh-cn/docs/quick-start.html

redis

mysql


#### 模块介绍

application -> 应用模块(具体业务应用)

application - web -> 上层应用

auth -> 认证模块

auth-common -> 认证公共模块

auth-resource -> 资源服务器模块

auth-server -> 认证服务器模块

common -> 公共模块

provider -> 基础服务模块(为上层应用提供服务)

#### 启动项目

修改application -> web,  auth-resource, auth-server, provider-base 中配置文件, 

#### 自定义授权模式(自定义登录逻辑)