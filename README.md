## 心里咨询系统后端
### 项目介绍：
![Image text](https://github.com/swagere/psychological_counsel/blob/master/src/main/resources/templates/image2020-11-30_17-47-55.png)


### 使用：
#### 1. springboot + mybatis + mybatis plus

#### 2. RBAC+spring security+JWT

- 结合RBAC权限认证模型，实现：
    - 身份认证：用户登陆的验证
    - 访问授权：授权系统资源的访问权限，以数据库动态管理用户权限，实现资源访问的控制。
- 不使用传统开发模式中的session登陆认证，用SHA512算法对Base64编码后认证关键信息进行签名，并与之拼接，形成token。
- Token认证关键流程为：
    - 客户端需要向服务端申请JWT令牌，即登录功能。
    - 访问系统其他的接口时，在HTTP的header中携带JWT令牌。
    - 服务端解签验证JWT中的用户标识，即通过服务端存储的密钥重新算出hash值并进行比较，同时校验token是否过期。
- 问题解决：
    - 令牌失效：退出登陆时但令牌有效期未到时，需做令牌失效处理：为了保持token的无状态特性，此处仅采用前端删除token的形式
    - 令牌刷新：根据业务选择合适的时机刷新JWT令牌：系统检测在有效期内，且超过有效期时间的一半，用旧的合法的JWT换取新的JWT令牌


#### 3. restful风格

### 亮点：
#### 1. 问卷筛查与等级评定
- 根据答题情况统计得分：用户在提交申请表时会填写问卷，后台自动根据用户的答题情况来计算得分。每一题的分数存储在数据库中，当用户选择某一题时，会返回该题目的id，后台以权重自动计算该题得分，最后存储用户的总得分。
- 根据得分进行等级评定：根据得分权重来划分等级。将等级划分为1-5级，不同的得分会自动划分出不同的紧急程度。可以酌情为紧急度高的用户增加排班。
- 选择紧急问题自动提高等级：某些问题为高危紧急选项，只要选了紧急项，等级一律为最高。

#### 2. 排班机制
- 排班周表：
类似课程表，中心管理员维护每一张排班周表 以校区和角色为单位

- 实际排班表：
通过排班周表 选择日期生成实际排班表，以实际排班表对应每一天的实际值班信息
涉及老师请假、增加紧急排班等

#### 4. 预约管理
- 智能筛选符合条件的咨询师：使用匹配算法，为用户智能推荐咨询师。用户可以根据 自己所在的校区、空闲时间、咨询类型等来选择符合自己的咨询师。以达到较好的咨询效果。

- 一键预约：一次性预约8周的同一时间地点，方便快捷。省去每次选择时间的步骤。若有临时变动也可随时更改。

- 预约审核：系统定时于晚上八点通过第二、三天所有预约，审核通过不可更改。若无冲突则审核自动通过，若有冲突则由管理员处理。若在未审核的预约中，遇到了紧急的用户，管理员有权临时更改排班。若在已审核的预约中，遇到了紧急的用户，管理员便酌情临时增加排班。更好地解决了冲突问题。
![Image text](https://github.com/swagere/psychological_counsel/blob/master/src/main/resources/templates/20210703141320.png)

#### 5. 结案报告生成
制作xml模板，基于Java模板技术动态生成Word文档，结案报告保存于后台指定路径。
![Image text](https://github.com/swagere/psychological_counsel/blob/master/src/main/resources/templates/1.png)
