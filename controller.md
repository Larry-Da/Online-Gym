# Controller Open-API

## 1. The User Controller

### 1.1. Get All Customers

**Type**: GET

**Author**: Wenrui Zhao  2021/04/03

**Content Type**: json, charset=utf-8

**Description**: Get all customers and return a list of them

**Request-parameters**:

| Parameter |  Type  | Required | Descriptions  |
| :-------: | :----: | :------: | :-----------: |
|  request  | String |   true   | Function name |
|   cusId   | String |   true   |  Customer ID  |

**Request-body**:

```json
{
	"request":"getCustomerByIds",
	"payload":{
		
	}
}
```

**Response-fields**:

| Parameter |   Type   |       Description       |
| :-------: | :------: | :---------------------: |
|  status   |  String  |       Status code       |
|  cusIds   | String[] | a list of customers' ID |

**Response-example**:

```json
{
	"status":"success",
	"payload":{
		"cusIds":["C001","C002"]
	}
}
```



### 1.2. Get A Customer By ID

**Type**: GET

**Author**: Wenrui Zhao  2021/04/03

**Content Type**: json, charset=utf-8

**Description**: Get a customer through his ID

**Request-parameters**:

| Parameter |  Type  | Required | Descriptions  |
| :-------: | :----: | :------: | :-----------: |
|  request  | String |   true   | Function name |
|   cusId   | String |   true   |  Customer ID  |

**Request-body**:

```json
{
	"request":"getCustomerById",
	"payload":{
		"cusId":"C001"
	}
}
```

**Response-fields**:

|  Parameter  |  Type  |         Description          |
| :---------: | :----: | :--------------------------: |
|   status    | String |         Status code          |
|    cusId    | String |         Customer ID          |
|     age     | String |             Age              |
|    name     | String |             Name             |
|  password   | String |           Password           |
|    email    | String |        Email address         |
|   gender    | String |            Gender            |
| dateOfBirth | String |        Date of birth         |
| remainTime  | String | Available time of membership |
|   balance   | String |   The amount of money left   |
|   points    | String |      Membership points       |

**Response-example**:

```json
{
  "status": "success",
  "payload": {
    "cusId": "C001",
    "age": "45",
    "name": "goteng",
    "password": "1234566",
    "phoneNo": "18235226823",
    "email": "1770927746@qq.com",
    "gender": "M",
    "dateOfBirth": "2000-04-17",
    "membershipLevel": "L1",
    "remainTime": "345",
    "balance": "12345",
    "points": "300"
  }
}
```



### 1.3. Get A Customer By Name

**Type**: GET

**Author**: Wenrui Zhao  2021/04/03

**Content Type**: json, charset=utf-8

**Description**: Get a customer through his name

**Request-parameters**:

| Parameter |  Type  | Required | Descriptions  |
| :-------: | :----: | :------: | :-----------: |
|  request  | String |   true   | Function name |
|  cusName  | String |   true   | Customer name |

**Request-body**:

```json
{
	"request":"getCusIdByName",
	"payload":{
		"cusName":"goteng"
	}
}
```

**Response-fields**:

|  Parameter  |  Type  |         Description          |
| :---------: | :----: | :--------------------------: |
|   status    | String |         Status code          |
|    cusId    | String |         Customer ID          |
|     age     | String |             Age              |
|    name     | String |             Name             |
|  password   | String |           Password           |
|    email    | String |        Email address         |
|   gender    | String |            Gender            |
| dateOfBirth | String |        Date of birth         |
| remainTime  | String | Available time of membership |
|   balance   | String |   The amount of money left   |
|   points    | String |      Membership points       |

**Response-example**:

```json
{
  "status": "success",
  "payload": {
    "cusId": "C001",
    "age": "45",
    "name": "goteng",
    "password": "1234566",
    "phoneNo": "18235226823",
    "email": "1770927746@qq.com",
    "gender": "M",
    "dateOfBirth": "2000-04-17",
    "membershipLevel": "L1",
    "remainTime": "345",
    "balance": "12345",
    "points": "300"
  }
}
```



