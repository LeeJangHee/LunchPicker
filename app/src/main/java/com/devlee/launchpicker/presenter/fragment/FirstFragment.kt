package com.devlee.launchpicker.presenter.fragment

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devlee.launchpicker.R
import com.devlee.launchpicker.databinding.FragmentFirstBinding
import com.devlee.launchpicker.util.Consts.TAG
import com.devlee.launchpicker.util.toPrettyJson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FirstFragment : Fragment(), SensorEventListener {
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var mSensorManager: SensorManager

    @Inject
    lateinit var mAccelerometer: Sensor

    private var shakeIgnoreTime: Long = 0L
    private var count: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            Log.e(TAG, "onSensorChanged \n${event.toPrettyJson()}")

            // x, y, z 받기
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            // 중력 가속도 값으로 나눈 값으로 만듬
            val gX = x / SensorManager.GRAVITY_EARTH
            val gY = y / SensorManager.GRAVITY_EARTH
            val gZ = z / SensorManager.GRAVITY_EARTH

            // gForce는 중력가속도를 포함하는 물체가 받는 힘 (피타고라스 사용)
            // 1 == stop
            // 1이하 = 아래로 떨어짐
            // 1이상 = 위로 올라가는힘
            val gForce = Math.sqrt((gX * gX) + (gY * gY) + (gZ * gZ).toDouble()).toFloat()

            // 진동 감지
            // gForce 기준치 이상일 경우
            if (gForce > 2.7f) {
                val now = System.currentTimeMillis()
                // 진동 간격이 너무 짧으면 무시
                if (shakeIgnoreTime + 1000L > now) {
                    return
                }

                shakeIgnoreTime = now
                count++
                // 흔들었을 때, 행동
                Toast.makeText(requireContext(), "흔들기 인식 -> $count", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}