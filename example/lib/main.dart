import 'dart:async';

import 'package:aesthetic_dialogs/aesthetic_dialogs.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  Future<void> buildDialog() async {
    AestheticDialogs.showDialog(
        title: "Ma Boite",
        message: "Bonjour",
        cancelable: true,
        darkMode: false,
        dialogAnimation: DialogAnimation.IN_OUT,
        dialogGravity: DialogGravity.CENTER,
        dialogStyle: DialogStyle.EMOJI,
        dialogType: DialogType.INFO,
        duration: 5000);
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: const Text('AestheticDialogs Example Flutter'),
        ),
        body: Center(
          child: RaisedButton(
            onPressed: buildDialog,
            child: Text("Open Dialog"),
          ),
        ),
      ),
    );
  }
}
