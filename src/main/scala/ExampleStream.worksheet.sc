import fs2.Pure
import cats.instances.stream
import cats.effect.IO
import fs2.Chunk
val unfold=fs2.Stream.unfold(0)(i=> if(i <20) Some(i->(i+1)) else None)

unfold.toList

val emit=fs2.Stream.emit(12)
emit.toList
val empty=fs2.Stream.empty
empty.toList

val chunk= fs2.Stream.unfoldChunk(0)(i => if (i < 5) Some(Chunk.seq(List.fill(i)(i)) -> (i+1)) else None)
chunk.toList

val emit1=fs2.Stream.emit(Chunk.seq((1 to 20)))

emit1.evalMap(c=> IO(c.toList)).compile.toList


val io=IO(12)

fs2.Stream.unfoldLoop(0)(i => (i, if (i < 5) Some(i+1) else None)).toList


fs2.Stream.emits('A' to 'E').toList

fs2.Stream.emits[IO, Char]('A' to 'E')
  .map(letter => fs2.Stream.emits[IO, Int](1 to 3).map(index => s"$letter$index"))
  .parJoin(5)

  val s = fs2.Stream(1, 2) ++ fs2.Stream(3) ++ fs2.Stream(4, 5, 6)
  s.chunks.toList
  fs2.Stream(1, 2, 3).append(fs2.Stream(4, 5, 6)).mapChunks { c => val ints = c.toArraySlice; for (i <- 0 until ints.values.size) ints.values(i) = 0; ints }.toList

  fs2.Stream("Hello", "Hi", "Greetings", "Hey").groupAdjacentBy(_.head).toList.map { case (k,vs) => k -> vs.toList }



  def lettersIter: fs2.Stream[Pure,Char]=fs2.Stream.emits('A' to 'Z')

  lettersIter.toList

  def lettersUnfold=fs2.Stream.unfold('a')(let=>Some(let,(let+1).toChar))

  lettersUnfold.take(26).toList

  val numbers=fs2.Stream.iterate(0)(_+1)

  numbers.take(12).toList

  def myIterate[A](initial:A)(next:A=>A): fs2.Stream[Pure,A]= fs2.Stream.unfold(initial)(init=>Some(init->next(init)))

  myIterate(0)(_+1).take(12).toList

  