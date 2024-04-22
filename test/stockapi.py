import requests

def get_itc_price():
    url = "https://query1.finance.yahoo.com/v8/finance/chart/ZOMATO.NS"
    params = {
        "region": "US",
        "lang": "en-US",
        "includePrePost": "false",
        "interval": "1d",
        "range": "1d",
        "corsDomain": "finance.yahoo.com"
    }

    response = requests.get(url, params=params)
    
    try:
        data = response.json()
    except Exception as e:
        print("Error decoding JSON response:", e)
        print("Response content:", response.content)
        return None

    if 'error' in data:
        print("Error:", data['error']['description'])
        return None

    current_price = data['chart']['result'][0]['meta']['regularMarketPrice']
    return current_price

itc_price = get_itc_price()
if itc_price is not None:
    print("Current price of ITC shares:", itc_price)
