# mshow接口说明

**包含模块**

- 用户模块（user、staff、curator）
- 场馆模块*exhibition*
- 展览模块show
- 展品模块*exhibits* 

**接口信息**

- 协议：HTTPS
- 项目名：mshow
- 最新版本：v1

## 元数据及其他接口

### 测试连通性

GET	/

- 请求示例：

  ```
  GET /
  ```

- 响应示例：

  ```
  {
      "status": "success",
      "info": "Hello, world!"
  }
  ```



##  用户模块

有关用户模块的相关接口



### 内容搜索

根据场馆名、展览名、展品名进行搜索

GET	/search?query=*

**参数**

- **query**  :String    根据场馆、展览、展品进行搜索 
- **page** （可选） :Integer    第几页，默认值：1  
- **Integer**（可选）  每页显示多少条，默认值：5

**成功 200 OK**

- **status**：响应结果，正常情况下为success
- **total_page** : Integer  总页数，当next_page大于total_pages时表示不再有数据   
- **next_page** : Integer  下一页索引 
- **items** : Object[]  搜索结果列表
  - item_id : Integer 结果id

  - item_name : String 结果名称

  - item_type : String 结果类型（场馆、展览、展品）

  - item_image : String 结果图片

**错误 4xx**

- ValidationError  输入字段验证出错，缺少字段或字段格式有误（详见?message）




### 查询预约记录

查询并返回用户相关的预约记录

GET 	/order_list/{user_id}

**参数**

- **user_id** : Integer 用户id

**成功 200 OK**

- **status**：响应结果，正常情况下为success
- **order_id** : Integer 预约记录id
- **user_id** : Integer 用户id
- **order_count** : Integer 预约记录包含的展览数量
- **show_list**: Object[] 预约记录中包含的展览
  - show_id : Integer 展览id
  - show_name : String 展览名称
  - show_image : String 展览图片
  - show_time : Date 展览开展时间

**错误 4xx**

InvalidToken 未登录或授权过期，请登录

```
HTTP/1.1 401 Unauthorized
{
  "code": "InvalidToken",
  "message": "未登录或授权过期，请登录"
}
```



### 用户登录

小程序登录  本接口使用1.微信服务器进行登录到mshow

POST	/login

**参数**

- **code** : String  wx.login()获取到的code

**成功 200 OK**

- **status**：响应结果，正常情况下为success
- **user_id** : Integer 用户唯一标识
- **token** : String 后续请求中用户访问控制
- **ttl** : Integer token有效期，单位秒



### 用户信息

对于用户信息进行操作

#### 获取用户信息

获取用户相应的信息

GET	/userinfo/{user_id}

**参数**

- **user_id** : Integer 用户id

**成功 200 OK**

- **status**：响应结果，正常情况下为success
- **userInfo** : Object 用户信息
  - **user_id** : Integer 用户唯一的标识
  - **user_nick** ： String 用户昵称
  - **user_name** ： String 用户姓名
  - **role ** : integer 0为普通用户，1为馆员，2为馆长
  - **order_id** : Integer 用户预约展览记录id
  - **collection_count** : Integer 收藏的数量
  - **collections** : Object[] 收藏列表




#### 修改用户信息

PUT	/user/{user_id}

**参数**

- **user_name** (可选) : String   用户姓名
- **user_sex**(可选) ： String   用户性别
- **user_location**(可选) ：String   用户性别

**成功 200 OK**

- **status**：响应结果，正常情况下为success
- **object** ： Object 用户信息，字段说明见获取用户信息接口



### 点赞、收藏、分享

#### 用户点赞

用户对场馆、展览、展品进行点赞

POST	/like

**参数**

- **like_type** ：Integer 类型 1场馆、2展览、3展品 取值范围1,2,3
- **like_type_id** ：Integer 类型id，比如场馆时为exhibition_id

**成功 200 OK**

- **status**：响应结果，正常情况下为success
- **tost** : String tost提示,如 已点赞
- **like_count** : integer 对象被点赞数

**错误 4xx**

