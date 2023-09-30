# Projeto Final Curso Desenvolvedor Java - +praTi e Alfamidia.

Este é o repositório para o projeto final do curso "Mais Pra Ti", que consiste em uma simulação back-end de gerenciamento de cadastros implementado em Java.
O objetivo do projeto é permitir que os usuários realizem operações básicas de CRUD (Create, Read, Update, Delete) para gerenciar informações de pessoas e alunos.
O programa funciona como um aplicativo de console, portanto, as interações são feitas por meio do teclado e das opções de menu exibidas na tela.

## Detalhes do Projeto

Este projeto é um exemplo de aplicação simples em Java que serve como uma base para entender como criar e trabalhar com objetos, 
tratamento de erros e interação com o usuário em Java.
Principais pacotes, classes e o que foi usado da linguagem Java neste projeto:

1. **Programa.java**:
   - Esta é a classe principal do programa. Ela contém o método `main` que inicia a execução do programa.
   - Usa a classe `Scanner` para ler entradas do usuário.
   - Cria instâncias das classes `PessoaService` e `AlunoService` para realizar operações CRUD.

2. **PessoaService.java**:
   - Esta classe lida com operações relacionadas a pessoas.
   - Possui métodos para cada operação CRUD (criar, ler, atualizar e excluir).
   - Usa exceções personalizadas como `CadastroDuplicado`, `CadastroInvalido` e `CadastrosInexistentes` para tratar erros específicos.

3. **AlunoService.java**:
   - Semelhante à classe `PessoaService`, mas lida com operações específicas para alunos.
   - Também possui métodos para cada operação CRUD.

4. **Inicializar.java**:
   - Classe que contém métodos para inicializar dados de exemplo para alunos e pessoas.
   - É usado para preencher as listas iniciais de alunos e pessoas.

5. **menus.java**:
   - Esta classe contém métodos que exibem menus interativos para o usuário.
   - Permite ao usuário realizar operações como cadastro, atualização, listagem e exclusão de registros.
   - Lida com exceções relacionadas à entrada do usuário.

6. **exceptions** (pacote):
   - Este pacote contém exceções personalizadas que são lançadas em situações específicas, como quando um cadastro é duplicado, inválido ou inexistente.
   
7. **models** (pacote):
   - Este pacote contém as classes de modelo `Pessoa` e `Aluno`, que representam os objetos do mundo real que estão sendo gerenciados pelo programa.
   
8. **pom.xml**:
    - O arquivo POM (Project Object Model) é usado para configurar as dependências do Maven e as informações do projeto.


## Recursos Utilizados

O projeto utiliza recursos da linguagem Java, como:

- **Classes e Objetos**: O projeto é baseado na criação e manipulação de objetos, como `Pessoa` e `Aluno`, para representar entidades do mundo real.

- **Métodos e Funções**: Métodos são usados para realizar operações específicas, como cadastrar, atualizar e listar registros.

- **Estruturas de Controle**: Estruturas condicionais (if-else) e estruturas de repetição (loops) são usadas para controlar o fluxo do programa.

- **Exceções Personalizadas**: São lançadas exceções personalizadas para tratar erros específicos e fornecer mensagens de erro descritivas.

- **Entrada do Usuário**: A classe `Scanner` é usada para ler entradas do usuário a partir do console.

- **Tratamento de Erros**: O projeto utiliza blocos try-catch para lidar com exceções e erros.

- **Listas e Coleções**: Listas são usadas para armazenar objetos `Pessoa` e `Aluno`, permitindo a manipulação de múltiplos registros.

- **Manipulação de Strings**: A manipulação de strings é usada para formatar e validar entradas do usuário.

## Como Funciona

O programa funciona como um aplicativo de console, onde os usuários podem interagir com as funcionalidades por meio de um menu interativo. 
Aqui está um resumo de como o programa funciona:

1. **Menu Inicial**: Ao iniciar o programa, os usuários são apresentados a um menu inicial com várias opções.

2. **Cadastro de Pessoas e Alunos**: Os usuários podem escolher a opção de cadastrar pessoas ou alunos, onde serão solicitadas informações relevantes, como nome, data de nascimento e número de telefone.

3. **Listagem de Cadastros**: Os usuários podem visualizar todos os cadastros existentes, o que permite uma rápida consulta das informações armazenadas.

4. **Atualização de Cadastros**: Os usuários podem atualizar informações de cadastros específicos, informando o número de telefone associado ao cadastro que desejam atualizar.

5. **Exclusão de Cadastros**: Caso seja necessário excluir um cadastro, os usuários podem fazê-lo informando o número de telefone associado ao cadastro que desejam excluir.

6. **Encerramento do Programa**: Os usuários podem encerrar o programa a qualquer momento, voltando ao menu inicial e selecionando a opção "SISTEMA ENCERRADO".
