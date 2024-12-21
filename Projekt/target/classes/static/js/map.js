let map;

// funkcija za prikaz mape
document.addEventListener("DOMContentLoaded", function () {
    map = L.map('map').setView([45.815399, 15.966568], 13); // Zagreb koordinate, default zoom 13

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '© OpenStreetMap'
    }).addTo(map);
});
const problemList = document.getElementById("problemList");
const filterStatusCheckbox = document.getElementById("filterStatus");



// funkcija za dobivanje geo kordinata iz zadane adrese
function geocodeAddress(address, callback) {
    const url = `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(address)}`;
    console.log(`Geocoding URL: ${url}`);

    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.length > 0) {
                const latitude = data[0].lat;
                const longitude = data[0].lon;
                callback(latitude, longitude);
            } else {
                callback(null, null);
            }
        })
        .catch(error => console.error("Greška prilikom geokodiranja:", error));
}

// constom markeri koje ćemo prikazivati na mapi
const zeleniMarker = L.icon({
    iconUrl: '/images/zeleniMarker.png',
    iconSize: [32, 32],
    iconAnchor: [16, 32],
    popupAnchor: [0, -32]
});

const crveniMarker = L.icon({
    iconUrl: '/images/crveniMarker.png',
    iconSize: [32, 32],
    iconAnchor: [16, 32],
    popupAnchor: [0, -32]
});




// dohvat problema i prikaz na karti

function ispis(){

    const allP = "/problems/all";
    const notWorkingOnes = "/problems/notWorking"

    const endPoint = filterStatusCheckbox.checked ? notWorkingOnes : allP;

    fetch(endPoint).
        then(response => {
            if (!response.ok) {
                throw new Error("Ne mogu naci");
            }
            return response.json();
    }).then(data => {

        const listContainer = document.querySelector(".div_list ol"); // Odabir liste u HTML-u
        data.forEach(problem => {

            const cityAdress = `${problem.adress}, Zagreb`; // Samo adrese u Zgu => kasnije promijeniti
            const listItem = document.createElement("li");

            map.eachLayer(layer => { // Čišćenje mapr prijašnjih markera
                if (layer instanceof L.Marker) {
                    map.removeLayer(layer);
                }
            });

            problemList.innerHTML = ""; // Čićnje liste prijašnjih entrysa

            geocodeAddress(cityAdress, (latitude, longitude) => { // Šaljemo adresu i dobivamo kordinate

                if (latitude && longitude) {
                    if (problem.status === "1") { // Ako je radi ispiši zeleni marker
                        L.marker([latitude, longitude], {
                            icon: zeleniMarker
                        }).addTo(map)
                            .bindPopup(`
                                <b>Adresa:</b> ${problem.adress}<br>
                                <b>Korištenje (Usage):</b> ${problem.usage}<br>
                                <b>Radni sati (Work Hours):</b> ${problem.workHours}<br>
                           `)
                            .openPopup();

                        listItem.innerHTML = `
                        <b>Adresa:</b> ${problem.adress}<br>
                        <b>Korištenje (Usage):</b> ${problem.usage}W<br>
                        <b>Radni sati (Work Hours):</b> ${problem.workHours}H<br>
                        <b>Status:</b> Working <br>`

                    } else { // ako ne crveni
                        L.marker([latitude, longitude], {
                            icon: crveniMarker
                        }).addTo(map)
                            .bindPopup(`
                            <b>Adresa:</b> ${problem.adress}<br>
                            <b>Korištenje (Usage):</b> ${problem.usage}<br>
                            <b>Radni sati (Work Hours):</b> ${problem.workHours}<br>
                            <b>Opis:</b> ${problem.description}
                           `)
                            .openPopup();

                        listItem.innerHTML = `
                        <b>Adresa:</b> ${problem.adress}<br>
                        <b>Korištenje (Usage):</b> ${problem.usage}W<br>
                        <b>Radni sati (Work Hours):</b> ${problem.workHours}H<br>
                        <b>Status:</b> Out of order <br>
                        <b>Opis kvara:</b> ${problem.description}`
                    }
                    listContainer.appendChild(listItem);
                } else {
                    console.warn(`Problem "${problem.description}" nije prikazan jer koordinate nisu pronađene.`);
                }
            });
        });
    })
        .catch(error => console.error("Dohvat", error));

}

filterStatusCheckbox.addEventListener("change", ispis);

ispis();