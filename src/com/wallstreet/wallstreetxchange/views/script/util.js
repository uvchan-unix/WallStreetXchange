document.addEventListener("DOMContentLoaded", function() {
    function doGet(url, callback) {
        fetch(url)
            .then(response => response.json())
            .then(data => callback(data))
            .catch(error => console.error('Request failed with error:', error));
    }

    function display(response) {
        let html = ""; 
    
        response.forEach(stock => {
            
            let stockHtml = template.replace("#RELIANCE POWER", stock.stockName)
                                    .replace("#RPOWER(NSE/BSE)", stock.stockSymbol + " (" + stock.stockExchange + ")")
                                    .replace("#$50", stock.currentPrice)
                                    
    
            
            html += stockHtml;
        });
    
        displaybox.innerHTML = html;
    }
    

    let searchbox = document.querySelector(".search-box");
    let displaybox = document.querySelector(".search-result-wrapper")
    let template = `<div class="search-result-stocks">
                        <div class="search-result-infoone">
                            <span class="search-stock-result-name">#RELIANCE POWER</span>
                            <span class="search-stock-result-symbol">#RPOWER(NSE/BSE)</span>
                        </div>
                        <div class="search-result-infotwo">
                            <span class="search-stock-result-exchange">#$50</span>
                            <span class="search-stock-result-price">#22%</span>
                        </div>
                    </div>`;

    searchbox.addEventListener('input', function() {
        displaybox.innerHTML = " ";
        doGet("http://localhost:8080/WallStreetXchange/services/stocks/getStocks?prefix="+searchbox.value, display);
    });
});
