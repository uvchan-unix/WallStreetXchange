function removeAllSearchResults() {
    let searchResults = document.querySelectorAll('.search-result-stocks');
    console.log(searchResults)
    searchResults.forEach(function (result) {
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
        searchStockResultPrice.textContent = stock.todayMovement+"%";
        if(stock.todayMovement < 0){
            searchStockResultPrice.style.backgroundColor = "red"
        }

        // Append the exchange and price spans to the info two div
        searchResultInfoTwo.appendChild(searchStockResultExchange);
        searchResultInfoTwo.appendChild(searchStockResultPrice);

        // Append the info one and info two divs to the search result stocks div
        searchResultStocks.appendChild(searchResultInfoOne);
        searchResultStocks.appendChild(searchResultInfoTwo);
        searchResultStocks.addEventListener("click", function () {
            loadChart("BSE:" + stock.stockSymbol, "BSE:ITC")
            let footerStockName = document.querySelector(".chart-footer-stockdetails-name")
            let footerStockSymbol = document.querySelector(".chart-footer-stockdetails-symbol")
            footerStockName.textContent = stock.stockName;
            footerStockSymbol.textContent = stock.stockSymbol + "(NSE/BSE)";
            loadOrderPopup(stock.stockSymbol, stock.currentPrice, 100000)
            moveMenuSlider(0,true)
            setCookie("marketstock",stock.stockSymbol)
        })
        // Append the search result stocks div to the container
        searchResultContainer.appendChild(searchResultStocks,stock.stockSymbol,10);

    });

}




document.addEventListener("DOMContentLoaded", function () {

    let searchbox = document.querySelector(".search-box");
    let displaybox = document.querySelector(".search-result-wrapper")
    searchbox.addEventListener('input', function () {
        console.log(searchbox.value)
        doGet("http://localhost:8080/WallStreetXchange/services/stocks/getStocks?prefix=" + searchbox.value, displaySearchbox, searchbarLoader, () => {
            displaybox.innerHTML = " "
        });
    });

    let BSoperation = document.querySelector(".chart-footer-stockoperation");
    let url = "http://localhost:8080/WallStreetXchange/stocks/trade";
    let data = {
        "stockSymbol": "ITC",
        "quantity": 200,
        "transactionType": false
    }

    BSoperation.addEventListener("click", function (e) {
        console.log(e.target)
        if (e.target.classList.contains("chart-footer-stockoperation-buy-btn")) {
            showOrderPopup(true)
            // doPost(url, data, (data) => console.log(data), () => { }, () => { })
        }
        else if (e.target.classList.contains("chart-footer-stockoperation-sell-btn")) {
            showOrderPopup(false)
            // doPost(url, data, (data) => console.log(data), () => { }, () => { })
        }
    });

    let sideBarMenu = document.querySelectorAll(".sidebar-menu-container ul li");
    
    console.log(sideBarMenu)
    sideBarMenu.forEach(function (ele,index) {
        ele.addEventListener("click",function(event){
            event.preventDefault(); 
            moveMenuSlider(index);
        })
    })

    document.querySelector(".menu-open-close-controller").addEventListener("click",function(){
        openCloseSidebar()
    })


});

function executeOrder(orderType, price) {
    const quantity = document.getElementById('quantity').value;
    // Assuming you have a function to calculate total funds required
    const totalFundsRequired = calculateTotalFunds(quantity, price);
    document.getElementById('order-type').textContent = `Order Type: ${orderType}`;
    document.getElementById('total-funds').innerText = `Funds Required: $${totalFundsRequired.toFixed(2)}`;
}

// Function to calculate total funds required
function calculateTotalFunds(quantity, price) {
    // Your calculation logic goes here
    // For example, let's say each unit costs $10
    return quantity * price;
}
