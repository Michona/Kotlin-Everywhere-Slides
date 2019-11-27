// in :apollo-service module
class ApolloService(private val context: Context) {

    @Volatile
    private var client: ApolloClient? = null

    fun getClient(): ApolloClient {
        return client ?: synchronized(this) {
            client ?: buildApolloClient().also {
                client = it
            }
        }
    }

    private fun buildApolloClient(): ApolloClient {

        ...

        return ApolloClient.builder()
            .serverUrl(BuildConfig.API_URL)
            .okHttpClient(okHttpClient)
            .normalizedCache(cacheFactory, resolver)
            .build()
    }
}