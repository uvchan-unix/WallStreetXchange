
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
        margin:0px 0.8px;
        display: block;
        width: 10px;
        height: 30px;
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

function openCloseSidebar() {
    let sidebarController = document.querySelector(".menu-open-close-controller")
    let sidebar = document.querySelector(".sidebar-wrapper")
    let menuSlider = document.querySelector(".menu-slider")
    sidebar.classList.toggle("open")
    menuSlider.classList.toggle("open")
    sidebarController.classList.toggle("open")
}

function moveMenuSlider(menuIndex,reset) {

    let menuicon = document.querySelectorAll(".menu-icon")
    let menuname = document.querySelectorAll(".menu-name")
    let header = document.querySelector(".section-header")

    if (!reset) {
        
        loadDynamicPage(menuname[menuIndex].textContent, () => { }, () => { })
    } 
    header.textContent = menuname[menuIndex].textContent
    menuicon.forEach(function (menu, index) {
        if (menuIndex == index) {
            menuicon[menuIndex].classList.add("active")

        } else {
            menu.classList.remove("active")

        }


    })


    if (menuIndex === 0) {
        document.documentElement.style.setProperty("--slider-position", "7rem");
        document.querySelector(".chart-footer").classList.add("active")
    }
    else {
        document.querySelector(".chart-footer").classList.remove("active")
        document.documentElement.style.setProperty("--slider-position", `${(menuIndex * 4.5) + 7}rem`);
    }
}

