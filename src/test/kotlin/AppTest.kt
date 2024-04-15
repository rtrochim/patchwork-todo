import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.strikt.bodyString
import org.http4k.strikt.status
import org.http4k.testing.ApprovalTest
import org.http4k.testing.Approver
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@ExtendWith(ApprovalTest::class)
class AppTest {

    private val items = Todos(listOf("Learn Kotlin", "Buy milk", "Buy Oreos"))

    @Test
    fun testToo(approver: Approver) {
        val response: Response = items.routes.invoke(Request(Method.GET, "/"))
        approver.assertApproved(response)
    }

    @Test
    fun testTodoItems() {
        val response: Response = items.routes(Request(Method.GET, "/items"))

        expectThat(response) {
            status.isEqualTo(Status.OK)
            bodyString.isEqualTo("Learn Kotlin\nBuy milk\nBuy Oreos")
        }
    }

    @Test
    fun deleteItemFromToDo() {
       items.routes(Request(Method.DELETE, "/items"))
    }
}