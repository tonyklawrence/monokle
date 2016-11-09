package evil.ducks.monokle

class Lens<A, B>(val get: (A) -> B, val set: (B) -> (A) -> A) {
    fun modify(ƒ: (B) -> B, a: A) = set(ƒ(get(a)))(a)
}

//infix fun Lens<A, B>.composeLens(l: Lens<B, C>) : Lens<A, C> = l