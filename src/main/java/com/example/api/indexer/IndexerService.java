package com.example.api.indexer;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.example.api.dto.RawDocument;

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

        // doc.add(new TextField("title", rawDoc.getData(), Field.Store.YES));

        writer.addDocument(doc);

    }

}