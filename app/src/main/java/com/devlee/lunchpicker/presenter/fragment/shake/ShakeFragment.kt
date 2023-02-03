package com.devlee.lunchpicker.presenter.fragment.shake

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.devlee.lunchpicker.BuildConfig
import com.devlee.lunchpicker.R
import com.devlee.lunchpicker.databinding.FragmentShakeBinding
import com.devlee.lunchpicker.util.Consts.STORE_LIST
import com.devlee.lunchpicker.util.Consts.TAG
import com.devlee.lunchpicker.util.PreferenceUtil
import com.devlee.lunchpicker.util.toPrettyJson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class ShakeFragment : Fragment(), SensorEventListener {
    private var _binding: FragmentShakeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var mSensorManager: SensorManager

    @Inject
    lateinit var mAccelerometer: Sensor

    private val shakeViewModel by viewModels<ShakeViewModel>()

    @Inject
    @Named(STORE_LIST)
    lateinit var storeList: List<String>

    @Inject
    lateinit var pref: PreferenceUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShakeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderShakeUiState()
        renderAnimateText()

        binding.shakeRegisterBtn.setOnClickListener {
            shakeViewModel.onEvent(ShakeEvent.ShakeStart)
        }

        binding.shakeRestartBtn.setOnClickListener {
            shakeViewModel.onEvent(ShakeEvent.ShakeStart)
        }

        binding.appVersion.text = getString(R.string.app_version_text, BuildConfig.VERSION_NAME)

    }

    private fun renderShakeUiState() = lifecycleScope.launchWhenResumed {
        shakeViewModel.shakeUiState.collect { uiState ->
            Log.d(TAG, "renderShakeUiState: ${uiState::class.java.simpleName}")
            val isShakeInitOrEnd = (uiState is ShakeUiState.Init) || (uiState is ShakeUiState.Shake.End)
            binding.shakeRegisterBtn.isVisible = isShakeInitOrEnd
            binding.storeText.isVisible = !isShakeInitOrEnd
            binding.shakeRestartBtn.isVisible = uiState is ShakeUiState.Shake.Complete
            binding.hintText.isVisible = uiState is ShakeUiState.OnceChoiceList

            shakeViewModel.isShaking.set(uiState is ShakeUiState.Shake.Ing)

            when (uiState) {
                is ShakeUiState.Init -> {
                    binding.shakeRegisterBtn.visibility = View.VISIBLE
                    binding.storeText.visibility = View.GONE
                }
                is ShakeUiState.EmptyChoiceList -> {
                    binding.storeText.text = getString(R.string.empty_item)
                }
                is ShakeUiState.OnceChoiceList -> {
                    binding.storeText.text = uiState.title
                }
                is ShakeUiState.Shake.Start -> {
                    mSensorManager.registerListener(this@ShakeFragment, mAccelerometer, SensorManager.SENSOR_DELAY_UI)
                    binding.storeText.text = getString(R.string.store_default_text)

                }
                is ShakeUiState.Shake.Complete,
                is ShakeUiState.Shake.End -> {
                    mSensorManager.unregisterListener(this@ShakeFragment)
                    if (uiState is ShakeUiState.Shake.Complete) {
                        binding.storeText.text = uiState.storeText
                    }
                }
                else -> {}
            }
        }
    }

    private fun renderAnimateText() = lifecycleScope.launchWhenResumed {
        shakeViewModel.sakeRecycleStoreArray.collect {
            binding.storeText.text = it
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        shakeViewModel.onEvent(ShakeEvent.ShakeEnd)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
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
                // 이미 흔들어서 선택중인 경우
                if (shakeViewModel.isShaking.get()) {
                    return
                }

                // 흔들었을 때, 행동
                shakeViewModel.setShakeCount()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}