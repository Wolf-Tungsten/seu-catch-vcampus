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
| address       | String   | 用户地址            |
| idcardNum     | String   | 身份证号            |
| birthdate     | long     | 出生日期时间戳      |

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



## Book

#### Path: book/addBook

添加书籍

| 字段名称 | 字段类型 | 备注                  |
| -------- | -------- | --------------------- |
| name     | String   | 书名                  |
| isbn     | String   | 书的isbn base64之类的 |
| author   | String   | 作者                  |

返回Response的内容

| 属性名称 | 类型    | 备注                      |
| -------- | ------- | ------------------------- |
| sucess   | Boolean | 添加成功(true)失败(false) |

#### Path: book/deleteBook

删除书籍(可批量)

| 字段名称 | 字段类型 | 备注     |
| -------- | -------- | -------- |
| uuid     | String   | 书的uuid |

request.setPath("book/deleteBook")

ArrayList<String>BookuuidList =....

BookuuidList.add("uuid1")

BookuuidList.add("uuid2")

request.getParams().put("uuidList",BookuuidList);

返回Response里的内容

| 属性名称 | 类型    | 备注              |
| -------- | ------- | ----------------- |
| success  | Boolean | 成功true失败false |



#### Path:book/queryAllBook

查询全部书籍

setPath即可

返回Response里的内容

| 属性名称 | 类型                   | 备注         |
| -------- | ---------------------- | ------------ |
| success  | Boolean                | 请求是否成功 |
| body     | HashMap<String,Object> |              |

HashMap<String,Object>={"booksInfoMapList":LinkedTreeMap<String,Object>}

