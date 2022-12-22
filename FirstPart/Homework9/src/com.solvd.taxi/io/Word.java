package io;

import lombok.Data;

@Data
public class Word implements Comparable<Word>{
    String word;
    int count;

    @Override
    public int compareTo(Word o) {
        return o.count-count;
    }
}
