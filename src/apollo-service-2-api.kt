interface ApolloService {

    suspend fun <D : Operation.Data, T : Operation.Data, V : Operation.Variables> query(
        query: Query<D, T, V>,
        cachePolicy: HttpCachePolicy.Policy = HttpCachePolicy.NETWORK_ONLY,
        responseFetcher: ResponseFetcher = ApolloResponseFetchers.NETWORK_ONLY
    ): NetworkResult<T>
}