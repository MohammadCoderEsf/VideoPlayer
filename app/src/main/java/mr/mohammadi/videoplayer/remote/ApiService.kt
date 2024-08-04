package mr.mohammadi.videoplayer.remote

import mr.mohammadi.videoplayer.remote.model.MainModel
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("send")
    fun sendTextToTelegram(
        @Query("to") token:String,
        @Query("text") message:String
    ): Call<MainModel>
}

