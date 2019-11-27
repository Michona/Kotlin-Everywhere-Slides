final HeroAndFriendsNames heroAndFriendsQuery = HeroAndFriendsNames.builder()
    .episode(NEWHOPE)
    .build();

apolloClient().query(heroAndFriendsQuery)
    .enqueue(new ApolloCallback<>(
        new ApolloCall.Callback<HeroAndFriendsNames.Data>() {
      @Override 
      public void onResponse(
          @NotNull Response<HeroAndFriendsNames.Data> response) {
        Log.i(TAG, response.toString());
      }

      @Override 
      public void onFailure(@NotNull ApolloException e) {
        Log.e(TAG, e.getMessage(), e);
      }
    }, uiHandler));
}