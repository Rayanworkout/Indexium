# Defining a Schema

Once the app is running, you are now able to index your first documents. The API is a RESTful API that allows you to interact with the app using HTTP requests.


##

The first step is to create a schema. Without a schema, you won't be able to index any document. The schema defines the structure of the documents you want to index.

Let's say we need to index some blog articles, so our users will be able to easily search for them. We could define a simple schema with this structure:

```json{2}
{
  "fields":
    {
        "id": "Integer",
        "title": "String",
        "content": "String"
    }
}
```

Note that the schema is wrapped in a JSON object with a `fields` key. Each field in the schema is defined by a key-value pair, where the key is the field name and the value is the field type.

Allowed field types are:
- `String`
- `Integer`
- `Boolean`
- `Double`

To create a schema, you need to send a POST request to the `/schema/set` endpoint with the schema JSON in the request body. Here's an example using `curl`:

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

If everything went well, you should receive a response with this content:

```json
{
    "message": "Schema defined successfully.",
    "httpStatus": "201 CREATED"
}
```

Otherwise, you will receive an error message with the reason why the schema couldn't be created.

For example, if you try to create a schema with a field that has an invalid type, you will receive an error message like this:

```json
{
    "status": "400 BAD_REQUEST",
    "message": "Schema contains one or more invalid type(s). Allowed types are [Integer, String, Boolean, Double]"
}
```

At any moment, if you wish to see the schema you have defined, you can send a GET request to the `/schema/get` endpoint:

```bash
curl -X GET http://localhost:8080/schema/get
```

Which will return the schema JSON you have defined:

```json
{
    "id": "integer",
    "title": "string",
    "content": "string"
}
```

::: warning
A schema is not persistent. If you restart the app, you will need to define the schema again.
:::