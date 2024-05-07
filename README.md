<h1 align="center">
  <a>Arquitetura de Microservi�os</a>
</h1>

<p align="center">
  <a href="#-tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-ordem-de-execucao">Ordem de execu��o</a>&nbsp;&nbsp;&nbsp;|&nbsp;
  <a href="#-keycloak-server">Keycloak Server</a>&nbsp;&nbsp;&nbsp;|&nbsp;
</p>


# Tecnologias

Esse projeto foi desenvolvido com as seguintes tecnologias:

- [Spring Authorization Server](https://spring.io/projects/spring-authorization-server)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Lombok](https://projectlombok.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Keycloak](https://www.keycloak.org/)
- [Actuator](https://www.keycloak.org/)


## Projeto

Este projeto tem como objetivo aprofundar o estudo da arquitetura de microservi�os, explorando a implementa��o de uma infraestrutura robusta e escal�vel. Compreender os princ�pios e pr�ticas dessa abordagem arquitet�nica � fundamental para o desenvolvimento de sistemas distribu�dos e resilientes, essenciais para empresas que buscam adaptabilidade e agilidade no cen�rio atual de desenvolvimento de software.

Ao longo deste projeto, ser�o utilizadas diversas ferramentas amplamente reconhecidas e empregadas em �mbito corporativo. Essas ferramentas s�o selecionadas com base em sua relev�ncia e aplicabilidade em ambientes de produ��o de grande escala, contribuindo para uma compreens�o abrangente e pr�tica da arquitetura de microservi�os.

![Projeto](docs/ms-course.png)

## Ordem de execu��o
<ol>
  <li>hr-eureka-server</li>
  <li>hr-config-server</li>
  <li>hr-api-gateway</li>
  <li>Os demais microservi�os sem ordem definida</li>
</ol>


## Keycloak Server
```
http://localhost:8081
```


## Swagger 
```
http://localhost:8765/swagger-ui.html
```