- **ValidationError** 	message：输入字段验证出错，缺少字段或字段格式有误（类型取值不正确）
- **InvalidToken**     message：未登录或授权过期，请登录
- **NotFound**   message：该场馆/展览/展品不存在



#### 取消点赞

DELETE	/like/{like_type_id}

参数

- **like_type** ：Integer 类型 1场馆、2展览、3展品 取值范围1,2,3
- **like_type_id** ：Integer 类型id，比如场馆时为exhibition_id

**成功 200 OK**

- **status**：响应结果，正常情况下为success

**错误 4xx**

- **ValidationError** 	message：输入字段验证出错，缺少字段或字段格式有误（无效数据参数）
- **InvalidToken**     message：未登录或授权过期，请登录



#### 新增收藏

用户对场馆、展览、展品进行收藏

POST	/collection

**参数**

- **collection_type** ：Integer 类型 1场馆、2展览、3展品 取值范围1,2,3
- **collection_type_id** ：Integer 类型id，比如场馆时为exhibition_id

**成功 200 OK**

- **status**：响应结果，正常情况下为success
- **tost** : String tost提示,如 已收藏
- **collection_count** : integer 对象被收藏数



#### 取消收藏

DELETE	/collection/{collection_type_id}

**参数**

- **collection_type** ：Integer 类型 1场馆、2展览、3展品 取值范围1,2,3
- **collection_type_id** ：Integer 类型id，比如场馆时为exhibition_id

**成功 200 OK**

- **status**：响应结果，正常情况下为success

**错误 4xx**

- **ValidationError** 	message：输入字段验证出错，缺少字段或字段格式有误（无效数据参数）
- **InvalidToken**     message：未登录或授权过期，请登录



#### 分享

？调用微信分享对于场馆、展览、展品进行分享

GET  /sharing/{sharing_type}/type/{sharing_type_id}

**参数**

- **sharing_type** ：Integer 类型 1场馆、2展览、3展品 取值范围1,2,3
- **sharing_type_id** ：Integer 类型id，比如场馆时为exhibition_id

**成功 200 OK**

- **status**：响应结果，正常情况下为success
- **share** ：Object 分享链接
  - **url** ： String 分享链接 
  - **image**： String  分享时使用的图片ur 
  - **title**：String  标题 
  - **content：String**  分享时的文字说明

**错误 4xx**

- **ValidationError** 	message：输入字段验证出错，缺少字段或字段格式有误
- **InvalidToken**     message：未登录或授权过期，请登录
- **NotFound**   message：该场馆/展览/展品不存在



### 意见反馈

POST	/feedback/{user_id}

**参数**

- **user_id** : Integer 用户的ID
- **content** : String  反馈内容
- **type** : Integer 针对那张类型 1场馆、2展览、3展品 取值范围1,2,3

**成功 200 OK**

- **status**：响应结果，正常情况下为success
- **tost** : String tost提示,如 意见反馈成功，会及时处理
- **user_name** : String 用户名
- **content** : String  反馈内容

**错误 4xx**

- **ValidationError** 输入字段验证出错，缺少字段或字段格式有误，message（无效数据参数、反馈内容不能为空）

### 升级员工

用户生成相应的二维码，由馆长验证后，升级为员工staff，role=1

？get	/staffqr

？如何生成



### 升级馆长(创建场馆)

用户或员工需要先填写场馆信息，创建场馆，升级为馆长curator,role=2

POST 	/curator/{user_id}

**参数**

- **user_id** : Integer 用户id（与微信接口中appid关联）
- **exhibition_name** : String 场馆名称
- **exhibition_phone** : String 场馆电话
- **exhibition_avatar** : String 场馆图片，加密后的String类型（特殊处理）
- **exhibition_position** : String 场馆地址
- **exhibition_intro** : String 场馆简介
- **open_time** : String 开馆时间
- **end_time** ： : String 闭馆时间

**成功 201 Created**

- **status**：响应结果，正常情况下为success
- **exhibition** : Object 场馆对象
- **role**（可选） : 返回权限（一般修改role即可 ）

**错误 4XX**

- **CreateError**   message：该场馆已存在
- **InvalidToken**        message：未登录或授权过期，请登录
- **ValidationError**    message：输入字段验证出错，缺少字段或字段格式有误

