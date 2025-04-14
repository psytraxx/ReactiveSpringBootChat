FROM public.ecr.aws/docker/library/node:22-alpine3.21 AS builder
WORKDIR /app
COPY . .
RUN npm i
RUN npm run build

FROM public.ecr.aws/docker/library/node:22-alpine3.21
RUN npm i -g serve
WORKDIR /app
COPY --from=builder /app/dist .
CMD ["serve", "-p", "3000", "-s", "."]