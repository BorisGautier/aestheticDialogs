package com.example.aestheticdialogs

import android.app.Activity
import android.view.Gravity
import com.thecode.aestheticdialogs.*
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler

class MethodCallHandlerImpl(var activity: Activity) : MethodCallHandler {

    private lateinit var mAestheticDialog: AestheticDialog


    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when(call.method) {
            "showDialog" -> {
                var title = call.argument<Any>("title").toString()
                var message = call.argument<Any>("message").toString()
                var cancelable = call.argument<Boolean>("cancelable")
                var darkMode = call.argument<Boolean>("darkMode")
                var gravity = call.argument<Any>("gravity").toString()
                var animation = call.argument<Any>("animation").toString()
                var dialogType = call.argument<Any>("type").toString()
                var dialogStyle = call.argument<Any>("style").toString()
                var duration = call.argument<Int>("duration")
                val mGravity: Int
                val mAnimation: DialogAnimation
                val mDialogType: DialogType
                val mDialogStyle: DialogStyle

                mGravity = when (gravity) {
                    "top" -> Gravity.TOP
                    "center" -> Gravity.CENTER
                    "bottom" -> Gravity.BOTTOM
                    else -> Gravity.BOTTOM
                }

                mAnimation = when (animation) {
                    "default" -> DialogAnimation.DEFAULT
                    "slide_up" -> DialogAnimation.SLIDE_UP
                    "slide_down" -> DialogAnimation.SLIDE_DOWN
                    "slide_right" -> DialogAnimation.SLIDE_RIGHT
                    "slide_left" -> DialogAnimation.SLIDE_LEFT
                    "swipe_left" -> DialogAnimation.SWIPE_LEFT
                    "swipe_right" -> DialogAnimation.SWIPE_RIGHT
                    "split" -> DialogAnimation.SPLIT
                    "spin" -> DialogAnimation.SPIN
                    "in_out" -> DialogAnimation.IN_OUT
                    "card" -> DialogAnimation.CARD
                    "diagonal" -> DialogAnimation.DIAGONAL
                    "windmill" -> DialogAnimation.WINDMILL
                    "fade" -> DialogAnimation.FADE
                    "zoom" -> DialogAnimation.ZOOM
                    "shrink" -> DialogAnimation.SHRINK
                    else -> DialogAnimation.SHRINK
                }

                mDialogType = when (dialogType) {
                    "success" -> DialogType.SUCCESS
                    "info" -> DialogType.INFO
                    "warning" -> DialogType.WARNING
                    "error" -> DialogType.ERROR
                    else -> DialogType.SUCCESS
                }

                mDialogStyle = when (dialogStyle) {
                    "connectify" -> DialogStyle.CONNECTIFY
                    "rainbow" -> DialogStyle.RAINBOW
                    "flat" -> DialogStyle.FLAT
                    "flash" -> DialogStyle.FLASH
                    "toaster" -> DialogStyle.TOASTER
                    "drake" -> DialogStyle.DRAKE
                    "emoji" -> DialogStyle.EMOJI
                    "emotion" -> DialogStyle.EMOTION
                    else -> DialogStyle.FLAT
                }

                mAestheticDialog = AestheticDialog.Builder(activity, mDialogStyle, mDialogType)
                        .setTitle(title)
                        .setMessage(message)
                        .setCancelable(cancelable!!)
                        .setDarkMode(darkMode!!)
                        .setGravity(mGravity)
                        .setAnimation(mAnimation)
                        .setDuration(duration!!)
                        .setOnClickListener(object : OnDialogClickListener {
                            override fun onClick(dialog: AestheticDialog.Builder) {
                                dialog.dismiss()
                                // actions...
                            }
                        })
                        .show()

                result.success(true)
            }

            else -> result.notImplemented()
        }

    }
}