### 馆长操作

#### 获取馆长信息

在个人信息中查看自己的馆长信息（需要先登录且需要验证是否为馆长curator,role=2）

GET	/curator/{user_id}

**参数**

- **user_id** : Integer 用户id（与微信接口中appid关联）

**成功 200 OK**

- **status**：响应结果，正常情况下为success
- **exhibition** : Object 场馆对象

**错误 4XX**

- **InvalidToken**        message：未登录或授权过期，请登录



#### 更改馆长信息(更新场馆信息)

馆长修改场馆信息，需要验证是否为馆长curator,role=2

PUT	/curator/{user_id}

**参数**

- **user_id** : Integer 用户id（与微信接口中appid关联）
- **exhibition_id** : Integer 场馆id
- **exhibition_name**（可选） : String 场馆名称
- **exhibition_phone**（可选） : String 场馆电话
- **exhibition_avatar**（可选） : String 场馆图片，加密后的String类型（特殊处理）
- **exhibition_position** （可选）: String 场馆地址
- **exhibition_intro** （可选）: String 场馆简介
- **open_time**（可选） : String 开馆时间
- **end_time** （可选）： : String 闭馆时间

**成功 201 Created**

- **status**：响应结果，正常情况下为success
- **exhibition** : Object 场馆对象

**错误 4XX**

- **UpdateError**   message：该场馆不存在
- **InvalidToken**        message：未登录或授权过期，请登录
- **ValidationError**    message：输入字段验证出错，缺少字段或字段格式有误



#### 注销馆长(注销场馆)

馆长注销自己馆长身份，同时需要场馆信息，需要验证是否为馆长curator,role=2

DELETE    /curator/{user_id}

**参数**

- **user_id** : Integer 用户id（与微信接口中appid关联）

**成功 204**

- **status**：响应结果，正常情况下为success
- **exhibition** : Object 场馆对象
- **role**（可选） : 返回权限（一般修改role即可 ）

**错误 4XX**

- **NotFound**   message：该场馆不存在
- **InvalidToken**        message：未登录或授权过期，请登录



#### 统计场馆数据

馆长查看场馆的相关数据（需要登录及馆长权限）

GET	/exhibition/count

**参数**



### 员工管理

#### 添加新员工

馆长扫描用户升级员工所生成相应的二维码，升级为员工staff，role=1

post	/staff

?扫描后传递的参数？

**参数**

**成功 200 OK**



#### 删除员工

馆长进行删除员工

DELETE 	/staff/{id}

**参数**

- **staff_id**(可选) : Integer 删除对于员工id 

**成功 200 OK**

- **status**：响应结果，正常情况下为success
- **staff_list** :  Object[]  返回删除后的新员工组

**错误 4XX**

- **NotFound**   message：该场馆不存在



#### 批量删除员工

馆长进行删除员工

DELETE 	/staff_list

**参数**

- **staff_id_list** : Object[] 删除对于员工组的所有id列表

**成功 200 OK**

- **status**：响应结果，正常情况下为success
- **staff_list** :  Object[]  返回删除后的新员工组列表

**错误 4XX**

- **NotFound**   message：该场馆不存在



## 场馆模块

有关场馆模块的接口说明



### 查询场馆列表

获取场馆的列表（游客模式，不需要登录验证）

GET	/exhibition_list

**成功 200 OK**

- **status**：响应结果，正常情况下为success
- **exhibition_list** :  Object[]  返回当前的所有场馆列表
  - **exhibition_id** : Integer 场馆id
  - **exhibition_name** : String 场馆名称
  - **exhibition_add** : String 场馆地址
  - **exhibition_intro** : String 场馆简介
  - **exhibition_thumb** : String 场馆图片，加密后的String类型（特殊处理）
  - **collected_count** : Integer 被收藏数
  - **star_count** : Integer 点赞数
  - **share_count** : Integer 分享数
- **exhibition_count** : Integer 当前的场馆总数



### 查询场馆详情

获取某一个场馆的详情信息

GET	/exhibition/{exhibition_id}

