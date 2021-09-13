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

    fun setupChannel(messenger: BinaryMessenger, appCompatActivity: Activity) {
        channel = MethodChannel(messenger, "aesthetic_dialogs")
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
