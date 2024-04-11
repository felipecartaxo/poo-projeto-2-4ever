# Sistema para gerenciar eventos

O sistema em questão é uma aplicação desenvolvida para facilitar o gerenciamento de eventos, participantes e ingressos. O sistema foi projetado com uma arquitetura que separa as camadas de apresentação, regras de negócio, modelo de negócio e repositório, visando modularidade e fácil manutenção.

## Funcionalidades Principais

### Classes do Modelo de Negócio e do Repositório

A classe Fachada contém métodos que implementam os requisitos do sistema, tais como:

- **Criar Evento**: Adicionar um evento no repositório, garantindo que as regras de negócio sejam respeitadas.
- **Criar Participante**: Adicionar um participante no repositório, considerando as regras de negócio relacionadas à idade.
- **Criar Convidado**: Adicionar um convidado no repositório, acumulando descontos de idade e desconto de empresa.
- **Criar Ingresso**: Adicionar um ingresso no repositório e criar os relacionamentos com o evento e o participante.
- **Apagar Evento**: Remover o evento do repositório, desde que não possua ingressos vinculados.
- **Apagar Participante**: Remover o participante do repositório, apagando todos os seus ingressos se necessário.
- **Apagar Ingresso**: Remover o ingresso do repositório, atualizando os relacionamentos com evento e participante.
- **Listar Eventos**, **Listar Participantes** e **Listar Ingressos**: Retorna todos os eventos, participantes e ingressos do repositório, respectivamente.

### Regras de Negócio

O sistema possui regras de negócio que devem ser respeitadas, tais como:

- Geração automática de ID para eventos.
- Preço mínimo de eventos é zero, mas nunca negativo.
- Validação obrigatória de data e descrição para eventos.
- Capacidade mínima de dois ingressos por evento.
- Restrições de capacidade e data para eventos.
- Restrições para exclusão de eventos, participantes e ingressos.

### Classes de Aplicação Swing

As classes de aplicação Swing oferecem uma interface gráfica para interação com o usuário, incluindo:

- **TelaPrincipal**: Menu de acesso às outras telas do sistema.
- **TelaEventos**: Listagem, criação e exclusão de eventos, além de exibição dos ingressos relacionados.
- **TelaParticipantes**: Listagem, criação e exclusão de participantes e convidados, com exibição dos ingressos relacionados.
- **TelaIngressos**: Listagem, criação e exclusão de ingressos, exibindo informações relevantes.

## Como utilizar

1. Clone o repositório em sua máquina local.
2. Compile e execute o projeto, garantindo que todas as dependências estejam instaladas.
3. Utilize a interface gráfica para gerenciar eventos, participantes e ingressos conforme suas necessidades.

## Persistência de Dados

As informações dos eventos, participantes e ingressos são armazenadas em arquivos CSV, permitindo a leitura e gravação dos objetos.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para propor melhorias!

## Autor

Este projeto foi desenvolvido por mim, Felipe Cartaxo. Você pode entrar em contato via [felipecartaxo1@gmail.com](felipecartaxo1@gmail.com) para mais informações.

## Licença

Sinta-se à vontade para utilizar e modificar o código conforme necessário.