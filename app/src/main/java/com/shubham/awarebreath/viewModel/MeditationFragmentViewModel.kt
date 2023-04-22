package com.shubham.awarebreath.viewModel

import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.awarebreath.database.AnalyticListData
import com.shubham.awarebreath.database.BreathData
import com.shubham.awarebreath.database.BreathDataBase
import com.shubham.awarebreath.model.AnimationData
import com.shubham.awarebreath.model.FlowerAdapterModel
import com.shubham.awarebreath.repository.AnalyticRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MeditationFragmentViewModel(context: Context) : ViewModel() {


    //-----------------animation of flower -----------------
    private val repository : AnalyticRepository
    //-----------------animation of flower -----------------
    private var inhaleH: Int = 0
    private var exhaleH: Int = 0
    private var counter: Int = 0
    private var singleton: Boolean = true
    private var introCounter: Int = 5
    private var intro:Boolean = true
    var repeatTime: Int = 0
    var exhaleT: Int = 0
    //-----------------meditation session finished -----------------
    private val _sessionFinished = MutableLiveData<Boolean>()
    val sessionFinished: LiveData<Boolean> get() = _sessionFinished

    private val _breathString = MutableLiveData<String>("sit comfortably and get ready")
    val breathString: LiveData<String> get() = _breathString

    private var noOfBreath: Int = 0
    var noOfAwareBreath: Int = 0
    var noOfUnawareBreath: Int = 0

    fun breathIncrement(flag: Boolean) {
        noOfBreath++
        _breathString.value = noOfBreath.toString()
        if (flag) noOfAwareBreath++
        else noOfUnawareBreath++
    }

    private val _flowerAdapterModel = MutableLiveData<FlowerAdapterModel>(FlowerAdapterModel(4000, 40000, true))
    val flowerAdapterModel: LiveData<FlowerAdapterModel> get() = _flowerAdapterModel
    private val timeObject = MutableLiveData<String>("Starting value")
    val time: LiveData<String> get() = timeObject
    private val _hold = MutableLiveData<Boolean>()
    val hold: LiveData<Boolean> get() = _hold
    private val _music = MutableLiveData<Boolean>()
    val music: LiveData<Boolean> get() = _music
    private val stateObject = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = stateObject
    private val _visibility = MutableLiveData<Boolean>(true)
    val visibility: LiveData<Boolean> get() = _visibility
    //-----------------Breath Data----------------------------
    var meditationTitle = "default"
    var meditationTime = 10

    init {
        val dao = BreathDataBase.getBreathDatabase(context).breathDataDao()
        repository = AnalyticRepository(dao)
    }

    fun saveData(){
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DATE)
        val month_date = SimpleDateFormat("MMM")
        val df = SimpleDateFormat("hh:mm a")
        val month_name = month_date.format(calendar.time)
        val date = "$month_name $day"
        val currentTime = df.format(calendar.time)
        val completedDuration = time.value.toString()
        val analyticListData = AnalyticListData(date,currentTime,meditationTitle)
        val breathData = BreathData(0,analyticListData,noOfBreath,noOfAwareBreath,noOfUnawareBreath,meditationTime,completedDuration)

        viewModelScope.launch(Dispatchers.IO) {
            repository.insertBreathData(breathData)
        }
    }

    fun startTimer(animationData: AnimationData) {
        meditationTitle = animationData.meditationTitle
        val guidedTime = animationData.guidedTime
        val unguidedTime = animationData.unguidedTime
        meditationTime = guidedTime + unguidedTime
        val totalTime = TimeUnit.MINUTES.toMillis ((guidedTime + unguidedTime).toLong()) + 5000
        object : CountDownTimer(totalTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                if(intro){
                    timeObject.value = introCounter.toString()
                    if(introCounter == 5){_hold.value = false}
                    introCounter--
                    if(introCounter == 0){intro = false
                    }
            }
                else{
                    if (singleton) {
                        _music.value = true
                        repeatTime = animationData.inhaleT + animationData.exhaleT + animationData.inhaleH + animationData.exhaleH
                        inhaleH = animationData.inhaleT
                        exhaleT = inhaleH + animationData.inhaleH
                        exhaleH = repeatTime - animationData.exhaleH
                        if(animationData.inhaleH == 0){
                            inhaleH = -3
                        }
                        if(animationData.exhaleH == 0){
                            exhaleH = -3
                        }
                        val x: Long = TimeUnit.SECONDS.toMillis((animationData.inhaleT).toLong())
                        val y: Long = TimeUnit.SECONDS.toMillis((animationData.exhaleT).toLong())
                        val model = FlowerAdapterModel(x, y, true)
                        _flowerAdapterModel.value = model
                        singleton = false
                    }

                    val check = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)

                    if (check > (unguidedTime -1) ) {
                        when (counter) {
                            0 -> { stateObject.value = true
                            _breathString.value = "Inhale"
                            }
                            inhaleH ->{_hold.value = true
                                _breathString.value = "Hold"
                            }
                            exhaleH -> {_hold.value = true
                                _breathString.value = "Hold"
                            }
                            exhaleT -> { stateObject.value = false
                                _breathString.value = "Exhale"
                            }
                            repeatTime - 1 -> { counter = -1 }
                        }
                        counter++
                    } else {
                        _visibility.value = false
                        if(!animationData.musicInUnguidedMode) {_music.value = false}
                    }

                    val hms = String.format(
                        "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                            TimeUnit.MILLISECONDS.toHours(
                                millisUntilFinished
                            )
                        ),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(
                                millisUntilFinished
                            )
                        )
                    )
                    timeObject.value = hms
                }
            }
            override fun onFinish() {
             _sessionFinished.value = true
            }
        }.start()
    }
}