**参数**

- **exhibition_id** : Integer 场馆id

**成功 200 OK**

- **exhibition_list** :  Object  返回当前的所有场馆列表
  - **exhibition_name** : String 场馆名称
  - **exhibition_add** : String 场馆地址
  - **exhibition_intro** : String 场馆简介
  - **exhibition_thumb** : String 场馆图片，加密后的String类型（特殊处理）
  - **collected_count** : Integer 被收藏数
  - **star_count** : Integer 点赞数
  - **share_count** : Integer 分享数

**错误 4XX**

- **NotFound**   message：该场馆不存在

### 场馆评论

#### 获取当前场馆所有评论

GET	/exhibition/{exhibition_id}/comment_list

**参数**

- **exhibition_id** : Integer 场馆id
- **page** (可选) : Integer 请求分页索引，默认值1
- **per_page ** (可选) : Integer 每页返回的数据条数，默认值10

**成功 200 OK**

- **next_page** :Integer 下一页索引 
- **total_pages**  :  Integer总页数，当next_page大于total_pages时表示不再有数据
- **comment_list** :Object[]  评论列表  
  - **comment_id**: String  评论id
  - **content** :String   评论内容
  - **create_at_format**  :String  格式化后的评论时间       
  - **user_id**  : Integer  评论用户的id
  - **user_name** : String  评论用户的用户名 
  - **sub_comments_count** : Integer 子评论数量
  - **sub_comment_list**(可选) : Object[] 子评论列表
    - **comment_id**: String  评论或回复id
    - **content** :String   评论或回复的日记，语音评论时为空
    - **create_at_format**  :String  格式化后的评论时         
    - **user_id**  : Integer  评论用户的id
    - **user_name** : String  评论用户的用户名
    - **to_user_id** : Integer,评论被回复的用户id
    - **to_user_name** : String  评论被回复的用户名

**错误 4XX**

- **NotFound**   message：该场馆不存在



#### 发布场馆评论

发布有关场馆的评论（需要登录）

POST	/exhibition/comment

**参数**

- **exhibition_id** : Integer 场馆id
- **user_id** : Integer 用户id（与微信接口中appid关联）
- **content** : String 评论内容

**成功 201 Created**

- **comment_id**: String  评论id
- **content** :String   评论内容
- **create_at_format**  :String  格式化后的评论时间       
- **user_id**  : Integer  评论用户的id
- **user_name** : String  评论用户的用户名 

**错误 4xx**

- **ValidationError** 	message：输入字段验证出错，缺少字段或字段格式有误（评论内容不能为空，只能评论一次）
- **InvalidToken**     message：未登录或授权过期，请登录
- **NotFound**   message：该场馆不存在



#### 删除场馆评论

删除场馆有关评论（需要登录）

DELETE	/exhibition/{exhibition_id}/comment/{comment_id}

**参数**

- **exhibition_id** : Integer 场馆id

- **user_id** : Integer 用户id（与微信接口中appid关联）

- **comment_id** : Integer  评论id

**错误 4xx**

- **InvalidToken**     message：未登录或授权过期，请登录
- **NotFound**   message：该场馆或评论不存在



## 展览模块

有关展览模块的接口信息



### 获取所有展览

获取当前所有展览的列表（游客模式，不需要登录验证）

GET	/shows

**成功 200 OK**

- **show_list** :  Object[]  返回当前的所有场馆列表
  - **show_id** : Integer 场馆id
  - **show_name** : String 场馆名称
  - **show_add** : String 场馆地址
  - **show_intro** : String 场馆简介
  - **show_thumb** : String 场馆图片，加密后的String类型（特殊处理）
  - **collected_count** : Integer 被收藏数
  - **star_count** : Integer 点赞数
  - **share_count** : Integer 分享数
- **show_count** : Integer 当前的场馆总数



### 获取展览内展品

### 展览信息

#### 创建展览

#### 编辑展览

#### 删除展览



### 预约展览

### 退订展览

### 查看进场信息

### 验证进场信息



## 展品模块

有关展品的接口信息

### 查看展品详情

### 编辑展品信息

