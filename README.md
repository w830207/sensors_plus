# sensors

[![Flutter Community: sensors_plus](https://fluttercommunity.dev/_github/header/sensors_plus)](https://github.com/fluttercommunity/community)

[![pub package](https://img.shields.io/pub/v/sensors_plus.svg)](https://pub.dev/packages/sensors_plus)

<p class="center">
<center><a href="https://flutter.dev/docs/development/packages-and-plugins/favorites" target="_blank" rel="noreferrer noopener"><img src="../../website/static/img/flutter-favorite-badge.png" width="100" alt="build"></a></center>
</p>

A Flutter plugin to access the accelerometer, gyroscope, and magnetometer
sensors.

## Platform Support

| Android |  iOS  | MacOS |  Web  | Linux | Windows |
| :-----: | :---: | :---: | :---: | :---: | :-----: |
|   ✔️   |   ✔️   |      |   ✔️  |       |         |

## Usage

To use this plugin, add `sensors_plus` as a [dependency in your pubspec.yaml
file](https://plus.fluttercommunity.dev/docs/overview).

This will expose four classes of sensor events through four different
streams.

- `AccelerometerEvent`s describe the velocity of the device, including the
  effects of gravity. Put simply, you can use accelerometer readings to tell if
  the device is moving in a particular direction.
- `UserAccelerometerEvent`s also describe the velocity of the device, but don't
  include gravity. They can also be thought of as just the user's affect on the
  device.
- `GyroscopeEvent`s describe the rotation of the device.
- `MagnetometerEvent`s describe the ambient magnetic field surrounding the
  device. A compass is an example usage of this data.

Each of these is exposed through a `BroadcastStream`: `accelerometerEvents`,
`userAccelerometerEvents`, `gyroscopeEvents`, and `magnetometerEvents`,
respectively.

### Example

```dart
import 'package:sensors_plus/sensors_plus.dart';

accelerometerEvents.listen((AccelerometerEvent event) {
  print(event);
});
// [AccelerometerEvent (x: 0.0, y: 9.8, z: 0.0)]

userAccelerometerEvents.listen((UserAccelerometerEvent event) {
  print(event);
});
// [UserAccelerometerEvent (x: 0.0, y: 0.0, z: 0.0)]

gyroscopeEvents.listen((GyroscopeEvent event) {
  print(event);
});
// [GyroscopeEvent (x: 0.0, y: 0.0, z: 0.0)]

magnetometerEvents.listen((MagnetometerEvent event) {
  print(event);
});
// [MagnetometerEvent (x: -23.6, y: 6.2, z: -34.9)]

```

Also see the `example` subdirectory for an example application that uses the
sensor data.

Check out our website to learn more: [Plus Plugins documentation](https://plus.fluttercommunity.dev/docs/overview)

**Important:** As of January 2021, the Flutter team is no longer accepting
non-critical PRs for the original set of plugins in `flutter/plugins`, and
instead they should be submitted in this project.
[You can read more about this announcement here](https://github.com/flutter/plugins/blob/master/CONTRIBUTING.md#important-note)
as well as [in the Flutter 2 announcement blog post](https://medium.com/flutter/whats-new-in-flutter-2-0-fe8e95ecc65).
