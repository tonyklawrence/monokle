package evil.ducks.monokle

class Lens<A, B>(val get: (A) -> B, val set: (B) -> (A) -> A)

//infix fun Lens<A, B>.composeLens(l: Lens<B, C>) : Lens<A, C> = l