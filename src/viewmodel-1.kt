class MemberViewModel(private val repository: MemberRepository) : ViewModel() {

    private val _memberData: MutableLiveData<MemberQuery.Member> = MutableLiveData()
    val memberData: LiveData<MemberQuery.Member> = _memberData

    fun getMember() {

       viewModelScope.launch(Dispatchers.IO) {

           when (val response = repository.getMember(
               HttpCachePolicy.CACHE_FIRST,
               ApolloResponseFetchers.CACHE_FIRST
            )) {
               is NetworkResult.Success -> {
                   _memberData.postValue(response.value.member())
               }
               is NetworkResult.Error -> {
                   //Handle error
               }
           }
       }
    }
}