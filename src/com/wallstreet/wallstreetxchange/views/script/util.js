function doGet(url, callback, startAnimation, stopAnimation) {
    startAnimation();

    fetch(url)
        .then(response => {
            stopAnimation();
            return response.json();
        })
        .then(data => callback(data))
        .catch(error => {
            console.error('Request failed with error:', error);
            stopAnimation();
        });
}

function loadDynamicPage(url, callback) {

    fetch(url)
        .then(response => {
            return response;
        })
        .then(data => callback(data))
        .catch(error => {
            console.error('Request failed with error:', error);

        });


}

function loadChart(symbol, watchlist) {

    new TradingView.widget(
        {
            "autosize": true,
            "symbol": symbol,
            "interval": "D",
            "timezone": "Etc/UTC",
            "theme": "dark",
            "style": "1",
            "locale": "en",
            "enable_publishing": false,
            "withdateranges": true,
            "hide_side_toolbar": false,
            "allow_symbol_change": true,
            "watchlist": ["BSE:ITC","BSE:RPOWER"],
            "details": true,
            "hotlist": true,
            "calendar": false,
            "support_host": "https://www.tradingview.com",
            "container_id": "chart-container"
        }
    );
}

document.addEventListener("DOMContentLoaded", function () {

    console.log("loaded")
    

});
