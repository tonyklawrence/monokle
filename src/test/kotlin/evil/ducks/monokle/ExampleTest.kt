package evil.ducks.monokle

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ExampleTest {
    val employee = Employee("Tony", Company("Evil Ducks", Address(22, Street("Acacia Avenue"))))

    @Test fun `let us start by creating a lens on a simple data class`() {
        assertThat(employee.name, equalTo("Tony"))
    }
}

data class Street(val name: String)
data class Address(val number: Int, val street: Street)
data class Company(val name: String, val address: Address)
data class Employee(val name: String, val company: Company)