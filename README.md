# T6 - Lista de Compras Inteligente
Marcos Cardoso Vendrame - 790725 <br/>
Carlos Eduardo Nascimento dos Santos - 791029

## Sobre o Programa
### 1. Descrição
O programa tem como objetivo auxiliar na elaboração de uma lista de compras com uma propriedade desejada. Dessa forma, é possível declarar os produtos existentes em cada uma das lojas pesquisadas e será organizada a lista automaticamente de produtos a serem comprados em cada loja, de acordo com as seguintes propriedade especificada:
- **Economia**: Serão selecionados os produtos nas lojas em que possuem melhor custo-benefício, com quantidade suficiente para suprir a solicitação, podendo exceder a quantidade desejada caso não possua a medida exata desejada.
- **Caro**: Serão selecionados os produtos nas lojas em que possuem pior custo-benefício, com quantidade suficiente para suprir a solicitação, podendo exceder a quantidade desejada caso não possua a medida exata desejada.

Após criada as listas de produtos por loja, será gerado um arquivo HTML exibindo uma página de visualização com as seguintes informações:
- **Nome da Lista**;
- **Propriedade**: Propriedade desejada da lista;
- **Lista de Lojas**:
  - **Nome da Loja**;
  - **Propriedades**:
    - **Produto**: Nome do Produto;
    - **Medida**: Quantidade e Unidade de Medida ofertada por unidade do Produto na Loja;
    - **Quantidade**: Quantas Unidade do Produto a serem compradas;
    - **Sobra**: Quantidade excedente do Produto em relação ao que havia sido desejado;
    - **Valor Unitário**;
    - **Valor Total**: Valor Total do Produto;
  - **Total**: Valor Total da Compra na Loja;
- **Valor Total**: Valor Total da Lista de Compras.

### 2. Como Descrever a Linguagem 
- Faça primeiramente a declaração das lojas, com a palavra reservada "LOJAS";
- Declare o nome da loja com: ```NOME: <Nome da Loja>```, inserindo uma quebra de linha após o término da declaração;
- Declare a lista de produtos da loja de acordo com o seguinte padrão:
  
  ```
  PRODUTO: <Nome do Produto>
  MEDIDA: <Quantidade e Unidade de Medida Ofertada> 
  PREÇO: <Valor do Produto>
  ```
  
  - **Observações**:
    - Após a declaração do nome do produto, insira uma quebra de linha;
    - Unidades de Medida disponíveis: UN, KG e L;
    - Digite o preço somente numérico e com "," (vírgula) para indicar casa decimal.
      
- Faça a declaração da lista, com a palavra reservada "LISTA";
- Declare o nome da lista com: ```NOME: <Nome da Lista>```, inserindo uma quebra de linha após o término da declaração;
- Declare a propriedade da lista com: ```PROPRIEDADE: <Propriedade da Lista>```, inserindo uma quebra de linha após o término da declaração;
- Declare a lista de produtos desejados de acordo com o seguinte padrão:
  ```
  PRODUTO: <Nome do Produto>
  QUANTIDADE: <Quantidade e Unidade de Medida Ofertada> 
  ```
- Finalize usando a palavra reservada "FIM".
<div align="center"> 
  
![image](https://github.com/mvtehutd/lista-compras/assets/100847921/b2a8af3d-df57-494f-a26f-6fc6f8137cee)

 </div>
<p align="center"><i><b>Imagem 1:</b> Exemplo de Arquivo de Entrada</i></p>

 <div align="center"> 
   
   ![image](https://github.com/mvtehutd/lista-compras/assets/100847921/44ce0f51-0ecc-4f3c-98cc-c27868d58877)
   
 </div>

<p align="center"><i><b>Imagem 2:</b> Exemplo de Arquivo de Saída</i></p>

### 3. Regras Semânticas
- Não é permitido declarar um produto duas vezes na mesma loja ou lista;
- Não é permitido declarar um produto com unidades de medida diferentes entre lojas e entre lojas e lista;
- Não é permitido declarar duas lojas com mesmo nome;
- Não é permitido declarar uma unidade (UN) como número real (decimal);
- Não é permitido declarar um produto na lista que não exista em nenhuma loja.

**Observação:** Há uma pasta no diretório raíz com os casos de teste para verificação de erros e demonstração de funcionamento do programa.

**Vídeo Demonstrativo:**

## Como Utilizar o Programa
### 1.	Programas Necessários
Para compilar e executar o programa, é necessário ter instalado na máquina o Java, o Maven e o Antlr4. 
Nas compilações e execuções realizadas nesse trabalho, as versões utilizadas para esses softwares foram:

- Java (JDK 17.0.1 e JDK 17.0.6)
- Apache Maven 3.9.2
- ANTLR 4.11.0

É possível que outras versões também sejam compatíveis, então verifique a compatibilidade caso já os tenha instalado na máquina.

### 2.	Compilação e Execução do Programa
Para compilar o código, acesse o diretório em que foi salvo o projeto em sua máquina e execute o comando ```mvn package``` no terminal para que o Maven crie os arquivos e o programa com extensão *.jar* necessários.
  
   ![image](https://github.com/mvtehutd/lista-compras/assets/100847921/efe9f60c-9db2-4fb6-806a-2134867554ba)

<p align="center"><i><b>Imagem 1:</b> Realizando a Compilação</i></p>

![image](https://github.com/mvtehutd/lista-compras/assets/100847921/f8c462de-e763-4f49-a1f3-e843aa6a0358)

<p align="center"><i><b>Imagem 2:</b> Mensagem Indicando Sucesso na Compilação</i></p>

Com a compilação finalizada, é possível executar o programa. Para isso, o comando a ser realizado é:

```java -jar <caminho do compilador> <arquivo de entrada> <arquivo de saída>```

  Sendo que: </br>
-	```<caminho do compilador>``` é o caminho completo até o arquivo de extensão *.jar* criado, lembrando de escolher o com as dependências. Ele está localizado na pasta target:
 
![image](https://github.com/mvtehutd/lista-compras/assets/100847921/947d9e47-85ac-4040-8ab4-94246fed4f8f)

<p align="center"><i><b>Imagem 3:</b> Localizando o Compilador no Projeto</i></p>

-	```<arquivo de entrada>``` é o caminho completo até o arquivo de extensão *.txt* com o algoritmo a ser analisado.

 ![image](https://github.com/mvtehutd/lista-compras/assets/100847921/b2a8af3d-df57-494f-a26f-6fc6f8137cee)
 
<p align="center"><i><b>Imagem 4:</b> Exemplo de Arquivo de Entrada</i></p>

-	```<arquivo de saída>``` é o caminho completo até o arquivo de extensão *.html* na qual serão salvos os resultados da análise.

 ![image](https://github.com/mvtehutd/lista-compras/assets/100847921/b7259e4f-acb8-49fd-ad0a-05c16e79e41f)

<p align="center"><i><b>Imagem 5:</b> Exemplo de Arquivo de Saída</i></p>

Exemplo de como o analisador deve rodar:
```
java -jar c:\compilador\meu-compilador.jar c:\casos-de-teste\arquivo1.txt c:\temp\saida.html
```
