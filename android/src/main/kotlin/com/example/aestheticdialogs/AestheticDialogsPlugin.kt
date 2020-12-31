package com.example.aestheticdialogs




import android.app.Activity
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry.Registrar


/** AestheticDialogsPlugin */
class AestheticDialogsPlugin: FlutterPlugin,ActivityAware {

    private var channel : MethodChannel? = null

    private var pluginBinding: FlutterPluginBinding? = null
    private val activityBinding: ActivityPluginBinding? = null

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val plugin = AestheticDialogsPlugin()
            val activity = registrar.activity()
            plugin.setupChannel(registrar.messenger(), activity)
        }
    }

    fun AestheticDialogsPlugin() {}

    override fun onAttachedToEngine(binding: FlutterPluginBinding) {
        pluginBinding = binding
    }

    override fun onDetachedFromEngine(binding: FlutterPluginBinding) {
        teardownChannel()
    }

   /* override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        setupChannel(binding.binaryMessenger, binding.applicationContext)
    }

    override fun onDetachedFromEngine(p0: FlutterPlugin.FlutterPluginBinding) {
        teardownChannel();
    }*/

    fun setupChannel(messenger: BinaryMessenger, appCompatActivity: Activity) {
        channel = MethodChannel(messenger, "AestheticDialogs")
        val handler = MethodCallHandlerImpl(appCompatActivity)
        channel?.setMethodCallHandler(handler)
    }

    private fun teardownChannel() {
        channel?.setMethodCallHandler(null)
        channel = null
    }

    override fun onDetachedFromActivity() {
        teardownChannel()
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        TODO("Not yet implemented")
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        setupChannel(pluginBinding?.binaryMessenger!!, binding.activity)
    }

    override fun onDetachedFromActivityForConfigChanges() {
        TODO("Not yet implemented")
    }

}

/*import android.app.Activity
import android.view.Gravity
import androidx.annotation.NonNull
import com.thecode.aestheticdialogs.*
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar


/** PluginCodelabPlugin  */
class AestheticDialogsPlugin: FlutterPlugin,ActivityAware,MethodCallHandler {
    private var channel: MethodChannel? = null
    private var synth: Synth? = null
    private val activityBinding: ActivityPluginBinding? = null
    private var pluginBinding: FlutterPluginBinding? = null
    private lateinit var mAestheticDialog: AestheticDialog
    private lateinit var activity: Activity
    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPluginBinding) {
        setup(activity, flutterPluginBinding.getBinaryMessenger())
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method.equals("showDialog")) {
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
            val mAnimation: Int
            val mDialogType: String
            val mDialogStyle: String

            mGravity = when (gravity) {
                "top" -> Gravity.TOP
                "center" -> Gravity.CENTER
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
        }  else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPluginBinding?) {
        channel.setMethodCallHandler(null)
        channel = null
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        setup(binding.activity,pluginBinding?.binaryMessenger!!)
    }

    override fun onDetachedFromActivity() {
        channel.setMethodCallHandler(null)
        channel = null
    }

    companion object {
        private const val channelName = "AestheticDialogs"
        private fun setup(appCompatActivity: Activity, binaryMessenger: BinaryMessenger) {
            plugin.channel = MethodChannel(binaryMessenger, channelName)
            plugin.channel.setMethodCallHandler(appCompatActivity)
            plugin.synth = Synth()
            plugin.synth.start()
        }
    }
}*/
