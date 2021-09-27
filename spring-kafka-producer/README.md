# KAFKA Producer

## Asynchronous Simple Event Publisher
This will publish event to user-event Topic

curl -X POST \
  http://localhost:8081/api/v1/demo1 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: f9eb6205-3c22-5427-3be1-8f342dda0e45' \
  -d '{
	"firstName":"John",
	"lastName":"Doe",
	"email":"John.Doe@gmail.com",
	"dob":"2021-09-09"
}'


## Event Publisher with key
This will publish event to user-event topic with key, this will publish event to same parition

curl -X POST \
  http://localhost:8081/api/v1/demo2 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 0f3ec9b6-8a14-b286-4e7f-023214d2f8d9' \
  -d '{
	"id":"0987654321",
	"firstName":"John",
	"lastName":"Doe",
	"email":"John.Doe@gmail.com",
	"dob":"2021-09-09"
}'

## Event Publisher with callback method

Shows how you can hook callback once event is published

curl -X POST \
  http://localhost:8081/api/v1/demo3 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 77bcb3b6-cdc6-489d-32cc-abb29cc70380' \
  -d '{
	"id":"0987654321",
	"firstName":"John",
	"lastName":"Doe",
	"email":"John.Doe@gmail.com",
	"dob":"2021-09-09"
}'

## Publish event with headers

Shows how you can add header when event is published.

curl -X POST \
  http://localhost:8081/api/v1/demo4 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: ce3ffbf2-a5df-1a3b-fd0f-456a3a453b1c' \
  -d '{
	"id":"0987654321",
	"firstName":"John",
	"lastName":"Doe",
	"email":"John.Doe@gmail.com",
	"dob":"2021-09-09"
}'

## Synchronous Event Publisher

Shows how you can publish event in synchronous way

curl -X POST \
  http://localhost:8081/api/v1/demo5 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 1128f9ba-fb28-2c2d-38a4-d72599d3ad90' \
  -d '{
	"id":"0987654321",
	"firstName":"John",
	"lastName":"Doe",
	"email":"John.Doe@gmail.com",
	"dob":"2021-09-09"
}'