class WordCountTree {
    // Nobody else needs to see how we internally store nodes, so
    // we keep the data class private
    private data class Node(
        var count: Int = 0,
        val children: MutableMap<Char, Node> = mutableMapOf<Char, Node>())

    // Store a pointer to the root node in the tree
    private var root: Node = Node()

    /**
     * Returns a string representation of the tree.
     */
    override fun toString(): String {
        return root.toString()
    }

    /**
     * Adds 1 to the existing count for given word, 
     * or adds given word to the WordCountTree with a count of 1 
     * if it is not already present.
     * Implementation must be recursive, not iterative.
     */
    fun incrementCount(word: String) {
        // TODO: Part A
        println(word)

        if(word.length==1){     // special case: word is only one letter, so just check if there's link to root: if there is, increment, if not, establish existence then increment
            var firstLetter: Node? = root.children.get(word[0])

            if(firstLetter!=null){
                firstLetter!!.count++}
            else{
                var tempNode: Node = Node(count=1)
                root.children.put(word[0], tempNode)}
        }
        else{                   // all other non-single letter cases: send to other loop to increment through
            traverseForIncrementCount(word, root)
        }
    }

    /**
     * Traverses through the tree for non-single letter words.
     * 1. If the whole word is found, final letter count is incremented.
     * 2. If word is incremented through and at some point a letter doesn't
     * follow in map, it is established as a child, and the rest of the word is
     * recursively looped through to establish the whole word -- then final
     * letter count is incremented.
     */
    private fun traverseForIncrementCount(word: String, current: Node?){
        var currentLetterExists: Node? = current!!.children.get(word[0])
        var tempNode: Node? = null

        if(currentLetterExists!=null){  // okay, the next letter in the series exists! so if we're at the last, we've finished, if not, we keep pushing
            if(word.length==1){
                currentLetterExists.count++} // we've finished! increment count and end
            else{
                traverseForIncrementCount(word.drop(1), currentLetterExists)} // let's keep going down the tree until we reach the end
        }

        else{       // okay, the next letter in the series doesn't exist in the map for this line: we're going to make the rest of the word then, and input it
            tempNode = Node(count=0)
            current!!.children.put(word[0], tempNode)

            if(word.length==1){ // okay, we gave current the child and we've reached the end of the word. so end and create the first number of count at the full word!
                tempNode.count++}
            else{   // okay, we haven't reached the end, but now we have the next letter in. re-traverse, and if all goes to plan: we will end up back in this loop, and keep recurring and inputting new letters until we reach the end
                traverseForIncrementCount(word.drop(1), current!!.children.get(word[0]))}
        }
    }

    /**
     * Returns the count of word. Returns 0 if word is not present.
     * Implementation must be recursive, not iterative.
     */
    fun getCount(word: String): Int {
        if(word.length==1){ // special case: 
            return if(root!!.children.get(word[0])!=null) root!!.count else 0
        }
        else{
            return traverseForGetCount(word, root)
        }
    }

    /**
     * Iterates through non-single letter words. 
     * If ever a letter in the line of the word doesn't exist in the map, return 0.
     * If whole word exists in map, return count of final letter.
     */
    private fun traverseForGetCount(word: String, current: Node?): Int{
        var currentLetterExists: Node? = current!!.children.get(word[0])

        if(currentLetterExists!=null){
            if(word.length==1){
                return currentLetterExists.count}
            else{
                return traverseForGetCount(word.drop(1), currentLetterExists)} // TK: return??
        }
        else{
            return 0
        }
    }

    /**
     * Returns true if word is stored in this WordCountTree
     * with a count greater than 0, and false otherwise.
     * Implementation must be recursive, not iterative.
     */
    fun contains(word: String): Boolean {
        // TODO: Part C
        fun inContains(word: String, current: Node): Boolean{
            var currentLetterExists: Node? = current.children.get(word[0])
            if(currentLetterExists!=null){
                if(word.length==1){
                    return currentLetterExists!!.count>0}

                return inContains(word.drop(1), currentLetterExists)}
            else{
                return false
            }
        }

        if(word.length==1){
            if(root.children.get(word[0])!=null){
                if(root.children.get(word[0])!!.count>0){return true}
                else{return false}
            }
            return false
        }

        return inContains(word, root)
    }

    /**
     * Returns a MutableMap of all words in WordCountTree that
     * start with the given prefix, mapped to their counts.
     * If prefix is not present, returns an empty MutableMap.
     */
    fun getAutocompletionMap(prefix: String): MutableMap<String, Int> {
        var wordList: MutableMap<String, Int> = mutableMapOf()

        fun traversal(prefix: String, current: Node){   //
            if(current.children.isEmpty()){
                wordList.put(prefix, current.count)}
            else{
                for((kidChar, kidNode) in current.children){
                    var fullishWord: String = prefix + kidChar // add kid[0] which is the char associated with the child
                    traversal(fullishWord, kidNode)}
                
                if(current.count>0){
                    var secondFullishWord: String = prefix
                    wordList.put(secondFullishWord, current.count)}
            }
        }

        if(exists(prefix)){
            println("")
            var nodeAtPrefix: Node? = root
            var keepTrack: String = ""
            for(letter in prefix){
                nodeAtPrefix = nodeAtPrefix!!.children.get(letter)
            }

            if(nodeAtPrefix!=null){traversal(prefix, nodeAtPrefix)}
            return wordList
        }
        else{
            return wordList
        }
    }

    /**
     * 
     */
    fun exists(word: String): Boolean{
        fun traverseForExists(word: String, current: Node?): Boolean{
            var currentLetterExists: Node? = current!!.children.get(word[0])

            if(currentLetterExists!=null){
                if(word.length==1){
                    return true}
                else{
                    return traverseForExists(word.drop(1), currentLetterExists)}
            }
            else{
                return false
            }
        }

        return traverseForExists(word, root)
    }
}