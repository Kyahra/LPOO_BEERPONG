# Beer Pong

## Setup and Installation

To do.

## UML

![alt tag](https://github.com/Kyahra/LPOO1617_T3G8/blob/finalRelease/UML/firstUML.png "UML")

## Design Patterns

**Singleton** – O Sigleton foi utilizado quando em situações em que é necessário aceder multiplas vezes a uma mesma classe, sendo esta instanciada apenas uma vez. Esta abordagem permitiu que as classes Main Activity, Game Controller e Game Model apenas fossem acedidas pela função getInstance(), permitindo assim um maior controlo sobre a instância.

**Strategy** – O pattern Strategy foi usado para definir um modelo específico ao nível do Game Model e do Game View. Consoante o nível pretendido é implementada  uma diferente estratégia de jogo (easy, medium ou hard). Este mesmo princípio é aplicado relativamente ao Game Model. Os diferentes objetos provêm de Entity Model e são apenas especificados consoante a necessidade.

**Factory Method** – O Factory Method é implementado devido à dificuldade em establecer diversos padrões de desenho. Desta forma, define-se um padrão em Entity View que irá permitir tratar as diversas sprites, sendo estas apenas especificadas num momento posterior.

## Uma Análise Global

Ao longo do projeto fomos confrontados com as mais diversas situações. Tudo começou com a simples configuração do IDE que viríamos a utilizar, o Android Studio: problemas ao nível do SDK, correr os projeto-exemplo, configurar o LibGdx, etc. Isto tudo apenas na primeira semana. Como é óbvio não vamos enumerar todos os problemas que fomos tendo ao nível da implemetação, mas óbvio também é que poucos não foram. Desde início que tinhamos bem presente qual era o resultado final que pretendíamos atingir, por isso apesar de ter sido tudo implementado incrementalmente o código sempre foi genérico o suficiente para não termos qualquer problema aquando a implementação de novas funcionalidades. Apesar de termos tido cerca de 2 meses para implementar todo o projeto, foi bastante dificíl gerir o tempo e dedicar horas semanais constantes, de modo que a repartição do esforço tenha sido constante ao longo das semanas. Isto porque surgiam constantemente testes ou prazos de entrega que exigiam uma maior prontidão, sendo que este era sempre o projeto que ficava para segundo plano devido à distância temporal relativamente ao prazo de entrega. Assim sendo, grande parte do projeto foi implementado nas últimas semanas. Mas apesar da pressão devido ao pouco tempo que tinhamos, esta gestão de tempo revelou-se bastante vantajosa ao nível da distribuição de tarefas e da cooperação entre os dois elementos do grupo. Ao nível de conhecimento adquirido também consideramos que o panorama geral se revelou muito proveitoso, ficamos com bastantes conceitos ao nível de programação orientada a objetos, assim como todas as boas práticas que se encontram subjacentes. Este segundo trabalho também conseguiu ter um estímulo adicional, o facto de estarmos a trabalhar em Android faz-nos ver uma aplicação direta dos conceitos leccionados assim como ter um feedback das outras pessoas bastante positivo em relação ao que estamos a desenvolver. Podemos publicar o nosso trabalho na PlayStore e qualquer um consegue ter acesso ao nosso jogo. Relativamente à implementação optamos por desenvolver toda a lógica do jogo em LibGdx, pois a utilização de uma framework traz-nos muitas vantagens ao nível de uma boa implementação, permitindo recorrer a um conjunto de ferramentas que terímos que implementar de raiz sem qualquer necessidade. Nesta sequência também utilizamos Box2d a fim de implementar a física do jogo. Ao nível do layout e dos diferentes menus optamos por utilizar Android nativo. Isto permitiu-nos programar de facto em Android, e não apenas ter um projeto que fosse compatível, além disso também nos permitiu utilizar um conjunto de ferramentas próprias de Android.

## Manual do Utilizador

| Screenshoots  | Intruções |
| ------------- | ------------- |
| ![alt tag](https://github.com/Kyahra/LPOO1617_T3G8/blob/finalRelease/ImagesREADME/mainMenu.png "main menu")  | Este é o menu pricipal. É possível abrir um Menu de Ajuda, com algumas indicações acerca do jogo. No ícone Definições é possível       ajustar algumas definições, como o som ou o volume. Além disso será possível partilhar os resultados no Facebook. Por fim, basta clicar no botão Play para iniciar o jogo. Para sair do jogo, o utilizador deve clicar no botão Exit |
| ![alt tag](https://github.com/Kyahra/LPOO1617_T3G8/blob/finalRelease/ImagesREADME/helpmenu.png "help menu")  | Se o utilizador clicar no ícone de ajuda é-lhe mostrado um pequeno texto com um resumo daquilo em que consiste o jogo. Basicamente, o jogador deve arrastar o dedo por cima da bola na direção que ele pretende que tome, quanto maior for o movimento maior será a velocidade da bola. O jogador também deve ter em atenção que quantas mais colisões a bola fizer maior será a sua pontuação. A bola pode bater em qualquer local, nas paredes, no chão, ou até mesmo na mesa (nos níveis em existe mesa). |
| ![alt tag](https://github.com/Kyahra/LPOO1617_T3G8/blob/finalRelease/ImagesREADME/settingsmenu.png "settings menu")  | Após clicar no ícone das definições o utilizador pode alterar as definições do jogo. É possível ativar/desativar a música de fundo e o som produzido pelos botões. |
| ![alt tag](https://github.com/Kyahra/LPOO1617_T3G8/blob/finalRelease/ImagesREADME/levelMenu.png "level menu")  | Após clicar no botão Play é possível escolher o nível que se pretende: Easy, Medium ou Hard. Easy, tal como o próprio nome indica é o nível mais fácil, trata-se apenas de acertar com a bola, sem qualquer obstáculo ou condicionante. A bola pode facil uma trajetória limpa. Medium já é um pouco mais complicado, existe uma mesa no entre a bola e o copo, dificultando um bocadinho fazer com que a bola acerte no copo. Por último, Hard é o nível mais complexo, pois além da mesa, o ecrã roda ligeiramente, sendo mais difícil posicionar a bola no ecrã. |
| ![alt tag](https://github.com/Kyahra/LPOO1617_T3G8/blob/finalRelease/ImagesREADME/beerpongame.png "game")  | Quando o utilizador escolhe o nível pretendido é aberta a janela do jogo. O seu objetivo é fazer com que a bola acerte no copo vermelho, para isso a bola não precisa de ir diretamente para o copo, pode bater em diversos locais, fazendo com que o jogador aumente a sua pontuação. É possível iniciar uma nova tentativa ao clicar na setinha para trás. |
| ![alt tag](https://github.com/Kyahra/LPOO1617_T3G8/blob/finalRelease/ImagesREADME/scoremenu.png "score menu")  | Quando acaba uma jogada é calculada a pontuação referente a essa mesma jogada. Neste calculo é tido em conta se o jogador acertou no interior do copo e o número de colisões da bola durante a jogada. Essa pontuação é apresentada neste menu. O utilizador pode partilhar a pontuação no Facebook, sendo que basta clicar em partilhar. Para voltar ao menu Principal basta clicar no botão Menu.|








[![BCH compliance](https://bettercodehub.com/edge/badge/Kyahra/LPOO1617_T3G8?token=b95cdade23d33bd80209bd0cdcb8c574b0a21573)](https://bettercodehub.com/)


