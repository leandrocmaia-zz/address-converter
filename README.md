# address-converter

## Requirements

+ Java 8

## How to run

Run: ```./gradlew clean build; java -jar build/libs/address-converter-0.0.1-SNAPSHOT.jar```

## API

There is a single point to convert the full address into a json format.

### GET /convert

#### Parameters

|Name   |Description|
|---    |---        |
|address|full address text|

Request example:
```
curl -X GET \
  'http://localhost:8080/convert?address=Calle%2039%20No%201540' 
```

Response:
```
{
  "streetName": "Calle 39",
  "number": "No 1540"
}
```