let map;

// Prikaz mape
document.addEventListener("DOMContentLoaded", function () {
    map = L.map('map').setView([45.815399, 15.966568], 13); // Zagreb koordinate, default zoom 13

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '© OpenStreetMap'
    }).addTo(map);

    ispis(); // Prikaz markera odmah pri učitavanju
});

const problemList = document.getElementById("problemList");
const filterStatusCheckbox = document.getElementById("filterStatus");

// Geo kodiranje adrese
function geocodeAddress(address, callback) {
    const url = `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(address)}`;
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

// Custom markeri
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

// Dohvat problema i prikaz na mapi
function ispis() {
    const allP = "/problems/all";
    const notWorkingOnes = "/problems/notWorking";

    const endPoint = filterStatusCheckbox.checked ? notWorkingOnes : allP;

    // Očisti mapu i listu
    map.eachLayer(layer => {
        if (layer instanceof L.Marker) {
            map.removeLayer(layer);
        }
    });

    problemList.innerHTML = ""; // Očisti listu

    fetch(endPoint)
        .then(response => {
            if (!response.ok) throw new Error("Ne mogu pronaći podatke.");
            return response.json();
        })
        .then(data => {
            data.forEach(problem => {
                const cityAdress = `${problem.adress}, Zagreb`; // Predrazuje samo adrese u Zagrebu

                geocodeAddress(cityAdress, (latitude, longitude) => { // funkcija za dobivanje kordinate
                    if (latitude && longitude) {                                       // iz adrese
                        // Odabir markera
                        const markerIcon = problem.status === "1" ? zeleniMarker : crveniMarker;

                        // Dodavanje markera na mapu

                        if(problem.status == "1"){
                            L.marker([latitude, longitude], { icon: markerIcon })
                                .addTo(map)
                                .bindPopup(`
                                <b>Adresa:</b> ${problem.adress}<br>
                                <b>Korištenje:</b> ${problem.usage}W<br>
                                <b>Radni sati:</b> ${problem.workHours}H<br>
                            `);
                        }else {
                            L.marker([latitude, longitude], { icon: markerIcon })
                                .addTo(map)
                                .bindPopup(`
                                <b>Adresa:</b> ${problem.adress}<br>
                                <b>Korištenje:</b> ${problem.usage}W<br>
                                <b>Radni sati:</b> ${problem.workHours}H<br>
                                <b>Opis:</b> ${problem.description}<br>
                            `);
                        }

                        // Dodavanje problema u listu
                        const listItem = document.createElement("li");

                        if(problem.status == "1"){
                            listItem.innerHTML = `
                            <b>Adresa:</b> ${problem.adress}<br>
                            <b>Korištenje:</b> ${problem.usage}W<br>
                            <b>Radni sati:</b> ${problem.workHours}H<br>
                            <b>Status:</b> Working <br>
                            <img class="delete-icon" src="/images/x-mark.png" alt="Delete" title="Delete problem" />
                        `;
                        }else{
                            listItem.innerHTML = `
                            <b>Adresa:</b> ${problem.adress}<br>
                            <b>Korištenje:</b> ${problem.usage}W<br>
                            <b>Radni sati:</b> ${problem.workHours}H<br>
                            <b>Status:</b> Out of order<br>
                            <b>Opis:</b> ${problem.description}<br>
                            <img class="delete-icon" src="/images/x-mark.png" alt="Delete" title="Delete problem" />
                        `;
                        }

                        // Dodavanje brisanja
                        listItem.querySelector(".delete-icon").addEventListener("click", () => {
                            if (confirm("Jeste li sigurni da želite obrisati ovaj problem?")) {
                                fetch(`/problems/delete?adress=${problem.adress}`, { method: "DELETE" })
                                    .then(response => {
                                        if (response.ok) {
                                            listItem.remove(); // Uklanja element iz liste
                                            alert("Problem uspješno obrisan.");
                                            ispis(); // Ponovno učitavanje podataka
                                        } else {
                                            alert("Došlo je do pogreške prilikom brisanja problema.");
                                        }
                                    })
                                    .catch(err => {
                                        console.error("Greška prilikom brisanja problema:", err);
                                        alert("Nije moguće obrisati problem.");
                                    });
                            }
                        });
                        problemList.appendChild(listItem);
                    } else {
                        console.warn(`Problem "${problem.description}" nije prikazan jer koordinate nisu pronađene.`);
                    }
                });
            });
        })
        .catch(error => console.error("Greška prilikom dohvaćanja problema:", error));
}

// event listener za checkbox za ne radeće
filterStatusCheckbox.addEventListener("change", ispis);
