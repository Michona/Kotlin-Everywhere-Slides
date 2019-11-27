
class MemberRepository(private val apolloService: ApolloService) {

    ...

    suspend fun getMember(
        cachePolicy: HttpCachePolicy.Policy,
        responseFetcher: ResponseFetcher
    ): NetworkResult<MemberQuery.Data> {

        val memberQuery = MemberQuery.builder().build()

        return apolloService.query(
            query = memberQuery,
            cachePolicy = cachePolicy,
            responseFetcher = responseFetcher
        )
    }

    ...
}