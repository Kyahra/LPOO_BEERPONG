# LPOO-Final-Project

## Architecture Design

### UML

![alt text](https://github.com/dolfander/LPOO-Final-Project/blob/master/ImagesREADME/uml.png "Logo Title Text 1")

O nosso jogo vai ter como suporte a framework libGDX, assim sendo, vamos utilizar grande parte das suas ferramentas e funcionalidades. No nosso UML especificamos algumas destas ferramentas, nomeadamente as que se encontram relacionadas com o ecrã e a sua manipulação (Screen e BaseScreen). Além disso também especificamos as principais classes que serão utilizadas para a lógica do jogo (Ball e Cup). A classe Ball será responsável pelo que está relacionado com o lançamento da bola (posição da bola no ínicio, no fim e ao longo do percurso, calculo de trajetória, etc) enquanto que a classe Cup será o alvo a atingir pela bola sendo responsável aferir se a bola acertou devidamente no copo.

### Behavioural Aspects

![alt text](https://github.com/dolfander/LPOO-Final-Project/blob/master/ImagesREADME/bahaviour.png "Logo Title Text 2")

### Design Patterns

**Singleton** – tal como já está especificado no UML, o nosso jogo deverá utilizar o Singleton para
a Classe jogo. Esta abordagem irá resolver o facto de esta classe ser acedida várias vezes ao
longo do código, sendo somente uma vez instanciada

**Template Method** – este design pattern vai ser muito útil ao nível do ciclo do jogo. Podemos
implementar pequenas variâncias sem alterar o algoritmo responsável pelo ciclo do jogo. Será
útil para implementar outros modos de jogo (por exemplo o Drunken Mode).

**Double Buffer** – como em qualquer jogo iremos precisar de transições entre imagens o mais
suave possível, o double buffer é ideal para obter o efeito pretendido aquando o lançamento
da bola.

## GUI Design

Este será o menu principal. Será possível abrir um Menu de Ajuda, com algumas indicações acerca do jogo. No ícone Definições será possível ajustar algumas definições, como o som ou o volume. Além disso será possível partilhar os resultados nas redes socias. Por fim, basta clicar no botão Play para iniciar o jogo.
