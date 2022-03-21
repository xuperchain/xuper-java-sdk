package com.baidu.xuper.crypto.wordlists;

public interface WordList {
    /**
     * Get a word in the word list.
     *
     * @param index Index of word in the word list [0..2047] inclusive.
     * @return the word from the list.
     */
    String getWord(final int index);

    /**
     * Get the space character for this language.
     *
     * @return a whitespace character.
     */
    char getSpace();

    /**
     * @return has or not.
     */
    boolean has(final String word);

    /**
     * @param word one word.
     * @return index.
     */
    int getIndex(final String word);
}
