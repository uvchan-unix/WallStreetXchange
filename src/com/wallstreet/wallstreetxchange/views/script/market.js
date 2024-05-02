function showOrderPopup(value) {
  let popup = document.querySelector(".popup");
  let orderExeBtn = document.getElementById("execute-order-btn");
  popup.classList.add("open-popup");
  if (value) {
    orderExeBtn.style.backgroundColor = "green";
    orderExeBtn.textContent = "Execute Buy"
  }
  else {
    orderExeBtn.style.backgroundColor = "red";
    orderExeBtn.textContent = "Execute Sell"
  }
 
}

function hideOrderPopup() {
  let popup = document.querySelector(".popup");
  popup.classList.remove("open-popup")

}

function loadOrderPopup(symbol, price, funds) {

  document.getElementById("symbol").textContent = symbol;
  document.getElementById("price").textContent = "₹ " + price;
  document.getElementById("available-funds").textContent = "₹ " + funds;

  let requiredFunds = document.getElementById("required-funds");
  requiredFunds.textContent = "₹ " + price

  let quantity = document.getElementById("quantity");
  quantity.addEventListener("input", function () {
    requiredFunds.textContent = "₹ " + (price * quantity.value)
  })

 
}

function getTradeMetaData() {
  let symbol = document.getElementById("symbol").textContent;
  let quantity = document.getElementById("quantity").value;
  let type = document.getElementById("execute-order-btn").textContent.trim().split(" ")[1];

  let json = {
    "stockSymbol": symbol,
    "quantity": quantity,
    "transactionType": (type === "Buy") ? true : false
  };

  console.log(type)
  console.log(json)
  return json;
}


document.addEventListener("DOMContentLoaded", function () {

  document.querySelector(".place-order-btn").addEventListener("click", hideOrderPopup);
 
  let orderExeBtn = document.getElementById("execute-order-btn");
  orderExeBtn.addEventListener("click",function(){
    let json = getTradeMetaData();
    let url = "http://localhost:8080/WallStreetXchange/stocks/trade"
    doPost(url,json,(data)=>{console.log(data)},()=>{},()=>{})
    hideOrderPopup()
    showPopup(`${json.transactionType == true ? "Buy" : "Sell"} Order Successfull( ${json.stockSymbol}, ${json.quantity} Quantity)`,"#38ed75")
  })

})