// in :repository module
class MemberRepository(private val apolloService: ApolloService) {

    ...

    suspend fun getMemberData(): NetworkResult<MemberQuery.Data> {
        try {
            val response = apolloService.getClient()
                .query(MemberQuery.builder().build())
                .toDeferred().await()

            return NetworkResult.Success(response)
        }
        catch(e: ApolloNetworkException) {
            return NetworkResult.Error(e)
        }
    }

    ...
}