# OUO课程管理系统

前后端分离的课程管理系统，其基本功能有

-   南京邮电大学新闻信息爬取
-   院系管理
-   专业管理
-   班级管理
-   教师管理
-   学生管理
-   课程管理
-   选课管理
-   用户管理
-   学生选课
-   课程查询
-   课表查询
-   考试查询
-   成绩查询
-   信息维护
-   授课查询
-   教师课表
-   成绩录入

### 技术栈

后端

-   框架：springboot
-   持久层：mybatis plus
-   session：springboot-session+redis
-   日志：aop+mongodb
-   权限控制：使用自定义注解和拦截器手写

数据库

-   mysql
-   redis：session保存，爬取的新闻信息保存、少量配置信息
-   mongodb：日志记录

前端

-   vue.js
-   elementUI
-   axios

