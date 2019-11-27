class MemberViewModel(private val repository: MemberRepository) : ViewModel() {
    ...

    val member: LiveData<MemberQuery.Member> =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            repository.getMemberOpen()
                .collect {
                    if (it is NetworkResult.Success) {
                        emit(it.value.member())
                    }
                }
        }

    ...
}
