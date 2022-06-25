# API CRUD RESTFUL usando Spring Boot, Hibernate, JPA, Lombok e MySQL.

<p align="center">
 <img src="https://img.shields.io/static/v1?label=java&message=Programming%20languages&color=red&style=for-the-badge&logo=JAVA"/>
 <img src="https://img.shields.io/static/v1?label=spring%20boot&message=Framework&color=green&style=for-the-badge&logo=SPRING%20BOOT"/>
 <img src="https://img.shields.io/static/v1?label=maven&message=Apache%20Maven%20Project&color=pink&style=for-the-badge&logo=MAVEN"/>
 <img src="http://img.shields.io/static/v1?label=License&message=MIT&color=green&style=for-the-badge&logo=LICENSE"/>
  <img src="http://img.shields.io/static/v1?label=STATUS&message=Finalizado&color=purple&style=for-the-badge&logo=FINALIZADO"/>
 </p>
 
> Status do Projeto: :heavy_check_mark: :warning: (em desenvolvimento)
 
### Tópicos
:small_blue_diamond: [Links Úteis](#links-úteis)

:small_blue_diamond: [Descrição do projeto](#descrição-do-projeto)

:small_blue_diamond: [Funcionalidades do projeto](#funcionalidades-do-projeto)

:small_blue_diamond: [Pré-requisistos](#descrição-do-projeto)

:small_blue_diamond: [Clonando o projeto](#clonando-o-projeto)

:small_blue_diamond: [Criando o projeto com Spring Boot](#criando-o-projeto-com-spring-boot)

:small_blue_diamond: [Criando a classe de entidate JPA](#criando-a-classe-de-entidate-jpa)
 
:small_blue_diamond: [Criando o repositório JPA](#criando-o-repositório-jpa)
 
:small_blue_diamond: [Criando o Controller](#criando-o-controller)
 
:small_blue_diamond: [Listando todos os contatos](#listando-todos-os-contatos)
 
:small_blue_diamond: [Listando contato por ID](#listando-contato-por-id)
 
:small_blue_diamond: [Criando um novo contato](#criando-um-novo-contato)

:small_blue_diamond: [Atualizando um contato por ID](#atualizando-um-contato-por-id)

:small_blue_diamond: [Removendo um contato pelo ID](#removendo-um-contato-por-id)

:small_blue_diamond: [Conectando com o banco de dados MySQL](#conectando-ao-banco-de-dados-mysql)

:small_blue_diamond: [Conclusão](#conclusão)

:small_blue_diamond: [Ícones](#ícones)

:small_blue_diamond: [Testando a API manualmente](#testando-a-api-manualmente)

...

## Links Úteis

Para completar o desenvolvimento do projeto tutorial será necessário as seguintes ferramentas:

- [Java JDK (v8+)](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
- [Maven (v3+)](https://maven.apache.org/download.cgi)
- [MySQL + MySQL Workbench (última versão disponível)](https://dev.mysql.com/downloads/workbench/)
- [Postman](https://www.getpostman.com)
- [Spring Initializer](https://start.spring.io/)
- [Spring Tools Suite](https://spring.io/tools)
- [REST Client](https://marketplace.visualstudio.com/items?itemName=humao.rest-client)

...

## Descrição do projeto

Nesse projeto será demonstrado como desenvolver uma API RESTFUL para um CRUD (Create, Read, Update e Delete - em português Criar, Ler, Atualizar e Remover) utilizando **Spring Boot**, **Hibernate**, **JPA**, **Lombok** e **MySQL**.

...

## Funcionalidades do projeto

Será criado uma API de Contatos com um Controller que irá expor cinco métodos HTTP (URIs RESTFUL) definidos abaixo:

- Listar todos os contatos - **@GetMapping(“/contacts)**
- Obter um contato específico pelo ID - **@GetMapping(“/contacts/{id}”)**
- Remover um contato pelo ID - **@DeleteMapping(“/contacts/{id}”)**
- Criar um novo contato - **@PostMapping(“/contacts)**
- Atualizar detalhes de um contato - **@PutMapping(“/contacts/{id}”)**

...

## Pré-requisitos

- :warning: Conhecimentos Progrmação Orientada a Objetos - POO.
- :warning: Conhecimentos de Programação Java.
- :warning: Conhecimentos de Hibernate.
- :warning: Conhecimentos de JPA.
- :warning: Conhecimentos de Banco de Dados (Mysql).
- :warning: Conhecimentos de API.

...

## Clonando o projeto

```
git clone git@github.com:KleilsonSantos/api-rest-spring
```
...

## Criando o projeto com Spring Boot

O primeiro passo é criar o projeto com Spring Boot. Pode-se utilizar o serviço do https://start.spring.io, que fornece um projeto pronto para ser importado por uma IDE, além de uma classe main e arquivo pom.xml do Maven com as dependências. Para este exemplo, as dependências necessárias são: **Web**, **JPA**, **Lombok** e **MySQL**.

## Criando a classe de entidate JPA

A primeira classe que será criada é a entidade **JPA**, ou seja, a classe que representa a tabela que está no banco de dados. A classe se chamará Contact com uma chave primária id:

```
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Contact {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;Criando um novo contato

   private String name;
   private String email;
   private String phone;
}
```

As seguintes anotações (annotations) são do projeto **Lombok** que ajuda a manter o código mais limpo e enxuto já que não é necessário gerar os métodos getters e setters, além dos construtores (esse código será gerado e estará presente nos arquivos .class quando o código for compilado).

- AllArgsConstructor: cria automaticamente um construtor com todas os atributos da classe como argumento.
- NoArgsConstructor: cria automaticamente um construtor vazio (sem argumentos).
- Data: cria automaticamente os métodos **toString**, **equals**, **hashCode**, **getters** e **setters**.

É necessário configurar o projeto Lombok na IDE para que essas anotações funcionem corretamente, senão o código apresentará problemas de compilação quando se tentar usar algum método getter ou setter (por exemplo). Caso esteja usando o Eclipse siga os passos descritos no link (https://projectlombok.org/setup/eclipse) e caso esteja usando IntelliJ IDEA instale o plugin descrito no link (https://plugins.jetbrains.com/plugin/6317-lombok-plugin). No link do projeto (https://projectlombok.org/) também pode encontrar os passos para outras IDES e editores.

A anotação **@Entity** pertence ao JPA e isso significa que a classe será automaticamente mapeada à tabela com o mesmo nome (classe Contact e tabela Contact). Todos os atributos dessa classe também serão mapeados com as respectivas colunas. Podemos omitir a anotação **@Column** para cada atributo da classe desde que o nome do atributo seja o mesmo nome da coluna. Caso a coluna tenha o nome diferente do atributo precisamos especificar assim (supondo que o nome da coluna seja contact_name):

```
@Id
@Column(name = "contact_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(name = "contact_name", nullable = false, length = 15)
private String name;

@Column(name = "contact_email", nullable = false, unique = true, length = 30)
private String email;

@Column(name = "contact_age")
private Integer age;

```
Para cada entidade do JPA, também é necessário identificar qual atributo é a chave primária. Isso é feito através da anotação **@Id**. E com a anotação @GeneratedValue serve para identificar como a coluna id será gerada. Nesse caso será definido pelo próprio banco de dados.

...

## Criando o repositório JPA

Com a classe modelo criada, o próximo passo é criar o repositório **(ou DAO: Data Access Object)** que irá fornecer os métodos para as operações CRUD. Uma forma fácil de fazer isso é criar uma interface que extende a interface **JpaRepository** (do Spring Data):

```
@Repository
public interface ContactRepository extends JpaRepository<contact, long=""> {}
```
A interface **JpaRepository** precisa de dois parâmetros do tipo **Generics:** o primeiro é a entidade JPA que representa a tabela e o segundo é o tipo da chave primária (o mesmo tipo do atributo id).

## Criando o Controller

O Controller é a classe responsável por expor cada **URI** que estará disponível na **API**. O código inicial está listado abaixo:

```
@RestController
@RequestMapping({"/contacts"})
public class ContactController {

   private ContactRepository repository;

   ContactController(ContactRepository contactRepository) {
       this.repository = contactRepository;
   }
   // métodos do CRUD aqui
}
```

A anotação **@RestController** contém as anotações **@Controller** e **@ResponseBody** (pode omitir essas duas para deixar o código mais limpo). A anotação **@Controller** representa uma classe com endpoints (URIs que serão expostas pela API) e a classe indica que o valor retornado pelos métodos devem ser vinculados ao corpo da resposta (response body).

A anotação **@RequestMapping("/contacts")** indica que a **URL** da **API** desse controller começa com /contacts, isso significa que pode-se acessar usando a URL http://localhost:8080/contacts (acesso local).

Como o controller irá acessar o repositório diretamente (como este é um exemplo simples, a camada de serviço está sendo omitida, porém é sempre uma boa prática usar uma classe Service que evoca o repositório e contém a lógica de negócio do projeto para deixar o código da classe controller enxuto e mais limpo), é necessário declarar o repositório como atributo.

O Spring automaticamente fornece a injeção de dependência. Este exemplo não está usando a anotação **@Autowired** pois não é mais considerado uma boa prática para injeção de dependência de atributos obrigatórios. Desde a versão 4 do Spring a prática recomendada é o uso de injeção de dependência por construtor (as IDEs mais modernas inclusive apresentam um alerta quando fazemos o uso do **@Autowired**).

## Listando todos os contatos

```
@GetMapping
public List<?> findAll(){
   return repository.findAll();
}
```

O método **findAll** é direto ao ponto: utiliza o método **findAll** da interface **JpaRepository** que faz um select * from contacts.

Como esta é uma **API RESTful**, pode-se omitir o código **@RequestMapping(value="/contacts", method=RequestMethod.GET)** e utilizar somente a anotação **@GetMapping**

Por padrão, o formato do resultado será um **JSON**.
Obtendo um contato especídifo pelo **ID (GET /contacts/{id})**

## Listando contato por ID

```
@GetMapping(value = "/{id}")
public Contact findById(@PathVariable Long id) {
	Contact contact = contactRepository.findById(id).get();
	return contact;
}
```

Seguindo os conceitos RESTful, é necessário passar na **URL** o **ID** do registro. A anotação **@PathVariable** vincula o parâmetro passado pelo método com a variável do path. Note que o parâmetro long id tem o mesmo nome do path declarado em **@GetMapping(path = {"/{id}"})**.

A lógica para obter um contato específico é utilizar o método **findById** da interface JpaRepository (que irá fazer um select * from contacts where id = ?). Caso o registro seja encontrado, é retornado o status **HTTP 200** (ResponseEntity.ok()) e no corpo da resposta é enviado o registro. Caso o registro não seja encontrado é retornado o status **HTTP 404** - Não Encontrado **(ResponseEntity.notFound())**.

Um ponto importante é notar a diferença entre os métodos **indAll** e **findById** da interface **JpaRepository**. O método **findAll** retorna uma lista de entidades. Caso não exista nenhum registro na tabela, é retornada uma lista vazia ([]). Já o método **findById** retorna um **Optional**. O classe Optional existe desde o Java 8 e representa um container que pode ou não conter um valor não nulo (diferente de null). Essa classe é bem interessante para evitar exceções do tipo **NullPointerException**, e também permite fazer o uso dos métodos da classe Optional que simulam a programação funcional. Com o retorno do método do método findAll da interface JpaRepository podemos fazer o uso do método map caso o valor retornado seja diferente de nulo. O método map transforma (mapeia) o registro retornado em um tipo ResponseEntity.

Existe também a diferença no tipo do retorno dos métodos no controller. Equanto to **findAll** retorna uma lista diretamente, o método **findById** retorna um **ResponseEntity** para indicar sucesso ou não.

## Criando um novo contato

```
@PostMapping
public Contact create(@RequestBody Contact contact){
   return repository.save(contact);
}
```
O método create também é bem direto ao ponto: apenas chama o método save da interface **JpaRepository**. Após criar o registro na tabela, retorna o contato com o atributo id populado e o registro é retornado no corpo de resposta.

A anotação **@RequestBody** indica que o parâmetro contact será vinculado do corpo da requisição. Isso significa que o método espera o seguinte conteúdo do corpo da requisição **(em formato JSON)**:

```
{
   "name": "Java",
   "email": "java@email.com",
   "phone": "(111) 111-1111"
}
```

Com o uso dessa anotação, o Spring é inteligente e consegue ler e transformar o conteúdo em uma instância da classe Contact.


## Atualizando um contato por ID

```
@PutMapping(value = "/{id}")
public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Contact contact) {
	return contactRepository.findById(id).map(update -> {
		update.setName(contact.getName());
		update.setEmail(contact.getEmail());
		update.setAge(contact.getAge());
		Contact updated = contactRepository.save(update);
		return ResponseEntity.ok().body(updated);
	}).orElse(ResponseEntity.notFound().build());
}
```

Para atualizar um registro, é necessário informar seu ID no caminho da URL (similar ao processo de obter um registro específico). Caso deseje usar um nome de variável diferente do que foi utilizado também pode utilizar o seguinte código **@PathVariable("recordID")** long id, desde que otherID também seja o nome em **@PutMapping(value="/{otherID}")**. Além do ID, também é necessário passar o objeto com os dados atualizados.

O próximo passo é encontrar o registro a ser atualiza que está na base de dados. Se o registro for encontrado, pode-se fazer as atualizações necessárias e assim chamar o método save e retornar os dados do registro atualizados. Note que o método save também foi utilizado na criação do registro. Caso o objeto tenha sido recuperado da base tenha um ID, será realizado um update e não um insert na tabela.

Caso o registro não seja encontrado, é retornado um erro **HTTP 404**.

Um ponto importante para esse método (e também para o processo de criação de registros) é que a própria classe de entidade JPA está sendo utilizada como objeto do tipo parâmetro. Não é uma prática recomendada utilizar a entidade JPA como um objeto de transferência **(ou DTO: Data Transfer Object)**. É sempre bom evitar expor o modelo da base diretamente para o cliente da API. Para esse caso, pode-se criar uma classe com todos os atributos da classe Contact, exceto o atributo id (ou uma classe com atributos que facilite a manipulação dos dados por um front-end por exemplo).

Pode-se ainda desenvolver uma série de validações para melhorar esse código. Por exemplo, pode-se adicionar uma validação para garantir que o id do registro passado como parâmetro é o mesmo id passado na URL. Pode-se também utilizar a API Java Beans para aplicar validações de tamanho de campo, obrigatoriedade de atributos, etc. É aqui que entrar toda a lógica de negócio necessária para a aplicação funcionar da forma que se é esperado.

## Removendo um contato pelo ID

```
@DeleteMapping(value = "/{id}")
public ResponseEntity<?> delete(@PathVariable("id") Long id) {
	return contactRepository.findById(id)
		.map(delete -> {
			contactRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
}
```

Para remover um contato pelo ID, utiliza-se o id que foi passado como parâmetro para procurar se o registro existe na base. Caso exista, utiliza-se o método deleteById da interface JpaRepository e retorna o status **HTTP 200** para indicar sucesso. Caso o registro não exista, retorna um erro **HTTP 404**.

## Conectando com o banco de dados MySQL

Para que o código consiga se conectar com sucesso na base de dados MySQL, é necessário informar os detalhes de conexão. Esses detalhes são declarados no arquivo src/main/resources/application.properties:


## Adicionando arquivo sql ao projeto

criar um arquivo com o nome **import.sql** na pasta **src/main/resources** com seguintes comandos

```
INSERT INTO tb_contact (contact_name,contact_email,contact_age) VALUES ('Kleilson','kleilson@icloud.com',40);
INSERT INTO tb_contact (contact_name,contact_email,contact_age) VALUES ('Jhon','jhon@gmail.com',34);
INSERT INTO tb_contact (contact_name,contact_email,contact_age) VALUES ('Carlos','kleilson@live.com',22);
INSERT INTO tb_contact (contact_name,contact_email,contact_age) VALUES ('Yanni','yanni@outlook.com',3);
INSERT INTO tb_contact (contact_name,contact_email,contact_age) VALUES ('Júlia','julia@icloud.com',12);
```


```
# Configurando a porta do servidor apach
server.port = 2222
## Spring DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/crud_spring
# User
spring.datasource.username=root
# Password
spring.datasource.password=123123
# Dialeto SQL melhorar o SQL gerado pelo Hibernate
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
# Apontando para o JPA e Hibernate qual é o Dialeto do banco de dados
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
# Hibernate ddl auto,responsável por ler nossas entidades e criar as tabelas do nosso banco de dados automaticamente (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto=create-drop
# Configuração para mostrar log do SQL no console
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
# configuração do Hibernate para reconhecer o nome de tabelas em caixa alta
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

Caso não deseje executar o script para criar a tabela manualmente, também pode-se adicionar essa configuração para que o Hibernate crie a tabela automaticamente:

```
# Hibernate ddl auto (create, create-drop, validate, update)
  spring.jpa.hibernate.ddl-auto=update
```

Apesar da configuração de geração de tabelas simplificar o processo, não é uma prática recomendada para bases de produção. Para produção, pode ser que queira criar um ID funcionar para poder executar apenas scripts DLM (Data Manipulation Language) como select, insert, update e delete e não poder executar nenhuma operação DDL (Data Definition Language) como create table, alter, drop table, etc.

Por enquanto, os dados de conexão estão declarados diretamente no arquivo (url da base, usuário e senha). Isso também não é uma boa prática. No desenvolvimento moderno, essas informações ficarão no container que irá executar a aplicação e irá passar essas informações dinamicamente. Iremos aprender como fazer isso em um artigo futuro.

## Testando a API manualmente

Pode-se ver com a API se comporta na prática utilizando o Postman (https://www.getpostman.com/). As imagens a seguir utilizam o plugin REST Client (https://marketplace.visualstudio.com/items?itemName=humao.rest-client).

...

## Conclusão

E a API RESTful CRUD está pronta! Um ponto importante é a anotação que foi utilizada em cada método que foi desenvolvido:

- Listar todos os contatos - **@GetMapping(“/contacts")**
- Obter um contato específico pelo ID - **@GetMapping(“/contacts/{id}”)**
- Remover um contato pelo ID - **DeleteMapping(“/contacts/{id}”)**
- Criar um novo contato - **@PostMapping(“/contacts)**
- Atualizar detalhes de um contato - **@PutMapping(“/contacts/{id}”)**

Apesar da URL ser a mesma (/contacts), o que garante os métodos HTTP diferentes são cada uma das anotações usadas em cada método.
Esse projeto mostrou como desenvolver uma API CRUD conectando num banco de dados local. Os próximos passos são criar um banco de dados em um ambiente cloud além de fazer o deploy da aplicação na nuvem também para que outros usuários também possam utilizar essa API.

...

## Ícones
- :package: New Functionality
- :arrow_up: Updating
- :beetle: Bug correction
- :checkered_flag: Release

...





