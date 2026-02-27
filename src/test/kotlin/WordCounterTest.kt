import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertNotNull
import kotlin.test.fail

public class WordCounterTest {

    // Testing basics, involving incrementCount and getCount
    @Test
    fun testIncrementAndGet() {
        val tester = WordCountTree()
        tester.incrementCount("bookkeeper")
        tester.incrementCount("bookkeep")
        tester.incrementCount("bookkeep")
        tester.incrementCount("book")
        tester.incrementCount("book")
        tester.incrementCount("book")
        assertEquals(3, tester.getCount("book"), "The count of 'book' should be 3")
        assertEquals(2, tester.getCount("bookkeep"), "The count of 'bookkeep' should be 2")
        assertEquals(0, tester.getCount("boo"), "The count of 'boo' should be 0")
    }

    // Testing contains with positive outcome after adding cheese.
    @Test
    fun testContainsPositive() {
        val tester = WordCountTree()
        tester.incrementCount("cheese")
        assertEquals(true, tester.contains("cheese"), "Cheese is cheese, Should return 'true' for contains cheese")
    }

    // Testing contains with negative outcome.
    @Test
    fun testContainsNegative() {
        val tester = WordCountTree()
        tester.incrementCount("chess")
        println("incremented chess")
        assertEquals(false, tester.contains("cheese"), "Chess is not cheese, Should return 'false' for contains cheese")
    }

    // Testing on some text
    @Test
    fun testText() {
        val myTree = WordCountTree()

        // Test "document"
        val documentString =
            "to be or not to be that is the question " +
            "whether tis nobler in the mind to suffer"
        val words = documentString.split(" ")

        // Populate the WordCountTree
        for (word in words) {
            myTree.incrementCount(word)
        }

        assertEquals(3, myTree.getCount("to"))
        assertEquals(2, myTree.getCount("be"))
        assertEquals(1, myTree.getCount("or"))
        assertEquals(0, myTree.getCount("sandwich"))
        assertEquals(false, myTree.contains("sandwich"))
        assertEquals(0, myTree.getCount("t"))
        assertEquals(false, myTree.contains("t"))
        assertEquals(false, myTree.contains("o"))
        assertEquals(false, myTree.contains("no"))

        // Check to see what autocompletions are possible for provided prefixes
        assertEquals(mapOf("not" to 1, "nobler" to 1),
                     myTree.getAutocompletionMap("n"))

        assertEquals(mapOf("to" to 3, "that" to 1, "the" to 2, "tis" to 1),
                     myTree.getAutocompletionMap("t"))

        assertEquals(mapOf("tis" to 1),
                     myTree.getAutocompletionMap("ti"))
    }
}