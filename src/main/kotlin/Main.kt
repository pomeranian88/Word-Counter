// Some testing functionality to try out your WordCountTree implementation.
fun main() {
    val myTree = WordCountTree()

    // Test "document"
    val documentString = "book book book"
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


    println(myTree.contains("no"))
    println(myTree.contains("nobler"))

    // Check to see what autocompletions are possible for provided prefixes
    println(myTree.getAutocompletionMap("b"))

    println("Done!")
}