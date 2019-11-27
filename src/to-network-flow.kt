fun <T : Any> ApolloCall<T>.toNetworkFlow() = flow {
    val channel = Channel<Response<T>>(Channel.CONFLATED)

    enqueue(ChannelCallback(channel = channel))
    try {
        for (it in channel) {
            if (!it.hasErrors()) {
                emit(NetworkResult.Success(it.data()))
            }
            else {
                val error = response.errors().first()
                emit(
                    NetworkResult.Error(BusinessException(error.message()))
                )
            }
        }
    } catch (e: ApolloNetworkException) {
        emit(NetworkResult.Error(e))
    } finally {
        cancel()
    }
}
