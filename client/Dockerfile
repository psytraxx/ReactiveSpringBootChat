FROM mhart/alpine-node:14 AS builder
WORKDIR /app
COPY . .
RUN yarn run build

FROM mhart/alpine-node:14
RUN yarn global add serve
WORKDIR /app
COPY --from=builder /app/build .
CMD ["serve", "-p", "3000", "-s", "."]
