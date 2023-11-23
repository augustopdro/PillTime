# PillTime:  Seu lembrete para uma vida mais saudável

Bem-vindo ao PillTime, a solução inteligente para gerenciar lembretes de medicamentos, desenvolvida para tornar a rotina de cuidados com a saúde mais simples e acessível para todos. Imagine um aplicativo intuitivo que ajuda não apenas os mais tech-savvy, mas também aqueles que não estão familiarizados com a tecnologia, como idosos e outros usuários menos experientes.
Integrantes do grupo:

-  RM 93442 - Gabriel Hitoshi Furone Yokogawa
-  RM 93150 - Guilherme Martins Nascimento
-  RM 93205 - Luis Fernando Nascimento de Oliveira
-  RM 94990 - Pedro Augusto Pereira Viana   
-  RM 95800 - Yasmin Cabral Dias


# Visão Geral

PillTime é mais do que um aplicativo de lembretes - é uma ferramenta que visa tornar a tomada de medicamentos uma tarefa fácil e organizada. Com uma interface intuitiva e fácil de usar, nosso objetivo é oferecer uma experiência amigável para que você possa gerenciar seus medicamentos sem esforço.

## Testar a API

A API está hospedada na Azure. Para testar se os endpoints estão funcionando, basta clicar no link do Postman e realizar os testes. Todos os endpoints contam com seus respectivos json's com exemplos de como devem ser feitas suas requisições. 
**IMPORTANTE:** Para testar a API, primeiramente use o endpoint de "Usuario - Cadastrar" para criar seu usuario, após isso, use o endpoint "Usuario - Login" para logar, ao fazer isso a requisição vai retornar um response com um Bearer Token, salve este token. Para conseguir testar todos os outros endpoints, você deve ir na aba "Authorization", no campo "Type" selecione o valor "Bearer Token" e no campo "Token" cole o token que você salvou anteriormente. Fazendo isso, será possível testar todos os outros endpoints de maneira adequada. Em caso de dúvidas, assista ao tutorial abaixo no youtube mostrando o passo a passo de como fazer isso. 

- Postman: https://www.postman.com/altimetry-astronaut-37339768/workspace/alimentech/collection/23202593-1f7b59a0-b414-465c-abaa-9aaa09dabb14?action=share&creator=23202593
- Vídeo tutorial: 


# Funcionalidades Principais

1. **Lembretes Personalizados:**  Crie lembretes personalizados para cada medicamento, fazendo uso de imagens, definindo horários e doses de acordo com suas necessidades.

2. **Interface Intuitiva:** Uma interface simplificada, projetada para ser fácil de usar para usuários de todas as idades, sem complicação.

3. **Segurança de Dados:** Utilizamos Azure como nuvem para garantir a segurança dos dados sensíveis dos usuários, mantendo-os protegidos e acessíveis somente por você.

4. **Compatibilidade Móvel:** Nosso aplicativo é uma aplicação mobile feita em React Native, permitindo que você gerencie seus lembretes onde quer que esteja.


# Benefícios do PillTime

1. **Simplicidade na Rotina:**  Simplificamos a gestão de medicamentos para que você não precise se preocupar em esquecer doses importantes.

2. **Acessibilidade para Todos:** Projetado para ser utilizado por qualquer pessoa, independentemente do nível de habilidade técnica.

3. **Facilidade de Acesso:** Disponível a qualquer momento e em qualquer lugar através do seu dispositivo móvel.

4. **Integração com Dispositivo IoT:** Com o PillTime, oferecemos a integração com um dispositivo IoT personalizado. Desenvolvemos um dispositivo utilizando Arduino que se conecta à nossa API. Esse dispositivo verifica os horários dos lembretes do cliente e, quando chega o momento agendado, emite um alarme sonoro e luminoso, garantindo que você nunca mais perca a hora de tomar seus medicamentos.



# Arquitetura

<img align="center" alt="Diagrama-UML" src="https://github.com/augustopdro/SmartSellers-DBE/blob/master/ArquiteturaSmartsellers.jpg?raw=true">

---

