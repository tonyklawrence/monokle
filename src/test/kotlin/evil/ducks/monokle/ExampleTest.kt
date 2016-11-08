package evil.ducks.monokle

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ExampleTest {
    val employee = Employee("Tony", Company("Evil Ducks", Address(22, Street("Acacia Avenue"))))

    @Test fun `let us start by accessing some simple data classes`() {
        assertThat(employee.name, equalTo("Tony"))
        assertThat(employee.company.name, equalTo("Evil Ducks"))
        assertThat(employee.company.address.number, equalTo(22))
        assertThat(employee.company.address.street.name, equalTo("Acacia Avenue"))
    }
}