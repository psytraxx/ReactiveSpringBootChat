FROM node:20-alpine AS builder
WORKDIR /app
COPY . .
RUN npm i
RUN npm run build

FROM node:20-alpine
RUN npm i -g serve
WORKDIR /app
COPY --from=builder /app/dist .
CMD ["serve", "-p", "3000", "-s", "."]
