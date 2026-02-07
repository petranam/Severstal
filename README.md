
# VetClinic

Приложение "Ветеринарная клиника" на Java с REST API.  
Позволяет управлять пациентами, записями к врачу и медицинскими карточками.
Почему выбрала этот проект? Потому что у меня есть замечательная собака по кличке Оливия :)
![Оливия](Olivia.jpeg)
---
## Запуск

1. Склонировать репозиторий:

```bash
git clone <repo-url>
cd VetClinic
````

2. Собрать и запустить через Maven:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.vetclinic.VetClinicServer"
```

Сервер стартует на `http://localhost:8080`.

---

## Структура проекта

```
src/
├─ main/java/com/vetclinic/
│  ├─ VetClinicServer.java         
│  ├─ handlers/ClinicHandler.java
│  ├─ models/                     
│  │  ├─ Patient.java
│  │  ├─ Appointment.java
│  │  └─ MedicalRecord.java
│  ├─ repository/                  
│  │  ├─ PatientRepository.java
│  │  ├─ AppointmentRepository.java
│  │  └─ MedicalRecordRepository.java
│  └─ utils/                      
│     ├─ JsonParser.java
│     └─ JsonSerializer.java
```

---

##  API

### Пациенты

* `GET /patients` – список всех пациентов
* `POST /patients` – добавить пациента

Пример POST тела:

```json
{
  "name": "Оливия",
  "species": "Собака",
  "breed": "Мальтипу",
  "ownerName": "Иван Иванов"
}
```

### Записи к врачу

* `GET /appointments` – список всех записей
* `POST /appointments` – создать запись

Пример POST тела:

```json
{
  "patientId": "<id_пациента>",
  "doctorName": "Мария Миронова"
}
```

### Медицинские карточки

* `GET /records` – список всех карточек
* `POST /records` – создать карточку

Пример POST тела:

```json
{
  "patientId": "<id_пациента>",
  "diagnosis": "Парагрипп"
}
```

---

## Схема данных (ER)

```
Table patients {
  id varchar [pk]
  name varchar
  species varchar
  breed varchar
  owner_name varchar
}

Table appointments {
  id varchar [pk]
  patient_id varchar [ref: > patients.id]
  doctor_name varchar
}

Table medical_records {
  id varchar [pk]
  patient_id varchar [ref: > patients.id]
  diagnosis varchar
}
```

ER-диаграмма: [`ER-diagram.png`](ER-diagram.png)

---

## OpenAPI

Файл с описанием API: [`openapi.yaml`](openapi.yaml)
Может использоваться в Postman или Swagger UI.

---

## Тестирование через Postman

* Все POST/GET запросы работают на `http://localhost:8080`
* Рекомендуется сначала создать пациента, затем использовать его `id` для записей и медицинских карточек.

