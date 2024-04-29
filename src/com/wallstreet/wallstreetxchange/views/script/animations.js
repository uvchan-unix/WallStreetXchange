
function startAnimation() {
    console.log('Animation started');
}

function stopAnimation() {
    console.log('Animation stopped');
}

function searchbarLoader() {
    let displaybox = document.querySelector(".search-result-wrapper")
    displaybox.innerHTML = `<div class="load-1">
    <div class="line"></div>
    <div class="line"></div>
    <div class="line"></div>
  </div>
    <style>
    .load-1 {
        height: 100%;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    
    .line {
        margin:0px 0.5px;
        display: block;
        width: 6px;
        height: 20px;
        border-radius: 4px;
        background-color: #4b9cdb;
        transform-origin: bottom; /* Set transform origin to bottom */
    }
    
    .load-1 .line:nth-last-child(1) {
        background-color: rgb(95, 205, 95);
        animation: loadingA 1.5s 0.6s infinite;
    }
    
    .load-1 .line:nth-last-child(2) {
        background-color: rgb(242, 47, 47);
        animation: loadingA 1.5s 0.3s infinite;
    }
    
    .load-1 .line:nth-last-child(3) {
        background-color: rgb(95, 205, 95);
        animation: loadingA 1.5s 0s infinite;
    }
    
    @keyframes loadingA {
        0% {
            transform: scaleY(0); 
        }
        50% {
            transform: scaleY(1); 
        }
        100% {
            transform: scaleY(0); 
        }
    }
    
    </style>`
}   