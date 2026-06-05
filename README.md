# TaskFlow - Aplicativo de Lista de Tarefas

![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue?logo=kotlin&logoColor=white) ![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-1.6-3DDC84?logo=jetpack&logoColor=white) ![Navigation Compose](https://img.shields.io/badge/Navigation_Compose-2.7-007ACC?logo=android&logoColor=white) ![Room Database](https://img.shields.io/badge/Room_Database-2.6-4CAF50?logo=sqlite&logoColor=white) ![MVVM](https://img.shields.io/badge/MVVM-architecture-7B1FA2) ![Material 3](https://img.shields.io/badge/Material_3-1.1-3B82F6?logo=materialdesign&logoColor=white)

Aplicativo desenvolvido como projeto final do módulo intermediário do curso **Capacita Brasil (iRede) - Desenvolvimento Android**. Este aplicativo apresenta a aplicação prática dos conteúdos do módulo e foi pensado para demonstrar, de forma clara e funcional, as habilidades exigidas na entrega final.

Objetivo: demonstrar a aplicação prática de Jetpack Compose, Navigation Compose, Room e MVVM em um aplicativo funcional de lista de tarefas.

Valor do projeto: segue boas práticas de engenharia (separação de responsabilidades e código modular), proporcionando uma base organizada para manutenção e futuras extensões.

## Informações do Aluno

- **Nome Completo:** Francisco Isaías Oliveira de Sousa
- **Matrícula:** Capacita Brasil (iRede) - Desenvolvimento Android
- **Data da Entrega:** 05/06/2026

## Justificativa da Escolha do Tema

O tema escolhido foi o desenvolvimento de um aplicativo de lista de tarefas, pois permite aplicar de forma prática os principais conceitos de desenvolvimento Android moderno.

Essa proposta atende diretamente aos requisitos obrigatórios do projeto, incluindo:

- Navegação entre telas com Navigation Compose
- Persistência local com Room Database
- Organização em arquitetura MVVM
- Interface em Jetpack Compose

Além disso, a lista de tarefas é um caso de uso funcional e comum, com boa escalabilidade para futuras melhorias como filtros, categorias ou prioridades.

## Descrição do Funcionamento do Aplicativo

O TaskFlow é um gerenciador de tarefas simples e funcional. O usuário pode:

- Criar novas tarefas com título e descrição
- Visualizar a lista de tarefas cadastradas
- Visualizar detalhes de cada tarefa
- Excluir tarefas existentes
- Navegar entre as telas do app

Os dados são armazenados localmente usando **Room Database**, garantindo que as tarefas permaneçam salvas mesmo após fechar o aplicativo.

### Telas Principais

- **Tela de Lista de Tarefas**
  - Exibe todas as tarefas salvas
  - Permite acessar o formulário de cadastro
  - Permite acessar os detalhes de uma tarefa
  - Permite excluir tarefas

- **Tela de Cadastro / Visualização de Tarefa**
  - Oferece campos para título e descrição
  - Permite salvar nova tarefa
  - Exibe os dados de uma tarefa existente para consulta

## Preview

### Demonstração da Aplicação

Confira abaixo como o **TaskFlow** facilita a criação, visualização e exclusão de tarefas de forma simples, rápida e intuitiva.

Acompanhe o funcionamento pelo GIF a seguir:

![Preview do TaskFlow](https://github.com/user-attachments/assets/5c29a940-b048-48e9-8dd0-abc30bd98d74)

## Tecnologias Utilizadas

- Kotlin
- Jetpack Compose
- Navigation Compose
- Room Database
- Arquitetura MVVM
- Material 3

## Estrutura do Projeto

O projeto está organizado em pacotes e pastas que refletem a arquitetura MVVM e a separação de responsabilidades:

- `app/src/main/java/com/francisco/taskflow/data/` → Entidade `Task`, DAO e `TaskDatabase`
- `app/src/main/java/com/francisco/taskflow/repository/` → `TaskRepository` para comunicação com o Room
- `app/src/main/java/com/francisco/taskflow/viewmodel/` → `TaskViewModel` e `TaskViewModelFactory`
- `app/src/main/java/com/francisco/taskflow/ui/` → UI em Jetpack Compose, com subpastas:
  - `components/` → componentes reutilizáveis de interface
  - `navigation/` → navegação entre telas usando Navigation Compose
  - `screens/` → telas principais do aplicativo
  - `theme/` → definições de tema, cores e tipografia
- `app/src/main/java/com/francisco/taskflow/` → `MainActivity.kt` e `TaskFlowApplication.kt`

Essa organização segue o padrão de projeto Android, mantendo cada camada separada:
- interface em `ui`
- lógica e estado em `viewmodel`
- dados e persistência em `data` e `repository`

## Observações

- Aplicativo desenvolvido com foco em arquitetura limpa e organização de código
- Interface construída em Jetpack Compose e Material 3
- A navegação foi implementada com Navigation Compose
- O projeto já está preparado para futuras melhorias, como categorias, filtros, prioridades e autenticação de usuário

## Conclusão

O TaskFlow foi desenvolvido para cumprir os critérios do desafio final do módulo intermediário do curso **Capacita Brasil (iRede) - Desenvolvimento Android**, demonstrando o uso de Kotlin, Jetpack Compose, Room e MVVM com um app funcional de gerenciamento de tarefas.

## Contribuição

Se quiser contribuir com feedback ou sugestões, fique à vontade para abrir uma **[Issue](https://github.com/isaias-oliveira-fullstack/TaskFlow/issues)** ou **[enviar ideias](https://github.com/isaias-oliveira-fullstack/TaskFlow/pulls)**. 

## Licença

Este projeto está licenciado sob a **Licença MIT**.

Veja o arquivo **[LICENSE](./LICENSE)** para mais detalhes.

## Autor

Projeto desenvolvido por **Isaias Oliveira**.  
Conecte-se comigo no **[in/isaias-oliveira-dev](https://www.linkedin.com/in/isaias-oliveira-dev/)**