### 1.4. Get Customers By Gender

**Type**: GET

**Author**: Wenrui Zhao  2021/04/03

**Content Type**: json, charset=utf-8

**Description**: Get customers of the same gender

**Request-parameters**:

| Parameter |  Type  | Required | Descriptions  |
| :-------: | :----: | :------: | :-----------: |
|  request  | String |   true   | Function name |
|  gender   | String |   true   |    Gender     |

**Request-body**:

```json
{
	"request":"getCusIdsByGender",
	"payload":{
		"gender":"M"
	}
}
```

**Response-fields**:

|  Parameter  |  Type  |         Description          |
| :---------: | :----: | :--------------------------: |
|   status    | String |         Status code          |
|    cusId    | String |         Customer ID          |
|     age     | String |             Age              |
|    name     | String |             Name             |
|  password   | String |           Password           |
|    email    | String |        Email address         |
|   gender    | String |            Gender            |
| dateOfBirth | String |        Date of birth         |
| remainTime  | String | Available time of membership |
|   balance   | String |   The amount of money left   |
|   points    | String |      Membership points       |

**Response-example**:

```json
{
	"status":"success",
	"payload":{
		"cusIds":["C001","C002"]
	}
}

{
  "status": "success",
  "payload": {
    "cusId": "C001",
    "age": "45",
    "name": "goteng",
    "password": "1234566",
    "phoneNo": "18235226823",
    "email": "1770927746@qq.com",
    "gender": "M",
    "dateOfBirth": "2000-04-17",
    "membershipLevel": "L1",
    "remainTime": "345",
    "balance": "12345",
    "points": "300"
  }
}
```



### 1.5. Get The Expire Time Of Membership Of A Given Customer

**Type**: GET

**Author**: Wenrui Zhao  2021/04/03

**Content Type**: json, charset=utf-8

**Description**: Get the expiring time of a customer's membership

**Request-parameters**:

| Parameter |  Type  | Required | Descriptions  |
| :-------: | :----: | :------: | :-----------: |
|  request  | String |   true   | Function name |
|   cusId   | String |   true   |  Customer ID  |

**Request-body**:

```json
{
	"request":"getExpireTimeByCusId",
	"payload":{
		"cusId":"C001"
	}
}

```

**Response-fields**:

| Parameter  |  Type  |   Description   |
| :--------: | :----: | :-------------: |
|   status   | String |   Status code   |
| Expiretime | String | A specific date |

**Response-example**:

```json
{
	"status":"success",
	"payload":{
		"Expiretime":"2020-05-01"
	}
}



```



## 2. The Membership Controller

### 2.1. Get The Number Of Customers For A Given Membership Level

**Type**: GET

**Author**: Wenrui Zhao  2021/04/03

**Content Type**: json, charset=utf-8

**Description**: Get the number of customer for a given membership level

**Request-parameters**:

|    Parameter    |  Type  | Required |      Descriptions       |
| :-------------: | :----: | :------: | :---------------------: |
|     request     | String |   true   |      Function name      |
| membershipLevel | String |   true   | The level of membership |

**Request-body**:

```json
{
	"request":"getCusNumByLevel",
	"payload":{
		"membershipLevel":"L1"
	}
}

```

**Response-fields**:

| Parameter |  Type  |       Description       |
| :-------: | :----: | :---------------------: |
|  status   | String |       Status code       |
|    num    | String | The number of customers |

**Response-example**:

```json
{
	"status":"success",
	"payload":{
		"num":"100"
	}
}

```



## 3. The Money Controller

### 3.1. Get The Monthly Income

**Type**: GET

**Author**: Wenrui Zhao  2021/04/03

**Content Type**: json, charset=utf-8

**Description**: Get the amount of money earned by the gtm during a given month

**Request-parameters**:

| Parameter |  Type  | Required |   Descriptions   |
| :-------: | :----: | :------: | :--------------: |
|  request  | String |   true   |  Function name   |
|   month   | String |   true   | A specific month |

