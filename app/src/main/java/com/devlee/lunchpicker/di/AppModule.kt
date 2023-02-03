package com.devlee.lunchpicker.di

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorManager
import com.devlee.lunchpicker.R
import com.devlee.lunchpicker.util.Consts.STORE_LIST
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerSensorManager(@ApplicationContext context: Context): SensorManager {
        return context.getSystemService(SENSOR_SERVICE) as SensorManager
    }

    @Provides
    @Singleton
    fun providerAccelerometer(sensorManager: SensorManager): Sensor {
        return sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    @Provides
    @Singleton
    @Named(STORE_LIST)
    fun providerStoreList(
        @ApplicationContext context: Context
    ) : List<String> = context.resources.getStringArray(R.array.lunch_store).toList()

    @Provides
    @Singleton
    fun providerPreference(
        @ApplicationContext context: Context
    ) : SharedPreferences {
        val preferenceName = "lunchPickerPreference"
        return context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    }
}