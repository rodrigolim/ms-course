
<h1 align="center">
  <a>Arquitetura de Microservi�os</a>
</h1>

<p align="center">
  <a href="#tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#ordem-de-execu��o">Ordem de execu��o</a>&nbsp;&nbsp;&nbsp;|&nbsp;
  <a href="#eureka">Eureka</a>&nbsp;&nbsp;&nbsp;|&nbsp;
  <a href="#keycloak">Keycloak</a>&nbsp;&nbsp;&nbsp;|&nbsp;
  <a href="#swagger">Swagger</a>&nbsp;&nbsp;&nbsp;|&nbsp;
</p>


# Tecnologias

Esse projeto foi desenvolvido com as seguintes tecnologias:
- [Spring Authorization Server](https://spring.io/projects/spring-authorization-server)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Lombok](https://projectlombok.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Keycloak](https://www.keycloak.org/)
- [Swagger](https://swagger.io/)


## Projeto

Este projeto tem como objetivo aprofundar o estudo da arquitetura de microservi�os, explorando a implementa��o de uma infraestrutura robusta e escal�vel. Compreender os princ�pios e pr�ticas dessa abordagem arquitet�nica � fundamental para o desenvolvimento de sistemas distribu�dos e resilientes, essenciais para empresas que buscam adaptabilidade e agilidade no cen�rio atual de desenvolvimento de software.

Ao longo deste projeto, ser�o utilizadas diversas ferramentas amplamente reconhecidas e empregadas em �mbito corporativo. Essas ferramentas s�o selecionadas com base em sua relev�ncia e aplicabilidade em ambientes de produ��o de grande escala, contribuindo para uma compreens�o abrangente e pr�tica da arquitetura de microservi�os.


  ```mermaid
flowchart LR
 subgraph gateway["api-gateway"]
    direction RL
        payroll["1 - payroll ms
                    2 - payroll ms
                    3 - payroll ms
                    ..."]
        worker["1 - worker ms
                   2 - worker ms
                   3 - worker ms
                    ..."]
        user["1 - user ms
              2 - user ms
              3 - user ms
              ..."]
  end
 subgraph postgressql["postgressql"]
        id1[("database-user")]
        id2[("database-worker")]
        id3[("database-keycloak")]
  end
    client(["client"]) --> gateway
    eureka("Eureka server") <--> gateway
    gateway --> keycloak("keycloak server") & config("config server")
    keycloak --> id3
    user --> id1
    worker --> id2
    config --> git{{"git repository"}}
    style client fill:#00C853,color:#000000
    style eureka fill:#FFD600,color:#000000
    style keycloak fill:#FFD600,color:#000000
    style git fill:#FFE0B2,color:#000000




   ```  
   _Modelado via [mermaidchart](https://www.mermaidchart.com/app/dashboard)_ 
   


## Ordem de execu��o
<ol>
  <li>hr-eureka-server</li>
  <li>hr-config-server</li>
  <li>hr-api-gateway</li>
  <li>Os demais microservi�os sem ordem definida.
        Sugest�o:
      <ul>
        <li>hr-user</li>
        <li>hr-worker</li>
        <li>hr-payroll</li>
    </ul>
  </li>
</ol>

## Eureka

O Eureka Server � uma ferramenta de c�digo aberto desenvolvida pela Netflix e utilizada para implementar a descoberta de servi�os em uma arquitetura de microservi�os. Ele atua como um registro centralizado onde os microservi�os podem se registrar e descobrir uns aos outros dinamicamente, facilitando a comunica��o entre eles.

Principais caracter�sticas do Eureka Server:

* __Descoberta de Servi�os:__ Oferece um mecanismo simples e eficaz para os microservi�os se registrarem e descobrirem uns aos outros em um ambiente distribu�do.
* __Balanceamento de Carga:__ Facilita a implementa��o de balanceamento de carga entre inst�ncias de servi�os registradas no Eureka Server, permitindo distribuir o tr�fego de forma equilibrada entre elas.
* __Monitoramento de Estado:__ Fornece recursos para monitorar o estado de sa�de dos servi�os registrados, permitindo a detec��o e remo��o autom�tica de inst�ncias com falha.
* __Alta Disponibilidade:__ Projetado para ser altamente dispon�vel e resiliente, com suporte para clusters e replica��o de dados para evitar pontos �nicos de falha.
* __Integra��o com Spring Cloud:__ Totalmente integrado com o ecossistema Spring Cloud, facilitando sua utiliza��o em aplicativos desenvolvidos com Spring Boot.
* __Auto-regenera��o:__ Capaz de auto-regenerar inst�ncias perdidas ou temporariamente inacess�veis, garantindo a integridade e disponibilidade cont�nua do registro de servi�os.

Em resumo, o Eureka Server desempenha um papel fundamental na arquitetura de microservi�os, fornecendo um mecanismo confi�vel e eficiente para a descoberta de servi�os e comunica��o entre eles, contribuindo para a constru��o de sistemas distribu�dos escal�veis e resilientes.

_Acessando o eureka server:_
```
http://localhost:8761
```

## Keycloak

O Keycloak Server � uma solu��o de c�digo aberto para gerenciamento de identidade e acesso, oferecendo recursos robustos de autentica��o, autoriza��o e gerenciamento de usu�rios. Desenvolvido pela Red Hat, o Keycloak simplifica a  implementa��o de autentica��o e autoriza��o em aplicativos web e servi�os RESTful.

Entre os principais recursos do Keycloak Server est�o:


* __Autentica��o centralizada:__ Permite a autentica��o �nica (SSO) em v�rios aplicativos e servi�os, reduzindo a necessidade de m�ltiplas credenciais de login.
* __Autoriza��o baseada em pap�is:__ Facilita a defini��o de pol�ticas de acesso com base em fun��es de usu�rio, permitindo um controle granular sobre os recursos protegidos.
* __Suporte a protocolos padr�o:__ Integra-se facilmente a aplicativos atrav�s de protocolos como OAuth 2.0, OpenID Connect, SAML e LDAP.
* __Customiza��o e extensibilidade:__ Oferece uma arquitetura flex�vel que permite a customiza��o e extens�o de funcionalidades de acordo com as necessidades espec�ficas do projeto.
* __Console de administra��o intuitivo:__ Possui uma interface de usu�rio amig�vel para gerenciamento de usu�rios, configura��es de seguran�a e pol�ticas de acesso.
* __Seguran�a avan�ada:__ Implementa pr�ticas de seguran�a robustas, incluindo criptografia de dados, prote��o contra ataques de seguran�a e monitoramento de atividades suspeitas.
* __Escalabilidade e alta disponibilidade:__ Projetado para ser escal�vel horizontalmente e oferecer alta disponibilidade para lidar com cargas de tr�fego vari�veis.

Em resumo, o Keycloak Server � uma solu��o poderosa e altamente configur�vel para gerenciamento de identidade e acesso, que oferece seguran�a e praticidade para desenvolvedores e administradores de sistemas ao implementar autentica��o e autoriza��o em aplicativos e servi�os.

_Acessando o keycloak server:_
```
http://localhost:8081
```


## Swagger 

O Swagger � uma poderosa ferramenta de c�digo aberto para documenta��o de APIs, projetada para simplificar e automatizar o processo de documenta��o de servi�os da web RESTful. Ele permite que desenvolvedores criem, visualizem e interajam com a documenta��o da API de forma eficiente, melhorando a compreens�o e ado��o das APIs por parte dos desenvolvedores consumidores.

Principais recursos do Swagger:

* __Interface Interativa:__ Oferece uma interface interativa e amig�vel que permite aos desenvolvedores explorar e testar endpoints da API diretamente no navegador.
* __Documenta��o Autom�tica:__ Gera automaticamente documenta��o precisa da API com base nas anota��es do c�digo-fonte, como JavaDoc ou anota��es espec�ficas para outras linguagens.
* __Padroniza��o:__ Promove a padroniza��o na documenta��o da API, garantindo que todas as informa��es essenciais, como par�metros de entrada, tipos de dados de resposta e c�digos de status HTTP, sejam claramente definidas e consistentes.
* __Valida��o de Esquema:__ Valida��o autom�tica dos esquemas de entrada e sa�da da API, garantindo consist�ncia e conformidade com as especifica��es da API.
* __Gera��o de Clientes:__ Facilita a gera��o autom�tica de clientes SDK (Software Development Kit) em v�rias linguagens de programa��o com base na especifica��o da API, agilizando o processo de desenvolvimento para os consumidores da API.
* __Suporte a Diversas Linguagens:__ Suporta uma ampla variedade de linguagens de programa��o e frameworks, permitindo que desenvolvedores de diferentes tecnologias se beneficiem da documenta��o gerada pelo Swagger.

Em resumo, o Swagger simplifica o processo de documenta��o de APIs, fornecendo uma solu��o abrangente e automatizada para criar e manter documenta��es precisas e interativas, facilitando a ado��o e integra��o de APIs por parte dos desenvolvedores.


_Acessando o swagger:_
```
http://localhost:8765/swagger-ui.html
```
