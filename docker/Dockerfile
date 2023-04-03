FROM php:8.2-fpm

ARG gid=1000
ARG uid=1000

RUN \
    apt-get update && \
    apt-get install -y zip unzip curl git libonig-dev && \
    docker-php-ext-install pdo mbstring pdo_mysql sockets

COPY --from=composer /usr/bin/composer /usr/bin/composer

WORKDIR /var/www
COPY . /var/www

RUN \
    groupadd -g $gid local && \
    useradd -m -g $gid -u $uid local && \
    chown -R $uid:$gid /var/www

USER $uid
CMD ["php-fpm"]
