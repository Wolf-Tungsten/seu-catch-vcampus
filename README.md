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

#### Path: book/addBook 添加书籍

| 字段名称  | 字段类型 | 备注                  |
| --------- | -------- | --------------------- |
| name      | String   | 书名                  |
| isbn      | String   | 书的isbn base64之类的 |
| author    | String   | 作者                  |
| publisher | String   | 出版社                |
| amount    | int      | 数量                  |
| location  | String   | 馆藏地点              |

返回Response的内容

| 属性名称 | 类型    | 备注                      |
| -------- | ------- | ------------------------- |
| success  | Boolean | 添加成功(true)失败(false) |

#### Path: book/deleteBook 删除书籍(可批量)

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

#### path:book/updateBook 改书本信息 

| 字段名称  | 字段类型               | 备注             |
| --------- | ---------------------- | ---------------- |
| booksList | HashMap<String,Object> | 一本书待更新信息 |



key:booksList  ,  value :HashMap<String,Object>  booksinfo

booksinfo里的内容

| 字段名称       | 字段类型 | 备注 |
| -------------- | -------- | ---- |
| uuid           | String   | 书   |
| entity里的属性 |          |      |

可更新书内任意数量的字段对应的信息(除了uuid).

#### Path:book/queryAllBook 查询全部书籍

setPath即可

返回Response里的内容

| 属性名称 | 类型                   | 备注         |
| -------- | ---------------------- | ------------ |
| success  | Boolean                | 请求是否成功 |
| body     | HashMap<String,Object> |              |

HashMap<String,Object>={"booksInfoMapList":LinkedTreeMap<String,Object>}

LinkedTreeMap 里的字段有

| Key(String) | valueType | 备注                     |
| ----------- | --------- | ------------------------ |
| uuid        | String    | 书的uuid                 |
| name        | String    | 书名                     |
| isbn        | String    | isbn码                   |
| author      | String    | 作者                     |
| isReturn    | boolean   | true 已归还 false 未归还 |
| publisher   | String    | 出版社                   |

#### path:book/queryByName    book/queryByAuthor

通过名字查询，通过作者查询相关书籍

| 字段名称 | 字段类型 | 备注 |
| -------- | -------- | ---- |
| name     | String   | 书名 |
| token    | String   |      |

queryByAuthor  name -> author(String)

返回LinkedTreeMap<String,Object>,其中有   <"sucess": true/false> ,
		                 <"booksInfoMapList": ArrayList<LinkedTreeMap<String,Object>>

LinkedTreeMap 里的字段有

| Key(String) | valueType | 备注                 |
| ----------- | --------- | -------------------- |
| uuid        | String    | 书的uuid             |
| name        | String    | 书名                 |
| isbn        | String    | isbn码               |
| author      | String    | 作者                 |
| isReturn    | boolean   | true 还了 false 未还 |
| publisher   | String    | 出版社               |



#### Path:book/borrowBook

借书  一本

| 字段名称 | 字段类型 | 备注     |
| -------- | -------- | -------- |
| uuid     | String   | 书的uuid |
| token    | String   |          |

response    {"success":boolean,"body":{}}

#### Path:book/borrowRecord

用户借阅信息

| 字段名称 | 字段类型 | 备注 |
| -------- | -------- | ---- |
| token    | String   |      |

response    {"success":boolean , "body": { "recordMaplist":ArrayList<HashMap<String,Object>>}

备注:"body"对应的键值类型为LinkedTreeMap

inkedTreeMap 里的字段有

| Key(String) | valueType | 备注               |
| ----------- | --------- | ------------------ |
| uuid        | String    | 借阅记录的uuid     |
| name        | String    | 书名               |
| author      | String    | 作者名             |
| publisher   | String    | 出版社             |
| borrowdate  | int       | 借书日期时间戳     |
| returndate  | int       | 应当归还日期时间戳 |
| isreturn    | int       | 0没还 ,1 还了      |

#### Path:book/renewBook

续借

| 字段名称 | 字段类型 | 备注           |
| -------- | -------- | -------------- |
| uuid     | String   | 借阅记录的uuid |
| token    | String   |                |

response  {"success":boolean,"body":{}}

#### Path:book/returnBook

| 字段名称 | 字段类型 | 备注           |
| -------- | -------- | -------------- |
| uuid     | String   | 借阅记录的uuid |
| token    | String   |                |

response  {"success":boolean,"body":{}}





# 教务后端

### path : EduAdmin/addCourse

| 字段名称  | 字段类型 | 备注                                           |
| --------- | -------- | ---------------------------------------------- |
| name      | String   | 课程名称                                       |
| capacity  | int      | 课程容量                                       |
| lecturer  | String   | 老师名称                                       |
| week      | String   | 上课周次(星期几)                               |
| classtime | String   | 上课时间  eg:(9:00-12:00)                      |
| location  | String   | 上课地点  也为考试地点                         |
| startTime | String   | 固定字符串 没法 前端  " 2018-01-10/9:40-10:50" |
| credits   | int      | 课程学分                                       |

返回值 response 里的内容  success 成功或失败  body 里没东西 

## path:EduAdmin/queryAllCourse

| 字段名称 | 字段类型 | 备注 |
| -------- | -------- | ---- |
|          |          |      |
|          |          |      |
|          |          |      |



### 考试

###  path : EduAdmin/studentlist

根据老师名和课程名搜索对应学生名单

| 字段     | 字段类型 | 备注     |
| -------- | -------- | -------- |
| lecturer | String   | 老师名字 |
| name     | String   | 课程名   |
| token    |          |          |
|          |          |          |

返回 body 

HashMap里的key

| Key            | value                                    | 备注      |
| -------------- | ---------------------------------------- | --------- |
| studentMaplist | ArrayList<LinkedTreeMap<String, Object>> | 信息map表 |
| name           | String                                   | 课程      |
| lecturer       | String                                   | 教师名    |



"studentMaplist", ArrayList<LinkedTreeMap<String, Object>>  表里是一个个学生课程信息map

LinkedTreeMap<String, Object>里的字段

| 字段     | 字段类型 | 备注               |
| -------- | -------- | ------------------ |
| username | String   | 学生姓名           |
| cardnum  | String   | 卡号               |
| uuid     | String   | UserXCourse 的uuid |



## path :EduAdmin/mark

打分



| 字段  | 类型   | 备注        |
| ----- | ------ | ----------- |
| token | String |             |
| uuid  | String | UserXCourse |
| score | int    | 分数        |



response 里有请求成功或失败，失败时body 里 有result字段 



## path:EduAdmin/schedule

课表查询

| 字段  | 类型 | 备注 |
| ----- | ---- | ---- |
| token |      |      |





response 里 body 内容

| key           | value                                   | 备注           |
| ------------- | --------------------------------------- | -------------- |
| recordMaplist | ArrayList<LinkedTreeMap<String,Object>> | 课表信息map 表 |

ArrayList<LinkedTreeMap<String,Object>>  里LinkedTreeMap的内容

| key       | value  | 备注                     |
| --------- | ------ | ------------------------ |
| name      | String | 课程                     |
| lecturer  | String | 教师名字                 |
| classtime | String | 上课时间(上午9:00-10:00) |
| week      | String | 上课周次(周一周二)       |

