//In ApolloServiceImpl
override suspend fun <D : Operation.Data, T : Operation.Data, V : Operation.Variables> openQuery(
    query: Query<D, T, V>
): Flow<NetworkResult<T>> {
    return getClient().query(query)
           .responseFetcher(ApolloResponseFetchers.CACHE_AND_NETWORK)
           .toNetworkFlow()
}
