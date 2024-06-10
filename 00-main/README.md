# Microservices com RabbitMQ

## Configurando o Ambiente

### Criando um container do RabbitMQ

```text
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.10-management
```

### Criando um container MySQL

```text
docker run -d --name mysqldb -p 3306:3306 -e "MYSQL_ROOT_PASSWORD=root" mysql
```