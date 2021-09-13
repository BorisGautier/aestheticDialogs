import 'dart:async';

import 'package:flutter/services.dart';

enum DialogType { SUCCESS, ERROR, WARNING, INFO }

enum DialogGravity { TOP, CENTER, BOTTOM }

enum DialogStyle { RAINBOW, FLAT, CONNECTIFY, TOASTER, DRAKE, EMOJI, EMOTION }

enum DialogAnimation {
  DEFAULT,
  SLIDE_UP,
  SLIDE_DOWN,
  SLIDE_LEFT,
  SLIDE_RIGHT,
  SWIPE_LEFT,
  SWIPE_RIGHT,
  IN_OUT,
  CARD,
  SHRINK,
  SPLIT,
  DIAGONAL,
  SPIN,
  WINDMILL,
  FADE,
  ZOOM
}

class AestheticDialogs {
  static const MethodChannel _channel = const MethodChannel('AestheticDialogs');

  static Future<bool?> showDialog({
    required String title,
    required String message,
    required bool cancelable,
    required bool darkMode,
    int duration = 3000,
    DialogStyle? dialogStyle,
    DialogAnimation? dialogAnimation,
    DialogGravity? dialogGravity,
    DialogType? dialogType,
  }) async {
    // Choose dialog position
    String gravity = "center";
    if (dialogGravity == DialogGravity.CENTER) {
      gravity = "center";
    } else if (dialogGravity == DialogGravity.TOP) {
      gravity = "top";
    } else if (dialogGravity == DialogGravity.BOTTOM) {
      gravity = "bottom";
    }

    //choose dialog animation
    String animation = "shrink";
    if (dialogAnimation == DialogAnimation.CARD) {
      animation = "card";
    } else if (dialogAnimation == DialogAnimation.DEFAULT) {
      animation = "default";
    } else if (dialogAnimation == DialogAnimation.DIAGONAL) {
      animation = "diagonal";
    } else if (dialogAnimation == DialogAnimation.FADE) {
      animation = "fade";
    } else if (dialogAnimation == DialogAnimation.IN_OUT) {
      animation = "in_out";
    } else if (dialogAnimation == DialogAnimation.SHRINK) {
      animation = "shrink";
    } else if (dialogAnimation == DialogAnimation.SLIDE_DOWN) {
      animation = "slide_down";
    } else if (dialogAnimation == DialogAnimation.SLIDE_LEFT) {
      animation = "slide_left";
    } else if (dialogAnimation == DialogAnimation.SLIDE_RIGHT) {
      animation = "slide_right";
    } else if (dialogAnimation == DialogAnimation.SLIDE_UP) {
      animation = "slide_up";
    } else if (dialogAnimation == DialogAnimation.SPIN) {
      animation = "spin";
    } else if (dialogAnimation == DialogAnimation.SPLIT) {
      animation = "split";
    } else if (dialogAnimation == DialogAnimation.SWIPE_LEFT) {
      animation = "swipe_left";
    } else if (dialogAnimation == DialogAnimation.SWIPE_RIGHT) {
      animation = "swipe_right";
    } else if (dialogAnimation == DialogAnimation.WINDMILL) {
      animation = "windmill";
    } else if (dialogAnimation == DialogAnimation.ZOOM) {
      animation = "zoom";
    }

    // choose dialog type
    String type = "success";
    if (dialogType == DialogType.SUCCESS) {
      type = "success";
    } else if (dialogType == DialogType.INFO) {
      type = "info";
    } else if (dialogType == DialogType.WARNING) {
      type = "warning";
    } else if (dialogType == DialogType.ERROR) {
      type = "error";
    }

    //choose dialog style
    String style = "flat";
    if (dialogStyle == DialogStyle.FLAT) {
      style = "flat";
    } else if (dialogStyle == DialogStyle.CONNECTIFY) {
      style = "connectify";
    } else if (dialogStyle == DialogStyle.DRAKE) {
      style = "drake";
    } else if (dialogStyle == DialogStyle.EMOJI) {
      style = "emoji";
    } else if (dialogStyle == DialogStyle.RAINBOW) {
      style = "rainbow";
    } else if (dialogStyle == DialogStyle.TOASTER) {
      style = "toaster";
    }

    final Map<String, dynamic> params = <String, dynamic>{
      'title': title,
      'message': message,
      'cancelable': cancelable,
      'darkMode': darkMode,
      'gravity': gravity,
      'style': style,
      'type': type,
      'animation': animation,
      'duration': duration,
    };

    // bool res = await
    return _channel.invokeMethod('showDialog', params);
  }
}
