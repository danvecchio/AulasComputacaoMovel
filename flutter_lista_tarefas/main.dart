import 'package:flutter/material.dart';

void main() => runApp(new TodoApp());

class TodoApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(title: 'Lista de Tarefas', home: new TodoList());
  }
}

class TodoList extends StatefulWidget {
  @override
  createState() => new TodoListState();
}

class TodoListState extends State<TodoList> {
  List<String> _todoItems = [];

  Map<int, bool> values = new Map<int, bool>();

//Adiciona item na lista
  void _addTodoItem(String todoText) {
    setState(() => _todoItems.add(todoText));
  }

  void _removeTodoItem(int index) {
    setState(() {
      _todoItems.removeAt(index);
      values[index] = false;
    });
  }

//Mostra Dialogo de Alerta para o usuário decidir se quer mesmo remover
  void _promptRemoveTodoItem(int index) {
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return new AlertDialog(
              title: new Text('Remover "${_todoItems[index]}"?'),
              actions: <Widget>[
                new FlatButton(
                    child: new Text('CANCELAR'),
                    //pop fecha o dialog
                    onPressed: () => Navigator.of(context).pop()),
                new FlatButton(
                    child: new Text('REMOVER'),
                    onPressed: () {
                      //invoca o método para remover um item da lista
                      _removeTodoItem(index);
                      //pop fecha o dialog
                      Navigator.of(context).pop();
                    })
              ]);
        });
  }

// Constrói a lista inteira
  Widget _buildTodoList() {
    return new ListView.builder(
      itemBuilder: (context, index) {
        //o item build é chamado uma vez para item da lista. Por isso temos que cuidar do index da nossa lista
        if (index < _todoItems.length) {
          return _buildTodoItem(_todoItems[index], index);
        }
      },
    );
  }

  // Constroi um único Todo Item
  Widget _buildTodoItem(String todoText, int index) {
    return new Card(
      child: ListTile(
        title: Text(todoText,
            style: values[index] == true
                ? TextStyle(decoration: TextDecoration.lineThrough)
                : null),        leading: new Checkbox(
            value: values[index] ?? false, //Se for nulo, então o valor é falso
            onChanged: (bool value) {
              setState(() {
                values[index] =
                    value; //Toda vez que o checkbox é clicado salvamos o estado (true ou false)
              });
            }),
        trailing: IconButton(
          icon: new Icon(Icons.delete),
          onPressed: () => _promptRemoveTodoItem(index),
        ),
        selected: values[index] ?? false,//Se for nulo, não ta selecionado
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(title: new Text('Lista de Tarefas')),
      body: _buildTodoList(),
      floatingActionButton: new FloatingActionButton(
          onPressed: _pushAddTodoScreen,
          tooltip: 'Adicionar tarefa',
          child: new Icon(Icons.add)),
    );
  }

  //Tela para adicionar tarefa
  void _pushAddTodoScreen() {
    // Coloca esta página na pilha
    Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
      return new Scaffold(
          appBar: new AppBar(title: new Text('Adicione uma nova tarefa')),
          body: new TextField(
            autofocus: true,
            //Da foco automático ao campo de texto, repare que o teclado vai ser mostrado automaticamente
            onSubmitted: (val) {
              _addTodoItem(val); //Envia o valor do TextField por parâmetro
              Navigator.pop(context); // Fecha a tela de adição de tarefas
            },
            decoration: new InputDecoration(
                hintText: 'Digite algo a ser feito...', //texto de ajuda
                contentPadding: const EdgeInsets.all(16.0)),
          ));
    }));
  }
}