**Request-body**:

```json
{
	"request":"getMonthlyIncome",
	"payload":{
		"month":"June"
	}
}

```

**Response-fields**:

| Parameter |  Type  |     Description     |
| :-------: | :----: | :-----------------: |
|  status   | String |     Status code     |
|  income   | String | The amount of money |

**Response-example**:

```json
{
	"status":"success",
	"payload":{
		"income":"100000"
	}
}

```



## 4. The Video Controller

### 4.1. Get All Videos For A Given Customer

**Type**: GET

**Author**: Wenrui Zhao  2021/04/03

**Content Type**: json, charset=utf-8

**Description**: Get all videos which a given customer has watched before

**Request-parameters**:

| Parameter |  Type  | Required | Descriptions  |
| :-------: | :----: | :------: | :-----------: |
|  request  | String |   true   | Function name |
|   cusId   | String |   true   |  Customer ID  |

**Request-body**:

```json
{
	"request":"getVideoIdsByCusId",
	"payload":{
		"cusId":"C001"
	}
}

```

**Response-fields**:

| Parameter |   Type   |   Description    |
| :-------: | :------: | :--------------: |
|  status   |  String  |   Status code    |
| videoIds  | String[] | A list of videos |

**Response-example**:

```json
{
	"status":"success",
	"payload":{
		"videoIds":["V001","V002"]
	}
}

```

### 4.2. Add A Video

**Type**: POST

**Author**: Wenrui Zhao  2021/04/03

**Content Type**: json, charset=utf-8

**Description**: Add a video

**Request-parameters**:

| Parameter  |  Type  | Required |            Descriptions             |
| :--------: | :----: | :------: | :---------------------------------: |
|  request   | String |   true   |            Function name            |
|  videoId   | String |   true   |              Video ID               |
|    url     | String |   true   |        The path of the video        |
|    name    | String |   true   |                Name                 |
|   rating   | String |   true   |      Video quality assessment       |
|  category  | String |   true   |              Category               |
|   likes    | String |   true   | The number of customers who like it |
| viewCounts | String |   true   | The number of customer who watch it |
|   level    | String |   true   |         How difficult it is         |

**Request-body**:

```json
{
  "request": "addVideo",
  "payload": {
    "videoId": "V001",
    "url": "usr/local/bin",
    "name": "strength",
    "rating": "7.8",
    "category": "Yoga",
    "likes": "100",
    "viewCounts": "3000",
    "level": "easy"
  }
}

```

**Response-fields**:

| Parameter |  Type  | Description |
| :-------: | :----: | :---------: |
|  status   | String | Status code |

**Response-example**:

```json
{
	"status":"success",
}

```

### 4.3. Modify A Video

**Type**: POST

**Author**: Wenrui Zhao  2021/04/03

**Content Type**: json, charset=utf-8

**Description**: Modify a video

**Request-parameters**:

| Parameter  |  Type  | Required |            Descriptions             |
| :--------: | :----: | :------: | :---------------------------------: |
|  request   | String |   true   |            Function name            |
|  videoId   | String |   true   |              Video ID               |
|    url     | String |   true   |        The path of the video        |
|    name    | String |   true   |                Name                 |
|   rating   | String |   true   |      Video quality assessment       |
|  category  | String |   true   |              Category               |
|   likes    | String |   true   | The number of customers who like it |
| viewCounts | String |   true   | The number of customer who watch it |
|   level    | String |   true   |         How difficult it is         |

**Request-body**:

```json
{
  "request": "modifyVideo",
  "payload": {
    "videoId": "V001",
    "url": "usr/local/bin",
    "name": "strength",
    "rating": "7.8",
    "category": "Yoga",
    "likes": "100",
    "viewCounts": "3000",
    "level": "easy"
  }
}

```

**Response-fields**:

| Parameter |  Type  | Description |
| :-------: | :----: | :---------: |
|  status   | String | Status code |

**Response-example**:

```json
{
	"status":"success",
}

```

### 

