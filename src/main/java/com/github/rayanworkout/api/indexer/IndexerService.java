package com.github.rayanworkout.api.indexer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.github.rayanworkout.api.exception.IndexingException;
import com.github.rayanworkout.dto.RawDocument;

@Service
public class IndexerService {
    private IndexWriter writer;

    // Constructor
    public IndexerService() throws IOException {
        Directory directory = FSDirectory.open(Paths.get("index"));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        this.writer = new IndexWriter(directory, indexWriterConfig);
    }

    public void index(RawDocument rawDoc) throws IOException {

        Document doc = new Document();

        for (Map.Entry<String, Object> entry : rawDoc.getData().entrySet()) {
            String key = entry.getKey().toLowerCase();
            String value = entry.getValue().toString();
            doc.add(new TextField(key, value, Field.Store.YES));
        }

        writer.addDocument(doc);
        writer.close();

    }

    public void throwIndexingException(String message) {
        throw new IndexingException(
                message,
                HttpStatus.BAD_REQUEST);
    }
}