## Endpoints
- Usuário
    - [Cadastrar](#cadastrar)
    - [Login](#login)
    - [Altualizar cadastro](#atualizar-cadastro)
- Lembrete
    - [Registrar](#registrar)
    - [Listar](#listar)
    - [Atualizar registro](#atualizar-registro)
    - [Deletar Registro](#deletar-registro)


---

## Cadastrar
`POST` /api/usuario/cadastrar

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| nome | string | sim | é o nome do usuário, deve respeitar o Regex(^[a-zA-Z]{3,}$)
| email | string | sim | é o email empresarial, deve respeitar o ReGex(^[A-Za-z0-9+_.-]+@(.+)$)
| senha | string | sim | é a senha do usuário, deve ter no minimo 6 caracteres


**Exemplo de corpo do request**
```js
{
	"nome": "Pedro Augusto",
	"email": "pedro@gmail.com",
	"senha": "Senha123"
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 201 | Usuario cadastrado com sucesso
| 400 | Erro na requisição

---

---

## Atualizar Cadastro
`PUT` /api/usuario/{id}

**Exemplo de corpo do request**
```js
{
	"nome": "Pedro Viana",
	"email": "pedro@gmail.com",
	"senha": "Senha1234"
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Usuario atualizado com sucesso
| 400 | Erro na requisição
| 404 | Usuario não encontrado

---

---

## Login
`POST` /api/usuario/login

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| codigoAcesso | string | sim | é o código enviado ao email do usuário, que permite logar na plataforma

**Exemplo de corpo do request**
```js
{
{
	"email": "pedro@gmail.com",
	"senha": "Senha123"
}
}
```

**Exemplo de corpo do response**

| Campo | Tipo | Descrição
|:-------:|:------:|-------------
| token | string | Token de autenticação do usuario no sistema

```js
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ5YXNtaW5AZ21haWwuY29tIiwiZXhwIjoxNzAwNzgwNTU1LCJpc3MiOiJQaWxsVGltZSJ9.KNk7pC8_iWxbddKToiwoSvlu5VvFZoJlx9gHPXdSEqc",
    "type": "JWT",
    "prefix": "Bearer",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/usuario/login"
        },
        "cadastrar": {
            "href": "http://localhost:8080/api/usuario/cadastrar"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Usuario validado com sucesso
| 401 | Código de acesso incorreto

---

---

## Registrar 
`POST` /api/lembrete/{userId}/registrar

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| nome | String | sim | é o nome do medicamento
| dosagem | String | sim | é uma pequena descrição sobre a dosagem do medicamento
| dataInicial | LocalDate | sim | é a data inicial que o usuario ingeriu o medicamento
| horarioInicial | LocalTime | sim | é o horario inicial que o usuario ingeriu o medicamento
| intervalo | Integer | sim | é o intervalo entre cada dose do medicamento
| dataFinal | LocalDate | não | é a data final que o usuario ingeriu o medicamento
| arquivoImagem | String | não | é uma imagem do medicamento


**Exemplo de corpo do request**
```js
{
  "nome": "Dipirona",
  "dosagem": "Duas doses de 150mg",
  "dataInicial": "2023-11-22",
  "horarioInicial": "09:00:00",
  "intervalo": 8,
  "dataFinal": "2023-11-25",
  "arquivoImagem": "Dipirona.jpeg"
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 201 | Lembrete cadastrado com sucesso
| 400 | Erro na requisição
| 404 | Usuario não encontrado

---

---
## Listar 
`GET` /api/lembrete/{userId}/historico

**Exemplo de corpo do response**

| Campo | Tipo | Descrição
|:-------:|:------:|-------------
| nome | String | sim | é o nome do medicamento
| dosagem | String | sim | é uma pequena descrição sobre a dosagem do medicamento
| dataInicial | LocalDate | sim | é a data inicial que o usuario ingeriu o medicamento
| horarioInicial | LocalTime | sim | é o horario inicial que o usuario ingeriu o medicamento
| intervalo | Integer | sim | é o intervalo entre cada dose do medicamento
| dataFinal | LocalDate | não | é a data final que o usuario ingeriu o medicamento
| arquivoImagem | String | não | é uma imagem do medicamento


```js
{
    "content": [
        {
            "id": 1,
            "nome": "Rivotril",
            "dosagem": "Duas doses de 150mg",
            "dataInicial": "2023-11-22",
            "horarioInicial": "09:00:00",
            "intervalo": 8,
            "dataFinal": "2023-11-25",
            "arquivoImagem": "Clonazepam.jpeg"
        }
    ],
    "number": 0,
    "totalElements": 1,
    "totalPages": 1,
    "first": true,
    "last": true,
    "_links": {
        "self": {
            "href": "https://pilltime.azurewebsites.net/api/lembrete/1/historico"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Lembretes recuperados com sucesso
| 404 | Usuario não encontrado
| 404 | Lembretes não encontrados
| 400 | Erro na requisição

---

---

## Atualizar Registro
`PUT` /api/lembrete/{lembreteId}

**Exemplo de corpo do request**
```js
{
    "nome": "Dipirona",
    "dosagem": "Uma dose de 200mg",
    "dataInicial": "2023-11-22",
    "horarioInicial": "09:00:00",
    "intervalo": 7,
    "dataFinal": "2023-11-25",
    "arquivoImagem": "Dipirona.jpeg"
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Lembrete atualizado com sucesso
| 400 | Erro na requisição
| 404 | Usuario não encontrado

---

---

## Deletar Registro
`DELETE` /api/lembrete/{userId}/deletar/{lembreteId}

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 204 | Lembrete deletado com sucesso
| 404 | Usuario não encontrado
| 404 | Lembrete não encontrado

---
