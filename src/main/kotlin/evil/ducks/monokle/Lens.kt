package evil.ducks.monokle

class Lens<A, B>(val get: (A) -> B, val set: (B) -> (A) -> A) {
    fun modify(ƒ: (B) -> B, a: A) = set(ƒ(get(a)))(a)

    infix fun <C> composeLens(l: Lens<B, C>): Lens<A, C> {
        return Lens(
                get = { a -> l.get(get(a)) },
                set = { c -> { a -> set(l.set(c)(get(a)))(a) }}
        )
    }
}