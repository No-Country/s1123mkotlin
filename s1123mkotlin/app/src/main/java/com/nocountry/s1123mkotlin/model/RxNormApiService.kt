import com.nocountry.s1123mkotlin.model.MedicationResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RxNormApiService {

    @GET("RxNormAPI/v2/term?type=IN+PIN")
    suspend fun buscarMedicamentoPorNombre(
        @Query("term") nombre: String
    ):  Response<List<MedicationResponse>>

    companion object {
        private const val BASE_URL = "https://rxnav.nlm.nih.gov/"

        fun create(): RxNormApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(RxNormApiService::class.java)
        }
    }
}
