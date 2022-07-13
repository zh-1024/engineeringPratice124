# Youddit 图片兴趣社区服务端

服务端项目主要使用Java语言和SpringBoot框架。可以采取测试运行和使用发行版本两种方式来分别测试和部署服务端程序。由于生产环境的不同，建议手动配置和构建**适用于目标生产环境的发行版本**。

## 预备条件

项目服务端与客户端依赖以下程序和中间件。在进一步操作前请确保所有依赖项的正确安装。

1. JDK17
   
   - 用于测试运行和构建服务端程序
   
   - 生产环境建议使用JRE17即可
   
   - [Microsoft Build of OpenJDK](https://www.microsoft.com/openjdk)

2. Maven
   
   - 依赖管理和构建工具
   - [Maven – Welcome to Apache Maven](https://maven.apache.org/)

3. MySQL
   
   - 数据库
   - [MySQL :: MySQL Downloads](https://www.mysql.com/downloads/)

4. ErLang
   
   - RabbitMQ的依赖
   
   - **Windows下请以管理员身份进行安装，并先于RabbitMQ安装**
   
   - [Downloads - Erlang/OTP](https://www.erlang.org/downloads)

5. RabbitMQ
   
   - 处理私信等的消息中间件
   - **Windows下请以管理员身份进行安装**
   - [Messaging that just works — RabbitMQ

## 依赖配置

### MySQL

使用如下命令来创建数据库和数据表。其中mysql路径，用户名，密码，create.sql文件所在路径视具体情况而定。

```bash
path\to\your\mysql –uroot –p123456 <C:\create.sql
```

### RabbitMQ

1. 在RabbitMQ安装目录的的bin目录下执行以下命令打开STOMP插件
   
   ```bash
   rabbitmq-plugins enable rabbitmq_stomp
   ```

2. 创建虚拟主机youddit
   
   ```bash
   rabbitmqctl add_vhost youddit
   ```

### 项目配置

修改服务端项目下的/src/resources/application.yml文件，其中附**带注释部分**更改为生产环境具体配置。

```yml
spring:
  datasource
    # database username
    # 数据库用户名
    username: root
    # database password
    # 数据库密码
    password: 123456
    # change localhost to the address of the database
    # 将localhost更改为数据库服务地址
    url: jdbc:mysql://${MYSQL_HOST:localhost}:3306/youddit
    driver-class-name: com.mysql.cj.jdbc.Driver
  rabbitmq:
    # RabbitMQ address
    # RabbitMQ服务地址
    host: 127.0.0.1
    port: 5672
    virtual-host: youddit
    # RabbitMQ username
    # RabbitMQ用户名
    username: guest
    # RabbitMQ password
    # RabbitMQ密码
    password: guest
  servlet:
    multipart:
      max-file-size: 2MB
      max-request-size: 20MB



mybatis-plus:
  mapper-locations: classpath:mapper/*.xml
management:
  endpoints:
    web:
      exposure:
        include: refresh
```

### 测试运行

*测试运行不建议用于生产环境。*

在项目pom.xml目录的终端下运行以下命令测试运行

*运行前请确保已经正确安装JDK17, maven。*

```bash
mvn clean package
```

### 生产环境

#### 从源码构建

在pom.xml目录终端下使用以下命令手动构建Jar文件

*流程前请确保机器已正确安装JDK17, maven。*

```bash
mvn clean package
```

命令正确执行后可在target目录下找到所需Jar文件

#### 使用Jar文件

*使用发行版本前请确保目标机器已正确安装JRE17(JDK17)。*

1. 在Jar文件所在目录打开终端

2. 执行如下命令
   
   ```bash
   java -jar youddit-0.0.1-SNAPSHOT.jar
   ```
