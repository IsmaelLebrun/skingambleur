document.addEventListener("DOMContentLoaded", function () {

    const caisseCards = document.querySelectorAll(".caisse-card");
    caisseCards.forEach(card => {
       card.addEventListener("click", () => {
           window.location.href = "/caisse/" + card.name;
       });
    });



});