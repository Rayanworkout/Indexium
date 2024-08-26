package com.github.rayanworkout.api.searcher;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;


import org.apache.lucene.search.FuzzyQuery;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearcherService {
    public List<String> search(String field, String q) throws IOException, ParseException {

        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(Paths.get("index")));

        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        Query query = new FuzzyQuery(new Term(field, q));

        TopDocs topDocs = indexSearcher.search(query, 10);

        long totalHits = topDocs.totalHits.value;
        System.out.println(String.format("Found %d hits.", totalHits));

        ScoreDoc[] results = topDocs.scoreDocs;

        List<String> resultList = new ArrayList<>();
        for (ScoreDoc scoreDoc : results) {
            int docId = scoreDoc.doc;
            Document result = indexSearcher.storedFields().document(docId);
            resultList.add(result.get(field));
        }

        return resultList;

    }
}
