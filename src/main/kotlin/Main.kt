// Some testing functionality to try out your WordCountTree implementation.
fun main() {
    val myTree = WordCountTree()

    // Test "document"
    val documentString = "book book book book"
    val words = documentString.split(" ")
    println(words)

    // Populate the WordCountTree
    for (word in words) {
        myTree.incrementCount(word)
    }

    // Check to see what is contained (or not) in the WordCountTree
    for (word in words + listOf("sandwich", "potato", "t", "o", "no")) {
        println("$word:")
        println(" contains: ${myTree.contains(word)}")
        println(" count: ${myTree.getCount(word)}")
    }

    // Check to see what autocompletions are possible for provided prefixes
    println(myTree.getAutocompletionMap("n"))
    println(myTree.getAutocompletionMap("t"))
    println(myTree.getAutocompletionMap("ti"))

    println("Done!")
}