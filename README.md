# Jersey-api-demo

- 查看是否有mysql镜像
`docker images`
如果没有执行： 
`docker pull mysql`
如果有直接执行
 

- 在项目根目录下执行
 ```
 ./gradlew build
 ./gradlew flywayMigrate
 ```

- 在项目根目录下执行
`./gradlew test`
测试所有测试
`./gradlew jettyrun` 
运行项目

- 在浏览器中访问 http://localhost:8080/jersey-user-demo/user/email/{email} 
*注：{email}是数据库已经写入的email，例如访问：http://localhost:8080/jersey-user-demo/user/email/565678150@qq.com  就可以返回这个用户的信息*
*如果访问一个数据库中不存在的email，例如访问http://localhost:8080/jersey-user-demo/user/email/123456789@qq.com  返回404，用户不存在*

- 在浏览器中访问 http://localhost:8080/jersey-user-demo/user/mobilePhone/{mobilePhone}
*注：{mobilePhone}是数据库已经写入的mobilePhone，可以通过mobilePhone找到用户*
