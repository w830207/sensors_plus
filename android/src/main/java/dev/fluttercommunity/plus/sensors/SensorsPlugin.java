// Copyright 2017 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package dev.fluttercommunity.plus.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;

/** SensorsPlugin */
public class SensorsPlugin implements FlutterPlugin {
  private static final String ACCELEROMETER_CHANNEL_NAME =
      "dev.fluttercommunity.plus/sensors/accelerometer";
  private static final String GYROSCOPE_CHANNEL_NAME =
      "dev.fluttercommunity.plus/sensors/gyroscope";
  private static final String USER_ACCELEROMETER_CHANNEL_NAME =
      "dev.fluttercommunity.plus/sensors/user_accel";
  private static final String MAGNETOMETER_CHANNEL_NAME =
      "dev.fluttercommunity.plus/sensors/magnetometer";

  private EventChannel accelerometerChannel;
  private EventChannel userAccelChannel;
  private EventChannel gyroscopeChannel;
  private EventChannel magnetometerChannel;

  @Override
  public void onAttachedToEngine(FlutterPluginBinding binding) {
    final Context context = binding.getApplicationContext();
    setupEventChannels(context, binding.getBinaryMessenger());
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    teardownEventChannels();
  }

  private void setupEventChannels(Context context, BinaryMessenger messenger) {
    accelerometerChannel = new EventChannel(messenger, ACCELEROMETER_CHANNEL_NAME);
    final StreamHandlerImpl accelerationStreamHandler =
        new StreamHandlerImpl(
            (SensorManager) context.getSystemService(Context.SENSOR_SERVICE),
            Sensor.TYPE_ACCELEROMETER);
    accelerometerChannel.setStreamHandler(accelerationStreamHandler);

    userAccelChannel = new EventChannel(messenger, USER_ACCELEROMETER_CHANNEL_NAME);
    final StreamHandlerImpl linearAccelerationStreamHandler =
        new StreamHandlerImpl(
            (SensorManager) context.getSystemService(Context.SENSOR_SERVICE),
            Sensor.TYPE_LINEAR_ACCELERATION);
    userAccelChannel.setStreamHandler(linearAccelerationStreamHandler);

    gyroscopeChannel = new EventChannel(messenger, GYROSCOPE_CHANNEL_NAME);
    final StreamHandlerImpl gyroScopeStreamHandler =
        new StreamHandlerImpl(
            (SensorManager) context.getSystemService(Context.SENSOR_SERVICE),
            Sensor.TYPE_GYROSCOPE);
    gyroscopeChannel.setStreamHandler(gyroScopeStreamHandler);

    magnetometerChannel = new EventChannel(messenger, MAGNETOMETER_CHANNEL_NAME);
    final StreamHandlerImpl magnetometerStreamHandler =
        new StreamHandlerImpl(
            (SensorManager) context.getSystemService(Context.SENSOR_SERVICE),
            Sensor.TYPE_MAGNETIC_FIELD);
    magnetometerChannel.setStreamHandler(magnetometerStreamHandler);
  }

  private void teardownEventChannels() {
    accelerometerChannel.setStreamHandler(null);
    userAccelChannel.setStreamHandler(null);
    gyroscopeChannel.setStreamHandler(null);
    magnetometerChannel.setStreamHandler(null);
  }
}
