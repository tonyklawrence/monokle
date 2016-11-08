package evil.ducks.monokle

data class Street(val name: String)
data class Address(val number: Int, val street: Street)
data class Company(val name: String, val address: Address)
data class Employee(val name: String, val company: Company)