import 'dart:async';
// In order to *not* need this ignore, consider extracting the "web" version
// of your plugin as a separate package, instead of inlining it in the same
// package as the core of your plugin.
// ignore: avoid_web_libraries_in_flutter
import 'dart:html' as html show window;

import 'package:aesthetic_dialogs/aesthetic_dialogs.dart';
import 'package:flutter/services.dart';
import 'package:flutter_web_plugins/flutter_web_plugins.dart';

/// A web implementation of the AestheticDialogs plugin.
class AestheticDialogsPlugin {
  static void registerWith(Registrar registrar) {
    final MethodChannel channel = MethodChannel(
      'aesthetic_dialogs',
      const StandardMethodCodec(),
      registrar,
    );

    final pluginInstance = AestheticDialogsPlugin();
    channel.setMethodCallHandler(pluginInstance.handleMethodCall);
  }

  /// Handles method calls over the MethodChannel of this plugin.
  /// Note: Check the "federated" architecture for a new way of doing this:
  /// https://flutter.dev/go/federated-plugins
  Future<dynamic> handleMethodCall(MethodCall call) async {
    switch (call.method) {
      case 'showDialog':
        return true;
      default:
        throw PlatformException(
          code: 'Unimplemented',
          details:
              'aesthetic_dialogs for web doesn\'t implement \'${call.method}\'',
        );
    }
  }

  /// Returns a [String] containing the version of the platform.
  Future<String> getPlatformVersion() {
    final version = html.window.navigator.userAgent;
    return Future.value(version);
  }

  /// Return Dialog
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
}
