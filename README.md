# AnkiApp - Gerador de Cards para Anki

## 📌 Descrição
O **AnkiApp** é uma aplicação web que permite gerar arquivos CSV no formato adequado para importação no Anki. Basta inserir uma série de perguntas e respostas no formato adequado, e o sistema converte automaticamente para um arquivo CSV pronto para download.

## 🚀 Funcionalidades
- Interface web para entrada de perguntas e respostas.
- Validação para garantir que o formato correto seja seguido.
- Geração de arquivo CSV para importação no Anki.
- Limpeza automática do campo de entrada após a geração do arquivo.

## 📋 Formato de Entrada
O texto deve seguir o seguinte padrão:

```
Frente: O que é o CRSFN (Conselho de Recursos do SFN)?
Verso: Se o operador receber punição de algum supervisor, pode recorrer ao CRSFN.

Frente: Qual é a função do CMN em relação à inflação?
Verso: Definir as metas de inflação (IPCA: 3% a.a em 2024), com margem de 1.5 percentual.
```

- As palavras **Frente:** e **Verso:** (independente de maiúsculas e minúsculas) são obrigatórias.
- O sistema valida a entrada e retorna erro caso o formato não seja seguido.

## 🤖 Uso com GPT para Geração Automática de Cards
Este aplicativo pode ser utilizado em conjunto com um modelo de IA, como o GPT, para extrair informações de um PDF e gerar automaticamente os cards no formato correto.

### 🔹 Passo a Passo:
1. Faça o upload do PDF para o GPT e peça para resumir o conteúdo em perguntas e respostas.
2. Use o seguinte prompt para garantir que as respostas estejam no formato correto para o AnkiApp:

```
Extraia informações deste PDF e transforme-as em flashcards no seguinte formato:
Frente: [Pergunta]
Verso: [Resposta]

Garanta que todas as perguntas comecem com 'Frente:' e todas as respostas com 'Verso:'.
Separe cada par de pergunta e resposta com uma linha em branco.
```

3. Copie e cole o resultado gerado no campo de entrada do AnkiApp.
4. Clique em "Gerar Arquivo CSV" para obter o arquivo pronto para importação no Anki.
5. **Dentro do Anki**, ao importar o arquivo CSV, **configure a separação por vírgula (",")** para que os dados sejam corretamente interpretados.

## 🛠️ Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3**
- **Thymeleaf**
- **Maven**

## 🏗️ Como Instalar o Projeto
### 1️⃣ Clonar o Repositório
```sh
git clone https://github.com/Anderson1605R/anki.git
cd anki
```

### 2️⃣ Instalar Dependências
```sh
mvn clean install
```

### 3️⃣ Importar o Projeto na IDE
- Abra sua IDE favorita (IntelliJ IDEA, Eclipse, VS Code com Extensão Spring Boot)
- Importe o projeto como um **projeto Maven**
- Aguarde a sincronização das dependências

### 4️⃣ Rodar o Projeto
- Na IDE, localize a classe principal (com a anotação `@SpringBootApplication`)
- Execute a aplicação clicando em **Run** ou rodando o comando abaixo no terminal:
```sh
mvn spring-boot:run
```
- Acesse a aplicação pelo navegador em: `http://localhost:8080`



## 📄 Licença
Este projeto é de uso livre. Sinta-se à vontade para modificar e contribuir! 🚀

