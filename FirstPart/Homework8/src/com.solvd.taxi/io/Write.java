package io;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class Write {
    public String getText(String path) throws IOException {
        File file = FileUtils.getFile(path);
        File tmpDir = FileUtils.getTempDirectory();
        FileUtils.copyFileToDirectory(file, tmpDir);
        File newTempFile = FileUtils.getFile(tmpDir, file.getName());
        String data = FileUtils.readFileToString(newTempFile, Charset.defaultCharset());
        return data;
    }

    public boolean checkBlank(String k){
        if(StringUtils.isBlank(k))
            return false;
        return false;
    }

    public String[] doArray(String k){
        k=StringUtils.remove(k,',');
        k=StringUtils.remove(k,'.');
        String[] f=StringUtils.split(k);
        return f;
    }

    public boolean checkSimilar(String[] k) throws IOException {
        Map<String, Word> t = new HashMap<>();
        for (String g : k) {

            Word word = t.get(g);
            if (word == null) {
                word = new Word();
                word.word = g;
                word.count = 1;
                t.put(String.valueOf(g), word);
            } else {
                word.count++;
                t.put(String.valueOf(word.count), word);
            }

        }
        Set<Word> f=new HashSet<Word>(t.values());
        List<Word> l=new LinkedList<>(f);
        String[] fina=new String[l.size()];
        for (int p=0;p<l.size();p++)
            fina[p]=String.valueOf(l.get(p));

        writeFile(fina);
        return true;

    }

    public boolean checkSimilarNumber(String[] k) throws IOException {
        Set<String> sets=new HashSet<>();
        for (int i=0;i<k.length;i++)
            sets.add(k[i]);
        writeFile(sets.size());
        return true;
    }

    private boolean writeFile(int count) throws IOException {
        File file = FileUtils.getFile("D:\\intCount.txt");

        FileUtils.writeStringToFile(file,String.valueOf(count));
        return true;
    }

    private boolean writeFile(String[] g) throws IOException {
        File file = FileUtils.getFile("D:\\stringOut.txt");
        String u="";
        for(int i=0;i<g.length;i++) {
            u+=g[i];
            u+=" ";
        }
        FileUtils.writeStringToFile(file,u);
        return true;
    }

}
