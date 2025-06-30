 const getProducts=async()=>{
    return fetch("/api/products").then(r=>r.json())
}
const createHtmlEl=(product)=>{
    const template = `
        <h4>${product.name}</h4>
        <p>${product.desc}<//p>
        <span>${product.price}</span>
        <button>add to cart</button>
    `;
    const el=document.createElement("li")
    el.innerHTML=template.trim();
    return el;
}
(()=>{
    const productList = document.querySelector(".products")
    getProducts()
        .then(products=>products.map(createHtmlEl))
        .then(htmlProps=>htmlProps.forEach(el=>productList.appendChild(el)))
})();