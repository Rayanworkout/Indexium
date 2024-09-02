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

## JavaScript

```javascript
const axios = require('axios');

const base_url = "http://localhost:8080/";

const schema = {
    "fields": {
        "id": "Integer",
        "title": "String",
        "content": "String"
    }
}

// Define the schema

axios.post(base_url + "schema/set", schema)
    .then(response => {
        console.log(response.data);
    })
    .catch(error => {
        console.error(error);
    });

// Index a document

const document = {
    "data": {
        "id": 1,
        "title": "My first article",
        "content": "The quick brown fox jumps over the lazy dog."
    }
}

axios.post(base_url + "index", document)
    .then(response => {
        console.log(response.data);
    })
    .catch(error => {
        console.error(error);
    });


// Search for content


axios.get(base_url + "search?field=content&q=fox")
    .then(response => {
        console.log(response.data);
    })
    .catch(error => {
        console.error(error);
    });
```


## Rust

```rust
use reqwest::blocking::Client;
use serde_json::json;

fn main() {
    let base_url = "http://localhost:8080/";

    let schema = json!({
        "fields": {
            "id": "Integer",
            "title": "String",
            "content": "String"
        }
    });

    // Define the schema

    let client = Client::new();

    let response = client
        .post(base_url.to_string() + "schema/set")
        .json(&schema)
        .send()
        .unwrap();

    println!("{:?}", response.json::<serde_json::Value>());

    // Index a document

    let document = json!({
        "data": {
            "id": 1,
            "title": "My first article",
            "content": "The quick brown fox jumps over the lazy dog."
        }
    });

    let response = client
        .post(base_url.to_string() + "index")
        .json(&document)
        .send()
        .unwrap();

    println!("{:?}", response.json::<serde_json::Value>());

    // Search for content

    let response = client
        .get(base_url.to_string() + "search?field=content&q=fox")
        .send()
        .unwrap();

    println!("{:?}", response.json::<serde_json::Value>());
}

```
