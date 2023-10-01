# test



SpringBoot 3.1.4 

MabatisPlus 3.5.3.1



启动项目，用postman进行测试



查询热门企业

```java
GET http://localhost:9999/companies
```

新增企业

```java
POST http://localhost:9999/companies
Content-Type: application/json

{
    "orgUniCode": "1",
    "orgChiName": "1",
    "induSmaPar": "1",
    "orgDele": "1",
    "regCap": "1",
    "orgEstDate": "2023-10-1 00:00:00"
  }
```

查询指定企业

```java
GET http://localhost:9999/companies/200000157
```

更新指定企业

```java
PUT http://localhost:9999/companies/200000157
Content-Type: application/json

{
  "orgChiName": "漳州片仔癀药业股份有限公司1",
  "induSmaPar": "医药制造业",
  "orgDele": "林纬奇",
  "regCap": "60331.721",
  "orgEstDate": "1999-12-28 00:00:00"
}
```

删除指定企业

```java
DELETE http://localhost:9999/companies/200000157
```

分页查询基金列表 

```java
GET http://localhost:9999/fund?pageNum=1&pageSize=5&sortField=UNIT_NET_VAL&sortDirection=desc
```

