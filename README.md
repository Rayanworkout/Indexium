# What is Indexium?

**Indexium** is a high-performance, blazing-fast search engine written in Java, built upon the robust [Apache Lucene](https://lucene.apache.org/core/) framework. Lucene also powers several other well-known search engines, such as [Elasticsearch](https://www.elastic.co/) and [Solr](https://lucene.apache.org/solr/).

The primary goal of Indexium is to be **user-friendly and easy to use**. It is designed as a RESTful API, allowing for seamless integration with any project. Indexium provides efficient and accurate search capabilities with minimal setup.


Harness the power of Lucene’s advanced indexing and querying features through a straightforward API that scales effortlessly with your needs. Enhance your project with Indexium to enable fast and precise search functionality, ensuring your users can find what they need swiftly and easily.

The documentation is available at https://indexium.vercel.app/


# Examples

### Creating a schema

First, we create a schema for the search engine. A schema defines the fields that will be indexed and searched.

```bash
curl -X POST http://localhost:8080/schema/set -H "Content-Type: application/json" -d '{
  "fields":
    {
        "id": "Integer",
        "title": "String",
        "content": "String"
    }
}'
```

### Indexing a document

Next, we index a document. A document is a set of fields that will be indexed and searched.

```bash
curl -X POST http://localhost:8080/index -H "Content-Type: application/json" -d '{
  "data": {
      "id": 1,
      "title": "My first article",
      "content": "The quick brown fox jumps over the lazy dog."
  }
}'
```


### Searching for documents

Finally, we search for documents that match a query.

```bash
curl -X GET "http://localhost:8080/search?field=content&q=fox"
```

The result will be something like this:

```json
{
  "httpStatus": "OK",
  "hits": 1,
  "field": "content",
  "result": [
    "the quick brown fox jumps over the lazy dog"
  ]
}
```


# Contributing
Any contribution is warmly welcomed.