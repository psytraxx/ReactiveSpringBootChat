# Developer Notes

run with docker-compose

    docker-compose up --build

## API Usage

[GET]

    http://localhost:8080/chats/stream?channelId=1

[POST]

http://localhost:8080/chats

Content-Type application/json

    {
        "message":"hello",
        "channelId":1,
        "sender":"sender",
        "recipient":"recipient"

    }

## References

https://medium.com/hacktive-devs/building-the-backend-of-chat-applications-with-spring-webflux-and-reactive-mongodb-26347a1ddce4

## TODO's

- Proper Input Validation on the react client side
- Generally a lot of error handling on the react side (EventSource onError - fetch errors etc, check HTTP status codes )
- Monitoring of the springboot app (actuator)
