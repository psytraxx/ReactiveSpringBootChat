# Developer Notes

## About the solution

A few years ago I wrote a realtime chat scaling to infinite for a social TV startup which was based on Socket.io and used Layer 4 Load Balancing and Redis Pub/Sub to glue multiple Socket.io together. This worked very well in production and I would chose it again.

Since a Java backend was required for this task I decided to try something new:
I know Spring Boot and I heard about server side events (SSE) as a new way to push data via http to a web client. For persistence I chose MongoDB because it scales well and I needed a simple NO-SQL DB running in docker (I would tend to DynamoDB or similar in a cloud native environment). Looking into MongoDB I came across tailable cursors and a nice tutorial who to use them in Spring - https://www.baeldung.com/spring-data-mongodb-tailable-cursors - At this point the solution was clear and fired up my IDE. I struggled setting up a capped collection in mongodb which is needed for the tailable cursors - A stackexchange thread suggested to run a setup.js but I was not satisfied and found a way to do this in Java/ Spring at application startup and not triggered by docker-compose. I lost a fair bit of time at this point.
The solution works great and updates are almost instant. Any appended new message to the chat message collection is immediately sent to spring which is then doing the SSE magic and pushing it to the client. The client is already subscribed to the stream and appends the data to the list in the GUI. A normal POST endpoint is used to add a new chat row to the mongodb.

The frontend is coded in React which I love and use for a few years now. Having said this - I do React and Next.js but i don't do any CSS at all - I would be able to pick some tailwind components and add them to the Client to make it look nice but I ran out of time.

SSE seems to be supported through all current browsers https://caniuse.com/eventsource so one could also use this in production.

At the bottom of the this document I added a todo list of things I would have definitely realised if I had more time.

# Run the Demo

1.  clone this repo from github

2.  run with docker-compose in the root of the project

        cd sse-chat/
        docker-compose up --build

access the react frontend at

http://localhost:3000/

## TODO's

- Proper input validation / sanitation on the react client side
- A LOT of error handling on the react side (EventSource onError - fetch errors etc, check HTTP status codes )
- check if SSE are supported in the browser (fallback to polling and add a none-reactive endpoint to retrieve Message list as json array)
- Monitoring of the springboot app (actuator)
- two stage build for spring docker image
- implement some proper test cases for the backendA API
- run tests as part of the build stage
- add SECURITY - this chat is wide open
- add some nginx proxy in front of the react app and the backend app
- add swagger for the backend api
- CSS!!! - i seriosly did run out of time - i would have grabbed a few nice tailwind components - make the input field stay at the bottom - content scrollable

## API Usage

get all chats in a specific channel

[GET]

    http://localhost:8080/chats/stream?channelId=1

add a new chat entry

[POST]

    http://localhost:8080/chats

Content-Type application/json

    {
        "message":"hello world!",
        "channelId":1,
        "sender": "send name"
    }
