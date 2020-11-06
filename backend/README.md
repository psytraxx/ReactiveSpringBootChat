#Developer Notes

    docker run -p 27017:27017 --rm  mongo

[GET]
http://localhost:8080/chats/stream?channelId=1

[POST]
http://localhost:8080/chats

as application/json

{
    "message":"hello",
    "channelId":1,
    "sender":"sender",
    "recipient":"recipient"

}

./gradlew bootRun