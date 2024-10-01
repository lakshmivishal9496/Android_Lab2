import android.util.Log // Add this import for logging
import com.example.lab2_database.Company
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class BusinessDataSource {
    private val _businessFlow = MutableStateFlow<List<Company>>(emptyList())
    //_businessFlow is private and mutable, while businessFlow is public and immutable (StateFlow).
    // This pattern ensures that external consumers can only observe the flow and not modify it.
    val businessFlow: StateFlow<List<Company>> = _businessFlow
    //suspend keyword run asynchronously inside a coroutine
    suspend fun retrieveBusinesses() {
        val reference = FirebaseDatabase.getInstance().getReference("companies")
        try {
            val dataSnapshot = reference.get().await()
            Log.d("FirebaseData", "DataSnapshot: ${dataSnapshot.value}")  // Log raw snapshot
            val businessList = mutableListOf<Company>()
            for (child in dataSnapshot.children) {
                // parsing json string fetched into kotlin class
                //Firebase(java based library) will use to deserialize the data into an instance of the Company class.
                val company = child.getValue(Company::class.java)
                Log.d("FirebaseData", "Company fetched: $company")  // Log each company
                company?.let { businessList.add(it) }
            }
            _businessFlow.value = businessList
        } catch (e: Exception) {
            Log.e("BusinessDataSource", "Error fetching data", e)
            _businessFlow.value = emptyList()
        }
    }


}
