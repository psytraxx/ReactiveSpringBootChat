FROM node:16-alpine AS builder
WORKDIR /app
COPY . .
RUN yarn install
RUN yarn run build

FROM node:16-alpine
RUN yarn global add serve
WORKDIR /app
COPY --from=builder /app/build .
CMD ["serve", "-p", "3000", "-s", "."]
