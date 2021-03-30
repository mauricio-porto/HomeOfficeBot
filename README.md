# HomeOfficeBot

### Sumário

- [Introdução](#Introdução)
- [Exemplo](#exemplo-para-fazer-registro-diário-de-atividades)
  - [Usando Selenium IDE](#usando-o-plugin-Selenium-para-gravação)

## Introdução
A motivação para este pequeno projeto foi a necessidade de automatizar algumas tarefas em virtude das mudanças de hábito causadas pela pandemia e o Home Office.
Por isso o nome **HomeOfficeBot**.

Estou iniciando por um exemplo simples, que consiste em um robô que usa _Selenium_ para efetuar periodicamente uma atividade qualquer num site.
Imagine uma consulta de situação (Detran ?) que precise ser feita semanalmente 
ou uma atividade de registro (como algum site com premiação por acesso) que precise ser feita diariamente.

## Exemplo para fazer registro diário de atividades
Este primeiro exemplo é uma forma de automatizar uma necessidade de registro diário de atividades através de um site qualquer.
Ele foi desenvolvido em ambiente Linux, mas à exceção do uso do _crontab_ para agendamento das execuções, acredito que todo o resto seja similar em ambiente Windows.

Para isso, os passos necessários são:

1. Registrar os passos de navegação no site através do uso do plugin _Selenium_ IDE no browser;
2. Exportar esses passos em linguagem Java, para uso posterior pelo programa que irá repetí-los periodicamente;
3. Construir o programa Java baseado no passo anterior, fazendo os ajustes necessários;
4. Gerar o executável Java em formato JAR que será executado periodicamente;
5. Editar a tabela de crontab para executar o programa nos períodos desejados.

### Usando o plugin Selenium para gravação

Se você não tem ainda, instale o plugin **Selenium IDE** no seu browser.

Uma vez instalado, clique no ícone do plugin Selenium.

Abrindo o Selenium:

![selenium-open](https://github.com/mauricio-porto/HomeOfficeBot/blob/develop/pictures/OpenSelenium.png "Abrindo Selenium")

Insira a URL que pretende fazer sua gravação e pressione **Start Recording**.

Iniciando a gravação:

![selenium-start](https://github.com/mauricio-porto/HomeOfficeBot/blob/develop/pictures/SeleniumURL.png "Iniciando gravação")

Siga navegando pelo site percorrendo o fluxo que pretende que seja reproduzido automaticamente depois.

Gravando:

![selenium-start](https://github.com/mauricio-porto/HomeOfficeBot/blob/develop/pictures/SeleniumRecording.png "Gravando")

Quando tiver concluído o fluxo, pare a gravação.

Parando a gravação:

![selenium-start](https://github.com/mauricio-porto/HomeOfficeBot/blob/develop/pictures/SeleniumToStop.png "Parando a gravação")

Pode dar um nome ao teste quando pedido ou deixar em branco.
Então escolha no menu suspenso (3 pontos verticais) ao lado do nome do teste a opção de exportar.

Selecionando exportação:

![selenium-start](https://github.com/mauricio-porto/HomeOfficeBot/blob/develop/pictures/SeleniumMenuExport.png "Parando a gravação")

No menu de opções de exportação, escolha a linguagem Java.
A seguir defina o local e o nome do arquivo a exportar.

Exportando a gravação em linguagem Java:

![selenium-start](https://github.com/mauricio-porto/HomeOfficeBot/blob/develop/pictures/SeleniumExportToJava.png "Exportando em Java")

### Construindo o programa Java

![intellij-project](https://github.com/mauricio-porto/HomeOfficeBot/algo.png "Projeto em Intellij")

### Exportando o programa como um Jar

![intellij-export](https://github.com/mauricio-porto/HomeOfficeBot/algo.png "Exportando para Jar")

### Editando o crontab

![crontab-edit](https://github.com/mauricio-porto/HomeOfficeBot/algo.png "Editando o crontab")
