import 'dart:async';

import 'package:flutter/material.dart';

void main() => runApp(TimerApp());

class TimerApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new TimerAppState();
  }
}

class TimerAppState extends State<TimerApp> {
  static const duration = const Duration(seconds: 1); //A unidade de tempo será de um segundo

  int secondsPassed = 0;//Armazena quantos segundos já se passaram
  bool isActive = false;//Variável para controlar o play/pause

  Timer timer;//Classe para controlar o tempo

  void handleTick() {
    if (isActive) {//Se estiver com play então continua
      setState(() {
        secondsPassed = secondsPassed + 1; //aumenta o segundo de um em um
      });
    }
  }


  @override
  Widget build(BuildContext context) {
    if (timer == null)//Se for nulo devemos instanciar o timer
      timer = Timer.periodic(duration, (Timer t) { //Cria um cronometro com callback a cada 1s
        handleTick();//Toda vez que 1s passar ele chama handleTick()
      });

    int seconds = secondsPassed % 60;
    int minutes = secondsPassed ~/ 60;
    int hours = secondsPassed ~/ (60 * 60);
    //OBS: The result of the truncating division a ~/ b is equivalent to (a / b).truncate().

    return MaterialApp(
        theme: ThemeData(
          primaryColor: Colors.black, //Define que toda AppBar será preta
        ),
        home: Scaffold(
          backgroundColor: Colors.white, //Cor de fundo da tela
          appBar: AppBar(
            title: Text("Timer"), //Texto que deve aparecer na AppBar
            actions: <Widget>[
              //Adicionando botões de ação na AppBar
              IconButton(
                icon: Icon(Icons.refresh),
                onPressed: () {
                  //Evento de clique do botão
                  setState(() {
                    secondsPassed = 0;
                  });
                },
              ),
            ],
          ),
          body: Center(
            //Para centralizar os elementos na tela
            child: Column(
              mainAxisSize: MainAxisSize.min,//Deixa a coluna com tamanho mínimo

              //Coluna pois teremos "tempo" na primeira linha e "botão" na segunda
              children: <Widget>[
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  //Centraliza no centro da linha

                  children: <Widget>[
                    CustomTextContainer(label:"Hrs", value:hours.toString().padLeft(2, '0')),
                    CustomTextContainer(label:"Min", value:minutes.toString().padLeft(2, '0')),
                    CustomTextContainer(label:"Seg", value:seconds.toString().padLeft(2, '0'))
                  ], //Armazenará o tempo
                ),
                Container(
                  width: double.infinity,
                  // Assim o botão deve ocupar toda largura da tela
                  padding: EdgeInsets.all(40),
                  //Dá um distanciamento em todas as bordas
                  child: RaisedButton(
                    color: Colors.black12,
                    padding: EdgeInsets.all(10),
                    //Dá um distanciamento para o ícone no centro do botão não ficar "colado" na borda
                    child: Icon(
                      isActive //controla qual ícone deve aparecer
                          ? Icons.pause_circle_outline
                          : Icons.play_circle_outline,
                      size: 40, //tamanho do ícone
                    ),
                    onPressed: () {
                      //evento de clique
                      setState(() {
                        isActive =
                            !isActive; //altera entre ativo e inativo (play/pause)
                      });
                    },
                  ),
                ),
              ],
            ),
          ),
        ));
  }
}

class CustomTextContainer extends StatelessWidget {//Não haverá interação com ele
  CustomTextContainer({this.label, this.value});//Recebe label e valor por parâmetro no construtor

  final String label;
  final String value;

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.symmetric(horizontal: 5),//Distanciamento fora do container
      padding: EdgeInsets.all(20),//Distanciamento dentro do container
      decoration: new BoxDecoration(
        borderRadius: new BorderRadius.circular(10), //Adiciona borda circular
        color: Colors.black87,//Define a cor
      ),
      child: Column(//Coluna pois temos valor em cima e label embaixo
        mainAxisSize: MainAxisSize.min,//Novamente definindo tamnho menor possível para coluna
        children: <Widget>[
          Text(//Texto que mostra a unidade de tempo
            '$value',//O valor será passado no construtor
            style: TextStyle(//Define o estilo do texto
              color: Colors.white,
              fontSize: 54,
              fontWeight: FontWeight.bold,
            ),
          ),
          Text(//Define o nome do container (Hora, Minuto ou Segundo?)
            '$label',
            style: TextStyle(
              color: Colors.white70,
            ),
          )
        ],
      ),
    );
  }
}
