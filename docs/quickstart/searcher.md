# Looking for content

Congratulations if you made it this far! You have successfully indexed your first document. Now, let's see how to search for content with the speed of light.

As a reminder, the schema we defined in the previous section was:

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

And the indexed document was:

```json
{
    "data": {
        "id": 1,
        "title": "My first article",
        "content": "The quick brown fox jumps over the lazy dog."
    }
}
```

To search for content, you need to send a GET request to the `/search` endpoint with the query you want to search for.
For example, let's look for the word "fox" in the content field using `curl`:


```bash
curl -X GET "http://localhost:8080/search?field=content&q=fox"
```

You should receive a response like this:

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

Nice, right? You can search for any content you want, and the search engine will return the results in a blink of an eye.