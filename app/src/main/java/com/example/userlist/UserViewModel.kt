import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    val userList: MutableLiveData<MutableList<User>> by lazy {
        MutableLiveData<MutableList<User>>(mutableListOf())
    }

    fun addUser(user: User) {
        val currentList = userList.value ?: mutableListOf()
        currentList.add(user)
        userList.value = currentList
    }

    fun removeUser(user: User) {
        val currentList = userList.value ?: mutableListOf()
        currentList.remove(user)
        userList.value = currentList
    }
}
