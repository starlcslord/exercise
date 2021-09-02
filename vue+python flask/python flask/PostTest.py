import requests, json

data = {
    "key":"周杰伦",
    "num":"3"
}
url = 'http://127.0.0.1:5000/add/student/'
# r = requests.post(url, data=data)
r = requests.post(url, json.dumps(data))
print(data)
print(r.text)