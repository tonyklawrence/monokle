@file:Suppress("ConvertLambdaToReference")

package evil.ducks.monokle

import evil.ducks.monokle.ExampleLens._address
import evil.ducks.monokle.ExampleLens._company
import evil.ducks.monokle.ExampleLens._companyAddress
import evil.ducks.monokle.ExampleLens._street
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

object ExampleLens {
    val _company = Lens(Employee::company, { c -> { e -> e.copy(company = c) } })
    val _address = Lens(Company::address, { a -> { c -> c.copy(address = a) } })
    val _street  = Lens(Address::street, { s -> { a -> a.copy(street = s) } })

    val _companyAddress = _company composeLens _address
}

class ExampleTest {
    val acaciaAvenue = Street("Acacia Avenue")
    val evilDucksBuilding = Address(22, acaciaAvenue)
    val evilDucks = Company("Evil Ducks", evilDucksBuilding)
    val tony = Employee("Tony", evilDucks)

    @Test fun `accessing some simple data classes is tedious`() {
        assertThat(tony.name, equalTo("Tony"))
        assertThat(tony.company.name, equalTo("Evil Ducks"))
        assertThat(tony.company.address.number, equalTo(22))
        assertThat(tony.company.address.street.name, equalTo("Acacia Avenue"))
    }

    @Test fun `using a simple data class lens makes reading light work`() {
        assertThat(_company.get(tony), equalTo(evilDucks))
        assertThat(_address.get(evilDucks), equalTo(evilDucksBuilding))
        assertThat(_street.get(evilDucksBuilding), equalTo(acaciaAvenue))
    }

    @Test fun `with lens we can modify inside nested structures`() {
        val moveTo = { address: Address -> { company: Company -> _address.modify( { address }, company) }}

        val bakerStreet = Address(221, Street("Baker Street"))
        val movedCompany = moveTo(bakerStreet)(evilDucks)

        assertThat(movedCompany.address, equalTo(bakerStreet))
    }

    @Test fun `lens should compose`() {
        val bakerStreet = Address(221, Street("Baker Street"))
        val result = _companyAddress.modify({ bakerStreet }, tony)

        assertThat(_companyAddress.get(tony), equalTo(evilDucksBuilding))
        assertThat(result.company.address, equalTo(bakerStreet))
    }
}