# Some code examples

This page demonstrates some example usage of Indexium using code snippets of various languages.

## Python

```python
import requests

base_url = "http://localhost:8080/"

schema = {
    "fields": {
        "id": "Integer",
        "title": "String",
        "content": "String"
    }
}

# Define the schema
response = requests.post(base_url + "schema/set", json=schema)

print(response.json())

# Index a document

document = {
    "data": {
        "id": 1,
        "title": "My first article",
        "content": "The quick brown fox jumps over the lazy dog."
    }
}

response = requests.post(base_url + "index", json=document)

print(response.json())


# Search for content

response = requests.get(base_url + "search?field=content&q=fox")

print(response.json())
```
