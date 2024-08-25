import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main() {
    println("Программа предоставления служебных номеров телефона сотрудникам компании\n")

    val persons = mutableListOf<Person>()
    val phones = mutableListOf<String>()
    val personInf0 = mutableListOf<String>()

    withContext(newSingleThreadContext("person_phone_context")) {
        launch {
            getPersonsFlow().collect { i ->
                persons.add(i)
            }
        }
        launch {
            getPhoneFlow(persons.size - 1).collect { i ->
                phones.add(i)
            }
        }
    }
    for (i in 0..<persons.size) {
        personInf0.add("${persons[i]}, ${phones[i]}")
    }

    println(personInf0.toString())
}

val persons = listOf(
    Person("Иван", "Developer"),
    Person("Людмила", "The tester"),
    Person("Елена", "Designer"),
    Person("Алексей", "Chief News Specialist at the cooler")
)

fun getPersonsFlow() = persons.asFlow()

fun getPhoneFlow(length: Int) = flow {
    val phone = Phone()
    for (i in 0..length) {
        emit(phone.phone())
    }
}