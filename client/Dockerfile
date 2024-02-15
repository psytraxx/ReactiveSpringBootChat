FROM node:20-alpine AS builder
WORKDIR /app
COPY . .
RUN yarn install
RUN yarn run build

FROM node:20-alpine
RUN npm i -g serve
WORKDIR /app
COPY --from=builder /app/build .
CMD ["serve", "-p", "3000", "-s", "."]
