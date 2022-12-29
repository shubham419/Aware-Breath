package com.shubham.awarebreath.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.awarebreath.R
import com.shubham.awarebreath.database.BreathDataBase
import com.shubham.awarebreath.database.MeditationSessionData
import com.shubham.awarebreath.model.ColorPickerData
import com.shubham.awarebreath.repository.MeditationSessionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateMeditationSessionFragmentViewModel(val context: Context) : ViewModel() {

    private val repository: MeditationSessionRepository

    init {
        val dao = BreathDataBase.getBreathDatabase(context).meditationSessionDataDao()
        repository = MeditationSessionRepository(dao)
    }


    val test = MutableLiveData<String>(R.drawable.img_c.toString())
    val percentage = MutableLiveData<String>("")

    //------------Breathing rhythm-------------//
    val inhaleT = MutableLiveData<String>("")
    val inhaleHoldT = MutableLiveData<String>("")
    val exhaleT = MutableLiveData<String>("")
    val exhaleHoldT = MutableLiveData<String>("")

    //------------meditation time-------------//
    val meditationTime = MutableLiveData<String>("")

    //------------color picker----------------//
    private val _colorPickerData = MutableLiveData<ColorPickerData>(ColorPickerData(7, 7))
    val colorPickerData: LiveData<ColorPickerData> get() = _colorPickerData

    //------------display img----------------//
    private val _image = MutableLiveData<String>("2131230945")
    val image: LiveData<String> get() = _image
    private val _visibility = MutableLiveData<Boolean>(true)
    val visibility: LiveData<Boolean> get() = _visibility

    //------------voice selector----------------//
    private val _voiceVisibility = MutableLiveData<Boolean>(true)
    val voiceVisibility: LiveData<Boolean> get() = _voiceVisibility
    private val _voice = MutableLiveData<Boolean>()
    val voice: LiveData<Boolean> get() = _voice
    private val _voiceName = MutableLiveData<String>("Alex")
    val voiceName: LiveData<String> get() = _voiceName

    //------------music selector----------------//
    private val _musicVisibility = MutableLiveData<Boolean>(true)
    val musicVisibility: LiveData<Boolean> get() = _musicVisibility
    private val _music = MutableLiveData<Boolean>()
    val music: LiveData<Boolean> get() = _music
    private val _musicName = MutableLiveData<String>("Bell")
    val musicName: LiveData<String> get() = _musicName


    fun createMeditationSession(
        name: CharSequence,
        time: CharSequence,
        imageId: String,
        guidedPercentage: CharSequence,
        switchUnguided: Boolean
    ) {

        val data = createMeditationSessionData(
            name.toString(),
            time.toString(),
            imageId.toInt(),
            guidedPercentage.toString(),
            switchUnguided
        )
        viewModelScope.launch(Dispatchers.IO) {
            repository.createMeditationSession(data)
        }
//        Toast.makeText(context, name.toString() + time.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun createMeditationSessionData(
        name: String,
        time: String,
        imageId: Int,
        guidedPercentage: String,
        switchUnguided: Boolean
    ): MeditationSessionData {
        val guidedModePercentage =
            if (guidedPercentage.toIntOrNull() != null) guidedPercentage.toInt() else 40
        val guidedTime = getGuidedTime(guidedModePercentage)
        val color: Int = colorPickerData.value!!.selectedColor
        val inhaleT = if (inhaleT.value?.toIntOrNull() != null) inhaleT.value!!.toInt() else 4
        val inhaleH =
            if (inhaleHoldT.value?.toIntOrNull() != null) inhaleHoldT.value!!.toInt() else 2
        val exhaleT = if (exhaleT.value?.toIntOrNull() != null) exhaleT.value!!.toInt() else 6
        val exhaleH =
            if (exhaleHoldT.value?.toIntOrNull() != null) exhaleHoldT.value!!.toInt() else 2
        val instructionVoice: Boolean = if (voice.value != null) voice.value!! else true
        val music: Boolean = if (music.value != null) music.value!! else true
        val meditationName = if (name == "") "New" else name
        val meditationTime = if (time == "") "10" else time
        val unguidedModePercentage = 100 - guidedModePercentage
        val unguidedTime = if (time.toIntOrNull() != null) time.toInt() - guidedTime else 6
        return MeditationSessionData(
            0,
            meditationName,
            meditationTime,
            imageId,
            color,
            inhaleT,
            inhaleH,
            exhaleT,
            exhaleH,
            guidedTime,
            unguidedTime,
            unguidedModePercentage,
            guidedModePercentage,
            instructionVoice,
            music,
            switchUnguided
        )
    }

    private fun getGuidedTime(guidedPercentage: Int): Int {
        val time =
            if (meditationTime.value?.toIntOrNull() != null) meditationTime.value!!.toInt() else 10
        return time * guidedPercentage / 100
    }

    fun colorClicked(selectedColor: Int) {
        val unselectedColor: Int = colorPickerData.value!!.selectedColor
        val data = ColorPickerData(selectedColor, unselectedColor)
        _colorPickerData.value = data
    }

    fun displayPictureSelected(id: String) {
        _image.value = id
        _visibility.value = true
    }

    fun displayPictureClicked() {
        _visibility.value = false
    }

    fun instructionVoiceClicked() {
        _voiceVisibility.value = false
    }

    fun voiceSelected(name: Boolean) {
        _voiceVisibility.value = true
        _voice.value = name
        if (name) {
            _voiceName.value = "Alex"
        } else {
            _voiceName.value = "Lubina"
        }
    }

    fun musicClicked() {
        _musicVisibility.value = false
    }

    fun musicSelected(name: Boolean) {
        _musicVisibility.value = true
        _music.value = name
        if (name) {
            _musicName.value = "Bell"
        } else {
            _musicName.value = "Flute"
        }
    }

}