## 后端接口文档

### User

#### user/register

用户注册

| 字段名称      | 字段类型 | 备注                |
| ------------- | -------- | ------------------- |
| username      | String   | 用户名              |
| cardnum       | String   | 用户注册的一卡通号  |
| hash_password | String   | 用户注册密码的MD5值 |
| photo         | String   | 用户注册上传的图片  |
| identity      | int      | 用户身份            |
| privilege     | int      | 用户特权值          |

### user/login 

用户登录

| 字段名称      | 字段类型 | 备注              |
| ------------- | -------- | ----------------- |
| cardnum       | String   | 用户一卡通号      |
| hash_password | String   | 用户登陆密码MD5值 |







