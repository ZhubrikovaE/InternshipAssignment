# API Документация торговой системы

## Базовый URL

http://localhost:8080/api


---

## 1. Инструменты (Instruments)

### 1.1 Создание инструмента
**POST** `/instruments`

**Тело запроса:**
```json
{
    "seccode": "SBER",
    "type": "STOCK",
    "lotSize": 10,
    "minStep": 0.01
}
```

**Ответ (201 Created)** 
```json
{
    "seccode": "SBER",
    "type": "STOCK",
    "lotSize": 10,
    "minStep": 0.01
}
```

### 1.2 Получение инструмента по коду
**GET** `/instruments/{seccode}`


**Ответ (200 OK)** 
```json
{
    "seccode": "SBER",
    "type": "STOCK",
    "lotSize": 10,
    "minStep": 0.01
}
```


## 2. Счета (Accounts)

### 2.1 Создание счета
**POST** `/accounts`

**Тело запроса:**
```json
{
    "userId": 1,
    "currency": "RUB",
    "balance": 100000.0
}
```

**Ответ (201 Created)** 
```json
{
    "id": 1,
    "userId": 1,
    "balance": 100000.0,
    "currency": "RUB"
}
```

### 2.2 Получение счета по ID
**GET** `/accounts/{id}`


**Ответ (200 OK)** 
```json
{
    "id": 1,
    "userId": 1,
    "balance": 100000.0,
    "currency": "RUB"
}
```

## 3. Заявки (Orders)

### 3.1 Создание заявки
**POST** `/orders`

**Тело запроса:**
```json
{
    "seccode": "SBER",
    "accountId": 1,
    "type": "BUY",
    "price": 250.0,
    "quantity": 100
}
```

**Ответ (201 Created)** 
```json
{
    "id": 1,
    "accountId": 1,
    "seccode": "SBER",
    "type": "BUY",
    "price": 250.0,
    "quantity": 100,
    "status": "PENDING",
    "createdAt": "2026-07-24T10:00:00.123"
}
```

### 3.2 Получение всех заявок
**GET** `/orders/all`


**Ответ (200 OK)** 
```json
[
    {
        "id": 1,
        "accountId": 1,
        "seccode": "SBER",
        "type": "BUY",
        "price": 250.0,
        "quantity": 100,
        "status": "PENDING",
        "createdAt": "2026-07-24T10:00:00.123"
    }
]
```


### 3.3 Получение заявки по ID
**GET** `/orders/{id}`


**Ответ (200 OK)** 
```json
{
    "id": 1,
    "accountId": 1,
    "seccode": "SBER",
    "type": "BUY",
    "price": 250.0,
    "quantity": 100,
    "status": "PENDING",
    "createdAt": "2026-07-24T10:00:00.123"
}
```

### 3.4 Изменение заявки
**PUT** `/orders/{id}`

**Тело запроса:**
```json
{
    "seccode": "SBER",
    "accountId": 1,
    "type": "BUY",
    "price": 260.0,
    "quantity": 50
}
```

**Ответ (200 OK)** 
```json
{
    "id": 1,
    "seccode": "SBER",
    "accountId": 1,
    "type": "BUY",
    "price": 260.0,
    "quantity": 50,
    "status": "PENDING",
    "createdAt": "2026-07-24T10:00:00.123"
}
```

### 3.5 Отмена заявки
**PUT** `/orders/{id}/cancel`


**Ответ (200 OK)** 
"Заявка успешно отменена"



## 4. Сделки (Trades)

### 4.1 Получение всех сделок
**GET** `/trades`


**Ответ (200 OK)** 
```json
[
    {
        "id": 1,
        "seccode": "SBER",
        "buyOrderId": 1,
        "sellOrderId": 2,
        "price": 250.0,
        "quantity": 50,
        "executedAt": "2026-07-24T10:05:00.123"
    }
]
```


## 5. Котировки (Quotes)

### 5.1 Получение всех котировок
**GET** `/quotes`


**Ответ (200 OK)** 
```json
[
    {
        "seccode": "SBER",
        "bid": 250.0,
        "ask": 252.0,
        "bidQuantity": 100,
        "askQuantity": 80
    },
    {
        "seccode": "GAZP",
        "bid": 180.0,
        "ask": 185.0,
        "bidQuantity": 200,
        "askQuantity": 100
    }
]
```

### 5.2 Получение котировки по инструменту
**GET** `/quotes/{seccode}`


**Ответ (200 OK)** 
```json
{
    "seccode": "SBER",
    "bid": 250.0,
    "ask": 252.0,
    "bidQuantity": 100,
    "askQuantity": 80
}
```
