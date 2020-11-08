# Developer Notes

run with docker-compose

    docker-compose up --build

access the react frontend at

http://localhost:3000/

## TODO's

- Proper Input Validation on the react client side
- A LOT of error handling on the react side (EventSource onError - fetch errors etc, check HTTP status codes )
- Monitoring of the springboot app (actuator)
- two stage build for spring docker image
- CSS!!! - i seriosly did run out of time - i would have grabbed a few nice tailwind components
- implement some proper test cases for the backendA API
- run tests as part of the build stage
- add send and recipient props on message
- add SECURITY - this chat is wide open
- add some nginx proxy in front of the react app and the backend app
- add swagger for the backend api

## API Usage

get all chats in a specific channel

[GET]

    http://localhost:8080/chats/stream?channelId=1

add a new chat entry

[POST]

    http://localhost:8080/chats

Content-Type application/json

    {
        "message":"hello",
        "channelId":1,
    }

## References

https://medium.com/hacktive-devs/building-the-backend-of-chat-applications-with-spring-webflux-and-reactive-mongodb-26347a1ddce4
