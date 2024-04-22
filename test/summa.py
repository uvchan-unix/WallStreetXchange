import requests
from bs4 import BeautifulSoup

# Define the URL
url = "https://www.google.com/finance/quote/ITC:NSE"

# Send a GET request to the URL
response = requests.get(url)

# Check if the request was successful (status code 200)
if response.status_code == 200:
    # Parse the HTML content of the webpage
    soup = BeautifulSoup(response.content, 'html.parser')
    
    # Find the element containing the price data
    price_element = soup.find("span", class_="YMlKec fxKbKc")
    
    print (response.content)
    if price_element:
        # Extract the price text
        price = price_element.text
        print("Current price:", price)
    else:
        print("Price data not found on the webpage.")
else:
    print("Failed to fetch webpage. Status code:", response.status_code)
