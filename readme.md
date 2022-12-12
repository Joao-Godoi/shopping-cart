# Shoppig Cart

![JAVA](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![SPRING](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-CC0200.svg?style=for-the-badge&logo=Flyway&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit5-25A162.svg?style=for-the-badge&logo=JUnit5&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D.svg?style=for-the-badge&logo=Swagger&logoColor=black)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C.svg?style=for-the-badge&logo=Hibernate&logoColor=white)


# Sobre o projeto

O projeto consiste em pequeno sistema de carrinho de compras, onde existe um cliente, um carrinho de compras e diversos produtos.

<br>

# Tecnologias utilizadas

## Back end

- Java 17
- Spring Boot 3
- JPA / Hibernate
- Maven
- Postgres
- JUnit

<br>

# Como executar o projeto

## Back end

Pré-requisitos:

- Java 17
    * [Guia de instalação Java](https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)

- Maven
    * [Guia de instalação Maven](https://www.digitalocean.com/community/tutorials/install-maven-linux-ubuntu)

- Docker
    * [Guia de instalação Docker](https://www.digitalocean.com/community/tutorials/how-to-install-docker-compose-on-ubuntu-20-04-quickstart-pt)
<br>

```bash
# entrar na pasta do projeto
cd shoppingcart

# executar o banco de dados
docker-compose up -d

# executar o projeto
./mvnw spring-boot:run
```

Após executar o projeto já é possível realizar requests, como por exemplo em http://localhost:8080/product, porém há também
uma interface para facilitar toda a documentação e a realização dos requests, o caminho para essa interface é
http://localhost:8080/swagger-ui.html, neste caminho há todas as URLs disponíveis para consumo. <br>
O projeto já conta e inicializa com alguns dados para você poder ver como é o retorno de cada endpoint e ter uma ideia do projeto.
