# Indexing documents

Once the schema is set, Indexium is ready to index some documents. The documents are the data you want to index and search for. Each document is a JSON object that contains the fields defined in the schema.

If the document if even slightly different from the schema, Indexium will reject it. Every time you try to index a document, Indexium will first check if the schema exists, then if the document is matching the current schema.

## How is stored my index ?

The index represents the data you want to search for. It is stored in a file on the disk, in the `index` directory.
You can easily tweak the projet to store the index in a database or in memory, but for now, it is stored in the filesystem.



In our previous example, the schema was as follows:

```json
{
  "fields":
    {
        "id": "Integer",
        "title": "String",
        "content": "String"
    }
}
```

It means that each document must have these 3 fields, and each field must have the correct type.

Let's say we want to index the following article:

```json
{
    "id": 1,
    "title": "My first article",
    "content": "The quick brown fox jumps over the lazy dog."
}
```

You need to wrap the document you want to index inside a `data` key, like this:

```json
{
    "data": {
        "id": 1,
        "title": "My first article",
        "content": "The quick brown fox jumps over the lazy dog."
    }
}
```

To index this document, you need to send a POST request to the `/index` endpoint with the document JSON in the request body. Here's an example using `curl`:

```bash
curl -X POST http://localhost:8080/index -H "Content-Type: application/json" -d '{
  "data": {
      "id": 1,
      "title": "My first article",
      "content": "The quick brown fox jumps over the lazy dog."
  }
}'
```

If the document was successfully indexed, you should receive a response with this content:

```json
{
    "message": "Successfully indexed document.",
    "httpStatus": "201 CREATED"
}
```

You can index as many documents as you want, as long as they match the schema.