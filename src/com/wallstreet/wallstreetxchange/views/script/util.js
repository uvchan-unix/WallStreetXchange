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

function doPost(url, data, callback, startAnimation, stopAnimation) {
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

function getEndpoint(url) {
    let parsedUrl = new URL(url);
    console.log(parsedUrl.pathname)
    return parsedUrl.pathname;
}

function forceReflow(element) {
    return element.offsetHeight;
}

function reloadStylesheet(sectionName) {
    const head = document.head;
    const linkSelector = `link[href*="${sectionName}.css"]`; // Construct a selector to find the relevant link
    const oldLink = head.querySelector(linkSelector);

    if (oldLink) {
        const newLink = document.createElement('link');
        newLink.rel = 'stylesheet';
        newLink.href = oldLink.href.replace(/(\?.*)?$/, `?timestamp=${new Date().getTime()}`); // Append a timestamp to force reload

        // Copy over any other relevant attributes
        Array.from(oldLink.attributes).forEach(attr => {
            if (attr.name !== 'href') { // Ensure we don't overwrite the new href
                newLink.setAttribute(attr.name, attr.value);
            }
        });

        // Handling load and error events
        newLink.onload = () => {
            console.log(`${newLink.href} has been loaded and applied.`);
            head.removeChild(oldLink); // Remove old link after new one has loaded
        };
        newLink.onerror = () => console.log(`Failed to load ${newLink.href}`);

        head.appendChild(newLink); // Append new link
    } else {
        console.log(`No stylesheet found for ${sectionName}`);
    }
}


function loadInnerHtml(elements) {

    let mainBody = document.querySelector(".main-body-content")
    mainBody.innerHTML = elements

    // forceReflow(mainBody)
    // const head = document.head;
    // const links = head.querySelectorAll('link[rel="stylesheet"]');

    // links.forEach(link => {
    //     const newLink = document.createElement('link');
    //     newLink.rel = 'stylesheet';
    //     newLink.href = link.href;
    //     head.appendChild(newLink);
    //     head.removeChild(link)
    // });

    
    
}

// function loadDynamicScript() {

// }

// dynamicStyles = []
// dynamicScripts = []

// function loadDynamicCss(filename) {
//     var link = document.createElement('link');
//     link.rel = 'stylesheet';
//     link.type = 'text/css';
//     link.href = `/WallStreetXchange/src/com/wallstreet/wallstreetxchange/views/css/${filename}`;
//     document.head.appendChild(link);
//     dynamicStyles.push(link);
// }

function loadDynamicPage(urlName, startAnimation, stopAnimation) {

    startAnimation();
    urlName = urlName.toLocaleLowerCase()
    console.log(urlName)
    fetch("http://localhost:8080/WallStreetXchange/src/com/wallstreet/wallstreetxchange/views/html/" + urlName + ".html")
        .then(response => {
            stopAnimation();
            return response.text();
        })
        .then(data => {
            loadInnerHtml(data);
            executeScripts(data)
        })
        .catch(error => {
            console.error('Request failed with error:', error);
            stopAnimation();
        });

    // Function to change the URL to an absolute path
    function changeUrl(title, newPath) {
        if (typeof history.pushState !== 'undefined') {
            // Constructing an absolute URL
            var baseUrl = window.location.protocol + '//' + window.location.host;
            var fullPath = baseUrl + '/WallStreetXchange/user/ui/' + newPath;

            var stateObj = { newUrl: fullPath };
            history.pushState(stateObj, title, fullPath);
            document.title = title;
        } else {
            console.warn('Browser does not support HTML5 pushState.');
        }
    }

    // Usage: set the URL to 'portfolio.html' under the specified path
    // changeUrl('WallStreet', urlName);

    reloadStylesheet(urlName)
    

}

function executeScripts(html) {
    const scripts = new DOMParser().parseFromString(html, "text/html").querySelectorAll('script');
    scripts.forEach(script => {
        const newScript = document.createElement('script');
        if (script.src) {
            newScript.src = script.src;
        }
        newScript.textContent = script.textContent;
        document.body.appendChild(newScript);
        if (!script.src) {
            document.body.removeChild(newScript);
        }
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

function showPopup(content, color) {
    let popup = document.getElementById('info-popup');
    popup.textContent = content;
    popup.style.display = 'block';
    popup.style.backgroundColor = color;
    setTimeout(function () {
        popup.style.display = 'none';
    }, 3000);
}

document.addEventListener("DOMContentLoaded", function () {

    console.log("loaded")
    showPopup("welcome", "orange")

});


function setCookie(cname, cvalue, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = cname + "=" + (cvalue || "") + expires + "; path=/";
}

function getCookie(cname) {

    if (document.cookie != null) {
        let cookies = document.cookie.split(";");
        cname = cname.trim();

        for (let i = 0; i < cookies.length; i++) {
            let c = cookies[i].trim().split("=");
            console.log(c)
            if (c[0] == cname) {
                return c[1];
            }
        }
    }
    return null;
}

function reloadStylesheet(sectionName) {
    const head = document.head;
    const linkSelector = `link[href*="${sectionName}.css"]`; // Construct a selector to find the relevant link
    const oldLink = head.querySelector(linkSelector);

    if (oldLink) {
        const newLink = document.createElement('link');
        newLink.rel = 'stylesheet';
        newLink.href = oldLink.href.replace(/(\?.*)?$/, `?timestamp=${new Date().getTime()}`); // Append a timestamp to force reload

        // Copy over any other relevant attributes
        Array.from(oldLink.attributes).forEach(attr => {
            if (attr.name !== 'href') { // Ensure we don't overwrite the new href
                newLink.setAttribute(attr.name, attr.value);
            }
        });

        // Handling load and error events
        newLink.onload = () => {
            console.log(`${newLink.href} has been loaded and applied.`);
            head.removeChild(oldLink); // Remove old link after new one has loaded
        };
        newLink.onerror = () => console.log(`Failed to load ${newLink.href}`);

        head.appendChild(newLink); // Append new link
    } else {
        console.log(`No stylesheet found for ${sectionName}`);
    }
}
