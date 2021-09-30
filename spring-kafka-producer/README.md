# KAFKA Producer

## Asynchronous Simple Event Publisher
This will publish event to user-event Topic

curl -X POST \
  http://localhost:8081/api/v1/publish/simple \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: f9eb6205-3c22-5427-3be1-8f342dda0e45' \
  -d '{
	"firstName":"John",
	"lastName":"Doe",
	"email":"John.Doe@gmail.com",
	"dob":"2021-09-09"
}'

<img width="1670" alt="1" src="https://user-images.githubusercontent.com/23295769/135392505-8bfcb757-acfc-4708-91ea-dc862bb6ab25.png">



## Event Publisher with key
This will publish event to user-event topic with key, this will publish event to same parition

curl -X POST \
  http://localhost:8081/api/v1/publish/key \
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

<img width="1677" alt="2" src="https://user-images.githubusercontent.com/23295769/135392504-a585fbcf-0fe2-402d-8979-b991bd3c1393.png">

## Event Publisher with callback method

Shows how you can hook callback once event is published

curl -X POST \
  http://localhost:8081/api/v1/publish/callback \
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

<img width="1511" alt="3" src="https://user-images.githubusercontent.com/23295769/135392501-70697b45-a455-4bcc-88e3-d1996feab6c4.png">

## Publish event with headers

Shows how you can add header when event is published.

curl -X POST \
  http://localhost:8081/api/v1/publish/header \
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

<img width="1680" alt="4" src="https://user-images.githubusercontent.com/23295769/135392496-52825d30-1e4f-4dfa-9b48-2e75d3c3f405.png">

## Synchronous Event Publisher

Shows how you can publish event in synchronous way

curl -X POST \
  http://localhost:8081/api/v1/publish/sync \
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

<img width="1495" alt="5" src="https://user-images.githubusercontent.com/23295769/135392487-06c9b52a-c0a4-402e-9d48-4b04c23187a9.png">