/**
 * Author: Zev Thompson
 * 
 * Collab Statement: Worked on all the code for this by myself, chatted with Sadie & Ian a few times about our logic for the code.
 * 
 * Reflection:  This project was not too hard -- my main problem, I realized at the end, was making my code more elegant! After turning
 *              it in I checked out Ian's code and saw it was way more concise than mine. I honestly don't know why this happens,
 *              the cases I check seem to be completely necessary but our computational logic is just different. Hoping to get better
 *              at thinking through ideas in more efficient ways, though this way works.
 *              This project took approximately 5 hours total.
 */
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