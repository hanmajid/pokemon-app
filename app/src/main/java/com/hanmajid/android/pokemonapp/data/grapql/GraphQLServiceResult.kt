package com.hanmajid.android.pokemonapp.data.grapql

/**
 * Represents the result of a GraphQL service call.
 */
data class GraphQLServiceResult<T>(
    /**
     * Whether the call is success or not.
     */
    val success: Boolean,

    /**
     * Error message if the call is unsuccessful.
     */
    val error: String? = null,

    /**
     * The result data if the call is successful.
     */
    val data: T? = null,
)