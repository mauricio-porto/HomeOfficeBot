# HomeOfficeBot

### Sumário

- [Introdução](#Introdução)
- [Exemplo de criação do robot](#exemplo-de-criação-do-robot)
  - [Usando Selenium IDE](#usando-o-plugin-Selenium-para-gravação)
  - [Construindo o programa Java](#Construindo-o-programa-Java)
  - [Exportando o programa como um Jar](#Exportando-o-programa-como-um-Jar)
  - [Editando o crontab](#Editando-o-crontab)
- [Conclusão](#Conclusão)
    

## Introdução
A motivação para este pequeno projeto foi a necessidade de automatizar algumas tarefas em virtude das mudanças de hábito causadas pela pandemia e o Home Office.
Por isso o nome **HomeOfficeBot**.

Estou iniciando por um exemplo simples, que consiste em um robô que usa _Selenium_ para efetuar periodicamente uma atividade qualquer num site.
Imagine uma consulta de situação (Detran ?) que precise ser feita semanalmente 
ou uma atividade de registro (como algum site com premiação por acesso) que precise ser feita diariamente.

## Exemplo de criação do robot
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

Uma vez instalado, clique no ícone do plugin Selenium e no diálogo, selecione **Record a new test in a new project**.

Abrindo o Selenium:

![selenium-open](https://github.com/mauricio-porto/HomeOfficeBot/blob/develop/pictures/OpenSelenium.png "Abrindo Selenium")

Insira então a URL que pretende fazer sua gravação e pressione **Start Recording**.

Iniciando a gravação:

![selenium-start](https://github.com/mauricio-porto/HomeOfficeBot/blob/develop/pictures/SeleniumURL.png "Iniciando gravação")

Siga navegando pelo site percorrendo o fluxo que pretende que seja reproduzido automaticamente depois.

Gravando:

![selenium-start](https://github.com/mauricio-porto/HomeOfficeBot/blob/develop/pictures/SeleniumRecording.png "Gravando")

Quando tiver concluído o fluxo, pare a gravação clicando o ícone vermelho à direita da barra de controle.

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

Vou destacar que as figuras apresentadas acima não representam a navegação feita para gerar o código Java que irei apresentar a seguir.
Essas figuras foram tomadas de forma rápida com acesso a esta página no **Github** apenas para ilustrar os passos.

Para usar o browser Chrome com o Selenium será necessário baixar o programa [chromedriver](https://chromedriver.chromium.org/) e colocá-lo em um diretório qualquer.


O código Java exportado será parecido com:

```
// Generated by Selenium IDE
...
...
public class UntitledTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    public String waitForWindow(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> whNow = driver.getWindowHandles();
        Set<String> whThen = (Set<String>) vars.get("window_handles");
        if (whNow.size() > whThen.size()) {
            whNow.removeAll(whThen);
        }
        return whNow.iterator().next();
    }

    @Test
    public void untitled() {
        ...
        driver.manage().window().setSize(new Dimension(1008, 735));
        driver.findElement(By.id("requiredusuario")).sendKeys("123456");
        driver.findElement(By.id("requiredsenha")).sendKeys("123456");
        {
          WebElement dropdown = driver.findElement(By.name("requiredempresa"));
          dropdown.findElement(By.xpath("//option[. = 'RESOURCE AMERICANA LTDA']")).click();
        }
        {
          WebElement element = driver.findElement(By.name("requiredempresa"));
          Actions builder = new Actions(driver);
          builder.moveToElement(element).clickAndHold().perform();
        }
        ...
    }
```

Ao invés de um teste JUnit (identificado pelas anotações _**@Test**_) nós vamos gerar um programa executável Java.

Devemos transformar o método anotado com @Test em um método privado (vou chamá-lo de execute) e inserir o famoso método main() para invocá-lo.

O código então ficará parecido com:

```
    ...

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/mapo/bin/chromedriver"); // TODO: Troque para o caminho do seu chromedriver
        HomeOfficeBot me = new HomeOfficeBot();
        me.execute();
    }

    private void execute() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);

            options.setBinary("/usr/bin/google-chrome"); // TODO: Troque para o caminho do seu chrome

            driver = new ChromeDriver(options);
            ...
    }

```

Como esse programa Java irá rodar de forma independente, é importante ajustar as referências aos programas **chromedriver** e o próprio **chrome**.
Veja os comentários **TODO** no código acima.

![intellij-project](https://github.com/mauricio-porto/HomeOfficeBot/algo.png "Projeto em Intellij")

### Exportando o programa como um Jar

![intellij-export](https://github.com/mauricio-porto/HomeOfficeBot/algo.png "Exportando para Jar")

### Editando o crontab

![crontab-edit](https://github.com/mauricio-porto/HomeOfficeBot/algo.png "Editando o crontab")

## Conclusão