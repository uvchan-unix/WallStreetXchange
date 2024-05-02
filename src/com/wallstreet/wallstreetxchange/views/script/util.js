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

function doPost(url,data,callback,startAnimation,stopAnimation) {
    startAnimation()
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
        mode: 'no-cors'
    };
    
    fetch(url, options)
        .then(response => {
            if (response.ok) {
                return response.json(); 
            } 
        })
        .then(data => {
            stopAnimation()
            console.log('Response data:', data);
            callback(data)
        })
        .catch(error => {
            stopAnimation()
            console.error('There was a problem with the fetch operation:', error);
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
            "watchlist": ["BSE:ITC", "BSE:RPOWER"],
            "details": true,
            "hotlist": true,
            "calendar": false,
            "support_host": "https://www.tradingview.com",
            "container_id": "chart-container"
        }
    );

    
}

function showPopup(content,color) {
    let popup = document.getElementById('info-popup');
    popup.textContent = content;
    popup.style.display = 'block';
    popup.style.backgroundColor = color;
    setTimeout(function() {
        popup.style.display = 'none';
    }, 3000);
}

document.addEventListener("DOMContentLoaded", function () {

    console.log("loaded")
    showPopup("welcome","orange")

});
