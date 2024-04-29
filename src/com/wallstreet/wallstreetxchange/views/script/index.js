function removeAllSearchResults() {
    let searchResults = document.querySelectorAll('.search-result-stocks');
    console.log(searchResults)
    searchResults.forEach(function(result) {
        result.remove();
    });
}

// function to display searchbox content

function displaySearchbox(response) {

    // let template = `<div class="search-result-stocks">
    //                         <div class="search-result-infoone">
    //                             <span class="search-stock-result-name">#RELIANCE POWER</span>
    //                             <span class="search-stock-result-symbol">#RPOWER(NSE/BSE)</span>
    //                         </div>
    //                         <div class="search-result-infotwo">
    //                             <span class="search-stock-result-exchange">#$50</span>
    //                             <span class="search-stock-result-price">#22%</span>
    //                         </div>
    //                     </div>`;

    
    let searchResultContainer = document.querySelector('.search-result-wrapper');
    response.forEach(stock => {
        // console.log("done")
        // let searchResultContainer = document.querySelector('.search-result-wrapper');

        // Create the search result stocks div
        let searchResultStocks = document.createElement('div');
        searchResultStocks.classList.add('search-result-stocks');
    
        // Create the search result info one div
        let searchResultInfoOne = document.createElement('div');
        searchResultInfoOne.classList.add('search-result-infoone');
    
        // Create the search stock result name span
        let searchStockResultName = document.createElement('span');
        searchStockResultName.classList.add('search-stock-result-name');
        searchStockResultName.textContent = stock.stockName;
    
        // Create the search stock result symbol span
        let searchStockResultSymbol = document.createElement('span');
        searchStockResultSymbol.classList.add('search-stock-result-symbol');
        searchStockResultSymbol.textContent = stock.stockSymbol;
    
        // Append the name and symbol spans to the info one div
        searchResultInfoOne.appendChild(searchStockResultName);
        searchResultInfoOne.appendChild(searchStockResultSymbol);
    
        // Create the search result info two div
        let searchResultInfoTwo = document.createElement('div');
        searchResultInfoTwo.classList.add('search-result-infotwo');
    
        // Create the search stock result exchange span
        let searchStockResultExchange = document.createElement('span');
        searchStockResultExchange.classList.add('search-stock-result-exchange');
        searchStockResultExchange.textContent = stock.currentPrice;
    
        // Create the search stock result price span
        let searchStockResultPrice = document.createElement('span');
        searchStockResultPrice.classList.add('search-stock-result-price');
        searchStockResultPrice.textContent = "#22%";
    
        // Append the exchange and price spans to the info two div
        searchResultInfoTwo.appendChild(searchStockResultExchange);
        searchResultInfoTwo.appendChild(searchStockResultPrice);
    
        // Append the info one and info two divs to the search result stocks div
        searchResultStocks.appendChild(searchResultInfoOne);
        searchResultStocks.appendChild(searchResultInfoTwo);
        searchResultStocks.addEventListener("click",function(){
            loadChart("BSE:"+stock.stockSymbol,"BSE:ITC")
        })  
        // Append the search result stocks div to the container
        searchResultContainer.appendChild(searchResultStocks);

    });

}


document.addEventListener("DOMContentLoaded", function () {

    let searchbox = document.querySelector(".search-box");
    let displaybox = document.querySelector(".search-result-wrapper")
    searchbox.addEventListener('input', function () {
        console.log(searchbox.value)
        doGet("http://localhost:8080/WallStreetXchange/services/stocks/getStocks?prefix=" + searchbox.value, displaySearchbox, searchbarLoader, ()=>{
            displaybox.innerHTML = " "
        });
    });

    
});

