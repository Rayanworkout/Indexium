import requests


BASE_URL = "http://127.0.0.1:8080"

payload = {
    "field": {
        "title": "String",
        "content": "String"
    }
}

# Send the POST request
response = requests.post(BASE_URL + "/schema/set", json=payload)

# Print the status code and response data
print("Status Code:", response.status_code)
print("Response JSON:", response.json())
