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
        title: "My Dialog",
        message: "Hello!!!",
        cancelable: true,
        darkMode: false,
        dialogAnimation: DialogAnimation.IN_OUT,
        dialogGravity: DialogGravity.CENTER,
        dialogStyle: DialogStyle.FLASH,
        dialogType: DialogType.SUCCESS,
        duration: 3000);
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: const Text('AestheticDialogs Flutter'),
        ),
        body: Center(
          child: ElevatedButton(
            onPressed: buildDialog,
            child: Text("Open Dialog"),
          ),
        ),
      ),
    );
  }
}
