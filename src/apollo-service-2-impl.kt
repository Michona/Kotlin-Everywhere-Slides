//In ApolloServiceImpl
override suspend fun <D : Operation.Data, T : Operation.Data, V : Operation.Variables> query(
    query: Query<D, T, V>,
    cachePolicy: HttpCachePolicy.Policy,
    responseFetcher: ResponseFetcher
): NetworkResult<T> {
    try {
        val response = getClient().query(query)
            .httpCachePolicy(cachePolicy).responseFetcher(responseFetcher)
            .toDeferred().await()

        if (response.hasErrors()) {
            val error = response.errors().first()
            return NetworkResult.Error(
                cause = BusinessException(error.message())
            )
        }

        return NetworkResult.Success(response.data())
    } catch (e: ApolloNetworkException) {
        return NetworkResult.Error(e)
    }
}