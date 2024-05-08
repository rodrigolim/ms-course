
<h1 align="center">
  <a>Arquitetura de Microserviços</a>
</h1>

<p align="center">
  <a href="#tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#ordem-de-execução">Ordem de execução</a>&nbsp;&nbsp;&nbsp;|&nbsp;
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

Este projeto tem como objetivo aprofundar o estudo da arquitetura de microserviços, explorando a implementação de uma infraestrutura robusta e escalável. Compreender os princípios e práticas dessa abordagem arquitetônica é fundamental para o desenvolvimento de sistemas distribuídos e resilientes, essenciais para empresas que buscam adaptabilidade e agilidade no cenário atual de desenvolvimento de software.

Ao longo deste projeto, serão utilizadas diversas ferramentas amplamente reconhecidas e empregadas em âmbito corporativo. Essas ferramentas são selecionadas com base em sua relevância e aplicabilidade em ambientes de produção de grande escala, contribuindo para uma compreensão abrangente e prática da arquitetura de microserviços.


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
   


## Ordem de execução
<ol>
  <li>hr-eureka-server</li>
  <li>hr-config-server</li>
  <li>hr-api-gateway</li>
  <li>Os demais microserviços sem ordem definida.
        Sugestão:
      <ul>
        <li>hr-user</li>
        <li>hr-worker</li>
        <li>hr-payroll</li>
    </ul>
  </li>
</ol>

## Eureka

O Eureka Server é uma ferramenta de código aberto desenvolvida pela Netflix e utilizada para implementar a descoberta de serviços em uma arquitetura de microserviços. Ele atua como um registro centralizado onde os microserviços podem se registrar e descobrir uns aos outros dinamicamente, facilitando a comunicação entre eles.

Principais características do Eureka Server:

* __Descoberta de Serviços:__ Oferece um mecanismo simples e eficaz para os microserviços se registrarem e descobrirem uns aos outros em um ambiente distribuído.
* __Balanceamento de Carga:__ Facilita a implementação de balanceamento de carga entre instâncias de serviços registradas no Eureka Server, permitindo distribuir o tráfego de forma equilibrada entre elas.
* __Monitoramento de Estado:__ Fornece recursos para monitorar o estado de saúde dos serviços registrados, permitindo a detecção e remoção automática de instâncias com falha.
* __Alta Disponibilidade:__ Projetado para ser altamente disponível e resiliente, com suporte para clusters e replicação de dados para evitar pontos únicos de falha.
* __Integração com Spring Cloud:__ Totalmente integrado com o ecossistema Spring Cloud, facilitando sua utilização em aplicativos desenvolvidos com Spring Boot.
* __Auto-regeneração:__ Capaz de auto-regenerar instâncias perdidas ou temporariamente inacessíveis, garantindo a integridade e disponibilidade contínua do registro de serviços.

Em resumo, o Eureka Server desempenha um papel fundamental na arquitetura de microserviços, fornecendo um mecanismo confiável e eficiente para a descoberta de serviços e comunicação entre eles, contribuindo para a construção de sistemas distribuídos escaláveis e resilientes.

_Acessando o eureka server:_
```
http://localhost:8761
```

## Keycloak

O Keycloak Server é uma solução de código aberto para gerenciamento de identidade e acesso, oferecendo recursos robustos de autenticação, autorização e gerenciamento de usuários. Desenvolvido pela Red Hat, o Keycloak simplifica a  implementação de autenticação e autorização em aplicativos web e serviços RESTful.

Entre os principais recursos do Keycloak Server estão:


* __Autenticação centralizada:__ Permite a autenticação única (SSO) em vários aplicativos e serviços, reduzindo a necessidade de múltiplas credenciais de login.
* __Autorização baseada em papéis:__ Facilita a definição de políticas de acesso com base em funções de usuário, permitindo um controle granular sobre os recursos protegidos.
* __Suporte a protocolos padrão:__ Integra-se facilmente a aplicativos através de protocolos como OAuth 2.0, OpenID Connect, SAML e LDAP.
* __Customização e extensibilidade:__ Oferece uma arquitetura flexível que permite a customização e extensão de funcionalidades de acordo com as necessidades específicas do projeto.
* __Console de administração intuitivo:__ Possui uma interface de usuário amigável para gerenciamento de usuários, configurações de segurança e políticas de acesso.
* __Segurança avançada:__ Implementa práticas de segurança robustas, incluindo criptografia de dados, proteção contra ataques de segurança e monitoramento de atividades suspeitas.
* __Escalabilidade e alta disponibilidade:__ Projetado para ser escalável horizontalmente e oferecer alta disponibilidade para lidar com cargas de tráfego variáveis.

Em resumo, o Keycloak Server é uma solução poderosa e altamente configurável para gerenciamento de identidade e acesso, que oferece segurança e praticidade para desenvolvedores e administradores de sistemas ao implementar autenticação e autorização em aplicativos e serviços.

_Acessando o keycloak server:_
```
http://localhost:8081
```


## Swagger 

O Swagger é uma poderosa ferramenta de código aberto para documentação de APIs, projetada para simplificar e automatizar o processo de documentação de serviços da web RESTful. Ele permite que desenvolvedores criem, visualizem e interajam com a documentação da API de forma eficiente, melhorando a compreensão e adoção das APIs por parte dos desenvolvedores consumidores.

Principais recursos do Swagger:

* __Interface Interativa:__ Oferece uma interface interativa e amigável que permite aos desenvolvedores explorar e testar endpoints da API diretamente no navegador.
* __Documentação Automática:__ Gera automaticamente documentação precisa da API com base nas anotações do código-fonte, como JavaDoc ou anotações específicas para outras linguagens.
* __Padronização:__ Promove a padronização na documentação da API, garantindo que todas as informações essenciais, como parâmetros de entrada, tipos de dados de resposta e códigos de status HTTP, sejam claramente definidas e consistentes.
* __Validação de Esquema:__ Validação automática dos esquemas de entrada e saída da API, garantindo consistência e conformidade com as especificações da API.
* __Geração de Clientes:__ Facilita a geração automática de clientes SDK (Software Development Kit) em várias linguagens de programação com base na especificação da API, agilizando o processo de desenvolvimento para os consumidores da API.
* __Suporte a Diversas Linguagens:__ Suporta uma ampla variedade de linguagens de programação e frameworks, permitindo que desenvolvedores de diferentes tecnologias se beneficiem da documentação gerada pelo Swagger.

Em resumo, o Swagger simplifica o processo de documentação de APIs, fornecendo uma solução abrangente e automatizada para criar e manter documentações precisas e interativas, facilitando a adoção e integração de APIs por parte dos desenvolvedores.


_Acessando o swagger:_
```
http://localhost:8765/swagger-ui.html
```
