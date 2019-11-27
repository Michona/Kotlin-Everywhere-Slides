// in :repository module
class MemberRepository(private val apolloService: ApolloService) {

    ...

    suspend fun getMemberData(): MemberQuery.Data {
        val response = apolloService.getClient()
            .query(MemberQuery.builder().build())
            .toDeferred().await()

        return response
    }

    ...
}