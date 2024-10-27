## Homework task for demonstrative servlet

Проект реализует CRUD операции с использованием Servlet

- Servlet (jakarta)
- Filter
- MongoDB
- MapStruct
- Lombok

Реализован Servlet с CRUD операциями для сущности Rocket. Данные выводятся в формате json.

Добавлены фильтры для логирования и авторизации.

Поля:

- uuid (UUID)
- name (String)
- rocketType (enum)
- country (String)
- startTestPeriod (LocalDate)
- endTestPeriod (LocalDate)

Значения RocketType (enum):

- PAPER
- WOOD
- PLASTIC
- IRON
- POTTERY
- SUGAR

---

При отсутствии mongodb есть возможность подключения его через docker

Для запуска необходимо запустить команду 
```
docker-compose up -d
```

---

Для работы необходимо авторизоваться

- "/login"
- METHOD: POST

parameters:

- username: admin
- password: admin

После чего появляется доступ CRUD Rocket сущности

---

Endpoints:

Сохранение rocket

- "/rocket"
- METHOD: POST

Body (json):

```
{
"name": "Space",
"rocketType": "PAPER",
"country": "Russia",
"startTestPeriod": "2024-10-27",
"endTestPeriod": "2024-10-29"
}
```

---

Получение Rocket по uuid

- "/rocket"
- METHOD: GET
- параметр action: findByUuid
- параметр uuid: uuid для поиска (например: 10da60dc-cdbd-41c9-a1cf-c3b00196af59)

---

Получение списка всех Rocket

- "/rocket"
- METHOD: GET
- параметр action: findAll

---

Обновление rocket

- "/rocket"
- METHOD: PUT
- параметр uuid: uuid для поиска (например: 10da60dc-cdbd-41c9-a1cf-c3b00196af59)

Body (json):

```
{
"name": "Space",
"rocketType": "PAPER",
"country": "Russia",
"startTestPeriod": "2024-10-27",
"endTestPeriod": "2024-10-29"
}
```

---

Удаление Rocket по uuid

- "/rocket"
- METHOD: DELETE
- параметр uuid: uuid для удаления (например: 10da60dc-cdbd-41c9-a1cf-c3b00196af59)


