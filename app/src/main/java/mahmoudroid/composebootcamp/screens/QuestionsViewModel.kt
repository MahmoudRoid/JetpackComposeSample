package mahmoudroid.composebootcamp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import mahmoudroid.composebootcamp.data.ResponseState
import mahmoudroid.composebootcamp.model.Question
import mahmoudroid.composebootcamp.repository.QuestionRepository
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val repository: QuestionRepository
) : ViewModel() {

    var questionsSize: Int = 0
        private set


    private val _questionData = MutableSharedFlow<ResponseState<Question>>()
    val questionData = _questionData.asSharedFlow()

    fun getAllQuestions() {

        viewModelScope.launch {
            delay(3000)
            repository.getAllQuestions().collect {
                if (it is ResponseState.Success)
                    questionsSize = it.data?.size ?: 0
                _questionData.emit(it)
            }
        }

    }


}