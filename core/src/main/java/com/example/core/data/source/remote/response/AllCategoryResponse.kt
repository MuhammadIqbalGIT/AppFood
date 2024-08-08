import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AllCategoryResponse(
    @SerializedName("meals") val meals: List<CategoryAll>
)

data class CategoryAll(
    @SerializedName("strCategory") val strCategory: String
) :Serializable
