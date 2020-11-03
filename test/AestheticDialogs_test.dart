import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:AestheticDialogs/AestheticDialogs.dart';

void main() {
  const MethodChannel channel = MethodChannel('AestheticDialogs');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await AestheticDialogs.platformVersion, '42');
  });
}
