## Drones

### Introduction

The drone is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. 
### My Implementation of the Drone

#### Drone Management System
A Drone Management System that allows for the registration, loading, and battery monitoring of drones.


### Requirements

- Java 17
- Gradle 8.0 or later
- Docker (for running the database in a container)

### Clone the Repository

```bash
git https://github.com/jerrycaffe/drones.git 
```

#### Build the project

```bash
./gradlew build
```

### Run the build with in memory db (h2)

```bash
./gradlew bootRun
```

### Deploy to Docker using docker compose

```bash
docker-compose up --build
```

### Run test

```bash
./gradlew test
```


## Endpoints

### Register a Drone

**POST api/v1/drones**

#### Request Body

```json
{
  "weightLimit": 500,
  "model": "Lightweight",
  "serialNumber": "SN123456"
}
```
#### Response Body

```json
{
  "id": 1,
  "weightLimit": 500,
  "model": "Lightweight",
  "batteryCapacity": 100,
  "serialNumber": "SN123456",
  "state": "IDLE"
}
```

### Load Medication onto a Drone

**POST api/v1/drones/{droneId}**

#### Request Body

```json
{
  "code": "MED123",
  "name": "Painkiller",
  "weight": 50,
  "imageUrl": "https://cdn.pixabay.com/photo/2023/10/01/14/40/medicine-8287535_640.jpg"
}
```
#### Response Body

```json
{
  "id": 1,
  "code": "MED123",
  "name": "Painkiller",
  "weight": 50,
  "imageUrl": "https://cdn.pixabay.com/photo/2023/10/01/14/40/medicine-8287535_640.jpg",
  "droneId": 1
}
```

### Get All Medications Loaded on a Drone

**GET api/v1/drones/{droneId}**

#### Response Body

```json
{
  "id": 1,
  "weightLimit": 500,
  "model": "Lightweight",
  "batteryCapacity": 100,
  "serialNumber": "SN123456",
  "state": "LOADED",
  "medications": [
    {
      "id": 1,
      "code": "MED123",
      "name": "Painkiller",
      "weight": 50,
      "imageUrl": "https://cdn.pixabay.com/photo/2023/10/01/14/40/medicine-8287535_640.jpg"
    }
  ]
}
```
### Check Drone Battery Status


**GET api/v1/drones/battery/{droneId}**

#### Response Body

```json
{
  "batteryLevel": 85,
  "status": "IDLE"
}
```

### Get All Available Drones

**GET api/v1/drones/available**

#### Response Body

```json
[
  {
    "id": 1,
    "weightLimit": 500,
    "model": "Lightweight",
    "batteryCapacity": 85,
    "serialNumber": "SN123456",
    "state": "IDLE"
  },
  {
    "id": 2,
    "weightLimit": 300,
    "model": "Middleweight",
    "batteryCapacity": 90,
    "serialNumber": "SN654321",
    "state": "IDLE"
  }
]
```


