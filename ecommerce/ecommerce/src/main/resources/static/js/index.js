 const getProducts=async()=>{
    return fetch("/api/products").then(r=>r.json())
}

const getCurrentOffer=()=>{
    return fetch("/api/current-offer").then(r=>r.json())
}

const handleAddToCart =(productId)=>{
    return fetch("api/add-product/{productId}",{
        method:"POST",

    })
}

const refreshOffer = async()=>{
    const offer = await getCurrentOffer()
    const offerEl = document.querySelector(".offer")
    offerEl.querySelector("span.total")
        .textContent = `${offer.total} PLN`
    offerEl.querySelector("span.itemsCount")
        .textContent = `${offer.itemsCount} items`
}
const initializeAddToCartHandler=(productEl)=>{
    productEl.querySelector('button').addEventListener("click",(e)=>{
        const btn = e.target;
        const productId = btn.getAttribute("data-id")
        handleAddToCart(productId)
            .then(()=>refreshOffer())
            .catch((error)=>console.log(error))
    })
    return productEl;
}
const createHtmlEl=(product)=>{
    const template = `
    <div class = "product">
        <h4>${product.name}</h4>
        <p>${product.desc}<//p>
        <img src="https://picsum.photos/id/237/200/300"/>
        <div class="product__price">
            <span>${product.price}</span>
            <button data-id="${product.id}">add to cart</button>
        </div>
        
    </div>
    `;
    const el=document.createElement("li")
    el.innerHTML=template.trim();
    return el;
}
(()=>{
    const productList = document.querySelector(".products")
    getProducts()
        .then(products=>products.map(createHtmlEl))
        .then(htmlProps=>htmlProps.map(initializeAddToCartHandler))
        .then(htmlProps=>htmlProps.forEach(el=>productList.appendChild(el)))
    refreshOffer()
        .then(()=>console.log("Offer refreshed "))
    const checkoutForm = document.querySelector("form.checkout")
    checkoutForm.addEventListener("submit",(e)=>{
        e.preventDefault();
        const data = new FormData(checkoutForm);
        let request = {};
        for (let [key,value] of data){
            request[key]= value;
        }
        fetch("/api/accept-offer",{
            method:"POST",
            headers:{
                "Content-Type":"application/json",
            },
            body:JSON.stringify(request)
        }).then(r=>r.json())
            .then(data=>window.location.href=data.paymentUrl)
    });
})();