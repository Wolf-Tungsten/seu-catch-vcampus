## 后端接口文档





### User

#### Path :  user/register

用户注册

| 字段名称      | 字段类型 | 备注                |
| ------------- | -------- | ------------------- |
| username      | String   | 用户名              |
| cardnum       | String   | 用户注册的一卡通号  |
| hash_password | String   | 用户注册密码的MD5值 |
| photo         | String   | 用户注册上传的图片  |
| identity      | int      | 用户身份            |
| privilege     | int      | 用户特权值          |

#### 返回Response 里的内容

| 属性名称 | 类型                   | 备注                         |
| -------- | ---------------------- | ---------------------------- |
| success  | Boolean                | true:注册成功,false:注册失败 |
| body     | HashMap<String,Object> | null                         |



#### Path:user/login

用户登录

| 字段名称      | 字段类型 | 备注              |
| ------------- | -------- | ----------------- |
| cardnum       | String   | 用户一卡通号      |
| hash_password | String   | 用户登陆密码MD5值 |

#### 返回Response里的内容

| 属性名称 | 类型                   | 备注                               |
| -------- | ---------------------- | ---------------------------------- |
| success  | Boolean                | true注册成功 ,false登录失败        |
| body     | HashMap<String,Object> | <"token",token> token为String 类型 |







