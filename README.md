# Admin Service

## Запуск локально

```sh
git clone https://git.astondevs.ru/aston/a-money/admin-service
cd admin-service

cp ./.env.example ./.env
cp ./docker/.env.example ./docker/.env

docker-compose \
    -f ./docker/docker-compose.yml \
    -f ./docker/docker-compose.local.yml \
    up -d
```

```sh
docker-compose \
    -f ./docker/docker-compose.yml \
    -f ./docker/docker-compose.local.yml \
    exec -it app-admin-service bash

composer install
php artisan key:generate
php artisan migrate
```

