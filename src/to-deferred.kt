//From apollo-android library source code
fun <T> ApolloCall<T>.toDeferred(): Deferred<Response<T>> {
    val deferred = CompletableDeferred<Response<T>>()
  
    deferred.invokeOnCompletion {
      if (deferred.isCancelled) {
        cancel()
      }
    }
    enqueue(object : ApolloCall.Callback<T>() {
      override fun onResponse(response: Response<T>) {
        deferred.complete(response)
      }
  
      override fun onFailure(e: ApolloException) {
        deferred.completeExceptionally(e)
      }
    })
  
    return deferred
}